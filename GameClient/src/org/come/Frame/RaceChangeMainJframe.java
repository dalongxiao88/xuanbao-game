package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.RaceChangeMainJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class RaceChangeMainJframe extends JInternalFrame implements MouseListener
{
    private RaceChangeMainJpanel raceChangeMainJpanel;
    private int first_x;
    private int first_y;
    
    public static RaceChangeMainJframe getRaceChangeMainJframe() {
        return (RaceChangeMainJframe)FormsManagement.getInternalForm(41).getFrame();
    }
    
    public RaceChangeMainJframe() {
        this.raceChangeMainJpanel = new RaceChangeMainJpanel();
        this.getContentPane().add(this.raceChangeMainJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBounds(180, 20, 640, 558);
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
                if (RaceChangeMainJframe.getRaceChangeMainJframe().isVisible()) {
                    int x = e.getX() - RaceChangeMainJframe.this.first_x;
                    int y = e.getY() - RaceChangeMainJframe.this.first_y;
                    RaceChangeMainJframe.getRaceChangeMainJframe().setBounds(x + RaceChangeMainJframe.getRaceChangeMainJframe().getX(), y + RaceChangeMainJframe.getRaceChangeMainJframe().getY(), RaceChangeMainJframe.getRaceChangeMainJframe().getWidth(), RaceChangeMainJframe.getRaceChangeMainJframe().getHeight());
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
            FormsManagement.HideForm(41);
        }
        else {
            FormsManagement.Switchinglevel(41);
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
    
    public RaceChangeMainJpanel getRaceChangeMainJpanel() {
        return this.raceChangeMainJpanel;
    }
    
    public void setRaceChangeMainJpanel(RaceChangeMainJpanel raceChangeMainJpanel) {
        this.raceChangeMainJpanel = raceChangeMainJpanel;
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
