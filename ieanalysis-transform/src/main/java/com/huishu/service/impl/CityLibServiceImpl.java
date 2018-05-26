package com.huishu.service.impl;

import com.huishu.entity.CityLib;
import com.huishu.repository.CityLibRepository;
import com.huishu.service.CityLibService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangjianchun
 */
@Service
public class CityLibServiceImpl implements CityLibService {

    @Autowired
    private CityLibRepository cityLibRepository;

    @Override
    public List<CityLib> findByCity(String city) {
        return cityLibRepository.findByCity(city);
    }

    @Override
    public CityLib findByProvince(String province) {
        List<CityLib> list = cityLibRepository.findByProvince(province);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<CityLib> findByTypeLessThan(Integer type) {
        return cityLibRepository.findByTypeLessThan(type);
    }


}
