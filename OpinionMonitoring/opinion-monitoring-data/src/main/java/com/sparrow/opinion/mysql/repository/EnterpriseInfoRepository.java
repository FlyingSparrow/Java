package com.sparrow.opinion.mysql.repository;

import com.sparrow.opinion.mysql.entity.EnterpriseInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wangjianchun
 * @create 2018/3/23
 */
public interface EnterpriseInfoRepository extends JpaRepository<EnterpriseInfo, Long> {
}
