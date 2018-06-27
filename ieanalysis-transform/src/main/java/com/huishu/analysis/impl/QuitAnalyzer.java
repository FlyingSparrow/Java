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

/**
 * 分析投资退出数据
 *
 * @author wangjianchun
 * @create 2018/5/26
 */
@Component("quitAnalyzer")
public class QuitAnalyzer extends DefaultAnalyzer {

    @Autowired
    private QuitDataBakService quitDataBakService;
    @Autowired
    private UnitsConfig unitsConfig;

    @Override
    public String getName() {
        return "投资退出";
    }

    @Override
    public String getType() {
        return SysConst.QUIT;
    }

    /**
     * 分析数据
     *
     * @param analysisConfig
     * @param indexMap
     * @param pageNumber
     */
    @Override
    protected void analysisData(AnalysisConfig analysisConfig, Map<String, String> indexMap, int pageNumber) {
        Map<String, String> newIndexMap = new HashMap<>(indexMap);

        QuitDataBak entity = new QuitDataBak();
        entity.setId(Long.valueOf(newIndexMap.get(getType())));
        Pageable pageable = new PageRequest(pageNumber, analysisConfig.getTransformNum());
        List<QuitDataBak> list = quitDataBakService.findOneHundred(entity, pageable);

        logger.info("分析,读取 {} 条", getName(), list.size());

        if (list.size() <= 0) {
            return;
        }

        String newId = list.get(list.size() - 1).getId() + "";
        String oldId = newIndexMap.get(getType());
        if (Long.parseLong(newId) > Long.parseLong(oldId)) {
            newIndexMap.put(getType(), newId);
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
            saveToFile(saveList, getType(), analysisConfig.getSourceLessPath());
            saveToKingBase(historyList);
        }
        if (readList.size() > 0) {
            quitDataBakService.save(readList);
        }

        logger.info("{}分析,入库 {} 条", getName(), saveList.size());
        logger.info("{}分析,分析 {} 条", getName(), readList.size());

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
        if (StringUtils.isEmpty(city)) {
            return;
        }

        List<CityLib> cityList = cityLibService.findByCity(city);
        if (cityList != null && cityList.size() > 0) {
            dgapData.setProvince(cityList.get(0).getProvince());
            dgapData.setArea(city);
        }
    }

}
