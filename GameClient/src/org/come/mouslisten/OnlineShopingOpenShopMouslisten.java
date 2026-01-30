package org.come.mouslisten;

import org.come.until.Music;
import org.come.until.FormsManagement;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class OnlineShopingOpenShopMouslisten implements MouseListener
{
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == 1) {
            FormsManagement.showForm(39);
        }
        Music.addyinxiao("开关窗口.mp3");
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
