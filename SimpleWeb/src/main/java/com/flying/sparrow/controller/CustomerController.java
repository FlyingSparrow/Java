package com.flying.sparrow.controller;

import com.flying.sparrow.annotation.Action;
import com.flying.sparrow.annotation.Controller;
import com.flying.sparrow.annotation.Inject;
import com.flying.sparrow.bean.Data;
import com.flying.sparrow.bean.Param;
import com.flying.sparrow.bean.View;
import com.flying.sparrow.framework.bean.FileParam;
import com.flying.sparrow.model.Customer;
import com.flying.sparrow.service.CustomerService;

import java.util.List;
import java.util.Map;

/**
 * 处理客户管理相关请求
 * Created by wangjianchun on 2017/11/15.
 */
@Controller
public class CustomerController {

    @Inject
    private CustomerService customerService;

    /***
     * 进入客户列表页面
     * @return
     */
    @Action("get:/customer")
    public View index(){
        List<Customer> customerList = customerService.getCustomerList();
        return new View("customer.jsp").addModel("customerList", customerList);
    }

    /**
     * 显示客户基本信息
     * @param param
     * @return
     */
    @Action("get:/customer_show")
    public View show(Param param){
        long id = param.getLong("id");
        Customer customer = customerService.getCustomer(id);
        return new View("customer_show.jsp").addModel("customer", customer);
    }

    /**
     * 进入创建客户界面
     * @param param
     * @return
     */
    @Action("get:/customer_create")
    public View create(Param param){
        return new View("customer_create.jsp");
    }

    /**
     * 处理创建客户请求
     * @param param
     * @return
     */
    @Action("post:/customer_create")
    public Data createSubmit(Param param){
        Map<String, Object> fieldMap = param.getFieldMap();
        FileParam fileParam = param.getFile("photo");
        boolean result = customerService.createCustomer(fieldMap, fileParam);
        return new Data(result);
    }

    /**
     * 进入编辑客户界面
     * @param param
     * @return
     */
    @Action("get:/customer_edit")
    public View edit(Param param){
        long id = param.getLong("id");
        Customer customer = customerService.getCustomer(id);
        return new View("customer_edit.jsp").addModel("customer", customer);
    }

    /**
     * 处理编辑客户请求
     * @param param
     * @return
     */
    @Action("put:/customer_edit")
    public Data editSubmit(Param param){
        long id = param.getLong("id");
        Map<String, Object> fieldMap = param.getFieldMap();
        boolean result = customerService.updateCustomer(id, fieldMap);
        return new Data(result);
    }

    /**
     * 处理删除客户请求
     * @param param
     * @return
     */
    @Action("delete:/customer_edit")
    public Data delete(Param param){
        long id = param.getLong("id");
        boolean result = customerService.deleteCustomer(id);
        return new Data(result);
    }

}
