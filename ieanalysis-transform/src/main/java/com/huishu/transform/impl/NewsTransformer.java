package com.huishu.transform.impl;

import com.huishu.config.TransformConfig;
import com.huishu.constants.SysConst;
import com.huishu.entity.NewsLib;
import com.huishu.entity.NewsLibBak;
import com.huishu.service.NewsLibBakService;
import com.huishu.service.NewsLibService;
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
 * 新闻转换器
 * @author wangjianchun
 * @create 2018/5/26
 */
@Component("newsTransformer")
public class NewsTransformer implements Transformer {

    private static Logger logger = LoggerFactory.getLogger(NewsTransformer.class);

    @Autowired
    private NewsLibService newsLibService;
    @Autowired
    private NewsLibBakService newsLibBakService;

    @Override
    public void transform(TransformConfig transformConfig, ThreadPoolExecutor executor) {
        if (transformConfig.isNewsMark()) {
            for (int x = 0; x < transformConfig.getNewsThreadNum(); x++) {
                final int tempNum = x;
                executor.execute(() -> {
                    Thread currentThread = Thread.currentThread();
                    logger.info(currentThread.getName() + ":"
                            + currentThread.getId() + "新闻数据转换开始");
                    try {
                        transformNews(transformConfig, tempNum);
                    } catch (Exception e) {
                        e.printStackTrace();
                        logger.error("新闻数据转换异常", e);
                    }
                    logger.info(currentThread.getName() + ":"
                            + currentThread.getId() + "新闻数据转换结束");
                });
            }
        }
    }

    private void transformNews(TransformConfig transformConfig, int tempNum) {
        NewsLib news = new NewsLib();
        Pageable pageable = new PageRequest(tempNum, transformConfig.getTransformNum());
        List<NewsLib> lists = newsLibService.findOneHundred(news, pageable);
        List<NewsLibBak> bakList = new ArrayList<NewsLibBak>();
        if (lists != null && lists.size() > 0) {
            for (NewsLib list : lists) {
                NewsLibBak bak = new NewsLibBak();
                BeanUtils.copyProperties(list, bak);
                bak.setFldrecddate(StringUtils.toTransformTime(bak.getFldrecddate()));
                bak.setType(String.valueOf(SysConst.PUBLISH_TYPE_NEWS));
                bak.setBiaoShi("0");
                long count = newsLibBakService.findExist(bak);
                if (count == 0) {
                    bakList.add(bak);
                }
            }
        }
        if (bakList != null && bakList.size() > 0) {
            newsLibBakService.save(bakList);
        }
        newsLibService.delete(lists);
    }
}
