package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.RookieGuideJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class RookieGuideJframe extends JInternalFrame implements MouseListener
{
    private RookieGuideJpanel guideJpanel;
    private int first_x;
    private int first_y;
    
    public static RookieGuideJframe getRookieGuideJframe() {
        return (RookieGuideJframe)FormsManagement.getInternalForm(68).getFrame();
    }
    
    public RookieGuideJframe() {
        this.guideJpanel = new RookieGuideJpanel();
        this.getContentPane().add(this.guideJpanel);
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
                if (RookieGuideJframe.this.isVisible()) {
                    int x = e.getX() - RookieGuideJframe.this.first_x;
                    int y = e.getY() - RookieGuideJframe.this.first_y;
                    RookieGuideJframe.this.setBounds(x + RookieGuideJframe.this.getX(), y + RookieGuideJframe.this.getY(), RookieGuideJframe.this.getWidth(), RookieGuideJframe.this.getHeight());
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
            FormsManagement.HideForm(68);
        }
        else {
            FormsManagement.Switchinglevel(68);
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
    
    public RookieGuideJpanel getGuideJpanel() {
        return this.guideJpanel;
    }
    
    public void setGuideJpanel(RookieGuideJpanel guideJpanel) {
        this.guideJpanel = guideJpanel;
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
