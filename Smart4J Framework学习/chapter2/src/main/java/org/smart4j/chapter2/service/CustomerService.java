package org.smart4j.chapter2.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.chapter2.helper.DatabaseHelper;
import org.smart4j.chapter2.model.Customer;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Map;

/**
 * 提供客户数据服务
 * Created by wangjianchun on 2017/12/22.
 */
public class CustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

    public List<Customer> getCustomerList(String keyword){
        //TODO
        return null;
    }

    public Customer getCustomer(Long id){
        String sql = "select * from customer where id="+id;
        return DatabaseHelper.queryEntity(Customer.class, sql, null);
    }

    public boolean createCustomer(Map<String, Object> fieldMap){
        return DatabaseHelper.insertEntity(Customer.class, fieldMap);
    }

    public boolean updateCustomer(Long id, Map<String, Object> fieldMap){
        return DatabaseHelper.updateEntity(Customer.class, id, fieldMap);
    }

    public boolean deleteCustomer(Long id){
        return DatabaseHelper.deleteEntity(Customer.class, id);
    }

    public List<Customer> getCustomerList() {
        String sql = "select * from customer";
        return DatabaseHelper.queryEntityList(Customer.class, sql, null);
    }
}
