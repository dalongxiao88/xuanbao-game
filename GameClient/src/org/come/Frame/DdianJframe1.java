package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.DianJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class DdianJframe1 extends JInternalFrame implements MouseListener
{
    private DianJpanel dianJpanel;
    private int first_x;
    private int first_y;
    
    public static DdianJframe1 getDdianJframe() {
        return (DdianJframe1)FormsManagement.getInternalForm(127).getFrame();
    }
    
    public DdianJframe1() throws Exception {
        this.dianJpanel = new DianJpanel();
        this.getContentPane().add(this.dianJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBounds(190, 140, 470, 315);
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
                if (DdianJframe1.this.isVisible()) {
                    int x = e.getX() - DdianJframe1.this.first_x;
                    int y = e.getY() - DdianJframe1.this.first_y;
                    DdianJframe1.this.setBounds(x + DdianJframe1.this.getX(), y + DdianJframe1.this.getY(), DdianJframe1.this.getWidth(), DdianJframe1.this.getHeight());
                }
            }
        });
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        Music.addyinxiao("关闭窗口.mp3");
        if (e.isMetaDown()) {
            FormsManagement.HideForm(127);
        }
        else {
            FormsManagement.Switchinglevel(127);
        }
        this.first_x = e.getX();
        this.first_y = e.getY();
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    public DianJpanel getDianJpanel() {
        return this.dianJpanel;
    }
    
    public void setDianJpanel(DianJpanel dianJpanel) {
        this.dianJpanel = dianJpanel;
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
