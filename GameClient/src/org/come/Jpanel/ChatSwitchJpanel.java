package org.come.Jpanel;

import java.awt.Graphics;
import com.tool.tcpimg.UIUtils;
import org.come.until.MessagrFlagUntil;
import org.come.mouslisten.SystemMouslisten;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import com.tool.btn.FormsOnOffBtn;
import java.awt.Color;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChatSwitchJpanel extends JPanel
{
    private String[] name;
    private JLabel[] checkBoxLabels;
    private JLabel[] selectLabels;
    private JLabel[] titleLabels;
    private ImageIcon icon;
    
    public ChatSwitchJpanel() {
        this.name = new String[] { "当前", "队伍", "帮派", "世界", "战斗", "系统", "信息" };
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(351, 307));
            this.setLayout(null);
            this.setBackground(new Color(0, 0, 0, 0));
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 709);
            offBtn.setBounds(314, 15, 25, 25);
            this.add(offBtn);
            this.checkBoxLabels = new JLabel[this.name.length];
            this.selectLabels = new JLabel[this.name.length];
            this.titleLabels = new JLabel[this.name.length];
            for (int i = 0; i < this.name.length; ++i) {
                (this.checkBoxLabels[i] = new JLabel()).setBounds(93 + i % 2 * 115, 65 + i / 2 * 25, 17, 17);
                this.checkBoxLabels[i].setIcon(new ImageIcon("inkImg/button/14.png"));
                (this.selectLabels[i] = new JLabel()).setBounds(this.checkBoxLabels[i].getX(), this.checkBoxLabels[i].getY(), 15, 15);
                if (FrameMessageChangeJpanel.getChatSwitch(i)) {
                    this.selectLabels[i].setIcon(new ImageIcon("inkImg/button/13.png"));
                }
                int finalI = i;
                this.selectLabels[i].addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                    }
                    
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (FrameMessageChangeJpanel.setChatSwitch(finalI)) {
                            ChatSwitchJpanel.this.selectLabels[finalI].setIcon(new ImageIcon("inkImg/button/13.png"));
                        }
                        else {
                            ChatSwitchJpanel.this.selectLabels[finalI].setIcon(null);
                        }
                        SystemMouslisten.writeTxt();
                    }
                    
                    @Override
                    public void mouseReleased(MouseEvent e) {
                    }
                    
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE12);
                    }
                    
                    @Override
                    public void mouseExited(MouseEvent e) {
                        MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
                    }
                });
                this.add(this.selectLabels[i]);
                this.add(this.checkBoxLabels[i]);
                (this.titleLabels[i] = new JLabel(this.name[i])).setForeground(UIUtils.COLOR_NAME_BACKGROUND);
                this.titleLabels[i].setFont(UIUtils.TEXT_FONT61);
                this.titleLabels[i].setBounds(this.checkBoxLabels[i].getX() + 20, this.checkBoxLabels[i].getY() - 2, 40, 20);
                this.add(this.titleLabels[i]);
            }
        }
        else {
            this.setPreferredSize(new Dimension(351, 307));
            this.setLayout(null);
            this.setBackground(new Color(0, 0, 0, 0));
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/hongmu/S74.png", 1, 709);
            offBtn.setBounds(326, 0, 25, 25);
            this.add(offBtn);
            this.checkBoxLabels = new JLabel[this.name.length];
            this.selectLabels = new JLabel[this.name.length];
            this.titleLabels = new JLabel[this.name.length];
            for (int i = 0; i < this.name.length; ++i) {
                (this.checkBoxLabels[i] = new JLabel()).setBounds(93 + i % 2 * 115, 80 + i / 2 * 25, 17, 17);
                this.checkBoxLabels[i].setIcon(new ImageIcon("inkImg/hongmu/ss544.png"));
                (this.selectLabels[i] = new JLabel()).setBounds(this.checkBoxLabels[i].getX(), this.checkBoxLabels[i].getY(), 17, 17);
                if (FrameMessageChangeJpanel.getChatSwitch(i)) {
                    this.selectLabels[i].setIcon(new ImageIcon("img/xy2uiimg/showjadesuit.png"));
                }
                int finalI = i;
                this.selectLabels[i].addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                    }
                    
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (FrameMessageChangeJpanel.setChatSwitch(finalI)) {
                            ChatSwitchJpanel.this.selectLabels[finalI].setIcon(new ImageIcon("img/xy2uiimg/showjadesuit.png"));
                        }
                        else {
                            ChatSwitchJpanel.this.selectLabels[finalI].setIcon(null);
                        }
                        SystemMouslisten.writeTxt();
                    }
                    
                    @Override
                    public void mouseReleased(MouseEvent e) {
                    }
                    
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE12);
                    }
                    
                    @Override
                    public void mouseExited(MouseEvent e) {
                        MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
                    }
                });
                this.add(this.selectLabels[i]);
                this.add(this.checkBoxLabels[i]);
                (this.titleLabels[i] = new JLabel(this.name[i])).setForeground(UIUtils.COLOR_Wing1);
                this.titleLabels[i].setFont(UIUtils.TEXT_FONT61);
                this.titleLabels[i].setBounds(this.checkBoxLabels[i].getX() + 20, this.checkBoxLabels[i].getY() - 2, 40, 20);
                this.add(this.titleLabels[i]);
            }
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            this.icon = new ImageIcon("inkImg/background1/pdkg.png");
            g.drawImage(this.icon.getImage(), 0, 0, null);
        }
        else {
            this.icon = new ImageIcon("inkImg/hongmu/pdkg1.png");
            g.drawImage(this.icon.getImage(), 0, 0, null);
        }
    }
}
