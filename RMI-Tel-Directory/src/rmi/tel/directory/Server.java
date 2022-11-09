/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi.tel.directory;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author ASUS
 */
public class Server {
    public static void main(String[] args) throws RemoteException, MalformedURLException {
        LocateRegistry.createRegistry(1099);
        PhoneDirectoryImpl pd = new PhoneDirectoryImpl();
        Naming.rebind("rmi://localhost/phonedir", pd);
        System.out.println("connect success");
    }
}
