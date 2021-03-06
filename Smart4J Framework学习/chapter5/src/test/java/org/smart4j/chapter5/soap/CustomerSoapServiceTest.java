package org.smart4j.chapter5.soap;

import org.junit.Assert;
import org.junit.Test;
import org.smart4j.chapter5.model.Customer;
import org.smart4j.plugin.soap.SoapHelper;

/**
 * 客户 SOAP 服务单元测试
 * @author wangjianchun
 * @create 2018/1/26
 */
public class CustomerSoapServiceTest {

    @Test
    public void getCustomerTest(){
        String wsdl = "http://localhost:8080/soap/CustomerSoapService";
        CustomerSoapService customerSoapService = SoapHelper.createClient(wsdl, CustomerSoapService.class);
        Customer customer = customerSoapService.getCustomer(1L);
        Assert.assertNotNull(customer);
    }

}
