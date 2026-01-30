package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.BoothBoxJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class BoothBoxJframe extends JInternalFrame implements MouseListener
{
    private BoothBoxJpanel boothboxjpanel;
    private int first_x;
    private int first_y;
    
    public static BoothBoxJframe getBoothboxjframe() {
        return (BoothBoxJframe)FormsManagement.getInternalForm(16).getFrame();
    }
    
    public BoothBoxJframe() throws Exception {
        this.boothboxjpanel = new BoothBoxJpanel(this);
        this.getContentPane().add(this.boothboxjpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBounds(20, 70, 345, 427);
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
                if (BoothBoxJframe.this.isVisible()) {
                    int x = e.getX() - BoothBoxJframe.this.first_x;
                    int y = e.getY() - BoothBoxJframe.this.first_y;
                    BoothBoxJframe.this.setBounds(x + BoothBoxJframe.this.getX(), y + BoothBoxJframe.this.getY(), BoothBoxJframe.this.getWidth(), BoothBoxJframe.this.getHeight());
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
            FormsManagement.HideForm(15);
            FormsManagement.HideForm(16);
        }
        else {
            FormsManagement.Switchinglevel(16);
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
    
    public BoothBoxJpanel getBoothboxjpanel() {
        return this.boothboxjpanel;
    }
    
    public void setBoothboxjpanel(BoothBoxJpanel boothboxjpanel) {
        this.boothboxjpanel = boothboxjpanel;
    }
}
