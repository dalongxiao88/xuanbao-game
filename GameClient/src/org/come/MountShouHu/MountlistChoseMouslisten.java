package org.come.MountShouHu;

import org.come.until.FormsManagement;
import java.awt.event.MouseEvent;
import javax.swing.JList;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;

public class MountlistChoseMouslisten implements MouseListener, MouseMotionListener
{
    private JList<String> lsitmount;
    private xuanzeJpanel xuanzeJpanel;
    
    public MountlistChoseMouslisten(JList<String> lsitmount, xuanzeJpanel xuanzeJpanel) {
        this.lsitmount = lsitmount;
        this.xuanzeJpanel = xuanzeJpanel;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.isMetaDown()) {
            FormsManagement.HideForm(2255);
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
        org.come.MountShouHu.xuanzeJpanel.idx = -1;
        MyMountRenderer.select = -1;
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {
    }
}
