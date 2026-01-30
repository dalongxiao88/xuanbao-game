package org.come.Frame;

import org.come.until.Music;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.Color;
import org.come.until.FormsManagement;
import org.come.Jpanel.GoodsMsgJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class GoodsMsgJframe extends JInternalFrame implements MouseListener
{
    private GoodsMsgJpanel goodsMsgJpanel;
    
    public static GoodsMsgJframe getGoodsMsgJframe() {
        return (GoodsMsgJframe)FormsManagement.getInternalForm(24).getFrame();
    }
    
    public GoodsMsgJframe() {
        this.add(this.goodsMsgJpanel = new GoodsMsgJpanel(420, 150));
        this.addMouseListener(this);
        this.setBackground(new Color(0, 0, 0, 0));
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
            }
        });
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBounds(0, 0, 420, 150);
        this.pack();
        this.setVisible(false);
        this.setDefaultCloseOperation(3);
    }
    
    public GoodsMsgJpanel getGoodsMsgJpanel() {
        return this.goodsMsgJpanel;
    }
    
    public void setGoodsMsgJpanel(GoodsMsgJpanel goodsMsgJpanel) {
        this.goodsMsgJpanel = goodsMsgJpanel;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        Music.addyinxiao("关闭窗口.mp3");
        if (e.isMetaDown()) {
            FormsManagement.HideForm(24);
        }
        else {
            FormsManagement.Switchinglevel(24);
        }
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
}
