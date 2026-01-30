package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.ZxpackJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class ZxpackJframe extends JInternalFrame implements MouseListener
{
    private ZxpackJpanel zxpackJpanel;
    private int first_x;
    private int first_y;
    
    public static ZxpackJframe getZxpackJpanel() {
        return (ZxpackJframe)FormsManagement.getInternalForm(643).getFrame();
    }
    
    public ZxpackJframe() throws Exception {
        this.zxpackJpanel = new ZxpackJpanel();
        this.getContentPane().add(this.zxpackJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBounds(480, 150, 382, 359);
        this.pack();
        this.setDefaultCloseOperation(3);
        this.setBackground(UIUtils.Color_BACK);
        this.setVisible(false);
        this.addMouseListener(this);
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (ZxpackJframe.this.isVisible()) {
                    int x = e.getX() - ZxpackJframe.this.first_x;
                    int y = e.getY() - ZxpackJframe.this.first_y;
                    ZxpackJframe.this.setBounds(x + ZxpackJframe.this.getX(), y + ZxpackJframe.this.getY(), ZxpackJframe.this.getWidth(), ZxpackJframe.this.getHeight());
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
        FormsManagement.Switchinglevel(643);
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
    
    public void setZxpackJpanel(ZxpackJpanel zxpackJpanel) {
        this.zxpackJpanel = zxpackJpanel;
    }
}
