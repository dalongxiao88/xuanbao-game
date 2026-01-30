package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.YuekaJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class YuekaJframe extends JInternalFrame implements MouseListener
{
    private YuekaJpanel yuekaJpanel;
    private int first_x;
    private int first_y;
    
    public static YuekaJframe getYuekaJframe() {
        return (YuekaJframe)FormsManagement.getInternalForm(901).getFrame();
    }
    
    public YuekaJframe() {
        this.yuekaJpanel = new YuekaJpanel();
        this.getContentPane().add(this.yuekaJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBounds(100, 90, 555, 407);
        this.setBackground(UIUtils.Color_BACK);
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
                if (YuekaJframe.this.isVisible()) {
                    int x = e.getX() - YuekaJframe.this.first_x;
                    int y = e.getY() - YuekaJframe.this.first_y;
                    YuekaJframe.this.setBounds(x + YuekaJframe.this.getX(), y + YuekaJframe.this.getY(), YuekaJframe.this.getWidth(), YuekaJframe.this.getHeight());
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
            FormsManagement.HideForm(901);
        }
        else {
            FormsManagement.Switchinglevel(901);
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
    
    public YuekaJpanel getYuekaJpanel() {
        return this.yuekaJpanel;
    }
    
    public void setYuekaJpanel(YuekaJpanel yuekaJpanel) {
        this.yuekaJpanel = yuekaJpanel;
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
