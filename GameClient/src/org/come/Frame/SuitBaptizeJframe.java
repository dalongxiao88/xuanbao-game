package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.SuitBaptizeJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class SuitBaptizeJframe extends JInternalFrame implements MouseListener
{
    private SuitBaptizeJpanel baptizeJpanel;
    private int first_x;
    private int first_y;
    
    public static SuitBaptizeJframe getSuitBaptizeJframe() {
        return (SuitBaptizeJframe)FormsManagement.getInternalForm(74).getFrame();
    }
    
    public SuitBaptizeJframe() {
        this.baptizeJpanel = new SuitBaptizeJpanel();
        this.getContentPane().add(this.baptizeJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBounds(210, 170, 456, 372);
        this.setOpaque(false);
        this.pack();
        this.setBackground(UIUtils.Color_BACK);
        this.setVisible(false);
        this.setDefaultCloseOperation(3);
        this.addMouseListener(this);
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (SuitBaptizeJframe.this.isVisible()) {
                    int x = e.getX() - SuitBaptizeJframe.this.first_x;
                    int y = e.getY() - SuitBaptizeJframe.this.first_y;
                    SuitBaptizeJframe.this.setBounds(x + SuitBaptizeJframe.this.getX(), y + SuitBaptizeJframe.this.getY(), SuitBaptizeJframe.this.getWidth(), SuitBaptizeJframe.this.getHeight());
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
            FormsManagement.HideForm(74);
        }
        else {
            FormsManagement.Switchinglevel(74);
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
    
    public SuitBaptizeJpanel getBaptizeJpanel() {
        return this.baptizeJpanel;
    }
    
    public void setBaptizeJpanel(SuitBaptizeJpanel baptizeJpanel) {
        this.baptizeJpanel = baptizeJpanel;
    }
}
