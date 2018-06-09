package com.jdjr.opinion.mongodb.repository;

import com.jdjr.opinion.mongodb.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {

    Product findByEnterpriseId(String enterpriseId);

}
