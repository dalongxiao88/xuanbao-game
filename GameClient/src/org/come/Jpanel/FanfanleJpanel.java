package org.come.Jpanel;

import org.come.until.LotteryFromServerUntil;
import java.awt.Graphics;
import com.updateNew.MyIsif;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import org.come.mouslisten.LotteryGoodslisten;
import javax.swing.JLabel;
import com.tool.btn.LotteryNineBtn;
import javax.swing.JPanel;

public class FanfanleJpanel extends JPanel
{
    private LotteryNineBtn btnReset;
    private LotteryNineBtn drop;
    private JLabel[] GoodsListLabel;
    private LotteryGoodslisten[] goodsMouslistens;
    private ImageIcon icon;
    
    public FanfanleJpanel() throws Exception {
        this.setPreferredSize(new Dimension(358, 329));
        this.setLayout(null);
        this.setBackground(UIUtils.Color_BACK);
        this.GoodsListLabel = new JLabel[9];
        this.goodsMouslistens = new LotteryGoodslisten[9];
        int Flag = 0;
        int count = 1;
        for (int i = 0; i < 9; ++i) {
            this.GoodsListLabel[i] = new JLabel();
            this.goodsMouslistens[i] = new LotteryGoodslisten(i);
            this.GoodsListLabel[i].addMouseListener(this.goodsMouslistens[i]);
            if (Flag < 3 && count == 1) {
                this.GoodsListLabel[i].setBounds(58 + Flag * 82, 55, 60, 60);
                ++Flag;
                this.add(this.GoodsListLabel[i]);
            }
            if (Flag < 3 && count == 2) {
                this.GoodsListLabel[i].setBounds(58 + Flag * 82, 135, 60, 60);
                ++Flag;
                this.add(this.GoodsListLabel[i]);
            }
            if (Flag < 3 && count == 3) {
                this.GoodsListLabel[i].setBounds(58 + Flag * 82, 215, 60, 60);
                ++Flag;
                this.add(this.GoodsListLabel[i]);
            }
            else if (Flag == 3) {
                Flag = 0;
                ++count;
            }
        }
        if (MyIsif.getStyle().equals("水墨")) {
            (this.btnReset = new LotteryNineBtn("inkImg/button1/B32.png", 1, "洗牌", this)).setBounds(48, 280, 51, 30);
            this.add(this.btnReset);
            (this.drop = new LotteryNineBtn("inkImg/button1/B30.png", 1, "放弃", this)).setBounds(240, 18, 51, 17);
            this.add(this.drop);
        }
        else {
            (this.btnReset = new LotteryNineBtn("inkImg/hongmu/B32h.png", 1, "洗牌", this)).setBounds(80, 288, 51, 17);
            this.add(this.btnReset);
            (this.drop = new LotteryNineBtn("inkImg/hongmu/B32h.png", 1, "放弃", this)).setBounds(180, 288, 51, 17);
            this.add(this.drop);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            this.icon = new ImageIcon("inkImg/background1/B462.png");
            g.drawImage(this.icon.getImage(), 0, 0, 311, 325, this);
            LotteryFromServerUntil.draw(g, 60, 50);
        }
        else {
            this.icon = new ImageIcon("inkImg/background1/B462-1.png");
            g.drawImage(this.icon.getImage(), 0, 0, 311, 325, this);
            LotteryFromServerUntil.draw(g, 60, 50);
        }
    }
}
