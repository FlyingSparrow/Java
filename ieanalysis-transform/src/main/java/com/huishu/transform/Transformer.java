package com.huishu.transform;

import com.huishu.config.TransformConfig;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author wangjianchun
 */
public interface Transformer {

    /**
     * 采集数据转换并保存
     * @param transformConfig 转换配置
     * @param executor 线程池执行器
     */
    void transform(TransformConfig transformConfig, ThreadPoolExecutor executor);

}
