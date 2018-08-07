package com.sparrow.dynamicproxy.protectionproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author wangjianchun
 * @create 2018/8/6
 */
public class MatchMakingTestDrive {

    public static void main(String[] args) {
        MatchMakingTestDrive test = new MatchMakingTestDrive();
        test.drive();
    }

    public MatchMakingTestDrive(){
        initializeDatabase();
    }

    private void initializeDatabase() {
    }

    private void drive() {
        PersonBean joe = getPersonFromDatabase("Joe Javabean");
        PersonBean ownerProxy = getOwnerProxy(joe);
        System.out.println("Name is "+ownerProxy.getName());
        ownerProxy.setInterests("bowling, Go");
        System.out.println("Interests set from owner proxy");
        try {
            ownerProxy.setHotOrNotRating(10);
        } catch (Exception e) {
            System.out.println("Can't set rating from owner proxy");
        }
        System.out.println("Rating is "+ownerProxy.getHotOrNotRating());

        System.out.println();
        System.out.println();
        PersonBean nonOwnerProxy = getNonOwnerProxy(joe);
        System.out.println("Name is "+nonOwnerProxy.getName());
        try {
            nonOwnerProxy.setInterests("bowling, Go");
        } catch (Exception e) {
            System.out.println("Can't set interests from non owner proxy");
        }
        nonOwnerProxy.setHotOrNotRating(3);
        System.out.println("Rating set from none owner proxy");
        System.out.println("Rating is "+nonOwnerProxy.getHotOrNotRating());
    }

    private PersonBean getPersonFromDatabase(String name) {
        PersonBean personBean = new PersonBeanImpl();
        personBean.setName(name);
        return personBean;
    }

    private static PersonBean getOwnerProxy(PersonBean personBean){
        return getProxy(personBean, new OwnerInvocationHandler(personBean));
    }

    private static PersonBean getNonOwnerProxy(PersonBean personBean){
        return getProxy(personBean, new NonOwnerInvocationHandler(personBean));
    }

    private static PersonBean getProxy(PersonBean personBean, InvocationHandler handler){
        return (PersonBean) Proxy.newProxyInstance(personBean.getClass().getClassLoader(),
                personBean.getClass().getInterfaces(), handler);
    }
}
