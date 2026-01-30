package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.RoleBornJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class RoleBornJframe extends JInternalFrame implements MouseListener
{
    private static final long serialVersionUID = -8285327662130024086L;
    private RoleBornJpanel roleBornJpanel;
    private int first_x;
    private int first_y;
    
    public static RoleBornJframe getRoleToggleJframe() {
        return (RoleBornJframe)FormsManagement.getInternalForm(1234).getFrame();
    }
    
    public RoleBornJframe() throws Exception {
        this.roleBornJpanel = new RoleBornJpanel(this);
        this.getContentPane().add(this.roleBornJpanel);
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
                if (RoleBornJframe.this.isVisible()) {
                    int x = e.getX() - RoleBornJframe.this.first_x;
                    int y = e.getY() - RoleBornJframe.this.first_y;
                    RoleBornJframe.this.setBounds(x + RoleBornJframe.this.getX(), y + RoleBornJframe.this.getY(), RoleBornJframe.this.getWidth(), RoleBornJframe.this.getHeight());
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
            FormsManagement.HideForm(1234);
            FormsManagement.disposeForm(1234);
            Music.addyinxiao("关闭窗口.mp3");
        }
        else {
            FormsManagement.Switchinglevel(1234);
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
    
    public RoleBornJpanel getRoleBornJpanel() {
        return this.roleBornJpanel;
    }
    
    public void setRoleBornJpanel(RoleBornJpanel roleBornJpanel) {
        this.roleBornJpanel = roleBornJpanel;
    }
}
