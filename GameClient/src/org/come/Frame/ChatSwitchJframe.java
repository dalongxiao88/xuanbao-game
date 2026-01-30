package org.come.Frame;

import org.come.until.Music;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.Color;
import org.come.until.FormsManagement;
import org.come.Jpanel.ChatSwitchJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class ChatSwitchJframe extends JInternalFrame implements MouseListener
{
    private ChatSwitchJpanel chatSwitchJpanel;
    private int first_x;
    private int first_y;
    
    public static ChatSwitchJframe getChatHistoryJframe() {
        return (ChatSwitchJframe)FormsManagement.getInternalForm(709).getFrame();
    }
    
    public ChatSwitchJframe() {
        this.add(this.chatSwitchJpanel = new ChatSwitchJpanel());
        this.setBackground(new Color(0, 0, 0, 0));
        this.chatSwitchJpanel.addMouseListener(this);
        this.chatSwitchJpanel.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (ChatSwitchJframe.this.isVisible()) {
                    int x = e.getX() - ChatSwitchJframe.this.first_x;
                    int y = e.getY() - ChatSwitchJframe.this.first_y;
                    ChatSwitchJframe.this.setBounds(x + ChatSwitchJframe.this.getX(), y + ChatSwitchJframe.this.getY(), ChatSwitchJframe.this.getWidth(), ChatSwitchJframe.this.getHeight());
                }
            }
        });
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBounds(300, 300, 351, 307);
        this.pack();
        this.setVisible(false);
        this.setDefaultCloseOperation(3);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        Music.addyinxiao("关闭窗口.mp3");
        if (e.isMetaDown()) {
            FormsManagement.HideForm(709);
        }
        else {
            FormsManagement.Switchinglevel(709);
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
    
    public ChatSwitchJpanel getChatSwitchJpanel() {
        return this.chatSwitchJpanel;
    }
}
