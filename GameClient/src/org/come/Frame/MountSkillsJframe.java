package org.come.Frame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.MountSkillsJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class MountSkillsJframe extends JInternalFrame implements MouseListener
{
    private MountSkillsJpanel mountSkillsJpanel;
    private int first_x;
    private int first_y;
    
    public static MountSkillsJframe getMountSkillsJframe() {
        return (MountSkillsJframe)FormsManagement.getInternalForm(20).getFrame();
    }
    
    public MountSkillsJframe() throws Exception {
        this.mountSkillsJpanel = new MountSkillsJpanel();
        this.getContentPane().add(this.mountSkillsJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBounds(400, 100, 100, 100);
        this.setBackground(UIUtils.Color_BACK);
        this.pack();
        this.setDefaultCloseOperation(3);
        this.setVisible(false);
        this.addMouseListener(this);
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (MountSkillsJframe.this.isVisible()) {
                    int x = e.getX() - MountSkillsJframe.this.first_x;
                    int y = e.getY() - MountSkillsJframe.this.first_y;
                    MountSkillsJframe.this.setBounds(x + MountSkillsJframe.this.getX(), y + MountSkillsJframe.this.getY(), MountSkillsJframe.this.getWidth(), MountSkillsJframe.this.getHeight());
                }
            }
        });
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.isMetaDown()) {
            FormsManagement.HideForm(20);
        }
        else {
            FormsManagement.Switchinglevel(20);
        }
        this.first_x = e.getX();
        this.first_y = e.getY();
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
    
    public MountSkillsJpanel getMountSkillsJpanel() {
        return this.mountSkillsJpanel;
    }
    
    public void setMountSkillsJpanel(MountSkillsJpanel mountSkillsJpanel) {
        this.mountSkillsJpanel = mountSkillsJpanel;
    }
    
    public int getFirst_x() {
        return this.first_x;
    }
    
    public void setFirst_x(int first_x) {
        this.first_x = first_x;
    }
    
    public int getFirst_y() {
        return this.first_y;
    }
    
    public void setFirst_y(int first_y) {
        this.first_y = first_y;
    }
}
