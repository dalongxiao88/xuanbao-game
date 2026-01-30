package org.come.Jpanel;

import java.awt.Graphics;
import org.come.until.ZxpackFromServerUntil;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import org.come.mouslisten.ZxpackGoodslisten;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ZxpackJpanel extends JPanel
{
    private JLabel[] GoodsListLabel;
    private ZxpackGoodslisten[] goodsMouslistens;
    private int row;
    private int column;
    private ImageIcon icon;
    
    public ZxpackJpanel() throws Exception {
        this.row = 0;
        this.column = 1;
        this.setPreferredSize(new Dimension(382, 359));
        this.setLayout(null);
        this.setBackground(UIUtils.Color_BACK);
        int sum = ZxpackFromServerUntil.getGoodslist().size();
        this.GoodsListLabel = new JLabel[20];
        this.goodsMouslistens = new ZxpackGoodslisten[20];
        for (int i = 0; i < 20; ++i) {
            this.GoodsListLabel[i] = new JLabel();
            this.goodsMouslistens[i] = new ZxpackGoodslisten(i);
            this.GoodsListLabel[i].addMouseListener(this.goodsMouslistens[i]);
            int x = 63 + this.row * 58;
            int y = 62 + (this.column - 1) * 56;
            this.GoodsListLabel[i].setBounds(x, y, 56, 56);
            this.add(this.GoodsListLabel[i]);
            ++this.row;
            if (this.row >= 5) {
                this.row = 0;
                ++this.column;
            }
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.icon = new ImageIcon("inkImg/background1/zxdj.png");
        g.drawImage(this.icon.getImage(), 0, 0, 382, 359, this);
        ZxpackFromServerUntil.draw(g, 35, 59);
    }
}
