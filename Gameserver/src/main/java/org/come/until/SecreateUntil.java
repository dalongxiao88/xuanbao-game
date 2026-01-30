package org.come.until;

import java.security.spec.PKCS8EncodedKeySpec;
import java.security.PrivateKey;
import java.security.KeyFactory;
import java.security.spec.X509EncodedKeySpec;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.File;
import java.security.PublicKey;

public class SecreateUntil
{
    static String PUBLIC_PRIVATE_SERCATE;
    static String PRIVATE_PRIVATE_SERCATE;
    
    public static PublicKey getPublicKey(String filename) throws Exception {
        File f = new File(filename);
        FileInputStream fis = new FileInputStream(f);
        DataInputStream dis = new DataInputStream(fis);
        byte[] keyBytes = new byte[(int)f.length()];
        dis.readFully(keyBytes);
        dis.close();
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(spec);
    }
    
    public static PrivateKey getPrivateKey(String filename) throws Exception {
        File f = new File(filename);
        FileInputStream fis = new FileInputStream(f);
        DataInputStream dis = new DataInputStream(fis);
        byte[] keyBytes = new byte[(int)f.length()];
        dis.readFully(keyBytes);
        dis.close();
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(spec);
    }
    
    static {
        SecreateUntil.PUBLIC_PRIVATE_SERCATE = "mypubkey.dat";
        SecreateUntil.PRIVATE_PRIVATE_SERCATE = SecreateUntil.class.getResource("/").getPath() + "secreate/myprikey.dat";
    }
}
