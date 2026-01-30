package org.come.Jpanel.spot.commodity;

import com.tool.tcp.SpriteFactory;
import java.awt.geom.RoundRectangle2D;
import java.awt.RenderingHints;
import org.come.bean.StallPurchase;
import com.tool.image.ImageMixDeal;
import org.come.until.GoodsListFromServerUntil;
import org.come.until.StallPurchaseUntil;
import org.come.until.Util;
import com.tool.tcpimg.UIUtils;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Dimension;
import com.tool.tcp.Sprite;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import com.tool.Stall.CommodityBean;
import javax.swing.JPanel;

public class SpotCommodityShowJpanel extends JPanel
{
    private CommodityBean commodityBean;
    private JLabel headLab;
    private int type;
    private int index;
    private ImageIcon icon;
    private ImageIcon backIcon;
    private boolean isSelect;
    private static Sprite tcp;
    
    public SpotCommodityShowJpanel(CommodityBean commodityBean, int index, int type) {
        this.setLayout(null);
        this.setOpaque(false);
        this.headLab = new JLabel();
        this.init(index, type);
        this.add(this.headLab);
        this.setCommodityBean(commodityBean);
        this.type = type;
        this.index = index;
    }
    
    private void init(int index, int type) {
        switch (type) {
            case 0: {
                this.setPreferredSize(new Dimension(152, 57));
                this.setBounds(5 + index % 4 * 151 - 4, 5 + index / 4 * 55 - 4, 152, 57);
                this.backIcon = new ImageIcon("inkImg/background/zswp.png");
                this.headLab.setBounds(8, 8, 40, 40);
                break;
            }
            case 1: {
                this.setPreferredSize(new Dimension(304, 80));
                this.setBounds(5 + index % 2 * 302 - 4, 8 + index / 2 * 82 - 4, 304, 80);
                this.backIcon = new ImageIcon("inkImg/background/zszs.png");
                this.headLab.setBounds(21, 19, 40, 40);
                break;
            }
            case 2: {
                this.setPreferredSize(new Dimension(304, 80));
                this.setBounds(5 + index % 2 * 302 - 4, 8 + index / 2 * 82 - 4, 304, 80);
                this.backIcon = new ImageIcon("inkImg/background/zslb.png");
                this.headLab.setBounds(21, 19, 40, 40);
                break;
            }
            case 3: {
                this.setPreferredSize(new Dimension(204, 60));
                this.setBounds(4 + index % 3 * 202 - 4, 6 + index / 3 * 60 - 4, 204, 60);
                this.backIcon = new ImageIcon("inkImg/background/zssg.png");
                this.headLab.setBounds(14, 10, 40, 40);
                break;
            }
            case 4: {
                this.setPreferredSize(new Dimension(139, 52));
                this.setBounds(5 + index % 2 * 137 - 4, 5 + index / 2 * 50 - 4, 139, 52);
                this.backIcon = new ImageIcon("inkImg/background/zssgs.png");
                this.headLab.setBounds(8, 8, 35, 35);
                break;
            }
        }
    }
    
    public void setCommodityBean(CommodityBean commodityBean) {
        if (commodityBean != null) {
            if (commodityBean.getType() == 0 || commodityBean.getType() == 3) {
                this.icon = new ImageIcon("img/item/" + commodityBean.getCommoditySkin() + ".png");
            }
            else if (commodityBean.getType() == 1) {
                this.icon = new ImageIcon("img/head/p" + commodityBean.getCommoditySkin() + ".png");
            }
            else {
                this.icon = new ImageIcon("img/lingbao/" + commodityBean.getCommoditySkin() + ".png");
            }
        }
        else {
            this.icon = null;
        }
        if (this.isSelect && (this.commodityBean == null || commodityBean == null || commodityBean.getCommodityId().compareTo(commodityBean.getCommodityId()) != 0)) {
            this.isSelect = false;
        }
        this.commodityBean = commodityBean;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.backIcon != null) {
            g.drawImage(this.backIcon.getImage(), 2, 2, this);
        }
        if (this.isSelect) {
            this.paintCommodityBorder((Graphics2D)g, Color.GREEN, this.getWidth() - 1, this.getHeight() - 1);
        }
        if (this.commodityBean != null) {
            if (this.type == 0) {
                g.drawImage(this.icon.getImage(), 8, 8, 40, 40, this);
                if (this.commodityBean.isSuperpose()) {
                    g.setFont(UIUtils.TEXT_FONT2);
                    g.setColor(Color.WHITE);
                    g.drawString(this.commodityBean.getSum() + "", 10, 20);
                }
                g.setFont(UIUtils.TEXT_FONT1);
                g.setColor(Color.BLACK);
                g.drawString(this.commodityBean.getCommodityName(), 58, 20);
                Util.drawPrice(g, this.commodityBean.getMoney(), 58, 40);
            }
            else if (this.type == 3) {
                g.drawImage(this.icon.getImage(), 14, 10, 40, 40, null);
                if (this.commodityBean.isSuperpose()) {
                    g.setFont(UIUtils.TEXT_FONT2);
                    g.setColor(Color.WHITE);
                    g.drawString(this.commodityBean.getSum() + "", 14, 22);
                }
                g.setFont(UIUtils.TEXT_FONT1);
                g.setColor(Color.BLACK);
                g.drawString(this.commodityBean.getCommodityName(), 60, 26);
                Util.drawPrice(g, this.commodityBean.getMoney(), 60, 46);
                StallPurchase stallPurchase = StallPurchaseUntil.getStallPurchaseByGoodsId(this.commodityBean.getCommodityId());
                if (stallPurchase!=null)
                if (GoodsListFromServerUntil.isContainsGoodsByGoodsIds(stallPurchase.getContainsGoodsIds())) {
                    SpotCommodityShowJpanel.tcp.updateToTime(ImageMixDeal.userimg.getTime(), 0);
                    SpotCommodityShowJpanel.tcp.draw(g, 16, 16);
                }
            }
            else if (this.type == 4) {
                g.drawImage(this.icon.getImage(), 8, 8, 35, 35, this);
                if (this.commodityBean.isSuperpose()) {
                    g.setFont(UIUtils.TEXT_FONT2);
                    g.setColor(Color.WHITE);
                    g.drawString(this.commodityBean.getSum() + "", 10, 20);
                }
                g.setFont(UIUtils.TEXT_FONT1);
                g.setColor(Color.BLACK);
                if (this.commodityBean.getCommodityName() != null) {
                    g.drawString(this.commodityBean.getCommodityName(), 42, 20);
                }
                g.setFont(UIUtils.TEXT_FONT);
                g.setColor(Color.BLACK);
                Util.drawPrice(g, this.commodityBean.getMoney(), 48, 40);
            }
            else {
                g.drawImage(this.icon.getImage(), 24, 21, 35, 35, this);
                g.setFont(UIUtils.TEXT_HY16);
                g.setColor(Color.BLACK);
                g.drawString(this.commodityBean.getCommodityName(), 82, 32);
                g.setFont(UIUtils.TEXT_FONT);
                g.setColor(Color.BLACK);
                g.drawString(this.commodityBean.getCommodityLvl(), 242, 32);
                Util.drawPrice(g, this.commodityBean.getMoney(), 112, 60);
            }
        }
    }
    
    protected void paintCommodityBorder(Graphics2D g2, Color color, int width, int height) {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(color);
        RoundRectangle2D borderShape = new RoundRectangle2D.Float(0.0f, 0.0f, (float)width, (float)height, 10.0f, 10.0f);
        g2.draw(borderShape);
    }
    
    public JLabel getHeadLab() {
        return this.headLab;
    }
    
    public CommodityBean getCommodityBean() {
        return this.commodityBean;
    }
    
    public void setSelect(boolean select) {
        this.isSelect = select;
    }
    
    public boolean isSelect() {
        return this.isSelect;
    }
    
    static {
        SpotCommodityShowJpanel.tcp = SpriteFactory.VloadSprite("inkImg/hongmu/转圈圈.tcp", null);
    }
}
