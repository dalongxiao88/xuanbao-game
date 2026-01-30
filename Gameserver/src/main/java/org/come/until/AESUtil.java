package org.come.until;

import java.security.MessageDigest;
import java.math.BigInteger;
import javax.crypto.SecretKey;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import javax.crypto.KeyGenerator;

public class AESUtil
{
    static String AES;
    static String UTF_8;
    
    public static byte[] AESJDKEncode(String message, String password) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(AESUtil.AES);
            keyGenerator.init(128, new SecureRandom(password.getBytes()));
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, AESUtil.AES);
            Cipher cipher = Cipher.getInstance(AESUtil.AES);
            cipher.init(1, key);
            byte[] result = cipher.doFinal(message.getBytes(AESUtil.UTF_8));
            return result;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public static String AESJDKDncode(byte[] msg, String password) {
        try {
            byte[] message = msg;
            KeyGenerator keyGenerator = KeyGenerator.getInstance(AESUtil.AES);
            keyGenerator.init(128, new SecureRandom(password.getBytes()));
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, AESUtil.AES);
            Cipher cipher = Cipher.getInstance(AESUtil.AES);
            cipher.init(2, key);
            byte[] result = cipher.doFinal(message);
            String info = new String(result, AESUtil.UTF_8);
            return info;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public static final byte[] hex2byte(String hex) throws IllegalArgumentException {
        if (hex.length() % 2 != 0) {
            throw new IllegalArgumentException();
        }
        char[] arr = hex.toCharArray();
        byte[] b = new byte[hex.length() / 2];
        String swap;
        int byteint;
        for (int i = 0, j = 0, l = hex.length(); i < l; swap = "" + arr[i++] + arr[i], byteint = (Integer.parseInt(swap, 16) & 0xFF), b[j] = new Integer(byteint).byteValue(), ++i, ++j) {}
        return b;
    }
    
    public static final String byte2hex(byte[] b) {
        if (b == null) {
            throw new IllegalArgumentException("Argument b ( byte array ) is null! ");
        }
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; ++n) {
            stmp = Integer.toHexString(b[n] & 0xFF);
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            }
            else {
                hs += stmp;
            }
        }
        return hs.toUpperCase();
    }
    
    public static String binary(byte[] bytes, int radix) {
        return new BigInteger(1, bytes).toString(radix);
    }
    
    public static String getMD5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            return new BigInteger(1, md.digest()).toString(16);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    static {
        AESUtil.AES = "AES";
        AESUtil.UTF_8 = "utf-8";
    }
}
