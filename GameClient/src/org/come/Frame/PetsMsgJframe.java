package org.come.Frame;

import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import com.tool.tcpimg.UIUtils;
import org.come.until.FormsManagement;
import org.come.Jpanel.PetsMsgJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class PetsMsgJframe extends JInternalFrame implements MouseListener
{
    private PetsMsgJpanel petsMsgJpanel;
    
    public static PetsMsgJframe getPetsMsgJframe() {
        return (PetsMsgJframe)FormsManagement.getInternalForm(42).getFrame();
    }
    
    public PetsMsgJframe(int gheight) {
        this.add(this.petsMsgJpanel = new PetsMsgJpanel(gheight));
        this.setBackground(UIUtils.Color_BACK);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBounds(0, 0, 400, gheight);
        this.pack();
        this.setVisible(false);
        this.setDefaultCloseOperation(3);
        this.addMouseListener(this);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.isMetaDown()) {
            FormsManagement.HideForm(42);
        }
        else {
            FormsManagement.Switchinglevel(42);
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
    
    public PetsMsgJpanel getPetsMsgJpanel() {
        return this.petsMsgJpanel;
    }
    
    public void setPetsMsgJpanel(PetsMsgJpanel petsMsgJpanel) {
        this.petsMsgJpanel = petsMsgJpanel;
    }
}
