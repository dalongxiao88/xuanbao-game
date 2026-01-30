package org.come.until;

import java.io.FileOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.File;

public class CreateTextUtil
{
    public static byte[] getContent(String filePath) throws IOException {
        File file = new File(filePath);
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
    
    public byte[] getContent2(String filePath) throws IOException {
        FileInputStream in = new FileInputStream(filePath);
        ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
        System.out.println("bytes available:" + in.available());
        byte[] temp = new byte[1024];
        int size = 0;
        while ((size = in.read(temp)) != -1) {
            out.write(temp, 0, size);
        }
        in.close();
        byte[] bytes = out.toByteArray();
        System.out.println("bytes size got is:" + bytes.length);
        return bytes;
    }
    
    public static void createFile(String path, byte[] content) throws IOException {
        FileOutputStream fos = new FileOutputStream(path);
        fos.write(content);
        fos.close();
    }
}
