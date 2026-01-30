package com.tool.time;

import org.come.until.FormsManagement;
import org.come.until.ScrenceUntil;
import org.come.Frame.MsgJframe;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TimeMouslisten implements MouseListener
{
    private Limit limit;
    
    public TimeMouslisten(Limit limit) {
        this.limit = limit;
    }
    
    @Override
    public void mouseClicked(MouseEvent mouseevent) {
    }
    
    @Override
    public void mousePressed(MouseEvent mouseevent) {
    }
    
    @Override
    public void mouseReleased(MouseEvent mouseevent) {
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        MsgJframe.getJframe().getJapnel().zssxk(this.limit, ScrenceUntil.Screen_x - 10 - TimeLimit.getLimits().getlimit(this.limit) * 25, 70);
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        FormsManagement.HideForm(46);
    }
}
