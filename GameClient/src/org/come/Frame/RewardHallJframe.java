package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.RewardHallJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class RewardHallJframe extends JInternalFrame implements MouseListener
{
    private RewardHallJpanel rewardHallJpanel;
    private int first_x;
    private int first_y;
    
    public static RewardHallJframe getRewardHallJframe() {
        return (RewardHallJframe)FormsManagement.getInternalForm(59).getFrame();
    }
    
    public RewardHallJframe() throws Exception {
        this.rewardHallJpanel = new RewardHallJpanel();
        this.getContentPane().add(this.rewardHallJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBounds(244, 85, 345, 436);
        this.pack();
        this.setVisible(false);
        this.setBackground(UIUtils.Color_BACK);
        this.setDefaultCloseOperation(3);
        this.addMouseListener(this);
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (RewardHallJframe.this.isVisible()) {
                    int x = e.getX() - RewardHallJframe.this.first_x;
                    int y = e.getY() - RewardHallJframe.this.first_y;
                    RewardHallJframe.this.setBounds(x + RewardHallJframe.this.getX(), y + RewardHallJframe.this.getY(), RewardHallJframe.this.getWidth(), RewardHallJframe.this.getHeight());
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
            FormsManagement.HideForm(59);
        }
        else {
            FormsManagement.Switchinglevel(59);
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
    
    public RewardHallJpanel getRewardHallJpanel() {
        return this.rewardHallJpanel;
    }
    
    public void setRewardHallJpanel(RewardHallJpanel rewardHallJpanel) {
        this.rewardHallJpanel = rewardHallJpanel;
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
