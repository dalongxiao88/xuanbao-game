package org.come.Frame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.HuangJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class HuangJframe extends JInternalFrame implements MouseListener
{
    private int first_x;
    private int first_y;
    private HuangJpanel huangJpanel;
    
    public static HuangJframe getHuangJframe() {
        return (HuangJframe)FormsManagement.getInternalForm(160).getFrame();
    }
    
    public HuangJframe() throws Exception {
        this.setContentPane(this.huangJpanel = new HuangJpanel());
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBounds(280, 70, 300, 128);
        this.pack();
        this.setVisible(false);
        this.setBackground(UIUtils.Color_BACK);
        this.setDefaultCloseOperation(3);
        this.addMouseListener(this);
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (HuangJframe.this.isVisible()) {
                    int x = e.getX() - HuangJframe.this.first_x;
                    int y = e.getY() - HuangJframe.this.first_y;
                    HuangJframe.this.setBounds(x + HuangJframe.this.getX(), y + HuangJframe.this.getY(), HuangJframe.this.getWidth(), HuangJframe.this.getHeight());
                }
            }
        });
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.isMetaDown()) {
            FormsManagement.HideForm(160);
        }
        else {
            FormsManagement.Switchinglevel(160);
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
    
    public HuangJpanel getHuangJpanel() {
        return this.huangJpanel;
    }
    
    public void setHuangJpanel(HuangJpanel huangJpanel) {
        this.huangJpanel = huangJpanel;
    }
}
