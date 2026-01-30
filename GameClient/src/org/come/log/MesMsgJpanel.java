package org.come.log;

import org.come.until.FormsManagement;
import com.tool.tcpimg.UIUtils;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Color;
import com.tool.tcpimg.ChatBox;
import javax.swing.JPanel;

public class MesMsgJpanel extends JPanel
{
    private ChatBox box;
    private int boxx;
    private int boxy;
    private int boxw;
    private int boxh;
    
    public MesMsgJpanel() {
        (this.box = new ChatBox()).setAlpha(0.9f);
        this.setLayout(null);
        this.setBackground(new Color(0, 0, 0, 0));
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g.create(0, 0, this.boxw, this.boxh);
        g2.dispose();
    }
    
    public void showCdan(String text, int x, int y) {
        this.box.removemsg();
        this.box.addText("#Y" + text, 100, UIUtils.TEXT_NAME_FONT15);
        this.boxw = this.box.getWidth();
        this.boxh = this.box.getHeight();
        this.boxx = x + 100 - this.box.getWidth() / 2;
        this.boxy = y + 50;
        this.displaymsg();
    }
    
    public void displaymsg() {
        MesMsgJframe.getMesMsgJframe().setBounds(this.boxx, this.boxy, this.boxw, this.boxh);
        FormsManagement.showForm(866198);
    }
    
    public ChatBox getBox() {
        return this.box;
    }
    
    public void setBox(ChatBox box) {
        this.box = box;
    }
}
