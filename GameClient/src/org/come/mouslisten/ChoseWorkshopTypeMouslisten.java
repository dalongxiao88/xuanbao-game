package org.come.mouslisten;

import java.awt.event.MouseEvent;
import org.come.Jpanel.WorkshopRefiningCardJpanel;
import java.awt.event.MouseListener;

public class ChoseWorkshopTypeMouslisten implements MouseListener
{
    private WorkshopRefiningCardJpanel cardJpanel;
    private int type;
    
    public ChoseWorkshopTypeMouslisten(int type, WorkshopRefiningCardJpanel cardJpanel) {
        this.type = type;
        this.cardJpanel = cardJpanel;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if (this.type != 1 && this.type != 2) {
            if (this.type == 3) {
                this.cardJpanel.getCar().show(this.cardJpanel, "l3");
            }
            else if (this.type == 4) {
                this.cardJpanel.getCar().show(this.cardJpanel, "l4");
            }
        }
        else {
            this.cardJpanel.getCar().show(this.cardJpanel, "l1");
            this.cardJpanel.getEquiJpanel().changeFrom(this.type);
        }
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
}
