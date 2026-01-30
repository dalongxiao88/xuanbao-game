package org.wing.panel;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class WingMainFrame extends JInternalFrame implements MouseListener
{
    private int first_x;
    private int first_y;
    private WingMainPanel wingMainPanel;
    
    public static WingMainFrame getWingMainFrame() {
        return (WingMainFrame)FormsManagement.getInternalForm(86).getFrame();
    }
    
    public WingMainFrame() {
        this.add(this.wingMainPanel = new WingMainPanel());
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBounds(200, 60, 561, 537);
        this.setOpaque(false);
        this.pack();
        this.setBackground(UIUtils.Color_BACK);
        this.setVisible(false);
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (WingMainFrame.this.isVisible()) {
                    int x = e.getX() - WingMainFrame.this.first_x;
                    int y = e.getY() - WingMainFrame.this.first_y;
                    WingMainFrame.this.setBounds(x + WingMainFrame.this.getX(), y + WingMainFrame.this.getY(), WingMainFrame.this.getWidth(), WingMainFrame.this.getHeight());
                }
            }
        });
        this.addMouseListener(this);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        Music.addyinxiao("关闭窗口.mp3");
        if (e.isMetaDown()) {
            FormsManagement.HideForm(86);
        }
        else {
            FormsManagement.Switchinglevel(86);
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
    
    public WingMainPanel getWingMainPanel() {
        return this.wingMainPanel;
    }
    
    public void setWingMainPanel(WingMainPanel wingMainPanel) {
        this.wingMainPanel = wingMainPanel;
    }
}
