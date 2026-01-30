package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.ChangePasswordJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class ChangePasswordJframe extends JInternalFrame implements MouseListener
{
    private ChangePasswordJpanel changePasswordJpanel;
    private int first_x;
    private int first_y;
    
    public static ChangePasswordJframe getChangePasswordJframe() {
        return (ChangePasswordJframe)FormsManagement.getInternalForm(21).getFrame();
    }
    
    public ChangePasswordJframe() throws Exception {
        this.changePasswordJpanel = new ChangePasswordJpanel();
        this.getContentPane().add(this.changePasswordJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBounds(480, 150, 300, 171);
        this.pack();
        this.setDefaultCloseOperation(3);
        this.setBackground(UIUtils.Color_BACK);
        this.setVisible(false);
        this.addMouseListener(this);
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (ChangePasswordJframe.this.isVisible()) {
                    int x = e.getX() - ChangePasswordJframe.this.first_x;
                    int y = e.getY() - ChangePasswordJframe.this.first_y;
                    ChangePasswordJframe.this.setBounds(x + ChangePasswordJframe.this.getX(), y + ChangePasswordJframe.this.getY(), ChangePasswordJframe.this.getWidth(), ChangePasswordJframe.this.getHeight());
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
            FormsManagement.HideForm(21);
        }
        else {
            FormsManagement.Switchinglevel(21);
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
    
    public ChangePasswordJpanel getChangePasswordJpanel() {
        return this.changePasswordJpanel;
    }
    
    public void setChangePasswordJpanel(ChangePasswordJpanel changePasswordJpanel) {
        this.changePasswordJpanel = changePasswordJpanel;
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
