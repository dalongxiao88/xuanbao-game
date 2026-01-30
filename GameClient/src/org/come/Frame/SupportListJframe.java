package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.SupportListJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class SupportListJframe extends JInternalFrame implements MouseListener
{
    private SupportListJpanel supportListJpanel;
    private int first_x;
    private int first_y;
    
    public static SupportListJframe getSupportListJframe() {
        return (SupportListJframe)FormsManagement.getInternalForm(62).getFrame();
    }
    
    public SupportListJframe() {
        this.supportListJpanel = new SupportListJpanel();
        this.getContentPane().add(this.supportListJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBounds(100, 50, 276, 441);
        this.setBackground(UIUtils.Color_BACK);
        this.pack();
        this.setVisible(false);
        this.setDefaultCloseOperation(3);
        this.addMouseListener(this);
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (SupportListJframe.this.isVisible()) {
                    int x = e.getX() - SupportListJframe.this.first_x;
                    int y = e.getY() - SupportListJframe.this.first_y;
                    SupportListJframe.this.setBounds(x + SupportListJframe.this.getX(), y + SupportListJframe.this.getY(), SupportListJframe.this.getWidth(), SupportListJframe.this.getHeight());
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
            FormsManagement.HideForm(62);
        }
        else {
            FormsManagement.Switchinglevel(62);
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
    
    public SupportListJpanel getSupportListJpanel() {
        return this.supportListJpanel;
    }
    
    public void setSupportListJpanel(SupportListJpanel supportListJpanel) {
        this.supportListJpanel = supportListJpanel;
    }
}
