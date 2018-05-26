package com.huishu.service;

import com.huishu.entity.QuitDataBak;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author wangjianchun
 */
public interface QuitDataBakService {

    List<QuitDataBak> findOneHundred(QuitDataBak data, Pageable pageable);

    void save(List<QuitDataBak> news);

    void delete(List<QuitDataBak> news);

    long findExit(QuitDataBak bak);
}
