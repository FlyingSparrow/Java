package com.huishu.service;

import com.huishu.entity.NewsLib;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author wangjianchun
 */
public interface NewsLibService {

    List<NewsLib> findOneHundred(NewsLib news, Pageable pageable);

    void save(List<NewsLib> news);

    void delete(List<NewsLib> news);

    void deleteById(String id);

    List<NewsLib> findOneHundredZonghe(NewsLib news, Pageable pageable);

    void saveZonghe(List<NewsLib> news);

    void deleteZonghe(List<NewsLib> news);

    void deleteZongheById(String id);

    List<NewsLib> findOneHundredPolicy(NewsLib news, Pageable pageable);

    void savePolicy(List<NewsLib> news);

    void deletePolicy(List<NewsLib> news);

    void deletePolicyById(String id);
}
