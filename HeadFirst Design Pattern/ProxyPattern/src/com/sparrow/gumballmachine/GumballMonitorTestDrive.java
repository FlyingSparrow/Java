package com.sparrow.gumballmachine;

import com.sparrow.gumballmachine.rmi.client.GumballMonitor;
import com.sparrow.gumballmachine.rmi.server.GumballMachineRemote;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * @author wangjianchun
 * @create 2018/7/31
 */
public class GumballMonitorTestDrive {

    public static void main(String[] args) {
        String[] location = {
                "rmi://127.0.0.1:80/santafe.mightygumball.com/gumballMachine",
                "rmi://127.0.0.1:80/boulder.mightygumball.com/gumballMachine",
                "rmi://127.0.0.1:80/seattle.mightygumball.com/gumballMachine"
        };

        GumballMonitor[] gumballMonitors = new GumballMonitor[location.length];

        for(int i=0; i<location.length; i++){
            try {
                GumballMachineRemote gumballMachine = (GumballMachineRemote) Naming.lookup(location[i]);
                gumballMonitors[i] = new GumballMonitor(gumballMachine);
                System.out.println(gumballMonitors[i]);
            } catch (NotBoundException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        System.out.println("-------------------------");
        for (int i = 0; i < gumballMonitors.length; i++) {
            gumballMonitors[i].report();
            System.out.println("=========================");
        }
    }

}
