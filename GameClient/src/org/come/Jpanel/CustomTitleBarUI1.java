package org.come.Jpanel;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CustomTitleBarUI1 extends JPanel
{
    public CustomTitleBarUI1(JFrame frame) {
        this.setLayout(new FlowLayout(0, 5, 5));
        this.setPreferredSize(new Dimension(5, 58));
        //this.setCursor(new Cursor(0));
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        Color color = new Color(113, 154, 194);
        g2d.setColor(color);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
        g2d.setComposite(AlphaComposite.getInstance(1, 1.0f));
        g2d.setColor(Color.black);
        g2d.fillRect(4, -13, 1, this.getHeight() + 13);
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Custom Title Bar UI Demo");
        frame.setDefaultCloseOperation(3);
        frame.setSize(400, 300);
        CustomTitleBarUI1 customTitleBarUI = new CustomTitleBarUI1(frame);
        customTitleBarUI.setOpaque(false);
        frame.add(customTitleBarUI, "North");
        frame.setVisible(true);
    }
}
