package com.sparrow.chapter8.templatemethod;

/**
 * @author wangjianchun
 * @create 2018/4/13
 */
public abstract class OnlineBanking {

    public void processCustomer(int id){
        Customer customer = DataBase.getCustomerWithId(id);
        makeCustomerHappy(customer);
    }

    protected abstract void makeCustomerHappy(Customer customer);

}
