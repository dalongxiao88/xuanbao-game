package org.come.log;

import java.util.Enumeration;
import java.util.zip.ZipFile;
import java.util.zip.ZipEntry;
import java.io.BufferedOutputStream;
import java.util.zip.ZipInputStream;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.io.FileWriter;
import java.util.ArrayList;
import com.google.gson.internal.LinkedTreeMap;
import java.util.concurrent.atomic.AtomicReference;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.InputStream;
import org.come.until.GsonUtil;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
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
    public static File downloadFile1(String urlPath, String downloadDir, String fileFullName) {
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
            fileFullName = "ifupdateOld.txt";
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
    
    public static Map downloadVersionMap(String urlPath) {
        try {
            URL url = new URL(urlPath);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            InputStreamReader inputStreamReader = new InputStreamReader(bufferedInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            bufferedReader.close();
            inputStreamReader.close();
            bufferedInputStream.close();
            inputStream.close();
            return (Map)GsonUtil.getGsonUtil().getgson().fromJson(stringBuilder.toString(), Map.class);
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
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
            String fileFullName = "server.json";
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
            long fileLength = (long)httpURLConnection.getContentLength();
            OutputStream out = new FileOutputStream(file);
            int size = 0;
            long len = 0L;
            byte[] buf = new byte[2048];
            while ((size = bin.read(buf)) != -1) {
                System.out.println(len * 100L / fileLength + "%");
                LanderJPanel.labtext1.setText(len * 100L / fileLength + "%");
                len += (long)size;
                out.write(buf, 0, size);
            }
            bin.close();
            out.close();
            LanderJPanel.labtext1.setText("文件下载成功");
            LanderJPanel.labtext1.repaint();
            String ur = System.getProperty("user.dir");
            getZip(ur);
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
            String fileFullName = "game.exe";
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
        downloadFile1(ip + "/ifupdate.txt", ur + "/resource/other/", "ifupdate.txt");
        Map map = downloadVersionMap(ip + "/versionMap.txt");
        String filePath = ur + "/resource/other/ifupdate.txt";
        String filePathOld = ur + "/resource/other/ifupdateOld.txt";
        FileInputStream fin = null;
        FileInputStream finOld = null;
        try {
            fin = new FileInputStream(filePath);
            File file = new File(filePathOld);
            if (!file.exists()) {
                file.createNewFile();
            }
            finOld = new FileInputStream(filePathOld);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e2) {
            throw new RuntimeException(e2);
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
        catch (IOException e3) {
            e3.printStackTrace();
        }
        try {
            buffReader.close();
        }
        catch (IOException e3) {
            e3.printStackTrace();
        }
        String strTmpOld = "";
        try {
            while ((strTmpOld = buffReaderOld.readLine()) != null) {
                System.out.println(strTmpOld);
                inOld = strTmpOld;
            }
        }
        catch (IOException e4) {
            e4.printStackTrace();
        }
        try {
            buffReaderOld.close();
        }
        catch (IOException e4) {
            e4.printStackTrace();
        }
        AtomicReference<Boolean> errorFlag = new AtomicReference(Boolean.valueOf(false));
        if (!in.equals(inOld)) {
            LanderJPanel.getVersion().setText("最新版本：" + inOld);
            LanderJPanel.getKais().setText("下载中");
            LanderJPanel.labtext1.setText("0%");
            AtomicReference<Boolean> b = new AtomicReference(Boolean.valueOf(false));
            String finalIn = in;
            map.forEach((k, v)/* java.lang.Object,java.lang.Object, */ -> {
                if ((boolean)b.get()) {
                    LinkedTreeMap s = (LinkedTreeMap)v;
                    s.forEach((k1, v1)/* java.lang.Object,java.lang.Object, */ -> {
                        if (k1.toString().startsWith("tcp")) {
                            ArrayList<String> tcps = (ArrayList)v1;
                            Iterator iterator = tcps.iterator();
                            while (iterator.hasNext()) {
                                String tcpUrl = (String)iterator.next();
                                try {
                                    String[] paths = tcpUrl.split("/");
                                    String path = paths[paths.length - 1];
                                    long l = System.currentTimeMillis();
                                    MyJFrame.downloadTcp(tcpUrl, ur + "/" + l + ".zip");
                                }
                                catch (Exception e8) {
                                    LanderJPanel.getKais().setText("开始游戏");
                                    LanderJPanel.getKais().setVisible(false);
                                    LanderJPanel.labtext1.setVisible(false);
                                    errorFlag.set(Boolean.valueOf(true));
                                    return;
                                }
                            }
                        }
                        else if (k1.toString().startsWith("exe2")) {
                            LinkedTreeMap s2 = (LinkedTreeMap)v1;
                            try {
                                MyJFrame.downloadFileDK(s2.get("url").toString(), ur + "/");
                            }
                            catch (Exception e9) {
                                LanderJPanel.getKais().setText("开始游戏");
                                LanderJPanel.getKais().setVisible(false);
                                LanderJPanel.labtext1.setVisible(false);
                                errorFlag.set(Boolean.valueOf(true));
                                return;
                            }
                        }
                        else if (k1.toString().startsWith("exe")) {
                            LinkedTreeMap s3 = (LinkedTreeMap)v1;
                            try {
                                MyJFrame.downloadFile2(s3.get("url").toString(), ur + "/");
                            }
                            catch (Exception e10) {
                                LanderJPanel.getKais().setText("开始游戏");
                                LanderJPanel.getKais().setVisible(false);
                                LanderJPanel.labtext1.setVisible(false);
                                errorFlag.set(Boolean.valueOf(true));
                                return;
                            }
                        }
                        return;
                    });
                }
                if (k.equals(finalIn)) {
                    b.set(Boolean.valueOf(true));
                }
                return;
            });
            if ((boolean)errorFlag.get()) {
                LanderJPanel.getKais().setText("开始游戏");
                LanderJPanel.getKais().setVisible(false);
                if (LanderJPanel.customImageProgressBar != null) {
                    LanderJPanel.customImageProgressBar.setVisible(false);
                }
                LanderJPanel.isErr = Boolean.valueOf(true);
                return;
            }
            else {
                try {
                    Thread.sleep(1000L);
                }
                catch (InterruptedException e5) {
                    throw new RuntimeException(e5);
                }
                try {
                    updateName2(ur + "/resource/other/ifupdateOld.txt");
                }
                catch (IOException ex) {}
                File file2 = null;
                FileWriter fw = null;
                file2 = new File(filePathOld);
                try {
                    if (!file2.exists()) {
                        file2.createNewFile();
                    }
                    fw = new FileWriter(file2);
                    fw.write(in);
                    fw.flush();
                }
                catch (IOException e6) {
                    e6.printStackTrace();
                }
                finally {
                    if (fw != null) {
                        try {
                            fw.close();
                        }
                        catch (IOException e7) {
                            e7.printStackTrace();
                        }
                    }
                }
                LanderJPanel.getKais().setText("开始游戏");
                LanderJPanel.getKais().setVisible(false);
                deleteFile(filePathOld);
                LanderJPanel.isUpdata = Boolean.valueOf(true);
                if (LanderJPanel.customImageProgressBar != null) {
                    LanderJPanel.customImageProgressBar.setVisible(false);
                }
            }
        }
        else {
            deleteFile(filePathOld);
            LanderJPanel.getKais().setText("开始游戏");
            LanderJPanel.getKais().setVisible(false);
            if (LanderJPanel.customImageProgressBar != null) {
                LanderJPanel.customImageProgressBar.setVisible(false);
            }
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
        if (file.exists()) {
            file.delete();
        }
    }
    
    public static void getZip(String url) throws IOException {
        String ur = System.getProperty("user.dir");
        LanderJPanel.getKais().setText("更新中");
        ZipInputStream in = new ZipInputStream(new FileInputStream(url), Charset.forName("GBK"));
        ZipEntry entry;
        while ((entry = in.getNextEntry()) != null) {
            if (!entry.isDirectory()) {
                String filePath = ur + "/" + entry.getName();
                File file = new File(filePath);
                File parentFile = file.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdirs();
                }
                FileOutputStream out = new FileOutputStream(filePath);
                BufferedOutputStream bos = new BufferedOutputStream(out);
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) != -1) {
                    bos.write(buf, 0, len);
                }
                bos.close();
                out.close();
                try {
                    Thread.sleep(10L);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        in.close();
        delZipFile(url);
    }
    
    public static void getZip2(String url) throws IOException {
        String urlfile = url + "/res/FightingSkillFull/tcps.zip";
        ZipInputStream in = new ZipInputStream(new FileInputStream(urlfile), Charset.forName("GBK"));
        ZipEntry entry;
        while ((entry = in.getNextEntry()) != null) {
            if (!entry.isDirectory()) {
                FileOutputStream out = new FileOutputStream(url + "/res/FightingSkillFull/" + entry.getName());
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
                    delZipFile(url + "/res/FightingSkillFull/");
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
        deleteFile(ur + "/resource/other/ifupdate.txt");
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
