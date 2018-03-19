package com.sparrow.opinion.mongodb.service.impl;

import com.sparrow.opinion.mongodb.entity.Article;
import com.sparrow.opinion.mongodb.repository.ArticleRepository;
import com.sparrow.opinion.mongodb.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public boolean save(Article entity) {
        articleRepository.save(entity);
        return true;
    }

    @Override
    public Article findById(String id) {
        return articleRepository.findOne(id);
    }

    @Override
    public List<Article> findListByUrlMD5(String urlMD5) {
        return articleRepository.findByUrlMD5OrderById(urlMD5);
    }

    @Override
    public long findArticleCount() {
        return articleRepository.count();
    }

    @Override
    public boolean delete(String id) {
        articleRepository.delete(id);
        return true;
    }
    
    @Override
    public List<Article> findIdsByPublishDate(String publishDate) {
        Article entity = new Article();
        entity.setCrawlerDate(publishDate);
        ExampleMatcher matcher = ExampleMatcher.matching();
        matcher = matcher.withMatcher("crawlerDate",
                ExampleMatcher.GenericPropertyMatchers.exact());
        Example<Article> example = Example.of(entity, matcher);
        return articleRepository.findAll(example);
    }

    @Override
    public long findArticleCountByCrawlerDate(String crawlerDate) {
        Article entity = new Article();
        entity.setCrawlerDate(crawlerDate);
        ExampleMatcher matcher = ExampleMatcher.matching();
        matcher = matcher.withMatcher("crawlerDate",
                ExampleMatcher.GenericPropertyMatchers.exact());
        Example<Article> example = Example.of(entity, matcher);
        return articleRepository.count(example);
    }

    @Override
    public String add(Article entity) {
        Article article = articleRepository.insert(entity);
        return article.getId();
    }
}
