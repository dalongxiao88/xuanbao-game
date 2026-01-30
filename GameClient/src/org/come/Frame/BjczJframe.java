package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import org.come.until.ScrenceUntil;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.BjczJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class BjczJframe extends JInternalFrame implements MouseListener
{
    private static BjczJpanel bjczJpanel;
    private int first_x;
    private int first_y;
    
    public static BjczJframe getRankingListJframe() {
        return (BjczJframe)FormsManagement.getInternalForm(60).getFrame();
    }
    
    public BjczJframe() {
        BjczJframe.bjczJpanel = new BjczJpanel();
        this.getContentPane().add(BjczJframe.bjczJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBounds(ScrenceUntil.Screen_x / 2 - 398, ScrenceUntil.Screen_y / 2 - 224, 795, 449);
        BjczJframe.bjczJpanel.setBounds(0, 0, 795, 449);
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
                if (BjczJframe.this.isVisible()) {
                    int x = e.getX() - BjczJframe.this.first_x;
                    int y = e.getY() - BjczJframe.this.first_y;
                    BjczJframe.this.setBounds(x + BjczJframe.this.getX(), y + BjczJframe.this.getY(), BjczJframe.this.getWidth(), BjczJframe.this.getHeight());
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
            FormsManagement.HideForm(3000);
        }
        else {
            FormsManagement.Switchinglevel(3000);
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
    
    public static BjczJpanel getbjczJpanel() {
        return BjczJframe.bjczJpanel;
    }
    
    public static void setbjczJpanel(BjczJpanel bjczJpanel) {
        BjczJframe.bjczJpanel = bjczJpanel;
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
