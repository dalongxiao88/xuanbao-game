package com.tool.btn;

import java.awt.event.MouseEvent;
import org.come.Jpanel.AntiPluginJpanel;

public class AntiPluginBtn extends MoBanBtn
{
    private static final long serialVersionUID = 1L;
    private String selt;
    private AntiPluginJpanel jpanel;
    
    public AntiPluginBtn(String iconpath, int type, String selt, AntiPluginJpanel jpanel) {
        super(iconpath, type);
        this.jpanel = jpanel;
        this.selt = selt;
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        String value = this.jpanel.getValue();
        if (value != null && !value.equals("")) {
            if (this.selt.equals(value)) {
                this.jpanel.correct();
            }
            else {
                this.jpanel.error();
            }
        }
    }
}
