package org.come.Jpanel;

import java.awt.Graphics;
import org.come.login.LoginMouslisten;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import org.come.login.SpriteBtn;
import javax.swing.JPanel;

public class ExitGameTipJpanel extends JPanel
{
    private SpriteBtn btnExit;
    private JLabel labtip;
    private ImageIcon icon;
    
    public ExitGameTipJpanel() {
        this.setPreferredSize(new Dimension(426, 164));
        this.setLayout(null);
        this.setBackground(new Color(0, 0, 0, 0));
        this.setOpaque(false);
        (this.labtip = new JLabel()).setText("服务器已断开连接（请重新登陆）");
        this.labtip.setForeground(Color.WHITE);
        this.labtip.setFont(new Font("宋体", 1, 14));
        this.labtip.setHorizontalTextPosition(0);
        this.labtip.setHorizontalAlignment(0);
        this.labtip.setBackground(new Color(0, 0, 0, 0));
        this.labtip.setBounds(70, 40, 300, 40);
        this.add(this.labtip);
        (this.btnExit = new SpriteBtn("resource/NewUi/按钮_确定2", 110, 90, false)).setBounds(110, 90, 188, 35);
        this.btnExit.addMouseListener(new LoginMouslisten(-1, this.btnExit, null));
        this.add(this.btnExit);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.icon == null) {
            this.icon = new ImageIcon("img/xy2uiimg/消息提示框背景.png");
        }
        g.drawImage(this.icon.getImage(), 0, 0, 426, 164, null);
        this.btnExit.draw(g);
    }
    
    public SpriteBtn getBtnExit() {
        return this.btnExit;
    }
    
    public void setBtnExit(SpriteBtn btnExit) {
        this.btnExit = btnExit;
    }
    
    public JLabel getLabtip() {
        return this.labtip;
    }
    
    public void setLabtip(JLabel labtip) {
        this.labtip = labtip;
    }
}
