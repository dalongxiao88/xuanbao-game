package org.come.Frame.spot;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.spot.box.SpotBoxJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class SpotBoxJframe extends JInternalFrame implements MouseListener
{
    private SpotBoxJpanel spotBoxJpanel;
    private int first_x;
    private int first_y;
    
    public static SpotBoxJframe getSpotBoxJframe() {
        return (SpotBoxJframe)FormsManagement.getInternalForm(800).getFrame();
    }
    
    public SpotBoxJframe() throws Exception {
        this.setContentPane(this.spotBoxJpanel = new SpotBoxJpanel());
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBackground(UIUtils.Color_BACK);
        this.setBounds(380, 70, 659, 466);
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
                if (SpotBoxJframe.this.isVisible()) {
                    int x = e.getX() - SpotBoxJframe.this.first_x;
                    int y = e.getY() - SpotBoxJframe.this.first_y;
                    SpotBoxJframe.this.setBounds(x + SpotBoxJframe.this.getX(), y + SpotBoxJframe.this.getY(), SpotBoxJframe.this.getWidth(), SpotBoxJframe.this.getHeight());
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
            FormsManagement.HideForm(800);
        }
        else {
            FormsManagement.Switchinglevel(800);
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
    
    public SpotBoxJpanel getSpotBoxJpanel() {
        return this.spotBoxJpanel;
    }
}
