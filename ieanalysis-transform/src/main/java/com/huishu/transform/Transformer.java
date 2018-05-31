package com.huishu.transform;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author wangjianchun
 */
public interface Transformer {

    /**
     * 获取转换标记，用于判断是否进行数据转换
     * @return
     */
    boolean getMark();

    /**
     * 获取转换数据的线程数
     * @return
     */
    int getThreadNum();

    /**
     * 获取转换器的简称
     * @return
     */
    String getName();

    /**
     * 采集数据转换并保存
     * @param executor 线程池执行器
     */
    void transform(ThreadPoolExecutor executor);

}
