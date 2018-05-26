package com.huishu.service;

import com.huishu.entity.MergerDataBak;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author wangjianchun
 */
public interface MergerDataBakService {

    List<MergerDataBak> findOneHundred(MergerDataBak data, Pageable pageable);

    void save(List<MergerDataBak> news);

    void delete(List<MergerDataBak> news);

    long findExit(MergerDataBak bak);
}
