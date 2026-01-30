package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.skill.panel.LxPanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class PetLxJframe extends JInternalFrame implements MouseListener
{
    private static final long serialVersionUID = -8285327662130024086L;
    private LxPanel lxPanel;
    private int first_x;
    private int first_y;
    
    public static PetLxJframe getPetLxJframe() {
        return (PetLxJframe)FormsManagement.getInternalForm(601).getFrame();
    }
    
    public PetLxJframe() throws Exception {
        this.lxPanel = new LxPanel();
        this.getContentPane().add(this.lxPanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBackground(UIUtils.Color_BACK);
        this.setBounds(100, 85, 100, 100);
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
                if (PetLxJframe.this.isVisible()) {
                    int x = e.getX() - PetLxJframe.this.first_x;
                    int y = e.getY() - PetLxJframe.this.first_y;
                    PetLxJframe.this.setBounds(x + PetLxJframe.this.getX(), y + PetLxJframe.this.getY(), PetLxJframe.this.getWidth(), PetLxJframe.this.getHeight());
                }
            }
        });
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.isMetaDown()) {
            FormsManagement.HideForm(601);
            Music.addyinxiao("关闭窗口.mp3");
        }
        else {
            FormsManagement.Switchinglevel(601);
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
    
    public LxPanel getLxPanel() {
        return this.lxPanel;
    }
    
    public void setLxPanel(LxPanel lxPanel) {
        this.lxPanel = lxPanel;
    }
}
