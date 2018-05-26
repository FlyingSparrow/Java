package com.huishu.service;

import com.huishu.entity.VideoBak;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author wangjianchun
 */
public interface VideoBakService {

    List<VideoBak> findOneHundred(VideoBak news, Pageable pageable);

    void save(List<VideoBak> news);

    void delete(List<VideoBak> news);

    long findExist(VideoBak bak);
}
