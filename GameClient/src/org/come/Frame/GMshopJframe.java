package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.GMshopJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class GMshopJframe extends JInternalFrame implements MouseListener
{
    private static GMshopJpanel gMshopJpanel;
    private int first_x;
    private int first_y;
    
    public static GMshopJframe getRankingListJframe() {
        return (GMshopJframe)FormsManagement.getInternalForm(60).getFrame();
    }
    
    public GMshopJframe() {
        GMshopJframe.gMshopJpanel = new GMshopJpanel();
        this.getContentPane().add(GMshopJframe.gMshopJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBounds(220, 150, 362, 308);
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
                if (GMshopJframe.this.isVisible()) {
                    int x = e.getX() - GMshopJframe.this.first_x;
                    int y = e.getY() - GMshopJframe.this.first_y;
                    GMshopJframe.this.setBounds(x + GMshopJframe.this.getX(), y + GMshopJframe.this.getY(), GMshopJframe.this.getWidth(), GMshopJframe.this.getHeight());
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
            FormsManagement.HideForm(2000);
        }
        else {
            FormsManagement.Switchinglevel(2000);
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
    
    public static GMshopJpanel getGMshopJpanel() {
        return GMshopJframe.gMshopJpanel;
    }
    
    public static void setGMshopJpanel(GMshopJpanel gMshopJpanel) {
        GMshopJframe.gMshopJpanel = gMshopJpanel;
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
