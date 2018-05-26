package com.huishu.service;

import com.huishu.entity.QuitDataTz;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author wangjianchun
 */
public interface QuitDataTzService {

    List<QuitDataTz> findOneHundred(QuitDataTz data, Pageable pageable);

    void save(List<QuitDataTz> news);

    void delete(List<QuitDataTz> news);
}
