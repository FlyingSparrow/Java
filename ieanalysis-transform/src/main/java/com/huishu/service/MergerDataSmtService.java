package com.huishu.service;

import com.huishu.entity.MergerDataSmt;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author wangjianchun
 */
public interface MergerDataSmtService {

    List<MergerDataSmt> findOneHundred(MergerDataSmt data, Pageable pageable);

    void save(List<MergerDataSmt> news);

    void delete(List<MergerDataSmt> news);
}
