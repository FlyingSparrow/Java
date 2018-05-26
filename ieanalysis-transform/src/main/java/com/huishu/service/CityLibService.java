package com.huishu.service;

import com.huishu.entity.CityLib;

import java.util.List;

/**
 * @author wangjianchun
 */
public interface CityLibService {

    /**
     * 根据城市进行查询
     *
     * @param city
     * @return
     */
    List<CityLib> findByCity(String city);

    /**
     * 根据省进行查询
     *
     * @param province
     * @return
     */
    CityLib findByProvince(String province);

    /**
     * 根据类型进行查询
     *
     * @param type
     * @return
     */
    List<CityLib> findByTypeLessThan(Integer type);
}
