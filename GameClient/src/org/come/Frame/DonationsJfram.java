package org.come.Frame;

import org.come.until.Music;
import com.tool.tcpimg.UIUtils;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.DonationsJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class DonationsJfram extends JInternalFrame implements MouseListener
{
    private DonationsJpanel donationsJpanel;
    private int first_x;
    private int first_y;
    
    public DonationsJpanel getDonationsJpanel() {
        return this.donationsJpanel;
    }
    
    public void setDonationsJpanel(DonationsJpanel donationsJpanel) {
        this.donationsJpanel = donationsJpanel;
    }
    
    public static DonationsJfram getdonationJfram() {
        return (DonationsJfram)FormsManagement.getInternalForm(112).getFrame();
    }
    
    public DonationsJfram() throws Exception {
        this.donationsJpanel = new DonationsJpanel();
        this.getContentPane().add(this.donationsJpanel);
        this.setBounds(300, 200, 296, 257);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.addMouseListener(this);
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (DonationsJfram.this.isVisible()) {
                    int x = e.getX() - DonationsJfram.this.first_x;
                    int y = e.getY() - DonationsJfram.this.first_y;
                    DonationsJfram.this.setBounds(x + DonationsJfram.this.getX(), y + DonationsJfram.this.getY(), DonationsJfram.this.getWidth(), DonationsJfram.this.getHeight());
                }
            }
        });
        this.setBackground(UIUtils.Color_BACK);
        this.pack();
        this.setVisible(false);
        this.setDefaultCloseOperation(2);
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        Music.addyinxiao("关闭窗口.mp3");
        if (e.isMetaDown()) {
            FormsManagement.HideForm(112);
        }
        this.first_x = e.getX();
        this.first_y = e.getY();
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
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
}
