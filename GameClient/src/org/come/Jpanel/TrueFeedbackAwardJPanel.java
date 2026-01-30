package org.come.Jpanel;

import java.awt.Graphics;
import com.tool.btn.FormsOnOffBtn;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class TrueFeedbackAwardJPanel extends JPanel
{
    private ImageIcon icon;
    
    public TrueFeedbackAwardJPanel() {
        this.setPreferredSize(new Dimension(336, 301));
        this.setOpaque(false);
        this.setLayout(null);
        FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 110);
        offBtn.setBounds(299, 10, 25, 25);
        this.add(offBtn);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.icon == null) {
            this.icon = new ImageIcon("inkImg/background/S199.png");
        }
        g.drawImage(this.icon.getImage(), 0, 0, null);
    }
}
