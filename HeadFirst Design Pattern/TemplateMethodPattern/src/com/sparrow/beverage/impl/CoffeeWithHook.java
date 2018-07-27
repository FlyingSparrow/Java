package com.sparrow.beverage.impl;

import com.sparrow.beverage.CaffeineBeverageWithHook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author wangjianchun
 * @create 2018/7/26
 */
public class CoffeeWithHook extends CaffeineBeverageWithHook {

    @Override
    public void brew(){
        System.out.println("Dripping Coffee through filter");
    }

    @Override
    public void addCondiments(){
        System.out.println("Adding Sugar and Milk");
    }

    @Override
    protected boolean customerWantsCondiments() {
        String answer = getUserInput();

        if(answer.toLowerCase().startsWith("y")){
            return true;
        }else {
            return false;
        }
    }

    private String getUserInput() {
        String answer = null;

        System.out.println("Would you like milk and sugar with your coffee (y/n)?");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            answer = br.readLine();
        } catch (IOException e) {
            System.out.println("IO error trying to read your answer");
        }

        if(answer == null){
            return "no";
        }

        return answer;
    }

}
