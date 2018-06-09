package com.jdjr.opinion.mongodb.service;

import com.jdjr.opinion.mongodb.entity.Product;

import java.util.List;

public interface ProductService {

    Product findProductByCompany(String enterpriseId);

    boolean updateProductByCompany(Product entity);

    /**
     * 查询总记录数
     * @return
     */
    long findCount();

    boolean delete(String id);

    List<String> findIdList(int pageNumber, int pageSize);

    boolean deleteAll();

}
