package org.skill.frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.skill.panel.SkillMainPanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class SkillMainFrame extends JInternalFrame implements MouseListener
{
    private int first_x;
    private int first_y;
    private SkillMainPanel skillMainPanel;
    
    public static SkillMainFrame getSkillMainFrame() {
        return (SkillMainFrame)FormsManagement.getInternalForm(82).getFrame();
    }
    
    public SkillMainFrame() {
        this.add(this.skillMainPanel = new SkillMainPanel());
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBounds(100, 100, 576, 447);
        this.setBackground(UIUtils.Color_BACK);
        this.setOpaque(false);
        this.setVisible(false);
        this.pack();
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (SkillMainFrame.this.isVisible()) {
                    int x = e.getX() - SkillMainFrame.this.first_x;
                    int y = e.getY() - SkillMainFrame.this.first_y;
                    SkillMainFrame.this.setBounds(x + SkillMainFrame.this.getX(), y + SkillMainFrame.this.getY(), SkillMainFrame.this.getWidth(), SkillMainFrame.this.getHeight());
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
            FormsManagement.HideForm(82);
            FormsManagement.disposeForm(82);
        }
        else {
            FormsManagement.Switchinglevel(82);
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
    
    public SkillMainPanel getSkillMainPanel() {
        return this.skillMainPanel;
    }
    
    public void setSkillMainPanel(SkillMainPanel skillMainPanel) {
        this.skillMainPanel = skillMainPanel;
    }
}
