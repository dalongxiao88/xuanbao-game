package org.come.Frame;

import org.come.until.Music;
import org.come.until.MessagrFlagUntil;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.DepositListJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class DepositListJframe extends JInternalFrame implements MouseListener
{
    private DepositListJpanel depositListJpanel;
    private int first_x;
    private int first_y;
    
    public static DepositListJframe getDepositListJframe() {
        return (DepositListJframe)FormsManagement.getInternalForm(63333).getFrame();
    }
    
    public DepositListJframe() {
        this.depositListJpanel = new DepositListJpanel();
        this.getContentPane().add(this.depositListJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBounds(100, 50, 230, 320);
        this.setBackground(UIUtils.Color_BACK);
        this.pack();
        this.setVisible(false);
        this.setDefaultCloseOperation(3);
        this.addMouseListener(this);
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if (MessagrFlagUntil.ImgFlagImg.equals(MessagrFlagUntil.MOUSE13)) {
                    MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
                }
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (DepositListJframe.this.isVisible()) {
                    int x = e.getX() - DepositListJframe.this.first_x;
                    int y = e.getY() - DepositListJframe.this.first_y;
                    DepositListJframe.this.setBounds(x + DepositListJframe.this.getX(), y + DepositListJframe.this.getY(), DepositListJframe.this.getWidth(), DepositListJframe.this.getHeight());
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
            FormsManagement.HideForm(63333);
        }
        else {
            FormsManagement.Switchinglevel(63333);
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
    
    public DepositListJpanel getDepositListJpanel() {
        return this.depositListJpanel;
    }
    
    public void setDepositListJpanel(DepositListJpanel depositListJpanel) {
        this.depositListJpanel = depositListJpanel;
    }
}
