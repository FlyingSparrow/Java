package com.jdjr.opinion.mongodb.repository;

import com.jdjr.opinion.mongodb.entity.EventInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * <p>Title: EventRepository</p>
 * <p>Description: 事件信息数据库接口</p>
 *
 * @author zhangtong
 * @date 2017年6月23日
 */
public interface EventRepository extends MongoRepository<EventInfo, String> {

    void deleteByEnterpriseId(String enterpriseId);

    EventInfo findByCoreArticleIdAndEnterpriseId(String coreArticleId, String enterpriseId);

    EventInfo findByEnterpriseIdAndEventType(String enterpriseId, String eventType);
}
