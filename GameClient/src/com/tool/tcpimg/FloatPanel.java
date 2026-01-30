package com.tool.tcpimg;

import java.awt.Color;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Dimension;
import org.come.until.CutButtonImage;
import org.come.until.Util;
import org.come.bean.ImgZoom;
import javax.swing.JPanel;

public class FloatPanel extends JPanel
{
    private static final long serialVersionUID = 1L;
    private RichLabel label;
    private long createTime;
    private ImgZoom imgZoom;
    
    public FloatPanel(String text) {
        this.setBorder(null);
        this.setLayout(null);
        this.setOpaque(false);
        this.setIgnoreRepaint(true);
        this.setFocusable(false);
        (this.label = new RichLabel(text, null)).setLocation(4, 3);
        Dimension d = this.label.computeSize(98);
        this.label.setSize(d);
        this.setSize(8 + d.width, 6 + d.height);
        this.createTime = Util.getTime();
        (this.imgZoom = CutButtonImage.cuts("img/xy2uiimg/88_png.xy2uiimg.png", 6, 6, true)).setMiddlew(d.width - 4);
        this.imgZoom.setMiddleh(d.height);
    }
    
    public FloatPanel(String text, Font font) {
        this.setBorder(null);
        this.setLayout(null);
        this.setOpaque(false);
        this.setIgnoreRepaint(true);
        this.setFocusable(false);
        (this.label = new RichLabel(text, font)).setLocation(4, 3);
        Dimension d = this.label.computeSize(98);
        this.label.setSize(d);
        this.setSize(8 + d.width, 6 + d.height);
        this.createTime = Util.getTime();
    }
    
    public FloatPanel() {
        this(null);
    }
    
    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D)g.create();
        g2d.setComposite(AlphaComposite.getInstance(3, 0.5f));
        g2d.setColor(Color.BLACK);
        g2d.dispose();
        if (this.imgZoom != null) {
            this.imgZoom.draw(g);
        }
        g.translate(this.label.getX(), this.label.getY());
        this.label.paint(g);
        g.translate(-this.label.getX(), -this.label.getY());
    }
    
    public void setText(String chatText) {
        this.label.setText(chatText);
        Dimension d = this.label.computeSize(98);
        this.label.setSize(d);
        this.setSize(8 + d.width, 6 + d.height);
    }
    
    public void remove() {
        this.label.remove();
        this.disable();
    }
    
    public long getCreateTime() {
        return this.createTime;
    }
    
    @Override
    public void paintImmediately(int i, int j, int k, int l) {
    }
}
