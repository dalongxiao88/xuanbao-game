package org.come.Frame;

import org.come.Jpanel.ZhuJpanel;
import org.come.entity.Goodstable;
import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.NedanJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class NedanJframe extends JInternalFrame implements MouseListener
{
    private NedanJpanel nedanJpanel;
    private int first_x;
    private int first_y;
    
    public static NedanJframe getNedanJframe() {
        return (NedanJframe)FormsManagement.getInternalForm(47).getFrame();
    }
    
    public NedanJframe() throws Exception {
        this.nedanJpanel = new NedanJpanel();
        this.getContentPane().add(this.nedanJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBounds(400, 100, 100, 100);
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
                if (NedanJframe.this.isVisible()) {
                    int x = e.getX() - NedanJframe.this.first_x;
                    int y = e.getY() - NedanJframe.this.first_y;
                    NedanJframe.this.setBounds(x + NedanJframe.this.getX(), y + NedanJframe.this.getY(), NedanJframe.this.getWidth(), NedanJframe.this.getHeight());
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
            FormsManagement.HideForm(47);
            ZhuJpanel.setNedangoods((Goodstable)null);
        }
        else {
            FormsManagement.Switchinglevel(47);
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
    
    public NedanJpanel getNedanJpanel() {
        return this.nedanJpanel;
    }
    
    public void setNedanJpanel(NedanJpanel nedanJpanel) {
        this.nedanJpanel = nedanJpanel;
    }
}
