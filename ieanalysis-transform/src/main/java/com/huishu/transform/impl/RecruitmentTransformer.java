package com.huishu.transform.impl;

import com.huishu.config.TransformConfig;
import com.huishu.entity.Recruitment;
import com.huishu.entity.RecruitmentBak;
import com.huishu.service.RecruitmentBakService;
import com.huishu.service.RecruitmentService;
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
 * 招聘数据转换器
 *
 * @author wangjianchun
 * @create 2018/5/26
 */
@Component("recruitmentTransformer")
public class RecruitmentTransformer implements Transformer {

    private static Logger logger = LoggerFactory.getLogger(RecruitmentTransformer.class);

    @Autowired
    private RecruitmentService recruitmentService;
    @Autowired
    private RecruitmentBakService recruitmentBakService;

    @Override
    public void transform(TransformConfig transformConfig, ThreadPoolExecutor executor) {
        if (transformConfig.isRecruitmentMark()) {
            // 转换招聘数据
            for (int i = 0; i < transformConfig.getRecruitmentThreadNum(); i++) {
                final int pageNumber = i;
                executor.execute(() -> {
                    Thread currentThread = Thread.currentThread();
                    logger.info("{}:{} 招聘数据转换开始", currentThread.getName(), currentThread.getId());
                    try {
                        transformRecruitment(transformConfig, pageNumber);
                    } catch (Exception e) {
                        logger.error("招聘转换异常", e);
                    }
                    logger.info("{}:{} 招聘数据转换结束", currentThread.getName(), currentThread.getId());
                });
            }
        }
    }

    private void transformRecruitment(TransformConfig transformConfig, int pageNumber) {
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

}
