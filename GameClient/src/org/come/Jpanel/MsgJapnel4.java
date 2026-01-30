package org.come.Jpanel;

import org.come.until.FormsManagement;
import org.come.Frame.MsgJframe4;
import java.awt.Point;
import java.awt.Dimension;
import org.come.Frame.ZhuFrame;
import com.tool.tcpimg.UIUtils;
import java.awt.Graphics;
import java.awt.Color;
import com.tool.tcpimg.ChatBox;
import javax.swing.JPanel;

public class MsgJapnel4 extends JPanel
{
    private ChatBox box;
    private int boxx;
    private int boxy;
    private int boxw;
    private int boxh;
    private long time;
    
    public MsgJapnel4() {
        this.time = 0L;
        (this.box = new ChatBox()).setAlpha(0.6f);
        this.setLayout(null);
        this.setBackground(new Color(0, 0, 0, 0));
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics g2 = g.create(0, 0, this.boxw, this.boxh);
        this.box.paintSSS1(g2);
        g2.dispose();
    }
    
    public void xy(int x, int y) {
        this.box.removemsg();
        this.box.removeAddText("#Y" + x + "," + y, 54, UIUtils.TEXT_FONT);
        this.boxw = this.box.getWidth();
        this.boxh = this.box.getHeight();
        Point goodx = ZhuFrame.getZhuJpanel().framepoint(GameJpanel.getGameJpanel().getMousePosition(), new Dimension(this.boxw, this.boxh));
        this.boxx = (int)goodx.getX();
        this.boxy = (int)goodx.getY() - 35;
        this.displaymsg();
    }
    
    public void displaymsg() {
        MsgJframe4.getJframe4().setBounds(this.boxx, this.boxy, this.boxw, this.boxh);
        FormsManagement.showForm(633);
    }
    
    public ChatBox getBox() {
        return this.box;
    }
    
    public void setBox(ChatBox box) {
        this.box = box;
    }
}
