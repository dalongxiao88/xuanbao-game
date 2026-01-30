package org.come.Frame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.GiveJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class GiveJframe extends JInternalFrame implements MouseListener
{
    private GiveJpanel givejpanel;
    private int first_x;
    private int first_y;
    
    public static GiveJframe getGivejframe() {
        return (GiveJframe)FormsManagement.getInternalForm(12).getFrame();
    }
    
    public GiveJframe() throws Exception {
        this.givejpanel = new GiveJpanel();
        this.getContentPane().add(this.givejpanel);
        this.setBounds(50, 70, 100, 50);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setFocusable(true);
        this.pack();
        this.setDefaultCloseOperation(3);
        this.setVisible(false);
        this.setBackground(UIUtils.Color_BACK);
        this.addMouseListener(this);
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (GiveJframe.this.isVisible()) {
                    int x = e.getX() - GiveJframe.this.first_x;
                    int y = e.getY() - GiveJframe.this.first_y;
                    GiveJframe.this.setBounds(x + GiveJframe.this.getX(), y + GiveJframe.this.getY(), GiveJframe.this.getWidth(), GiveJframe.this.getHeight());
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
            FormsManagement.HideForm(12);
        }
        else {
            FormsManagement.Switchinglevel(12);
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
    
    public GiveJpanel getGivejpanel() {
        return this.givejpanel;
    }
    
    public void setGivejpanel(GiveJpanel givejpanel) {
        this.givejpanel = givejpanel;
    }
}
