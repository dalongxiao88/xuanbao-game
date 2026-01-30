package org.cbg.until;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import java.awt.RenderingHints;
import java.awt.GradientPaint;
import java.awt.Color;
import java.awt.Graphics2D;
import com.updateNew.MyIsif;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Dimension;
import javax.swing.JComponent;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class TraslationDemoScrollBarUI extends BasicScrollBarUI
{
    private JScrollPane jScrollPane;
    private JButton up;
    private JButton down;
    
    public TraslationDemoScrollBarUI() {
    }
    
    public TraslationDemoScrollBarUI(JScrollPane jScrollPane) {
        this.jScrollPane = jScrollPane;
        jScrollPane.getVerticalScrollBar().setUnitIncrement(30);
    }
    
    @Override
    protected void configureScrollBarColors() {
    }
    
    @Override
    public Dimension getPreferredSize(JComponent c) {
        c.setPreferredSize(new Dimension(17, 0));
        return super.getPreferredSize(c);
    }
    
    public void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        if (MyIsif.getStyle().equals("水墨")) {
            Graphics2D g2 = (Graphics2D)g;
            GradientPaint gp = null;
            if (this.scrollbar.getOrientation() == 1) {
                gp = new GradientPaint(0.0f, 0.0f, new Color(3), (float)trackBounds.width, 0.0f, new Color(3));
            }
            if (this.scrollbar.getOrientation() == 0) {
                gp = new GradientPaint(0.0f, 0.0f, new Color(3), (float)trackBounds.height, 0.0f, new Color(3));
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
        else {
            Graphics2D g2 = (Graphics2D)g;
            GradientPaint gp = null;
            if (this.scrollbar.getOrientation() == 1) {
                gp = new GradientPaint(0.0f, 0.0f, new Color(3), (float)trackBounds.width, 0.0f, new Color(3));
            }
            if (this.scrollbar.getOrientation() == 0) {
                gp = new GradientPaint(0.0f, 0.0f, new Color(3), (float)trackBounds.height, 0.0f, new Color(3));
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
    }
    
    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        super.paintThumb(g, c, thumbBounds);
        String gdt = "";
        if (MyIsif.getStyle().equals("水墨")) {
            gdt = "inkImg/button/23.png";
        }
        else {
            gdt = "img/xy2uiimg/gundongtiao_副本_副本.png";
        }
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
        this.up.setBounds(0, 0, 19, 20);
        if (MyIsif.getStyle().equals("水墨")) {
            this.up.setIcon(new ImageIcon("inkImg/button/68.png"));
            this.up.addMouseListener(new MouseListener() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    TraslationDemoScrollBarUI.this.up.setIcon(new ImageIcon("inkImg/button/68.png"));
                }
                
                @Override
                public void mousePressed(MouseEvent e) {
                    TraslationDemoScrollBarUI.this.up.setIcon(new ImageIcon("inkImg/button/68.png"));
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                    TraslationDemoScrollBarUI.this.up.setIcon(new ImageIcon("inkImg/button/68.png"));
                }
                
                @Override
                public void mouseEntered(MouseEvent e) {
                    TraslationDemoScrollBarUI.this.up.setIcon(new ImageIcon("inkImg/button/68.png"));
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                }
            });
        }
        else {
            this.up.setIcon(new ImageIcon("resource/jiuUI/jiuuijiemian/1shang.png"));
            this.up.addMouseListener(new MouseListener() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    TraslationDemoScrollBarUI.this.up.setIcon(new ImageIcon("resource/jiuUI/jiuuijiemian/2shang.png"));
                }
                
                @Override
                public void mousePressed(MouseEvent e) {
                    TraslationDemoScrollBarUI.this.up.setIcon(new ImageIcon("resource/jiuUI/jiuuijiemian/3shang.png"));
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                    TraslationDemoScrollBarUI.this.up.setIcon(new ImageIcon("resource/jiuUI/jiuuijiemian/1shang.png"));
                }
                
                @Override
                public void mouseEntered(MouseEvent e) {
                    TraslationDemoScrollBarUI.this.up.setIcon(new ImageIcon("resource/jiuUI/jiuuijiemian/2shang.png"));
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                }
            });
        }
        return this.up;
    }
    
    @Override
    protected JButton createIncreaseButton(int orientation) {
        if (MyIsif.getStyle().equals("水墨")) {
            (this.down = new JButton()).setBorderPainted(false);
            this.down.setContentAreaFilled(false);
            this.down.setFocusable(false);
            this.down.setBorder(null);
            this.down.setBounds(0, 30, 19, 20);
            this.down.setIcon(new ImageIcon("inkImg/button/69.png"));
            this.down.addMouseListener(new MouseListener() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    TraslationDemoScrollBarUI.this.down.setIcon(new ImageIcon("inkImg/button/69.png"));
                }
                
                @Override
                public void mousePressed(MouseEvent e) {
                    TraslationDemoScrollBarUI.this.down.setIcon(new ImageIcon("inkImg/button/69.png"));
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                    TraslationDemoScrollBarUI.this.down.setIcon(new ImageIcon("inkImg/button/69.png"));
                }
                
                @Override
                public void mouseEntered(MouseEvent e) {
                    TraslationDemoScrollBarUI.this.down.setIcon(new ImageIcon("inkImg/button/69.png"));
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                }
            });
        }
        else {
            (this.down = new JButton()).setBorderPainted(false);
            this.down.setContentAreaFilled(false);
            this.down.setFocusable(false);
            this.down.setBorder(null);
            this.down.setBounds(0, 30, 19, 20);
            this.down.setIcon(new ImageIcon("resource/jiuUI/jiuuijiemian/1xia.png"));
            this.down.addMouseListener(new MouseListener() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    TraslationDemoScrollBarUI.this.down.setIcon(new ImageIcon("resource/jiuUI/jiuuijiemian/2xia.png"));
                }
                
                @Override
                public void mousePressed(MouseEvent e) {
                    TraslationDemoScrollBarUI.this.down.setIcon(new ImageIcon("resource/jiuUI/jiuuijiemian/3xia.png"));
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                    TraslationDemoScrollBarUI.this.down.setIcon(new ImageIcon("resource/jiuUI/jiuuijiemian/1xia.png"));
                }
                
                @Override
                public void mouseEntered(MouseEvent e) {
                    TraslationDemoScrollBarUI.this.down.setIcon(new ImageIcon("resource/jiuUI/jiuuijiemian/2xia.png"));
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                }
            });
        }
        return this.down;
    }
}
