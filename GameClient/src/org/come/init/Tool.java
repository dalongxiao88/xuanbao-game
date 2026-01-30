package org.come.init;

import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.nio.charset.Charset;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import java.lang.reflect.Method;
import java.net.URLClassLoader;
import java.net.URI;
import java.awt.Desktop;
import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.File;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;

public class Tool
{
    public static JSONObject StrintToJson(String str) {
        try {
            if (str.charAt(0) != '{') {
                str = str.substring(1);
            }
            JSONObject jsonObject = new JSONObject(str);
            return jsonObject;
        }
        catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static String getWebCon(String domain) {
        StringBuffer sb = new StringBuffer();
        try {
            URL url = new URL(domain);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            in.close();
        }
        catch (Exception e) {
            return null;
        }
        return sb.toString();
    }
    
    public static String readToString(String fileName) {
        String encoding = "UTF-8";
        File file = new File(fileName);
        Long filelength = Long.valueOf(file.length());
        byte[] filecontent = new byte[filelength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
        try {
            return new String(filecontent, encoding);
        }
        catch (UnsupportedEncodingException e3) {
            System.err.println("The OS does not support " + encoding);
            e3.printStackTrace();
            return null;
        }
    }
    
    public static void browse2(String url) throws Exception {
        Desktop desktop = Desktop.getDesktop();
        if (Desktop.isDesktopSupported() && desktop.isSupported(Desktop.Action.BROWSE)) {
            URI uri = new URI(url);
            desktop.browse(uri);
        }
    }
    
    public static void loadJar(String jarPath) {
        File jarFile = new File(jarPath);
        Method method = null;
        try {
            method = URLClassLoader.class.getDeclaredMethod("addURL", new Class[] { URL.class });
        }
        catch (NoSuchMethodException | SecurityException e1) {
            e1.printStackTrace();
        }
        boolean accessible = method.isAccessible();
        try {
            method.setAccessible(true);
            URLClassLoader classLoader = (URLClassLoader)ClassLoader.getSystemClassLoader();
            URL url = jarFile.toURI().toURL();
            method.invoke(classLoader, new Object[] { url });
        }
        catch (Exception e2) {
            e2.printStackTrace();
        }
        finally {
            method.setAccessible(accessible);
        }
    }
    
    public static void parsing(JSONObject JSONArray, String version) {
        try {
            JSONArray JSONCommands = JSONArray.getJSONArray("commands");
            for (int i = 0; i < JSONCommands.length(); ++i) {
                try {
                    String string = (String)JSONCommands.get(i);
                    parsingCommand(string, version);
                    try {
                        Thread.sleep(100L);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
        }
        catch (JSONException e3) {
            e3.printStackTrace();
        }
    }
    
    public static void parsingCommand(String command, String version) {
        String[] args = command.split(" ");
        for (int i = 0; i < args.length; ++i) {
            args[i] = args[i].replace("%p%", System.getProperty("user.dir") + "/update/UpdatePacks/" + version + "/").replace("%s%", System.getProperty("user.dir") + "/");
        }
        try {
            String s = args[0];
            int n = -1;
            switch (s.hashCode()) {
                case 3643: {
                    if (s.equals("rm")) {
                        n = 0;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 3497: {
                    if (s.equals("mv")) {
                        n = 1;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 3181: {
                    if (s.equals("cp")) {
                        n = 2;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 103950895: {
                    if (s.equals("mkdir")) {
                        n = 3;
                        break;
                    }
                    else {
                        break;
                    }
                }
            }
            switch (n) {
                case 0: {
                    if (args.length > 1 && !FileUtils.deleteQuietly(new File(args[1]))) {
                        System.gc();
                        FileUtils.forceDelete(new File(args[1]));
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1: {
                    if (args.length > 2) {
                        if (Equ(args[2], '/')) {
                            FileUtils.moveFileToDirectory(new File(args[1]), new File(args[2]), true);
                            break;
                        }
                        else {
                            FileUtils.moveFile(new File(args[1]), new File(args[2]));
                            break;
                        }
                    }
                    else {
                        break;
                    }
                }
                case 2: {
                    if (args.length > 2) {
                        if (Equ(args[2], '/')) {
                            FileUtils.copyFileToDirectory(new File(args[1]), new File(args[2]), true);
                            break;
                        }
                        else {
                            FileUtils.copyFile(new File(args[1]), new File(args[2]));
                            break;
                        }
                    }
                    else {
                        break;
                    }
                }
                case 3: {
                    if (args.length > 1) {
                        FileUtils.forceMkdir(new File(args[1]));
                        break;
                    }
                    else {
                        break;
                    }
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static boolean Equ(String str, char ch) {
        return str.charAt(str.length() - 1) == ch;
    }
    
    public static void downloadHttpUrl(String url, String dir, String fileName, UpdateView updateView, long length) {
        InputStream input = null;
        OutputStream output = null;
        try {
            URL httpurl = new URL(url);
            File dirfile = new File(dir);
            if (!dirfile.exists()) {
                dirfile.mkdirs();
            }
            HttpURLConnection conn = (HttpURLConnection)httpurl.openConnection();
            input = conn.getInputStream();
            output = new FileOutputStream(dir + fileName);
            byte[] buffer = new byte[4096];
            long count = 0L;
            double p = 0.0;
            int n;
            while (-1 != (n = input.read(buffer))) {
                output.write(buffer, 0, n);
                count += (long)n;
                p = (double)count * 1.0 / (double)length * 100.0;
                updateView.getProgress().setBounds(258, 565, (int)((double)(int)p * 2.9), 29);
            }
        }
        catch (Exception e) {
            try {
                if (input != null) {
                    input.close();
                }
                if (output != null) {
                    output.close();
                }
            }
            catch (NullPointerException | IOException e2) {
                e2.printStackTrace();
            }
            e.printStackTrace();
        }
    }
    
    public static void run_cmd(String strcmd) {
    }
    
    public static void unZip(File srcFile, String destDirPath) throws RuntimeException {
        if (!srcFile.exists()) {
            throw new RuntimeException(srcFile.getPath() + "所指文件不存在");
        }
        ZipFile zipFile = null;
        try {
            zipFile = new ZipFile(srcFile, Charset.forName("GBK"));
            Enumeration entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = (ZipEntry)entries.nextElement();
                if (entry.isDirectory()) {
                    String dirPath = destDirPath + "/" + entry.getName();
                    File dir = new File(dirPath);
                    dir.mkdirs();
                }
                else {
                    File targetFile = new File(destDirPath + "/" + entry.getName());
                    if (!targetFile.getParentFile().exists()) {
                        targetFile.getParentFile().mkdirs();
                    }
                    targetFile.createNewFile();
                    InputStream is = zipFile.getInputStream(entry);
                    FileOutputStream fos = new FileOutputStream(targetFile);
                    byte[] buf = new byte[1024000];
                    int len;
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                    }
                    fos.close();
                    is.close();
                }
            }
        }
        catch (Exception e) {
            throw new RuntimeException("unzip error from ZipUtils", e);
        }
        finally {
            if (zipFile != null) {
                try {
                    zipFile.close();
                }
                catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }
}
