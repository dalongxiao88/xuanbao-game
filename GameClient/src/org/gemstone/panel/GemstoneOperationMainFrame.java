package org.gemstone.panel;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class GemstoneOperationMainFrame extends JInternalFrame implements MouseListener
{
    private int first_x;
    private int first_y;
    private GemstoneOperationMainPanel gemstoneOperationMainPanel;
    
    public static GemstoneOperationMainFrame getGemstoneOperationMainFrame() {
        return (GemstoneOperationMainFrame)FormsManagement.getInternalForm(85).getFrame();
    }
    
    public GemstoneOperationMainFrame() {
        this.add(this.gemstoneOperationMainPanel = new GemstoneOperationMainPanel());
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBounds(100, 100, 526, 397);
        this.setBackground(UIUtils.Color_BACK);
        this.setVisible(false);
        this.pack();
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (GemstoneOperationMainFrame.this.isVisible()) {
                    int x = e.getX() - GemstoneOperationMainFrame.this.first_x;
                    int y = e.getY() - GemstoneOperationMainFrame.this.first_y;
                    GemstoneOperationMainFrame.this.setBounds(x + GemstoneOperationMainFrame.this.getX(), y + GemstoneOperationMainFrame.this.getY(), GemstoneOperationMainFrame.this.getWidth(), GemstoneOperationMainFrame.this.getHeight());
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
            FormsManagement.HideForm(85);
        }
        else {
            FormsManagement.Switchinglevel(85);
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
    
    public GemstoneOperationMainPanel getGemstoneOperationMainPanel() {
        return this.gemstoneOperationMainPanel;
    }
    
    public void setGemstoneOperationMainPanel(GemstoneOperationMainPanel gemstoneOperationMainPanel) {
        this.gemstoneOperationMainPanel = gemstoneOperationMainPanel;
    }
}
