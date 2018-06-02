package com.huishu.analysis.impl;

import com.huishu.config.AnalysisConfig;
import com.huishu.config.UnitsConfig;
import com.huishu.constants.SysConst;
import com.huishu.entity.CityLib;
import com.huishu.entity.KingBaseDgap;
import com.huishu.entity.QuitDataBak;
import com.huishu.service.QuitDataBakService;
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
 * 分析投资退出数据
 *
 * @author wangjianchun
 * @create 2018/5/26
 */
@Component("quitAnalyzer")
public class QuitAnalyzer extends DefaultAnalyzer {

    private static final List<DgapData> STATIC_LIST = new ArrayList<DgapData>();

    @Autowired
    private QuitDataBakService quitDataService;
    @Autowired
    private UnitsConfig unitsConfig;

    @Override
    public String getName() {
        return SysConst.QUIT;
    }

    @Override
    public void analysis(AnalysisConfig analysisConfig, ThreadPoolExecutor executor, Map<String, String> indexMap) {
        STATIC_LIST.clear();
        if (analysisConfig.isQuitMark()) {
            // 分析招聘数据
            for (int i = 0; i < analysisConfig.getQuitThreadNum(); i++) {
                final int pageNumber = i;
                executor.execute(() -> {
                    Thread currentThread = Thread.currentThread();
                    logger.info("{}:{}投资退出数据分析开始", currentThread.getName(), currentThread.getId());
                    try {
                        analysisData(analysisConfig, indexMap, pageNumber);
                    } catch (Exception e) {
                        logger.error("投资退出数据分析异常", e);
                    }
                    logger.info("{}:{}投资退出数据分析结束", currentThread.getName(), currentThread.getId());
                });
            }
        }
    }

    /**
     * 分析数据
     * @param analysisConfig
     * @param indexMap
     * @param pageNumber
     */
    private void analysisData(AnalysisConfig analysisConfig, Map<String, String> indexMap, int pageNumber) {
        QuitDataBak quitDataBak = new QuitDataBak();
        quitDataBak.setId(Long.valueOf(indexMap.get(SysConst.QUIT)));
        Pageable pageable = new PageRequest(pageNumber,  analysisConfig.getTransformNum());
        List<QuitDataBak> lists = quitDataService.findOneHundred(quitDataBak, pageable);

        logger.info("退出分析,读取" + lists.size() + "条");

        if (lists != null && lists.size() > 0) {
            String newId = lists.get(lists.size() - 1).getId() + "";
            String oldId = indexMap.get(SysConst.QUIT);
            Map<String, String> newIndexMap = new HashMap<>(indexMap);
            if (Long.valueOf(newId)>Long.valueOf(oldId)) {
                newIndexMap.put(SysConst.QUIT, newId);
            }
            List<DgapData> saveList = new ArrayList<DgapData>();
            List<QuitDataBak> readList = new ArrayList<QuitDataBak>();
            List<KingBaseDgap> historyList = new ArrayList<KingBaseDgap>();
            for (QuitDataBak item : lists) {
                // 分析
                DgapData dgapData = setDgapDataQuit(item);
                if (dgapData != null) {
                    item.setBiaoShi(SysConst.ESDataStatus.EXISTS_IN_ES.getCode());
                    addKingBaseData(historyList, dgapData);
                    dgapData.setId(String.valueOf(item.getId()));
                    saveList.add(dgapData);
                }
                if (SysConst.ESDataStatus.NOT_EXISTS_IN_ES.getCode().equals(item.getBiaoShi())) {
                    item.setBiaoShi(SysConst.ESDataStatus.EXCEPTION.getCode());
                }
                readList.add(item);
            }

            if (saveList.size() > 0) {
                saveToFile(saveList, SysConst.QUIT, analysisConfig.getSourceLessPath());
                saveToKingBase(historyList);
            }
            if (readList.size() > 0) {
                quitDataService.save(readList);
            }

            logger.info("退出分析,入库 {} 条", saveList.size());
            logger.info("退出分析,分析 {} 条", readList.size());

            recordNum(newIndexMap);
        }
    }

    private DgapData setDgapDataQuit(QuitDataBak single) {
        DgapData result = new DgapData();

        result.setDataType(SysConst.DataType.INVESTMENT.getCode());

        // 时间
        String singleData = single.getTime();
        if (toSetTime(result, singleData)) {
            return null;
        }
        // 行业
        String industry = single.getIndustry();
        if (StringUtils.isNotEmpty(industry)) {
            result.setIndustry(industry.trim());
        }
        if (StringUtils.isEmpty(result.getIndustry())) {
            return null;
        }
        // 省份
        String region = single.getRegion();
        toSetDataProvince(result, region);
        if (StringUtils.isEmpty(result.getProvince())) {
            return null;
        }
        result.setPublishType(SysConst.PublishType.QUIT.getCode());

        // 金额
        Double amount = com.huishu.utils.StringUtils.transformAmount(unitsConfig, single.getReturnAmount());
        if (amount == null) {
            return null;
        }
        result.setCompanyName(single.getInvestor());
        result.setQuitAmount(amount);
        result.setFinancingAmount(0d);
        result.setMergersAmount(0d);
        result.setPolicyUrl(single.getFldUrlAddr());

        return result;
    }

    private boolean toSetTime(DgapData data, String singleData) {
        try {
            if (StringUtils.isNotEmpty(singleData)) {
                singleData = com.huishu.utils.StringUtils.transformTime(singleData);
                data.setHour(0L);
                int yearindex = singleData.indexOf("-");
                int monthIndex = singleData.indexOf("-", yearindex + 1);
                int sIndex = singleData.indexOf(" ", monthIndex + 1);
                int hourIndex = singleData.indexOf(":");
                if (yearindex > 0) {
                    data.setYear(Long.valueOf(singleData
                            .substring(0, yearindex).trim()));
                    if (monthIndex > 0) {
                        data.setMonth(Long.valueOf(singleData.substring(
                                yearindex + 1, monthIndex).trim()));
                        if (sIndex > 0) {
                            data.setDay(Long.valueOf(singleData.substring(
                                    monthIndex + 1, sIndex).trim()));
                            if (hourIndex > 0) {
                                data.setHour(Long.valueOf(singleData.substring(
                                        sIndex + 1, hourIndex).trim()));
                            }
                        } else {
                            data.setDay(Long.valueOf(singleData.substring(
                                    monthIndex + 1, singleData.length()).trim()));
                        }
                    } else {
                        return true;
                    }
                } else {
                    return true;
                }
            } else {
                return true;
            }
            return false;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            logger.error("日期转换错误：" + singleData, e);
            return false;
        }
    }

    private void toSetDataProvince(DgapData data, String region) {
        if (StringUtils.isNotEmpty(region)) {
            String str = "北京,上海,重庆,天津,河北,山西,辽宁,吉林,黑龙江,江苏,浙江,安徽,福建,江西,山东,河南,湖北,湖南,广东,海南,四川,贵州,云南,陕西,甘肃,青海,台湾,广西,宁夏,西藏,新疆,内蒙古,香港,澳门";
            String[] provinces = str.split(",");
            for (String province : provinces) {
                if (region.indexOf(province) >= 0) {
                    data.setProvince(province);
                    break;
                }
            }
            if (StringUtils.isEmpty(data.getProvince())) {
                String tempcity = com.huishu.utils.StringUtils.getCity(region);
                if (StringUtils.isNotEmpty(tempcity)) {
                    List<CityLib> city = cityLibService.findByCity(tempcity);
                    if (city != null && city.size() > 0) {
                        data.setProvince(city.get(0).getProvince());
                        data.setArea(tempcity);
                    }
                }
            }
        }
    }

}
