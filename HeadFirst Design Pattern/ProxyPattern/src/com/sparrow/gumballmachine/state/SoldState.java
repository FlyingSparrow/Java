package com.sparrow.gumballmachine.state;


import com.sparrow.gumballmachine.rmi.server.impl.GumballMachine;

/**
 * @author wangjianchun
 * @create 2018/7/31
 */
public class SoldState implements State {

    private transient GumballMachine gumballMachine;

    public SoldState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("Please wait, we're already giving you a gumball");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("Sorry, you already turned the crank");
    }

    @Override
    public boolean turnCrank() {
        System.out.println("Turning twice doesn't get you another gumball!");
        return false;
    }

    @Override
    public void dispense() {
        gumballMachine.releaseBall();
        if(gumballMachine.getCount() == 0){
            System.out.println("Oops, out of gumballs!");
            gumballMachine.setState(gumballMachine.getSoldOutState());
        }else{
            gumballMachine.setState(gumballMachine.getNoQuarterState());
        }
    }
}
