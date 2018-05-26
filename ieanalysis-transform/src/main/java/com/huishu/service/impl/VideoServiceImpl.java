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
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
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
    public List<Video> findOneHundred(Video video, Pageable pageable) {
        if (pageable == null) {
            pageable = new PageRequest(0, 100);
        }
        Page<Video> page = videoRepository.findAll(new Specification<Video>() {
            @Override
            public Predicate toPredicate(Root<Video> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<String> fldRecdId = root.get("fldRecdId");
                Path<String> biaoShi = root.get("biaoShi");
                if (video != null) {
                    List<Predicate> queryList = new ArrayList<Predicate>();
                    if (StringUtils.isNotEmpty(video.getFldRecdId())) {
                        queryList.add(cb.greaterThan(fldRecdId, video.getFldRecdId()));
                    }
                    if (video.getBiaoShi() != null) {
                        queryList.add(cb.equal(biaoShi, video.getBiaoShi()));
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
            }
        }, pageable);
        if (page == null || page.getContent() == null || page.getContent().size() == 0) {
            return new ArrayList<Video>();
        } else {
            return page.getContent();
        }
    }

    @Override
    @TargetDataSource(name = "news")
    public void save(List<Video> news) {
        videoRepository.save(news);
    }

    @Override
    @TargetDataSource(name = "news")
    public void delete(List<Video> news) {
        videoRepository.delete(news);
    }


}
