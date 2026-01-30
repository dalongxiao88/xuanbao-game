package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.JoinBangJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class JoinBangJframe extends JInternalFrame implements MouseListener
{
    private JoinBangJpanel joinBangjpanel;
    private int first_x;
    private int first_y;
    
    public static JoinBangJframe getJoinBangjframe() {
        return (JoinBangJframe)FormsManagement.getInternalForm(28).getFrame();
    }
    
    public JoinBangJframe() throws Exception {
        this.joinBangjpanel = new JoinBangJpanel();
        this.getContentPane().add(this.joinBangjpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBounds(100, 130, 345, 370);
        this.setBackground(UIUtils.Color_BACK);
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
                if (JoinBangJframe.this.isVisible()) {
                    int x = e.getX() - JoinBangJframe.this.first_x;
                    int y = e.getY() - JoinBangJframe.this.first_y;
                    JoinBangJframe.this.setBounds(x + JoinBangJframe.this.getX(), y + JoinBangJframe.this.getY(), JoinBangJframe.this.getWidth(), JoinBangJframe.this.getHeight());
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
            FormsManagement.HideForm(28);
        }
        else {
            FormsManagement.Switchinglevel(28);
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
    
    public JoinBangJpanel getJoinBangjpanel() {
        return this.joinBangjpanel;
    }
    
    public void setJoinBangjpanel(JoinBangJpanel joinBangjpanel) {
        this.joinBangjpanel = joinBangjpanel;
    }
}
