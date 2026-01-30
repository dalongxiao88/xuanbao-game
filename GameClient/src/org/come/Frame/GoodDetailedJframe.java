package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.GoodDetailedJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class GoodDetailedJframe extends JInternalFrame implements MouseListener
{
    private int first_x;
    private int first_y;
    private GoodDetailedJpanel goodDetailedJpanel;
    
    public static GoodDetailedJframe getGoodDetailedJframe() {
        return (GoodDetailedJframe)FormsManagement.getInternalForm(44).getFrame();
    }
    
    public GoodDetailedJframe() throws Exception {
        this.goodDetailedJpanel = new GoodDetailedJpanel();
        this.getContentPane().add(this.goodDetailedJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBounds(80, 50, 656, 497);
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
                if (GoodDetailedJframe.this.isVisible()) {
                    int x = e.getX() - GoodDetailedJframe.this.first_x;
                    int y = e.getY() - GoodDetailedJframe.this.first_y;
                    GoodDetailedJframe.this.setBounds(x + GoodDetailedJframe.this.getX(), y + GoodDetailedJframe.this.getY(), GoodDetailedJframe.this.getWidth(), GoodDetailedJframe.this.getHeight());
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
            FormsManagement.HideForm(44);
        }
        else {
            FormsManagement.Switchinglevel(44);
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
    
    public GoodDetailedJpanel getGoodDetailedJpanel() {
        return this.goodDetailedJpanel;
    }
    
    public void setGoodDetailedJpanel(GoodDetailedJpanel goodDetailedJpanel) {
        this.goodDetailedJpanel = goodDetailedJpanel;
    }
}
