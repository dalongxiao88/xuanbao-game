package org.come.until;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.border.Border;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Dimension;
import javax.swing.JComponent;
import com.updateNew.MyIsif;
import org.come.bean.ImgZoom;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.RenderingHints;
import java.awt.Color;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class SrcollPanelUI extends BasicScrollBarUI
{
    private Color frameColor;
    private Color tranColor;
    private RenderingHints rh;
    private JButton up;
    private JButton down;
    private ImageIcon[] upBtnIcons;
    private ImageIcon[] downBtnIcons;
    private ImgZoom imgZoom;
    private String upurl;
    private String dwurl;
    private String gdt;
    
    public SrcollPanelUI() {
        this.frameColor = new Color(23, 11, 15);
        this.tranColor = new Color(0, 0, 0, 0);
        if (MyIsif.getStyle().equals("水墨")) {
            this.gdt = "inkImg/button/23.png";
            this.upurl = "inkImg/button/24.png";
            this.dwurl = "inkImg/button/25.png";
        }
        else {
            this.gdt = "img/xy2uiimg/gundongtiao_副本_副本.png";
            this.upurl = "inkImg/hongmu/24.png";
            this.dwurl = "inkImg/hongmu/25.png";
        }
        this.rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        this.imgZoom = CutButtonImage.cuts(this.gdt, 6, 6, true);
    }
    
    @Override
    protected void configureScrollBarColors() {
        this.setThumbBounds(0, 0, 3, 10);
        this.trackHighlightColor = this.tranColor;
    }
    
    @Override
    public Dimension getPreferredSize(JComponent c) {
        c.setPreferredSize(new Dimension(17, 0));
        return super.getPreferredSize(c);
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
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        super.paintThumb(g, c, thumbBounds);
        g.translate(thumbBounds.x, thumbBounds.y);
        Graphics2D g2 = (Graphics2D)g;
        g2.addRenderingHints(this.rh);
        this.imgZoom.drawScroll(g2, thumbBounds.width, thumbBounds.height);
    }
    
    @Override
    protected JButton createDecreaseButton(int orientation) {
        try {
            this.upBtnIcons = CutButtonImage.cuts(this.upurl);
        }
        catch (Exception var3) {
            var3.printStackTrace();
        }
        (this.up = new JButton()).setBorderPainted(false);
        this.up.setContentAreaFilled(false);
        this.up.setBorder((Border)null);
        this.up.setBounds(0, 0, 8, 12);
        this.up.setIcon(this.upBtnIcons[0]);
        this.up.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {
                SrcollPanelUI.this.up.setIcon(SrcollPanelUI.this.upBtnIcons[1]);
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                SrcollPanelUI.this.up.setIcon(SrcollPanelUI.this.upBtnIcons[2]);
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                SrcollPanelUI.this.up.setIcon(SrcollPanelUI.this.upBtnIcons[0]);
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                SrcollPanelUI.this.up.setIcon(SrcollPanelUI.this.upBtnIcons[1]);
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
            this.downBtnIcons = CutButtonImage.cuts(this.dwurl);
        }
        catch (Exception var3) {
            var3.printStackTrace();
        }
        (this.down = new JButton()).setBorderPainted(false);
        this.down.setContentAreaFilled(false);
        this.down.setFocusable(false);
        this.down.setBorder((Border)null);
        this.down.setBounds(0, 10, 8, 12);
        this.down.setIcon(this.downBtnIcons[0]);
        this.down.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {
                SrcollPanelUI.this.down.setIcon(SrcollPanelUI.this.downBtnIcons[1]);
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                SrcollPanelUI.this.down.setIcon(SrcollPanelUI.this.downBtnIcons[2]);
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                SrcollPanelUI.this.down.setIcon(SrcollPanelUI.this.downBtnIcons[0]);
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                SrcollPanelUI.this.down.setIcon(SrcollPanelUI.this.downBtnIcons[1]);
            }
            
            @Override
            public void mouseClicked(MouseEvent e) {
            }
        });
        return this.down;
    }
}
