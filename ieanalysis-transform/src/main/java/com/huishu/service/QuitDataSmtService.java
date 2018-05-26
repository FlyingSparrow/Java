package com.huishu.service;

import com.huishu.entity.QuitDataSmt;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author wangjianchun
 */
public interface QuitDataSmtService {

    List<QuitDataSmt> findOneHundred(QuitDataSmt data, Pageable pageable);

    void save(List<QuitDataSmt> news);

    void delete(List<QuitDataSmt> news);
}
