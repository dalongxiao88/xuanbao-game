package org.come.Frame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.PawnJfpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class PawnJfram extends JInternalFrame implements MouseListener
{
    private PawnJfpanel pawnjpanel;
    private int first_x;
    private int first_y;
    
    public static PawnJfram getPawnJfram() {
        return (PawnJfram)FormsManagement.getInternalForm(29).getFrame();
    }
    
    public PawnJfram() throws Exception {
        this.setTitle("[回忆西游*有兄弟不在寂寞]");
        this.setDefaultCloseOperation(3);
        this.pawnjpanel = new PawnJfpanel();
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBounds(100, 130, 360, 370);
        this.add(this.pawnjpanel);
        this.pack();
        this.setVisible(false);
        this.setBackground(UIUtils.Color_BACK);
        this.addMouseListener(this);
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (PawnJfram.this.isVisible()) {
                    int x = e.getX() - PawnJfram.this.first_x;
                    int y = e.getY() - PawnJfram.this.first_y;
                    PawnJfram.this.setBounds(x + PawnJfram.this.getX(), y + PawnJfram.this.getY(), PawnJfram.this.getWidth(), PawnJfram.this.getHeight());
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
            FormsManagement.HideForm(29);
        }
        else {
            FormsManagement.Switchinglevel(29);
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
    
    public PawnJfpanel getPawnjpanel() {
        return this.pawnjpanel;
    }
    
    public void setPawnjpanel(PawnJfpanel pawnjpanel) {
        this.pawnjpanel = pawnjpanel;
    }
}
