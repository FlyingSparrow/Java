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
            for (int x = 0; x < transformConfig.getRecruitmentThreadNum(); x++) {
                final int tempNum = x;
                executor.execute(() -> {
                    Thread currentThread = Thread.currentThread();
                    logger.info(currentThread.getName() + ":"
                            + currentThread.getId() + "招聘数据转换开始");
                    try {
                        transformRecruitment(transformConfig, tempNum);
                    } catch (Exception e) {
                        e.printStackTrace();
                        logger.error("招聘转换异常", e);
                    }
                    logger.info(currentThread.getName() + ":"
                            + currentThread.getId() + "招聘数据转换结束");
                });
            }
        }
    }

    private void transformRecruitment(TransformConfig transformConfig, int tempNum) {
        Recruitment news = new Recruitment();
        Pageable pageable = new PageRequest(tempNum, transformConfig.getTransformNum());
        List<Recruitment> lists = recruitmentService.findOneHundred(news, pageable);
        List<RecruitmentBak> bakList = new ArrayList<RecruitmentBak>();
        if (lists != null && lists.size() > 0) {
            for (Recruitment list : lists) {
                RecruitmentBak bak = new RecruitmentBak();
                BeanUtils.copyProperties(list, bak);
                bak.setFldrecddate(StringUtils.toTransformTime(bak.getFldrecddate()));
                bak.setBiaoShi("0");
                long count = recruitmentBakService.findExist(bak);
                if (count == 0) {
                    bakList.add(bak);
                }
            }
        }
        if (bakList != null && bakList.size() > 0) {
            recruitmentBakService.save(bakList);
        }
        recruitmentService.delete(lists);
    }

}
