package com.sparrow.opinion.mysql.repository;

import com.sparrow.opinion.mysql.entity.RelatedPersonOpinion;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wangjianchun
 * @create 2018/3/23
 */
public interface RelatedPersonOpinionRepository extends JpaRepository<RelatedPersonOpinion, Long> {
}
