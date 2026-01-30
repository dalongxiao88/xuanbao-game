package org.come.action;

import org.come.until.FormsManagement;
import java.awt.Point;
import java.awt.Dimension;
import org.come.Jpanel.GameJpanel;
import org.come.Frame.ZhuFrame;
import com.tool.tcpimg.UIUtils;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Color;
import com.tool.tcpimg.ChatBox;
import javax.swing.JPanel;

public class MsgJapnel2 extends JPanel
{
    private ChatBox box;
    private int boxx;
    private int boxy;
    private int boxw;
    private int boxh;
    private String vc;
    public boolean open;
    
    public MsgJapnel2() {
        this.open = false;
        (this.box = new ChatBox()).setAlpha(0.6f);
        this.setLayout(null);
        this.setBackground(new Color(0, 0, 0, 0));
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics g2 = g.create(0, 0, this.boxw, this.boxh);
        this.box.paintSSS(g2);
        g2.dispose();
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        if (this.open) {
            g2d.setFont(UIUtils.TEXT_FONT19);
            g2d.setColor(new Color(248, 252, 112));
            g2d.drawString(this.vc, 7, 23);
            g2d.drawString(this.vc, 7, 23);
            g2d.drawString(this.vc, 8, 23);
        }
        else {
            g2d.setFont(UIUtils.TEXT_FONT_17);
            g2d.setColor(new Color(130, 174, 255));
            g2d.drawString(this.vc, 7, 20);
            g2d.drawString(this.vc, 7, 20);
            g2d.drawString(this.vc, 7, 20);
        }
    }
    
    public void LX(String name, String msg) {
        this.box.removemsg();
        this.vc = name;
        this.open = false;
        this.box.removeAddText(" ", 310, UIUtils.TEXT_FONT_17);
        this.box.addText(msg, this.box.getWidth(), UIUtils.TEXT_FONT1);
        this.boxw = this.box.getWidth() + 10;
        this.boxh = this.box.getHeight();
        Point goodx = ZhuFrame.getZhuJpanel().framepoint(GameJpanel.getGameJpanel().getMousePosition(), new Dimension(this.boxw, this.boxh));
        this.boxx = (int)goodx.getX();
        this.boxy = (int)goodx.getY();
        this.displaymsg();
    }
    
    public void TYC(String name, String msg) {
        this.box.removemsg();
        this.vc = name;
        this.open = true;
        this.box.addText("#r ", 300, UIUtils.TEXT_FONT_17);
        this.box.addText(msg, 295, UIUtils.TEXT_FONT1);
        this.boxw = 315;
        this.boxh = this.box.getHeight();
        Point goodx = ZhuFrame.getZhuJpanel().framepoint(GameJpanel.getGameJpanel().getMousePosition(), new Dimension(this.boxw, this.boxh));
        this.boxx = (int)goodx.getX() - 10;
        this.boxy = (int)goodx.getY() + 20;
        this.displaymsg();
    }
    
    public void KJL(String name, String msg) {
        this.box.removemsg();
        this.vc = name;
        this.open = true;
        this.box.addText("#r ", 300, UIUtils.TEXT_FONT_17);
        this.box.addText("#G" + msg, 295, UIUtils.TEXT_FONT1);
        this.boxw = 310;
        this.boxh = this.box.getHeight();
        Point goodx = ZhuFrame.getZhuJpanel().framepoint(GameJpanel.getGameJpanel().getMousePosition(), new Dimension(this.boxw, this.boxh));
        this.boxx = (int)goodx.getX() + 100;
        this.boxy = (int)goodx.getY() + 180;
        this.displaymsg();
    }
    
    public void displaymsg() {
        MsgJframe2.getJframe2().setBounds(this.boxx, this.boxy, this.boxw, this.boxh);
        FormsManagement.showForm(628);
    }
    
    public ChatBox getBox() {
        return this.box;
    }
    
    public void setBox(ChatBox box) {
        this.box = box;
    }
}
