package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.PetSkillsJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class PetSkillsJframe extends JInternalFrame implements MouseListener
{
    private PetSkillsJpanel petSkillsJpanel;
    private int first_x;
    private int first_y;
    
    public static PetSkillsJframe getPetSkillsJframe() {
        return (PetSkillsJframe)FormsManagement.getInternalForm(18).getFrame();
    }
    
    public PetSkillsJframe() throws Exception {
        this.petSkillsJpanel = new PetSkillsJpanel();
        this.getContentPane().add(this.petSkillsJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBackground(UIUtils.Color_BACK);
        this.setBounds(300, 85, 100, 100);
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
                if (PetSkillsJframe.this.isVisible()) {
                    int x = e.getX() - PetSkillsJframe.this.first_x;
                    int y = e.getY() - PetSkillsJframe.this.first_y;
                    PetSkillsJframe.this.setBounds(x + PetSkillsJframe.this.getX(), y + PetSkillsJframe.this.getY(), PetSkillsJframe.this.getWidth(), PetSkillsJframe.this.getHeight());
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
            FormsManagement.HideForm(18);
        }
        else {
            FormsManagement.Switchinglevel(18);
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
    
    public PetSkillsJpanel getPetSkillsJpanel() {
        return this.petSkillsJpanel;
    }
    
    public void setPetSkillsJpanel(PetSkillsJpanel petSkillsJpanel) {
        this.petSkillsJpanel = petSkillsJpanel;
    }
}
