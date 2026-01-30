package org.come.Jpanel;

import java.awt.Graphics;
import java.awt.Dimension;
import org.come.until.CutButtonImage;
import java.awt.Color;
import com.tool.tcpimg.UIUtils;
import com.tool.btn.FormsOnOffBtn;
import java.awt.LayoutManager;
import javax.swing.ImageIcon;
import com.tool.btn.TtCjBtn;
import com.tool.tcpimg.RichLabel;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TtCjJpanel extends JPanel
{
    private JLabel[] GoodsImg;
    private RichLabel[] GoodsName;
    private JLabel myJfLab;
    private JLabel useJfLab;
    private TtCjBtn cjBtn;
    private ImageIcon icon;
    
    public TtCjJpanel() {
        this.setBounds(176, 0, 684, 455);
        this.setOpaque(false);
        this.setLayout((LayoutManager)null);
        FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 607);
        offBtn.setBounds(647, 10, 25, 25);
        this.add(offBtn);
        this.GoodsImg = new JLabel[16];
        this.GoodsName = new RichLabel[16];
        this.myJfLab = new JLabel();
        this.useJfLab = new JLabel();
        this.cjBtn = new TtCjBtn("inkImg/button/50.png", 1, UIUtils.COLOR_BTNPUTONG, "抽奖", UIUtils.TEXT_FONT1, 3, this);
        for (int i = 0; i < this.GoodsImg.length; ++i) {
            this.GoodsImg[i] = new JLabel();
            this.GoodsName[i] = new RichLabel();
        }
        for (int i = 0; i < this.GoodsImg.length; ++i) {
            this.GoodsImg[i].setBounds(70, 56, 50, 50);
            this.GoodsName[i].setBounds(70, 110, 80, 80);
        }
        this.myJfLab.setBounds(400, 150, 200, 60);
        this.useJfLab.setBounds(400, 182, 200, 60);
        this.cjBtn.setBounds(300, 245, 80, 26);
        this.myJfLab.setForeground(Color.RED);
        this.useJfLab.setForeground(Color.red);
        for (int i = 0; i < this.GoodsImg.length; ++i) {
            this.GoodsName[i].setForeground(Color.BLACK);
        }
        for (int i = 0; i < this.GoodsImg.length; ++i) {
            this.add(this.GoodsImg[i]);
            this.add(this.GoodsName[i]);
        }
        this.add(this.myJfLab);
        this.add(this.useJfLab);
        this.add(this.cjBtn);
    }
    
    public void initData() {
        String skin = "7006";
        for (int i = 0; i < this.GoodsImg.length; ++i) {
            this.GoodsImg[i].setIcon(CutButtonImage.getImage("img/item/" + skin + ".png", 50, 50));
            this.GoodsName[i].setText("抽奖物品");
            Dimension d = this.GoodsName[i].computeSize(100);
            this.GoodsName[i].setSize(d);
            this.GoodsName[i].setPreferredSize(d);
        }
        this.myJfLab.setText("0");
        this.useJfLab.setText("0");
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.icon == null) {
            this.icon = new ImageIcon("inkImg/background/S276.png");
        }
        g.drawImage(this.icon.getImage(), 0, 0, 684, 455, null);
    }
    
    public JLabel[] getGoodsImg() {
        return this.GoodsImg;
    }
    
    public void setGoodsImg(JLabel[] goodsImg) {
        this.GoodsImg = goodsImg;
    }
    
    public RichLabel[] getGoodsName() {
        return this.GoodsName;
    }
    
    public void setGoodsName(RichLabel[] goodsName) {
        this.GoodsName = goodsName;
    }
    
    public JLabel getMyJfLab() {
        return this.myJfLab;
    }
    
    public void setMyJfLab(JLabel myJfLab) {
        this.myJfLab = myJfLab;
    }
    
    public JLabel getUseJfLab() {
        return this.useJfLab;
    }
    
    public void setUseJfLab(JLabel useJfLab) {
        this.useJfLab = useJfLab;
    }
}
