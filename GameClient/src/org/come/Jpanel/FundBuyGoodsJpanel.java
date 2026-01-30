package org.come.Jpanel;

import java.awt.Graphics;
import java.awt.Dimension;
import org.come.until.CutButtonImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import com.tool.btn.SpecificBtn;
import javax.swing.JPanel;

public class FundBuyGoodsJpanel extends JPanel
{
    private SpecificBtn btnBuy;
    private JLabel labBorder;
    private JLabel labImg;
    private String name;
    private String value;
    public ImageIcon icon;
    
    public FundBuyGoodsJpanel() {
        this.icon = CutButtonImage.getImage("inkImg/background/52.png", -1, -1);
        this.setPreferredSize(new Dimension(178, 48));
        this.setLayout(null);
        this.setOpaque(false);
        (this.btnBuy = new SpecificBtn("inkImg/button/2.png", 1, "购买", 10)).setBounds(140, 20, 34, 17);
        this.add(this.btnBuy);
        (this.labBorder = new JLabel()).setBounds(0, 0, 178, 48);
        this.labBorder.setIcon(this.icon);
        this.add(this.labBorder);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
