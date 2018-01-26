package org.smart4j.chapter5.soap;

import org.smart4j.chapter5.model.Customer;

/**
 * 客户 SOAP 服务接口
 * Created by wangjianchun on 2017/12/22.
 */
public interface CustomerSoapService {

    Customer getCustomer(Long customerId);
}
