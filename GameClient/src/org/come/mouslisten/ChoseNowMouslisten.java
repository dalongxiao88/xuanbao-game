package org.come.mouslisten;

import com.tool.btn.MyAWTEventListener;
import java.awt.event.MouseEvent;
import org.come.Jpanel.ZhuJpanel;
import java.awt.event.MouseListener;

public class ChoseNowMouslisten implements MouseListener
{
    private ZhuJpanel zhuJpanel;
    
    public ChoseNowMouslisten(ZhuJpanel zhuJpanel) {
        this.zhuJpanel = zhuJpanel;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        String msg = this.zhuJpanel.getLabnow().getText();
        this.zhuJpanel.getDangqian().setText(msg);
        this.zhuJpanel.getLabnow().setVisible(false);
        this.zhuJpanel.getLabgroups().setVisible(false);
        this.zhuJpanel.getLabbangs().setVisible(false);
        this.zhuJpanel.getLabworld().setVisible(false);
        this.zhuJpanel.getLabnotice().setVisible(false);
        MyAWTEventListener.setMesageType("当前");
        this.zhuJpanel.getChoseDangQianMounslisten().setWhether(true);
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
    }
}
