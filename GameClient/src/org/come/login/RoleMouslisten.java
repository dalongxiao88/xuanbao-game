package org.come.login;

import org.come.until.AutoNameUtil;
import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RoleMouslisten implements MouseListener
{
    private int i;
    private SpriteBtn btn;
    private LoginJpanel loginJpanel;
    private CreateView createView;
    
    public RoleMouslisten() {
    }
    
    public RoleMouslisten(int i, SpriteBtn btn, LoginJpanel loginJpanel) {
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
                if (this.i >= 14 && this.i <= 18) {
                    this.loginJpanel.loginSelected(this.i - 14);
                }
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
            if (this.i < 8) {
                this.loginJpanel.getCreateView().btnAbout(this.i);
            }
            else if (this.i == 8) {
                this.loginJpanel.getCreateView().ModifyDir(-1, 0);
            }
            else if (this.i == 9) {
                this.loginJpanel.getCreateView().ModifyDir(-1, 1);
            }
            else if (this.i == 10) {
                this.loginJpanel.createRole();
            }
            else if (this.i == 11) {
                Music.beijing(true);
                this.loginJpanel.framechange(2, null, null);
            }
            else if (this.i == 12) {
                this.loginJpanel.intoGame();
            }
            else if (this.i == 13) {
                this.loginJpanel.framechange(3, null, null);
            }
            else if (this.i == 14) {
                Music.beijing(false);
                this.loginJpanel.framechange(4, null, null);
            }
            else if (this.i == 15) {
                String autoName = AutoNameUtil.autoSurAndName();
                CreateView.textAccount.setText(autoName);
            }
            else if (this.i == 20) {
                Music.beijing(true);
                this.loginJpanel.framechange(1, null, null);
            }
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
