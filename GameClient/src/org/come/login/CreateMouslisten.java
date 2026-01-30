package org.come.login;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CreateMouslisten implements MouseListener
{
    private int i;
    private SpriteBtn btn;
    private LoginJpanel loginJpanel;
    
    public CreateMouslisten(int i, SpriteBtn btn, LoginJpanel loginJpanel) {
        this.i = i;
        this.btn = btn;
        this.loginJpanel = loginJpanel;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if (this.btn.isChoose()) {
            if (this.btn.getZhen() != 2) {
                this.btn.btn(2);
                this.loginJpanel.getCreateView().xzRole(this.i);
            }
            else {
                this.btn.btn(0);
            }
        }
        else {
            this.btn.btn(2);
        }
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        if (!this.btn.isChoose()) {
            this.btn.btn(0);
        }
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        if (this.btn.getZhen() != 2) {
            this.btn.btn(1);
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        if (this.btn.getZhen() != 2) {
            this.btn.btn(0);
        }
    }
}
