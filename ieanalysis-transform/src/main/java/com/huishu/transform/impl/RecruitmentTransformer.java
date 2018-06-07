package com.huishu.transform.impl;

import com.huishu.config.TransformConfig;
import com.huishu.entity.Recruitment;
import com.huishu.entity.RecruitmentBak;
import com.huishu.service.RecruitmentBakService;
import com.huishu.service.RecruitmentService;
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
 * 招聘数据转换器
 *
 * @author wangjianchun
 * @create 2018/5/26
 */
@Component("recruitmentTransformer")
public class RecruitmentTransformer extends AbstractTransformer {

    @Autowired
    private RecruitmentService recruitmentService;
    @Autowired
    private RecruitmentBakService recruitmentBakService;
    @Autowired
    private TransformConfig transformConfig;

    @Override
    protected void transformData(int pageNumber) {
        Recruitment entity = new Recruitment();
        Pageable pageable = new PageRequest(pageNumber, transformConfig.getTransformNum());
        List<Recruitment> list = recruitmentService.findOneHundred(entity, pageable);
        if (list == null || list.isEmpty()) {
            return;
        }

        List<RecruitmentBak> bakList = new ArrayList<RecruitmentBak>();
        for (Recruitment item : list) {
            RecruitmentBak bak = new RecruitmentBak();
            BeanUtils.copyProperties(item, bak);
            bak.setFldrecddate(StringUtils.transformTime(bak.getFldrecddate()));
            bak.setBiaoShi("0");
            long count = recruitmentBakService.findExist(bak);
            if (count == 0) {
                bakList.add(bak);
            }
        }
        if (bakList.size() > 0) {
            recruitmentBakService.save(bakList);
        }
        recruitmentService.delete(list);
    }

    @Override
    protected void transformData() throws InterruptedException {
        int pageNumber = 1;
        int totalPages = 10;
        Recruitment entity = new Recruitment();
        while (pageNumber <= totalPages){
            Pageable pageable = new PageRequest(pageNumber, transformConfig.getTransformNum());
            Page<Recruitment> page = recruitmentService.findByPage(entity, pageable);
            totalPages = page.getTotalPages();

            List<Recruitment> list = page.getContent();
            if(list != null && list.size() > 0){
                logger.info("总页数：{}，每页记录数：{}，剩余 {} 条{}数据待转换", page.getTotalPages(),
                        transformConfig.getTransformNum(), page.getTotalElements(), getName());

                logger.info("第 {} 页{}数据转换开始", pageNumber, getName());


                List<RecruitmentBak> bakList = new ArrayList<RecruitmentBak>();
                for (Recruitment item : list) {
                    RecruitmentBak bak = new RecruitmentBak();
                    BeanUtils.copyProperties(item, bak);
                    bak.setFldrecddate(StringUtils.transformTime(bak.getFldrecddate()));
                    bak.setBiaoShi("0");
                    long count = recruitmentBakService.findExist(bak);
                    if (count == 0) {
                        bakList.add(bak);
                    }
                }
                if (bakList.size() > 0) {
                    recruitmentBakService.save(bakList);
                }
                recruitmentService.delete(list);

                logger.info("第 {} 页{}数据转换结束", pageNumber, getName());

            }else{
                //如果没有数据需要分析，那么当前线程休眠5分钟
                Thread.sleep(300000);
            }
            pageNumber++;
        }
    }

    @Override
    public boolean getMark() {
        return transformConfig.isRecruitmentMark();
    }

    @Override
    public int getThreadNum() {
        return transformConfig.getRecruitmentThreadNum();
    }

    @Override
    public String getName() {
        return "招聘";
    }
}
