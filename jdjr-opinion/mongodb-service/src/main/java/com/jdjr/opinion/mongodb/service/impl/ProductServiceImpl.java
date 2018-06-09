package com.jdjr.opinion.mongodb.service.impl;

import com.jdjr.opinion.mongodb.entity.Product;
import com.jdjr.opinion.mongodb.repository.ProductRepository;
import com.jdjr.opinion.mongodb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product findProductByCompany(String enterpriseId) {

        Product product = productRepository.findByEnterpriseId(enterpriseId);

        return product;
    }

    @Override
    public boolean updateProductByCompany(Product entity) {

        Product product = productRepository.findByEnterpriseId(entity.getEnterpriseId());
        if (product != null) {
            product.setProduct(entity.getProduct());
            productRepository.save(product);
        } else {
            productRepository.save(entity);
        }

        return true;
    }

    @Override
    public long findCount() {
        return productRepository.count();
    }

    @Override
    public boolean delete(String id) {
        productRepository.delete(id);
        return true;
    }

    @Override
    public List<String> findIdList(int pageNumber, int pageSize) {
        Pageable pageable = new PageRequest(pageNumber-1, pageSize);
        Page<Product> page = productRepository.findAll(pageable);
        return page.getContent().parallelStream().map(Product::getId)
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteAll() {
        productRepository.deleteAll();
        return true;
    }

}
