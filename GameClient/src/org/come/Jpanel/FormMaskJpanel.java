package org.come.Jpanel;

import java.awt.Color;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Graphics;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import org.come.until.ScrenceUntil;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FormMaskJpanel extends JPanel
{
    private JLabel jLabel;
    
    public FormMaskJpanel() {
        this.setPreferredSize(new Dimension(ScrenceUntil.ChatFram_X + ScrenceUntil.Screen_x, ScrenceUntil.Screen_y));
        this.setLayout(null);
        this.setBackground(UIUtils.Color_BACK);
        (this.jLabel = new JLabel()).setBounds(0, 0, ScrenceUntil.ChatFram_X + ScrenceUntil.Screen_x, ScrenceUntil.Screen_y);
        this.add(this.jLabel);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g.create();
        g2.setComposite(AlphaComposite.getInstance(3, 0.5f));
        g2.setColor(Color.BLACK);
        g2.fill(this.getBounds());
        g2.dispose();
    }
    
    public void changge(int w, int h) {
        this.setSize(w, h);
        this.jLabel.setSize(w, h);
    }
}
