package com.tool.btn;

import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.until.FormsManagement;
import org.come.Frame.YaZhuJframe;
import java.awt.event.MouseEvent;
import org.come.Jpanel.JieGuaJpanel;
import javax.swing.ImageIcon;

public class GuaBtn extends MoBanBtn
{
    private static final ImageIcon[][] backIcons;
    private int index;
    private JieGuaJpanel jieGuaJpanel;
    
    public GuaBtn(String iconpath, int type, int index, JieGuaJpanel jieGuaJpanel) {
        super(iconpath, type);
        this.index = index;
        this.jieGuaJpanel = jieGuaJpanel;
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        YaZhuJframe.getYaZhuJframe().getYaZhuJpanel().initType(this.index);
        FormsManagement.showForm(713);
        SendMessageUntil.toServer(Agreement.getAgreement().JieGuaAgreement("3"));
    }
    
    public void update(int yangIndex, int yinIndex, boolean is) {
        if (is) {
            if (this.index == yangIndex && yangIndex == yinIndex) {
                this.setIcon(GuaBtn.backIcons[3][this.index]);
            }
            else if (this.index == yinIndex) {
                this.setIcon(GuaBtn.backIcons[2][this.index]);
            }
            else if (this.index == yangIndex) {
                this.setIcon(GuaBtn.backIcons[1][this.index]);
            }
            else {
                this.setIcon(this.icons[0]);
            }
        }
        else {
            this.setIcon(this.icons[0]);
        }
    }
    
    public void update() {
        this.setIcon(GuaBtn.backIcons[0][this.index]);
    }
    
    public void changeState(boolean is) {
        this.btntypechange(is ? 1 : -1);
        this.btnchange(0);
    }
    
    static {
        backIcons = new ImageIcon[4][8];
        for (int i = 0; i < GuaBtn.backIcons[0].length; ++i) {
            GuaBtn.backIcons[0][i] = new ImageIcon("img/jiegua/xp" + (i + 1) + ".png");
        }
        for (int i = 0; i < GuaBtn.backIcons[1].length; ++i) {
            GuaBtn.backIcons[1][i] = new ImageIcon("img/jiegua/hs" + (i + 1) + ".png");
        }
        for (int i = 0; i < GuaBtn.backIcons[2].length; ++i) {
            GuaBtn.backIcons[2][i] = new ImageIcon("img/jiegua/ls" + (i + 1) + ".png");
        }
        for (int i = 0; i < GuaBtn.backIcons[3].length; ++i) {
            if (i == 2 || i == 6) {
                GuaBtn.backIcons[3][i] = new ImageIcon("img/jiegua/hs" + ((i == 2) ? 10 : 20) + ".png");
            }
        }
    }
}
