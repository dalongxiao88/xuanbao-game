package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.RoleResistanceJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class RoleResistanceJframe extends JInternalFrame implements MouseListener,MouseMotionListener
{
    private RoleResistanceJpanel resistancejpanel;
    private int first_x;
    private int first_y;
    
    public RoleResistanceJpanel getResistancejpanel() {
        return this.resistancejpanel;
    }
    
    public void setResistancejpanel(RoleResistanceJpanel resistancejpanel) {
        this.resistancejpanel = resistancejpanel;
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
    
    public static RoleResistanceJframe getResistancejframe() {
        return (RoleResistanceJframe)FormsManagement.getInternalForm(8).getFrame();
    }
    
    public RoleResistanceJframe() throws Exception {
        this.resistancejpanel = new RoleResistanceJpanel();
        this.getContentPane().add(this.resistancejpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBounds(400, 50, 290, 450);
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
                if (RoleResistanceJframe.this.isVisible()) {
                    int x = e.getX() - RoleResistanceJframe.this.first_x;
                    int y = e.getY() - RoleResistanceJframe.this.first_y;
                    RoleResistanceJframe.this.setBounds(x + RoleResistanceJframe.this.getX(), y + RoleResistanceJframe.this.getY(), RoleResistanceJframe.this.getWidth(), RoleResistanceJframe.this.getHeight());
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
            FormsManagement.HideForm(8);
        }
        else {
            FormsManagement.Switchinglevel(8);
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
    @Override
    public void mouseDragged(MouseEvent e) {
        if (RoleResistanceJframe.this.isVisible()) {
            int x = e.getX() - RoleResistanceJframe.this.first_x;
            int y = e.getY() - RoleResistanceJframe.this.first_y;
            RoleResistanceJframe.this.setBounds(x + RoleResistanceJframe.this.getX(), y + RoleResistanceJframe.this.getY(), RoleResistanceJframe.this.getWidth(), RoleResistanceJframe.this.getHeight());
        }

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
