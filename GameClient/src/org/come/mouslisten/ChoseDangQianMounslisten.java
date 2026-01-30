package org.come.mouslisten;

import java.awt.event.MouseEvent;
import org.come.Jpanel.ZhuJpanel;
import java.awt.event.MouseListener;

public class ChoseDangQianMounslisten implements MouseListener
{
    private ZhuJpanel zhuJpanel;
    private boolean whether;
    
    public ChoseDangQianMounslisten(ZhuJpanel zhuJpanel) {
        this.whether = true;
        this.zhuJpanel = zhuJpanel;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (this.whether) {
            this.zhuJpanel.getLabnow().setVisible(true);
            this.zhuJpanel.getLabgroups().setVisible(true);
            this.zhuJpanel.getLabbangs().setVisible(true);
            this.zhuJpanel.getLabworld().setVisible(true);
            this.zhuJpanel.getLabnotice().setVisible(true);
        }
        else {
            this.zhuJpanel.getLabnow().setVisible(false);
            this.zhuJpanel.getLabgroups().setVisible(false);
            this.zhuJpanel.getLabbangs().setVisible(false);
            this.zhuJpanel.getLabworld().setVisible(false);
            this.zhuJpanel.getLabnotice().setVisible(false);
        }
        this.whether = !this.whether;
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
    
    public boolean isWhether() {
        return this.whether;
    }
    
    public void setWhether(boolean whether) {
        this.whether = whether;
    }
}
