package com.huishu.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 自定义配置类
 * @author wangjianchun
 */
@Component
public class CustomConfig {

	@Value("${custom.wordCloud.path}")
	private String wordCloudPath;
	@Value("${custom.source.more.path}")
	private String sourceMorePath;
	@Value("${custom.source.less.path}")
	private String sourceLessPath;
	@Value("${custom.media.site}")
	private String mediaSite;
	@Value("${custom.target.path}")
	private String targetPath;
	@Value("${custom.analysis.mark}")
	private boolean analysisMark;
	@Value("${custom.analysis.num}")
	private int  analysisNum;
	@Value("${custom.kingbase.save.mark}")
	private boolean kingbaseSaveMark;

	public String getWordCloudPath() {
		return wordCloudPath;
	}

	public String getSourceMorePath() {
		return sourceMorePath;
	}

	public String getSourceLessPath() {
		return sourceLessPath;
	}

	public String getMediaSite() {
		return mediaSite;
	}

	public String getTargetPath() {
		return targetPath;
	}

	public boolean isAnalysisMark() {
		return analysisMark;
	}

	public int getAnalysisNum() {
		return analysisNum;
	}

	public boolean isKingbaseSaveMark() {
		return kingbaseSaveMark;
	}
}
