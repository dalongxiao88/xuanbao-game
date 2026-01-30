
package org.come.until;

import org.come.bean.ImgZoom;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ActiveSrcollPanelUI extends BasicScrollBarUI {
    private Color frameColor = new Color(23, 11, 15);
    private Color tranColor = new Color(0, 0, 0, 0);
    private RenderingHints rh;
    private JButton up;
    private JButton down;
    private ImageIcon[] upBtnIcons;
    private ImageIcon[] downBtnIcons;
    private ImgZoom imgZoom;
    private String upurl;
    private String dwurl;
    private String gdt;
    public ActiveSrcollPanelUI() {
        	gdt = "inkImg/button/gd66.png";
        	upurl = "inkImg/button/kb.png";
        	dwurl = "inkImg/button/kb.png";
        this.rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        this.imgZoom = CutButtonImage.cuts(gdt, 1, 1, true);
//        this.imgZoom = CutButtonImage.cuts("inkImg/hongmu/15.png", 6, 6, true);
    }

    protected void configureScrollBarColors() {
        this.setThumbBounds(0, 0, 3, 10);
        this.trackHighlightColor = this.tranColor;
    }

    public Dimension getPreferredSize(JComponent c) {
        c.setPreferredSize(new Dimension(17, 0));
        return super.getPreferredSize(c);
    }

    public void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        Graphics2D g2 = (Graphics2D)g;
        GradientPaint gp = null;
        c.setOpaque(false);
        if (this.scrollbar.getOrientation() == 1) {
            gp = new GradientPaint(0.0F, 0.0F, this.tranColor, (float)trackBounds.width, 0.0F, this.tranColor);
        } else if (this.scrollbar.getOrientation() == 0) {
            gp = new GradientPaint(0.0F, 0.0F, this.tranColor, (float)trackBounds.height, 0.0F, this.tranColor);
        }

        g2.setPaint(gp);
        g2.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
        if (this.trackHighlight == 1) {
            this.paintDecreaseHighlight(g);
        } else if (this.trackHighlight == 2) {
            this.paintIncreaseHighlight(g);
        }

    }

    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        super.paintThumb(g, c, thumbBounds);
        g.translate(thumbBounds.x, thumbBounds.y);
        Graphics2D g2 = (Graphics2D)g;
        g2.addRenderingHints(this.rh);
        this.imgZoom.drawScroll(g2, thumbBounds.width, thumbBounds.height);
    }

    protected JButton createDecreaseButton(int orientation) {
        try {
            this.upBtnIcons = CutButtonImage.cuts(upurl);
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        this.up = new JButton();
        this.up.setBorderPainted(false);
        this.up.setContentAreaFilled(false);
        this.up.setBorder((Border)null);
        this.up.setBounds(0, 0, 8, 12);
        this.up.setIcon(this.upBtnIcons[0]);
        this.up.addMouseListener(new MouseListener() {
            public void mouseReleased(MouseEvent e) {
                ActiveSrcollPanelUI.this.up.setIcon(ActiveSrcollPanelUI.this.upBtnIcons[1]);
            }

            public void mousePressed(MouseEvent e) {
                ActiveSrcollPanelUI.this.up.setIcon(ActiveSrcollPanelUI.this.upBtnIcons[2]);
            }

            public void mouseExited(MouseEvent e) {
                ActiveSrcollPanelUI.this.up.setIcon(ActiveSrcollPanelUI.this.upBtnIcons[0]);
            }

            public void mouseEntered(MouseEvent e) {
                ActiveSrcollPanelUI.this.up.setIcon(ActiveSrcollPanelUI.this.upBtnIcons[1]);
            }

            public void mouseClicked(MouseEvent e) {
            }
        });
        return this.up;
    }

    protected JButton createIncreaseButton(int orientation) {
        try {
            this.downBtnIcons = CutButtonImage.cuts(dwurl);
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        this.down = new JButton();
        this.down.setBorderPainted(false);
        this.down.setContentAreaFilled(false);
        this.down.setFocusable(false);
        this.down.setBorder((Border)null);
        this.down.setBounds(0, 10, 8, 12);
        this.down.setIcon(this.downBtnIcons[0]);
        this.down.addMouseListener(new MouseListener() {
            public void mouseReleased(MouseEvent e) {
                ActiveSrcollPanelUI.this.down.setIcon(ActiveSrcollPanelUI.this.downBtnIcons[1]);
            }

            public void mousePressed(MouseEvent e) {
                ActiveSrcollPanelUI.this.down.setIcon(ActiveSrcollPanelUI.this.downBtnIcons[2]);
            }

            public void mouseExited(MouseEvent e) {
                ActiveSrcollPanelUI.this.down.setIcon(ActiveSrcollPanelUI.this.downBtnIcons[0]);
            }

            public void mouseEntered(MouseEvent e) {
                ActiveSrcollPanelUI.this.down.setIcon(ActiveSrcollPanelUI.this.downBtnIcons[1]);
            }

            public void mouseClicked(MouseEvent e) {
            }
        });
        return this.down;
    }
}
