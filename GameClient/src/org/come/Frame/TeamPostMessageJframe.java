package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.TeamPostMessageJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class TeamPostMessageJframe extends JInternalFrame implements MouseListener
{
    private TeamPostMessageJpanel teamPostMessageJpanel;
    private int first_x;
    private int first_y;
    
    public static TeamPostMessageJframe getTeamPostMessageJframe() {
        return (TeamPostMessageJframe)FormsManagement.getInternalForm(19).getFrame();
    }
    
    public TeamPostMessageJframe() {
        this.add(this.teamPostMessageJpanel = new TeamPostMessageJpanel());
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
                if (TeamPostMessageJframe.this.isVisible()) {
                    int x = e.getX() - TeamPostMessageJframe.this.first_x;
                    int y = e.getY() - TeamPostMessageJframe.this.first_y;
                    TeamPostMessageJframe.this.setBounds(x + TeamPostMessageJframe.this.getX(), y + TeamPostMessageJframe.this.getY(), TeamPostMessageJframe.this.getWidth(), TeamPostMessageJframe.this.getHeight());
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
            FormsManagement.HideForm(19);
        }
        else {
            FormsManagement.Switchinglevel(19);
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
    
    public TeamPostMessageJpanel getTeamPostMessageJpanel() {
        return this.teamPostMessageJpanel;
    }
    
    public void setTeamPostMessageJpanel(TeamPostMessageJpanel teamPostMessageJpanel) {
        this.teamPostMessageJpanel = teamPostMessageJpanel;
    }
}
