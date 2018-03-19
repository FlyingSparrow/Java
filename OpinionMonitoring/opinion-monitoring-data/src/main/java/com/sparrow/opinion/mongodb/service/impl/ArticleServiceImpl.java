package com.sparrow.opinion.mongodb.service.impl;

import com.sparrow.opinion.mongodb.entity.Article;
import com.sparrow.opinion.mongodb.repository.ArticleRepository;
import com.sparrow.opinion.mongodb.service.ArticleService;
import com.sparrow.opinion.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>Title: ArticleRepository</p>
 * <p>Description: 文章信息接口实现类</p>
 *
 * @author zhangtong
 * @date 2017年6月23日
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public String add(Article entity) {
        Article article = articleRepository.insert(entity);
        return article.getId();
    }

    @Override
    public Article findById(String id) {
        return articleRepository.findOne(id);
    }

    @Override
    public boolean updateAnalyzedState(String id) {
        Date currentDate = DateUtils.currentDate();
        String currentDateStr = DateUtils.formatDate(currentDate);
        Article article = this.findById(id);
        if (article != null) {
            if (StringUtils.isEmpty(article.getPublishDate())) {
                article.setPublishDate(currentDateStr);
                article.setPublishDateTime(currentDate);
            }
            article.setIsAnalyzed("是");
            articleRepository.save(article);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isExist(String urlMD5) {
        List<Article> articleList = articleRepository.findByUrlMD5(urlMD5);
        return (articleList != null && articleList.size() > 0);
    }

    @Override
    public long findCount() {
        return articleRepository.count();
    }

    @Override
    public boolean delete(String id) {
        articleRepository.delete(id);
        return true;
    }

    @Override
    public List<String> findIdList(int pageNumber, int pageSize) {
        Pageable pageable = new PageRequest(pageNumber-1, pageSize);
        Page<Article> page = articleRepository.findAll(pageable);
        return page.getContent().parallelStream().map(Article::getId)
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteAll() {
        articleRepository.deleteAll();
        return true;
    }

    @Override
    public boolean save(Article entity) {
        articleRepository.save(entity);
        return true;
    }
}
