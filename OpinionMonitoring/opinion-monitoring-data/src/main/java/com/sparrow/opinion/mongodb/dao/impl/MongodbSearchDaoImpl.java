package com.sparrow.opinion.mongodb.dao.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.sparrow.opinion.base.bean.PageResult;
import com.sparrow.opinion.mongodb.dao.MongodbSearchDao;
import com.sparrow.opinion.mongodb.entity.Article;
import com.sparrow.opinion.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

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

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<String> finArtcileKeywords() {
        TypedAggregation<Article> agg = Aggregation.newAggregation(
                Article.class,
                Aggregation.project("keywords"),
                Aggregation.group("keywords").count().as("totalNum"),
                Aggregation.sort(Direction.DESC, "totalNum"));
        AggregationResults<BasicDBObject> result = mongoTemplate.aggregate(agg, BasicDBObject.class);
        List<BasicDBObject> resultList = result.getMappedResults();

        List<String> st = resultList.stream().map(a -> {
            return a.getString("_id");
        }).collect(Collectors.toList());
        return st;
    }

    @Override
    public PageResult<Article> findListByEntity(Article entity) {
        int pageSize = entity.getPageSize();
        int pageNumber = entity.getPageNumber();
        int skip = (pageNumber - 1) * pageSize;

        DBObject crawlerDateTimeDBObject = new BasicDBObject("crawlerDateTime",
                new BasicDBObject("$gt", DateUtils.parseDate("2017-01-01", DateUtils.DATE_FORMAT)));
        Query query = new BasicQuery(crawlerDateTimeDBObject);
        if (StringUtils.isNotEmpty(entity.getCrawlerDate())) {
            DBObject crawlerDateDBObject = new BasicDBObject("crawlerDate", entity.getCrawlerDate());
            query = new BasicQuery(crawlerDateDBObject);
        }

        long total = mongoTemplate.count(query, Article.class);
        query.skip(skip);
        query.limit(pageSize);
        List<Article> list = mongoTemplate.find(query, Article.class);
        long totalPages = (total + Long.valueOf(pageSize - 1)) / Long.valueOf(pageSize);
        PageResult<Article> pageResult = new PageResult<Article>(
                list, totalPages, total, list.size());

        return pageResult;
    }

    @Override
    public List<Article> findArticleListByIds(Set<String> ids) {
        DBObject queryDBObject = new BasicDBObject("id", new BasicDBObject("$in", ids));
        DBObject fieldsDBObject = new BasicDBObject("title", true);
        fieldsDBObject.put("content", true);
        Query query = new BasicQuery(queryDBObject, fieldsDBObject);
        return mongoTemplate.find(query, Article.class);
    }
}
