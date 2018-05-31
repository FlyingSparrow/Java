package com.huishu.transform.impl;

import com.huishu.config.TransformConfig;
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
        Recruitment news = new Recruitment();
        Pageable pageable = new PageRequest(pageNumber, transformConfig.getTransformNum());
        List<Recruitment> list = recruitmentService.findOneHundred(news, pageable);
        List<RecruitmentBak> bakList = new ArrayList<RecruitmentBak>();
        if (list != null && list.size() > 0) {
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
