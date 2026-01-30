package org.come.MountShouHu;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class RandFJframe extends JInternalFrame implements MouseListener
{
    private int first_x;
    private int first_y;
    private RandFJpanel randFJpanel;
    
    public static RandFJframe getRandFJframe() {
        return (RandFJframe)FormsManagement.getInternalForm(2256).getFrame();
    }
    
    public RandFJframe() throws Exception {
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setContentPane(this.randFJpanel = new RandFJpanel());
        this.setBackground(UIUtils.Color_BACK);
        this.setBounds(100, 140, 281, 342);
        this.pack();
        this.setDefaultCloseOperation(3);
        this.setVisible(false);
        this.addMouseListener(this);
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (RandFJframe.this.isVisible()) {
                    int x = e.getX() - RandFJframe.this.first_x;
                    int y = e.getY() - RandFJframe.this.first_y;
                    RandFJframe.this.setBounds(x + RandFJframe.this.getX(), y + RandFJframe.this.getY(), RandFJframe.this.getWidth(), RandFJframe.this.getHeight());
                }
            }
        });
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        FormsManagement.showForm(2256);
        Music.addyinxiao("关闭窗口.mp3");
        if (e.isMetaDown()) {
            FormsManagement.HideForm(2256);
        }
        else {
            FormsManagement.Switchinglevel(2256);
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
    
    public RandFJpanel getRandFJpanel() {
        return this.randFJpanel;
    }
    
    public void setRandFJpanel(RandFJpanel randFJpanel) {
        this.randFJpanel = randFJpanel;
    }
}
