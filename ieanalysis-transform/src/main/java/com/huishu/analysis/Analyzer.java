package com.huishu.analysis;

import com.huishu.config.AnalysisConfig;

import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by wangjianchun on 2017/11/21.
 */
public interface Analyzer {

    String getName();

    void analysis(AnalysisConfig config, ThreadPoolExecutor executor, Map<String, String> indexMap);

}
