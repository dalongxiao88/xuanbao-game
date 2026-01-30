package org.come.login;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RaceMouslistenn implements MouseListener
{
    private int i;
    private int type;
    private SpriteBtn btn;
    private LoginJpanel loginJpanel;
    
    public RaceMouslistenn() {
    }
    
    public RaceMouslistenn(int i, SpriteBtn btn, LoginJpanel loginJpanel, int type) {
        this.i = i;
        this.type = type;
        this.btn = btn;
        this.loginJpanel = loginJpanel;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (this.type == 0) {
            if (this.btn.isChoose()) {
                if (this.btn.getZhen() != 2) {
                    this.btn.btn(2);
                }
            }
            else {
                this.btn.btn(2);
            }
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if (this.type == 1) {
            if (this.btn.isChoose()) {
                if (this.btn.getZhen() != 2) {
                    this.btn.btn(2);
                }
            }
            else {
                this.btn.btn(2);
            }
            this.loginJpanel.raceSelected(this.i);
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
            this.loginJpanel.getRaceView().yuxuantext(this.i);
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        if (this.btn.getZhen() != 2) {
            this.btn.btn(0);
            this.loginJpanel.getRaceView().yuxuantext(-1);
        }
    }
    
    public void raceSelected() {
        this.loginJpanel.raceSelected(this.i);
    }
}
