package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import java.awt.Color;
import org.come.until.FormsManagement;
import org.come.Jpanel.RoleMsgJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class RoleMsgJframe extends JInternalFrame implements MouseListener
{
    private RoleMsgJpanel jpanel;
    private int first_x;
    private int first_y;
    
    public static RoleMsgJframe getRoleMsgJframe() {
        return (RoleMsgJframe)FormsManagement.getInternalForm(77).getFrame();
    }
    
    public RoleMsgJframe() throws Exception {
        this.jpanel = new RoleMsgJpanel();
        this.getContentPane().add(this.jpanel);
        this.setBackground(new Color(0, 0, 0, 0));
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBounds(0, 0, 160, 70);
        this.pack();
        this.setVisible(false);
        this.setDefaultCloseOperation(3);
        this.addMouseListener(this);
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (RoleMsgJframe.this.isVisible()) {
                    int x = e.getX() - RoleMsgJframe.this.first_x;
                    int y = e.getY() - RoleMsgJframe.this.first_y;
                    RoleMsgJframe.this.setBounds(x + RoleMsgJframe.this.getX(), y + RoleMsgJframe.this.getY(), RoleMsgJframe.this.getWidth(), RoleMsgJframe.this.getHeight());
                }
            }
        });
    }
    
    public RoleMsgJpanel getJpanel() {
        return this.jpanel;
    }
    
    public void setJpanel(RoleMsgJpanel jpanel) {
        this.jpanel = jpanel;
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
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        FormsManagement.showForm(289);
        if (e.isMetaDown()) {
            FormsManagement.HideForm(77);
            Music.addyinxiao("关闭窗口.mp3");
        }
        else {
            FormsManagement.Switchinglevel(77);
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
}
