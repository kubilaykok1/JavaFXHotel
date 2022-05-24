package application;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
	public static String MD5(String icerik) {
    try {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] encryption =md.digest(icerik.getBytes());
        BigInteger no = new BigInteger(1,encryption);
        String hash = no.toString(16);
        while(hash.length()<32) {
            hash="0"+hash;
        }
        return hash;
    } catch (NoSuchAlgorithmException e) {
   
        throw new RuntimeException(e);
    }
    
}
}
