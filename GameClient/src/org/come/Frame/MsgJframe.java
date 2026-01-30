package org.come.Frame;

import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import com.tool.tcpimg.UIUtils;
import org.come.until.FormsManagement;
import org.come.Jpanel.MsgJapnel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class MsgJframe extends JInternalFrame implements MouseListener
{
    private MsgJapnel japnel;
    
    public static MsgJframe getJframe() {
        return (MsgJframe)FormsManagement.getInternalForm(46).getFrame();
    }
    
    public MsgJframe() {
        this.add(this.japnel = new MsgJapnel());
        this.setBackground(UIUtils.Color_BACK);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBounds(0, 0, 470, 310);
        this.pack();
        this.setVisible(false);
        this.setDefaultCloseOperation(3);
    }
    
    public MsgJapnel getJapnel() {
        return this.japnel;
    }
    
    public void setJapnel(MsgJapnel japnel) {
        this.japnel = japnel;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.isMetaDown()) {
            FormsManagement.HideForm(46);
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
