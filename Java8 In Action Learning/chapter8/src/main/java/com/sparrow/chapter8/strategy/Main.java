package com.sparrow.chapter8.strategy;

/**
 * @author wangjianchun
 * @create 2018/4/13
 */
public class Main {

    public static void main(String[] args) {
        Validator lowerCaseValidator = new Validator((String string) -> string.matches("[a-z]+"));
        boolean lowerCaseFlag = lowerCaseValidator.validate("aaaa");
        Validator numericValidator = new Validator((String string) -> string.matches("\\d+"));
        boolean numericFlag = numericValidator.validate("bbbb");
        System.out.println("lowerCaseFlag="+lowerCaseFlag+", numericFlag="+numericFlag);

        boolean lowerCaseFlag2 = lowerCaseValidator.validate("hello world!");
        boolean numericFlag2 = numericValidator.validate("24683179");
        System.out.println("lowerCaseFlag2="+lowerCaseFlag2+", numericFlag2="+numericFlag2);
    }

}
