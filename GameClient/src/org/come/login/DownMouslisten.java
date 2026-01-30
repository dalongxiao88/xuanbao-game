package org.come.login;

import java.awt.Color;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import java.awt.event.MouseListener;

public class DownMouslisten implements MouseListener
{
    private int type;
    private LoginJpanel loginJpanel;
    private String text;
    private JLabel jlabel;
    
    public DownMouslisten(LoginJpanel loginJpanel, String text, JLabel jlabel, int type) {
        this.loginJpanel = loginJpanel;
        this.text = text;
        this.jlabel = jlabel;
        this.type = type;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (this.type == 0) {
            this.loginJpanel.getLoginView().cshtext(this.text);
        }
        else if (this.type == 1) {
            this.loginJpanel.getLoginView().getDownView().cshuser(this.loginJpanel.getLoginView().removeJiLu(this.text).split("\\|"), this.loginJpanel);
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
        if (this.type == 0) {
            this.jlabel.setForeground(Color.RED);
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        if (this.type == 0) {
            this.jlabel.setForeground(Color.WHITE);
        }
    }
}
