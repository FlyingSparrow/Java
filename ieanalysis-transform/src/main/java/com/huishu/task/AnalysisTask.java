package com.huishu.task;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.huishu.analysis.Analyzer;
import com.huishu.config.AnalysisConfig;
import com.huishu.constants.SysConst;
import com.huishu.init.SysInit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;

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
//@Component
public class AnalysisTask {

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
	 * 间隔 30 秒钟执行一次
	 */
	@Scheduled(fixedDelay = 1000 * 30)
	public void warn() {
		// 分析新闻
		newsAnalyzer.analysis(analysisConfig, executor, indexMap);
		// 分析政策
		policyAnalyzer.analysis(analysisConfig, executor, indexMap);
		// 分析综合
		zongheAnalyzer.analysis(analysisConfig, executor, indexMap);
		// 论坛数据分析
		forumAnalyzer.analysis(analysisConfig, executor, indexMap);
		// 视频数据分析
		videoAnalyzer.analysis(analysisConfig, executor, indexMap);
		// 微信分析
		wechatAnalyzer.analysis(analysisConfig, executor, indexMap);
		// 招聘数据分析
		recruitmentAnalyzer.analysis(analysisConfig, executor, indexMap);
		// 投资数据分析
		investmentAnalyzer.analysis(analysisConfig, executor, indexMap);
		// 投资并购数据分析
		mergerAnalyzer.analysis(analysisConfig, executor, indexMap);
		// 投资退出数据分析
		quitAnalyzer.analysis(analysisConfig, executor, indexMap);
	}

}
