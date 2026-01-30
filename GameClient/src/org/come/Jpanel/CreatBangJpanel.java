package org.come.Jpanel;

import java.awt.Graphics;
import javax.swing.BorderFactory;
import com.tool.btn.FormsOnOffBtn;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import java.awt.Color;
import com.tool.btn.CreatBangBtn;
import javax.swing.JLabel;
import org.come.Frame.CreatBangJframe;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JPanel;

public class CreatBangJpanel extends JPanel
{
    private JTextArea setword1;
    private JTextField setword2;
    private CreatBangJframe creatBangjframe;
    private JLabel labtrade;
    private CreatBangBtn btnbang;
    private Color fontcolor;
    ImageIcon icon;
    
    public CreatBangJpanel(CreatBangJframe creatBangjframe) throws Exception {
        this.creatBangjframe = creatBangjframe;
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(351, 316));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 25);
            offBtn.setBounds(326, 0, 25, 25);
            this.add(offBtn);
            (this.setword1 = new JTextArea()).setLineWrap(true);
            this.setword1.setBounds(53, 78, 270, 88);
            this.setword1.setForeground(Color.white);
            this.setword1.setCaretColor(Color.white);
            this.setword1.setBackground(new Color(0, 0, 0, 0));
            this.setword1.setBorder(BorderFactory.createEmptyBorder());
            this.setword1.setFont(UIUtils.TEXT_TIP);
            this.add(this.setword1);
            (this.setword2 = new JTextField()).setBounds(130, 31, 200, 16);
            this.setword2.setForeground(Color.white);
            this.setword2.setCaretColor(Color.white);
            this.setword2.setBackground(new Color(0, 0, 0, 0));
            this.setword2.setBorder(BorderFactory.createEmptyBorder());
            this.setword2.setFont(UIUtils.TEXT_TIP);
            this.add(this.setword2);
            (this.btnbang = new CreatBangBtn("inkImg/button/B60.png", 1, "创建帮派", this, null)).setBounds(140, 180, 92, 26);
            this.add(this.btnbang);
        }
        else {
            this.setPreferredSize(new Dimension(337, 200));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/hongmu/s74.png", 1, 25);
            offBtn.setBounds(312, 0, 25, 25);
            this.add(offBtn);
            (this.setword1 = new JTextArea()).setLineWrap(true);
            this.setword1.setBounds(38, 90, 260, 60);
            this.setword1.setForeground(Color.white);
            this.setword1.setCaretColor(Color.white);
            this.setword1.setBackground(new Color(0, 0, 0, 0));
            this.setword1.setBorder(BorderFactory.createEmptyBorder());
            this.setword1.setFont(UIUtils.TEXT_HY88);
            this.add(this.setword1);
            (this.setword2 = new JTextField()).setBounds(110, 43, 200, 19);
            this.setword2.setForeground(Color.white);
            this.setword2.setCaretColor(Color.white);
            this.setword2.setBackground(new Color(0, 0, 0, 0));
            this.setword2.setBorder(BorderFactory.createEmptyBorder());
            this.setword2.setFont(UIUtils.TEXT_HY88);
            this.add(this.setword2);
            (this.btnbang = new CreatBangBtn("inkImg/hongmu/1_png.button.btn_xy.png", 1, "创建帮派", this)).setBounds(140, 160, 80, 26);
            this.add(this.btnbang);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            this.icon = new ImageIcon("inkImg/background/S60.png");
            g.drawImage(this.icon.getImage(), 0, 0, 351, 316, this);
        }
        else {
            if (this.icon == null) {
                this.icon = new ImageIcon("img/xy2uiimg/100_png.xy2uiimg.groupcreate.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 337, 200, this);
        }
    }
    
    public CreatBangJframe getcreatBangjframe() {
        return this.creatBangjframe;
    }
    
    public void creatBangjframe(CreatBangJframe creatBangjframe) {
        this.creatBangjframe = creatBangjframe;
    }
    
    public JLabel getLabtrade() {
        return this.labtrade;
    }
    
    public void setLabtrade(JLabel labtrade) {
        this.labtrade = labtrade;
    }
    
    public Color getFontcolor() {
        return this.fontcolor;
    }
    
    public void setFontcolor(Color fontcolor) {
        this.fontcolor = fontcolor;
    }
    
    public JTextArea getSetword1() {
        return this.setword1;
    }
    
    public void setSetword1(JTextArea setword1) {
        this.setword1 = setword1;
    }
    
    public JTextField getSetword2() {
        return this.setword2;
    }
    
    public void setSetword2(JTextField setword2) {
        this.setword2 = setword2;
    }
}
