package org.come.Frame;

import org.come.until.Music;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.Color;
import org.come.until.FormsManagement;
import org.come.Jpanel.SkillMsgJpaenl1;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class SkillMsgJframe1 extends JInternalFrame implements MouseListener
{
    private SkillMsgJpaenl1 skillMsgJpaenl1;
    private int first_x;
    private int first_y;
    
    public static SkillMsgJframe1 getSkillMsgJframe1() {
        return (SkillMsgJframe1)FormsManagement.getInternalForm(631).getFrame();
    }
    
    public SkillMsgJframe1() {
        this.add(this.skillMsgJpaenl1 = new SkillMsgJpaenl1());
        this.addMouseListener(this);
        this.setBackground(new Color(0, 0, 0, 0));
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (SkillMsgJframe1.this.isVisible()) {
                    int x = e.getX() - SkillMsgJframe1.this.first_x;
                    int y = e.getY() - SkillMsgJframe1.this.first_y;
                    SkillMsgJframe1.this.setBounds(x + SkillMsgJframe1.this.getX(), y + SkillMsgJframe1.this.getY(), SkillMsgJframe1.this.getWidth(), SkillMsgJframe1.this.getHeight());
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
        new SkillMsgJframe1();
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
    
    public SkillMsgJpaenl1 getSkillMsgJpaenl1() {
        return this.skillMsgJpaenl1;
    }
    
    public void setSkillMsgJpaenl1(SkillMsgJpaenl1 skillMsgJpaenl1) {
        this.skillMsgJpaenl1 = skillMsgJpaenl1;
    }
}
