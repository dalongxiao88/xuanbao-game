package org.come.log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.util.Properties;
import java.awt.Image;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import org.come.test.Main;
import javax.swing.JInternalFrame;
import javax.swing.JComboBox;
import javax.swing.JFrame;

public class LaddJFrame extends JFrame
{
    public static JComboBox<String> comboBox2;
    public static JComboBox<String> comboBox1;
    public static JComboBox<String> comboBox;
    public static JComboBox<String> comboBoxMu;
    public static JFrame frame;
    public JInternalFrame f;
    private Main LanderJFrame;
    
    public LaddJFrame() {
        this.f = new JInternalFrame("Cheapest To Deliver", true, true, true, true);
        this.init();
    }
    
    public void init() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        double n = toolkit.getScreenSize().getWidth() - 750.0;
        Main landerJFrame = this.LanderJFrame;
        int x = (int)(n - (double)Main.frame.getX());
        double n2 = toolkit.getScreenSize().getHeight() - 550.0;
        Main landerJFrame2 = this.LanderJFrame;
        int y = (int)(n2 - (double)Main.frame.getY());
        LaddJFrame.frame.setLocation(x, y);
        LaddJFrame.frame = Singleton1.getInstance();
        Container ct = LaddJFrame.frame.getContentPane();
        LaddJFrame.frame.setTitle("游戏设置");
        Toolkit tool = LaddJFrame.frame.getToolkit();
        Image myimage = tool.getImage("img/icon/ico.png");
        LaddJFrame.frame.setIconImage(myimage);
        LaddJFrame.frame.setVisible(true);
        LaddJFrame.frame.setResizable(false);
        LaddJFrame.frame.setFocusCycleRoot(false);
        Font font = new Font("宋体", 0, 13);
        LaddJFrame.frame.setBounds(x, y, 347, 296);
        ct.setLayout(null);
        JLabel lblNewLabel = new JLabel("选择登录界面风格：");
        lblNewLabel.setFont(font);
        lblNewLabel.setBounds(20, 14, 150, 15);
        ct.add(lblNewLabel);
        JLabel lblNewLabel2 = new JLabel("选择登录方式：");
        lblNewLabel2.setFont(font);
        lblNewLabel2.setBounds(20, 44, 150, 15);
        ct.add(lblNewLabel2);
        JLabel lblNewLabel3 = new JLabel("选择界面UI风格：");
        lblNewLabel3.setFont(font);
        lblNewLabel3.setBounds(20, 74, 150, 15);
        ct.add(lblNewLabel3);
        JLabel lblNewLabel4 = new JLabel("欢迎页音乐设置：");
        lblNewLabel4.setFont(font);
        lblNewLabel4.setBounds(20, 104, 150, 15);
        ct.add(lblNewLabel4);
        (LaddJFrame.comboBox1 = new JComboBox<>()).setFont(font);
        LaddJFrame.comboBox1.setBounds(140, 41, 95, 21);
        LaddJFrame.comboBox1.addItem("独立窗口版");
        LaddJFrame.comboBox1.setEditable(true);
        ct.add(LaddJFrame.comboBox1);
        (LaddJFrame.comboBox2 = new JComboBox<>()).setBounds(140, 71, 95, 21);
        LaddJFrame.comboBox2.setFont(font);
        LaddJFrame.comboBox2.addItem("水墨书卷");
        LaddJFrame.comboBox2.addItem("红木经典");
        LaddJFrame.comboBox2.addItem("丹心墨意");
        LaddJFrame.comboBox2.addItem("新版水墨");
        LaddJFrame.comboBox2.setEditable(true);
        ct.add(LaddJFrame.comboBox2);
        (LaddJFrame.comboBox = new JComboBox<>()).setBounds(140, 11, 95, 21);
        LaddJFrame.comboBox.setFont(font);
        LaddJFrame.comboBox.addItem("雨过天晴");
        LaddJFrame.comboBox.addItem("红木经典");
        LaddJFrame.comboBox.setEditable(true);
        ct.add(LaddJFrame.comboBox);
        (LaddJFrame.comboBoxMu = new JComboBox<>()).setBounds(140, 101, 95, 21);
        LaddJFrame.comboBoxMu.setFont(font);
        LaddJFrame.comboBoxMu.addItem("开启");
        LaddJFrame.comboBoxMu.addItem("关闭");
        LaddJFrame.comboBoxMu.setEditable(true);
        ct.add(LaddJFrame.comboBoxMu);
        JLabel lblResult = new JLabel("");
        lblResult.setFont(font);
        lblResult.setBounds(0, 150, 146, 15);
        LaddJFrame.frame.getContentPane().add(lblResult);
        JLabel lblResult2 = new JLabel("");
        lblResult2.setFont(font);
        lblResult2.setBounds(0, 170, 146, 15);
        LaddJFrame.frame.getContentPane().add(lblResult2);
        JLabel lblResult3 = new JLabel("");
        lblResult3.setFont(font);
        lblResult3.setBounds(0, 190, 146, 15);
        LaddJFrame.frame.getContentPane().add(lblResult3);
        JButton btnNewButton = new JButton("确定");
        btnNewButton.setBounds(62, 220, 75, 23);
        btnNewButton.setFont(font);
        ct.add(btnNewButton);
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                lblResult.setText("您选择的是：" + LaddJFrame.comboBox.getSelectedItem());
                lblResult2.setText("您选择的是：" + LaddJFrame.comboBox1.getSelectedItem());
                lblResult3.setText("您选择的是：" + LaddJFrame.comboBox2.getSelectedItem());
                LaddJFrame.writeTxt();
                LaddJFrame.writeTxtlog();
                LaddJFrame.frame.setVisible(false);
            }
        });
        JButton btnNewButtonx = new JButton("取消");
        btnNewButtonx.setBounds(200, 220, 75, 23);
        btnNewButtonx.setFont(font);
        ct.add(btnNewButtonx);
        btnNewButtonx.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                LaddJFrame.frame.setVisible(false);
            }
        });
    }
    
    public static void Systeminitial() {
        LaddJFrame.comboBox2.addItem("红木经典");
        LaddJFrame.comboBox.addItem("红木经典");
        LaddJFrame.comboBox1.addItem("独立窗口版");
        LaddJFrame.comboBoxMu.addItem("开启");
    }
    
    public static void readSysteminitlog() {
        Properties properties = new Properties();
        FileInputStream fis = null;
        InputStreamReader isr = null;
        try {
            fis = new FileInputStream("resource/other/Config.txt");
            isr = new InputStreamReader(fis, "UTF-8");
            properties.load(isr);
            String hongmu = properties.getProperty("hongmu");
            if (hongmu != null) {
                if ("1".equals(hongmu)) {
                    LaddJFrame.comboBox2.setSelectedItem("红木经典");
                }
                else if ("2".equals(hongmu)) {
                    LaddJFrame.comboBox2.setSelectedItem("水墨书卷");
                }
                else if ("3".equals(hongmu)) {
                    LaddJFrame.comboBox2.setSelectedItem("丹心墨意");
                }
                else if ("4".equals(hongmu)) {
                    LaddJFrame.comboBox2.setSelectedItem("新版水墨");
                }
            }
        }
        catch (Exception e3) {
            Systeminitial();
            try {
                if (isr != null) {
                    isr.close();
                }
            }
            catch (IOException e4) {
                e4.printStackTrace();
            }
            try {
                if (fis != null) {
                    fis.close();
                }
            }
            catch (IOException e4) {
                e4.printStackTrace();
            }
        }
        finally {
            try {
                if (isr != null) {
                    isr.close();
                }
            }
            catch (IOException e5) {
                e5.printStackTrace();
            }
            try {
                if (fis != null) {
                    fis.close();
                }
            }
            catch (IOException e5) {
                e5.printStackTrace();
            }
        }
    }
    
    public static void readSysteminit() {
        System.out.println("加载登陆器配置配置");
        Properties properties = new Properties();
        FileInputStream fis = null;
        InputStreamReader isr = null;
        try {
            fis = new FileInputStream("resource/other/lander.txt");
            isr = new InputStreamReader(fis, "UTF-8");
            properties.load(isr);
            String combBox1 = properties.getProperty("Label");
            String music = properties.getProperty("music");
            String ctbox = properties.getProperty("ctbox");
            if (ctbox != null) {
                if ("1".equals(ctbox)) {
                    LaddJFrame.comboBox.setSelectedItem("红木经典");
                }
                else if ("2".equals(ctbox)) {
                    LaddJFrame.comboBox.setSelectedItem("雨过天晴");
                }
            }
            if (music != null) {
                if ("1".equals(music)) {
                    LaddJFrame.comboBoxMu.setSelectedItem("开启");
                }
                else if ("2".equals(music)) {
                    LaddJFrame.comboBoxMu.setSelectedItem("关闭");
                }
            }
            if (combBox1 != null && "1".equals(combBox1)) {
                LaddJFrame.comboBox1.setSelectedItem("独立窗口版");
            }
        }
        catch (Exception e3) {
            Systeminitial();
            try {
                if (isr != null) {
                    isr.close();
                }
            }
            catch (IOException e4) {
                e4.printStackTrace();
            }
            try {
                if (fis != null) {
                    fis.close();
                }
            }
            catch (IOException e4) {
                e4.printStackTrace();
            }
        }
        finally {
            try {
                if (isr != null) {
                    isr.close();
                }
            }
            catch (IOException e5) {
                e5.printStackTrace();
            }
            try {
                if (fis != null) {
                    fis.close();
                }
            }
            catch (IOException e5) {
                e5.printStackTrace();
            }
        }
    }
    
    public static void writeTxtlog() {
        FileOutputStream outputStream = null;
        Properties properties = new Properties();
        System.out.println("改写配置");
        try {
            outputStream = new FileOutputStream("resource/other/Config.txt");
            if ("红木经典".equals(LaddJFrame.comboBox2.getSelectedItem())) {
                properties.setProperty("hongmu", "1");
            }
            else if ("水墨书卷".equals(LaddJFrame.comboBox2.getSelectedItem())) {
                properties.setProperty("hongmu", "2");
            }
            else if ("丹心墨意".equals(LaddJFrame.comboBox2.getSelectedItem())) {
                properties.setProperty("hongmu", "3");
            }
            else if ("新版水墨".equals(LaddJFrame.comboBox2.getSelectedItem())) {
                properties.setProperty("hongmu", "4");
            }
            properties.store(outputStream, null);
        }
        catch (IOException e) {
            e.printStackTrace();
            if (outputStream != null) {
                try {
                    outputStream.close();
                }
                catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                }
                catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
        }
    }
    
    public static void writeTxt() {
        FileOutputStream outputStream = null;
        Properties properties = new Properties();
        try {
            outputStream = new FileOutputStream("resource/other/lander.txt");
            if ("雨过天晴".equals(LaddJFrame.comboBox.getSelectedItem())) {
                properties.setProperty("ctbox", "2");
            }
            else if ("红木经典".equals(LaddJFrame.comboBox.getSelectedItem())) {
                properties.setProperty("ctbox", "1");
            }
            if ("开启".equals(LaddJFrame.comboBoxMu.getSelectedItem())) {
                properties.setProperty("music", "1");
            }
            else if ("关闭".equals(LaddJFrame.comboBoxMu.getSelectedItem())) {
                properties.setProperty("music", "2");
            }
            if ("独立窗口版".equals(LaddJFrame.comboBox1.getSelectedItem())) {
                properties.setProperty("Label", "1");
            }
            properties.store(outputStream, null);
        }
        catch (IOException e) {
            e.printStackTrace();
            if (outputStream != null) {
                try {
                    outputStream.close();
                }
                catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                }
                catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
        }
    }
    
    public static JComboBox<String> getComboBox2() {
        return LaddJFrame.comboBox2;
    }
    
    public static void setComboBox2(JComboBox<String> comboBox2) {
        LaddJFrame.comboBox2 = comboBox2;
    }
    
    public static JComboBox<String> getComboBox1() {
        return LaddJFrame.comboBox1;
    }
    
    public static void setComboBox1(JComboBox<String> comboBox1) {
        LaddJFrame.comboBox1 = comboBox1;
    }
    
    public static JComboBox<String> getComboBox() {
        return LaddJFrame.comboBox;
    }
    
    public static void setComboBox(JComboBox<String> comboBox) {
        LaddJFrame.comboBox = comboBox;
    }
    
    public static JComboBox<String> getComboBoxMu() {
        return LaddJFrame.comboBoxMu;
    }
    
    public static void setComboBoxMu(JComboBox<String> comboBoxMu) {
        LaddJFrame.comboBoxMu = comboBoxMu;
    }
    
    static {
        LaddJFrame.frame = new JFrame("JInternalFrame");
    }
    
    public static class Singleton1 extends JFrame
    {
        private Singleton1() {
        }
        
        public static Singleton1 getInstance() {
            return SingletonClass1.instance;
        }
        
        public static class SingletonClass1
        {
            private static final Singleton1 instance;
            
            static {
                instance = new Singleton1();
            }
        }
    }
}
