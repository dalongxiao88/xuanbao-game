package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.mountJPanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class MountJframe extends JInternalFrame implements MouseListener
{
    private mountJPanel mountjpanel;
    private int first_x;
    private int first_y;
    
    public static MountJframe getMountjframe() {
        return (MountJframe)FormsManagement.getInternalForm(7).getFrame();
    }
    
    public MountJframe() throws Exception {
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setContentPane(this.mountjpanel = new mountJPanel());
        this.setBackground(UIUtils.Color_BACK);
        this.setBounds(110, 70, 337, 450);
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
                if (MountJframe.this.isVisible()) {
                    int x = e.getX() - MountJframe.this.first_x;
                    int y = e.getY() - MountJframe.this.first_y;
                    MountJframe.this.setBounds(x + MountJframe.this.getX(), y + MountJframe.this.getY(), MountJframe.this.getWidth(), MountJframe.this.getHeight());
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
            FormsManagement.HideForm(7);
        }
        else {
            FormsManagement.Switchinglevel(7);
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
    
    public mountJPanel getMountjpanel() {
        return this.mountjpanel;
    }
    
    public void setMountjpanel(mountJPanel mountjpanel) {
        this.mountjpanel = mountjpanel;
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
