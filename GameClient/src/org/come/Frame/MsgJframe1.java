package org.come.Frame;

import java.awt.event.MouseEvent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import com.tool.tcpimg.UIUtils;
import org.come.until.FormsManagement;
import org.come.Jpanel.MsgJapnel1;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class MsgJframe1 extends JInternalFrame implements MouseListener
{
    private MsgJapnel1 japnel1;
    
    public static MsgJframe1 getJframe1() {
        return (MsgJframe1)FormsManagement.getInternalForm(603).getFrame();
    }
    
    public MsgJframe1() {
        this.add(this.japnel1 = new MsgJapnel1());
        this.setBackground(UIUtils.Color_BACK);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBounds(0, 0, 470, 310);
        this.pack();
        this.setVisible(false);
        this.setDefaultCloseOperation(3);
    }
    
    public MsgJapnel1 getJapnel1() {
        return this.japnel1;
    }
    
    public void setJapnel1(MsgJapnel1 japnel) {
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.isMetaDown()) {
            FormsManagement.HideForm(603);
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
