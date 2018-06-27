package com.huishu.task;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.huishu.analysis.Analyzer;
import com.huishu.config.AnalysisConfig;
import com.huishu.constants.SysConst;
import com.huishu.init.SysInit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 分析任务
 * <p>
 * Title: Task
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author xiaobo
 * @date 2017年4月10日
 */
@Component
public class AnalysisTask {

	private static final Logger logger = LoggerFactory.getLogger(AnalysisTask.class);

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

	@Autowired
	@Qualifier("industryDataAnalyzer")
	private Analyzer industryDataAnalyzer;

	/**
	 * 间隔 30 秒钟执行一次
	 */
	@Scheduled(fixedDelay = 1000 * 30)
	public void warn() {
		try {
			newsAnalyzer.analysis(analysisConfig, executor, indexMap);
			policyAnalyzer.analysis(analysisConfig, executor, indexMap);
			zongheAnalyzer.analysis(analysisConfig, executor, indexMap);
			forumAnalyzer.analysis(analysisConfig, executor, indexMap);
			videoAnalyzer.analysis(analysisConfig, executor, indexMap);
			wechatAnalyzer.analysis(analysisConfig, executor, indexMap);
			recruitmentAnalyzer.analysis(analysisConfig, executor, indexMap);
			investmentAnalyzer.analysis(analysisConfig, executor, indexMap);
			mergerAnalyzer.analysis(analysisConfig, executor, indexMap);
			quitAnalyzer.analysis(analysisConfig, executor, indexMap);
			industryDataAnalyzer.analysis(analysisConfig, executor, indexMap);

//			executor.shutdown();
		} catch (Exception e) {
			logger.error("分析数据出错", e);
		}
	}

}
