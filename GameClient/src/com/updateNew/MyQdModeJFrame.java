package com.updateNew;

import java.io.FileWriter;
import org.come.test.Main;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.awt.Graphics;
import java.io.OutputStream;
import java.net.URLConnection;
import java.io.IOException;
import java.net.MalformedURLException;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import java.awt.Font;
import javax.swing.JRadioButton;
import com.tool.tcpimg.UIUtils;
import javax.swing.ImageIcon;
import java.awt.Image;
import com.tool.btn.RoleCaoZuoBtn;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyQdModeJFrame extends JPanel
{
    private static JFrame myJFrame;
    private static JLabel labtext1;
    private static JLabel labtext2;
    private static JLabel labtext3;
    private static RoleCaoZuoBtn sureGive;
    private static RoleCaoZuoBtn deleteGive;
    static int i;
    public static String version;
    private String txt;
    private Image image;
    
    public MyQdModeJFrame() {
        this.txt = "红木";
        MyQdModeJFrame.version = this.getVersion();
        updateStyle(this.txt);
        Image im = new ImageIcon("img/bjt.PNG").getImage();
        MyQdModeJFrame.myJFrame.setSize(350, 560);
        MyQdModeJFrame myJPanel = new MyQdModeJFrame(im);
        (MyQdModeJFrame.sureGive = new RoleCaoZuoBtn("img/button/yjdkdl.png", 1, "", 3345, UIUtils.COLOR_BTNPUTONG)).setBounds(100, 350, 68, 56);
        myJPanel.add(MyQdModeJFrame.sureGive);
        (MyQdModeJFrame.deleteGive = new RoleCaoZuoBtn("img/button/ptdl.png", 1, "", 3346, UIUtils.COLOR_BTNPUTONG)).setBounds(200, 350, 68, 56);
        myJPanel.add(MyQdModeJFrame.deleteGive);
        System.out.println(this.txt);
        JLabel label1 = new JLabel("选择界面风格：");
        JRadioButton rb1 = new JRadioButton("红木", true);
        JRadioButton rb2 = new JRadioButton("水墨");
        label1.setFont(new Font("楷体", 1, 16));
        ButtonGroup group = new ButtonGroup();
        group.add(rb1);
        group.add(rb2);
        myJPanel.add(label1);
        myJPanel.add(rb1);
        myJPanel.add(rb2);
        MyQdModeJFrame.myJFrame.setUndecorated(true);
        MyQdModeJFrame.myJFrame.add(myJPanel);
        MyQdModeJFrame.myJFrame.setLocationRelativeTo(null);
        MyQdModeJFrame.myJFrame.setDefaultCloseOperation(3);
        MyQdModeJFrame.myJFrame.setVisible(true);
        rb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyQdModeJFrame.this.txt = ((JRadioButton)e.getSource()).getText();
                MyQdModeJFrame.updateStyle(MyQdModeJFrame.this.txt);
            }
        });
        rb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyQdModeJFrame.this.txt = ((JRadioButton)e.getSource()).getText();
                MyQdModeJFrame.updateStyle(MyQdModeJFrame.this.txt);
            }
        });
    }
    
    public MyQdModeJFrame(Image image) {
        this.txt = "红木";
        this.image = image;
    }
    
    public static File downloadFile2(String urlPath, String downloadDir) {
        new MyQdModeJFrame();
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
            MyQdModeJFrame.labtext1.setBounds(150, 440, 280, 30);
            MyQdModeJFrame.labtext1.setText("您要下载的文件大小为：" + fileLength / 1048576 + "MB");
            MyQdModeJFrame.labtext2.setBounds(65, 460, 280, 20);
            MyQdModeJFrame.labtext2.setText("资源下载中");
            MyQdModeJFrame.labtext3.setBounds(655, 457, 280, 20);
            MyQdModeJFrame.labtext3.setText("当前版本：" + MyQdModeJFrame.version);
            URLConnection con = url.openConnection();
            BufferedInputStream bin = new BufferedInputStream(httpURLConnection.getInputStream());
            String fileFullName = "xyWjGame-0.1.exe";
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
                MyQdModeJFrame.labtext1.setBounds(150, 440, 280, 30);
                MyQdModeJFrame.i = len * 100 / fileLength;
                if (MyQdModeJFrame.i > 70) {
                    MyQdModeJFrame.labtext1.setText("正在解压资源......");
                    MyQdModeJFrame.labtext2.setBounds(65, 460, 280, 20);
                    MyQdModeJFrame.labtext2.setText("资源处理中");
                    MyQdModeJFrame.labtext3.setBounds(655, 457, 280, 20);
                    MyQdModeJFrame.labtext3.setText("当前版本：" + MyQdModeJFrame.version);
                }
                else {
                    MyQdModeJFrame.labtext1.setText("下载进度：" + (len * 100 / fileLength + 25) + "%\n");
                    MyQdModeJFrame.labtext2.setBounds(65, 460, 280, 20);
                    MyQdModeJFrame.labtext2.setText("资源下载中");
                    MyQdModeJFrame.labtext3.setBounds(655, 457, 280, 20);
                    MyQdModeJFrame.labtext3.setText("当前版本：" + MyQdModeJFrame.version);
                }
            }
            bin.close();
            out.close();
            MyQdModeJFrame.labtext1.setText("游戏更新成功！正在启动请稍后！！！");
            MyQdModeJFrame.labtext2.setBounds(65, 460, 280, 20);
            MyQdModeJFrame.labtext2.setText("正在启动");
            MyQdModeJFrame.labtext3.setBounds(655, 457, 280, 20);
            MyQdModeJFrame.labtext3.setText("当前版本：" + MyQdModeJFrame.version);
            Thread.sleep(1500L);
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException e2) {
            e2.printStackTrace();
            MyQdModeJFrame.labtext1.setText("文件下载失败！");
        }
        finally {
            return file;
        }
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.image, 0, 0, this.getWidth(), this.getHeight(), null);
    }
    
    public String getVersion() {
        String inOld = "";
        String ur = System.getProperty("user.dir");
        String filePathOld = ur + "/resource/TXT/ifupdate.txt";
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
    
    public static String ggt(String[] s) {
        getSingle();
        MyIsif.ifs = "D";
        try {
            Main.main(s);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        MyQdModeJFrame.myJFrame.dispose();
        return null;
    }
    
    public static String ggtd(String[] s) throws Exception {
        MyIsif.ifs = "DK";
        getMultiple();
        Main.main(s);
        MyQdModeJFrame.myJFrame.dispose();
        return null;
    }
    
    public static void getMultiple() {
        String in = "";
        String ur = System.getProperty("user.dir");
        String filePath = ur + "/resource/other/StartUpMode.txt";
        FileInputStream fin = null;
        try {
            fin = new FileInputStream(filePath);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        InputStreamReader reader = new InputStreamReader(fin);
        BufferedReader buffReader = new BufferedReader(reader);
        String strTmp = "";
        try {
            while ((strTmp = buffReader.readLine()) != null) {
                System.out.println(strTmp);
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
        File file = null;
        FileWriter fw = null;
        file = new File(filePath);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            fw = new FileWriter(file);
            fw.write("Multiple");
            fw.flush();
        }
        catch (IOException e3) {
            e3.printStackTrace();
        }
        finally {
            if (fw != null) {
                try {
                    fw.close();
                }
                catch (IOException e4) {
                    e4.printStackTrace();
                }
            }
        }
    }
    
    public static void getSingle() {
        String in = "";
        String ur = System.getProperty("user.dir");
        String filePath = ur + "/resource/other/StartUpMode.txt";
        FileInputStream fin = null;
        try {
            fin = new FileInputStream(filePath);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        InputStreamReader reader = new InputStreamReader(fin);
        BufferedReader buffReader = new BufferedReader(reader);
        String strTmp = "";
        try {
            while ((strTmp = buffReader.readLine()) != null) {
                System.out.println(strTmp);
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
        File file = null;
        FileWriter fw = null;
        file = new File(filePath);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            fw = new FileWriter(file);
            fw.write("Single");
            fw.flush();
        }
        catch (IOException e3) {
            e3.printStackTrace();
        }
        finally {
            if (fw != null) {
                try {
                    fw.close();
                }
                catch (IOException e4) {
                    e4.printStackTrace();
                }
            }
        }
    }
    
    public static void updateStyle(String txt) {
        String in = "";
        String ur = System.getProperty("user.dir");
        String filePath = ur + "/resource/other/style.txt";
        FileInputStream fin = null;
        try {
            fin = new FileInputStream(filePath);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        InputStreamReader reader = new InputStreamReader(fin);
        BufferedReader buffReader = new BufferedReader(reader);
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
        File file = null;
        FileWriter fw = null;
        file = new File(filePath);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            fw = new FileWriter(file);
            fw.write(txt);
            MyIsif.style = txt;
            fw.flush();
        }
        catch (IOException e3) {
            e3.printStackTrace();
        }
        finally {
            if (fw != null) {
                try {
                    fw.close();
                }
                catch (IOException e4) {
                    e4.printStackTrace();
                }
            }
        }
    }
    
    static {
        MyQdModeJFrame.myJFrame = new JFrame("登录方式");
        MyQdModeJFrame.i = 1;
        MyQdModeJFrame.version = "";
    }
}
