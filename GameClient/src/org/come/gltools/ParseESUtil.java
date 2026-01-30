package org.come.gltools;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import org.come.until.GsonUtil;
import org.come.until.GZip;
import org.come.until.Util;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import javax.crypto.Cipher;

public class ParseESUtil
{
    public static String AES;
    public static String UTF_8;
    public static String GB2312;
    static String modelType;
    public static Cipher Encode;
    public static Cipher Dncode;
    static BASE64Encoder base64Encodr;
    static BASE64Decoder base64Decoder;
    static BASE64Encoder base64Encodr1;
    static BASE64Decoder base64Decoder1;
    
    public static synchronized String AESJDKEncode(String content) {
        System.out.println("=======鍔犲瘑鍓�" + content);
        content = content.replaceAll("鈥�", "!#!");
        try {
            content = Util.getTime2() + content;
            byte[] encrypted = GZip.gZip(content.getBytes(ParseESUtil.UTF_8));
            encrypted = ParseESUtil.Encode.doFinal(encrypted);
            return GsonUtil.getGsonUtil().getgson().toJson(ParseESUtil.base64Encodr.encode(encrypted));
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static synchronized String AESJDKDncode(String content) {
        try {
            content = (String)GsonUtil.getGsonUtil().getgson().fromJson(content, String.class);
            byte[] encrypted1 = ParseESUtil.base64Decoder.decodeBuffer(content);
            byte[] original = ParseESUtil.Dncode.doFinal(encrypted1);
            original = GZip.unGZip(original);
            String originalString = new String(original, ParseESUtil.GB2312);
            originalString = originalString.replaceAll("!#!", "鈥�");
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
            byte[] encrypted1 = ParseESUtil.base64Decoder.decodeBuffer(content);
            byte[] original = ParseESUtil.Dncode.doFinal(encrypted1);
            original = GZip.unGZip(original);
            String originalString = new String(original, ParseESUtil.UTF_8);
            originalString = originalString.replaceAll("!#!", "?");
            return originalString;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public static void main(String[] args) {
    }
    
    static {
        ParseESUtil.AES = "AES";
        ParseESUtil.UTF_8 = "UTF-8";
        ParseESUtil.GB2312 = "GBK";
        ParseESUtil.modelType = "AES/CBC/PKCS5Padding";
        ParseESUtil.base64Encodr1 = new BASE64Encoder();
        ParseESUtil.base64Decoder1 = new BASE64Decoder();
        try {
            String decrypt = "CZXQWE592CA292S2";
            byte[] raw = decrypt.getBytes(ParseESUtil.UTF_8);
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            ParseESUtil.Encode = Cipher.getInstance(ParseESUtil.modelType);
            IvParameterSpec iv = new IvParameterSpec("T-X+-S-X-Z-132-X".getBytes(ParseESUtil.UTF_8));
            ParseESUtil.Encode.init(1, skeySpec, iv);
            ParseESUtil.base64Encodr = new BASE64Encoder();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            String decrypt = "CZXQWE592CA292S2";
            byte[] raw = decrypt.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, ParseESUtil.AES);
            ParseESUtil.Dncode = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec("T-X+-S-X-Z-132-X".getBytes());
            ParseESUtil.Dncode.init(2, skeySpec, iv);
            ParseESUtil.base64Decoder = new BASE64Decoder();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
