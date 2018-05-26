package com.huishu.service;

import com.huishu.entity.Video;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author wangjianchun
 */
public interface VideoService {

    List<Video> findOneHundred(Video news, Pageable pageable);

    void save(List<Video> news);

    void delete(List<Video> news);
}
