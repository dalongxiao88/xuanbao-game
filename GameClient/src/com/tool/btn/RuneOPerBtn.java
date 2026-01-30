package com.tool.btn;

import org.come.until.FormsManagement;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
import com.tool.tcpimg.UIUtils;

public class RuneOPerBtn extends MoBanBtn
{
    private int caozuo;
    
    public RuneOPerBtn(String iconpath, int type, int caozuo) {
        super(iconpath, type);
        this.caozuo = caozuo;
        if (caozuo == 1) {
            this.setText("重铸");
        }
        else if (caozuo == 2) {
            this.setText("升级");
        }
        this.setFont(UIUtils.nameFont);
        this.setForeground(Color.orange);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    public RuneOPerBtn(String iconpath, int type, Color[] colors, Font font, String text, int caozuo) {
        super(iconpath, type, colors);
        this.caozuo = caozuo;
        this.setText(text);
        this.setFont(font);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        if (this.caozuo != 1 && this.caozuo != 2) {
            if (this.caozuo == 3) {
                if (!FormsManagement.getframe(72).isVisible()) {
                    FormsManagement.showForm(72);
                }
                else {
                    FormsManagement.HideForm(72);
                }
            }
            else if (this.caozuo == 4) {
                if (!FormsManagement.getframe(72).isVisible()) {
                    FormsManagement.showForm(72);
                }
                else {
                    FormsManagement.HideForm(72);
                }
            }
            else if ("升级规则".equals(this.getText())) {
                if (!FormsManagement.getframe(72).isVisible()) {
                    FormsManagement.showForm(72);
                }
                else {
                    FormsManagement.HideForm(72);
                }
            }
            else if ("重铸规则".equals(this.getText())) {
                if (!FormsManagement.getframe(72).isVisible()) {
                    FormsManagement.showForm(72);
                }
                else {
                    FormsManagement.HideForm(72);
                }
            }
        }
    }
}
