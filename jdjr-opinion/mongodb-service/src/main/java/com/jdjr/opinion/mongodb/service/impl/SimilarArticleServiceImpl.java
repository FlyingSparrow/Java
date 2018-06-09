package com.jdjr.opinion.mongodb.service.impl;

import com.jdjr.opinion.mongodb.entity.SimilarArticle;
import com.jdjr.opinion.mongodb.repository.SimilarArticleRepository;
import com.jdjr.opinion.mongodb.service.SimilarArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>Title: SimilarArticleRepository</p>
 * <p>Description: 相似文章信息接口实现类</p>
 *
 * @author zhangtong
 * @date 2017年6月23日
 */
@Service
public class SimilarArticleServiceImpl implements SimilarArticleService {
    @Autowired
    private SimilarArticleRepository similarArticleRepository;

    @Override
    public boolean save(SimilarArticle entity) {
        boolean isExist = this.isExist(entity.getArticleId());
        if (!isExist) {
            similarArticleRepository.save(entity);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isExist(String similarArticleId) {
        SimilarArticle similarArticle = similarArticleRepository.findBySimilarArticleId(similarArticleId);
        return (similarArticle != null);
    }

    @Override
    public List<SimilarArticle> findListByArticleId(String articleId) {
        return similarArticleRepository.findTop10ByArticleIdOrderByPublishDateTimeDesc(articleId);
    }

    @Override
    public long findCount() {
        return similarArticleRepository.count();
    }

    @Override
    public boolean delete(String id) {
        similarArticleRepository.delete(id);
        return true;
    }

    @Override
    public List<String> findIdList(int pageNumber, int pageSize) {
        Pageable pageable = new PageRequest(pageNumber-1, pageSize);
        Page<SimilarArticle> page = similarArticleRepository.findAll(pageable);
        return page.getContent().parallelStream().map(SimilarArticle::getId)
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteAll() {
        similarArticleRepository.deleteAll();
        return true;
    }

}
