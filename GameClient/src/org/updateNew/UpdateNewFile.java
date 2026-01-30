package org.updateNew;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipFile;
import java.util.zip.ZipEntry;
import java.io.BufferedOutputStream;
import java.util.zip.ZipInputStream;
import java.nio.charset.Charset;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import com.updateNew.MyJFrame;
import org.come.login.LoginJpanel;

import java.io.OutputStream;
import java.net.URLConnection;
import java.io.IOException;
import java.net.MalformedURLException;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.File;

public class UpdateNewFile
{
    public static File downloadFile1(String urlPath, String downloadDir) {
        File file = null;
        try {
            URL url = new URL(urlPath);
            URLConnection urlConnection = url.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection)urlConnection;
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            httpURLConnection.connect();
            int fileLength = httpURLConnection.getContentLength();
            System.out.println("您要下载的文件大小为:" + fileLength / 1048576 + "MB");
            URLConnection con = url.openConnection();
            BufferedInputStream bin = new BufferedInputStream(httpURLConnection.getInputStream());
            String fileFullName = "ifupdateOld.txt";
            String path = downloadDir + File.separatorChar + fileFullName;
            file = new File(path);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            OutputStream out = new FileOutputStream(file);
            int size = 0;
            int len = 0;
            byte[] buf = new byte[2048];
            while ((size = bin.read(buf)) != -1) {
                len += size;
                out.write(buf, 0, size);
                System.out.println("下载了-------> " + len * 100 / fileLength + "%\n");
            }
            bin.close();
            out.close();
            System.out.println("文件下载成功！");
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException e2) {
            e2.printStackTrace();
            System.out.println("文件下载失败！");
        }
        finally {
            return file;
        }
    }
    
    public static File downloadFile3(String urlPath, String downloadDir) {
        File file = null;
        try {
            URL url = new URL(urlPath);
            URLConnection urlConnection = url.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection)urlConnection;
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            httpURLConnection.connect();
            BufferedInputStream bin = new BufferedInputStream(httpURLConnection.getInputStream());
            String fileFullName = "resource\\other\\server.json";
            String path = downloadDir + File.separatorChar + fileFullName;
            file = new File(path);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            OutputStream out = new FileOutputStream(file);
            int size = 0;
            int len = 0;
            byte[] buf = new byte[2048];
            while ((size = bin.read(buf)) != -1) {
                len += size;
                out.write(buf, 0, size);
            }
            bin.close();
            out.close();
            System.out.println("文件下载成功！");
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException e2) {
            e2.printStackTrace();
            System.out.println("文件下载失败！");
        }
        finally {
            return file;
        }
    }
    
    public static File downloadFile4(String urlPath, String downloadDir) {
        File file = null;
        try {
            URL url = new URL(urlPath);
            URLConnection urlConnection = url.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection)urlConnection;
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            httpURLConnection.connect();
            BufferedInputStream bin = new BufferedInputStream(httpURLConnection.getInputStream());
            String fileFullName = "tcps.zip";
            String path = downloadDir + File.separatorChar + fileFullName;
            file = new File(path);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            OutputStream out = new FileOutputStream(file);
            int size = 0;
            int len = 0;
            byte[] buf = new byte[2048];
            while ((size = bin.read(buf)) != -1) {
                len += size;
                out.write(buf, 0, size);
            }
            bin.close();
            out.close();
            System.out.println("文件下载成功！");
            return file;
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException e2) {
            e2.printStackTrace();
            System.out.println("文件下载失败！");
        }
        finally {
            return file;
        }
    }
    
    public static File downloadFile5(String urlPath, String downloadDir) {
        File file = null;
        try {
            URL url = new URL(urlPath);
            URLConnection urlConnection = url.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection)urlConnection;
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            httpURLConnection.connect();
            BufferedInputStream bin = new BufferedInputStream(httpURLConnection.getInputStream());
            String fileFullName = "material.zip";
            String path = downloadDir + File.separatorChar + fileFullName;
            file = new File(path);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            OutputStream out = new FileOutputStream(file);
            int size = 0;
            int len = 0;
            byte[] buf = new byte[2048];
            while ((size = bin.read(buf)) != -1) {
                len += size;
                out.write(buf, 0, size);
            }
            bin.close();
            out.close();
            System.out.println("文件下载成功！");
            String ur = System.getProperty("user.dir");
            decompress(ur + "/skin/material.zip", ur + "/skin/");
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException e2) {
            e2.printStackTrace();
            System.out.println("文件下载失败！");
        }
        finally {
            return file;
        }
    }
    
    public static File downloadFile6(String urlPath, String downloadDir) {
        File file = null;
        try {
            URL url = new URL(urlPath);
            URLConnection urlConnection = url.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection)urlConnection;
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            httpURLConnection.connect();
            BufferedInputStream bin = new BufferedInputStream(httpURLConnection.getInputStream());
            String fileFullName = "tcps.zip";
            String path = downloadDir + File.separatorChar + fileFullName;
            file = new File(path);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            OutputStream out = new FileOutputStream(file);
            int size = 0;
            int len = 0;
            byte[] buf = new byte[2048];
            while ((size = bin.read(buf)) != -1) {
                len += size;
                out.write(buf, 0, size);
            }
            bin.close();
            out.close();
            System.out.println("文件下载成功！");
            return file;
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException e2) {
            e2.printStackTrace();
            System.out.println("文件下载失败！");
        }
        finally {
            return file;
        }
    }
    
    public static File downloadFile22(String urlPath, String downloadDir) {
        new MyJFrame();
        File file = null;
        try {
            URL url = new URL(urlPath);
            URLConnection urlConnection = url.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection)urlConnection;
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            httpURLConnection.connect();
            int fileLength = httpURLConnection.getContentLength();
            System.out.println("您要下载的文件大小为:" + fileLength / 1048576 + "MB");
            URLConnection con = url.openConnection();
            BufferedInputStream bin = new BufferedInputStream(httpURLConnection.getInputStream());
            String fileFullName = "Multiple.exe";
            String path = downloadDir + File.separatorChar + fileFullName;
            file = new File(path);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            OutputStream out = new FileOutputStream(file);
            int size = 0;
            int len = 0;
            byte[] buf = new byte[2048];
            while ((size = bin.read(buf)) != -1) {
                len += size;
                out.write(buf, 0, size);
                System.out.println("下载了-------> " + len * 100 / fileLength + "%\n");
            }
            bin.close();
            out.close();
            System.out.println("游戏更新成功！正在启动请稍后！！！");
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException e2) {
            e2.printStackTrace();
            System.out.println("文件下载失败！");
        }
        finally {
            return file;
        }
    }
    
    public static void updateNewexe(String ip) {
        String in = "";
        String inOld = "";
        String ur = System.getProperty("user.dir");
        downloadFile1("http://" + ip + ":"+ LoginJpanel.Webport+"/updateNewFile/ifupdate.txt", ur + "/resource/TXT/");
        downloadFile3("http://" + ip + ":"+ LoginJpanel.Webport+"/updateNewFile/server.json", ur);
        String filePath = ur + "/resource/TXT/ifupdate.txt";
        String filePathOld = ur + "/resource/TXT/ifupdateOld.txt";
        FileInputStream fin = null;
        FileInputStream finOld = null;
        try {
            fin = new FileInputStream(filePath);
            finOld = new FileInputStream(filePathOld);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        InputStreamReader reader = new InputStreamReader(fin);
        BufferedReader buffReader = new BufferedReader(reader);
        InputStreamReader readerOld = new InputStreamReader(finOld);
        BufferedReader buffReaderOld = new BufferedReader(readerOld);
        String strTmp = "";
        try {
            while ((strTmp = buffReader.readLine()) != null) {
                in = strTmp;
            }
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
        try {
            buffReader.close();
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
        String strTmpOld = "";
        try {
            while ((strTmpOld = buffReaderOld.readLine()) != null) {
                System.out.println(strTmpOld);
                inOld = strTmpOld;
            }
        }
        catch (IOException e3) {
            e3.printStackTrace();
        }
        try {
            buffReaderOld.close();
        }
        catch (IOException e3) {
            e3.printStackTrace();
        }
        if (!in.equals(inOld)) {
            MyJFrame.downloadFile2("http://" + ip + ":"+ LoginJpanel.Webport+"/updateNewFile/sdls.dll", ur);
            downloadFile22("http://" + ip + ":"+ LoginJpanel.Webport+"/updateNewFile/Multiple.exe", ur);
            try {
                updateName2(ur + "/resource/TXT/ifupdateOld.txt");
                System.err.println("启动登录器");
                Runtime.getRuntime().exec(ur + "/gatU.bat");
                System.exit(0);
            }
            catch (IOException ex) {}
            File file = null;
            FileWriter fw = null;
            file = new File(filePathOld);
            try {
                if (!file.exists()) {
                    file.createNewFile();
                }
                fw = new FileWriter(file);
                fw.write(in);
                fw.flush();
            }
            catch (IOException e4) {
                e4.printStackTrace();
            }
            finally {
                if (fw != null) {
                    try {
                        fw.close();
                    }
                    catch (IOException e5) {
                        e5.printStackTrace();
                    }
                }
            }
            deleteFile(filePathOld);
        }
        else {
            deleteFile(filePathOld);
        }
    }
    
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("删除单个文件" + fileName + "成功！");
                return true;
            }
            System.out.println("删除单个文件" + fileName + "失败！");
            return false;
        }
        else {
            System.out.println("删除单个文件失败：" + fileName + "不存在！");
            return false;
        }
    }
    
    public static void delZipFile(String url) {
        File file = new File(url);
        if (file.isDirectory()) {
            File[] files;
            for (File f : files = file.listFiles()) {
                if (f.getName().endsWith(".zip")) {
                    if (f.delete()) {
                        System.out.println("zip文件已经删除");
                    }
                    else {
                        System.out.println("zip文件删除失败");
                    }
                }
            }
        }
    }
    
    public static void getZip(String url) throws IOException {
        String urlfile = url + "/resource/FightingSkill/tcps.zip";
        ZipInputStream in = new ZipInputStream(new FileInputStream(urlfile), Charset.forName("GBK"));
        ZipEntry entry;
        while ((entry = in.getNextEntry()) != null) {
            if (!entry.isDirectory()) {
                FileOutputStream out = new FileOutputStream(url + "/resource/FightingSkill/" + entry.getName());
                BufferedOutputStream bos = new BufferedOutputStream(out);
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) != -1) {
                    bos.write(buf, 0, len);
                }
                bos.close();
                out.close();
                try {
                    Thread.sleep(1500L);
                    delZipFile(url + "/resource/FightingSkill/");
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public static void getZip2(String url) throws IOException {
        String urlfile = url + "/resource/FightingSkillFull/tcps.zip";
        ZipInputStream in = new ZipInputStream(new FileInputStream(urlfile), Charset.forName("GBK"));
        ZipEntry entry;
        while ((entry = in.getNextEntry()) != null) {
            if (!entry.isDirectory()) {
                FileOutputStream out = new FileOutputStream(url + "/resource/FightingSkillFull/" + entry.getName());
                BufferedOutputStream bos = new BufferedOutputStream(out);
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) != -1) {
                    bos.write(buf, 0, len);
                }
                bos.close();
                out.close();
                try {
                    Thread.sleep(1500L);
                    delZipFile(url + "/resource/FightingSkillFull/");
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public static void getZip1(String url) throws IOException {
        String urlfile = url + "/skin/material.zip";
        ZipInputStream in = new ZipInputStream(new FileInputStream(urlfile), Charset.forName("GBK"));
        ZipEntry entry;
        while ((entry = in.getNextEntry()) != null) {
            if (!entry.isDirectory()) {
                FileOutputStream out = new FileOutputStream(url + "/skin/" + entry.getName());
                BufferedOutputStream bos = new BufferedOutputStream(out);
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) != -1) {
                    bos.write(buf, 0, len);
                }
                bos.close();
                out.close();
                try {
                    Thread.sleep(1500L);
                    delZipFile(url + "/skin/");
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public static void decompress(String zipFile, String dstPath) throws IOException {
        File pathFile = new File(dstPath);
        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }
        ZipFile zip = new ZipFile(zipFile);
        Enumeration entries = zip.entries();
        while (entries.hasMoreElements()) {
            ZipEntry entry = (ZipEntry)entries.nextElement();
            String zipEntryName = entry.getName();
            InputStream in = null;
            OutputStream out = null;
            try {
                in = zip.getInputStream(entry);
                String outPath = (dstPath + "/" + zipEntryName).replaceAll("\\*", "/");
                File file = new File(outPath.substring(0, outPath.lastIndexOf(47)));
                if (!file.exists()) {
                    file.mkdirs();
                }
                if (new File(outPath).isDirectory()) {
                    continue;
                }
                else {
                    out = new FileOutputStream(outPath);
                    byte[] buf1 = new byte[1024];
                    int len;
                    while ((len = in.read(buf1)) > 0) {
                        out.write(buf1, 0, len);
                    }
                }
            }
            finally {
                if (null != in) {
                    in.close();
                }
                if (null != out) {
                    out.close();
                }
            }
        }
        zip.close();
        delZipFile(dstPath);
    }
    
    public static void updateName2(String url) throws IOException {
        File oldFile = new File(url);
        if (!oldFile.exists()) {
            oldFile.createNewFile();
        }
        String ur = System.getProperty("user.dir");
        deleteFile(ur + "/resource/TXT/ifupdate.txt");
        System.out.println("修改前文件名称是：" + oldFile.getName());
        String rootPath = oldFile.getParent();
        System.out.println("根路径是：" + rootPath);
        File newFile = new File(rootPath + File.separator + "/ifupdate.txt");
        System.out.println("修改后文件名称是：" + newFile.getName());
        if (oldFile.renameTo(newFile)) {
            System.out.println("修改成功!");
        }
        else {
            System.out.println("修改失败");
        }
    }
    
    public static void main(String[] args) {
    }
}
