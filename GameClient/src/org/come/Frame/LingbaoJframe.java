package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.updateNew.MyIsif;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.LingbaoJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class LingbaoJframe extends JInternalFrame implements MouseListener
{
    private LingbaoJpanel lingbaoJpanel;
    private int first_x;
    private int first_y;
    
    public static LingbaoJframe getLingbaoJframe() {
        return (LingbaoJframe)FormsManagement.getInternalForm(43).getFrame();
    }
    
    public LingbaoJframe() throws Exception {
        this.lingbaoJpanel = new LingbaoJpanel(this);
        this.getContentPane().add(this.lingbaoJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setOpaque(false);
        if (MyIsif.getStyle().equals("水墨")) {
            this.setBounds(100, 35, 538, 469);
        }
        else {
            this.setBounds(100, 35, 512, 496);
        }
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
                if (LingbaoJframe.this.isVisible()) {
                    int x = e.getX() - LingbaoJframe.this.first_x;
                    int y = e.getY() - LingbaoJframe.this.first_y;
                    LingbaoJframe.this.setBounds(x + LingbaoJframe.this.getX(), y + LingbaoJframe.this.getY(), LingbaoJframe.this.getWidth(), LingbaoJframe.this.getHeight());
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
            FormsManagement.HideForm(43);
        }
        else {
            FormsManagement.Switchinglevel(43);
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
    
    public LingbaoJpanel getLingbaoJpanel() {
        return this.lingbaoJpanel;
    }
    
    public void setLingbaoJpanel(LingbaoJpanel lingbaoJpanel) {
        this.lingbaoJpanel = lingbaoJpanel;
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
