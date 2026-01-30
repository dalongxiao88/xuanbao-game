package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.SetPasswordJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class SetPassJfram extends JInternalFrame implements MouseListener
{
    private int first_x;
    private int first_y;
    private SetPasswordJpanel setPasswordJpanel;
    
    public static SetPassJfram getSetPassJframe() {
        return (SetPassJfram)FormsManagement.getInternalForm(32).getFrame();
    }
    
    public SetPassJfram() throws Exception {
        this.setPasswordJpanel = new SetPasswordJpanel();
        this.getContentPane().add(this.setPasswordJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBounds(480, 150, 300, 171);
        this.pack();
        this.setDefaultCloseOperation(3);
        this.setVisible(false);
        this.setBackground(UIUtils.Color_BACK);
        this.addMouseListener(this);
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (SetPassJfram.this.isVisible()) {
                    int x = e.getX() - SetPassJfram.this.first_x;
                    int y = e.getY() - SetPassJfram.this.first_y;
                    SetPassJfram.this.setBounds(x + SetPassJfram.this.getX(), y + SetPassJfram.this.getY(), SetPassJfram.this.getWidth(), SetPassJfram.this.getHeight());
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
            FormsManagement.HideForm(32);
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
    
    public SetPasswordJpanel getSetPasswordJpanel() {
        return this.setPasswordJpanel;
    }
    
    public void setSetPasswordJpanel(SetPasswordJpanel setPasswordJpanel) {
        this.setPasswordJpanel = setPasswordJpanel;
    }
}
