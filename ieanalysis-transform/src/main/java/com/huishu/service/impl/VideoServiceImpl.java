package com.huishu.service.impl;

import com.huishu.datasource.TargetDataSource;
import com.huishu.entity.Video;
import com.huishu.repository.VideoRepository;
import com.huishu.service.VideoService;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangjianchun
 */
@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoRepository videoRepository;

    @Override
    @TargetDataSource(name = "news")
    public List<Video> findOneHundred(Video entity, Pageable pageable) {
        if (pageable == null) {
            pageable = new PageRequest(0, 100);
        }
        Page<Video> page = videoRepository.findAll((root, query, cb) -> {
            Path<String> fldRecdId = root.get("fldRecdId");
            Path<String> biaoShi = root.get("biaoShi");
            if (entity != null) {
                List<Predicate> queryList = new ArrayList<Predicate>();
                if (StringUtils.isNotEmpty(entity.getFldRecdId())) {
                    queryList.add(cb.greaterThan(fldRecdId, entity.getFldRecdId()));
                }
                if (entity.getBiaoShi() != null) {
                    queryList.add(cb.equal(biaoShi, entity.getBiaoShi()));
                }
                Predicate[] querys = new Predicate[queryList.size()];
                if (queryList != null && queryList.size() > 0) {
                    for (int i = 0, len = queryList.size(); i < len; i++) {
                        querys[i] = queryList.get(i);
                    }
                }
                query.where(querys).orderBy(new OrderImpl(fldRecdId, true));
            }
            return null;
        }, pageable);
        if (page == null || page.getContent() == null || page.getContent().size() == 0) {
            return new ArrayList<Video>();
        } else {
            return page.getContent();
        }
    }

    @Override
    @TargetDataSource(name = "news")
    public void save(List<Video> list) {
        videoRepository.save(list);
    }

    @Override
    @TargetDataSource(name = "news")
    public void delete(List<Video> list) {
        videoRepository.delete(list);
    }

    @Override
    @TargetDataSource(name = "news")
    public Page<Video> findByPage(Video entity, Pageable pageable) {
        if (pageable == null) {
            pageable = new PageRequest(0, 100);
        }
        Page<Video> page = videoRepository.findAll((root, query, cb) -> {
            Path<String> fldRecdId = root.get("fldRecdId");
            Path<String> biaoShi = root.get("biaoShi");
            if (entity != null) {
                List<Predicate> queryList = new ArrayList<Predicate>();
                if (StringUtils.isNotEmpty(entity.getFldRecdId())) {
                    queryList.add(cb.greaterThan(fldRecdId, entity.getFldRecdId()));
                }
                if (entity.getBiaoShi() != null) {
                    queryList.add(cb.equal(biaoShi, entity.getBiaoShi()));
                }
                Predicate[] querys = new Predicate[queryList.size()];
                if (queryList != null && queryList.size() > 0) {
                    for (int i = 0, len = queryList.size(); i < len; i++) {
                        querys[i] = queryList.get(i);
                    }
                }
                query.where(querys).orderBy(new OrderImpl(fldRecdId, true));
            }
            return null;
        }, pageable);

        return page;
    }
}
