package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.TeamArenaMainJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class TeamArenaMainJframe extends JInternalFrame implements MouseListener
{
    private TeamArenaMainJpanel teamArenaMainJpanel;
    private int first_x;
    private int first_y;
    
    public static TeamArenaMainJframe getTeamArenaMainJframe() {
        return (TeamArenaMainJframe)FormsManagement.getInternalForm(108).getFrame();
    }
    
    public TeamArenaMainJframe() throws Exception {
        this.teamArenaMainJpanel = new TeamArenaMainJpanel();
        this.getContentPane().add(this.teamArenaMainJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBounds(40, 100, 676, 467);
        this.pack();
        this.setBackground(UIUtils.Color_BACK);
        this.setDefaultCloseOperation(3);
        this.setVisible(false);
        this.addMouseListener(this);
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (TeamArenaMainJframe.this.isVisible()) {
                    int x = e.getX() - TeamArenaMainJframe.this.first_x;
                    int y = e.getY() - TeamArenaMainJframe.this.first_y;
                    TeamArenaMainJframe.this.setBounds(x + TeamArenaMainJframe.this.getX(), y + TeamArenaMainJframe.this.getY(), TeamArenaMainJframe.this.getWidth(), TeamArenaMainJframe.this.getHeight());
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
        FormsManagement.Switchinglevel(108);
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
    
    public TeamArenaMainJpanel getTeamArenaMainJpanel() {
        return this.teamArenaMainJpanel;
    }
    
    public void setTeamArenaMainJpanel(TeamArenaMainJpanel teamArenaMainJpanel) {
        this.teamArenaMainJpanel = teamArenaMainJpanel;
    }
    
    public int getFirst_x() {
        return this.first_x;
    }
    
    public void setFirst_x(int first_x) {
        this.first_x = first_x;
    }
    
    public int getFirst_y() {
        return this.first_y;
    }
    
    public void setFirst_y(int first_y) {
        this.first_y = first_y;
    }
}
