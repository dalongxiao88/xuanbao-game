package org.come.until;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class NewAESForServerUtil
{
    static String Instance;
    public static String sKey;
    static String AES;
    static String UTF_8;
    static String GB2312;
    static SecretKeySpec JMskeySpec;
    static IvParameterSpec JMiv;
    static SecretKeySpec skeySpec;
    static IvParameterSpec iv;
    
    public static String AESJDKEncode(String content) {
        try {
            byte[] encrypted = GZip.gZip(content.getBytes());
            Cipher Encode = Cipher.getInstance(NewAESForServerUtil.Instance);
            Encode.init(1, NewAESForServerUtil.JMskeySpec, NewAESForServerUtil.JMiv);
            encrypted = Encode.doFinal(encrypted);
            return GsonUtil.getGsonUtil().getgson().toJson(new BASE64Encoder().encode(encrypted)) + "\n";
        }
        catch (Exception var3) {
            var3.printStackTrace();
            return null;
        }
    }
    
    public static String AESJDKDncode(String content) {
        try {
            content = (String)GsonUtil.getGsonUtil().getgson().fromJson(content, String.class);
            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(content);
            Cipher Dncode = Cipher.getInstance(NewAESForServerUtil.Instance);
            Dncode.init(2, NewAESForServerUtil.skeySpec, NewAESForServerUtil.iv);
            byte[] original = Dncode.doFinal(encrypted1);
            original = GZip.unGZip(original);
            String originalString = new String(original, NewAESForServerUtil.UTF_8);
            return originalString;
        }
        catch (Exception var5) {
            var5.printStackTrace();
            return null;
        }
    }
    
    static {
        NewAESForServerUtil.Instance = "AES/CBC/PKCS5Padding";
        NewAESForServerUtil.sKey = "PiaoMIAO77778888";
        NewAESForServerUtil.AES = "AES";
        NewAESForServerUtil.UTF_8 = "UTF-8";
        NewAESForServerUtil.GB2312 = "GBK";
        try {
            byte[] raw = NewAESForServerUtil.sKey.getBytes();
            NewAESForServerUtil.JMskeySpec = new SecretKeySpec(raw, "AES");
            NewAESForServerUtil.JMiv = new IvParameterSpec("0102030709020708".getBytes());
        }
        catch (Exception ex) {}
        try {
            byte[] raw = NewAESForServerUtil.sKey.getBytes("ASCII");
            NewAESForServerUtil.skeySpec = new SecretKeySpec(raw, NewAESForServerUtil.AES);
            NewAESForServerUtil.iv = new IvParameterSpec("0102030709020708".getBytes());
        }
        catch (Exception ex2) {}
    }
}
