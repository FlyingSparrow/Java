package com.huishu.service;

import com.huishu.entity.ForumLibBak;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author wangjianchun
 */
public interface ForumLibBakService {

    List<ForumLibBak> findOneHundred(ForumLibBak news, Pageable pageable);

    void save(List<ForumLibBak> news);

    void delete(List<ForumLibBak> news);

    long findExist(ForumLibBak bak);
}
