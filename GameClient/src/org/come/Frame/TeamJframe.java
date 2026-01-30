package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.TeamJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class TeamJframe extends JInternalFrame implements MouseListener
{
    private TeamJpanel teamjpanel;
    private int first_x;
    private int first_y;
    
    public static TeamJframe getTeamJframe() {
        return (TeamJframe)FormsManagement.getInternalForm(13).getFrame();
    }
    
    public TeamJframe() throws Exception {
        this.teamjpanel = new TeamJpanel();
        this.getContentPane().add(this.teamjpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBounds(40, 100, 333, 348);
        this.pack();
        this.setDefaultCloseOperation(3);
        this.setVisible(false);
        this.addMouseListener(this);
        this.setBackground(UIUtils.Color_BACK);
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (TeamJframe.this.isVisible()) {
                    int x = e.getX() - TeamJframe.this.first_x;
                    int y = e.getY() - TeamJframe.this.first_y;
                    TeamJframe.this.setBounds(x + TeamJframe.this.getX(), y + TeamJframe.this.getY(), TeamJframe.this.getWidth(), TeamJframe.this.getHeight());
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
            FormsManagement.HideForm(13);
        }
        else {
            FormsManagement.Switchinglevel(13);
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
    
    public TeamJpanel getTeamjpanel() {
        return this.teamjpanel;
    }
    
    public void setTeamjpanel(TeamJpanel teamjpanel) {
        this.teamjpanel = teamjpanel;
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
