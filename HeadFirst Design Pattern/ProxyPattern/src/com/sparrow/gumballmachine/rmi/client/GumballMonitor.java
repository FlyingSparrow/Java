package com.sparrow.gumballmachine.rmi.client;

import com.sparrow.gumballmachine.rmi.server.GumballMachineRemote;

import java.rmi.RemoteException;

/**
 * @author wangjianchun
 * @create 2018/7/31
 */
public class GumballMonitor {

    private GumballMachineRemote gumballMachine;

    public GumballMonitor(GumballMachineRemote gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    public void report(){
        try {
            System.out.println("Gumball Machine: "+gumballMachine.getLocation());
            System.out.println("Current Inventory: "+gumballMachine.getCount()+" gumballs");
            System.out.println("Current State: "+gumballMachine.getState());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
