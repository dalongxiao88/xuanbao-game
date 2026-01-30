package org.come.until;

import java.security.MessageDigest;

public class StringUtil
{
    public static String str;
    public static final String EMPTY_STRING = "";
    private static final String[] hexDigits;
    
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n += 256;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return StringUtil.hexDigits[d1] + StringUtil.hexDigits[d2];
    }
    
    public static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; ++i) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }
    
    public static String MD5Encode(String origin) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
        }
        catch (Exception ex) {}
        return resultString;
    }
    
    static {
        hexDigits = new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F" };
    }
}
