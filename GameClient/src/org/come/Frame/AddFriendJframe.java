package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.AddFriendJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class AddFriendJframe extends JInternalFrame implements MouseListener
{
    private AddFriendJpanel addFriendJpanel;
    private int first_x;
    private int first_y;
    
    public static AddFriendJframe getAddFriendJframe() {
        return (AddFriendJframe)FormsManagement.getInternalForm(75).getFrame();
    }
    
    public AddFriendJframe() {
        this.setContentPane(this.addFriendJpanel = new AddFriendJpanel());
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBounds(275, 185, 452, 459);
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
                if (AddFriendJframe.this.isVisible()) {
                    int x = e.getX() - AddFriendJframe.this.first_x;
                    int y = e.getY() - AddFriendJframe.this.first_y;
                    AddFriendJframe.this.setBounds(x + AddFriendJframe.this.getX(), y + AddFriendJframe.this.getY(), AddFriendJframe.this.getWidth(), AddFriendJframe.this.getHeight());
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
            FormsManagement.HideForm(75);
        }
        else {
            FormsManagement.Switchinglevel(75);
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
    
    public AddFriendJpanel getAddFriendJpanel() {
        return this.addFriendJpanel;
    }
    
    public void setAddFriendJpanel(AddFriendJpanel addFriendJpanel) {
        this.addFriendJpanel = addFriendJpanel;
    }
}
