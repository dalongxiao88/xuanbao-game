package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.TryOntxJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class TryOntxJframe extends JInternalFrame implements MouseListener
{
    private TryOntxJpanel ontxJpanel;
    private int first_x;
    private int first_y;
    
    public static TryOntxJframe getTryOntxJframe() {
        return (TryOntxJframe)FormsManagement.getInternalForm(51).getFrame();
    }
    
    public TryOntxJframe() {
        this.ontxJpanel = new TryOntxJpanel();
        this.getContentPane().add(this.ontxJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBounds(335, 126, 385, 395);
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
                if (TryOntxJframe.this.isVisible()) {
                    int x = e.getX() - TryOntxJframe.this.first_x;
                    int y = e.getY() - TryOntxJframe.this.first_y;
                    TryOntxJframe.this.setBounds(x + TryOntxJframe.this.getX(), y + TryOntxJframe.this.getY(), TryOntxJframe.this.getWidth(), TryOntxJframe.this.getHeight());
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
            FormsManagement.HideForm(51);
        }
        else {
            FormsManagement.Switchinglevel(51);
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
    
    public TryOntxJpanel getOntxJpanel() {
        return this.ontxJpanel;
    }
    
    public void setOntxJpanel(TryOntxJpanel ontxJpanel) {
        this.ontxJpanel = ontxJpanel;
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
