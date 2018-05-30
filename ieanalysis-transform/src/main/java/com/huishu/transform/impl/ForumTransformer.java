package com.huishu.transform.impl;

import com.huishu.config.TransformConfig;
import com.huishu.entity.ForumLib;
import com.huishu.entity.ForumLibBak;
import com.huishu.service.ForumLibBakService;
import com.huishu.service.ForumLibService;
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
 * 论坛数据转换器
 *
 * @author wangjianchun
 * @create 2018/5/26
 */
@Component("forumTransformer")
public class ForumTransformer implements Transformer {

    private static Logger logger = LoggerFactory.getLogger(ForumTransformer.class);

    @Autowired
    private ForumLibService forumLibService;
    @Autowired
    private ForumLibBakService forumLibBakService;

    @Override
    public void transform(TransformConfig transformConfig, ThreadPoolExecutor executor) {
        if (transformConfig.isForumMark()) {
            for (int x = 0; x < transformConfig.getForumThreadNum(); x++) {
                final int tempNum = x;
                executor.execute(() -> {
                    Thread currentThread = Thread.currentThread();
                    logger.info(currentThread.getName() + ":"
                            + currentThread.getId() + "论坛数据转换开始");
                    try {
                        transformForum(transformConfig, tempNum);
                    } catch (Exception e) {
                        e.printStackTrace();
                        logger.error("论坛数据转换异常", e);
                    }
                    logger.info(currentThread.getName() + ":"
                            + currentThread.getId() + "论坛数据转换结束");
                });
            }
        }
    }

    private void transformForum(TransformConfig transformConfig, int tempNum) {
        ForumLib news = new ForumLib();
        Pageable pageable = new PageRequest(tempNum, transformConfig.getTransformNum());
        List<ForumLib> lists = forumLibService.findOneHundred(news, pageable);
        List<ForumLibBak> bakList = new ArrayList<ForumLibBak>();
        if (lists != null && lists.size() > 0) {
            for (ForumLib list : lists) {
                ForumLibBak bak = new ForumLibBak();
                BeanUtils.copyProperties(list, bak);
                bak.setFldrecddate(StringUtils.transformTime(bak.getFldrecddate()));
                bak.setBiaoShi("0");
                long count = forumLibBakService.findExist(bak);
                if (count == 0) {
                    bakList.add(bak);
                }
            }
        }
        if (bakList != null && bakList.size() > 0) {
            forumLibBakService.save(bakList);
        }
        forumLibService.delete(lists);
    }

}
