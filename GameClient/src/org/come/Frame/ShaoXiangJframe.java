package org.come.Frame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import com.tool.tcpimg.UIUtils;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.until.FormsManagement;
import org.come.Jpanel.ShaoXiangJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class ShaoXiangJframe extends JInternalFrame implements MouseListener
{
    private ShaoXiangJpanel shaoXiangJpanel;
    private int first_x;
    private int first_y;
    
    public static ShaoXiangJframe getShaoXiangJframe() {
        return (ShaoXiangJframe)FormsManagement.getInternalForm(636).getFrame();
    }
    
    public ShaoXiangJframe(int gheight) {
        String sendmes = Agreement.getAgreement().shaoxiangAgreement("GETLIMIT");
        SendMessageUntil.toServer(sendmes);
        this.add(this.shaoXiangJpanel = new ShaoXiangJpanel());
        this.setBackground(UIUtils.Color_BACK);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBounds(300, 200, 400, gheight);
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
                if (ShaoXiangJframe.this.isVisible()) {
                    int x = e.getX() - ShaoXiangJframe.this.first_x;
                    int y = e.getY() - ShaoXiangJframe.this.first_y;
                    ShaoXiangJframe.this.setBounds(x + ShaoXiangJframe.this.getX(), y + ShaoXiangJframe.this.getY(), ShaoXiangJframe.this.getWidth(), ShaoXiangJframe.this.getHeight());
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
            FormsManagement.HideForm(636);
        }
        else {
            FormsManagement.Switchinglevel(636);
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
    
    public ShaoXiangJpanel getShaoXiangJpanel() {
        return this.shaoXiangJpanel;
    }
    
    public void setShaoXiangJpanel(ShaoXiangJpanel shaoXiangJpanel) {
        this.shaoXiangJpanel = shaoXiangJpanel;
    }
}
