package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.NewRefiningJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class NewRefiningJframe extends JInternalFrame implements MouseListener
{
    private NewRefiningJpanel refiningJpanel;
    private int first_x;
    private int first_y;
    
    public static NewRefiningJframe getNewRefiningJframe() {
        return (NewRefiningJframe)FormsManagement.getInternalForm(11).getFrame();
    }
    
    public NewRefiningJframe() {
        this.refiningJpanel = new NewRefiningJpanel();
        this.getContentPane().add(this.refiningJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBounds(180, 80, 478, 362);
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
                if (NewRefiningJframe.this.isVisible()) {
                    int x = e.getX() - NewRefiningJframe.this.first_x;
                    int y = e.getY() - NewRefiningJframe.this.first_y;
                    NewRefiningJframe.this.setBounds(x + NewRefiningJframe.this.getX(), y + NewRefiningJframe.this.getY(), NewRefiningJframe.this.getWidth(), NewRefiningJframe.this.getHeight());
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
            FormsManagement.HideForm(11);
            NewRefiningJpanel.isLH = false;
        }
        else {
            FormsManagement.Switchinglevel(11);
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
    
    public NewRefiningJpanel getRefiningJpanel() {
        return this.refiningJpanel;
    }
    
    public void setRefiningJpanel(NewRefiningJpanel refiningJpanel) {
        this.refiningJpanel = refiningJpanel;
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
