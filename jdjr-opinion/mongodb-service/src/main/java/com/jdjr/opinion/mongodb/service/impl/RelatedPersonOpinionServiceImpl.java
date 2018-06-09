package com.jdjr.opinion.mongodb.service.impl;

import com.jdjr.opinion.mongodb.dao.MongodbSearchDao;
import com.jdjr.opinion.mongodb.entity.RelatedPersonOpinion;
import com.jdjr.opinion.mongodb.repository.RelatedPersonOpinionRepository;
import com.jdjr.opinion.mongodb.service.RelatedPersonOpinionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>Title: RelatedPersonOpinionRepository</p>
 * <p>Description: 相关人物舆情信息接口实现类</p>
 *
 * @author zhangtong
 * @date 2017年6月23日
 */
@Transactional
@Service
public class RelatedPersonOpinionServiceImpl implements RelatedPersonOpinionService {

    @Autowired
    private RelatedPersonOpinionRepository relatedPersonOpinionRepository;

    @Autowired
    private MongodbSearchDao mongodbSearchDao;

    @Override
    public boolean save(RelatedPersonOpinion entity) {
        boolean isExist = this.isExists(entity.getRelatedPersonName(), entity.getEnterpriseId(), entity.getArticleId());
        if (!isExist) {
            relatedPersonOpinionRepository.save(entity);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean isExists(String relatedPersonName, String enterpriseId, String articleId) {
        RelatedPersonOpinion entity = relatedPersonOpinionRepository.findByRelatedPersonNameAndEnterpriseIdAndAndArticleId(
                relatedPersonName, enterpriseId, articleId);
        return (entity != null);
    }

    @Override
    public List<RelatedPersonOpinion> findListByEntity(RelatedPersonOpinion entity) {
        return mongodbSearchDao.findListRelatedPersonOpinionByEntity(entity);
    }

    @Override
    public Long findCountByEntity(RelatedPersonOpinion entity) {
        return mongodbSearchDao.findCountRelatedPersonOpinionByEntity(entity);
    }

    @Override
    public RelatedPersonOpinion findById(String id) {
        return relatedPersonOpinionRepository.findOne(id);
    }

    @Override
    public long findCount() {
        return relatedPersonOpinionRepository.count();
    }

    @Override
    public boolean delete(String id) {
        relatedPersonOpinionRepository.delete(id);
        return true;
    }

    @Override
    public List<String> findIdList(int pageNumber, int pageSize) {
        Pageable pageable = new PageRequest(pageNumber-1, pageSize);
        Page<RelatedPersonOpinion> page = relatedPersonOpinionRepository.findAll(pageable);
        return page.getContent().parallelStream().map(RelatedPersonOpinion::getId)
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteAll() {
        relatedPersonOpinionRepository.deleteAll();
        return true;
    }

}
