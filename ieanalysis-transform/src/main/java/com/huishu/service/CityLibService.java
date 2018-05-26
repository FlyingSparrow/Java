package com.huishu.service;

import com.huishu.entity.CityLib;

import java.util.List;

/**
 * @author wangjianchun
 */
public interface CityLibService {

    List<CityLib> findByCity(String city);

    CityLib findByProvince(String province);

    List<CityLib> findByTypeLessThan(Integer type);
}
