package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.TeamCreateJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class TeamCreateJframe extends JInternalFrame implements MouseListener
{
    private TeamCreateJpanel teamCreateJpanel;
    private int first_x;
    private int first_y;
    
    public static TeamCreateJframe getTeamCreateJframe() {
        return (TeamCreateJframe)FormsManagement.getInternalForm(31).getFrame();
    }
    
    public TeamCreateJframe() throws Exception {
        this.teamCreateJpanel = new TeamCreateJpanel();
        this.getContentPane().add(this.teamCreateJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBounds(40, 100, 333, 277);
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
                if (TeamCreateJframe.this.isVisible()) {
                    int x = e.getX() - TeamCreateJframe.this.first_x;
                    int y = e.getY() - TeamCreateJframe.this.first_y;
                    TeamCreateJframe.this.setBounds(x + TeamCreateJframe.this.getX(), y + TeamCreateJframe.this.getY(), TeamCreateJframe.this.getWidth(), TeamCreateJframe.this.getHeight());
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
            FormsManagement.HideForm(31);
        }
        else {
            FormsManagement.Switchinglevel(31);
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
    
    public TeamCreateJpanel getTeamCreateJpanel() {
        return this.teamCreateJpanel;
    }
    
    public void setTeamCreateJpanel(TeamCreateJpanel teamCreateJpanel) {
        this.teamCreateJpanel = teamCreateJpanel;
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
