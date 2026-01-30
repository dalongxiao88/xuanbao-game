package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.DianQJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class DianQJframe extends JInternalFrame implements MouseListener
{
    private DianQJpanel dianJpanel;
    private int first_x;
    private int first_y;
    
    public static DianQJframe getDdianQJframe() {
        return (DianQJframe)FormsManagement.getInternalForm(128).getFrame();
    }
    
    public DianQJframe() throws Exception {
        this.dianJpanel = new DianQJpanel();
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setContentPane(this.dianJpanel);
        this.setBounds(190, 140, 470, 315);
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
                if (DianQJframe.this.isVisible()) {
                    int x = e.getX() - DianQJframe.this.first_x;
                    int y = e.getY() - DianQJframe.this.first_y;
                    DianQJframe.this.setBounds(x + DianQJframe.this.getX(), y + DianQJframe.this.getY(), DianQJframe.this.getWidth(), DianQJframe.this.getHeight());
                }
            }
        });
    }
    
    public DianQJpanel getDianJpanel() {
        return this.dianJpanel;
    }
    
    public void setDianJpanel(DianQJpanel dianJpanel) {
        this.dianJpanel = dianJpanel;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        Music.addyinxiao("关闭窗口.mp3");
        if (e.isMetaDown()) {
            FormsManagement.HideForm(128);
        }
        else {
            FormsManagement.Switchinglevel(128);
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
