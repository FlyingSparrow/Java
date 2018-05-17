package com.sparrow.opinion.mysql.repository;

import com.sparrow.opinion.mysql.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wangjianchun
 * @create 2018/3/23
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
}
