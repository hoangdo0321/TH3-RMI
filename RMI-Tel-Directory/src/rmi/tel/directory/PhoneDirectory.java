/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi.tel.directory;

import java.util.List;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author ASUS
 */
public interface PhoneDirectory extends Remote{
    public List<PhoneDirectoryEntry> getPhoneDirectory() throws RemoteException;
    public PhoneDirectoryEntry getEntry(String info) throws RemoteException;
    public boolean addEntry(PhoneDirectoryEntry pde) throws RemoteException;
    public boolean updateEntry(String info, String nName, String nPhoneNumber) throws RemoteException;
    public boolean deleteEntry(String info) throws RemoteException;
    
}
