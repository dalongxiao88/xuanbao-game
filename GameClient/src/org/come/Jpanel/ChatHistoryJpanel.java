package org.come.Jpanel;

import java.awt.Graphics;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import com.tool.tcpimg.UIUtils;
import com.tool.btn.FormsOnOffBtn;
import java.awt.Color;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import com.tool.tcpimg.ChatBox;
import com.tool.btn.ChatTypeBtn;
import javax.swing.JPanel;

public class ChatHistoryJpanel extends JPanel
{
    private String[][] typeNames;
    private ChatTypeBtn[] chatTypeBtns1;
    private ChatTypeBtn[] chatTypeBtns2;
    private ChatBox[] chatBoxes;
    private int currentType;
    private ImageIcon icon;
    private int chatX;
    private int chatY;
    private int chatW;
    private int chatH;
    
    public ChatHistoryJpanel() {
        this.typeNames = new String[][] { { "当前", "世界", "队伍", "帮派", "战斗", "系统", "信息" }, { "传音" } };
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(550, 520));
            this.setLayout(null);
            this.setBackground(new Color(0, 0, 0, 0));
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 708);
            offBtn.setBounds(501, 15, 25, 25);
            this.add(offBtn);
            this.chatTypeBtns1 = new ChatTypeBtn[this.typeNames[0].length];
            for (int i = 0; i < this.chatTypeBtns1.length; ++i) {
                (this.chatTypeBtns1[i] = new ChatTypeBtn("inkImg/button1/B20.png", 1, i, this.typeNames[0][i], UIUtils.COLOR_WHITE2, UIUtils.TEXT_HY16sb, this)).setBounds(430, 50 + i * 30, 59, 24);
                this.add(this.chatTypeBtns1[i]);
            }
            this.chatTypeBtns2 = new ChatTypeBtn[this.typeNames[1].length];
            for (int i = 0; i < this.chatTypeBtns2.length; ++i) {
                (this.chatTypeBtns2[i] = new ChatTypeBtn("inkImg/button1/B20.png", 1, this.typeNames[0].length + i, this.typeNames[1][i], UIUtils.COLOR_WHITE2, UIUtils.TEXT_HY16sb, this)).setBounds(430, 50 + i * 30, 59, 24);
                this.add(this.chatTypeBtns2[i]);
            }
            this.chatBoxes = new ChatBox[this.typeNames[0].length + this.typeNames[1].length];
            for (int i = 0; i < this.chatBoxes.length; ++i) {
                (this.chatBoxes[i] = new ChatBox()).setW(401);
                this.chatBoxes[i].setH(410);
            }
        }
        else {
            this.setPreferredSize(new Dimension(550, 520));
            this.setLayout(null);
            this.setBackground(new Color(0, 0, 0, 0));
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/hongmu/S74.png", 1, 708);
            offBtn.setBounds(493, 0, 25, 25);
            this.add(offBtn);
            this.chatTypeBtns1 = new ChatTypeBtn[this.typeNames[0].length];
            for (int i = 0; i < this.chatTypeBtns1.length; ++i) {
                (this.chatTypeBtns1[i] = new ChatTypeBtn("inkImg/hongmu/6026.png", 1, i, this.typeNames[0][i], UIUtils.COLOR_BTNPUTONG2, UIUtils.TEXT_HY16sb, this)).setBounds(430, 50 + i * 30, 59, 24);
                this.add(this.chatTypeBtns1[i]);
            }
            this.chatTypeBtns2 = new ChatTypeBtn[this.typeNames[1].length];
            for (int i = 0; i < this.chatTypeBtns2.length; ++i) {
                (this.chatTypeBtns2[i] = new ChatTypeBtn("inkImg/hongmu/6026.png", 1, this.typeNames[0].length + i, this.typeNames[1][i], UIUtils.COLOR_BTNPUTONG2, UIUtils.TEXT_HY16sb, this)).setBounds(430, 50 + i * 30, 59, 24);
                this.add(this.chatTypeBtns2[i]);
            }
            this.chatBoxes = new ChatBox[this.typeNames[0].length + this.typeNames[1].length];
            for (int i = 0; i < this.chatBoxes.length; ++i) {
                (this.chatBoxes[i] = new ChatBox()).setW(398);
                this.chatBoxes[i].setH(388);
            }
        }
        this.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                if (e.getWheelRotation() == -1) {
                    ChatHistoryJpanel.this.chatBoxes[ChatHistoryJpanel.this.currentType].AddAndSubtract(0);
                }
                else if (e.getWheelRotation() == 1) {
                    ChatHistoryJpanel.this.chatBoxes[ChatHistoryJpanel.this.currentType].AddAndSubtract(1);
                }
            }
        });
        this.updateType();
    }
    
    public void addChatHistory(String type, String msg) {
        int index = 0;
    LOOP:
        for (int i = 0; i < this.typeNames.length; ++i) {
            int j = 0;
            while (j < this.typeNames[i].length) {
                if (this.typeNames[i][j].equals(type)) {
                    break LOOP;
                }
                else {
                    ++index;
                    ++j;
                }
            }
        }
        if (index < this.chatBoxes.length) {
            if (MyIsif.getStyle().equals("水墨")) {
                this.chatBoxes[index].addtext(msg, 401);
            }
            else {
                this.chatBoxes[index].addtext(msg, 398);
            }
        }
    }
    
    public void updateType(int type) {
        this.currentType = type;
        this.updateType();
    }
    
    public void updateType() {
        if (MyIsif.getStyle().equals("水墨")) {
            for (int i = 0; i < this.chatTypeBtns1.length; ++i) {
                if (this.currentType == i) {
                    this.chatTypeBtns1[i].setBounds(450, 50 + i * 30, 59, 24);
                }
                else {
                    this.chatTypeBtns1[i].setBounds(445, 50 + i * 30, 59, 24);
                }
            }
            for (int i = 0; i < this.chatTypeBtns2.length; ++i) {
                if (this.currentType == this.typeNames[0].length + i) {
                    this.chatTypeBtns2[i].setBounds(450, 450 - (i + 30), 59, 24);
                }
                else {
                    this.chatTypeBtns2[i].setBounds(445, 450 - (i + 30), 59, 24);
                }
            }
        }
        else {
            for (int i = 0; i < this.chatTypeBtns1.length; ++i) {
                if (this.currentType == i) {
                    this.chatTypeBtns1[i].setBounds(434, 50 + i * 30, 59, 24);
                }
                else {
                    this.chatTypeBtns1[i].setBounds(430, 50 + i * 30, 59, 24);
                }
            }
            for (int i = 0; i < this.chatTypeBtns2.length; ++i) {
                if (this.currentType == this.typeNames[0].length + i) {
                    this.chatTypeBtns2[i].setBounds(434, 450 - (i + 30), 59, 24);
                }
                else {
                    this.chatTypeBtns2[i].setBounds(430, 450 - (i + 30), 59, 24);
                }
            }
        }
        this.updateNew();
    }
    
    public void updateNew() {
        this.chatBoxes[this.currentType].update();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            this.icon = new ImageIcon("inkImg/background1/ltjl.png");
            g.drawImage(this.icon.getImage(), 0, 0, null);
            this.chatX = 43;
            this.chatY = 50;
            this.chatW = 401;
            this.chatH = 410;
        }
        else {
            this.icon = new ImageIcon("inkImg/hongmu/ltjl1.png");
            g.drawImage(this.icon.getImage(), 0, 0, null);
            this.chatX = 25;
            this.chatY = 55;
            this.chatW = 398;
            this.chatH = 388;
        }
        Graphics g2 = g.create(this.chatX, this.chatY, this.chatW, this.chatH);
        this.chatBoxes[this.currentType].paint(g2);
        g2.dispose();
    }
    
    public ChatBox getChatTypeBtns1() {
        return this.chatBoxes[this.currentType];
    }
    
    public int getChatX() {
        return this.chatX;
    }
    
    public void setChatX(int chatX) {
        this.chatX = chatX;
    }
    
    public int getChatY() {
        return this.chatY;
    }
    
    public void setChatY(int chatY) {
        this.chatY = chatY;
    }
    
    public int getChatW() {
        return this.chatW;
    }
    
    public void setChatW(int chatW) {
        this.chatW = chatW;
    }
    
    public int getChatH() {
        return this.chatH;
    }
    
    public void setChatH(int chatH) {
        this.chatH = chatH;
    }
}
