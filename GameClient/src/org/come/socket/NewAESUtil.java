package org.come.socket;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import org.come.until.GsonUtil;
import org.come.until.GZip;
import org.come.until.Util;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import javax.crypto.Cipher;

public class NewAESUtil
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
        System.out.println("=======加密前" + content);
        content = content.replaceAll("？", "!#!");
        try {
            content = Util.getTime2() + content;
            byte[] encrypted = GZip.gZip(content.getBytes(NewAESUtil.UTF_8));
            encrypted = NewAESUtil.Encode.doFinal(encrypted);
            return GsonUtil.getGsonUtil().getgson().toJson(NewAESUtil.base64Encodr.encode(encrypted));
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static synchronized String AESJDKDncode(String content) {
        try {
            content = (String)GsonUtil.getGsonUtil().getgson().fromJson(content, String.class);
            byte[] encrypted1 = NewAESUtil.base64Decoder.decodeBuffer(content);
            byte[] original = NewAESUtil.Dncode.doFinal(encrypted1);
            original = GZip.unGZip(original);
            String originalString = new String(original, NewAESUtil.GB2312);
            originalString = originalString.replaceAll("!#!", "?");
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
            byte[] encrypted1 = NewAESUtil.base64Decoder.decodeBuffer(content);
            byte[] original = NewAESUtil.Dncode.doFinal(encrypted1);
            original = GZip.unGZip(original);
            String originalString = new String(original, NewAESUtil.UTF_8);
            originalString = originalString.replaceAll("!#!", "?");
            return originalString;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    static {
        NewAESUtil.sKey = "Goudongxi####123";
        NewAESUtil.AES = "AES";
        NewAESUtil.UTF_8 = "UTF-8";
        NewAESUtil.GB2312 = "GBK";
        NewAESUtil.modelType = "AES/CBC/PKCS5Padding";
        try {
            byte[] raw = NewAESUtil.sKey.getBytes(NewAESUtil.UTF_8);
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            NewAESUtil.Encode = Cipher.getInstance(NewAESUtil.modelType);
            IvParameterSpec iv = new IvParameterSpec("^ZFS585>5=5>5<<<".getBytes(NewAESUtil.UTF_8));
            NewAESUtil.Encode.init(1, skeySpec, iv);
            NewAESUtil.base64Encodr = new BASE64Encoder();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            byte[] raw = NewAESUtil.sKey.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, NewAESUtil.AES);
            NewAESUtil.Dncode = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec("0102030709020708".getBytes());
            NewAESUtil.Dncode.init(2, skeySpec, iv);
            NewAESUtil.base64Decoder = new BASE64Decoder();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
