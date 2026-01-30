package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.RoleToggleJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class RoleToggleJframe extends JInternalFrame implements MouseListener
{
    private static final long serialVersionUID = -8285327662130024086L;
    private RoleToggleJpanel roleToggleJpanel;
    private int first_x;
    private int first_y;
    
    public static RoleToggleJframe getRoleToggleJframe() {
        return (RoleToggleJframe)FormsManagement.getInternalForm(123).getFrame();
    }
    
    public RoleToggleJframe() throws Exception {
        this.roleToggleJpanel = new RoleToggleJpanel(this);
        this.getContentPane().add(this.roleToggleJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBackground(UIUtils.Color_BACK);
        this.setBounds(100, 85, 100, 100);
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
                if (RoleToggleJframe.this.isVisible()) {
                    int x = e.getX() - RoleToggleJframe.this.first_x;
                    int y = e.getY() - RoleToggleJframe.this.first_y;
                    RoleToggleJframe.this.setBounds(x + RoleToggleJframe.this.getX(), y + RoleToggleJframe.this.getY(), RoleToggleJframe.this.getWidth(), RoleToggleJframe.this.getHeight());
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
            FormsManagement.HideForm(123);
            Music.addyinxiao("关闭窗口.mp3");
        }
        else {
            FormsManagement.Switchinglevel(123);
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
    
    public RoleToggleJpanel getRoleToggleJpanel() {
        return this.roleToggleJpanel;
    }
    
    public void setRoleToggleJpanel(RoleToggleJpanel roleToggleJpanel) {
        this.roleToggleJpanel = roleToggleJpanel;
    }
}
