package org.come.until;

import java.util.Iterator;
import java.io.IOException;
import net.lingala.zip4j.io.ZipInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.io.File;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.exception.ZipException;
import java.io.InputStream;
import java.io.BufferedInputStream;
import net.lingala.zip4j.core.ZipFile;
import java.util.Map;

public class ZipUtils
{
    private static Map<String, ZipFile> cache;
    private static Map<String, BufferedInputStream> fileCache;
    
    public static InputStream unZipFile(String zipFileFullName, String targetFile, String password) {
        try {
            ZipFile zipFile = (ZipFile)ZipUtils.cache.get(zipFileFullName);
            if (zipFile == null) {
                zipFile = new ZipFile(zipFileFullName);
                ZipUtils.cache.put(zipFileFullName, zipFile);
            }
            zipFile.setFileNameCharset("GBK");
            if (zipFile.isEncrypted()) {
                zipFile.setPassword(password);
            }
            FileHeader fileHeader = zipFile.getFileHeader(targetFile);
            if (fileHeader == null) {
                return null;
            }
            return (InputStream)zipFile.getInputStream(fileHeader);
        }
        catch (ZipException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static void addFileToZip(String targetPath, String zipFile, String password) {
        try {
            ZipParameters parameters = new ZipParameters();
            parameters.setCompressionMethod(8);
            parameters.setCompressionLevel(5);
            if (password.length() > 0) {
                parameters.setEncryptFiles(true);
                parameters.setEncryptionMethod(99);
                parameters.setAesKeyStrength(3);
                parameters.setPassword(password);
            }
            ZipFile zip = new ZipFile(zipFile);
            File targetFile = new File(targetPath);
            BasicFileAttributes basicFileAttributes = Files.readAttributes(targetFile.toPath(), BasicFileAttributes.class, new LinkOption[0]);
            if (basicFileAttributes.isDirectory()) {
                zip.addFolder(targetFile, parameters);
            }
            else if (basicFileAttributes.isRegularFile()) {
                zip.addFile(targetFile, parameters);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static boolean deleteFile(String delFile, String zipFile, String password) {
        boolean flag = false;
        try {
            ZipFile zip = new ZipFile(zipFile);
            if (zip.isEncrypted()) {
                zip.setPassword(password);
            }
            zip.setFileNameCharset("GBK");
            List<FileHeader> list = (List)zip.getFileHeaders();
            for (int i = 0; i < list.size(); ++i) {
                String name = ((FileHeader)list.get(i)).getFileName();
                if (name.endsWith(delFile)) {
                    zip.removeFile(name);
                    --i;
                    flag = true;
                }
            }
        }
        catch (ZipException e) {
            return false;
        }
        return flag;
    }
    
    public static boolean deleteFolder(String delFolder, String zipFile, String password) {
        try {
            ZipFile zip = new ZipFile(zipFile);
            if (zip.isEncrypted()) {
                zip.setPassword(password);
            }
            zip.setFileNameCharset("GBK");
            List<FileHeader> list = (List)zip.getFileHeaders();
            for (int i = 0; i < list.size(); ++i) {
                String name = ((FileHeader)list.get(i)).getFileName();
                if (((FileHeader)list.get(i)).getFileName().startsWith(delFolder)) {
                    zip.removeFile(name);
                    --i;
                }
            }
        }
        catch (ZipException e) {
            return false;
        }
        return true;
    }
    
    public static InputStream getInputStream(String str) {
        String[] split = str.split("/");
        String fileName = split[0];
        String fileStr = fileName + ".npk";
        if (new File(str).exists()) {
            File file = new File(str);
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                return (InputStream)fileInputStream;
            }
            catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        if (new File(fileStr).exists()) {
            InputStream inputStream = unZipFile(fileName + ".npk", str, "9FS38R6DS5741B31B885CFCE02E271E3");
            CheckTimer.add(new StreamTimer(System.currentTimeMillis() + 3000L, inputStream));
            return inputStream;
        }
        return null;
    }
    
    public static Image read(InputStream inputStream) {
        try {
            BufferedImage read = ImageIO.read(inputStream);
            return (Image)read;
        }
        catch (Exception e) {
            return CutButtonImage.getJT().getImage();
        }
    }
    
    static {
        ZipUtils.cache = new HashMap<>();
        ZipUtils.fileCache = new HashMap<>();
        new Thread(new CheckTimer()).start();
    }
    
    public static class StreamTimer
    {
        public long time;
        public InputStream inputStream;
        
        public StreamTimer(long time, InputStream inputStream) {
            this.time = time;
            this.inputStream = inputStream;
        }
    }
    
    public static class CheckTimer implements Runnable
    {
        private static final Object lock;
        private static final ArrayList<StreamTimer> list;
        
        @Override
        public void run() {
            while (true) {
                try {
                    long l = System.currentTimeMillis();
                    synchronized (CheckTimer.lock) {
                        Iterator<StreamTimer> iterator = CheckTimer.list.iterator();
                        while (iterator.hasNext()) {
                            StreamTimer next = (StreamTimer)iterator.next();
                            if (l - next.time >= 0L) {
                                try {
                                    if (next.inputStream == null) {
                                        continue;
                                    }
                                    else {
                                        ((ZipInputStream)next.inputStream).close(true);
                                    }
                                }
                                catch (IOException e) {
                                    e.printStackTrace();
                                }
                                finally {
                                    iterator.remove();
                                }
                            }
                        }
                    }
                    System.gc();
                    Thread.sleep(1000L);
                    continue;
                }
                catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
            }
        }
        
        public static void add(StreamTimer streamTimer) {
            synchronized (CheckTimer.lock) {
                CheckTimer.list.add(streamTimer);
            }
        }
        
        static {
            lock = new Object();
            list = new ArrayList<>();
        }
    }
}
