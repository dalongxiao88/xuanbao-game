package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.PalacePKJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class PalacePKJframe extends JInternalFrame implements MouseListener
{
    private PalacePKJpanel pkJpanel;
    private int first_x;
    private int first_y;
    
    public static PalacePKJframe getPalacePKJframe() {
        return (PalacePKJframe)FormsManagement.getInternalForm(66).getFrame();
    }
    
    public PalacePKJframe() {
        this.pkJpanel = new PalacePKJpanel();
        this.getContentPane().add(this.pkJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBounds(370, 150, 300, 210);
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
                if (PalacePKJframe.this.isVisible()) {
                    int x = e.getX() - PalacePKJframe.this.first_x;
                    int y = e.getY() - PalacePKJframe.this.first_y;
                    PalacePKJframe.this.setBounds(x + PalacePKJframe.this.getX(), y + PalacePKJframe.this.getY(), PalacePKJframe.this.getWidth(), PalacePKJframe.this.getHeight());
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
            FormsManagement.HideForm(66);
        }
        else {
            FormsManagement.Switchinglevel(66);
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
    
    public PalacePKJpanel getPkJpanel() {
        return this.pkJpanel;
    }
    
    public void setPkJpanel(PalacePKJpanel pkJpanel) {
        this.pkJpanel = pkJpanel;
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
