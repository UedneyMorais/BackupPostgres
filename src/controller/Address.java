/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * @author pc
 */
public class Address {
    
    public String getInet(){
        String macAddress = "";
        try {         
           InetAddress address = InetAddress.getLocalHost();  
           NetworkInterface ni = NetworkInterface.getByInetAddress(address);  
           byte[] mac = ni.getHardwareAddress();
           
           for (int i = 0; i < mac.length; i++) {             
               macAddress += (String.format("%02X-", mac[i]));  
           }
           //System.out.println(macAddress.substring(0, macAddress.length()-1));
           
        } catch (UnknownHostException e) {  
           e.printStackTrace();
        } catch (SocketException e) {  
           e.printStackTrace();  
        }
        return macAddress.substring(0, macAddress.length());
    }
    
}
