/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi.tel.directory;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class Client {
    public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException {
        PhoneDirectory pd = (PhoneDirectory)Naming.lookup("rmi://localhost/phonedir");
        // add Entry:
//        PhoneDirectoryEntry pde1 = new PhoneDirectoryEntry("Nguyễn Duy Hùng", "09342891");
//        PhoneDirectoryEntry pde2 = new PhoneDirectoryEntry("Nguyễn Minh Hiếu", "071128973");
//        PhoneDirectoryEntry pde3 = new PhoneDirectoryEntry("Cao Tông", "0189490504");
//        PhoneDirectoryEntry pde4 = new PhoneDirectoryEntry("Vũ Đức Anh", "87318984094");
//        if(pd.addEntry(pde1) && pd.addEntry(pde2) && pd.addEntry(pde3) && pd.addEntry(pde4)){
//            System.out.println("Addes success");
//        }
//        //list all entry
//        List<PhoneDirectoryEntry>lst = pd.getPhoneDirectory();
//        for(PhoneDirectoryEntry pde: lst){
//            System.out.println("Name: " + pde.getName() + " | " + "Phone Number: " + pde.getPhoneNumber());
//        }
           //delte Entry
//        if(pd.deleteEntry("Cao Tông")){
//            System.out.println("Delete Entry Successfully!!!");
//            List<PhoneDirectoryEntry>lst = pd.getPhoneDirectory();
//            for(PhoneDirectoryEntry pde: lst){
//                System.out.println("Name: " + pde.getName() + " | " + "Phone Number: " + pde.getPhoneNumber());
//            }
//        }
        //updatEntry
        if(pd.updateEntry("Đỗ Minh Hoàng","Trần Trung Kiên","287482429")){
            System.out.println("Update Entry Successfully!!!");
            List<PhoneDirectoryEntry>lst = pd.getPhoneDirectory();
            for(PhoneDirectoryEntry pde: lst){
                System.out.println("Name: " + pde.getName() + " | " + "Phone Number: " + pde.getPhoneNumber());
            }
        }
    }
}
