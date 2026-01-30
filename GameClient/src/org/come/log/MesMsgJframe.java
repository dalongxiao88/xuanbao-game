package org.come.log;

import java.awt.event.MouseEvent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import com.tool.tcpimg.UIUtils;
import org.come.until.FormsManagement;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class MesMsgJframe extends JInternalFrame implements MouseListener
{
    private MesMsgJpanel japnel;
    
    public static MesMsgJframe getMesMsgJframe() {
        return (MesMsgJframe)FormsManagement.getInternalForm(866198).getFrame();
    }
    
    public MesMsgJframe() {
        this.add(this.japnel = new MesMsgJpanel());
        this.setBackground(UIUtils.Color_BACK);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBounds(0, 0, 470, 310);
        this.pack();
        this.setVisible(false);
        this.setDefaultCloseOperation(3);
    }
    
    public MesMsgJpanel getJapnel() {
        return this.japnel;
    }
    
    public void setJapnel(MesMsgJpanel japnel) {
        this.japnel = japnel;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.isMetaDown()) {
            FormsManagement.HideForm(866198);
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
