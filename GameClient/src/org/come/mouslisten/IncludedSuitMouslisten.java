package org.come.mouslisten;

import org.come.Frame.ZhuFrame;
import org.come.Frame.AlreadyRecordedJframe;
import org.come.until.AccessSuitMsgUntil;
import java.awt.event.MouseEvent;
import org.come.Jpanel.AlreadyRecordedJpanel;
import java.awt.event.MouseListener;

public class IncludedSuitMouslisten implements MouseListener
{
    private AlreadyRecordedJpanel recordedJpanel;
    
    public IncludedSuitMouslisten(AlreadyRecordedJpanel recordedJpanel) {
        this.recordedJpanel = recordedJpanel;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (this.recordedJpanel.getListModel().size() > 0) {
            if (this.recordedJpanel.getListSuit().getSelectedIndex() != -1) {
                String v = (String)this.recordedJpanel.getListSuit().getSelectedValue();
                int suitid = (int)AccessSuitMsgUntil.returnSuitID(v);
                AlreadyRecordedJframe.getAlreadyRecordedJframe().getRecordedJpanel().setSuitid(suitid);
            }
            else {
                ZhuFrame.getZhuJpanel().addPrompt("请选择要收录的套装！！");
            }
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
    }
}
