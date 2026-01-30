package org.come.mouslisten;

import java.util.List;
import org.come.entity.Baby;
import org.come.Frame.TestChildJframe;
import org.come.until.UserMessUntil;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ChangeBabyMouslisten implements MouseListener
{
    private int path;
    
    public ChangeBabyMouslisten(int path) {
        this.path = path;
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        List<Baby> a = UserMessUntil.getMyListBaby();
        if (a != null && this.path < a.size()) {
            TestChildJframe.getTestChildJframe().getTestChildJpanel().ShowBaby((Baby)a.get(this.path));
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
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
}
