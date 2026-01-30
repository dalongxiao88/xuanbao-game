package org.come.Jpanel;

import java.awt.event.MouseEvent;
import com.tool.btn.FormsOnOffBtn;
import java.awt.Font;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.GrayFilter;
import java.awt.Image;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import com.tool.btn.RoleOperationPanelBtn;
import javax.swing.JPasswordField;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

public class UnLockJpanel extends JPanel implements MouseListener
{
    private JPasswordField textUnlockpwd;
    private RoleOperationPanelBtn btnSureunlock;
    private RoleOperationPanelBtn btnSureunlock1;
    final ImageIcon imageIcon;
    ImageIcon icon;
    
    public UnLockJpanel() throws Exception {
        this.imageIcon = new ImageIcon("img/123_副本.png");
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(534, 145));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            (this.textUnlockpwd = new JPasswordField() {
                Image image = UnLockJpanel.this.imageIcon.getImage();
                Image grayImage = GrayFilter.createDisabledImage(this.image);
                
                {
                    this.setOpaque(false);
                }
                
                @Override
                public void paint(Graphics g) {
                    g.drawImage(this.grayImage, 0, 0, this);
                    super.paint(g);
                }
            }).setBounds(28, 88, 475, 16);
            this.textUnlockpwd.setForeground(Color.white);
            this.textUnlockpwd.setBackground(UIUtils.Color_BACK);
            this.textUnlockpwd.setBorder(BorderFactory.createEmptyBorder());
            this.textUnlockpwd.setCaretColor(Color.WHITE);
            this.textUnlockpwd.setFont(new Font("微软雅黑", 1, 16));
            this.add(this.textUnlockpwd);
            (this.btnSureunlock = new RoleOperationPanelBtn("inkImg/button1/B21.png", 1, "解  锁", null, null)).setBounds(145, 110, 79, 24);
            this.add(this.btnSureunlock);
            (this.btnSureunlock1 = new RoleOperationPanelBtn("inkImg/button1/B21.png", 1, "取  消", null, null)).setBounds(310, 110, 79, 24);
            this.add(this.btnSureunlock1);
        }
        else {
            this.setPreferredSize(new Dimension(300, 128));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("img/xy2uiimg/8_jpg.button.btn_close.jpg", 1, 33);
            offBtn.setBounds(280, 0, 23, 23);
            this.add(offBtn);
            (this.textUnlockpwd = new JPasswordField() {
                Image image = UnLockJpanel.this.imageIcon.getImage();
                Image grayImage = GrayFilter.createDisabledImage(this.image);
                
                {
                    this.setOpaque(false);
                }
                
                @Override
                public void paint(Graphics g) {
                    g.drawImage(this.grayImage, 0, 0, this);
                    super.paint(g);
                }
            }).setBounds(103, 50, 166, 17);
            this.textUnlockpwd.setForeground(Color.white);
            this.textUnlockpwd.setBackground(UIUtils.Color_BACK);
            this.textUnlockpwd.setBorder(BorderFactory.createEmptyBorder());
            this.textUnlockpwd.setCaretColor(Color.WHITE);
            this.textUnlockpwd.setFont(new Font("微软雅黑", 1, 16));
            this.add(this.textUnlockpwd);
            (this.btnSureunlock = new RoleOperationPanelBtn("inkImg/hongmu/1_png.button.btn_xy.png", 1, "解  锁", null)).setBounds(108, 83, 85, 26);
            this.add(this.btnSureunlock);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            this.icon = new ImageIcon("inkImg/background1/B259.png");
            g.drawImage(this.icon.getImage(), 0, 0, 534, 145, this);
        }
        else {
            this.icon = new ImageIcon("img/xy2uiimg/18_png.xy2uiimg.unlogbg.png");
            g.drawImage(this.icon.getImage(), 0, 0, 300, 128, this);
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    public JPasswordField getTextUnlockpwd() {
        return this.textUnlockpwd;
    }
    
    public void setTextUnlockpwd(JPasswordField textUnlockpwd) {
        this.textUnlockpwd = textUnlockpwd;
    }
    
    public RoleOperationPanelBtn getBtnSureunlock() {
        return this.btnSureunlock;
    }
    
    public void setBtnSureunlock(RoleOperationPanelBtn btnSureunlock) {
        this.btnSureunlock = btnSureunlock;
    }
}
