package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.LimitedTimeShopJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class LimitedTimeShopFrame extends JInternalFrame implements MouseListener
{
    private int first_x;
    private int first_y;
    private LimitedTimeShopJpanel limitedTimeShopJpanel;
    
    public static LimitedTimeShopFrame getLimitedTimeShopFrame() {
        return (LimitedTimeShopFrame)FormsManagement.getInternalForm(113).getFrame();
    }
    
    public LimitedTimeShopFrame() {
        this.add(this.limitedTimeShopJpanel = new LimitedTimeShopJpanel());
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBounds(115, 115, 735, 451);
        this.setOpaque(false);
        this.pack();
        this.setBackground(UIUtils.Color_BACK);
        this.setVisible(false);
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (LimitedTimeShopFrame.this.isVisible()) {
                    int x = e.getX() - LimitedTimeShopFrame.this.first_x;
                    int y = e.getY() - LimitedTimeShopFrame.this.first_y;
                    LimitedTimeShopFrame.this.setBounds(x + LimitedTimeShopFrame.this.getX(), y + LimitedTimeShopFrame.this.getY(), LimitedTimeShopFrame.this.getWidth(), LimitedTimeShopFrame.this.getHeight());
                }
            }
        });
        this.addMouseListener(this);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        Music.addyinxiao("关闭窗口.mp3");
        if (e.isMetaDown()) {
            FormsManagement.HideForm(113);
        }
        else {
            FormsManagement.Switchinglevel(113);
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
    
    public LimitedTimeShopJpanel getLimitedTimeShopJpanel() {
        return this.limitedTimeShopJpanel;
    }
    
    public void setLimitedTimeShopJpanel(LimitedTimeShopJpanel limitedTimeShopJpanel) {
        this.limitedTimeShopJpanel = limitedTimeShopJpanel;
    }
}
