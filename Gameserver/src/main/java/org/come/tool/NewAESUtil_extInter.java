package org.come.tool;

import javax.crypto.Cipher;
import java.security.SecureRandom;
import javax.crypto.KeyGenerator;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import org.come.until.GsonUtil;

public class NewAESUtil_extInter
{
    public static String transferKey;
    
    public static String AESJDKEncode(String message) {
        try {
            return GsonUtil.getGsonUtil().getgson().toJson(base64Encode(aesEncryptToBytes(System.currentTimeMillis() + message, NewAESUtil_extInter.transferKey)) + "/r/n");
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    private static String base64Encode(byte[] bytes) {
        return new BASE64Encoder().encode(bytes);
    }
    
    private static byte[] base64Decode(String base64Code) throws Exception {
        return new BASE64Decoder().decodeBuffer(base64Code);
    }
    
    private static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(encryptKey.getBytes());
        kgen.init(128, secureRandom);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(1, kgen.generateKey());
        return cipher.doFinal(content.getBytes("UTF-8"));
    }
    
    public static String AESJDKDncode(String decryptKey) throws Exception {
        return aesDecryptByBytes(base64Decode(decryptKey), NewAESUtil_extInter.transferKey);
    }
    
    private static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(decryptKey.getBytes());
        kgen.init(128, secureRandom);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(2, kgen.generateKey());
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        return new String(decryptBytes, "UTF-8");
    }
    
    public static String getEncoding(String str) {
        String encode = "GB2312";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s = encode;
                return s;
            }
        }
        catch (Exception ex) {}
        encode = "ISO-8859-1";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s2 = encode;
                return s2;
            }
        }
        catch (Exception ex2) {}
        encode = "UTF-8";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s3 = encode;
                return s3;
            }
        }
        catch (Exception ex3) {}
        encode = "GBK";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s4 = encode;
                return s4;
            }
        }
        catch (Exception ex4) {}
        return "";
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
    
    public static void main(String[] args) throws Exception {
        String cSrc = "1尽快JFK垃圾啊离开家九零五九二哦i";
        System.out.println(cSrc);
        String enString = NewAESUtil.AESJDKEncode(cSrc);
        System.out.println("加密后的字串是：" + enString);
        String DeString = NewAESUtil.AESJDKDncode(enString);
        System.out.println("解密后的字串是：" + DeString);
    }
    
    static {
        NewAESUtil_extInter.transferKey = "btnm018idnwtyaTNNkkdTnd11_!@$TTASdg212c21_djTdj";
    }
}
