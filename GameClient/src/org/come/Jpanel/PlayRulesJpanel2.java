package org.come.Jpanel;

import com.tool.btn.FormsOnOffBtn;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PlayRulesJpanel2 extends JPanel {
    private ImageIcon icon;

    public PlayRulesJpanel2() {
        setPreferredSize(new Dimension(514, 452));
        setOpaque(false);
        setLayout(null);
        FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 8035);
        offBtn.setBounds(450, 20, 25, 25);
        add((Component) offBtn);
    }


    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.icon == null) {
            this.icon = new ImageIcon("img/xuan/xuanbaodengjishuoming.png");
        }
        g.drawImage(this.icon.getImage(), 0, 0, 514, 452, this);
    }
}

