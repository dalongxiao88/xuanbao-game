package com.tool.btn;

import org.come.until.FormsManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class RuneOPerBtn1 extends MoBanBtn {

    private int caozuo;

    public RuneOPerBtn1(String iconpath, int type, Color[] colors, Font font, String text, int caozuo) {
        super(iconpath, type, colors);
        this.caozuo = caozuo;
        setText(text);
        setFont(font);
        setVerticalTextPosition(SwingConstants.CENTER);
        setHorizontalTextPosition(SwingConstants.CENTER);
    }

    @Override
    public void chooseyes() {
        // TODO Auto-generated method stub

    }

    @Override
    public void chooseno() {
        // TODO Auto-generated method stub

    }

    @Override
    public void nochoose(MouseEvent e) {

        if (caozuo == 3) {// 炼妖
            if (!FormsManagement.getframe(12121).isVisible()) {
                FormsManagement.showForm(12121);
            } else {
                FormsManagement.HideForm(12121);
            }
        } else if (caozuo == 4) {// 提炼
            if (!FormsManagement.getframe(12122).isVisible()) {
                FormsManagement.showForm(12122);
            } else {
                FormsManagement.HideForm(12122);
            }
        } else if (caozuo == 5) {// 灵返
//            if (!FormsManagement.getframe(12123).isVisible()) {
//                FormsManagement.showForm(12123);
//            } else {
//                FormsManagement.HideForm(12123);
//            }

        }
    }
}


