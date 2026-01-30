package org.come.login;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class KeyMouslisten implements MouseListener
{
    private String getKey;
    private LoginView loginView;
    
    public KeyMouslisten(String getKey, LoginView loginView) {
        this.getKey = getKey;
        this.loginView = loginView;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        this.loginView.addtext(this.getKey);
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
