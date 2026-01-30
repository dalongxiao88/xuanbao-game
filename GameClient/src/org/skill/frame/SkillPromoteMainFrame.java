package org.skill.frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.skill.panel.SkillPromoteMainPanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class SkillPromoteMainFrame extends JInternalFrame implements MouseListener
{
    private int first_x;
    private int first_y;
    private SkillPromoteMainPanel skillPromoteMainPanel;
    
    public static SkillPromoteMainFrame getSkillPromoteMainFrame() {
        return (SkillPromoteMainFrame)FormsManagement.getInternalForm(83).getFrame();
    }
    
    public SkillPromoteMainFrame() {
        this.skillPromoteMainPanel = new SkillPromoteMainPanel();
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
                if (SkillPromoteMainFrame.this.isVisible()) {
                    int x = e.getX() - SkillPromoteMainFrame.this.first_x;
                    int y = e.getY() - SkillPromoteMainFrame.this.first_y;
                    SkillPromoteMainFrame.this.setBounds(x + SkillPromoteMainFrame.this.getX(), y + SkillPromoteMainFrame.this.getY(), SkillPromoteMainFrame.this.getWidth(), SkillPromoteMainFrame.this.getHeight());
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
            FormsManagement.HideForm(83);
        }
        else {
            FormsManagement.Switchinglevel(83);
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
    
    public SkillPromoteMainPanel getSkillPromoteMainPanel() {
        return this.skillPromoteMainPanel;
    }
    
    public void setSkillPromoteMainPanel(SkillPromoteMainPanel skillPromoteMainPanel) {
        this.skillPromoteMainPanel = skillPromoteMainPanel;
    }
}
