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

import java.util.*;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 分析投资退出数据
 *
 * @author wangjianchun
 * @create 2018/5/26
 */
@Component("quitAnalyzer")
public class QuitAnalyzer extends DefaultAnalyzer {

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
     *
     * @param analysisConfig
     * @param indexMap
     * @param pageNumber
     */
    private void analysisData(AnalysisConfig analysisConfig, Map<String, String> indexMap, int pageNumber) {
        QuitDataBak entity = new QuitDataBak();
        entity.setId(Long.valueOf(indexMap.get(SysConst.QUIT)));
        Pageable pageable = new PageRequest(pageNumber, analysisConfig.getTransformNum());
        List<QuitDataBak> list = quitDataService.findOneHundred(entity, pageable);

        logger.info("退出分析,读取 {} 条", list.size());

        if (list.size() <= 0) {
            return;
        }

        String newId = list.get(list.size() - 1).getId() + "";
        String oldId = indexMap.get(SysConst.QUIT);
        Map<String, String> newIndexMap = new HashMap<>(indexMap);
        if (Long.parseLong(newId) > Long.parseLong(oldId)) {
            newIndexMap.put(SysConst.QUIT, newId);
        }


        List<DgapData> saveList = new ArrayList<DgapData>();
        List<QuitDataBak> readList = new ArrayList<QuitDataBak>();
        List<KingBaseDgap> historyList = new ArrayList<KingBaseDgap>();
        for (QuitDataBak item : list) {
            // 分析
            if(validate(item)){
                DgapData dgapData = fillDgapData(item);
                if (dgapData != null) {
                    item.setBiaoShi(SysConst.ESDataStatus.EXISTS_IN_ES.getCode());
                    addKingBaseData(historyList, dgapData);
                    dgapData.setId(String.valueOf(item.getId()));
                    saveList.add(dgapData);
                }
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

    private boolean validate(QuitDataBak quitDataBak){
        String time = quitDataBak.getTime();
        if (StringUtils.isEmpty(quitDataBak.getIndustry())
                || StringUtils.isEmpty(time)) {
            return false;
        }

        logger.info("time: {}", time);

        String tempTime = com.huishu.utils.StringUtils.transformTime(time);
        int yearIndex = tempTime.indexOf("-");
        if (yearIndex <= 0) {
            return false;
        }

        int monthIndex = tempTime.indexOf("-", yearIndex + 1);
        if (monthIndex <= 0) {
            return false;
        }

        return true;
    }

    private DgapData fillDgapData(QuitDataBak quitDataBak) {
        // 金额
        Double amount = com.huishu.utils.StringUtils.transformAmount(unitsConfig, quitDataBak.getReturnAmount());
        if (amount == null) {
            return null;
        }


        DgapData result = new DgapData();

        // 省份
        fillAreaAndProvinceInfo(result, quitDataBak.getRegion());
        if (StringUtils.isEmpty(result.getProvince())) {
            return null;
        }
        // 时间
        fillDateInfoOfDgapData(result, quitDataBak.getTime());
        result.setDataType(SysConst.DataType.INVESTMENT.getCode());
        // 行业
        result.setIndustry(quitDataBak.getIndustry().trim());
        result.setPublishType(SysConst.PublishType.QUIT.getCode());
        result.setCompanyName(quitDataBak.getInvestor());
        result.setQuitAmount(amount);
        result.setFinancingAmount(0D);
        result.setMergersAmount(0D);
        result.setPolicyUrl(quitDataBak.getFldUrlAddr());

        return result;
    }

    private void fillAreaAndProvinceInfo(DgapData dgapData, String region) {
        if (StringUtils.isEmpty(region)) {
            return;
        }

        Set<String> provinceSet = SysConst.getProvinceSet();
        for (String province : provinceSet) {
            if (region.contains(province)) {
                dgapData.setProvince(province);
                break;
            }
        }
        if (StringUtils.isNotEmpty(dgapData.getProvince())) {
            return;
        }

        String city = com.huishu.utils.StringUtils.getCity(region);
        if (StringUtils.isNotEmpty(city)) {
            List<CityLib> cityList = cityLibService.findByCity(city);
            if (cityList != null && cityList.size() > 0) {
                dgapData.setProvince(cityList.get(0).getProvince());
                dgapData.setArea(city);
            }
        }
    }

}
