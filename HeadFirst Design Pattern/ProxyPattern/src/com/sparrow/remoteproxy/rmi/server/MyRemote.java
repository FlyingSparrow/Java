package com.sparrow.remoteproxy.rmi.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author wangjianchun
 * @create 2018/8/2
 */
public interface MyRemote extends Remote {

    String sayHell() throws RemoteException;
}
