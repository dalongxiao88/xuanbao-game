package org.come.Frame;

import org.come.until.Music;
import org.come.until.MessagrFlagUntil;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.WxchangeAwardJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class WxchangeAwardJframe extends JInternalFrame implements MouseListener
{
    private WxchangeAwardJpanel wxchangeAwardJpanel;
    private int first_x;
    private int first_y;
    
    public static WxchangeAwardJframe getExchangeAwardJframe() {
        return (WxchangeAwardJframe)FormsManagement.getInternalForm(289).getFrame();
    }
    
    public WxchangeAwardJframe() throws Exception {
        this.wxchangeAwardJpanel = new WxchangeAwardJpanel();
        this.getContentPane().add(this.wxchangeAwardJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBackground(UIUtils.Color_BACK);
        this.setBounds(0, 0, 0, 0);
        this.pack();
        this.setVisible(false);
        this.setDefaultCloseOperation(3);
        this.addMouseListener(this);
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if (MessagrFlagUntil.ImgFlagImg.equals(MessagrFlagUntil.MOUSE1)) {
                    MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
                }
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (WxchangeAwardJframe.this.isVisible()) {
                    int x = e.getX() - WxchangeAwardJframe.this.first_x;
                    int y = e.getY() - WxchangeAwardJframe.this.first_y;
                    WxchangeAwardJframe.this.setBounds(x + WxchangeAwardJframe.this.getX(), y + WxchangeAwardJframe.this.getY(), WxchangeAwardJframe.this.getWidth(), WxchangeAwardJframe.this.getHeight());
                }
            }
        });
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        Music.addyinxiao("鍏抽棴绐楀彛.mp3");
        if (e.isMetaDown()) {
            FormsManagement.HideForm(289);
        }
        else {
            FormsManagement.Switchinglevel(289);
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
    
    public WxchangeAwardJpanel getWxchangeAwardJpanel() {
        return this.wxchangeAwardJpanel;
    }
    
    public void setWxchangeAwardJpanel(WxchangeAwardJpanel wxchangeAwardJpanel) {
        this.wxchangeAwardJpanel = wxchangeAwardJpanel;
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
