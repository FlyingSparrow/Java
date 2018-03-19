package com.sparrow.opinion.mysql.repository;

import com.sparrow.opinion.mysql.entity.EnterpriseAbbreviation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by wangjianchun on 2017/12/7.
 */
public interface EnterpriseAbbreviationRepository extends JpaRepository<EnterpriseAbbreviation, Long> {
}
