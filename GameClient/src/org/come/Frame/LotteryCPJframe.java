package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.LotteryCPJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class LotteryCPJframe extends JInternalFrame implements MouseListener
{
    private static LotteryCPJpanel lotteryCPJpanel;
    private int first_x;
    private int first_y;
    
    public static LotteryCPJframe getRankingListJframe() {
        return (LotteryCPJframe)FormsManagement.getInternalForm(60).getFrame();
    }
    
    public LotteryCPJframe() {
        LotteryCPJframe.lotteryCPJpanel = new LotteryCPJpanel();
        this.getContentPane().add(LotteryCPJframe.lotteryCPJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBounds(150, 100, 605, 400);
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
                if (LotteryCPJframe.this.isVisible()) {
                    int x = e.getX() - LotteryCPJframe.this.first_x;
                    int y = e.getY() - LotteryCPJframe.this.first_y;
                    LotteryCPJframe.this.setBounds(x + LotteryCPJframe.this.getX(), y + LotteryCPJframe.this.getY(), LotteryCPJframe.this.getWidth(), LotteryCPJframe.this.getHeight());
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
            FormsManagement.HideForm(1105);
        }
        else {
            FormsManagement.Switchinglevel(1105);
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
    
    public static LotteryCPJpanel getLotteryCPJpanel() {
        return LotteryCPJframe.lotteryCPJpanel;
    }
    
    public void setEventCalendarJpanel(LotteryCPJpanel lotteryCPJpanel) {
        LotteryCPJframe.lotteryCPJpanel = lotteryCPJpanel;
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
