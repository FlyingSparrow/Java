package com.sparrow.opinion.mongodb.dao;

import java.util.List;
import java.util.Set;

public interface MongodbSearchDao {

    List<String> getUrlMD5PageList(int pageNumber, int pageSize);

    Set<String> findDistinctArticleIdByCrawlerDate(String crawlerDate);

}
