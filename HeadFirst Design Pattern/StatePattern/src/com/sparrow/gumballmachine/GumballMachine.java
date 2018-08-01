package com.sparrow.gumballmachine;

import com.sparrow.gumballmachine.state.*;

/**
 * @author wangjianchun
 * @create 2018/7/31
 */
public class GumballMachine {

    private State noQuarterState;
    private State hasQuarterState;
    private State soldState;
    private State soldOutState;
    private State winnerState;
    private State state = soldOutState;
    private int count = 0;

    public GumballMachine(int count){
        noQuarterState = new NoQuarterState(this);
        hasQuarterState = new HasQuarterState(this);
        soldState = new SoldState(this);
        soldOutState = new SoldOutState(this);
        winnerState = new WinnerState(this);
        this.count = count;
        if(count > 0){
            state = new NoQuarterState(this);
        }
    }

    public void insertQuarter(){
        state.insertQuarter();
    }

    public void ejectQuarter(){
        state.ejectQuarter();
    }

    public void turnCrank(){
        state.turnCrank();
        dispense();
    }

    public void dispense() {
        state.dispense();
    }

    public void refill(int fillCount){
        this.count = this.count + fillCount;
    }

    @Override
    public String toString() {
        StringBuilder sd = new StringBuilder();
        sd.append("\nMighty Gumball, Inc.\n")
            .append("Java-enabled Standing Gumball Model #2004\n")
            .append("Inventory: ").append(count).append(" gumballs\n")
            .append("Machine is waiting for quarter\n");

        return sd.toString();
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getNoQuarterState() {
        return noQuarterState;
    }

    public State getHasQuarterState(){
        return hasQuarterState;
    }

    public State getSoldState(){
        return soldState;
    }

    public State getSoldOutState(){
        return soldOutState;
    }

    public State getWinnerState(){
        return winnerState;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void releaseBall() {
    }
}
