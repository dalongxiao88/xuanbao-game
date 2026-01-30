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

public class DianQJframe1 extends JInternalFrame implements MouseListener
{
    private DianQJpanel dianJpanel;
    private int first_x;
    private int first_y;
    
    public static DianQJframe1 getDdianQJframe() {
        return (DianQJframe1)FormsManagement.getInternalForm(128).getFrame();
    }
    
    public DianQJframe1() throws Exception {
        this.dianJpanel = new DianQJpanel();
        this.getContentPane().add(this.dianJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBounds(400, 130, 100, 100);
        this.pack();
        this.setVisible(false);
        this.setBackground(UIUtils.Color_BACK);
        this.addMouseListener(this);
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (DianQJframe1.this.isVisible()) {
                    int x = e.getX() - DianQJframe1.this.first_x;
                    int y = e.getY() - DianQJframe1.this.first_y;
                    DianQJframe1.this.setBounds(x + DianQJframe1.this.getX(), y + DianQJframe1.this.getY(), DianQJframe1.this.getWidth(), DianQJframe1.this.getHeight());
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
            FormsManagement.HideForm(128);
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
    
    public DianQJpanel getDianJpanel() {
        return this.dianJpanel;
    }
    
    public void setDianJpanel(DianQJpanel dianJpanel) {
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
