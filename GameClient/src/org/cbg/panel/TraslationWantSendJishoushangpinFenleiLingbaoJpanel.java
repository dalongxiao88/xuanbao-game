package org.cbg.panel;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import org.cbg.until.TraslationImageIconZishiying;
import javax.swing.JLabel;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class TraslationWantSendJishoushangpinFenleiLingbaoJpanel extends JPanel
{
    private ImageIcon icon1;
    
    public TraslationWantSendJishoushangpinFenleiLingbaoJpanel() {
        this.setPreferredSize(new Dimension(310, 259));
        this.setBackground(null);
        this.setOpaque(false);
        this.setLayout(null);
        int j = 0;
        for (int i = 0; i < 10; ++i) {
            JLabel wupin = new JLabel();
            wupin.setBounds(3 + i % 6 * 51, 2 + j * 51, 49, 49);
            wupin.setOpaque(false);
            wupin.setBorder(null);
            wupin.setIcon(TraslationImageIconZishiying.xiugaitupainSize(new ImageIcon("img/xy2uiimg/202.png"), 49, 49));
            this.add(wupin);
            if ((i + 1) % 6 == 0) {
                ++j;
            }
            wupin.addMouseListener(new MouseListener() {
                @Override
                public void mouseReleased(MouseEvent e) {
                }
                
                @Override
                public void mousePressed(MouseEvent e) {
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                }
                
                @Override
                public void mouseEntered(MouseEvent e) {
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                }
            });
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.icon1 == null) {
            this.icon1 = new ImageIcon("img/xy2uiimg/物品&灵宝w310,h259px.png");
        }
        g.drawImage(this.icon1.getImage(), 0, 0, 310, 259, this);
    }
}
