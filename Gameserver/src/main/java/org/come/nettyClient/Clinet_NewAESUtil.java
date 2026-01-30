package org.come.nettyClient;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import org.come.until.GsonUtil;
import org.come.tool.GZip;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import javax.crypto.Cipher;

public class Clinet_NewAESUtil
{
    public static String sKey;
    public static String AES;
    public static String UTF_8;
    public static String GB2312;
    static String modelType;
    public static Cipher Encode;
    public static Cipher Dncode;
    static BASE64Encoder base64Encodr;
    static BASE64Decoder base64Decoder;
    
    public static synchronized String AESJDKEncode(String content) {
        try {
            content = getTime() + content;
            byte[] encrypted = GZip.gZip(content.getBytes(Clinet_NewAESUtil.UTF_8));
            encrypted = Clinet_NewAESUtil.Encode.doFinal(encrypted);
            return GsonUtil.getGsonUtil().getgson().toJson(Clinet_NewAESUtil.base64Encodr.encode(encrypted));
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static synchronized String AESJDKDncode(String content) {
        try {
            content = (String)GsonUtil.getGsonUtil().getgson().fromJson(content, String.class);
            byte[] encrypted1 = Clinet_NewAESUtil.base64Decoder.decodeBuffer(content);
            byte[] original = Clinet_NewAESUtil.Dncode.doFinal(encrypted1);
            original = GZip.unGZip(original);
            String originalString = new String(original, Clinet_NewAESUtil.GB2312);
            return originalString;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public static synchronized String AESJDKDncode_utf8(String content) throws IOException {
        try {
            content = (String)GsonUtil.getGsonUtil().getgson().fromJson(content, String.class);
            byte[] encrypted1 = Clinet_NewAESUtil.base64Decoder.decodeBuffer(content);
            byte[] original = Clinet_NewAESUtil.Dncode.doFinal(encrypted1);
            original = GZip.unGZip(original);
            String originalString = new String(original, Clinet_NewAESUtil.UTF_8);
            return originalString;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public static String getTime() {
        if (UrlUntil.time == System.currentTimeMillis()) {
            ++UrlUntil.time;
        }
        else {
            UrlUntil.time = System.currentTimeMillis();
        }
        return UrlUntil.time + "";
    }
    
    static {
        Clinet_NewAESUtil.sKey = "PiaoMIAO77778888";//zheli
        Clinet_NewAESUtil.AES = "AES";
        Clinet_NewAESUtil.UTF_8 = "UTF-8";
        Clinet_NewAESUtil.GB2312 = "GBK";
        Clinet_NewAESUtil.modelType = "AES/CBC/PKCS5Padding";
        try {
            byte[] raw = Clinet_NewAESUtil.sKey.getBytes(Clinet_NewAESUtil.UTF_8);
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Clinet_NewAESUtil.Encode = Cipher.getInstance(Clinet_NewAESUtil.modelType);
            IvParameterSpec iv = new IvParameterSpec("0102030709020708".getBytes(Clinet_NewAESUtil.UTF_8));
            Clinet_NewAESUtil.Encode.init(1, skeySpec, iv);
            Clinet_NewAESUtil.base64Encodr = new BASE64Encoder();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            byte[] raw = Clinet_NewAESUtil.sKey.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, Clinet_NewAESUtil.AES);
            Clinet_NewAESUtil.Dncode = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec("0102030709020708".getBytes());
            Clinet_NewAESUtil.Dncode.init(2, skeySpec, iv);
            Clinet_NewAESUtil.base64Decoder = new BASE64Decoder();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
