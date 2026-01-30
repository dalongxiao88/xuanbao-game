package org.come.tool;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import org.come.until.GsonUtil;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class NewAESUtil
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
    static String mmkey = "Q=nidema!uog==DX";
    public static String AESJDKEncode(String content) {
        try {
            byte[] encrypted = GZip.gZip(content.getBytes());
            Cipher Encode = Cipher.getInstance(NewAESUtil.Instance);
            Encode.init(1, NewAESUtil.JMskeySpec, NewAESUtil.JMiv);
            encrypted = Encode.doFinal(encrypted);
            return GsonUtil.getGsonUtil().getgson().toJson(new BASE64Encoder().encode(encrypted)) + "\n";
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static String AESJDKDncode(String content) {
        try {
            content = (String)GsonUtil.getGsonUtil().getgson().fromJson(content, String.class);
            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(content);
            if (encrypted1 == null) {
                return null;
            }
            Cipher Dncode = Cipher.getInstance(NewAESUtil.Instance);
            Dncode.init(2, NewAESUtil.skeySpec, NewAESUtil.iv);
            byte[] original = Dncode.doFinal(encrypted1);
            original = GZip.unGZip(original);
            String originalString = new String(original, NewAESUtil.UTF_8);
            return originalString;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    static {
        NewAESUtil.Instance = "AES/CBC/PKCS5Padding";
        NewAESUtil.sKey = "Gou#zhen>fan<=SB";//zheli
        NewAESUtil.AES = "AES";
        NewAESUtil.UTF_8 = "UTF-8";
        NewAESUtil.GB2312 = "GBK";
        try {
            byte[] JMraw = NewAESUtil.sKey.getBytes();
            NewAESUtil.JMskeySpec = new SecretKeySpec(JMraw, "AES");
            NewAESUtil.JMiv = new IvParameterSpec(mmkey.getBytes());
        }
        catch (Exception ex) {}
        try {
            byte[] raw = NewAESUtil.sKey.getBytes("ASCII");
            NewAESUtil.skeySpec = new SecretKeySpec(raw, NewAESUtil.AES);
            NewAESUtil.iv = new IvParameterSpec(mmkey.getBytes());
        }
        catch (Exception ex2) {}
    }
}
