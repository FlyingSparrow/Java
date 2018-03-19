package com.sparrow.opinion.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CustomConfig {
	
	@Value("${custom.scheduler.task.enable}")
    private Boolean schedulerTaskEnable;
    @Value("${custom.zkdj.sync_article.start_date}")
    private String zkdjSyncArticleStartDate;
    @Value("${custom.core.analysis.http.urls}")
    private String coreAnalysisUrls;
    @Value("${custom.analysis.publishDate}")
    private String analysisPublishDate;

    public Boolean getSchedulerTaskEnable() {
        if(schedulerTaskEnable == null){
            return false;
        }
        return schedulerTaskEnable;
    }

    public String getZkdjSyncArticleStartDate() {
        return zkdjSyncArticleStartDate;
    }

    public String getCoreAnalysisUrls() {
        return coreAnalysisUrls;
    }

    public String getAnalysisPublishDate() {
        return analysisPublishDate;
    }
}
