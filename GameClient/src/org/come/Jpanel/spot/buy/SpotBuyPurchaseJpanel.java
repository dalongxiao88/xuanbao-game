package org.come.Jpanel.spot.buy;

import javax.swing.BorderFactory;
import org.come.until.Util;
import java.awt.Graphics;
import com.tool.Stall.Stall;
import org.come.socket.SendMessageUntil;
import org.come.until.GsonUtil;
import org.come.socket.Agreement;
import com.tool.Stall.StallBuy;
import org.come.Frame.ZhuFrame;
import org.apache.commons.lang.StringUtils;
import org.come.entity.Goodstable;
import java.util.List;
import org.come.bean.StallPurchase;
import org.come.until.GoodsListFromServerUntil;
import org.come.until.StallPurchaseUntil;
import com.tool.Stall.CommodityBean;
import com.tool.tcpimg.UIUtils;
import com.tool.btn.spot.SpotPublishBtn;
import org.come.Jpanel.spot.box.SpotBuyBoxJpanel;
import org.come.Jpanel.spot.commodity.CommodityShowJpanel;
import org.come.Jpanel.spot.commodity.SpotShowJpanel;

public class SpotBuyPurchaseJpanel extends SpotBuyBaseJpanel
{
    private final SpotShowJpanel showJpanel;
    private final CommodityShowJpanel commodityShowJpanel;
    private int selectIndex;
    
    public SpotBuyPurchaseJpanel(SpotBuyBoxJpanel spotBuyBoxJpanel) {
        super(spotBuyBoxJpanel, "inkImg/background/S339.png", "purchase");
        this.selectIndex = -1;
        (this.showJpanel = new SpotShowJpanel(this, 3)).setBounds(44, 76, 612, 338);
        this.add(this.showJpanel);
        (this.commodityShowJpanel = new CommodityShowJpanel(this)).setBounds(44, 350, 630, 64);
        this.add(this.commodityShowJpanel);
    }
    
    @Override
    public SpotPublishBtn createConductTransactionsBtn() {
        return new SpotPublishBtn("inkImg/button/B409.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, "出售", 10, this);
    }
    
    @Override
    public void setCurrentCommodity(CommodityBean commodity, int sum) {
        this.commodity = commodity;
        this.labUnitprice.setText("");
        this.labTotalPrice.setText("");
        if (commodity != null) {
            this.setPrice(this.labUnitprice, commodity.getMoney());
            StallPurchase stallPurchase = StallPurchaseUntil.getStallPurchaseByGoodsId(commodity.getCommodityId());
            List<Goodstable> goodsList = GoodsListFromServerUntil.getGoodsByGoodsIds(stallPurchase.getContainsGoodsIds());
            this.commodityShowJpanel.init(goodsList);
        }
        else {
            this.commodityShowJpanel.init(null);
        }
    }
    
    public void showSelectGoods(String skin, int sum) {
        if (this.commodity != null) {
            if (sum > 0) {
                this.textNumber.setText(sum + "");
            }
            else {
                this.textNumber.setText("");
            }
            if (StringUtils.isNotBlank(skin)) {
                this.labCommodity.setIcon(GoodsListFromServerUntil.imgpathAdaptive(skin, 48, 48));
            }
            else {
                this.labCommodity.setIcon(null);
            }
        }
        else {
            this.textNumber.setText(sum + "");
            this.labCommodity.setIcon(null);
        }
    }
    
    @Override
    public void conductTransactions() {
        CommodityBean commodity = this.getCommodity();
        Goodstable goods = this.commodityShowJpanel.getSelectGoods();
        if (commodity == null || goods == null) {
            ZhuFrame.getZhuJpanel().addPrompt2("你还没有选中商品");
            return;
        }
        int sum = this.getNumber();
        if (sum <= 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("你出售数量为0");
            return;
        }
        if ((int)goods.getUsetime() < sum) {
            ZhuFrame.getZhuJpanel().addPrompt2("物品数量不足");
            return;
        }
        StallBuy stallBuy = new StallBuy();
        stallBuy.setType(3);
        Stall stall = this.getStall();
        stallBuy.setId(stall.getId());
        stallBuy.setRoleid(stall.getRoleid());
        stallBuy.setBuyid(commodity.getCommodityId());
        stallBuy.setCommodityId(goods.getRgid());
        stallBuy.setSum(sum);
        SendMessageUntil.toServer(Agreement.getAgreement().stallbuyAgreement(GsonUtil.getGsonUtil().getgson().toJson(stallBuy)));
        goods.goodxh(sum);
    }
    
    @Override
    public void updateCommoditys() {
        List<CommodityBean> goodsList = this.getStall().getCollectGoodsList();
        this.showJpanel.updateCommoditys(goodsList, this.pageNumber);
    }
    
    @Override
    public void showSelelctBorder(int type, int index) {
    }
    
    @Override
    public void showEnteredBorder(int type, int index) {
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Util.drawMoney(g, 372, 466);
        this.commodityShowJpanel.setBorder(BorderFactory.createEmptyBorder());
    }
    
    @Override
    public int getGoodsNumber() {
        Goodstable selectGoods = this.commodityShowJpanel.getSelectGoods();
        if (selectGoods != null) {
            return (int)selectGoods.getUsetime();
        }
        return 0;
    }
}
