package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.PartnerMainJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class PartnerJframe extends JInternalFrame implements MouseListener
{
    private PartnerMainJpanel partnerMainJpanel;
    private int first_x;
    private int first_y;
    
    public static PartnerJframe getPartnerJframe() {
        return (PartnerJframe)FormsManagement.getInternalForm(105).getFrame();
    }
    
    public PartnerJframe() {
        this.add(this.partnerMainJpanel = new PartnerMainJpanel());
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
                if (PartnerJframe.this.isVisible()) {
                    int x = e.getX() - PartnerJframe.this.first_x;
                    int y = e.getY() - PartnerJframe.this.first_y;
                    PartnerJframe.this.setBounds(x + PartnerJframe.this.getX(), y + PartnerJframe.this.getY(), PartnerJframe.this.getWidth(), PartnerJframe.this.getHeight());
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
            FormsManagement.HideForm(105);
        }
        else {
            FormsManagement.Switchinglevel(105);
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
    
    public PartnerMainJpanel getPartnerMainJpanel() {
        return this.partnerMainJpanel;
    }
    
    public void setPartnerMainJpanel(PartnerMainJpanel partnerMainJpanel) {
        this.partnerMainJpanel = partnerMainJpanel;
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
