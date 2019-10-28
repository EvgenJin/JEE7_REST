/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.Key;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class functions {
    
    public static String formatDateOut(Date date) {
        DateFormat df = new SimpleDateFormat("dd MMM yyy GG");
        String res = df.format(date);
        return res;
    }
    
    private static Pattern DATE_PATTERN = Pattern.compile(
      "^\\d{4}-\\d{2}-\\d{2}$");
 
    public static boolean matches(String date) {
        return DATE_PATTERN.matcher(date).matches();
    }
    
    public static void metch () {
        String expectedPattern = "MM/dd/yyyy";
        SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);
    }
    
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
