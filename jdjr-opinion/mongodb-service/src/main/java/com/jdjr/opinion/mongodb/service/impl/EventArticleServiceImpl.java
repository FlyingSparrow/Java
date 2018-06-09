package com.jdjr.opinion.mongodb.service.impl;

import com.jdjr.opinion.mongodb.dao.MongodbSearchDao;
import com.jdjr.opinion.mongodb.entity.EventArticle;
import com.jdjr.opinion.mongodb.repository.EventArticleRepository;
import com.jdjr.opinion.mongodb.service.EventArticleService;
import com.jdjr.opinion.mongodb.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>Title: EventArticleServiceImpl</p>
 * <p>Description: 事件文章信息实现接口类</p>
 *
 * @author zhangtong
 * @date 2017年6月26日
 */
@Transactional
@Service
public class EventArticleServiceImpl implements EventArticleService {

    @Autowired
    private EventArticleRepository eventArticleRepository;

    @Autowired
    private MongodbSearchDao mongodbSearchDao;
    @Autowired
    private EventService eventService;

    @Override
    public boolean add(EventArticle entity) {
        eventArticleRepository.save(entity);
        return true;
    }

    @Override
    public EventArticle findById(String id) {
        return eventArticleRepository.findOne(id);
    }

    @Override
    public List<EventArticle> findListByEntity(EventArticle entity) {
        return mongodbSearchDao.findListEventArticleByEntity(entity);
    }

    @Override
    public boolean update(String articleType, String id) {
        EventArticle ea = eventArticleRepository.findOne(id);
        ea.setArticleType(articleType);
        eventArticleRepository.save(ea);
        return true;
    }

    @Override
    public boolean updateEventArticleByCompanyAndArticle(EventArticle entity) {
        EventArticle eventArticle = mongodbSearchDao.findEventArticleByCompanyAndArtId(entity.getEnterpriseIdList(), entity.getArticleId());
        if (eventArticle != null) {
            entity.setId(eventArticle.getId());
            eventArticleRepository.save(entity);
        } else {
            eventArticleRepository.save(entity);
        }

        return true;
    }

    @Override
    public long findCount() {
        return eventArticleRepository.count();
    }

    @Override
    public boolean delete(String id) {
        eventArticleRepository.delete(id);
        return true;
    }

    @Override
    public List<String> findIdList(int pageNumber, int pageSize) {
        Pageable pageable = new PageRequest(pageNumber-1, pageSize);
        Page<EventArticle> page = eventArticleRepository.findAll(pageable);
        return page.getContent().parallelStream().map(EventArticle::getId)
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteAll() {
        eventArticleRepository.deleteAll();
        return true;
    }

}
