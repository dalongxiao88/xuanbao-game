package org.come.Frame;

import com.tool.tcpimg.UIUtils;
import org.come.Jpanel.carJPanel;
import org.come.Jpanel.mountJPanel;
import org.come.until.FormsManagement;
import org.come.until.Music;

import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class CarJframe extends JInternalFrame implements MouseListener
{
    private carJPanel carjpanel;
    private int first_x;
    private int first_y;

    public static CarJframe getMountjframe() {
        return (CarJframe)FormsManagement.getInternalForm(1002).getFrame();
    }

    public CarJframe() throws Exception {
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setContentPane(this.carjpanel = new carJPanel());
        this.setBackground(UIUtils.Color_BACK);
        this.setBounds(110, 70, 337, 450);
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
                if (CarJframe.this.isVisible()) {
                    int x = e.getX() - CarJframe.this.first_x;
                    int y = e.getY() - CarJframe.this.first_y;
                    CarJframe.this.setBounds(x + CarJframe.this.getX(), y + CarJframe.this.getY(), CarJframe.this.getWidth(), CarJframe.this.getHeight());
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
            FormsManagement.HideForm(1002);
        }
        else {
            FormsManagement.Switchinglevel(1002);
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
    
    public carJPanel getCarjpanel() {
        return this.carjpanel;
    }
    
    public void setCarjpanel(carJPanel carjpanel) {
        this.carjpanel = carjpanel;
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
