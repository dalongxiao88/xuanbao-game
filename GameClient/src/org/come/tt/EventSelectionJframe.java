package org.come.tt;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class EventSelectionJframe extends JInternalFrame implements MouseListener
{
    private EventSelectionJpanel eventSelectionJpanel;
    private int first_x;
    private int first_y;
    
    public static EventSelectionJframe getLadderJframe() {
        return (EventSelectionJframe)FormsManagement.getInternalForm(605).getFrame();
    }
    
    public EventSelectionJframe() {
        this.eventSelectionJpanel = new EventSelectionJpanel();
        this.getContentPane().add(this.eventSelectionJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBounds(80, 50, 656, 497);
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
                if (EventSelectionJframe.this.isVisible()) {
                    int x = e.getX() - EventSelectionJframe.this.first_x;
                    int y = e.getY() - EventSelectionJframe.this.first_y;
                    EventSelectionJframe.this.setBounds(x + EventSelectionJframe.this.getX(), y + EventSelectionJframe.this.getY(), EventSelectionJframe.this.getWidth(), EventSelectionJframe.this.getHeight());
                }
            }
        });
    }
    
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        Music.addyinxiao("关闭窗口.mp3");
        if (e.isMetaDown()) {
            FormsManagement.HideForm(605);
        }
        else {
            FormsManagement.Switchinglevel(605);
        }
        this.first_x = e.getX();
        this.first_y = e.getY();
    }
    
    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
    }
    
    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
    }
    
    @Override
    public void mouseExited(MouseEvent mouseEvent) {
    }
    
    public EventSelectionJpanel getEventSelectionJpanel() {
        return this.eventSelectionJpanel;
    }
    
    public void setEventSelectionJpanel(EventSelectionJpanel eventSelectionJpanel) {
        this.eventSelectionJpanel = eventSelectionJpanel;
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
