package org.come.Frame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.StallBuyJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class StallBuyJframe extends JInternalFrame implements MouseListener
{
    private StallBuyJpanel stallBuyJpanel;
    private int first_x;
    private int first_y;
    
    public static StallBuyJframe getStallBuyJframe() {
        return (StallBuyJframe)FormsManagement.getInternalForm(35).getFrame();
    }
    
    public StallBuyJframe() throws Exception {
        this.setContentPane(this.stallBuyJpanel = new StallBuyJpanel());
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBounds(380, 70, 345, 427);
        this.pack();
        this.setVisible(false);
        this.setDefaultCloseOperation(3);
        this.setBackground(UIUtils.Color_BACK);
        this.addMouseListener(this);
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (StallBuyJframe.this.isVisible()) {
                    int x = e.getX() - StallBuyJframe.this.first_x;
                    int y = e.getY() - StallBuyJframe.this.first_y;
                    StallBuyJframe.this.setBounds(x + StallBuyJframe.this.getX(), y + StallBuyJframe.this.getY(), StallBuyJframe.this.getWidth(), StallBuyJframe.this.getHeight());
                }
            }
        });
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.isMetaDown()) {
            FormsManagement.HideForm(35);
        }
        else {
            FormsManagement.Switchinglevel(35);
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
    
    public StallBuyJpanel getStallBuyJpanel() {
        return this.stallBuyJpanel;
    }
    
    public void setStallBuyJpanel(StallBuyJpanel stallBuyJpanel) {
        this.stallBuyJpanel = stallBuyJpanel;
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
