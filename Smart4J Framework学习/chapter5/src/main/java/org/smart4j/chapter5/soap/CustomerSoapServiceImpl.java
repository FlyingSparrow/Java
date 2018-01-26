package org.smart4j.chapter5.soap;

import org.smart4j.chapter5.model.Customer;
import org.smart4j.chapter5.service.CustomerService;
import org.smart4j.framework.annotation.Service;
import org.smart4j.plugin.soap.Soap;

/**
 * 客户 SOAP 服务接口实现
 * @author wangjianchun
 * @create 2018/1/26
 */
@Soap
@Service
public class CustomerSoapServiceImpl implements CustomerSoapService {

//    @Inject
    private CustomerService customerService = new CustomerService();

    @Override
    public Customer getCustomer(Long customerId) {
        return customerService.getCustomer(customerId);
    }
}
