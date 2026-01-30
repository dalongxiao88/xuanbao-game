package org.come.until;

import java.math.BigInteger;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import javax.crypto.KeyGenerator;

public class AESUtil
{
    public static byte[] AESJDKEncode(String message, String password) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128, new SecureRandom(password.getBytes()));
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(1, key);
            byte[] result = cipher.doFinal(message.getBytes(StandardCharsets.UTF_8));
            return result;
        }
        catch (Exception var8) {
            return null;
        }
    }
    
    public static String AESJDKDncode(byte[] msg, String password) {
        try {
            if (msg == null) {
                return "";
            }
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(password.getBytes());
            keyGenerator.init(128, secureRandom);
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(2, key);
            byte[] result = cipher.doFinal(msg);
            String info = new String(result, StandardCharsets.UTF_8);
            return info;
        }
        catch (Exception var11) {
            var11.printStackTrace();
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
    
    public static String binary(byte[] bytes, int radix) {
        return new BigInteger(1, bytes).toString(radix);
    }
}
