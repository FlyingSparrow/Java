package com.huishu.transform.impl;

import com.huishu.config.TransformConfig;
import com.huishu.constants.SysConst;
import com.huishu.entity.NewsLib;
import com.huishu.entity.PolicyBak;
import com.huishu.service.NewsLibService;
import com.huishu.service.PolicyBakService;
import com.huishu.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 政策新闻转换器
 * @author wangjianchun
 * @create 2018/5/26
 */
@Component("policyTransformer")
public class PolicyTransformer extends AbstractTransformer {

    @Autowired
    private NewsLibService newsLibService;
    @Autowired
    private PolicyBakService policyBakService;
    @Autowired
    private TransformConfig transformConfig;

    @Override
    protected void transformData(int pageNumber) {
        NewsLib entity = new NewsLib();
        Pageable pageable = new PageRequest(pageNumber, transformConfig.getTransformNum());
        List<NewsLib> list = newsLibService.findOneHundredPolicy(entity, pageable);
        if (list == null || list.isEmpty()) {
            return;
        }

        logger.info("待转换{}数据 {} 条", getName(), list.size());

        List<PolicyBak> bakList = new ArrayList<PolicyBak>(list.size());
        for (NewsLib item : list) {
            PolicyBak bak = new PolicyBak();
            BeanUtils.copyProperties(item, bak);
            bak.setFldrecddate(StringUtils.transformTime(bak.getFldrecddate()));
            bak.setType(String.valueOf(SysConst.PublishType.LOCAL.getCode()));
            bak.setBiaoShi(SysConst.ESDataStatus.NOT_EXISTS_IN_ES.getCode());
            long count = policyBakService.findExist(bak);
            if (count == 0) {
                bakList.add(bak);
            }
        }
        if (bakList.size() > 0) {
            policyBakService.save(bakList);
        }
        newsLibService.deletePolicy(list);
    }

    @Override
    public boolean getMark() {
        return transformConfig.isPolicyMark();
    }

    @Override
    public int getThreadNum() {
        return transformConfig.getPolicyThreadNum();
    }

    @Override
    public String getName() {
        return "政策";
    }
}
