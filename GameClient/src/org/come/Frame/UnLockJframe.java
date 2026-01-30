package org.come.Frame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.UnLockJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class UnLockJframe extends JInternalFrame implements MouseListener
{
    private UnLockJpanel unLockJpanel;
    private int first_x;
    private int first_y;
    
    public static UnLockJframe getUnLockJframe() {
        return (UnLockJframe)FormsManagement.getInternalForm(33).getFrame();
    }
    
    public UnLockJframe() throws Exception {
        this.setContentPane(this.unLockJpanel = new UnLockJpanel());
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBounds(380, 300, 300, 128);
        this.pack();
        this.setVisible(false);
        this.setBackground(UIUtils.Color_BACK);
        this.setDefaultCloseOperation(3);
        this.addMouseListener(this);
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (UnLockJframe.this.isVisible()) {
                    int x = e.getX() - UnLockJframe.this.first_x;
                    int y = e.getY() - UnLockJframe.this.first_y;
                    UnLockJframe.this.setBounds(x + UnLockJframe.this.getX(), y + UnLockJframe.this.getY(), UnLockJframe.this.getWidth(), UnLockJframe.this.getHeight());
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
            FormsManagement.HideForm(33);
        }
        else {
            FormsManagement.Switchinglevel(33);
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
    
    public UnLockJpanel getUnLockJpanel() {
        return this.unLockJpanel;
    }
    
    public void setUnLockJpanel(UnLockJpanel unLockJpanel) {
        this.unLockJpanel = unLockJpanel;
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
