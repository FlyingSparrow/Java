package com.huishu.service;

import com.huishu.entity.ZongheBak;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author wangjianchun
 */
public interface ZongheBakService {

    List<ZongheBak> findOneHundred(ZongheBak news, Pageable pageable);

    void save(List<ZongheBak> news);

    long findExist(ZongheBak bak);
}
