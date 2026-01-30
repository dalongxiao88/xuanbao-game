package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.ActivityJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class ActivityJframe extends JInternalFrame implements MouseListener
{
    private ActivityJpanel activityJpanel;
    private int first_x;
    private int first_y;
    
    public static ActivityJframe getActivityJframe() {
        return (ActivityJframe)FormsManagement.getInternalForm(40).getFrame();
    }
    
    public ActivityJframe() throws Exception {
        this.activityJpanel = new ActivityJpanel();
        this.getContentPane().add(this.activityJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBounds(190, 140, 676, 467);
        this.pack();
        this.setBackground(UIUtils.Color_BACK);
        this.setDefaultCloseOperation(3);
        this.setVisible(false);
        this.addMouseListener(this);
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (ActivityJframe.this.isVisible()) {
                    int x = e.getX() - ActivityJframe.this.first_x;
                    int y = e.getY() - ActivityJframe.this.first_y;
                    ActivityJframe.this.setBounds(x + ActivityJframe.this.getX(), y + ActivityJframe.this.getY(), ActivityJframe.this.getWidth(), ActivityJframe.this.getHeight());
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
            FormsManagement.HideForm(40);
        }
        else {
            FormsManagement.Switchinglevel(40);
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
    
    public ActivityJpanel getActivityJpanel() {
        return this.activityJpanel;
    }
    
    public void setActivityJpanel(ActivityJpanel activityJpanel) {
        this.activityJpanel = activityJpanel;
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
