package org.come.until;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.come.gltools.ParseESUtil;
import java.io.IOException;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import javax.crypto.Cipher;

public class AC999
{
    public static String AES;
    public static String UTF_8;
    public static String GB2312;
    static String modelType;
    public static Cipher Encode;
    public static Cipher Dncode;
    static BASE64Encoder base64Encodr;
    static BASE64Decoder base64Decoder;

    static String mmkey = "Q=nidema!uog==DX";//以前Q=nimade!gou==DX

    public static synchronized String AESJDKEncode(String content) {
        content = content.replaceAll("!#!", "?");
        try {
            content = Util.getTime2() + content;
            byte[] encrypted = GZip.gZip(content.getBytes(AC999.UTF_8));
            encrypted = AC999.Encode.doFinal(encrypted);
            return GsonUtil.getGsonUtil().getgson().toJson(AC999.base64Encodr.encode(encrypted));
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static synchronized String AESJDKDncode(String content) {
        try {
            content = (String)GsonUtil.getGsonUtil().getgson().fromJson(content, String.class);
            byte[] encrypted1 = AC999.base64Decoder.decodeBuffer(content);
            byte[] original = AC999.Dncode.doFinal(encrypted1);
            original = GZip.unGZip(original);
            String originalString = new String(original, AC999.GB2312);
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
            byte[] encrypted1 = AC999.base64Decoder.decodeBuffer(content);
            byte[] original = AC999.Dncode.doFinal(encrypted1);
            original = GZip.unGZip(original);
            String originalString = new String(original, AC999.UTF_8);
            originalString = originalString.replaceAll("!#!", "?");
            return originalString;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    static {
        AC999.AES = "AES";
        AC999.UTF_8 = "UTF-8";
        AC999.GB2312 = "GBK";
        AC999.modelType = "AES/CBC/PKCS5Padding";
        try {
            byte[] raw = ParseESUtil.AESJDKDncode("\"miDo5LS6DFub7pQmMd5XWh6zr3k4+ytX2pft9b2F/Q6GX7KUzgaWu+MwPMIc/R2zYJTyY5VEdTcv\\r\\n7UVHatXZvQ\\u003d\\u003d\"").substring(13).getBytes(AC999.UTF_8);
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            AC999.Encode = Cipher.getInstance(AC999.modelType);
            IvParameterSpec iv = new IvParameterSpec(mmkey.getBytes(AC999.UTF_8));
            AC999.Encode.init(1, skeySpec, iv);
            AC999.base64Encodr = new BASE64Encoder();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            byte[] raw = ParseESUtil.AESJDKDncode("\"miDo5LS6DFub7pQmMd5XWh6zr3k4+ytX2pft9b2F/Q6GX7KUzgaWu+MwPMIc/R2zYJTyY5VEdTcv\\r\\n7UVHatXZvQ\\u003d\\u003d\"").substring(13).getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, AC999.AES);
            AC999.Dncode = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(mmkey.getBytes());
            AC999.Dncode.init(2, skeySpec, iv);
            AC999.base64Decoder = new BASE64Decoder();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
