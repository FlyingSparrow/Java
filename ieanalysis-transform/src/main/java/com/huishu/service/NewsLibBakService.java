package com.huishu.service;

import com.huishu.entity.NewsLibBak;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author wangjianchun
 */
public interface NewsLibBakService {

    List<NewsLibBak> findOneHundred(NewsLibBak news, Pageable pageable);

    void save(List<NewsLibBak> news);

    long findExist(NewsLibBak bak);
}
