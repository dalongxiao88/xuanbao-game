package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.SummoningCompoundJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class SummoningCompoundJframe extends JInternalFrame implements MouseListener
{
    private SummoningCompoundJpanel summoningCompoundJpanel;
    private int first_x;
    private int first_y;
    
    public static SummoningCompoundJframe getSummoningCompoundJframe() {
        return (SummoningCompoundJframe)FormsManagement.getInternalForm(36).getFrame();
    }
    
    public SummoningCompoundJframe() {
        this.add(this.summoningCompoundJpanel = new SummoningCompoundJpanel());
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBounds(210, 170, 456, 372);
        this.setOpaque(false);
        this.pack();
        this.setBackground(UIUtils.Color_BACK);
        this.setVisible(false);
        this.setDefaultCloseOperation(3);
        this.addMouseListener(this);
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (SummoningCompoundJframe.this.isVisible()) {
                    int x = e.getX() - SummoningCompoundJframe.this.first_x;
                    int y = e.getY() - SummoningCompoundJframe.this.first_y;
                    SummoningCompoundJframe.this.setBounds(x + SummoningCompoundJframe.this.getX(), y + SummoningCompoundJframe.this.getY(), SummoningCompoundJframe.this.getWidth(), SummoningCompoundJframe.this.getHeight());
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
            FormsManagement.HideForm(36);
        }
        else {
            FormsManagement.Switchinglevel(36);
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
    
    public SummoningCompoundJpanel getSummoningCompoundJpanel() {
        return this.summoningCompoundJpanel;
    }
    
    public void setSummoningCompoundJpanel(SummoningCompoundJpanel summoningCompoundJpanel) {
        this.summoningCompoundJpanel = summoningCompoundJpanel;
    }
}
