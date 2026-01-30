package com.tool.btn;

import java.awt.event.MouseEvent;
import java.awt.Font;
import org.come.login.RegisterView;

public class CodeBtn extends MoBanBtn
{
    private RegisterView registerView;
    
    public CodeBtn(String iconpath, int type, Font font, String text, RegisterView registerView) {
        super(iconpath, type);
        this.setText(text);
        this.setFont(font);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.registerView = registerView;
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent mouseevent) {
    }
}
