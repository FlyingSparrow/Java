package com.huishu.transform.impl;

import com.huishu.config.TransformConfig;
import com.huishu.entity.IndustryDataBak;
import com.huishu.entity.IndustryDataTyc;
import com.huishu.service.IndustryDataBakService;
import com.huishu.service.IndustryDataTycService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 天眼查网站企业工商数据转换器
 *
 * @author wangjianchun
 * @create 2018/6/6
 */
@Component("industryDataTransformer")
public class IndustryDataTransformer extends AbstractTransformer {

    private static final Logger logger = LoggerFactory.getLogger(IndustryDataTransformer.class);

    @Autowired
    private IndustryDataTycService industryDataTycService;
    @Autowired
    private IndustryDataBakService industryDataBakService;
    @Autowired
    private TransformConfig transformConfig;

    @Override
    protected void transformData(int pageNumber) {
        IndustryDataTyc entity = new IndustryDataTyc();
        Pageable pageable = new PageRequest(pageNumber, transformConfig.getTransformNum());
        List<IndustryDataTyc> list = industryDataTycService.findOneHundred(entity, pageable);
        if (list == null || list.isEmpty()) {
            return;
        }

        logger.info("待转换{}数据 {} 条", getName(), list.size());

        List<IndustryDataBak> bakList = new ArrayList<IndustryDataBak>(list.size());
        for (IndustryDataTyc item : list) {
            IndustryDataBak bak = new IndustryDataBak();
            BeanUtils.copyProperties(item, bak);
            if(StringUtils.isNotEmpty(item.getEnterpriseName())){
                bak.setEnterpriseName(item.getEnterpriseName().trim());
            }
            if(StringUtils.isNotEmpty(item.getRealName())){
                bak.setRealName(item.getRealName().trim());
            }
            String businessScope = item.getBusinessScope();
            if(StringUtils.isNotEmpty(businessScope)){
                if(businessScope.length() > 1024){
                    businessScope = businessScope.substring(0, 1000)+"...";
                    item.setBusinessScope(businessScope);
                }
            }
            bak.setBiaoShi("0");
            bak.setSource("天眼查");
            if (!industryDataBakService.isExists(bak)) {
                bakList.add(bak);
            }
        }

        if (bakList.size() > 0) {
            industryDataBakService.save(bakList);
        }
        industryDataTycService.delete(list);
    }

    @Override
    public boolean getMark() {
        return transformConfig.isIndustryMark();
    }

    @Override
    public int getThreadNum() {
        return transformConfig.getIndustryThreadNum();
    }

    @Override
    public String getName() {
        return "企业工商";
    }

}
