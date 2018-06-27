package com.huishu.transform.impl;

import com.huishu.config.TransformConfig;
import com.huishu.constants.SysConst;
import com.huishu.entity.Recruitment;
import com.huishu.entity.RecruitmentBak;
import com.huishu.service.RecruitmentBakService;
import com.huishu.service.RecruitmentService;
import com.huishu.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

        logger.info("待转换{}数据 {} 条", getName(), list.size());

        List<RecruitmentBak> bakList = new ArrayList<RecruitmentBak>(list.size());
        for (Recruitment item : list) {
            RecruitmentBak bak = new RecruitmentBak();
            BeanUtils.copyProperties(item, bak);
            bak.setFldrecddate(StringUtils.transformTime(bak.getFldrecddate()));
            bak.setBiaoShi(SysConst.ESDataStatus.NOT_EXISTS_IN_ES.getCode());
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
