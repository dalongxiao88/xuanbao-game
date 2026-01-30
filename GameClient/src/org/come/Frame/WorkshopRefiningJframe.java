package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.WorkshopRefiningJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class WorkshopRefiningJframe extends JInternalFrame implements MouseListener
{
    private WorkshopRefiningJpanel workshopRefiningJpanel;
    private int first_x;
    private int first_y;
    
    public static WorkshopRefiningJframe getWorkshopRefiningJframe() {
        return (WorkshopRefiningJframe)FormsManagement.getInternalForm(61).getFrame();
    }
    
    public WorkshopRefiningJframe() {
        this.workshopRefiningJpanel = new WorkshopRefiningJpanel();
        this.getContentPane().add(this.workshopRefiningJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBounds(100, 50, 536, 542);
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
                if (WorkshopRefiningJframe.this.isVisible()) {
                    int x = e.getX() - WorkshopRefiningJframe.this.first_x;
                    int y = e.getY() - WorkshopRefiningJframe.this.first_y;
                    WorkshopRefiningJframe.this.setBounds(x + WorkshopRefiningJframe.this.getX(), y + WorkshopRefiningJframe.this.getY(), WorkshopRefiningJframe.this.getWidth(), WorkshopRefiningJframe.this.getHeight());
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
            FormsManagement.HideForm(61);
        }
        else {
            FormsManagement.Switchinglevel(61);
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
    
    public WorkshopRefiningJpanel getWorkshopRefiningJpanel() {
        return this.workshopRefiningJpanel;
    }
    
    public void setWorkshopRefiningJpanel(WorkshopRefiningJpanel workshopRefiningJpanel) {
        this.workshopRefiningJpanel = workshopRefiningJpanel;
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
