package com.jdjr.opinion.mongodb.repository;

import com.jdjr.opinion.mongodb.entity.EventArticle;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * <p>Title: EventArticleRepository</p>
 * <p>Description: 事件文章信息表数据库接口</p>
 *
 * @author zhangtong
 * @date 2017年6月26日
 */
public interface EventArticleRepository extends MongoRepository<EventArticle, String> {

}
