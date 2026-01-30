package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.FriendChatMessageJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class FriendChatMessageJframe extends JInternalFrame implements MouseListener
{
    private FriendChatMessageJpanel jpanel;
    private int first_x;
    private int first_y;
    
    public static FriendChatMessageJframe getFriendChatMessageJframe() {
        return (FriendChatMessageJframe)FormsManagement.getInternalForm(56).getFrame();
    }
    
    public FriendChatMessageJframe() throws Exception {
        this.jpanel = new FriendChatMessageJpanel();
        this.getContentPane().add(this.jpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBackground(UIUtils.Color_BACK);
        this.setBounds(280, 170, 584, 404);
        this.pack();
        this.setVisible(false);
        this.setDefaultCloseOperation(3);
        this.addMouseListener(this);
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (FriendChatMessageJframe.this.isVisible()) {
                    int x = e.getX() - FriendChatMessageJframe.this.first_x;
                    int y = e.getY() - FriendChatMessageJframe.this.first_y;
                    FriendChatMessageJframe.this.setBounds(x + FriendChatMessageJframe.this.getX(), y + FriendChatMessageJframe.this.getY(), FriendChatMessageJframe.this.getWidth(), FriendChatMessageJframe.this.getHeight());
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
            FormsManagement.HideForm(56);
        }
        else {
            FormsManagement.Switchinglevel(56);
        }
        this.first_x = e.getX();
        this.first_y = e.getY();
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    public FriendChatMessageJpanel getJpanel() {
        return this.jpanel;
    }
    
    public void setJpanel(FriendChatMessageJpanel jpanel) {
        this.jpanel = jpanel;
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
    
    @Override
    public void mouseReleased(MouseEvent e) {
    }
}
