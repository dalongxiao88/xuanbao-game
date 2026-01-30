package org.come.Jpanel;

import java.awt.Graphics;
import com.tool.btn.FormsOnOffBtn;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class TryOntxJpanel extends JPanel
{
    private ShowImageJpanel[] imageJpanels;
    private ImageIcon icon;
    
    public TryOntxJpanel() {
        this.imageJpanels = new ShowImageJpanel[4];
        this.setPreferredSize(new Dimension(385, 395));
        this.setLayout(null);
        this.setBackground(UIUtils.Color_BACK);
        FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 51);
        offBtn.setBounds(358, 2, 25, 25);
        this.add(offBtn);
        for (int i = 0; i < 4; ++i) {
            (this.imageJpanels[i] = new ShowImageJpanel()).setBounds(8 + i * 125, 27, 120, 155);
            this.add(this.imageJpanels[i]);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.icon == null) {
            this.icon = new ImageIcon("inkImg/background/4.png");
        }
        g.drawImage(this.icon.getImage(), 0, 0, 385, 395, this);
    }
    
    public ShowImageJpanel[] getImageJpanels() {
        return this.imageJpanels;
    }
    
    public void setImageJpanels(ShowImageJpanel[] imageJpanels) {
        this.imageJpanels = imageJpanels;
    }
}
