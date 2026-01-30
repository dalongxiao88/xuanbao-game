package org.come.action;

import java.awt.event.MouseEvent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import com.tool.tcpimg.UIUtils;
import org.come.until.FormsManagement;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class MsgJframe2 extends JInternalFrame implements MouseListener
{
    private MsgJapnel2 japnel2;
    
    public static MsgJframe2 getJframe2() {
        return (MsgJframe2)FormsManagement.getInternalForm(628).getFrame();
    }
    
    public MsgJframe2() {
        this.add(this.japnel2 = new MsgJapnel2());
        this.setBackground(UIUtils.Color_BACK);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBounds(0, 0, 470, 310);
        this.pack();
        this.setVisible(false);
        this.setDefaultCloseOperation(3);
    }
    
    public MsgJapnel2 getJapnel2() {
        return this.japnel2;
    }
    
    public void setJapnel2(MsgJapnel2 japnel2) {
        this.japnel2 = japnel2;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.isMetaDown()) {
            FormsManagement.HideForm(628);
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
