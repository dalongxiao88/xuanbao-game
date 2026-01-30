package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.CollectionJadeJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class CollectionJadeJframe extends JInternalFrame implements MouseListener
{
    private CollectionJadeJpanel jadeJpanel;
    private int first_x;
    private int first_y;
    
    public static CollectionJadeJframe getCollectionJadeJframe() {
        return (CollectionJadeJframe)FormsManagement.getInternalForm(64).getFrame();
    }
    
    public CollectionJadeJframe() {
        this.jadeJpanel = new CollectionJadeJpanel();
        this.getContentPane().add(this.jadeJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBounds(200, 80, 526, 517);
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
                if (CollectionJadeJframe.this.isVisible()) {
                    int x = e.getX() - CollectionJadeJframe.this.first_x;
                    int y = e.getY() - CollectionJadeJframe.this.first_y;
                    CollectionJadeJframe.this.setBounds(x + CollectionJadeJframe.this.getX(), y + CollectionJadeJframe.this.getY(), CollectionJadeJframe.this.getWidth(), CollectionJadeJframe.this.getHeight());
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
            FormsManagement.HideForm(64);
        }
        else {
            FormsManagement.Switchinglevel(64);
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
    
    public CollectionJadeJpanel getJadeJpanel() {
        return this.jadeJpanel;
    }
    
    public void setJadeJpanel(CollectionJadeJpanel jadeJpanel) {
        this.jadeJpanel = jadeJpanel;
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
