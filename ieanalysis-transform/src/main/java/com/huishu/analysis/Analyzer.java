package com.huishu.analysis;

import com.huishu.config.AnalysisConfig;

import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author wangjianchun
 */
public interface Analyzer {

    String getName();

    void analysis(AnalysisConfig analysisConfig, ThreadPoolExecutor executor, Map<String, String> indexMap);

}
