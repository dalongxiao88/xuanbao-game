package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.experimentCompoundJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class experimentCompoundJframe extends JInternalFrame implements MouseListener
{
    private experimentCompoundJpanel summoningCompoundJpanel;
    private int first_x;
    private int first_y;
    
    public static experimentCompoundJframe getSummoningCompoundJframe() {
        return (experimentCompoundJframe)FormsManagement.getInternalForm(700).getFrame();
    }
    
    public experimentCompoundJframe() {
        this.add(this.summoningCompoundJpanel = new experimentCompoundJpanel());
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
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
                if (experimentCompoundJframe.this.isVisible()) {
                    int x = e.getX() - experimentCompoundJframe.this.first_x;
                    int y = e.getY() - experimentCompoundJframe.this.first_y;
                    experimentCompoundJframe.this.setBounds(x + experimentCompoundJframe.this.getX(), y + experimentCompoundJframe.this.getY(), experimentCompoundJframe.this.getWidth(), experimentCompoundJframe.this.getHeight());
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
            FormsManagement.HideForm(700);
        }
        else {
            FormsManagement.Switchinglevel(700);
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
    
    public experimentCompoundJpanel getSummoningCompoundJpanel() {
        return this.summoningCompoundJpanel;
    }
    
    public void setSummoningCompoundJpanel(experimentCompoundJpanel summoningCompoundJpanel) {
        this.summoningCompoundJpanel = summoningCompoundJpanel;
    }
}
