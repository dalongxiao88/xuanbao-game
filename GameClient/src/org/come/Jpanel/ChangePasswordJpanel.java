package org.come.Jpanel;

import java.awt.Graphics;
import java.awt.Font;
import javax.swing.BorderFactory;
import java.awt.Color;
import com.tool.btn.FormsOnOffBtn;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import com.tool.btn.RoleOperationPanelBtn;
import javax.swing.JPasswordField;
import javax.swing.JPanel;

public class ChangePasswordJpanel extends JPanel
{
    private JPasswordField Oldpassword;
    private JPasswordField Newpassword;
    private RoleOperationPanelBtn btnChangepwd;
    private ImageIcon icon;
    
    public ChangePasswordJpanel() throws Exception {
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(358, 329));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 21);
            offBtn.setBounds(321, 10, 25, 25);
            this.add(offBtn);
            (this.Oldpassword = new JPasswordField()).setBounds(132, 62, 175, 16);
            this.Oldpassword.setForeground(Color.white);
            this.Oldpassword.setBackground(UIUtils.Color_BACK);
            this.Oldpassword.setCaretColor(Color.white);
            this.Oldpassword.setBorder(BorderFactory.createEmptyBorder());
            this.Oldpassword.setFont(new Font("微软雅黑", 1, 16));
            this.add(this.Oldpassword);
            (this.Newpassword = new JPasswordField()).setBounds(132, 96, 175, 16);
            this.Newpassword.setForeground(Color.white);
            this.Newpassword.setBackground(UIUtils.Color_BACK);
            this.Newpassword.setCaretColor(Color.white);
            this.Newpassword.setBorder(BorderFactory.createEmptyBorder());
            this.Newpassword.setFont(new Font("微软雅黑", 1, 16));
            this.add(this.Newpassword);
            (this.btnChangepwd = new RoleOperationPanelBtn("inkImg/button1/B22.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, "修改密码")).setBounds(143, 266, 99, 24);
            this.add(this.btnChangepwd);
        }
        else {
            this.setPreferredSize(new Dimension(300, 171));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("img/xy2uiimg/8_jpg.button.btn_close.jpg", 1, 21);
            offBtn.setBounds(280, 0, 23, 23);
            this.add(offBtn);
            (this.Oldpassword = new JPasswordField()).setBounds(105, 51, 160, 15);
            this.Oldpassword.setForeground(Color.white);
            this.Oldpassword.setBackground(UIUtils.Color_BACK);
            this.Oldpassword.setCaretColor(Color.white);
            this.Oldpassword.setBorder(BorderFactory.createEmptyBorder());
            this.Oldpassword.setFont(new Font("微软雅黑", 1, 16));
            this.add(this.Oldpassword);
            (this.Newpassword = new JPasswordField()).setBounds(105, 86, 160, 15);
            this.Newpassword.setForeground(Color.white);
            this.Newpassword.setBackground(UIUtils.Color_BACK);
            this.Newpassword.setCaretColor(Color.white);
            this.Newpassword.setBorder(BorderFactory.createEmptyBorder());
            this.Newpassword.setFont(new Font("微软雅黑", 1, 16));
            this.add(this.Newpassword);
            (this.btnChangepwd = new RoleOperationPanelBtn("inkImg/hongmu/1_png.button.btn_xy.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY16, "修改密码")).setBounds(123, 115, 85, 24);
            this.add(this.btnChangepwd);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            this.icon = new ImageIcon("inkImg/background1/B258.png");
            g.drawImage(this.icon.getImage(), 0, 0, 358, 329, this);
        }
        else {
            this.icon = new ImageIcon("img/xy2uiimg/37_png.xy2uiimg.editlogbg.png");
            g.drawImage(this.icon.getImage(), 0, 0, 300, 171, this);
        }
    }
    
    public JPasswordField getOldpassword() {
        return this.Oldpassword;
    }
    
    public void setOldpassword(JPasswordField oldpassword) {
        this.Oldpassword = oldpassword;
    }
    
    public JPasswordField getNewpassword() {
        return this.Newpassword;
    }
    
    public void setNewpassword(JPasswordField newpassword) {
        this.Newpassword = newpassword;
    }
    
    public RoleOperationPanelBtn getBtnChangepwd() {
        return this.btnChangepwd;
    }
    
    public void setBtnChangepwd(RoleOperationPanelBtn btnChangepwd) {
        this.btnChangepwd = btnChangepwd;
    }
}
