package org.come.until;

import java.util.zip.GZIPInputStream;
import java.io.ByteArrayInputStream;
import java.util.zip.GZIPOutputStream;
import java.io.ByteArrayOutputStream;

/**
 * 客户端 GZip 压缩工具。
 */
public class GZip
{
    /** 压缩字节数组。 */
    public static byte[] gZip(byte[] data) {
        byte[] b = null;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            GZIPOutputStream gzip = new GZIPOutputStream(bos);
            gzip.write(data);
            gzip.finish();
            gzip.close();
            b = bos.toByteArray();
            bos.close();
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return b;
    }
    
    /** 解压字节数组。 */
    public static byte[] unGZip(byte[] data) {
        byte[] b = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(data);
            GZIPInputStream gzip = new GZIPInputStream(bis);
            byte[] buf = new byte[1024];
            int num = -1;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((num = gzip.read(buf, 0, buf.length)) != -1) {
                baos.write(buf, 0, num);
            }
            b = baos.toByteArray();
            baos.flush();
            baos.close();
            gzip.close();
            bis.close();
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return b;
    }
    
    /** 将字节数组转为大写十六进制字符串。 */
    public static String bytesToHexString(byte[] bArray) {
        StringBuffer sb = new StringBuffer(bArray.length);
        for (int i = 0; i < bArray.length; ++i) {
            String sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2) {
                sb.append(0);
            }
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }
}
