package org.come.log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.util.Properties;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Dimension;
import com.tool.tcpimg.UIUtils;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class LaddJPanel extends JPanel
{
    public static JComboBox<String> comboBox2;
    public static JComboBox<String> comboBox1;
    public static JComboBox<String> comboBox;
    public static JComboBox<String> comboBoxMu;
    private ImageIcon iconBack;
    
    public LaddJPanel() {
        this.setLayout(null);
        this.setOpaque(false);
        this.setBackground(UIUtils.Color_BACK);
        this.setPreferredSize(new Dimension(347, 296));
        Font font = new Font("宋体", 0, 13);
        JLabel lblNewLabel = new JLabel("选择登录界面风格：");
        lblNewLabel.setFont(font);
        lblNewLabel.setBounds(20, 14, 150, 15);
        this.add(lblNewLabel);
        JLabel lblNewLabel2 = new JLabel("选择登录方式：");
        lblNewLabel2.setFont(font);
        lblNewLabel2.setBounds(20, 44, 150, 15);
        this.add(lblNewLabel2);
        JLabel lblNewLabel3 = new JLabel("选择界面UI风格：");
        lblNewLabel3.setFont(font);
        lblNewLabel3.setBounds(20, 74, 150, 15);
        this.add(lblNewLabel3);
        JLabel lblNewLabel4 = new JLabel("欢迎页音乐设置：");
        lblNewLabel4.setFont(font);
        lblNewLabel4.setBounds(20, 104, 150, 15);
        this.add(lblNewLabel4);
        (LaddJPanel.comboBox1 = new JComboBox<>()).setFont(font);
        LaddJPanel.comboBox1.setBounds(140, 41, 95, 21);
        LaddJPanel.comboBox1.addItem("独立窗口版");
        LaddJPanel.comboBox1.addItem("多标签版");
        LaddJPanel.comboBox1.setEditable(true);
        this.add(LaddJPanel.comboBox1);
        (LaddJPanel.comboBox2 = new JComboBox<>()).setBounds(140, 71, 95, 21);
        LaddJPanel.comboBox2.setFont(font);
        LaddJPanel.comboBox2.addItem("水墨书卷");
        LaddJPanel.comboBox2.addItem("红木经典");
        LaddJPanel.comboBox2.addItem("丹心墨意");
        LaddJPanel.comboBox2.setEditable(true);
        this.add(LaddJPanel.comboBox2);
        (LaddJPanel.comboBox = new JComboBox<>()).setBounds(140, 11, 95, 21);
        LaddJPanel.comboBox.setFont(font);
        LaddJPanel.comboBox.addItem("暂未完善");
        LaddJPanel.comboBox.addItem("红木经典");
        LaddJPanel.comboBox.setEditable(true);
        this.add(LaddJPanel.comboBox);
        (LaddJPanel.comboBoxMu = new JComboBox<>()).setBounds(140, 101, 95, 21);
        LaddJPanel.comboBoxMu.setFont(font);
        LaddJPanel.comboBoxMu.addItem("开启");
        LaddJPanel.comboBoxMu.addItem("关闭");
        LaddJPanel.comboBoxMu.setEditable(true);
        this.add(LaddJPanel.comboBoxMu);
        JLabel lblResult = new JLabel("");
        lblResult.setFont(font);
        lblResult.setBounds(0, 150, 146, 15);
        this.add(lblResult);
        JLabel lblResult2 = new JLabel("");
        lblResult2.setFont(font);
        lblResult2.setBounds(0, 170, 146, 15);
        this.add(lblResult2);
        JLabel lblResult3 = new JLabel("");
        lblResult3.setFont(font);
        lblResult3.setBounds(0, 190, 146, 15);
        this.add(lblResult3);
        JButton btnNewButton = new JButton("确定");
        btnNewButton.setBounds(62, 220, 75, 23);
        btnNewButton.setFont(font);
        this.add(btnNewButton);
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                lblResult.setText("您选择的是：" + LaddJPanel.comboBox.getSelectedItem());
                lblResult2.setText("您选择的是：" + LaddJPanel.comboBox1.getSelectedItem());
                lblResult3.setText("您选择的是：" + LaddJPanel.comboBox2.getSelectedItem());
                LaddJPanel.writeTxt();
                LaddJPanel.writeTxtlog();
                LaddJPanel.this.setVisible(false);
            }
        });
        JButton btnNewButtonx = new JButton("取消");
        btnNewButtonx.setBounds(200, 220, 75, 23);
        btnNewButtonx.setFont(font);
        this.add(btnNewButtonx);
        btnNewButtonx.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                LaddJPanel.this.setVisible(false);
            }
        });
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.iconBack == null) {
            this.iconBack = new ImageIcon("inkImg/background/S9.png");
        }
        g.drawImage(this.iconBack.getImage(), 0, 0, 682, 479, this);
    }
    
    public static void Systeminitial() {
        LaddJPanel.comboBox2.addItem("红木经典");
        LaddJPanel.comboBox.addItem("红木经典");
        LaddJPanel.comboBox1.addItem("独立窗口版");
        LaddJPanel.comboBoxMu.addItem("开启");
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
                    LaddJPanel.comboBox2.setSelectedItem("红木经典");
                }
                else if ("2".equals(hongmu)) {
                    LaddJPanel.comboBox2.setSelectedItem("水墨书卷");
                }
                else if ("3".equals(hongmu)) {
                    LaddJPanel.comboBox2.setSelectedItem("丹心墨意");
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
        Properties properties = new Properties();
        FileInputStream fis = null;
        InputStreamReader isr = null;
        try {
            fis = new FileInputStream("resource/other/lander.txt");
            isr = new InputStreamReader(fis, "UTF-8");
            properties.load(isr);
            String combBox1 = properties.getProperty("Label");
            String music = properties.getProperty("music");
            if (music != null) {
                if ("1".equals(music)) {
                    LaddJPanel.comboBoxMu.setSelectedItem("开启");
                }
                else if ("2".equals(music)) {
                    LaddJPanel.comboBoxMu.setSelectedItem("关闭");
                }
            }
            if (combBox1 != null) {
                if ("1".equals(combBox1)) {
                    LaddJPanel.comboBox1.setSelectedItem("独立窗口版");
                }
                else if ("2".equals(combBox1)) {
                    LaddJPanel.comboBox1.setSelectedItem("多标签版");
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
    
    public static void writeTxtlog() {
        FileOutputStream outputStream = null;
        Properties properties = new Properties();
        try {
            outputStream = new FileOutputStream("resource/other/Config.txt");
            if ("红木经典".equals(LaddJPanel.comboBox2.getSelectedItem())) {
                properties.setProperty("hongmu", "1");
            }
            else if ("水墨书卷".equals(LaddJPanel.comboBox2.getSelectedItem())) {
                properties.setProperty("hongmu", "2");
            }
            else if ("丹心墨意".equals(LaddJPanel.comboBox2.getSelectedItem())) {
                properties.setProperty("hongmu", "3");
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
            if ("开启".equals(LaddJPanel.comboBoxMu.getSelectedItem())) {
                properties.setProperty("music", "1");
            }
            else if ("关闭".equals(LaddJPanel.comboBoxMu.getSelectedItem())) {
                properties.setProperty("music", "2");
            }
            if ("多标签版".equals(LaddJPanel.comboBox1.getSelectedItem())) {
                properties.setProperty("Label", "2");
            }
            else if ("独立窗口版".equals(LaddJPanel.comboBox1.getSelectedItem())) {
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
}
