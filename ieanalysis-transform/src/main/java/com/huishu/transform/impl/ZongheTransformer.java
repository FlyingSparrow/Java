package com.huishu.transform.impl;

import com.huishu.config.TransformConfig;
import com.huishu.constants.SysConst;
import com.huishu.entity.NewsLib;
import com.huishu.entity.ZongheBak;
import com.huishu.service.NewsLibService;
import com.huishu.service.ZongheBakService;
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
 * 综合新闻转换器
 * @author wangjianchun
 * @create 2018/5/26
 */
@Component("zongheTransformer")
public class ZongheTransformer implements Transformer {

    private static Logger logger = LoggerFactory.getLogger(ZongheTransformer.class);

    @Autowired
    private NewsLibService newsLibService;
    @Autowired
    private ZongheBakService zongheBakService;

    @Override
    public void transform(TransformConfig transformConfig, ThreadPoolExecutor executor) {
        if (transformConfig.isZongheMark()) {
            for (int x = 0; x < transformConfig.getZongheThreadNum(); x++) {
                final int tempNum = x;
                executor.execute(() -> {
                    Thread currentThread = Thread.currentThread();
                    logger.info(currentThread.getName() + ":"
                            + currentThread.getId() + "综合数据转换开始");
                    try {
                        transformZonghe(transformConfig, tempNum);
                    } catch (Exception e) {
                        e.printStackTrace();
                        logger.error("综合数据转换异常", e);
                    }
                    logger.info(currentThread.getName() + ":"
                            + currentThread.getId() + "综合数据转换结束");
                });
            }
        }
    }

    private void transformZonghe(TransformConfig transformConfig, int tempNum) {
        NewsLib news = new NewsLib();
        Pageable pageable = new PageRequest(tempNum, transformConfig.getTransformNum());
        List<NewsLib> lists = newsLibService.findOneHundredZonghe(news, pageable);
        List<ZongheBak> bakList = new ArrayList<ZongheBak>();
        if (lists != null && lists.size() > 0) {
            for (NewsLib list : lists) {
                ZongheBak bak = new ZongheBak();
                BeanUtils.copyProperties(list, bak);
                bak.setFldrecddate(StringUtils.transformTime(bak.getFldrecddate()));
                bak.setType(String.valueOf(SysConst.PublishType.COMPREHENSIVE.getCode()));
                bak.setBiaoShi("0");
                long count = zongheBakService.findExist(bak);
                if (count == 0) {
                    bakList.add(bak);
                }
            }
        }
        if (bakList != null && bakList.size() > 0) {
            zongheBakService.save(bakList);
        }
        newsLibService.deleteZonghe(lists);
    }

}
