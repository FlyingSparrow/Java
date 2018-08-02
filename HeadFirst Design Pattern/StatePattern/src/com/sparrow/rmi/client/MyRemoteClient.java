package com.sparrow.rmi.client;

import com.sparrow.rmi.server.MyRemote;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * 说明：RMI的服务端实现在 ProxyPattern 项目中的com.sparrow.rmi包下面
 *
 * @author wangjianchun
 * @create 2018/8/2
 */
public class MyRemoteClient {

    public static void main(String[] args) {
        new MyRemoteClient().go();
    }

    private void go() {
        try {
            MyRemote service = (MyRemote) Naming.lookup("rmi://127.0.0.1:80/RemoteHello");
            String result = service.sayHell();
            System.out.println(result);
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
