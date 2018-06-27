package com.huishu.repository;

import com.huishu.entity.IndustryDataBak;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IndustryDataBakRepository extends JpaRepository<IndustryDataBak, Long> {

    Page<IndustryDataBak> findAll(Specification<IndustryDataBak> specification, Pageable pageable);

    List<IndustryDataBak> findByEnterpriseName(String enterpriseName);
}
