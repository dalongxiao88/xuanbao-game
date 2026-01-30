package org.come.Jpanel;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CustomTitleBarUI2 extends JPanel
{
    private String title;
    
    public CustomTitleBarUI2(JFrame frame) {
        this.setLayout(new FlowLayout(0, 5, 5));
        this.setPreferredSize(new Dimension(frame.getWidth(), 5));
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        Color color1 = new Color(113, 154, 194);
        g.setColor(color1);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Custom Title Bar UI Demo");
        frame.setDefaultCloseOperation(3);
        frame.setSize(400, 300);
        CustomTitleBarUI2 customTitleBarUI = new CustomTitleBarUI2(frame);
        customTitleBarUI.setOpaque(false);
        frame.add(customTitleBarUI, "North");
        frame.setVisible(true);
    }
}
