package org.come.Jpanel;

import java.awt.Graphics;
import com.tool.tcpimg.UIUtils;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import com.tool.btn.LiangHaoPaiBtn;
import javax.swing.JLabel;
import java.util.Map;
import javax.swing.JPanel;

public class GetLiangHaoThreeJpanel extends JPanel
{
    private static final long serialVersionUID = 1L;
    private Map<String, String> xjDate;
    private JLabel title;
    private JLabel xjDateStr;
    private LiangHaoPaiBtn xjBtn;
    private JLabel xjBtnLabel;
    ImageIcon bg1;
    
    public GetLiangHaoThreeJpanel() {
        this.setPreferredSize(new Dimension(697, 538));
        this.setLayout(null);
        this.setOpaque(false);
        this.bg1 = new ImageIcon("inkImg/background1/132.png");
        (this.title = new JLabel()).setBounds(110, 80, 120, 15);
        this.title.setForeground(Color.black);
        this.title.setFont(new Font("宋体", 0, 16));
        this.title.setText("投放时间为：");
        this.add(this.title);
        (this.xjDateStr = new JLabel()).setBounds(230, 80, 400, 15);
        this.xjDateStr.setForeground(Color.RED);
        this.xjDateStr.setFont(new Font("宋体", 0, 16));
        this.xjDateStr.setText("");
        this.add(this.xjDateStr);
        (this.xjBtn = new LiangHaoPaiBtn("inkImg/button1/187.png", 1, UIUtils.COLOR_WHITE, UIUtils.TEXT_HY16, "购买", Integer.valueOf(3), null)).setBounds(325, 440, 67, 35);
        this.add(this.xjBtn);
        (this.xjBtnLabel = new JLabel()).setBounds(48, 70, 625, 426);
        this.xjBtnLabel.setIcon(this.bg1);
        this.add(this.xjBtnLabel);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.xjDate != null) {
            this.xjDateStr.setText((String)this.xjDate.get("start") + " 00:00:00 ~ " + (String)this.xjDate.get("end") + " 00:00:00");
        }
    }
    
    public void changeFrom(int caozuo) {
    }
    
    public Map<String, String> getXjDate() {
        return this.xjDate;
    }
    
    public void setXjDate(Map<String, String> xjDate) {
        this.xjDate = xjDate;
    }
}
