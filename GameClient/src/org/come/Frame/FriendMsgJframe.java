package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.FriendMsgJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class FriendMsgJframe extends JInternalFrame implements MouseListener
{
    private FriendMsgJpanel msgJpanel;
    private int first_x;
    private int first_y;
    
    public static FriendMsgJframe getFriendMsgJframe() {
        return (FriendMsgJframe)FormsManagement.getInternalForm(76).getFrame();
    }
    
    public FriendMsgJframe() {
        this.setContentPane(this.msgJpanel = new FriendMsgJpanel());
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBounds(275, 185, 360, 308);
        this.pack();
        this.setDefaultCloseOperation(3);
        this.setBackground(UIUtils.Color_BACK);
        this.setVisible(false);
        this.addMouseListener(this);
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (FriendMsgJframe.this.isVisible()) {
                    int x = e.getX() - FriendMsgJframe.this.first_x;
                    int y = e.getY() - FriendMsgJframe.this.first_y;
                    FriendMsgJframe.this.setBounds(x + FriendMsgJframe.this.getX(), y + FriendMsgJframe.this.getY(), FriendMsgJframe.this.getWidth(), FriendMsgJframe.this.getHeight());
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
            FormsManagement.HideForm(76);
        }
        else {
            FormsManagement.Switchinglevel(76);
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
    
    public FriendMsgJpanel getMsgJpanel() {
        return this.msgJpanel;
    }
    
    public void setMsgJpanel(FriendMsgJpanel msgJpanel) {
        this.msgJpanel = msgJpanel;
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
