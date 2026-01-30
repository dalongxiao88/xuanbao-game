package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.PlayRulesJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class PlayRulesJframe extends JInternalFrame implements MouseListener
{
    private PlayRulesJpanel rulesJpanel;
    private int first_x;
    private int first_y;
    
    public static PlayRulesJframe getPlayRulesJframe() {
        return (PlayRulesJframe)FormsManagement.getInternalForm(72).getFrame();
    }
    
    public PlayRulesJframe() {
        this.setContentPane(this.rulesJpanel = new PlayRulesJpanel());
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBounds(600, 170, 360, 310);
        this.setOpaque(false);
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
                if (PlayRulesJframe.this.isVisible()) {
                    int x = e.getX() - PlayRulesJframe.this.first_x;
                    int y = e.getY() - PlayRulesJframe.this.first_y;
                    PlayRulesJframe.this.setBounds(x + PlayRulesJframe.this.getX(), y + PlayRulesJframe.this.getY(), PlayRulesJframe.this.getWidth(), PlayRulesJframe.this.getHeight());
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
            FormsManagement.HideForm(72);
        }
        else {
            FormsManagement.Switchinglevel(72);
        }
        Music.addyinxiao("关闭窗口.mp3");
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
}
