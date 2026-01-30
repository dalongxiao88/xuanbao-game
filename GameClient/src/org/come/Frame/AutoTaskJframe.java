package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.AutoTaskJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class AutoTaskJframe extends JInternalFrame implements MouseListener
{
    private AutoTaskJpanel autoTaskJpanel;
    private int first_x;
    private int first_y;
    
    public static AutoTaskJframe getAutoTaskJframe() {
        return (AutoTaskJframe)FormsManagement.getInternalForm(637).getFrame();
    }
    
    public AutoTaskJframe() throws Exception {
        this.autoTaskJpanel = new AutoTaskJpanel();
        this.getContentPane().add(this.autoTaskJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBounds(40, 100, 862, 411);
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
                if (AutoTaskJframe.this.isVisible()) {
                    int x = e.getX() - AutoTaskJframe.this.first_x;
                    int y = e.getY() - AutoTaskJframe.this.first_y;
                    AutoTaskJframe.this.setBounds(x + AutoTaskJframe.this.getX(), y + AutoTaskJframe.this.getY(), AutoTaskJframe.this.getWidth(), AutoTaskJframe.this.getHeight());
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
            FormsManagement.HideForm(637);
        }
        else {
            FormsManagement.Switchinglevel(637);
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
    
    public AutoTaskJpanel getAutoTaskJpanel() {
        return this.autoTaskJpanel;
    }
    
    public void setAutoTaskJpanel(AutoTaskJpanel autoTaskJpanel) {
        this.autoTaskJpanel = autoTaskJpanel;
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
