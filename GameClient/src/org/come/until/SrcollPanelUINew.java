package org.come.until;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import java.awt.RenderingHints;
import java.awt.GradientPaint;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Dimension;
import javax.swing.JComponent;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class SrcollPanelUINew extends BasicScrollBarUI
{
    private JScrollPane jScrollPane;
    private JButton up;
    private JButton down;
    
    public SrcollPanelUINew() {
    }
    
    public SrcollPanelUINew(JScrollPane jScrollPane) {
        this.jScrollPane = jScrollPane;
        jScrollPane.getVerticalScrollBar().setUnitIncrement(30);
    }
    
    @Override
    protected void configureScrollBarColors() {
    }
    
    @Override
    public Dimension getPreferredSize(JComponent c) {
        c.setPreferredSize(new Dimension(8, 180));
        return super.getPreferredSize(c);
    }
    
    public void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        Graphics2D g2 = (Graphics2D)g;
        GradientPaint gp = null;
        if (this.scrollbar.getOrientation() == 1) {
            gp = new GradientPaint(0.0f, 0.0f, new Color(10193786), (float)trackBounds.width, 0.0f, new Color(10193786));
        }
        if (this.scrollbar.getOrientation() == 0) {
            gp = new GradientPaint(0.0f, 0.0f, new Color(10193786), (float)trackBounds.height, 0.0f, new Color(10193786));
        }
        g2.setPaint(gp);
        g2.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
        if (this.trackHighlight == 1) {
            this.paintDecreaseHighlight(g);
        }
        if (this.trackHighlight == 2) {
            this.paintIncreaseHighlight(g);
        }
    }
    
    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        super.paintThumb(g, c, thumbBounds);
        String gdt = "";
        gdt = "inkImg/tupiankuang/Z1008.png";
        g.translate(thumbBounds.x, thumbBounds.y);
        Graphics2D g2 = (Graphics2D)g;
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.addRenderingHints(rh);
        g2.drawImage(new ImageIcon(gdt).getImage(), 0, 0, thumbBounds.width, thumbBounds.height, 0, 0, thumbBounds.width, thumbBounds.height, this.jScrollPane);
    }
    
    @Override
    protected JButton createDecreaseButton(int orientation) {
        (this.up = new JButton()).setBorderPainted(false);
        this.up.setContentAreaFilled(false);
        this.up.setBorder(null);
        this.up.setBounds(0, 0, 0, 0);
        this.up.setIcon(new ImageIcon("inkImg/tupiankuang/绌�.png"));
        this.up.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {
                SrcollPanelUINew.this.up.setIcon(new ImageIcon("inkImg/tupiankuang/绌�.png"));
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                SrcollPanelUINew.this.up.setIcon(new ImageIcon("inkImg/tupiankuang/绌�.png"));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                SrcollPanelUINew.this.up.setIcon(new ImageIcon("inkImg/tupiankuang/绌�.png"));
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                SrcollPanelUINew.this.up.setIcon(new ImageIcon("inkImg/tupiankuang/绌�.png"));
            }
            
            @Override
            public void mouseClicked(MouseEvent e) {
            }
        });
        return this.up;
    }
    
    @Override
    protected JButton createIncreaseButton(int orientation) {
        (this.down = new JButton()).setBorderPainted(false);
        this.down.setContentAreaFilled(false);
        this.down.setFocusable(false);
        this.down.setBorder(null);
        this.down.setBounds(0, 0, 0, 0);
        this.down.setIcon(new ImageIcon("inkImg/tupiankuang/691.png"));
        this.down.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {
                SrcollPanelUINew.this.down.setIcon(new ImageIcon("inkImg/tupiankuang/绌�.png"));
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                SrcollPanelUINew.this.down.setIcon(new ImageIcon("inkImg/tupiankuang/绌�.png"));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                SrcollPanelUINew.this.down.setIcon(new ImageIcon("inkImg/tupiankuang/绌�.png"));
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                SrcollPanelUINew.this.down.setIcon(new ImageIcon("inkImg/tupiankuang/691.png"));
            }
            
            @Override
            public void mouseClicked(MouseEvent e) {
            }
        });
        return this.down;
    }
}
