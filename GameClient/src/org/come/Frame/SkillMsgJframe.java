package org.come.Frame;

import org.come.until.Music;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.Color;
import org.come.until.FormsManagement;
import org.come.Jpanel.SkillMsgJpaenl;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class SkillMsgJframe extends JInternalFrame implements MouseListener
{
    private SkillMsgJpaenl skillMsgJpaenl;
    private int first_x;
    private int first_y;
    
    public static SkillMsgJframe getSkillMsgJframe() {
        return (SkillMsgJframe)FormsManagement.getInternalForm(34).getFrame();
    }
    
    public SkillMsgJframe() {
        this.add(this.skillMsgJpaenl = new SkillMsgJpaenl());
        this.addMouseListener(this);
        this.setBackground(new Color(0, 0, 0, 0));
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (SkillMsgJframe.this.isVisible()) {
                    int x = e.getX() - SkillMsgJframe.this.first_x;
                    int y = e.getY() - SkillMsgJframe.this.first_y;
                    SkillMsgJframe.this.setBounds(x + SkillMsgJframe.this.getX(), y + SkillMsgJframe.this.getY(), SkillMsgJframe.this.getWidth(), SkillMsgJframe.this.getHeight());
                }
            }
        });
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBounds(854, 160, 420, 350);
        this.pack();
        this.setVisible(false);
        this.setDefaultCloseOperation(3);
    }
    
    public static void main(String[] args) {
        new SkillMsgJframe();
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        FormsManagement.showForm(289);
        Music.addyinxiao("关闭窗口.mp3");
        if (e.isMetaDown()) {
            FormsManagement.HideForm(46);
            FormsManagement.HideForm(34);
            FormsManagement.HideForm(558);
            FormsManagement.HideForm(631);
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
    
    public SkillMsgJpaenl getSkillMsgJpaenl() {
        return this.skillMsgJpaenl;
    }
    
    public void setSkillMsgJpaenl(SkillMsgJpaenl skillMsgJpaenl) {
        this.skillMsgJpaenl = skillMsgJpaenl;
    }
}
