package org.come.Jpanel;

import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MessageErrorPromptJpanel extends JPanel
{
    private JLabel sure;
    private JLabel cancle;
    
    public MessageErrorPromptJpanel() {
        this.setPreferredSize(new Dimension(400, 200));
        this.setLayout(null);
        (this.sure = new JLabel("确定")).setBounds(90, 150, 60, 25);
        this.sure.setFont(new Font("微软雅黑", 1, 14));
        this.sure.setForeground(Color.yellow);
        (this.cancle = new JLabel("取消")).setBounds(260, 150, 60, 25);
        this.cancle.setFont(new Font("微软雅黑", 1, 14));
        this.cancle.setForeground(Color.yellow);
        this.add(this.sure);
        this.add(this.cancle);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(new ImageIcon("img/loginimg/111.png").getImage(), 0, 0, 400, 200, this);
        g.drawImage(new ImageIcon("img/xy2uiimg/btn_anniu0000(1).png").getImage(), 80, 150, 60, 25, this);
        g.drawImage(new ImageIcon("img/xy2uiimg/btn_anniu0000(1).png").getImage(), 250, 150, 60, 25, this);
    }
}
