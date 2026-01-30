package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.AircraftJPanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class AircraftJframe extends JInternalFrame implements MouseListener
{
    private AircraftJPanel aircraftJPanel;
    private int first_x;
    private int first_y;
    
    public static AircraftJframe getAircraftJframe() {
        return (AircraftJframe)FormsManagement.getInternalForm(119).getFrame();
    }
    
    public AircraftJframe() throws Exception {
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setContentPane(this.aircraftJPanel = new AircraftJPanel());
        this.setBackground(UIUtils.Color_BACK);
        this.setBounds(40, 100, 676, 467);
        this.pack();
        this.setDefaultCloseOperation(3);
        this.setVisible(false);
        this.addMouseListener(this);
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (AircraftJframe.this.isVisible()) {
                    int x = e.getX() - AircraftJframe.this.first_x;
                    int y = e.getY() - AircraftJframe.this.first_y;
                    AircraftJframe.this.setBounds(x + AircraftJframe.this.getX(), y + AircraftJframe.this.getY(), AircraftJframe.this.getWidth(), AircraftJframe.this.getHeight());
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
            FormsManagement.HideForm(119);
        }
        else {
            FormsManagement.Switchinglevel(119);
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
    
    public AircraftJPanel getAircraftJPanel() {
        return this.aircraftJPanel;
    }
    
    public void setAircraftJPanel(AircraftJPanel aircraftJPanel) {
        this.aircraftJPanel = aircraftJPanel;
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
    
    public AircraftJPanel getaircraftJPanel() {
        return this.aircraftJPanel;
    }
    
    public void setaircraftJPanel(AircraftJPanel aircraftJPanel) {
        this.aircraftJPanel = aircraftJPanel;
    }
}
