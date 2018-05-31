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
            for (int i = 0; i < transformConfig.getNewsThreadNum(); i++) {
                final int pageNumber = i;
                executor.execute(() -> {
                    Thread currentThread = Thread.currentThread();
                    logger.info("{}:{} 新闻数据转换开始", currentThread.getName(), currentThread.getId());
                    try {
                        transformNews(transformConfig, pageNumber);
                    } catch (Exception e) {
                        logger.error("新闻数据转换异常", e);
                    }
                    logger.info("{}:{} 新闻数据转换结束", currentThread.getName(), currentThread.getId());
                });
            }
        }
    }

    private void transformNews(TransformConfig transformConfig, int pageNumber) {
        NewsLib news = new NewsLib();
        Pageable pageable = new PageRequest(pageNumber, transformConfig.getTransformNum());
        List<NewsLib> list = newsLibService.findOneHundred(news, pageable);
        List<NewsLibBak> bakList = new ArrayList<NewsLibBak>();
        if (list != null && list.size() > 0) {
            for (NewsLib item : list) {
                NewsLibBak bak = new NewsLibBak();
                BeanUtils.copyProperties(item, bak);
                bak.setFldrecddate(StringUtils.transformTime(bak.getFldrecddate()));
                bak.setType(String.valueOf(SysConst.PublishType.NEWS.getCode()));
                bak.setBiaoShi("0");
                long count = newsLibBakService.findExist(bak);
                if (count == 0) {
                    bakList.add(bak);
                }
            }
        }
        if (bakList.size() > 0) {
            newsLibBakService.save(bakList);
        }
        newsLibService.delete(list);
    }
}
