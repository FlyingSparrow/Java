package com.sparrow.gumballmachine;

import com.sparrow.gumballmachine.rmi.server.impl.GumballMachine;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * @author wangjianchun
 * @create 2018/7/31
 */
public class GumballMachineTestDrive {

    public static void main(String[] args) {
//        testGumballMachine();
//        testGumballMachine2();
        testGumballMonitor(args);
    }

    private static void testGumballMachine() {
        GumballMachine gumballMachine = null;
        try {
            gumballMachine = new GumballMachine("Seattle", 12);

            System.out.println(gumballMachine);

            gumballMachine.insertQuarter();
            gumballMachine.turnCrank();

            System.out.println(gumballMachine);

            gumballMachine.insertQuarter();
            gumballMachine.ejectQuarter();
            gumballMachine.turnCrank();

            System.out.println(gumballMachine);

            gumballMachine.insertQuarter();
            gumballMachine.turnCrank();
            gumballMachine.insertQuarter();
            gumballMachine.turnCrank();
            gumballMachine.ejectQuarter();

            System.out.println(gumballMachine);

            gumballMachine.insertQuarter();
            gumballMachine.insertQuarter();
            gumballMachine.turnCrank();
            gumballMachine.insertQuarter();
            gumballMachine.turnCrank();
            gumballMachine.insertQuarter();
            gumballMachine.turnCrank();

            System.out.println(gumballMachine);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private static void testGumballMachine2() {
        GumballMachine gumballMachine = null;
        try {
            gumballMachine = new GumballMachine("Seattle", 5);

            System.out.println(gumballMachine);

            gumballMachine.insertQuarter();
            gumballMachine.turnCrank();

            System.out.println(gumballMachine);

            gumballMachine.insertQuarter();
            gumballMachine.turnCrank();
            gumballMachine.insertQuarter();
            gumballMachine.turnCrank();

            System.out.println(gumballMachine);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private static final void testGumballMonitor(String[] args){
        try {
            int count = 2;

            if(args.length < 2){
                System.out.println("GumballMachine <name> <inventory>");
                System.exit(1);
            }

            if(args.length == 2){

            }else if(args.length > 2){
                count = Integer.parseInt(args[args.length-1]);
                LocateRegistry.createRegistry(80);
                for (int i = 0; i < (args.length -1); i++) {
                    GumballMachine gumballMachine = new GumballMachine(args[i], count);
                    Naming.rebind("rmi://127.0.0.1:80/"+args[i], gumballMachine);
                }
            }

            System.out.println("服务已启动");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

}
