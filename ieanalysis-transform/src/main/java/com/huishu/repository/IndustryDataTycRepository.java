package com.huishu.repository;

import com.huishu.entity.IndustryDataTyc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IndustryDataTycRepository extends JpaRepository<IndustryDataTyc, Long> {

    Page<IndustryDataTyc> findAll(Specification<IndustryDataTyc> specification, Pageable pageable);

}
