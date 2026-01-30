package org.come.Frame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.TradeJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class TradeJframe extends JInternalFrame implements MouseListener
{
    private TradeJpanel tradejpanel;
    private int first_x;
    private int first_y;
    
    public static TradeJframe getTradejframe() {
        return (TradeJframe)FormsManagement.getInternalForm(15).getFrame();
    }
    
    public TradeJframe() throws Exception {
        this.setContentPane(this.tradejpanel = new TradeJpanel(this));
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBackground(UIUtils.Color_BACK);
        this.setBounds(639, 70, 330, 450);
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
                if (TradeJframe.this.isVisible()) {
                    int x = e.getX() - TradeJframe.this.first_x;
                    int y = e.getY() - TradeJframe.this.first_y;
                    TradeJframe.this.setBounds(x + TradeJframe.this.getX(), y + TradeJframe.this.getY(), TradeJframe.this.getWidth(), TradeJframe.this.getHeight());
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
            FormsManagement.HideForm(15);
            FormsManagement.HideForm(16);
        }
        else {
            FormsManagement.Switchinglevel(15);
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
    
    public TradeJpanel getTradejpanel() {
        return this.tradejpanel;
    }
    
    public void setTradejpanel(TradeJpanel tradejpanel) {
        this.tradejpanel = tradejpanel;
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
