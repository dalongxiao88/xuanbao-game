package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.RechargeVIPJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class RechargeVIPJframe extends JInternalFrame implements MouseListener
{
    private static RechargeVIPJpanel rechargeVIPJpanel;
    private int first_x;
    private int first_y;
    
    public static RechargeVIPJframe getRankingListJframe() {
        return (RechargeVIPJframe)FormsManagement.getInternalForm(60).getFrame();
    }
    
    public RechargeVIPJframe() {
        RechargeVIPJframe.rechargeVIPJpanel = new RechargeVIPJpanel();
        this.getContentPane().add(RechargeVIPJframe.rechargeVIPJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBounds(150, 100, 574, 356);
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
                if (RechargeVIPJframe.this.isVisible()) {
                    int x = e.getX() - RechargeVIPJframe.this.first_x;
                    int y = e.getY() - RechargeVIPJframe.this.first_y;
                    RechargeVIPJframe.this.setBounds(x + RechargeVIPJframe.this.getX(), y + RechargeVIPJframe.this.getY(), RechargeVIPJframe.this.getWidth(), RechargeVIPJframe.this.getHeight());
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
            FormsManagement.HideForm(3002);
        }
        else {
            FormsManagement.Switchinglevel(3002);
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
    
    public static RechargeVIPJpanel getRechargeVIPJpanel() {
        return RechargeVIPJframe.rechargeVIPJpanel;
    }
    
    public void setRechargeVIPJpanel(RechargeVIPJpanel rechargeVIPJpanel) {
        RechargeVIPJframe.rechargeVIPJpanel = rechargeVIPJpanel;
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
