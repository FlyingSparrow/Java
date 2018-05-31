package com.huishu.transform.impl;

import com.huishu.config.TransformConfig;
import com.huishu.constants.SysConst;
import com.huishu.entity.NewsLib;
import com.huishu.entity.PolicyBak;
import com.huishu.service.NewsLibService;
import com.huishu.service.PolicyBakService;
import com.huishu.transform.Transformer;
import com.huishu.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 政策新闻转换器
 * @author wangjianchun
 * @create 2018/5/26
 */
@Component("policyTransformer")
public class PolicyTransformer implements Transformer {

    private static Logger logger = LoggerFactory.getLogger(PolicyTransformer.class);

    @Autowired
    private NewsLibService newsLibService;
    @Autowired
    private PolicyBakService policyBakService;

    @Override
    public void transform(TransformConfig transformConfig, ThreadPoolExecutor executor) {
        if (transformConfig.isPolicyMark()) {
            for (int i = 0; i < transformConfig.getPolicyThreadNum(); i++) {
                final int pageNumber = i;
                executor.execute(() -> {
                    Thread currentThread = Thread.currentThread();
                    logger.info("{}:{} 政策数据转换开始", currentThread.getName(), currentThread.getId());
                    try {
                        transformPolicy(transformConfig, pageNumber);
                    } catch (Exception e) {
                        logger.error("政策数据转换异常", e);
                    }
                    logger.info("{}:{} 政策数据转换结束", currentThread.getName(), currentThread.getId());
                });
            }
        }
    }

    private void transformPolicy(TransformConfig transformConfig, int pageNumber) {
        NewsLib news = new NewsLib();
        Pageable pageable = new PageRequest(pageNumber, transformConfig.getTransformNum());
        List<NewsLib> list = newsLibService.findOneHundredPolicy(news, pageable);
        List<PolicyBak> bakList = new ArrayList<PolicyBak>();
        if (list != null && list.size() > 0) {
            for (NewsLib item : list) {
                PolicyBak bak = new PolicyBak();
                BeanUtils.copyProperties(item, bak);
                bak.setFldrecddate(StringUtils.transformTime(bak.getFldrecddate()));
                bak.setType(String.valueOf(SysConst.PublishType.LOCAL.getCode()));
                bak.setBiaoShi("0");
                long count = policyBakService.findExist(bak);
                if (count == 0) {
                    bakList.add(bak);
                }
            }
        }
        if (bakList.size() > 0) {
            policyBakService.save(bakList);
        }
        newsLibService.deletePolicy(list);
    }

}
