package com.sparrow.gumballmachine.state;

import com.sparrow.gumballmachine.GumballMachine;

/**
 * @author wangjianchun
 * @create 2018/7/31
 */
public class NoQuarterState implements State {

    private GumballMachine gumballMachine;

    public NoQuarterState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        gumballMachine.setState(gumballMachine.getHasQuarterState());
        System.out.println("You inserted a quarter");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("You haven't inserted a quarter");
    }

    @Override
    public boolean turnCrank() {
        System.out.println("You turned but there's no quarter");
        return false;
    }

    @Override
    public void dispense() {
        System.out.println("You need to pay first");
    }
}
