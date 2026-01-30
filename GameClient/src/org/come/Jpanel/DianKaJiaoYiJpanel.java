package org.come.Jpanel;

import java.awt.Graphics;
import org.come.bean.LoginResult;
import com.tool.role.RoleData;
import com.tool.btn.FormsOnOffBtn;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import com.tool.btn.DianKaJiaoYiBtn;
import javax.swing.JPanel;

public class DianKaJiaoYiJpanel extends JPanel
{
    private DianKaJiaoYiCardJpanel cardJpanel;
    private DianKaJiaoYiBtn btnGouMai;
    private DianKaJiaoYiBtn btnJiShou;
    private DianKaJiaoYiBtn btnChouJiang;
    private ImageIcon iconBack;
    
    public DianKaJiaoYiJpanel() {
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(480, 520));
            this.cardJpanel = new DianKaJiaoYiCardJpanel();
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 114);
            offBtn.setBounds(443, 10, 25, 25);
            this.add(offBtn);
            (this.btnGouMai = new DianKaJiaoYiBtn("Client/神通天演册/40×40/仙/B400.png", 1, 2, this.cardJpanel, this)).setBounds(65, 27, 75, 35);
            this.add(this.btnGouMai);
            (this.btnJiShou = new DianKaJiaoYiBtn("Client/神通天演册/40×40/仙/B405.png", 1, 3, this.cardJpanel, this)).setBounds(145, 27, 75, 35);
            this.add(this.btnJiShou);
            (this.btnChouJiang = new DianKaJiaoYiBtn("Client/神通天演册/40×40/仙/B403.png", 1, 4, this.cardJpanel, this)).setBounds(225, 27, 75, 35);
            this.add(this.btnChouJiang);
            this.cardJpanel.setBounds(0, 0, 480, 520);
            this.add(this.cardJpanel);
        }
        else {
            this.setPreferredSize(new Dimension(456, 572));
            this.cardJpanel = new DianKaJiaoYiCardJpanel();
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/hongmu/s74.png", 1, 114);
            offBtn.setBounds(431, 0, 25, 25);
            this.add(offBtn);
            (this.btnGouMai = new DianKaJiaoYiBtn("Client/神通天演册/40×40/仙/B113.png", 1, 2, this.cardJpanel, this)).setBounds(65, 25, 80, 40);
            this.add(this.btnGouMai);
            LoginResult loginResult = RoleData.getRoleData().getLoginResult();
            (this.btnJiShou = new DianKaJiaoYiBtn("Client/神通天演册/40×40/仙/B114.png", 1, 3, this.cardJpanel, this)).setBounds(150, 25, 80, 40);
            this.add(this.btnJiShou);
            (this.btnChouJiang = new DianKaJiaoYiBtn("Client/神通天演册/40×40/仙/B116.png", 1, 4, this.cardJpanel, this)).setBounds(235, 25, 80, 40);
            this.add(this.btnChouJiang);
            this.cardJpanel.setBounds(0, 0, 480, 520);
            this.add(this.cardJpanel);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        if (MyIsif.getStyle().equals("水墨")) {
            super.paintComponent(g);
            if (this.iconBack == null) {
                this.iconBack = new ImageIcon("Client/神通天演册/40×40/仙/dkjy-bg.png");
            }
            g.drawImage(this.iconBack.getImage(), 0, 0, 480, 520, this);
        }
        else {
            super.paintComponent(g);
            if (this.iconBack == null) {
                this.iconBack = new ImageIcon("Client/神通天演册/40×40/仙/仙玉寄售1.png");
            }
            g.drawImage(this.iconBack.getImage(), 0, 0, 456, 527, this);
        }
    }
    
    public DianKaJiaoYiCardJpanel getCardJpanel() {
        return this.cardJpanel;
    }
    
    public void setCardJpanel(DianKaJiaoYiCardJpanel cardJpanel) {
        this.cardJpanel = cardJpanel;
    }
    
    public DianKaJiaoYiBtn getBtnGouMai() {
        return this.btnGouMai;
    }
    
    public void setBtnGouMai(DianKaJiaoYiBtn btnGouMai) {
        this.btnGouMai = btnGouMai;
    }
    
    public DianKaJiaoYiBtn getBtnJiShou() {
        return this.btnJiShou;
    }
    
    public void setBtnJiShou(DianKaJiaoYiBtn btnJiShou) {
        this.btnJiShou = btnJiShou;
    }
    
    public DianKaJiaoYiBtn getBtnChouJiang() {
        return this.btnChouJiang;
    }
    
    public void setBtnChouJiang(DianKaJiaoYiBtn btnChouJiang) {
        this.btnChouJiang = btnChouJiang;
    }
    
    public ImageIcon getIconBack() {
        return this.iconBack;
    }
    
    public void setIconBack(ImageIcon iconBack) {
        this.iconBack = iconBack;
    }
}
