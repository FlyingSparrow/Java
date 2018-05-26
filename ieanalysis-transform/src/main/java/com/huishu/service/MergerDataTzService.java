package com.huishu.service;

import com.huishu.entity.MergerDataTz;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author wangjianchun
 */
public interface MergerDataTzService {

    List<MergerDataTz> findOneHundred(MergerDataTz data, Pageable pageable);

    void save(List<MergerDataTz> news);

    void delete(List<MergerDataTz> news);
}
