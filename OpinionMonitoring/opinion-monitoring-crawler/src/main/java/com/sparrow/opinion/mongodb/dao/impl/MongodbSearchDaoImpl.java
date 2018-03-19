package com.sparrow.opinion.mongodb.dao.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.sparrow.opinion.mongodb.dao.MongodbSearchDao;
import com.sparrow.opinion.mongodb.entity.Article;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>Title: MongodbSearchDaoImpl</p>
 * <p>Description: mongodb公共查询类</p>
 *
 * @author zhangtong
 * @date 2017年6月27日
 */
@Repository
public class MongodbSearchDaoImpl implements MongodbSearchDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<String> getUrlMD5PageList(int pageNumber, int pageSize) {
        DBObject queryObject = new BasicDBObject();
        queryObject.put("id", new BasicDBObject("$exists", true));
        BasicDBObject fieldsObject = new BasicDBObject("urlMD5", true);
        fieldsObject.put("id", false);
        Query query = new BasicQuery(queryObject, fieldsObject);
        query.skip((pageNumber-1)*pageSize);
        query.limit(pageSize);
        List<Article> list = mongoTemplate.find(query, Article.class);

        if(list != null){
            return list.parallelStream().map(Article::getUrlMD5).collect(Collectors.toList());
        }
        return Lists.newArrayList();
    }

    @Override
    public Set<String> findDistinctArticleIdByCrawlerDate(String crawlerDate) {
        DBObject queryObject = new BasicDBObject();
        queryObject.put("crawlerDate", crawlerDate);
        BasicDBObject fieldsObject = new BasicDBObject("id", true);
        Query query = new BasicQuery(queryObject, fieldsObject);
        List<Article> list = mongoTemplate.find(query, Article.class);

        return list.stream().map(Article::getId).collect(Collectors.toSet());
    }
}
