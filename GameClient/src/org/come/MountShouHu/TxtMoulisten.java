package org.come.MountShouHu;

import org.come.until.FormsManagement;
import org.come.bean.PathPoint;
import org.come.Frame.MsgJframe;
import org.come.Jpanel.GameJpanel;
import org.come.until.MessagrFlagUntil;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TxtMoulisten implements MouseListener
{
    private int i;
    
    public TxtMoulisten(int i) {
        this.i = i;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        if (MessagrFlagUntil.ImgFlagImg.equals(MessagrFlagUntil.MOUSE1)) {
            MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE12);
        }
        switch (this.i) {
            case 0: {
                PathPoint point = GameJpanel.getGameJpanel().mousepath();
                MsgJframe.getJframe().getJapnel().vips1("右键取消被守护的坐骑", "c9bd86");
                break;
            }
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        if (MessagrFlagUntil.ImgFlagImg.equals(MessagrFlagUntil.MOUSE12)) {
            MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
        }
        FormsManagement.HideForm(46);
        FormsManagement.HideForm(866198);
        FormsManagement.HideForm(603);
    }
}
