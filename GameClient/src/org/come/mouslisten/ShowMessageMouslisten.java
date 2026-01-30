package org.come.mouslisten;

import org.come.Frame.ZhuFrame;
import java.awt.event.MouseEvent;
import org.come.entity.RoleSummoning;
import org.come.entity.Goodstable;
import java.awt.event.MouseListener;

public class ShowMessageMouslisten implements MouseListener
{
    private int type;
    private Goodstable goodstable;
    private RoleSummoning roleSummoning;
    
    public ShowMessageMouslisten(int type, int index) {
        this.type = type;
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
        if (this.type == 0 && this.goodstable != null) {
            ZhuFrame.getZhuJpanel().creatgoodtext(this.goodstable);
        }
        else if (this.type == 1 && this.roleSummoning != null) {
            ZhuFrame.getZhuJpanel().creatpettext(this.roleSummoning);
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        if (this.type == 0) {
            ZhuFrame.getZhuJpanel().cleargoodtext();
        }
        else if (this.type == 1) {
            ZhuFrame.getZhuJpanel().pettext();
        }
    }
    
    public Goodstable getGoodstable() {
        return this.goodstable;
    }
    
    public void setGoodstable(Goodstable goodstable) {
        this.goodstable = goodstable;
    }
    
    public RoleSummoning getRoleSummoning() {
        return this.roleSummoning;
    }
    
    public void setRoleSummoning(RoleSummoning roleSummoning) {
        this.roleSummoning = roleSummoning;
    }
}
