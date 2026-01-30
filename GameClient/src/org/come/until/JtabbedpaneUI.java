package org.come.until;

import com.tool.tcpimg.UIUtils;
import java.awt.Rectangle;
import java.awt.Font;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

public class JtabbedpaneUI extends BasicTabbedPaneUI
{
    @Override
    protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
    }
    
    @Override
    protected void paintContentBorder(Graphics g, int tabPlacement, int selectedIndex) {
    }
    
    @Override
    protected int calculateTabWidth(int tabPlacement, int tabIndex, FontMetrics metrics) {
        return 65;
    }
    
    @Override
    protected void paintContentBorderTopEdge(Graphics g, int tabPlacement, int selectedIndex, int x, int y, int w, int h) {
        g.setColor(Color.yellow);
        super.paintContentBorderTopEdge(g, tabPlacement, selectedIndex, x, y, w, h);
    }
    
    @Override
    protected void paintText(Graphics g, int tabPlacement, Font font, FontMetrics metrics, int tabIndex, String title, Rectangle textRect, boolean isSelected) {
        if (isSelected && tabPlacement == 1) {
            g.setColor(new Color(177, 162, 107));
            g.setFont(UIUtils.TEXT_FONT2);
        }
        else {
            g.setColor(new Color(151, 140, 118));
            g.setFont(UIUtils.TEXT_FONT1);
        }
        g.drawString(title, textRect.x - 8, textRect.y + metrics.getAscent());
    }
}
