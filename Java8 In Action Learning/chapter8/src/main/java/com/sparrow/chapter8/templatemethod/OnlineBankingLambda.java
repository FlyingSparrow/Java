package com.sparrow.chapter8.templatemethod;

import java.util.function.Consumer;

/**
 * @author wangjianchun
 * @create 2018/4/13
 */
public class OnlineBankingLambda {

    public void processCustomer(int id, Consumer<Customer> makeCustomerHappy){
        Customer customer = DataBase.getCustomerWithId(id);
        makeCustomerHappy.accept(customer);
    }

}
