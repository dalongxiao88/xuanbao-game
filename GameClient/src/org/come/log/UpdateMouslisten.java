package org.come.log;

import java.io.IOException;
import com.main.UpdateMain;
import java.awt.event.MouseEvent;
import org.come.login.LoginJpanel;
import org.come.login.SpriteBtn;
import java.awt.event.MouseListener;

public class UpdateMouslisten implements MouseListener
{
    private int i;
    private SpriteBtn btn;
    private LoginJpanel loginJpanel;
    
    public UpdateMouslisten(int i, SpriteBtn btn) {
        this.i = i;
        this.btn = btn;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if (this.btn.isChoose()) {
            if (this.btn.getZhen() != 2) {
                this.btn.btn(2);
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
            if (this.i == 2) {
                System.exit(1);
            }
            if (this.i == 1) {
                UpdateMain.setJframe.setVisible(!UpdateMain.setJframe.isVisible());
            }
            else if (this.i == 4) {
                String ur = System.getProperty("user.dir");
                try {
                    Runtime.getRuntime().exec(ur + "/sdls.dll -gameParam=" + UpdateMain.Param);
                    System.exit(1);
                }
                catch (IOException ioException) {
                    ioException.printStackTrace();
                }
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
