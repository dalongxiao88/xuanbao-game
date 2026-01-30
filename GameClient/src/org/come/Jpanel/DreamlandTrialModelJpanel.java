package org.come.Jpanel;

import com.tool.tcpimg.UIUtils;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DreamlandTrialModelJpanel extends JPanel
{
    private JLabel labImg;
    private JLabel labNum;
    private int type;
    
    public DreamlandTrialModelJpanel() {
        this.setPreferredSize(new Dimension(227, 47));
        this.setOpaque(false);
        this.setLayout(null);
        this.getLabNum();
        this.getLabImg();
    }
    
    public void changeShow(int type) {
        this.type = type;
        if (type == 0) {
            this.labImg.setForeground(Color.green);
            this.labImg.setText("已完成");
            this.labNum.setText("1");
        }
        else if (type == 1) {
            this.labImg.setForeground(Color.red);
            this.labImg.setText("未完成");
            this.labNum.setText("0");
        }
        else if (type == 2) {
            this.labImg.setForeground(Color.gray);
            this.labImg.setText("未解锁");
            this.labNum.setText("0");
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
    
    public JLabel getLabImg() {
        if (this.labImg == null) {
            (this.labImg = new JLabel()).setBounds(6, 30, 60, 14);
            this.labImg.setFont(UIUtils.TEXT_FONT);
            this.add(this.labImg);
        }
        return this.labImg;
    }
    
    public void setLabImg(JLabel labImg) {
        this.labImg = labImg;
    }
    
    public JLabel getLabNum() {
        if (this.labNum == null) {
            (this.labNum = new JLabel("1", 0)).setForeground(Color.white);
            this.labNum.setFont(UIUtils.TEXT_FONT1);
            this.labNum.setBounds(198, 30, 29, 17);
            this.add(this.labNum);
        }
        return this.labNum;
    }
    
    public void setLabNum(JLabel labNum) {
        this.labNum = labNum;
    }
    
    public int getType() {
        return this.type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
}
