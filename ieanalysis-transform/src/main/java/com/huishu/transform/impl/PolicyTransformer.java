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
            for (int x = 0; x < transformConfig.getPolicyThreadNum(); x++) {
                final int tempNum = x;
                executor.execute(() -> {
                    Thread currentThread = Thread.currentThread();
                    logger.info(currentThread.getName() + ":"
                            + currentThread.getId() + "政策数据转换开始");
                    try {
                        transformPolicy(transformConfig, tempNum);
                    } catch (Exception e) {
                        e.printStackTrace();
                        logger.error("政策数据转换异常", e);
                    }
                    logger.info(currentThread.getName() + ":"
                            + currentThread.getId() + "政策数据转换结束");
                });
            }
        }
    }

    private void transformPolicy(TransformConfig transformConfig, int tempNum) {
        NewsLib news = new NewsLib();
        Pageable pageable = new PageRequest(tempNum, transformConfig.getTransformNum());
        List<NewsLib> lists = newsLibService.findOneHundredPolicy(news, pageable);
        List<PolicyBak> bakList = new ArrayList<PolicyBak>();
        if (lists != null && lists.size() > 0) {
            for (NewsLib list : lists) {
                PolicyBak bak = new PolicyBak();
                BeanUtils.copyProperties(list, bak);
                bak.setFldrecddate(StringUtils.toTransformTime(bak.getFldrecddate()));
                bak.setType(String.valueOf(SysConst.PUBLISH_TYPE_POLICY));
                bak.setBiaoShi("0");
                long count = policyBakService.findExist(bak);
                if (count == 0) {
                    bakList.add(bak);
                }
            }
        }
        if (bakList != null && bakList.size() > 0) {
            policyBakService.save(bakList);
        }
        newsLibService.deletePolicy(lists);
    }

}
