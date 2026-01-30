package org.soaring.panel;

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

public class SoaringMainFrame extends JInternalFrame implements MouseListener
{
    private int first_x;
    private int first_y;
    private SoaringPanel soaringPanel;
    
    public static SoaringMainFrame getSoaringMainFrame() {
        return (SoaringMainFrame)FormsManagement.getInternalForm(81).getFrame();
    }
    
    public SoaringMainFrame() {
        this.add(this.soaringPanel = new SoaringPanel());
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBounds(400, 100, 332, 407);
        this.setOpaque(false);
        this.setBackground(UIUtils.Color_BACK);
        this.pack();
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (SoaringMainFrame.this.isVisible()) {
                    int x = e.getX() - SoaringMainFrame.this.first_x;
                    int y = e.getY() - SoaringMainFrame.this.first_y;
                    SoaringMainFrame.this.setBounds(x + SoaringMainFrame.this.getX(), y + SoaringMainFrame.this.getY(), SoaringMainFrame.this.getWidth(), SoaringMainFrame.this.getHeight());
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
            FormsManagement.HideForm(81);
        }
        else {
            FormsManagement.Switchinglevel(81);
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
    
    public SoaringPanel getSoaringPanel() {
        return this.soaringPanel;
    }
    
    public void setSoaringPanel(SoaringPanel soaringPanel) {
        this.soaringPanel = soaringPanel;
    }
}
