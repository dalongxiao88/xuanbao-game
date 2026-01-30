package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.PartnerArenaExchangePanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class PartnerArenaExchangeJframe extends JInternalFrame implements MouseListener
{
    private PartnerArenaExchangePanel partnerArenaExchangePanel;
    private int first_x;
    private int first_y;
    
    public static PartnerArenaExchangeJframe getPartnerArenaExchangeJframe() {
        return (PartnerArenaExchangeJframe)FormsManagement.getInternalForm(107).getFrame();
    }
    
    public PartnerArenaExchangeJframe() {
        this.add(this.partnerArenaExchangePanel = new PartnerArenaExchangePanel());
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
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
                if (PartnerArenaExchangeJframe.this.isVisible()) {
                    int x = e.getX() - PartnerArenaExchangeJframe.this.first_x;
                    int y = e.getY() - PartnerArenaExchangeJframe.this.first_y;
                    PartnerArenaExchangeJframe.this.setBounds(x + PartnerArenaExchangeJframe.this.getX(), y + PartnerArenaExchangeJframe.this.getY(), PartnerArenaExchangeJframe.this.getWidth(), PartnerArenaExchangeJframe.this.getHeight());
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
            FormsManagement.HideForm(107);
        }
        else {
            FormsManagement.Switchinglevel(107);
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
    
    public PartnerArenaExchangePanel getPartnerArenaExchangePanel() {
        return this.partnerArenaExchangePanel;
    }
    
    public void setPartnerArenaExchangePanel(PartnerArenaExchangePanel partnerArenaExchangePanel) {
        this.partnerArenaExchangePanel = partnerArenaExchangePanel;
    }
}
