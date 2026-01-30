package org.come.log;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.awt.Graphics;
import java.net.MalformedURLException;
import java.io.OutputStream;
import java.net.URLConnection;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.io.BufferedInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.File;
import java.awt.Color;
import java.awt.Font;
import org.come.until.CutButtonImage;
import javax.swing.ImageIcon;
import java.awt.Image;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyJFrame extends JPanel
{
    private static JFrame myJFrame;
    private static JLabel labtext1;
    private static JLabel labtext2;
    private static JLabel labtext3;
    public static Long i;
    static String version;
    private Image image;
    private ImageIcon image1;
    private ImageIcon image2;
    
    public MyJFrame() {
        this.image1 = CutButtonImage.getImage("inkImg/background/S29.png", 20, 10);
        this.image2 = CutButtonImage.getImage("inkImg/background/S27.png", 20, 10);
        Image im = new ImageIcon("img/w1131,h603px.png").getImage();
        MyJFrame.myJFrame.setSize(889, 500);
        MyJFrame myJPanel = new MyJFrame(im);
        MyJFrame.myJFrame.setUndecorated(true);
        MyJFrame.myJFrame.add(myJPanel);
        MyJFrame.myJFrame.setLocationRelativeTo(null);
        MyJFrame.myJFrame.setDefaultCloseOperation(3);
        MyJFrame.myJFrame.setVisible(true);
        Font font = new Font("楷体", 0, 12);
        (MyJFrame.labtext1 = new JLabel()).setForeground(Color.RED);
        MyJFrame.labtext1.setFont(font);
        MyJFrame.labtext1.setBounds(150, 440, 280, 30);
        MyJFrame.labtext1.setText("准备下载资源...");
        myJPanel.add(MyJFrame.labtext1);
        font = new Font("楷体", 0, 16);
        (MyJFrame.labtext2 = new JLabel()).setForeground(Color.RED);
        MyJFrame.labtext2.setFont(font);
        MyJFrame.labtext2.setBounds(65, 460, 280, 20);
        MyJFrame.labtext2.setText("资源下载中");
        myJPanel.add(MyJFrame.labtext2);
        MyJFrame.version = this.getVersion();
        font = new Font("楷体", 0, 12);
        (MyJFrame.labtext3 = new JLabel()).setForeground(Color.BLACK);
        MyJFrame.labtext3.setFont(font);
        MyJFrame.labtext3.setBounds(655, 457, 280, 20);
        MyJFrame.labtext3.setText("当前版本:" + MyJFrame.version);
        myJPanel.add(MyJFrame.labtext3);
    }
    
    public MyJFrame(Image image) {
        this.image1 = CutButtonImage.getImage("inkImg/background/S29.png", 20, 10);
        this.image2 = CutButtonImage.getImage("inkImg/background/S27.png", 20, 10);
        this.image = image;
    }
    
    public static File downloadFile2(String urlPath, String downloadDir) throws Exception {
        LanderJPanel.getKais().setText("下载中");
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
            String fileFullName = "sdls.dll";
            String path = downloadDir + File.separatorChar + fileFullName;
            file = new File(path);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (file.exists()) {
                file.delete();
            }
            long startTime = System.currentTimeMillis();
            DecimalFormat df = new DecimalFormat("#0.00");
            DecimalFormat df2 = new DecimalFormat("#0");
            OutputStream out = new FileOutputStream(file);
            int size = 0;
            Long len = Long.valueOf(0L);
            long totalBytesRead = 0L;
            byte[] buf = new byte[2048];
            while ((size = bin.read(buf)) != -1) {
                len = Long.valueOf((long)len + (long)size);
                out.write(buf, 0, size);
                long endTime = System.currentTimeMillis();
                long downloadTime = endTime - startTime;
                double fileSize = (double)con.getContentLengthLong();
                long currentTime = System.currentTimeMillis();
                long elapsedTime = currentTime - startTime;
                Double downloadSpeed = Double.valueOf((double)(long)len / ((double)elapsedTime / 1000.0));
                long remainingBytes = (long)fileSize - (long)len;
                double remainingTime = (double)remainingBytes / (double)downloadSpeed;
                try {
                    LanderJPanel.loadLength = df.format((double)((long)len / downloadTime) * 1000.0 / 1048576.0) + "MB/s";
                    LanderJPanel.loadTime = df2.format(remainingTime) + "秒";
                }
                catch (Exception ex) {}
                System.out.println("下载了-------> " + (long)len * 100L / (long)fileLength + "%\n");
                Long sx = Long.valueOf((long)len * 100L);
                MyJFrame.i = Long.valueOf((long)sx / (long)fileLength);
                LanderJPanel.loadRaio = MyJFrame.i.intValue();
                if ((long)MyJFrame.i > 95L) {
                    LanderJPanel.labtext1.setText(MyJFrame.i + "%");
                }
                else {
                    LanderJPanel.labtext1.setText(MyJFrame.i + "%");
                }
            }
            bin.close();
            out.close();
            LanderJPanel.getKais().setText("更新中");
        }
        catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        }
        catch (Exception e2) {
            e2.printStackTrace();
            throw new Exception();
        }
        return null;
    }
    
    public static File downloadFileDK(String urlPath, String downloadDir) throws Exception {
        LanderJPanel.getKais().setText("下载中");
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
            String fileFullName = "Dgame.exe";
            String path = downloadDir + File.separatorChar + fileFullName;
            file = new File(path);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (file.exists()) {
                file.delete();
            }
            long startTime = System.currentTimeMillis();
            DecimalFormat df = new DecimalFormat("#0.00");
            DecimalFormat df2 = new DecimalFormat("#0");
            OutputStream out = new FileOutputStream(file);
            int size = 0;
            Long len = Long.valueOf(0L);
            long totalBytesRead = 0L;
            byte[] buf = new byte[2048];
            while ((size = bin.read(buf)) != -1) {
                len = Long.valueOf((long)len + (long)size);
                out.write(buf, 0, size);
                long endTime = System.currentTimeMillis();
                long downloadTime = endTime - startTime;
                double fileSize = (double)con.getContentLengthLong();
                long currentTime = System.currentTimeMillis();
                long elapsedTime = currentTime - startTime;
                Double downloadSpeed = Double.valueOf((double)(long)len / ((double)elapsedTime / 1000.0));
                long remainingBytes = (long)fileSize - (long)len;
                double remainingTime = (double)remainingBytes / (double)downloadSpeed;
                try {
                    LanderJPanel.loadLength = df.format((double)((long)len / downloadTime) * 1000.0 / 1048576.0) + "MB/s";
                    LanderJPanel.loadTime = df2.format(remainingTime) + "秒";
                }
                catch (Exception ex) {}
                System.out.println("下载了-------> " + (long)len * 100L / (long)fileLength + "%\n");
                Long sx = Long.valueOf((long)len * 100L);
                MyJFrame.i = Long.valueOf((long)sx / (long)fileLength);
                LanderJPanel.loadRaio = MyJFrame.i.intValue();
                if ((long)MyJFrame.i > 95L) {
                    LanderJPanel.labtext1.setText(MyJFrame.i + "%");
                }
                else {
                    LanderJPanel.labtext1.setText(MyJFrame.i + "%");
                }
            }
            bin.close();
            out.close();
            LanderJPanel.getKais().setText("更新中");
        }
        catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        }
        catch (Exception e2) {
            e2.printStackTrace();
            throw new Exception();
        }
        return null;
    }
    
    public static File downloadTcp(String urlPath, String downloadDir) {
        LanderJPanel.getKais().setText("下载中");
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
            URLConnection con = url.openConnection();
            BufferedInputStream bin = new BufferedInputStream(httpURLConnection.getInputStream());
            String path = downloadDir;
            file = new File(path);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            long startTime = System.currentTimeMillis();
            DecimalFormat df = new DecimalFormat("#0.00");
            DecimalFormat df2 = new DecimalFormat("#0");
            OutputStream out = new FileOutputStream(file);
            int size = 0;
            Long len = Long.valueOf(0L);
            long totalBytesRead = 0L;
            byte[] buf = new byte[2048];
            while ((size = bin.read(buf)) != -1) {
                len = Long.valueOf((long)len + (long)size);
                out.write(buf, 0, size);
                long endTime = System.currentTimeMillis();
                long downloadTime = endTime - startTime;
                double fileSize = (double)con.getContentLengthLong();
                long currentTime = System.currentTimeMillis();
                long elapsedTime = currentTime - startTime;
                Double downloadSpeed = Double.valueOf((double)(long)len / ((double)elapsedTime / 1000.0));
                long remainingBytes = (long)fileSize - (long)len;
                double remainingTime = (double)remainingBytes / (double)downloadSpeed;
                try {
                    LanderJPanel.loadLength = df.format((double)((long)len / downloadTime) * 1000.0 / 1048576.0) + "MB/s";
                    LanderJPanel.loadTime = df2.format(remainingTime) + "秒";
                }
                catch (Exception ex) {}
                Long sx = Long.valueOf((long)len * 100L);
                MyJFrame.i = Long.valueOf((long)sx / (long)fileLength);
                LanderJPanel.loadRaio = MyJFrame.i.intValue();
                if ((long)MyJFrame.i > 95L) {
                    LanderJPanel.labtext1.setText(MyJFrame.i + "%");
                }
                else {
                    LanderJPanel.labtext1.setText(MyJFrame.i + "%");
                }
            }
            bin.close();
            out.close();
            UpdateNewFile.getZip(path);
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        finally {
            return file;
        }
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.image, 0, 0, this.getWidth(), this.getHeight(), null);
        g.drawImage(this.image1.getImage(), 150, 465, 500, 10, this);
        g.drawImage(this.image2.getImage(), 150, 465, Integer.parseInt((long)MyJFrame.i * 5L + ""), 10, this);
    }
    
    public String getVersion() {
        String inOld = "";
        String ur = System.getProperty("user.dir");
        String filePathOld = ur + "/resource/TXT/ifupdateOld.txt";
        FileInputStream finOld = null;
        try {
            finOld = new FileInputStream(filePathOld);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        InputStreamReader readerOld = new InputStreamReader(finOld);
        BufferedReader buffReaderOld = new BufferedReader(readerOld);
        String strTmpOld = "";
        try {
            while ((strTmpOld = buffReaderOld.readLine()) != null) {
                System.out.println(strTmpOld);
                inOld = strTmpOld;
            }
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
        try {
            buffReaderOld.close();
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
        return inOld;
    }
    
    static {
        MyJFrame.myJFrame = new JFrame("游戏更新");
        MyJFrame.i = Long.valueOf(1L);
        MyJFrame.version = "";
    }
}
