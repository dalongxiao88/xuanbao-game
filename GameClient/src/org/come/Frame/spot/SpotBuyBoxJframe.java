package org.come.Frame.spot;

import org.come.until.UserStallUntil;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.spot.box.SpotBuyBoxJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class SpotBuyBoxJframe extends JInternalFrame implements MouseListener
{
    private SpotBuyBoxJpanel spotBuyBoxJpanel;
    private int first_x;
    private int first_y;
    
    public static SpotBuyBoxJframe getSpotBuyBoxJframe() {
        return (SpotBuyBoxJframe)FormsManagement.getInternalForm(801).getFrame();
    }
    
    public SpotBuyBoxJframe() throws Exception {
        this.setContentPane(this.spotBuyBoxJpanel = new SpotBuyBoxJpanel());
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBackground(UIUtils.Color_BACK);
        this.setBounds(380, 70, 687, 487);
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
                if (SpotBuyBoxJframe.this.isVisible()) {
                    int x = e.getX() - SpotBuyBoxJframe.this.first_x;
                    int y = e.getY() - SpotBuyBoxJframe.this.first_y;
                    SpotBuyBoxJframe.this.setBounds(x + SpotBuyBoxJframe.this.getX(), y + SpotBuyBoxJframe.this.getY(), SpotBuyBoxJframe.this.getWidth(), SpotBuyBoxJframe.this.getHeight());
                }
            }
        });
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.isMetaDown()) {
            UserStallUntil.hideBuyStall();
        }
        else {
            FormsManagement.Switchinglevel(801);
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
    
    public SpotBuyBoxJpanel getSpotBuyBoxJpanel() {
        return this.spotBuyBoxJpanel;
    }
}
