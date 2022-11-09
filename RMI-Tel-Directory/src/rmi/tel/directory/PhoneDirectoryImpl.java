/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi.tel.directory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
class MyObjectOutputStream extends ObjectOutputStream {

    // Constructor of this class
    // 1. Default
    MyObjectOutputStream() throws IOException {

        // Super keyword refers to parent class instance
        super();
    }

    // Constructor of this class
    // 1. Parameterized constructor
    MyObjectOutputStream(OutputStream o) throws IOException {
        super(o);
    }

    // Method of this class
    public void writeStreamHeader() throws IOException {
        return;
    }
}

public class PhoneDirectoryImpl extends UnicastRemoteObject implements PhoneDirectory {
    
    private static File f = new File("PhoneDirectory.bin");
   
    PhoneDirectoryImpl() throws RemoteException {
        super();
    }

    @Override
    public List<PhoneDirectoryEntry> getPhoneDirectory() throws RemoteException {
        List<PhoneDirectoryEntry> list = new ArrayList<>();
        try {
 
            f.createNewFile();
        }
 
        catch (Exception e) {
            e.printStackTrace();
        }
        if (f.length() != 0) {
 
            try {

                FileInputStream fis = null;
 
                fis = new FileInputStream(
                    "PhoneDirectory.bin");
                ObjectInputStream ois
                    = new ObjectInputStream(fis);
 
                PhoneDirectoryEntry pde = null;
 
                while (fis.available() != 0) {
                    pde = (PhoneDirectoryEntry)ois.readObject();
                    list.add(pde);
                }
 
                ois.close();
                fis.close();
 
            }
            catch (Exception e) {
                System.out.println("Error Occurred" + e);
                e.printStackTrace();
            }
        }
        return list;

    }

    @Override
    public PhoneDirectoryEntry getEntry(String info) throws RemoteException {
        PhoneDirectoryEntry res = null;
        try {
 
            f.createNewFile();
        }
 
        catch (Exception e) {
            e.printStackTrace();
        }
        if (f.length() != 0) {
 
            try {

                FileInputStream fis = null;
 
                fis = new FileInputStream(
                    "PhoneDirectory.bin");
                ObjectInputStream ois
                    = new ObjectInputStream(fis);
 
                PhoneDirectoryEntry pde = null;
                while (fis.available() != 0) {
                    pde = (PhoneDirectoryEntry)ois.readObject();
                    if((pde.getName().equalsIgnoreCase(info)) || (pde.getPhoneNumber().equalsIgnoreCase(info))){
                        res = pde;
                        break;
                    }
                }
 
                ois.close();
                fis.close();
 
            }
            catch (Exception e) {
                System.out.println("Error Occurred" + e);
                e.printStackTrace();
            }
        }
        return res;
    }

    @Override
    public boolean addEntry(PhoneDirectoryEntry pde) throws RemoteException {
        boolean status = false;

        if (pde != null) {
            try {
                FileOutputStream fos = null;

                fos = new FileOutputStream(
                        "PhoneDirectory.bin", true);

                if (f.length() == 0) {
                    ObjectOutputStream oos
                            = new ObjectOutputStream(fos);
                    oos.writeObject(pde);
                    oos.close();
                } // There is content in file to be write on
                else {

                    MyObjectOutputStream oos = null;
                    oos = new MyObjectOutputStream(fos);
                    oos.writeObject(pde);

                    // Closing the FileOutputStream object
                    // to release memory resources
                    oos.close();
                }

                // Closing the File class object to avoid
                // read-write
                fos.close();
            } // Catch block to handle the exceptions
            catch (Exception e) {

                System.out.println("Error Occurred" + e);
            }

            // Change the flag status
            status = true;
        }

        return status;
    }

    @Override
    public boolean updateEntry(String info, String newName, String newPhoneNum) throws RemoteException {
        boolean isUpdated = false;
        //System.out.println(newName.isEmpty());
        List<PhoneDirectoryEntry> lst = getPhoneDirectory();
        if (f.length() != 0) {
 
            try {

                FileInputStream fis = null;
 
                fis = new FileInputStream(
                    "PhoneDirectory.bin");
                ObjectInputStream ois
                    = new ObjectInputStream(fis);
 
                PhoneDirectoryEntry pde = null;
                int count = 0;
                
                while (fis.available() != 0) {
                    pde = (PhoneDirectoryEntry)ois.readObject();
                    count += 1;
                    if((pde.getName().equalsIgnoreCase(info)) || (pde.getPhoneNumber().equalsIgnoreCase(info)) ){
                        System.out.println(count);
                        break;
                        
                    }
                }
                ois.close();
                fis.close();
                PhoneDirectoryEntry p1 = lst.get(count-1);
                if(!newName.isEmpty()){
                    p1.setName(newName);
                }
                if(!newPhoneNum.isEmpty()){
                    p1.setPhoneNumber(newPhoneNum);
                }
                lst.remove(count-1);
                lst.add(count-1, p1);
                System.out.println(f.getAbsolutePath());
                if(f.delete()){
                    //System.out.println("File has been deleted");
                    for(PhoneDirectoryEntry p: lst){
                        addEntry(p);
                        //System.out.println("DELE: NAME:" + p.getName() + " && " + "PhoneNumbber: " + p.getPhoneNumber());
                    }
                }
                else{
                    System.out.println("Cannot delete file");
                }
//                for(PhoneDirectoryEntry p: lst){
//                    System.out.println("Name: " + pde.getName() + " | " + "Phone Number: " + pde.getPhoneNumber());
//                }
                
 
            }
            catch (Exception e) {
                System.out.println("Error Occurred" + e);
                e.printStackTrace();
            }
            isUpdated = true;
            
        }
        
        return isUpdated;
         
    }

    @Override
    public boolean deleteEntry(String info) throws RemoteException{
        boolean isDeleted = false;
        List<PhoneDirectoryEntry> lst = getPhoneDirectory();
        if (f.length() != 0) {
 
            try {

                FileInputStream fis = null;
 
                fis = new FileInputStream(
                    "PhoneDirectory.bin");
                ObjectInputStream ois
                    = new ObjectInputStream(fis);
 
                PhoneDirectoryEntry pde = null;
                int count = 0;
                
                while (fis.available() != 0) {
                    pde = (PhoneDirectoryEntry)ois.readObject();
                    count += 1;
                    if((pde.getName().equalsIgnoreCase(info)) || (pde.getPhoneNumber().equalsIgnoreCase(info)) ){
                        System.out.println(count);
                        break;
                        
                    }
                }
                ois.close();
                fis.close();
                lst.remove(count-1);
                
                System.out.println(f.getAbsolutePath());
                if(f.delete()){
                    System.out.println("File has been deleted");
                    for(PhoneDirectoryEntry p: lst){
                        addEntry(p);
                        System.out.println("DELE: NAME:" + p.getName() + " && " + "PhoneNumbber: " + p.getPhoneNumber());
                    }
                }
                else{
                    System.out.println("Cannot delete file");
                }
//                for(PhoneDirectoryEntry p: lst){
//                    System.out.println("Name: " + pde.getName() + " | " + "Phone Number: " + pde.getPhoneNumber());
//                }
                
 
            }
            catch (Exception e) {
                System.out.println("Error Occurred" + e);
                e.printStackTrace();
            }
            isDeleted = true;
            
        }
        
        return isDeleted;
        
        
    }

}
