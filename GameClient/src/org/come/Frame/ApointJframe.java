package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.ApointJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class ApointJframe extends JInternalFrame implements MouseListener
{
    private ApointJpanel apointJpanel;
    private int first_x;
    private int first_y;
    
    public static ApointJframe getApointJframe() {
        return (ApointJframe)FormsManagement.getInternalForm(37).getFrame();
    }
    
    public ApointJframe() throws Exception {
        this.apointJpanel = new ApointJpanel();
        this.getContentPane().add(this.apointJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBackground(UIUtils.Color_BACK);
        this.setBounds(370, 150, 100, 100);
        this.pack();
        this.setVisible(false);
        this.setDefaultCloseOperation(3);
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (ApointJframe.this.isVisible()) {
                    int x = e.getX() - ApointJframe.this.first_x;
                    int y = e.getY() - ApointJframe.this.first_y;
                    ApointJframe.this.setBounds(x + ApointJframe.this.getX(), y + ApointJframe.this.getY(), ApointJframe.this.getWidth(), ApointJframe.this.getHeight());
                }
            }
        });
        this.addMouseListener(this);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        Music.addyinxiao("关闭窗口.mp3");
        if (e.isMetaDown()) {
            FormsManagement.HideForm(37);
        }
        else {
            FormsManagement.Switchinglevel(37);
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
    
    public ApointJpanel getapointJpanel() {
        return this.apointJpanel;
    }
    
    public void setapointJpanel(ApointJpanel apointJpanel) {
        this.apointJpanel = apointJpanel;
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
    
    public ApointJpanel getApointJpanel() {
        return this.apointJpanel;
    }
    
    public void setApointJpanel(ApointJpanel apointJpanel) {
        this.apointJpanel = apointJpanel;
    }
}
