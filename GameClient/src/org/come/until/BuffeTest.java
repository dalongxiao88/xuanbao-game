package org.come.until;

import java.io.ByteArrayOutputStream;
import java.util.Map;
import java.io.IOException;
import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.File;

public class BuffeTest
{
    public static void decryptFile(File decryptedFile, File newFile) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(decryptedFile);
        FileOutputStream fileOutputStream = new FileOutputStream(newFile);
        BufferedInputStream inputStream = new BufferedInputStream(fileInputStream);
        BufferedOutputStream outputStream = new BufferedOutputStream(fileOutputStream);
        int b;
        while ((b = inputStream.read()) != -1) {
            outputStream.write(b ^ 0x2104E);
        }
        outputStream.flush();
        inputStream.close();
        outputStream.close();
    }
    
    public static void decryptFileTOByte(File decryptedFile, Map<String, byte[]> imgMap) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(decryptedFile);
        BufferedInputStream inputStream = new BufferedInputStream(fileInputStream);
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        int b;
        while ((b = inputStream.read()) != -1) {
            bout.write(b ^ 0x2104E);
        }
        bout.flush();
        imgMap.put(decryptedFile.getName(), bout.toByteArray());
        inputStream.close();
        bout.close();
    }
    
    public static void encryptedFile(File oldFile, File newFile) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(oldFile);
        FileOutputStream fileOutputStream = new FileOutputStream(newFile);
        BufferedInputStream inputStream = new BufferedInputStream(fileInputStream);
        BufferedOutputStream outputStream = new BufferedOutputStream(fileOutputStream);
        int b;
        while ((b = inputStream.read()) != -1) {
            outputStream.write(b ^ 0x2104E);
        }
        outputStream.flush();
        inputStream.close();
        outputStream.close();
    }
    
    public static void main(String[] args) throws IOException {
    }
}
