package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import com.tool.tcpimg.UIUtils;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.YaZhuJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class YaZhuJframe extends JInternalFrame implements MouseListener
{
    private YaZhuJpanel yaZhuJpanel;
    private int first_x;
    private int first_y;
    
    public static YaZhuJframe getYaZhuJframe() {
        return (YaZhuJframe)FormsManagement.getInternalForm(713).getFrame();
    }
    
    public YaZhuJframe() {
        this.yaZhuJpanel = new YaZhuJpanel();
        this.getContentPane().add(this.yaZhuJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setBackground(UIUtils.Color_BACK);
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setLocation(400, 50);
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
                int x = e.getX() - YaZhuJframe.this.first_x;
                int y = e.getY() - YaZhuJframe.this.first_y;
                YaZhuJframe.this.setBounds(x + YaZhuJframe.this.getX(), y + YaZhuJframe.this.getY(), YaZhuJframe.this.getWidth(), YaZhuJframe.this.getHeight());
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
            FormsManagement.HideForm(713);
        }
        else {
            FormsManagement.Switchinglevel(713);
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
    
    public YaZhuJpanel getYaZhuJpanel() {
        return this.yaZhuJpanel;
    }
}
