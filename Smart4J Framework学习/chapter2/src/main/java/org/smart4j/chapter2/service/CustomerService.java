package org.smart4j.chapter2.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.chapter2.helper.DatabaseHelper;
import org.smart4j.chapter2.model.Customer;

import java.sql.Connection;
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
        //TODO
        return null;
    }

    public boolean createCustomer(Map<String, Object> fieldMap){
        //TODO
        return false;
    }

    public boolean updateCustomer(Long id, Map<String, Object> fieldMap){
        //TODO
        return false;
    }

    public boolean deleteCustomer(Long id){
        //TODO
        return false;
    }

    public List<Customer> getCustomerList() {
        Connection conn = DatabaseHelper.getConnection();
        try {
            String sql = "select * from customer";
            return DatabaseHelper.queryEntityList(Customer.class, conn, sql, null);
        } finally{
            DatabaseHelper.closeConnection(conn);
        }
    }
}
