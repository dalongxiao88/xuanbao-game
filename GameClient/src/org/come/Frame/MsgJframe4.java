package org.come.Frame;

import java.awt.event.MouseEvent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import com.tool.tcpimg.UIUtils;
import org.come.until.FormsManagement;
import org.come.Jpanel.MsgJapnel4;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class MsgJframe4 extends JInternalFrame implements MouseListener
{
    private MsgJapnel4 japnel4;
    
    public static MsgJframe4 getJframe4() {
        return (MsgJframe4)FormsManagement.getInternalForm(633).getFrame();
    }
    
    public MsgJframe4() {
        this.add(this.japnel4 = new MsgJapnel4());
        this.setBackground(UIUtils.Color_BACK);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBounds(0, 0, 470, 310);
        this.pack();
        this.setVisible(false);
        this.setDefaultCloseOperation(3);
    }
    
    public MsgJapnel4 getJapnel4() {
        return this.japnel4;
    }
    
    public void setJapnel4(MsgJapnel4 japnel4) {
        this.japnel4 = japnel4;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.isMetaDown()) {
            FormsManagement.HideForm(633);
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
