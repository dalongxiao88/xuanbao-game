package org.come.Jpanel;

import java.awt.Graphics;
import com.tool.btn.FormsOnOffBtn;
import java.awt.LayoutManager;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class TtDhJpanel extends JPanel
{
    private ImageIcon icon;
    
    public TtDhJpanel() {
        this.setBounds(176, 0, 588, 478);
        this.setOpaque(false);
        this.setLayout((LayoutManager)null);
        FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 608);
        offBtn.setBounds(551, 10, 25, 25);
        this.add(offBtn);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.icon == null) {
            this.icon = new ImageIcon("inkImg/background/S81.png");
        }
        g.drawImage(this.icon.getImage(), 0, 0, 588, 478, null);
    }
}
