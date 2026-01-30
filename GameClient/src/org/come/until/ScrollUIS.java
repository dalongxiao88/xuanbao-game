package org.come.until;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Dimension;
import javax.swing.JComponent;
import java.awt.Color;
import javax.swing.JScrollPane;
import org.come.bean.ImgZoom;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class ScrollUIS extends BasicScrollBarUI
{
    private JButton up;
    private JButton down;
    private ImageIcon[] upBtnIcons;
    private ImageIcon[] downBtnIcons;
    private ImgZoom imgZoom;
    private JScrollPane jScrollPane;
    private final Color tranColor;
    
    public ScrollUIS() {
        this.tranColor = new Color(0, 0, 0, 0);
    }
    
    @Override
    public Dimension getPreferredSize(JComponent c) {
        return new Dimension(19, 20);
    }
    
    @Override
    protected void configureScrollBarColors() {
        this.setThumbBounds(0, 0, 3, 10);
        this.trackHighlightColor = this.tranColor;
    }
    
    public void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        super.paintThumb(g, c, thumbBounds);
        this.imgZoom = CutButtonImage.cuts("inkimg/hongmu/a18.png", 6, 6, true);
        g.translate(thumbBounds.x, thumbBounds.y);
        Graphics2D g2 = (Graphics2D)g;
        GradientPaint gp = null;
        g2.setPaint(gp);
        g2.setColor(new Color(0, 0, 0));
        this.imgZoom.drawScroll(g, thumbBounds.width, thumbBounds.height);
    }
    
    public void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        Graphics2D g2 = (Graphics2D)g;
        GradientPaint gp = null;
        c.setOpaque(false);
        if (this.scrollbar.getOrientation() == 1) {
            gp = new GradientPaint(0.0f, 0.0f, this.tranColor, (float)trackBounds.width, 0.0f, this.tranColor);
        }
        else if (this.scrollbar.getOrientation() == 0) {
            gp = new GradientPaint(0.0f, 0.0f, this.tranColor, (float)trackBounds.height, 0.0f, this.tranColor);
        }
        g2.setPaint(gp);
        g2.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
        if (this.trackHighlight == 1) {
            this.paintDecreaseHighlight(g);
        }
        else if (this.trackHighlight == 2) {
            this.paintIncreaseHighlight(g);
        }
    }
    
    @Override
    protected JButton createDecreaseButton(int orientation) {
        try {
            this.upBtnIcons = CutButtonImage.cuts("inkImg/hongmu/24.png");
        }
        catch (Exception var3) {
            var3.printStackTrace();
        }
        (this.up = new JButton()).setBorderPainted(false);
        this.up.setContentAreaFilled(false);
        this.up.setBorder(null);
        this.up.setBounds(0, 0, 19, 20);
        this.up.setIcon(this.upBtnIcons[0]);
        this.up.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {
                ScrollUIS.this.up.setIcon(ScrollUIS.this.upBtnIcons[1]);
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                ScrollUIS.this.up.setIcon(ScrollUIS.this.upBtnIcons[2]);
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                if (MessagrFlagUntil.ImgFlagImg.equals(MessagrFlagUntil.MOUSE12)) {
                    MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
                }
                ScrollUIS.this.up.setIcon(ScrollUIS.this.upBtnIcons[0]);
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                if (MessagrFlagUntil.ImgFlagImg.equals(MessagrFlagUntil.MOUSE1)) {
                    MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE12);
                }
                ScrollUIS.this.up.setIcon(ScrollUIS.this.upBtnIcons[1]);
            }
            
            @Override
            public void mouseClicked(MouseEvent e) {
            }
        });
        return this.up;
    }
    
    @Override
    protected JButton createIncreaseButton(int orientation) {
        try {
            this.downBtnIcons = CutButtonImage.cuts("inkImg/hongmu/25.png");
        }
        catch (Exception var3) {
            var3.printStackTrace();
        }
        (this.down = new JButton()).setBorderPainted(false);
        this.down.setContentAreaFilled(false);
        this.down.setFocusable(false);
        this.down.setBorder(null);
        this.down.setBounds(0, 0, 19, 20);
        this.down.setIcon(this.downBtnIcons[0]);
        this.down.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {
                ScrollUIS.this.down.setIcon(ScrollUIS.this.downBtnIcons[1]);
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                ScrollUIS.this.down.setIcon(ScrollUIS.this.downBtnIcons[2]);
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                if (MessagrFlagUntil.ImgFlagImg.equals(MessagrFlagUntil.MOUSE12)) {
                    MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
                }
                ScrollUIS.this.down.setIcon(ScrollUIS.this.downBtnIcons[0]);
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                if (MessagrFlagUntil.ImgFlagImg.equals(MessagrFlagUntil.MOUSE1)) {
                    MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE12);
                }
                ScrollUIS.this.down.setIcon(ScrollUIS.this.downBtnIcons[1]);
            }
            
            @Override
            public void mouseClicked(MouseEvent e) {
            }
        });
        return this.down;
    }
}
