package com.huishu.analysis;

import com.huishu.config.AnalysisConfig;

import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author wangjianchun
 */
public interface Analyzer {

    /**
     * 获取分析器的简写名称
     * @return
     */
    String getName();

    /**
     * 获取标记，用于判断是否进行数据分析
     *
     * @return
     */
    boolean getMark();

    /**
     * 获取分析器类型
     * @return
     */
    String getType();

    /**
     * 分析数据
     * @param analysisConfig 分析配置
     * @param executor 线程池执行器
     * @param indexMap 分析配置map
     */
    void analysis(AnalysisConfig analysisConfig, ThreadPoolExecutor executor, Map<String, String> indexMap);

}
