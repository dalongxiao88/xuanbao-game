package org.come.Jpanel;

import java.awt.Graphics;
import com.tool.btn.FormsOnOffBtn;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PlayRulesJpanel extends JPanel
{
    private ImageIcon icon;
    
    public PlayRulesJpanel() {
        this.setPreferredSize(new Dimension(360, 310));
        this.setOpaque(false);
        this.setLayout(null);
        FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/hongmu/S74.png", 1, 72);
        offBtn.setBounds(334, 0, 25, 25);
        this.add(offBtn);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.icon == null) {
            this.icon = new ImageIcon("img/xy2uiimg/重铸攻略.png");
        }
        g.drawImage(this.icon.getImage(), 0, 0, 360, 310, this);
    }
}
