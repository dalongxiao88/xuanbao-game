package org.come.Frame;

import java.awt.event.MouseEvent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.GangsGuardJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class GangsGuardJframe extends JInternalFrame implements MouseListener
{
    private static final long serialVersionUID = 1L;
    private GangsGuardJpanel gangsGuardJpanel;
    
    public static GangsGuardJframe getGangsGuardJframe() {
        return (GangsGuardJframe)FormsManagement.getInternalForm(53).getFrame();
    }
    
    public GangsGuardJframe() {
        this.add(this.gangsGuardJpanel = new GangsGuardJpanel());
        this.setContentPane(this.gangsGuardJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.addMouseListener(this);
        this.pack();
        this.setBounds(150, 160, this.getWidth(), this.getHeight());
        this.setVisible(false);
        this.setDefaultCloseOperation(2);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.isMetaDown()) {
            FormsManagement.HideForm(53);
        }
        else {
            FormsManagement.Switchinglevel(53);
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
    
    public GangsGuardJpanel getGangsGuardJpanel() {
        return this.gangsGuardJpanel;
    }
    
    public void setGangsGuardJpanel(GangsGuardJpanel gangsGuardJpanel) {
        this.gangsGuardJpanel = gangsGuardJpanel;
    }
}
