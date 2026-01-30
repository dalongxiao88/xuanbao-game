package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.XYJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class XYJframe extends JInternalFrame implements MouseListener
{
    private int first_x;
    private int first_y;
    private XYJpanel xyJpanel;
    
    public static XYJframe getXYJframe() {
        return (XYJframe)FormsManagement.getframe(124);
    }
    
    public XYJframe() throws Exception {
        this.xyJpanel = new XYJpanel();
        this.getContentPane().add(this.xyJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBounds(40, 100, 676, 467);
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
                if (XYJframe.this.isVisible()) {
                    int x = e.getX() - XYJframe.this.first_x;
                    int y = e.getY() - XYJframe.this.first_y;
                    XYJframe.this.setBounds(x + XYJframe.this.getX(), y + XYJframe.this.getY(), XYJframe.this.getWidth(), XYJframe.this.getHeight());
                }
            }
        });
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        int k = 0;
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        Music.addyinxiao("关闭窗口.mp3");
        if (e.isMetaDown()) {
            FormsManagement.HideForm(124);
        }
        else {
            FormsManagement.Switchinglevel(124);
        }
        this.first_x = e.getX();
        this.first_y = e.getY();
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        int k = 0;
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        int k = 0;
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        int k = 0;
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
    
    public XYJpanel getXyJpanel() {
        return this.xyJpanel;
    }
    
    public void setXyJpanel(XYJpanel xyJpanel) {
        this.xyJpanel = xyJpanel;
    }
}
