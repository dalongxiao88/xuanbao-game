package com.tool.tab;

import java.awt.FontMetrics;
import java.awt.Rectangle;
import java.awt.Graphics;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

public class TabbePaneUI extends BasicTabbedPaneUI
{
    private boolean tabsOverlapBorder;
    private Color SELECT_COLOR;
    
    public TabbePaneUI() {
        this.tabsOverlapBorder = UIManager.getBoolean("TabbedPane.tabsOverlapBorder");
        this.SELECT_COLOR = new Color(117, 151, 187);
    }
    
    @Override
    protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
    }
    
    @Override
    protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
    }
    
    @Override
    protected void paintContentBorder(Graphics g, int tabPlacement, int selectedIndex) {
    }
    
    @Override
    protected void paintContentBorderLeftEdge(Graphics g, int tabPlacement, int selectedIndex, int x, int y, int w, int h) {
        g.setColor(this.SELECT_COLOR);
    }
    
    @Override
    protected void paintContentBorderTopEdge(Graphics g, int tabPlacement, int selectedIndex, int x, int y, int w, int h) {
        g.setColor(this.SELECT_COLOR);
    }
    
    @Override
    protected void paintContentBorderRightEdge(Graphics g, int tabPlacement, int selectedIndex, int x, int y, int w, int h) {
        g.setColor(this.SELECT_COLOR);
    }
    
    @Override
    protected void paintContentBorderBottomEdge(Graphics g, int tabPlacement, int selectedIndex, int x, int y, int w, int h) {
        g.setColor(this.SELECT_COLOR);
    }
    
    @Override
    protected void paintFocusIndicator(Graphics g, int tabPlacement, Rectangle[] rects, int tabIndex, Rectangle iconRect, Rectangle textRect, boolean isSelected) {
    }
    
    @Override
    protected int calculateTabWidth(int tabPlacement, int tabIndex, FontMetrics metrics) {
        return super.calculateTabWidth(tabPlacement, tabIndex, metrics);
    }
    
    @Override
    protected int calculateTabHeight(int tabPlacement, int tabIndex, int fontHeight) {
        return super.calculateTabHeight(tabPlacement, tabIndex, fontHeight);
    }
}
