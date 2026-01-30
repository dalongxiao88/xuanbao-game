package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.PartnerArenaMainPanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class PartnerArenaJframe extends JInternalFrame implements MouseListener
{
    private PartnerArenaMainPanel partnerArenaMainPanel;
    private int first_x;
    private int first_y;
    
    public static PartnerArenaJframe getPartnerArenaJframe() {
        return (PartnerArenaJframe)FormsManagement.getInternalForm(5).getFrame();
    }
    
    public PartnerArenaJframe() {
        this.add(this.partnerArenaMainPanel = new PartnerArenaMainPanel());
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBounds(300, 150, 300, 210);
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
                if (PartnerArenaJframe.this.isVisible()) {
                    int x = e.getX() - PartnerArenaJframe.this.first_x;
                    int y = e.getY() - PartnerArenaJframe.this.first_y;
                    PartnerArenaJframe.this.setBounds(x + PartnerArenaJframe.this.getX(), y + PartnerArenaJframe.this.getY(), PartnerArenaJframe.this.getWidth(), PartnerArenaJframe.this.getHeight());
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
            FormsManagement.HideForm(5);
        }
        else {
            FormsManagement.Switchinglevel(5);
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
    
    public PartnerArenaMainPanel getPartnerArenaMainPanel() {
        return this.partnerArenaMainPanel;
    }
    
    public void setPartnerArenaMainPanel(PartnerArenaMainPanel partnerArenaMainPanel) {
        this.partnerArenaMainPanel = partnerArenaMainPanel;
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
