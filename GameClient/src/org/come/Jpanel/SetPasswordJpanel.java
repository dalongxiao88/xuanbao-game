package org.come.Jpanel;

import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.Color;
import com.tool.btn.FormsOnOffBtn;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import com.tool.btn.RoleOperationPanelBtn;
import javax.swing.JTextArea;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

public class SetPasswordJpanel extends JPanel implements MouseListener
{
    private JTextArea passAreaText;
    private RoleOperationPanelBtn btnSetPassword;
    ImageIcon icon;
    
    public SetPasswordJpanel() throws Exception {
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(358, 329));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 32);
            offBtn.setBounds(321, 10, 25, 25);
            this.add(offBtn);
            (this.passAreaText = new JTextArea()).setBounds(132, 78, 175, 16);
            this.passAreaText.setOpaque(false);
            this.passAreaText.setBackground(new Color(0, 0, 0, 0));
            this.passAreaText.setForeground(Color.WHITE);
            this.passAreaText.setCaretColor(Color.white);
            this.add(this.passAreaText);
            (this.btnSetPassword = new RoleOperationPanelBtn("inkImg/button1/B22.png", 1, UIUtils.COLOR_WHITE2, UIUtils.TEXT_HY16, "设置密码")).setBounds(143, 266, 99, 24);
            this.add(this.btnSetPassword);
        }
        else {
            this.setPreferredSize(new Dimension(300, 171));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("img/xy2uiimg/8_jpg.button.btn_close.jpg", 1, 32);
            offBtn.setBounds(277, 0, 23, 23);
            this.add(offBtn);
            (this.passAreaText = new JTextArea()).setBounds(102, 81, 170, 20);
            this.passAreaText.setOpaque(false);
            this.passAreaText.setBackground(new Color(0, 0, 0, 0));
            this.passAreaText.setForeground(Color.WHITE);
            this.passAreaText.setCaretColor(Color.white);
            this.add(this.passAreaText);
            (this.btnSetPassword = new RoleOperationPanelBtn("inkImg/hongmu/1_png.button.btn_xy.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY16, "设置密码")).setBounds(128, 116, 85, 24);
            this.add(this.btnSetPassword);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background1/B257.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 358, 329, this);
        }
        else {
            if (this.icon == null) {
                this.icon = new ImageIcon("img/loginimg/设置密码.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 300, 171, this);
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
    
    public JTextArea getPassAreaText() {
        return this.passAreaText;
    }
    
    public void setPassAreaText(JTextArea passAreaText) {
        this.passAreaText = passAreaText;
    }
    
    public RoleOperationPanelBtn getBtnSetPassword() {
        return this.btnSetPassword;
    }
    
    public void setBtnSetPassword(RoleOperationPanelBtn btnSetPassword) {
        this.btnSetPassword = btnSetPassword;
    }
}
