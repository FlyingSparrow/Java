package com.huishu.repository;

import com.huishu.entity.SiteLib;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author wangjianchun
 */
public interface SiteLibRepository extends JpaRepository<SiteLib, Long> {

    List<SiteLib> findByName(String name);
}
