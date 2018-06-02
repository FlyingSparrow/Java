package com.huishu.analysis.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.analysis.vo.NewsVO;
import com.huishu.analysis.vo.ValidationVO;
import com.huishu.config.AnalysisConfig;
import com.huishu.constants.SysConst;
import com.huishu.entity.CityLib;
import com.huishu.entity.KingBaseDgap;
import com.huishu.entity.RecruitmentBak;
import com.huishu.service.RecruitmentBakService;
import com.huishu.vo.DgapData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 分析招聘数据
 *
 * @author wangjianchun
 * @create 2018/5/26
 */
@Component("recruitmentAnalyzer")
public class RecruitmentAnalyzer extends DefaultAnalyzer {

    private static final List<DgapData> STATIC_LIST = new ArrayList<DgapData>();

    @Autowired
    private RecruitmentBakService recruitmentService;
    @Override
    public String getName() {
        return SysConst.RECRIUTMENT;
    }

    @Override
    public void analysis(AnalysisConfig analysisConfig, ThreadPoolExecutor executor, Map<String, String> indexMap) {
        STATIC_LIST.clear();
        if (analysisConfig.isRecruitmentMark()) {
            // 分析招聘数据
            for (int i = 0; i < analysisConfig.getRecruitmentThreadNum(); i++) {
                final int pageNumber = i;
                executor.execute(() -> {
                    Thread currentThread = Thread.currentThread();
                    logger.info("{}:{}招聘数据分析开始", currentThread.getName(), currentThread.getId());

                    try {
                        analysisData(analysisConfig, indexMap, pageNumber);
                    } catch (Exception e) {
                        logger.error("招聘分析异常", e);
                    }
                    logger.info("{}:{}招聘数据分析结束", currentThread.getName(), currentThread.getId());
                });
            }
        }
    }

    /**
     * 分析数据
     *
     * @param analysisConfig
     * @param indexMap
     * @param pageNumber
     */
    private void analysisData(AnalysisConfig analysisConfig, Map<String, String> indexMap, int pageNumber) {
        // 招聘业务逻辑
        // 工资高于 10000 学历是研究生 以上为双高人才
        // 1、获取数据 是否重复数据
        RecruitmentBak recruitmentBak = new RecruitmentBak();
        recruitmentBak.setId(Long.valueOf(indexMap.get(SysConst.RECRIUTMENT)));
        Pageable pageable = new PageRequest(pageNumber, analysisConfig.getTransformNum());
        List<RecruitmentBak> list = recruitmentService.findOneHundred(recruitmentBak, pageable);

        logger.info("招聘分析,读取 {} 条", list.size());

        if (list.size() <= 0) {
            return;
        }

        String newId = list.get(list.size() - 1).getId() + "";
        String oldId = indexMap.get(SysConst.RECRIUTMENT);
        Map<String, String> newIndexMap = new HashMap<>(indexMap);
        if (Long.parseLong(newId) > Long.parseLong(oldId)) {
            newIndexMap.put(SysConst.RECRIUTMENT, newId);
        }

        List<DgapData> saveList = new ArrayList<DgapData>();
        List<com.huishu.entity.RecruitmentBak> readList = new ArrayList<RecruitmentBak>();
        List<KingBaseDgap> historyList = new ArrayList<KingBaseDgap>();
        for (RecruitmentBak item : list) {
            if (isNotExists(STATIC_LIST, item.getFldUrlAddr())) {
                // 分析
                ValidationVO validationVO = ValidationVO.create(item);
                if(validate(validationVO)){
                    NewsVO newsVO = NewsVO.create(item);
                    DgapData dgapData = fillDgapData(newsVO);
                    addKingBaseData(historyList, dgapData);
                    item.setBiaoShi(SysConst.ESDataStatus.EXISTS_IN_ES.getCode());
                    dgapData.setId(String.valueOf(item.getId()));
                    saveList.add(dgapData);
                    STATIC_LIST.add(dgapData);
                }
                if (SysConst.ESDataStatus.NOT_EXISTS_IN_ES.getCode().equals(item.getBiaoShi())) {
                    item.setBiaoShi(SysConst.ESDataStatus.EXCEPTION.getCode());
                }
                readList.add(item);
            }
        }

        if (saveList.size() > 0) {
            saveToFile(saveList, SysConst.RECRIUTMENT, analysisConfig.getSourceMorePath());
            saveToKingBase(historyList);
        }
        if (readList.size() > 0) {
            recruitmentService.save(readList);
        }

        logger.info("招聘分析,入库 {} 条", saveList.size());
        logger.info("招聘分析,分析 {} 条", readList.size());

        recordNum(newIndexMap);
    }

    @Override
    protected boolean validate(ValidationVO validationVO) {
        String publishDate = validationVO.getFldrecddate();
        //岗位工资
        String jobSalary = validationVO.getGwGZ();
        if (StringUtils.isEmpty(validationVO.getAddr())
                || StringUtils.isEmpty(publishDate)
                || StringUtils.isEmpty(jobSalary)) {
            return false;
        }

        if (jobSalary.contains("面议")) {
            return false;
        }
        Long remuneration = calculateRemuneration(jobSalary);
        if(remuneration == null){
            return false;
        }

        int yearIndex = publishDate.indexOf("-");
        if (yearIndex <= 0) {
            return false;
        }

        return true;
    }

    @Override
    protected void fillDateInfoOfDgapData(DgapData dgapData, String fldrecddate) {
        dgapData.setTime(fldrecddate);
        dgapData.setHour(0L);
        int yearIndex = fldrecddate.indexOf("-");
        int monthIndex = fldrecddate.indexOf("-", yearIndex + 1);
        dgapData.setYear(Long.valueOf(fldrecddate.substring(0, yearIndex).trim()));
        if (monthIndex <= 0) {
            return;
        }

        int sIndex = fldrecddate.indexOf(" ", monthIndex + 1);
        int hourIndex = fldrecddate.indexOf(":");
        dgapData.setMonth(Long.valueOf(fldrecddate.substring(yearIndex + 1, monthIndex).trim()));
        if (sIndex > 0) {
            dgapData.setDay(Long.valueOf(fldrecddate.substring(monthIndex + 1, sIndex).trim()));
            if (hourIndex > 0) {
                dgapData.setHour(Long.valueOf(fldrecddate.substring(sIndex + 1, hourIndex).trim()));
            }
        } else {
            dgapData.setDay(Long.valueOf(fldrecddate.substring(monthIndex + 1, fldrecddate.length()).trim()));
        }
    }

    /**
     * 计算岗位薪酬
     * @param jobSalary 岗位工资
     * @return
     */
    private Long calculateRemuneration(String jobSalary){
        Long remuneration = null;
        try {
            int start = jobSalary.indexOf("-");
            if (start == -1) {
                start = 0;
            }
            String tempJobSalary = jobSalary;
            tempJobSalary = tempJobSalary.replaceAll("K", "000");
            tempJobSalary = tempJobSalary.replaceAll("k", "000");
            int end = tempJobSalary.indexOf("元", start);
            int unit = 1;
            if (end == -1) {
                end = tempJobSalary.indexOf("千", start);
                if (end >= 0) {
                    unit = 1000;
                }
            }
            if (end == -1) {
                end = tempJobSalary.indexOf("万", start);
                if (end >= 0) {
                    unit = 10000;
                }
            }
            if (end == -1) {
                end = tempJobSalary.length();
            }
            int ration = 1;
            //工资单位，月薪还是年薪
            int gzUnit = tempJobSalary.indexOf("月", start);
            if (gzUnit == -1) {
                int indexOf = tempJobSalary.indexOf("年", start);
                if (indexOf >= 0) {
                    ration = 12;
                }
            }

            remuneration = Math.round(Double.valueOf(jobSalary.substring(start + 1, end).trim()) * unit) / ration;
        } catch (NumberFormatException e) {
            logger.error("薪酬转换错误：{}", jobSalary, e);
        }

        return remuneration;
    }

    /**
     * 是否双高人才
     * @param item
     * @param remuneration 岗位薪酬
     *
     * @return
     */
    private boolean isDoubleHighTalent(NewsVO item, Long remuneration){
        if (StringUtils.isNotEmpty(item.getXueli()) && remuneration >= 10000
                && analysisConfig.getRecruitmentEdu().indexOf(item.getXueli()) > 0) {
            return true;
        } else {
            return false;
        }
    }

    private void fillAreaInfo(DgapData dgapData, String address) {
        String commonArea = analysisConfig.getCommonArea();
        if (StringUtils.isEmpty(commonArea)) {
            return;
        }

        JSONArray commonAreaArray = com.huishu.utils.StringUtils.split(commonArea, SysConst.COMMA);
        for (int i = 0, size = commonAreaArray.size(); i < size; i++) {
            String area = commonAreaArray.getString(i);
            if (address.indexOf(area) < 0) {
                continue;
            }
            dgapData.setArea(area);
            List<CityLib> city = cityLibService.findByCity(area);
            if (city != null && city.size() > 0) {
                dgapData.setProvince(city.get(0).getProvince());
            }
            break;
        }

        if (StringUtils.isNotEmpty(dgapData.getProvince())) {
            return;
        }

        List<CityLib> cityList = cityLibService.findByTypeLessThan(3);
        for (CityLib item : cityList) {
            String city = item.getCity();
            if (StringUtils.isEmpty(city) || address.indexOf(city) < 0) {
                continue;
            }
            dgapData.setProvince(item.getProvince());
            if (StringUtils.isEmpty(dgapData.getArea())) {
                dgapData.setArea(city);
            }
            break;
        }
    }

    private String searchIndustry(String title) {
        if (StringUtils.isEmpty(title)) {
            return null;
        }

        JSONObject jsonObject = new JSONObject();

        String regex = SysConst.COMMA;
        JSONArray internetKeywordArray = com.huishu.utils.StringUtils.split(
                analysisConfig.getRecruitmentInternetKeywords(), regex);
        int internetCount = com.huishu.utils.StringUtils.countOccurrenceNumber(internetKeywordArray, title);
        jsonObject.put("count", internetCount);
        jsonObject.put("industry", SysConst.IndustryType.INTERNET.getType());

        JSONArray educationKeywordArray = com.huishu.utils.StringUtils.split(
                analysisConfig.getRecruitmentEducationKeywords(), regex);
        int eduCount = com.huishu.utils.StringUtils.countOccurrenceNumber(educationKeywordArray, title);
        filterIndustry(SysConst.IndustryType.EDUCATION.getType(), eduCount, jsonObject);

        JSONArray trafficKeywordArray = com.huishu.utils.StringUtils.split(
                analysisConfig.getRecruitmentTrafficKeywords(), regex);
        int trafficCount = com.huishu.utils.StringUtils.countOccurrenceNumber(trafficKeywordArray, title);
        filterIndustry(SysConst.IndustryType.TRAFFIC.getType(), trafficCount, jsonObject);

        JSONArray tourismKeywordArray = com.huishu.utils.StringUtils.split(
                analysisConfig.getRecruitmentTourismKeywords(), regex);
        int tourismCount = com.huishu.utils.StringUtils.countOccurrenceNumber(tourismKeywordArray, title);
        filterIndustry(SysConst.IndustryType.TOURISM.getType(), tourismCount, jsonObject);

        JSONArray financeKeywordArray = com.huishu.utils.StringUtils.split(
                analysisConfig.getRecruitmentFinanceKeywords(), regex);
        int financeCount = com.huishu.utils.StringUtils.countOccurrenceNumber(financeKeywordArray, title);
        filterIndustry(SysConst.IndustryType.FINANCE.getType(), financeCount, jsonObject);

        return jsonObject.getString("industry");
    }

    @Override
    protected DgapData fillDgapData(NewsVO newsVO) {
        DgapData result = super.fillDgapData(newsVO);

        result.setPublishType(SysConst.PublishType.RECRUITMENT.getCode());
        result.setDataType(SysConst.DataType.RECRUITMENT.getCode());
        fillDateInfoOfDgapData(result, newsVO.getFldrecddate());

        // 地域
        // 站点
        result.setSite(newsVO.getWebname());
        // 招聘数据地区识别
        fillAreaInfo(result, newsVO.getAddr());
        // 行业分析
        result.setIndustry(searchIndustry(newsVO.getFldtitle()));
        result.setJobsName(newsVO.getFldtitle());
        if (StringUtils.isEmpty(newsVO.getGwNum()) || newsVO.getGwNum().contains("若干")) {
            result.setJobsNumber(0L);
        } else {
            Long jobNumber = Long.valueOf(newsVO.getGwNum().replace("人", ""));
            result.setJobsNumber(jobNumber);
        }

        //岗位工资
        String jobSalary = newsVO.getGwGZ();
        Long remuneration = calculateRemuneration(jobSalary);
        result.setJobsRemuneration(remuneration);
        if (isDoubleHighTalent(newsVO, remuneration)) {
            result.setJobsTalentMark(SysConst.JobsTalentMark.DOUBLE_HIGH_TALENT.getCode());
        } else {
            result.setJobsTalentMark(SysConst.JobsTalentMark.NOT_DOUBLE_HIGH_TALENT.getCode());
        }

        result.setPolicyUrl(newsVO.getFldUrlAddr());
        result.setPolicyTitle(newsVO.getFldtitle());

        return result;
    }

}
