package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.RoleSkillJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class RoleSkillJframe extends JInternalFrame implements MouseListener
{
    private RoleSkillJpanel skilljpanel;
    private int first_x;
    private int first_y;
    
    public static RoleSkillJframe getSkilljframe() {
        return (RoleSkillJframe)FormsManagement.getInternalForm(9).getFrame();
    }
    
    public RoleSkillJframe() throws Exception {
        this.skilljpanel = new RoleSkillJpanel(this);
        this.getContentPane().add(this.skilljpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.pack();
        this.setDefaultCloseOperation(3);
        this.setVisible(false);
        this.setBounds(400, 65, 441, 395);
        this.addMouseListener(this);
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (RoleSkillJframe.getSkilljframe().isVisible()) {
                    int x = e.getX() - RoleSkillJframe.this.first_x;
                    int y = e.getY() - RoleSkillJframe.this.first_y;
                    RoleSkillJframe.getSkilljframe().setBounds(x + RoleSkillJframe.getSkilljframe().getX(), y + RoleSkillJframe.getSkilljframe().getY(), RoleSkillJframe.getSkilljframe().getWidth(), RoleSkillJframe.getSkilljframe().getHeight());
                }
            }
        });
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        Music.addyinxiao("关闭窗口.mp3");
        if (e.isMetaDown()) {
            FormsManagement.HideForm(9);
        }
        else {
            FormsManagement.Switchinglevel(9);
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
    
    public RoleSkillJpanel getSkilljpanel() {
        return this.skilljpanel;
    }
    
    public void setSkilljpanel(RoleSkillJpanel skilljpanel) {
        this.skilljpanel = skilljpanel;
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
