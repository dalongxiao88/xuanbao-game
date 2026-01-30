package org.come.until;

import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Cipher;

public class AES
{
    private static final String AES = "AES";
    private static final String CRYPT_KEY = "YUUAtestYUUAtest";
    
    public static byte[] encrypt(byte[] src, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec securekey = new SecretKeySpec(key.getBytes(), "AES");
        cipher.init(1, securekey);
        return cipher.doFinal(src);
    }
    
    public static byte[] decrypt(byte[] src, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec securekey = new SecretKeySpec(key.getBytes(), "AES");
        cipher.init(2, securekey);
        return cipher.doFinal(src);
    }
    
    public static String byte2hex(byte[] b) {
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
    
    public static byte[] hex2byte(byte[] b) {
        if (b.length % 2 != 0) {
            throw new IllegalArgumentException("长度不是偶数");
        }
        byte[] b2 = new byte[b.length / 2];
        for (int n = 0; n < b.length; n += 2) {
            String item = new String(b, n, 2);
            b2[n / 2] = (byte)Integer.parseInt(item, 16);
        }
        return b2;
    }
    
    public static final String decrypt(String data) {
        try {
            return new String(decrypt(hex2byte(data.getBytes()), "YUUAtestYUUAtest"));
        }
        catch (Exception var2) {
            return null;
        }
    }
    
    public static final String encrypt(String data) {
        try {
            return byte2hex(encrypt(data.getBytes(), "YUUAtestYUUAtest"));
        }
        catch (Exception var2) {
            return null;
        }
    }
    
    public static void main(String[] args) {
        String ID = "611111";
        String idEncrypt = encrypt(ID);
        System.out.println(idEncrypt);
        String idDecrypt = decrypt(idEncrypt);
        System.out.println(idDecrypt);
    }
}
