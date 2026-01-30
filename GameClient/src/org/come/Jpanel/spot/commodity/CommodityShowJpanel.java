package org.come.Jpanel.spot.commodity;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import com.tool.btn.MoBanBtn;
import javax.swing.ImageIcon;
import org.come.until.GoodsListFromServerUntil;
import org.come.until.EquipTool;
import com.tool.tcpimg.UIUtils;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JLabel;
import org.come.entity.Goodstable;
import java.util.List;
import org.come.Jpanel.spot.buy.SpotBuyPurchaseJpanel;
import javax.swing.JPanel;

public class CommodityShowJpanel extends JPanel
{
    private SpotBuyPurchaseJpanel spotBuyPurchaseJpanel;
    private List<Goodstable> goodsList;
    private final JLabel[] labels;
    private final ChangePageNumberBtn changePageNumberBtn1;
    private final ChangePageNumberBtn changePageNumberBtn2;
    private int pageCount;
    private int pageNumber;
    private int selectIndex;
    
    public CommodityShowJpanel(SpotBuyPurchaseJpanel spotBuyPurchaseJpanel) {
        this.goodsList = new ArrayList<>();
        this.labels = new JLabel[10];
        this.pageCount = 1;
        this.pageNumber = 1;
        this.selectIndex = -1;
        this.setLayout(null);
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(687, 487));
        for (int i = 0; i < this.labels.length; ++i) {
            (this.labels[i] = new JLabel()).setBounds(3 + i * 61, 5, 56, 54);
            this.labels[i].addMouseListener(new GoodsMouseAdapterListener(i));
            this.add(this.labels[i]);
        }
        (this.changePageNumberBtn1 = new ChangePageNumberBtn("inkImg/button1/7.png", false)).setBounds(611, 2, 18, 18);
        (this.changePageNumberBtn2 = new ChangePageNumberBtn("inkImg/button1/8.png", true)).setBounds(611, 44, 18, 18);
        this.add(this.changePageNumberBtn1);
        this.add(this.changePageNumberBtn2);
        this.spotBuyPurchaseJpanel = spotBuyPurchaseJpanel;
    }
    
    public void init(List<Goodstable> goodsList) {
        this.goodsList.clear();
        int size = 0;
        if (goodsList != null) {
            this.goodsList.addAll(goodsList);
            size = goodsList.size();
        }
        if (size > 0) {
            this.pageCount = size / 10 + ((size % 10 == 0) ? 0 : 1);
        }
        else {
            this.pageCount = 1;
        }
        if (this.pageCount < this.pageNumber) {
            this.pageCount = this.pageNumber;
        }
        this.changeSelectGoods(this.selectIndex, -1);
    }
    
    private void changeSelectGoods(int selectIndex, int buttonType) {
        this.selectIndex = selectIndex;
        if (this.selectIndex != -1) {
            int pageNumber = this.selectIndex / this.labels.length + 1;
            if (this.pageNumber != pageNumber) {
                this.selectIndex = -1;
            }
        }
        for (int i = 0; i < this.labels.length; ++i) {
            if (selectIndex == i) {
                this.labels[i].setBorder(BorderFactory.createLineBorder(Color.red));
            }
            else {
                this.labels[i].setBorder(BorderFactory.createEmptyBorder());
            }
        }
        Goodstable selectGoods = this.getSelectGoods();
        if (selectGoods != null) {
            if (buttonType == -1) {
                int sum = this.spotBuyPurchaseJpanel.getNumber();
                if (sum > (int)selectGoods.getUsetime()) {
                    sum = (int)selectGoods.getUsetime();
                }
                this.spotBuyPurchaseJpanel.showSelectGoods(selectGoods.getSkin(), sum);
            }
            else if (buttonType == 1) {
                this.spotBuyPurchaseJpanel.showSelectGoods(selectGoods.getSkin(), 1);
            }
            else if (buttonType == 3) {
                this.spotBuyPurchaseJpanel.showSelectGoods(selectGoods.getSkin(), (int)selectGoods.getUsetime());
            }
        }
        else {
            this.spotBuyPurchaseJpanel.showSelectGoods(null, 0);
        }
    }
    
    public Goodstable getSelectGoods() {
        if (this.selectIndex != -1 && this.selectIndex < this.goodsList.size()) {
            return (Goodstable)this.goodsList.get(this.selectIndex);
        }
        return null;
    }
    
    private int getCurrentIndex(int index) {
        return (this.pageCount - 1) * 10 + index;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(UIUtils.TEXT_FONT1);
        g.setColor(Color.WHITE);
        for (int i = 0; i < this.labels.length; ++i) {
            int index = this.getCurrentIndex(i);
            if (index < this.goodsList.size()) {
                Goodstable goods = (Goodstable)this.goodsList.get(index);
                if (!EquipTool.isEquip(goods.getType())) {
                    g.drawString(goods.getUsetime() + "", this.labels[i].getX() + 2, this.labels[i].getY() + 12);
                }
                ImageIcon goodsIcon = GoodsListFromServerUntil.imgpathAdaptive(goods.getSkin(), 49, 49);
                g.drawImage(goodsIcon.getImage(), this.labels[i].getX(), this.labels[i].getY(), null);
            }
        }
    }
    
    private class ChangePageNumberBtn extends MoBanBtn
    {
        private boolean isAdd;
        
        public ChangePageNumberBtn(String iconpath, boolean isAdd) {
            super(iconpath, 1);
            this.isAdd = isAdd;
        }
        
        @Override
        public void chooseyes() {
        }
        
        @Override
        public void chooseno() {
        }
        
        @Override
        public void nochoose(MouseEvent e) {
            if (this.isAdd) {
                if (CommodityShowJpanel.this.pageNumber < CommodityShowJpanel.this.pageCount) {
                    CommodityShowJpanel.this.pageNumber++;
                }
                CommodityShowJpanel.this.changeSelectGoods(CommodityShowJpanel.this.selectIndex, -1);
            }
            else {
                if (CommodityShowJpanel.this.pageNumber > 1) {
                    CommodityShowJpanel.this.pageNumber--;
                }
                CommodityShowJpanel.this.changeSelectGoods(CommodityShowJpanel.this.selectIndex, -1);
            }
        }
    }
    
    private class GoodsMouseAdapterListener extends MouseAdapter
    {
        private int index;
        
        public GoodsMouseAdapterListener(int index) {
            this.index = index;
        }
        
        @Override
        public void mousePressed(MouseEvent e) {
            CommodityShowJpanel.this.changeSelectGoods(this.index, e.getButton());
        }
    }
}
