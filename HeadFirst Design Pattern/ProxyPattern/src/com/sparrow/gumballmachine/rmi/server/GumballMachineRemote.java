package com.sparrow.gumballmachine.rmi.server;

import com.sparrow.gumballmachine.state.State;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author wangjianchun
 * @create 2018/8/2
 */
public interface GumballMachineRemote extends Remote {

    int getCount() throws RemoteException;
    String getLocation()throws RemoteException;
    State getState() throws RemoteException;

}
