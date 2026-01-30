package com.tool.btn;

import org.come.mouslisten.ChangeMouseSymbolMouslisten;
import java.awt.event.MouseEvent;
import com.tool.tcpimg.UIUtils;

public class MouseStyleBtn extends MoBanBtn
{
    private String FlagMes;
    
    public MouseStyleBtn(String iconpath, int type, String FlagMes) {
        super(iconpath, type);
        this.FlagMes = FlagMes;
    }
    
    public MouseStyleBtn(byte[] iconByte, String fileName, int type, String FlagMes) {
        super(iconByte, fileName, type, UIUtils.COLOR_WHITE2);
        this.FlagMes = FlagMes;
    }
    
    public MouseStyleBtn(int type, String FlagMes) {
        super("", type);
        this.FlagMes = FlagMes;
    }
    
    public MouseStyleBtn(String iconpath, int type, String FlagMes, String text) {
        super(iconpath, type, UIUtils.COLOR_WHITE2);
        this.FlagMes = FlagMes;
        this.setText(text);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.setFont(UIUtils.TEXT_HY88);
    }
    
    public MouseStyleBtn(String iconpath, int type, String FlagMes, String text, String sm) {
        super(iconpath, type, UIUtils.COLOR_BTNPUTONG2);
        this.FlagMes = FlagMes;
        this.setText(text);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.setFont(UIUtils.TEXT_FONT_17);
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        ChangeMouseSymbolMouslisten.dianji(this.FlagMes);
    }
}
