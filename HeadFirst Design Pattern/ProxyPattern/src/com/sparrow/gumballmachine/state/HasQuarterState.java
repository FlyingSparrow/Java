package com.sparrow.gumballmachine.state;


import com.sparrow.gumballmachine.rmi.server.impl.GumballMachine;

import java.util.Random;

/**
 * @author wangjianchun
 * @create 2018/7/31
 */
public class HasQuarterState implements State {

    private transient Random randomWinner = new Random(System.currentTimeMillis());
    private transient GumballMachine gumballMachine;

    public HasQuarterState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("You can't insert another quarter");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("Quarter returned");
        gumballMachine.setState(gumballMachine.getNoQuarterState());
    }

    @Override
    public boolean turnCrank() {
        System.out.println("You turned...");
        int winner = randomWinner.nextInt(10);
        if (winner == 0 && gumballMachine.getCount() > 1) {
            gumballMachine.setState(gumballMachine.getWinnerState());
        }else{
            gumballMachine.setState(gumballMachine.getSoldState());
        }
        return true;
    }

    @Override
    public void dispense() {
        System.out.println("No gumball dispensed");
    }
}
