package com.huishu.task;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.huishu.analysis.Analyzer;
import com.huishu.config.AnalysisConfig;
import com.huishu.constants.SysConst;
import com.huishu.init.SysInit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 分析任务
 * <p>
 * Title: TransformTask
 * </p>
 * <p>
 * Description:
 * </p>
 *
 * @author xiaobo
 * @date 2017年4月10日
 */
@Component
public class AnalysisJob implements CommandLineRunner {

    private static ThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(SysConst.DEFAULT_CORE_POOL_SIZE,
            new ThreadFactoryBuilder().setNameFormat("analysis-pool-%d").build());

    private Map<String, String> indexMap = SysInit.getIndexMap();

    @Autowired
    private AnalysisConfig analysisConfig;

    @Autowired
    @Qualifier("newsAnalyzer")
    private Analyzer newsAnalyzer;

    @Autowired
    @Qualifier("policyAnalyzer")
    private Analyzer policyAnalyzer;

    @Autowired
    @Qualifier("zongheAnalyzer")
    private Analyzer zongheAnalyzer;

    @Autowired
    @Qualifier("forumAnalyzer")
    private Analyzer forumAnalyzer;

    @Autowired
    @Qualifier("videoAnalyzer")
    private Analyzer videoAnalyzer;

    @Autowired
    @Qualifier("wechatAnalyzer")
    private Analyzer wechatAnalyzer;

    @Autowired
    @Qualifier("recruitmentAnalyzer")
    private Analyzer recruitmentAnalyzer;

    @Autowired
    @Qualifier("investmentAnalyzer")
    private Analyzer investmentAnalyzer;

    @Autowired
    @Qualifier("mergerAnalyzer")
    private Analyzer mergerAnalyzer;

    @Autowired
    @Qualifier("quitAnalyzer")
    private Analyzer quitAnalyzer;

    /**
     * 项目启动后执行
     */
    @Override
    public void run(String... strings) throws Exception {
        // 分析新闻
        newsAnalyzer.analysisV2(analysisConfig, executor, indexMap);
        // 分析政策
        policyAnalyzer.analysisV2(analysisConfig, executor, indexMap);
        // 分析综合
        zongheAnalyzer.analysisV2(analysisConfig, executor, indexMap);
        // 论坛数据分析
        forumAnalyzer.analysisV2(analysisConfig, executor, indexMap);
        // 视频数据分析
        videoAnalyzer.analysisV2(analysisConfig, executor, indexMap);
        // 微信分析
        wechatAnalyzer.analysisV2(analysisConfig, executor, indexMap);
        // 招聘数据分析
        recruitmentAnalyzer.analysisV2(analysisConfig, executor, indexMap);
        // 投资数据分析
        investmentAnalyzer.analysisV2(analysisConfig, executor, indexMap);
        // 投资并购数据分析
        mergerAnalyzer.analysisV2(analysisConfig, executor, indexMap);
        // 投资退出数据分析
        quitAnalyzer.analysisV2(analysisConfig, executor, indexMap);
    }
}
