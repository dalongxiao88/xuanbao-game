package org.come.Jpanel;

import java.awt.Graphics;
import com.tool.tcpimg.UIUtils;
import java.awt.Color;
import java.awt.Font;
import com.tool.btn.FormsOnOffBtn;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import com.tool.btn.RoleCaoZuoBtn;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class NedanJpanel extends JPanel
{
    private JLabel needExperience;
    private JTextArea areaMsg;
    private JLabel labNedanName;
    private JLabel labLevel;
    private JLabel labnumber;
    private JLabel labExp;
    private JLabel labTotalExp;
    private RoleCaoZuoBtn btnSpitOut;
    private RoleCaoZuoBtn btnConversion;
    ImageIcon icon;
    
    public NedanJpanel() throws Exception {
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(268, 323));
            this.setLayout(null);
            this.setOpaque(false);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 47);
            offBtn.setBounds(231, 10, 25, 25);
            this.add(offBtn);
            Font font2 = new Font("微软雅黑", 0, 13);
            (this.areaMsg = new JTextArea()).setBounds(46, 125, 185, 88);
            this.areaMsg.setLineWrap(true);
            this.areaMsg.setForeground(Color.WHITE);
            this.areaMsg.setFont(font2);
            this.areaMsg.setEnabled(false);
            this.areaMsg.setOpaque(false);
            this.add(this.areaMsg);
            (this.labNedanName = new JLabel()).setBounds(119, 51, 116, 16);
            this.labNedanName.setForeground(Color.WHITE);
            this.labNedanName.setFont(font2);
            this.add(this.labNedanName);
            (this.labLevel = new JLabel()).setBounds(119, 74, 116, 16);
            this.labLevel.setForeground(Color.WHITE);
            this.labLevel.setFont(font2);
            this.add(this.labLevel);
            (this.labnumber = new JLabel()).setBounds(119, 97, 116, 16);
            this.labnumber.setForeground(Color.WHITE);
            this.labnumber.setFont(font2);
            this.add(this.labnumber);
            (this.labExp = new JLabel()).setBounds(119, 231, 116, 16);
            this.labExp.setForeground(Color.WHITE);
            this.labExp.setFont(font2);
            this.add(this.labExp);
            (this.labTotalExp = new JLabel()).setBounds(119, 254, 116, 16);
            this.labTotalExp.setForeground(Color.WHITE);
            this.labTotalExp.setFont(font2);
            this.add(this.labTotalExp);
            (this.btnSpitOut = new RoleCaoZuoBtn("inkImg/button1/B22.png", 1, UIUtils.COLOR_WHITE2, UIUtils.TEXT_HY16, "吐出内丹", 0)).setBounds(144, 280, 99, 24);
            this.add(this.btnSpitOut);
            (this.btnConversion = new RoleCaoZuoBtn("inkImg/button1/B22.png", 1, UIUtils.COLOR_WHITE2, UIUtils.TEXT_HY16, "转换经验", 0)).setBounds(24, 280, 99, 24);
            this.add(this.btnConversion);
        }
        else {
            (this.btnSpitOut = new RoleCaoZuoBtn("inkImg/hongmu/1_png.button.btn_xy.png", 1, "吐出内丹", 0)).setBounds(74, 197, 80, 26);
            this.add(this.btnSpitOut);
            (this.btnConversion = new RoleCaoZuoBtn("inkImg/hongmu/1_png.button.btn_xy.png", 1, "转换经验", 0)).setBounds(207, 197, 80, 26);
            this.add(this.btnConversion);
            this.setPreferredSize(new Dimension(375, 235));
            this.setLayout(null);
            this.setOpaque(false);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/hongmu/s74.png", 1, 47);
            offBtn.setBounds(355, 0, 23, 23);
            this.add(offBtn);
            Font font2 = new Font("微软雅黑", 0, 13);
            (this.areaMsg = new JTextArea()).setBounds(24, 60, 124, 124);
            this.areaMsg.setLineWrap(true);
            this.areaMsg.setForeground(Color.WHITE);
            this.areaMsg.setFont(font2);
            this.areaMsg.setEnabled(false);
            this.areaMsg.setOpaque(false);
            this.add(this.areaMsg);
            (this.labNedanName = new JLabel()).setBounds(252, 47, 98, 19);
            this.labNedanName.setForeground(Color.WHITE);
            this.labNedanName.setFont(font2);
            this.add(this.labNedanName);
            (this.labLevel = new JLabel()).setBounds(252, 74, 98, 19);
            this.labLevel.setForeground(Color.WHITE);
            this.labLevel.setFont(font2);
            this.add(this.labLevel);
            (this.labnumber = new JLabel()).setBounds(252, 100, 98, 19);
            this.labnumber.setForeground(Color.WHITE);
            this.labnumber.setFont(font2);
            this.add(this.labnumber);
            (this.labExp = new JLabel()).setBounds(252, 125, 98, 19);
            this.labExp.setForeground(Color.WHITE);
            this.labExp.setFont(font2);
            this.add(this.labExp);
            (this.labTotalExp = new JLabel()).setBounds(252, 151, 98, 19);
            this.labTotalExp.setFont(font2);
            this.labTotalExp.setForeground(Color.WHITE);
            this.add(this.labTotalExp);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background1/B320.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 268, 323, this);
        }
        else {
            if (this.icon == null) {
                this.icon = new ImageIcon("img/xy2uiimg/13_png.xy2uiimg.petbar_bg.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 375, 235, this);
        }
    }
    
    public JTextArea getAreaMsg() {
        return this.areaMsg;
    }
    
    public void setAreaMsg(JTextArea areaMsg) {
        this.areaMsg = areaMsg;
    }
    
    public JLabel getLabNedanName() {
        return this.labNedanName;
    }
    
    public void setLabNedanName(JLabel labNedanName) {
        this.labNedanName = labNedanName;
    }
    
    public JLabel getLabLevel() {
        return this.labLevel;
    }
    
    public void setLabLevel(JLabel labLevel) {
        this.labLevel = labLevel;
    }
    
    public JLabel getLabnumber() {
        return this.labnumber;
    }
    
    public void setLabnumber(JLabel labnumber) {
        this.labnumber = labnumber;
    }
    
    public JLabel getLabExp() {
        return this.labExp;
    }
    
    public void setLabExp(JLabel labExp) {
        this.labExp = labExp;
    }
    
    public JLabel getLabTotalExp() {
        return this.labTotalExp;
    }
    
    public void setLabTotalExp(JLabel labTotalExp) {
        this.labTotalExp = labTotalExp;
    }
    
    public RoleCaoZuoBtn getBtnSpitOut() {
        return this.btnSpitOut;
    }
    
    public void setBtnSpitOut(RoleCaoZuoBtn btnSpitOut) {
        this.btnSpitOut = btnSpitOut;
    }
    
    public RoleCaoZuoBtn getBtnConversion() {
        return this.btnConversion;
    }
    
    public void setBtnConversion(RoleCaoZuoBtn btnConversion) {
        this.btnConversion = btnConversion;
    }
}
