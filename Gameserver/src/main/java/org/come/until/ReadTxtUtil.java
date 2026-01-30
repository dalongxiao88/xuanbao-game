package org.come.until;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.File;
import java.io.IOException;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class ReadTxtUtil
{
    public static String str;
    
    public static String getFileEncode(String path) {
        String charset = "asci";
        byte[] first3Bytes = new byte[3];
        BufferedInputStream bis = null;
        try {
            boolean checked = false;
            bis = new BufferedInputStream(new FileInputStream(path));
            bis.mark(0);
            int read = bis.read(first3Bytes, 0, 3);
            if (read == -1) {
                return charset;
            }
            if (first3Bytes[0] == -1 && first3Bytes[1] == -2) {
                charset = "Unicode";
                checked = true;
            }
            else if (first3Bytes[0] == -2 && first3Bytes[1] == -1) {
                charset = "Unicode";
                checked = true;
            }
            else if (first3Bytes[0] == -17 && first3Bytes[1] == -69 && first3Bytes[2] == -65) {
                charset = "UTF8";
                checked = true;
            }
            bis.reset();
            if (!checked) {
                int len = 0;
                int loc = 0;
                while ((read = bis.read()) != -1) {
                    ++loc;
                    if (read >= 240) {
                        break;
                    }
                    else if (128 <= read && read <= 191) {
                        break;
                    }
                    else if (192 <= read && read <= 223) {
                        read = bis.read();
                        if (128 <= read && read <= 191) {
                            continue;
                        }
                        else {
                            break;
                        }
                    }
                    else if (224 <= read && read <= 239) {
                        read = bis.read();
                        if (128 <= read && read <= 191) {
                            read = bis.read();
                            if (128 <= read && read <= 191) {
                                charset = "UTF-8";
                                break;
                            }
                            else {
                                break;
                            }
                        }
                        else {
                            break;
                        }
                    }
                    else {
                        continue;
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (bis != null) {
                try {
                    bis.close();
                }
                catch (IOException ex) {}
            }
        }
        return charset;
    }
    
    private static String getEncode(int flag1, int flag2, int flag3) {
        String encode = "";
        if (flag1 == 255 && flag2 == 254) {
            encode = "Unicode";
        }
        else if (flag1 == 254 && flag2 == 255) {
            encode = "UTF-16";
        }
        else if (flag1 == 239 && flag2 == 187 && flag3 == 191) {
            encode = "UTF8";
        }
        else {
            encode = "asci";
        }
        return encode;
    }
    
    public static byte[] readFile(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            return null;
        }
        long fileSize = file.length();
        if (fileSize > 2147483647L) {
            System.out.println("file too big...");
            return null;
        }
        FileInputStream fi = new FileInputStream(file);
        byte[] buffer = new byte[(int)fileSize];
        int offset = 0;
        for (int numRead = 0; offset < buffer.length && (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0; offset += numRead) {}
        if (offset != buffer.length) {
            throw new IOException("Could not completely read file " + file.getName());
        }
        fi.close();
        return buffer;
    }
    
    public static String readFile1(String fileName) {
        String path = fileName;
        String data = null;
        File file = new File(path);
        if (!file.exists()) {
            return data;
        }
        String code = getFileEncode(path);
        InputStreamReader isr = null;
        try {
            if ("asci".equals(code)) {
                code = System.getProperty("file.encoding");
            }
            isr = new InputStreamReader(new FileInputStream(file), code);
            int length = -1;
            char[] buffer = new char[1024];
            StringBuffer sb = new StringBuffer();
            while ((length = isr.read(buffer, 0, 1024)) != -1) {
                sb.append(buffer, 0, length);
            }
            data = new String(sb);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (isr != null) {
                    isr.close();
                }
            }
            catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        return data;
    }
    
    public static byte[] InputStream2ByteArray(String filePath) {
        InputStream in = null;
        byte[] data = null;
        try {
            in = new FileInputStream(filePath);
            data = toByteArray(in);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
        finally {
            try {
                in.close();
            }
            catch (IOException e3) {
                e3.printStackTrace();
            }
        }
        return data;
    }
    
    public static byte[] toByteArray(InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int n = 0;
        while ((n = in.read(buffer)) != -1) {
            out.write(buffer, 0, n);
        }
        return out.toByteArray();
    }
}
