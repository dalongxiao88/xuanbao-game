package org.come.Frame;

import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.TransJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class TransJframe extends JInternalFrame implements MouseListener
{
    private TransJpanel transJpanel;
    private int first_x;
    private int first_y;
    
    public static TransJframe getTransJframe() {
        return (TransJframe)FormsManagement.getInternalForm(14).getFrame();
    }
    
    public TransJframe() throws Exception {
        this.setContentPane(this.transJpanel = new TransJpanel());
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBounds(110, 70, 536, 497);
        this.pack();
        this.setVisible(false);
        this.setBackground(UIUtils.Color_BACK);
        this.setDefaultCloseOperation(3);
        this.addMouseListener(this);
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (TransJframe.this.isVisible()) {
                    int x = e.getX() - TransJframe.this.first_x;
                    int y = e.getY() - TransJframe.this.first_y;
                    TransJframe.this.setBounds(x + TransJframe.this.getX(), y + TransJframe.this.getY(), TransJframe.this.getWidth(), TransJframe.this.getHeight());
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
            String send = Agreement.getAgreement().TransStateAgreement("2");
            SendMessageUntil.toServer(send);
        }
        else {
            FormsManagement.Switchinglevel(14);
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
    
    public TransJpanel getTransJpanel() {
        return this.transJpanel;
    }
    
    public void setTransJpanel(TransJpanel transJpanel) {
        this.transJpanel = transJpanel;
    }
}
