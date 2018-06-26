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
import org.springframework.data.domain.Page;
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
            bak.setBiaoShi("0");
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
    protected void transformData() throws InterruptedException {
        int pageNumber = 0;
        int totalPages = 10;
        NewsLib entity = new NewsLib();
        while (pageNumber <= totalPages){
            Pageable pageable = new PageRequest(pageNumber, transformConfig.getTransformNum());
            Page<NewsLib> page = newsLibService.findPolicyListByPage(entity, pageable);
            totalPages = page.getTotalPages();

            List<NewsLib> list = page.getContent();
            if(list != null && list.size() > 0){
                logger.info("总页数：{}，每页记录数：{}，剩余 {} 条{}数据待转换", page.getTotalPages(),
                        transformConfig.getTransformNum(), page.getTotalElements(), getName());

                logger.info("第 {} 页{}数据转换开始", pageNumber, getName());


                List<PolicyBak> bakList = new ArrayList<PolicyBak>(list.size());
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
                if (bakList.size() > 0) {
                    policyBakService.save(bakList);
                }
                newsLibService.deletePolicy(list);

                logger.info("第 {} 页{}数据转换结束", pageNumber, getName());

                pageNumber++;
            }else{
                //如果待转换数据都已经处理完成，那么重置 pageNumber 和 totalPages，确保可以无限循环，在有数据以后继续处理
                pageNumber = 0;
                totalPages = 10;
                //如果没有数据需要转换，那么当前线程休眠5分钟
                logger.info("没有{}数据需要转换，线程休眠 5 分钟", getName());
                Thread.sleep(300000);
            }
        }
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
