package org.come.MountShouHu;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class xuanzeJframe extends JInternalFrame implements MouseListener
{
    private int first_x;
    private int first_y;
    private xuanzeJpanel xuanzeJpanel;
    
    public static xuanzeJframe getxuanzeJframe() {
        return (xuanzeJframe)FormsManagement.getInternalForm(2255).getFrame();
    }
    
    public xuanzeJframe() throws Exception {
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setContentPane(this.xuanzeJpanel = new xuanzeJpanel());
        this.setBackground(UIUtils.Color_BACK);
        this.setBounds(100, 140, 740, 516);
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
                if (xuanzeJframe.this.isVisible()) {
                    int x = e.getX() - xuanzeJframe.this.first_x;
                    int y = e.getY() - xuanzeJframe.this.first_y;
                    xuanzeJframe.this.setBounds(x + xuanzeJframe.this.getX(), y + xuanzeJframe.this.getY(), xuanzeJframe.this.getWidth(), xuanzeJframe.this.getHeight());
                }
            }
        });
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        FormsManagement.showForm(2255);
        Music.addyinxiao("关闭窗口.mp3");
        if (e.isMetaDown()) {
            FormsManagement.HideForm(2255);
        }
        else {
            FormsManagement.Switchinglevel(2255);
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
    
    public xuanzeJpanel getXuanzeJpanel() {
        return this.xuanzeJpanel;
    }
    
    public void setXuanzeJpanel(xuanzeJpanel xuanzeJpanel) {
        this.xuanzeJpanel = xuanzeJpanel;
    }
}
