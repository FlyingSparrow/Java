package com.huishu.transform.impl;

import com.huishu.transform.Transformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 论坛数据转换器
 *
 * @author wangjianchun
 * @create 2018/5/26
 */
public abstract class AbstractTransformer implements Transformer {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void transform(ThreadPoolExecutor executor) {
        if (getMark()) {
            for (int i = 0; i < getThreadNum(); i++) {
                final int pageNumber = i;
                executor.execute(() -> {
                    Thread currentThread = Thread.currentThread();
                    logger.info("{}:{} {}数据转换开始", currentThread.getName(), currentThread.getId(), getName());
                    try {
                        transformData(pageNumber);
                    } catch (Exception e) {
                        logger.error("{}数据转换异常", getName(), e);
                    }
                    logger.info("{}:{} {}数据转换结束", currentThread.getName(), currentThread.getId(), getName());
                });
            }
        }
    }

    /**
     * 转换数据
     *
     * @param pageNumber
     */
    protected abstract void transformData(int pageNumber);

}
