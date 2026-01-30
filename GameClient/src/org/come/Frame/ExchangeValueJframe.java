package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.ExchangeValueJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class ExchangeValueJframe extends JInternalFrame implements MouseListener
{
    private ExchangeValueJpanel valueJpanel;
    private int first_x;
    private int first_y;
    
    public static ExchangeValueJframe getExchangeValueJframe() {
        return (ExchangeValueJframe)FormsManagement.getInternalForm(63).getFrame();
    }
    
    public ExchangeValueJframe() {
        this.valueJpanel = new ExchangeValueJpanel();
        this.getContentPane().add(this.valueJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBounds(200, 80, 536, 542);
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
                if (ExchangeValueJframe.this.isVisible()) {
                    int x = e.getX() - ExchangeValueJframe.this.first_x;
                    int y = e.getY() - ExchangeValueJframe.this.first_y;
                    ExchangeValueJframe.this.setBounds(x + ExchangeValueJframe.this.getX(), y + ExchangeValueJframe.this.getY(), ExchangeValueJframe.this.getWidth(), ExchangeValueJframe.this.getHeight());
                }
            }
        });
    }
    
    public ExchangeValueJpanel getValueJpanel() {
        return this.valueJpanel;
    }
    
    public void setValueJpanel(ExchangeValueJpanel valueJpanel) {
        this.valueJpanel = valueJpanel;
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
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        Music.addyinxiao("关闭窗口.mp3");
        if (e.isMetaDown()) {
            FormsManagement.HideForm(63);
        }
        else {
            FormsManagement.Switchinglevel(63);
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
}
