package com.jdjr.opinion.mongodb.service.impl;

import com.jdjr.opinion.mongodb.entity.Article;
import com.jdjr.opinion.mongodb.repository.ArticleRepository;
import com.jdjr.opinion.mongodb.service.ArticleService;
import com.jdjr.opinion.utils.DateUtils;
import com.jdjr.opinion.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
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

    @Override
    public Page<Article> findPageList(Article entity){
        Pageable pageable = new PageRequest((entity.getPageNumber()-1), entity.getPageSize(),
                new Sort(Sort.Direction.DESC, "publishDateTime"));
        return articleRepository.findByIsAnalyzed(entity.getIsAnalyzed(), pageable);
    }
}
