package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.DianJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class DdianJframe extends JInternalFrame implements MouseListener
{
    private DianJpanel dianJpanel;
    private int first_x;
    private int first_y;
    
    public static DdianJframe getDdianJframe() {
        return (DdianJframe)FormsManagement.getInternalForm(127).getFrame();
    }
    
    public DdianJframe() throws Exception {
        this.dianJpanel = new DianJpanel();
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setContentPane(this.dianJpanel = new DianJpanel());
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
                if (DdianJframe.this.isVisible()) {
                    int x = e.getX() - DdianJframe.this.first_x;
                    int y = e.getY() - DdianJframe.this.first_y;
                    DdianJframe.this.setBounds(x + DdianJframe.this.getX(), y + DdianJframe.this.getY(), DdianJframe.this.getWidth(), DdianJframe.this.getHeight());
                }
            }
        });
    }
    
    public DianJpanel getDianJpanel() {
        return this.dianJpanel;
    }
    
    public void setDianJpanel(DianJpanel dianJpanel) {
        this.dianJpanel = dianJpanel;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
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
