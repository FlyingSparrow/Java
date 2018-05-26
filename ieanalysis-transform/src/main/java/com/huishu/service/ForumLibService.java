package com.huishu.service;

import com.huishu.entity.ForumLib;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author wangjianchun
 */
public interface ForumLibService {

    List<ForumLib> findOneHundred(ForumLib news, Pageable pageable);

    void save(List<ForumLib> news);

    void delete(List<ForumLib> news);
}
