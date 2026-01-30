package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.AlreadyRecordedJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class AlreadyRecordedJframe extends JInternalFrame implements MouseListener
{
    private AlreadyRecordedJpanel recordedJpanel;
    private int first_x;
    private int first_y;
    
    public static AlreadyRecordedJframe getAlreadyRecordedJframe() {
        return (AlreadyRecordedJframe)FormsManagement.getInternalForm(65).getFrame();
    }
    
    public AlreadyRecordedJframe() {
        this.recordedJpanel = new AlreadyRecordedJpanel();
        this.getContentPane().add(this.recordedJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBounds(200, 80, 526, 475);
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
                if (AlreadyRecordedJframe.this.isVisible()) {
                    int x = e.getX() - AlreadyRecordedJframe.this.first_x;
                    int y = e.getY() - AlreadyRecordedJframe.this.first_y;
                    AlreadyRecordedJframe.this.setBounds(x + AlreadyRecordedJframe.this.getX(), y + AlreadyRecordedJframe.this.getY(), AlreadyRecordedJframe.this.getWidth(), AlreadyRecordedJframe.this.getHeight());
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
            FormsManagement.HideForm(65);
        }
        else {
            FormsManagement.Switchinglevel(65);
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
    
    public AlreadyRecordedJpanel getRecordedJpanel() {
        return this.recordedJpanel;
    }
    
    public void setRecordedJpanel(AlreadyRecordedJpanel recordedJpanel) {
        this.recordedJpanel = recordedJpanel;
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
