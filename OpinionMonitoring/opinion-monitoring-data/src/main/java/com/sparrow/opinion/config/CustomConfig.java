package com.sparrow.opinion.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * CachingConfig
 */
@Configuration
public class CustomConfig {

    @Value("${custom.scheduler.task.enable}")
    private Boolean schedulerTaskEnable;
    @Value("${custom.esServer.companyOpinion.url}")
    private String esCompanyOpinionUrl;
    @Value("${custom.keyWords.warning.urls}")
    private String keyWordsWarningUrls;
    @Value("${custom.pressure.warning.urls}")
    private String pressureWarningUrls;

    /**
     * 监控企业ES查询需要排除的关键词
     */
    @Value("${custom.es.search.enterprise.exclude.keywords}")
    private String enterpriseExcludeKeywords;

    /**
     * 监控企业ES查询需要包含的关键词
     */
    @Value("${custom.es.search.enterprise.include.keywords}")
    private String enterpriseIncludeKeywords;

    public Boolean getSchedulerTaskEnable() {
        if(schedulerTaskEnable == null){
            return false;
        }
        return schedulerTaskEnable;
    }

    public String getEsCompanyOpinionUrl() {
        return esCompanyOpinionUrl;
    }

    public String getKeyWordsWarningUrls() {
        return keyWordsWarningUrls;
    }

    public String getPressureWarningUrls() {
        return pressureWarningUrls;
    }

    public String getEnterpriseExcludeKeywords() {
        return enterpriseExcludeKeywords;
    }

    public String getEnterpriseIncludeKeywords() {
        return enterpriseIncludeKeywords;
    }
}
