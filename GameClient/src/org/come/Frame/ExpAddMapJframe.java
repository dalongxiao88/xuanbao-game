package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.ExpAddMapJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class ExpAddMapJframe extends JInternalFrame implements MouseListener
{
    private static ExpAddMapJpanel expAddMapJpanel;
    private int first_x;
    private int first_y;
    
    public static ExpAddMapJframe getRankingListJframe() {
        return (ExpAddMapJframe)FormsManagement.getInternalForm(60).getFrame();
    }
    
    public ExpAddMapJframe() {
        ExpAddMapJframe.expAddMapJpanel = new ExpAddMapJpanel();
        this.getContentPane().add(ExpAddMapJframe.expAddMapJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBounds(100, 100, 684, 455);
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
                if (ExpAddMapJframe.this.isVisible()) {
                    int x = e.getX() - ExpAddMapJframe.this.first_x;
                    int y = e.getY() - ExpAddMapJframe.this.first_y;
                    ExpAddMapJframe.this.setBounds(x + ExpAddMapJframe.this.getX(), y + ExpAddMapJframe.this.getY(), ExpAddMapJframe.this.getWidth(), ExpAddMapJframe.this.getHeight());
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
            FormsManagement.HideForm(3003);
        }
        else {
            FormsManagement.Switchinglevel(3003);
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
    
    public static ExpAddMapJpanel getExpAddMapJpanel() {
        return ExpAddMapJframe.expAddMapJpanel;
    }
    
    public static void setExpAddMapJpanel(ExpAddMapJpanel expAddMapJpanel) {
        ExpAddMapJframe.expAddMapJpanel = expAddMapJpanel;
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
