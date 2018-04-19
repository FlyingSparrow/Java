package com.sparrow.chapter8.strategy;

/**
 * @author wangjianchun
 * @create 2018/4/13
 */
public class Validator {

    private final ValidationStrategy validationStrategy;

    public Validator(ValidationStrategy validationStrategy){
        this.validationStrategy = validationStrategy;
    }

    public boolean validate(String string){
        return validationStrategy.execute(string);
    }

}
