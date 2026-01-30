package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.TeamApplyJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class TeamApplyJframe extends JInternalFrame implements MouseListener
{
    private TeamApplyJpanel teamApplyJpanel;
    private int first_x;
    private int first_y;
    
    public static TeamApplyJframe getTeamApplyJframe() {
        return (TeamApplyJframe)FormsManagement.getInternalForm(30).getFrame();
    }
    
    public TeamApplyJframe() throws Exception {
        this.teamApplyJpanel = new TeamApplyJpanel();
        this.getContentPane().add(this.teamApplyJpanel);
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
                if (TeamApplyJframe.this.isVisible()) {
                    int x = e.getX() - TeamApplyJframe.this.first_x;
                    int y = e.getY() - TeamApplyJframe.this.first_y;
                    TeamApplyJframe.this.setBounds(x + TeamApplyJframe.this.getX(), y + TeamApplyJframe.this.getY(), TeamApplyJframe.this.getWidth(), TeamApplyJframe.this.getHeight());
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
            FormsManagement.HideForm(30);
        }
        else {
            FormsManagement.Switchinglevel(30);
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
    
    public TeamApplyJpanel getTeamApplyJpanel() {
        return this.teamApplyJpanel;
    }
    
    public void setTeamApplyJpanel(TeamApplyJpanel teamApplyJpanel) {
        this.teamApplyJpanel = teamApplyJpanel;
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
