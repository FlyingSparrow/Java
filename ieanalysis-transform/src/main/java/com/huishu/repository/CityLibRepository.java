package com.huishu.repository;

import com.huishu.entity.CityLib;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author wangjianchun
 */
public interface CityLibRepository extends JpaRepository<CityLib, Long> {

    List<CityLib> findByCity(String city);

    List<CityLib> findByProvince(String province);

    List<CityLib> findByTypeLessThan(Integer type);
}
