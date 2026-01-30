package org.come.Frame;

import org.come.mouslisten.Mouselistener;
import com.tool.tcpimg.ChatBox;
import org.come.until.Music;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.Color;
import org.come.until.FormsManagement;
import com.tool.tcpimg.InputBean;
import org.come.Jpanel.ChatHistoryJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class ChatHistoryJframe extends JInternalFrame implements MouseListener
{
    private ChatHistoryJpanel chatHistoryJpanel;
    private InputBean inputBean;
    private int first_x;
    private int first_y;
    
    public static ChatHistoryJframe getChatHistoryJframe() {
        return (ChatHistoryJframe)FormsManagement.getInternalForm(708).getFrame();
    }
    
    public ChatHistoryJframe() {
        this.add(this.chatHistoryJpanel = new ChatHistoryJpanel());
        this.setBackground(new Color(0, 0, 0, 0));
        this.chatHistoryJpanel.addMouseListener(this);
        this.chatHistoryJpanel.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (ChatHistoryJframe.this.isVisible()) {
                    int x = e.getX() - ChatHistoryJframe.this.first_x;
                    int y = e.getY() - ChatHistoryJframe.this.first_y;
                    ChatHistoryJframe.this.setBounds(x + ChatHistoryJframe.this.getX(), y + ChatHistoryJframe.this.getY(), ChatHistoryJframe.this.getWidth(), ChatHistoryJframe.this.getHeight());
                }
            }
        });
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBounds(150, 100, 750, 550);
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
        this.first_x = e.getX();
        this.first_y = e.getY();
        if (e.isMetaDown()) {
            FormsManagement.HideForm(708);
        }
        else {
            ChatBox chatBox = this.chatHistoryJpanel.getChatTypeBtns1();
            int chatX = this.chatHistoryJpanel.getChatX();
            int chatY = this.chatHistoryJpanel.getChatY();
            int chatW = this.chatHistoryJpanel.getChatW();
            int chatH = this.chatHistoryJpanel.getChatH();
            if (this.first_x >= chatX && this.first_y >= chatY && this.first_x <= chatX + chatW && this.first_y <= chatY + chatH) {
                this.inputBean = chatBox.isMonitor(this.first_x - chatX, this.first_y - chatY);
                if (this.inputBean != null) {
                    this.inputBean.setM(true);
                }
            }
            FormsManagement.Switchinglevel(708);
        }
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        if (this.inputBean != null) {
            Mouselistener.DJInputBean(this.inputBean);
            this.inputBean = null;
        }
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    public ChatHistoryJpanel getChatHistoryJpanel() {
        return this.chatHistoryJpanel;
    }
}
