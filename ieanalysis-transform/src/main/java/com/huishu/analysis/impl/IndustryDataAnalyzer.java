package com.huishu.analysis.impl;

import com.huishu.config.AnalysisConfig;
import com.huishu.constants.SysConst;
import com.huishu.entity.IndustryDataBak;
import com.huishu.entity.KingBaseDgap;
import com.huishu.service.IndustryDataBakService;
import com.huishu.vo.DgapData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分析工商数据
 *
 * @author wangjianchun
 * @create 2018/5/26
 */
@Component("industryDataAnalyzer")
public class IndustryDataAnalyzer extends DefaultAnalyzer {

    private static final List<DgapData> STATIC_LIST = new ArrayList<DgapData>();

    @Autowired
    private IndustryDataBakService industryDataBakService;

    @Override
    public String getName() {
        return "工商数据";
    }

    @Override
    public boolean getMark() {
        return analysisConfig.isIndustryMark();
    }

    @Override
    public String getType() {
        return SysConst.INDUSTRY;
    }

    /**
     * 分析数据
     * @param analysisConfig
     * @param indexMap
     * @param pageNumber
     */
    @Override
    protected void analysisData(AnalysisConfig analysisConfig, Map<String, String> indexMap, int pageNumber) {
        STATIC_LIST.clear();
        Map<String, String> newIndexMap = new HashMap<>(indexMap);

        IndustryDataBak entity = new IndustryDataBak();
        entity.setId(Integer.valueOf(newIndexMap.get(getType())));
        Pageable pageable = new PageRequest(pageNumber, analysisConfig.getTransformNum());
        List<IndustryDataBak> list = industryDataBakService.findOneHundred(entity, pageable);

        logger.info("{}分析,读取 {} 条", getName(), list.size());

        if (list.size() <= 0) {
            return;
        }

        String newId = list.get(list.size() - 1).getId() + "";
        String oldId = newIndexMap.get(getType());
        if (Integer.parseInt(newId) > Integer.parseInt(oldId)) {
            newIndexMap.put(getType(), newId);
        }

        List<DgapData> saveList = new ArrayList<DgapData>();
        List<IndustryDataBak> readList = new ArrayList<IndustryDataBak>();
        List<KingBaseDgap> historyList = new ArrayList<KingBaseDgap>();
        for (IndustryDataBak item : list) {
            if (isNotExists(STATIC_LIST, item.getEnterpriseName())) {
                // 分析
                DgapData dgapData = new DgapData();
                BeanUtils.copyProperties(item, dgapData);
                dgapData.setPublishType(SysConst.PublishType.INDUSTRY.getCode());

                item.setBiaoShi(SysConst.ESDataStatus.EXISTS_IN_ES.getCode());
                addKingBaseData(historyList, dgapData);
                dgapData.setId(String.valueOf(item.getId()));
                saveList.add(dgapData);
                STATIC_LIST.add(dgapData);
                readList.add(item);
            }
        }

        if (saveList.size() > 0) {
            saveToFile(saveList, getType(), analysisConfig.getSourceMorePath());
            saveToKingBase(historyList);
        }
        if (readList.size() > 0) {
            industryDataBakService.save(readList);
        }

        logger.info("{}分析,入库 {} 条", getName(), saveList.size());
        logger.info("{}分析,分析 {} 条", getName(), readList.size());

        recordNum(newIndexMap);
    }

    @Override
    protected boolean isNotExists(List<DgapData> dgapDataList, String enterpriseName) {
        boolean flag = true;
        for (DgapData item : dgapDataList) {
            if (StringUtils.isNotEmpty(item.getEnterpriseName()) && item.getEnterpriseName().equals(enterpriseName)) {
                flag = false;
                break;
            }
        }

        return flag;
    }
}
