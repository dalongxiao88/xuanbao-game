package org.gemstone.panel;

import java.awt.Graphics;
import java.awt.LayoutManager;
import com.tool.tcpimg.UIUtils;
import java.awt.Color;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import org.come.entity.Goodstable;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GemstoneTabPanel extends JPanel
{
    private int type;
    private JLabel goodsIcon;
    private JLabel goodsName;
    private JLabel[] mountLabel;
    private Goodstable[] goods;
    private ImageIcon iconBack;
    
    public GemstoneTabPanel(int type) {
        if (MyIsif.getStyle().equals("水墨")) {
            this.type = type;
            this.setPreferredSize(new Dimension(150, 68));
            this.setOpaque(false);
            this.setLayout(null);
            this.goods = new Goodstable[4];
            this.goodsIcon = new JLabel();
            this.goodsName = new JLabel("武器");
            this.mountLabel = new JLabel[3];
            for (int i = 0; i < this.mountLabel.length; ++i) {
                (this.mountLabel[i] = new JLabel()).setBounds(68 + i * 29, 33, 25, 25);
                this.mountLabel[i].setOpaque(false);
                this.add(this.mountLabel[i]);
            }
            this.goodsIcon.setBounds(10, 8, 53, 51);
            this.goodsName.setBounds(68, 8, 80, 20);
            this.goodsIcon.setOpaque(false);
            this.goodsName.setOpaque(false);
            if (type != 2) {
                this.mountLabel[2].setOpaque(false);
            }
            this.goodsName.setForeground(new Color(187, 165, 75));
            this.goodsName.setFont(UIUtils.TEXT_HY16);
            this.add(this.goodsName);
            this.add(this.goodsIcon);
        }
        else {
            this.type = type;
            this.setPreferredSize(new Dimension(150, 68));
            this.setOpaque(false);
            this.setLayout((LayoutManager)null);
            this.goods = new Goodstable[4];
            this.goodsIcon = new JLabel();
            this.goodsName = new JLabel("武器");
            this.mountLabel = new JLabel[3];
            for (int i = 0; i < this.mountLabel.length; ++i) {
                (this.mountLabel[i] = new JLabel()).setBounds(68 + i * 29, 33, 25, 25);
                this.mountLabel[i].setOpaque(false);
                this.add(this.mountLabel[i]);
            }
            this.goodsIcon.setBounds(10, 8, 53, 51);
            this.goodsName.setBounds(68, 8, 80, 20);
            this.goodsIcon.setOpaque(false);
            this.goodsName.setOpaque(false);
            if (type != 2) {
                this.mountLabel[2].setOpaque(false);
            }
            this.goodsName.setForeground(new Color(187, 165, 75));
            this.goodsName.setFont(UIUtils.TEXT_HY16);
            this.add(this.goodsName);
            this.add(this.goodsIcon);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.iconBack == null) {
                if (this.type == 2) {
                    this.iconBack = new ImageIcon("img/gemstone/选项卡2个宝石w150,h68px.png");
                }
                else {
                    this.iconBack = new ImageIcon("img/gemstone/选项卡3个宝石w150,h68px.png");
                }
            }
            g.drawImage(this.iconBack.getImage(), 0, 0, 150, 68, this);
        }
        else {
            if (this.iconBack == null) {
                if (this.type == 2) {
                    this.iconBack = new ImageIcon("img/gemstone/选项卡2个宝石w150,h68px.png");
                }
                else {
                    this.iconBack = new ImageIcon("img/gemstone/选项卡3个宝石w150,h68px.png");
                }
            }
            g.drawImage(this.iconBack.getImage(), 0, 0, 150, 68, this);
        }
    }
    
    public int getType() {
        return this.type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    
    public JLabel getGoodsIcon() {
        return this.goodsIcon;
    }
    
    public void setGoodsIcon(JLabel goodsIcon) {
        this.goodsIcon = goodsIcon;
    }
    
    public JLabel getGoodsName() {
        return this.goodsName;
    }
    
    public void setGoodsName(JLabel goodsName) {
        this.goodsName = goodsName;
    }
    
    public JLabel[] getMountLabel() {
        return this.mountLabel;
    }
    
    public void setMountLabel(JLabel[] mountLabel) {
        this.mountLabel = mountLabel;
    }
    
    public Goodstable[] getGoods() {
        return this.goods;
    }
    
    public void setGoods(Goodstable[] goods) {
        this.goods = goods;
    }
}
