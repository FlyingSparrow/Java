package com.sparrow.gumballmachine.state;


import com.sparrow.gumballmachine.rmi.server.impl.GumballMachine;

/**
 * @author wangjianchun
 * @create 2018/7/31
 */
public class WinnerState implements State {

    private transient GumballMachine gumballMachine;

    public WinnerState(GumballMachine gumballMachine) {
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
        System.out.println("YOU'RE A WINNER! You get two gumballs for your quarter");
        gumballMachine.releaseBall();
        if(gumballMachine.getCount() == 0){
            gumballMachine.setState(gumballMachine.getSoldOutState());
        }else{
            if(gumballMachine.getCount() > 0){
                gumballMachine.releaseBall();
                gumballMachine.setState(gumballMachine.getNoQuarterState());
            }else{
                System.out.println("Oops, out of gumballs");
                gumballMachine.setState(gumballMachine.getSoldOutState());
            }
        }
    }
}
