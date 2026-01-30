package org.skill.frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.skill.panel.LXiulianMainPanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class LXiulianMainFrame extends JInternalFrame implements MouseListener
{
    private static final long serialVersionUID = 6487538265898556693L;
    private int first_x;
    private int first_y;
    private LXiulianMainPanel skillPromoteMainPanel;
    
    public static LXiulianMainFrame getLXiulianMainFrame() {
        return (LXiulianMainFrame)FormsManagement.getInternalForm(602).getFrame();
    }
    
    public LXiulianMainFrame() {
        this.skillPromoteMainPanel = new LXiulianMainPanel();
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBounds(200, 100, 376, 466);
        this.setBackground(UIUtils.Color_BACK);
        this.add(this.skillPromoteMainPanel);
        this.pack();
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (LXiulianMainFrame.this.isVisible()) {
                    int x = e.getX() - LXiulianMainFrame.this.first_x;
                    int y = e.getY() - LXiulianMainFrame.this.first_y;
                    LXiulianMainFrame.this.setBounds(x + LXiulianMainFrame.this.getX(), y + LXiulianMainFrame.this.getY(), LXiulianMainFrame.this.getWidth(), LXiulianMainFrame.this.getHeight());
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
            FormsManagement.HideForm(602);
        }
        else {
            FormsManagement.Switchinglevel(602);
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
    
    public LXiulianMainPanel getLXiulianMainPanel() {
        return this.skillPromoteMainPanel;
    }
    
    public void setLXiulianMainPanel(LXiulianMainPanel skillPromoteMainPanel) {
        this.skillPromoteMainPanel = skillPromoteMainPanel;
    }
}
