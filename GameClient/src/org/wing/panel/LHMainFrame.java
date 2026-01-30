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

public class LHMainFrame extends JInternalFrame implements MouseListener
{
    private int first_x;
    private int first_y;
    private LHMainPanel lhMainPanel;
    
    public static LHMainFrame LHMainFrame() {
        return (LHMainFrame)FormsManagement.getInternalForm(866).getFrame();
    }
    
    public LHMainFrame() {
        this.add(this.lhMainPanel = new LHMainPanel());
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
                if (LHMainFrame.this.isVisible()) {
                    int x = e.getX() - LHMainFrame.this.first_x;
                    int y = e.getY() - LHMainFrame.this.first_y;
                    LHMainFrame.this.setBounds(x + LHMainFrame.this.getX(), y + LHMainFrame.this.getY(), LHMainFrame.this.getWidth(), LHMainFrame.this.getHeight());
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
            FormsManagement.HideForm(866);
        }
        else {
            FormsManagement.Switchinglevel(866);
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
    
    public LHMainPanel getLhMainPanel() {
        return this.lhMainPanel;
    }
    
    public void setLhMainPanel(LHMainPanel lhMainPanel) {
        this.lhMainPanel = lhMainPanel;
    }
}
