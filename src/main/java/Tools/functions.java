/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class functions {
             
    public static String getServerIp() {
        String ip;
        Pattern pattern = Pattern.compile("(?<=\\/)(.\\S.+)");
        try {
            ip = InetAddress.getLocalHost().toString();            
            System.out.println("Your current IP address : " + ip);
            Matcher matcher = pattern.matcher(ip);
            if (matcher.find())
            {
                return matcher.group(1);
            }            
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }         
        return null;
    }    
}
