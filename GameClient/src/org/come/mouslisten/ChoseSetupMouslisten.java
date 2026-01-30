package org.come.mouslisten;

import org.come.until.FormsManagement;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ChoseSetupMouslisten implements MouseListener
{
    @Override
    public void mouseClicked(MouseEvent e) {
        if (!FormsManagement.getframe(50).isVisible()) {
            FormsManagement.showForm(50);
        }
        else {
            FormsManagement.HideForm(50);
        }
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
}
