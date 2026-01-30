package org.come.Jpanel.spot.buy;

import javax.swing.ImageIcon;
import java.awt.event.MouseEvent;
import com.tool.btn.MoBanBtn;
import org.come.until.Util;
import java.awt.Graphics;
import com.tool.btn.BtnUtil;
import java.util.stream.Collectors;
import java.util.Comparator;
import java.util.List;
import com.tool.Stall.Stall;
import java.util.Map;
import org.come.bean.ConfigureBean;
import org.come.entity.Goodstable;
import org.come.bean.LoginResult;
import org.come.socket.SendMessageUntil;
import org.come.until.GsonUtil;
import org.come.socket.Agreement;
import org.come.model.Configure;
import org.come.until.UserMessUntil;
import com.tool.Stall.StallBuy;
import java.math.BigDecimal;
import com.tool.role.RoleData;
import org.come.Frame.ZhuFrame;
import org.come.until.CutButtonImage;
import org.come.until.GoodsListFromServerUntil;
import com.tool.Stall.CommodityBean;
import com.tool.tcpimg.UIUtils;
import com.tool.btn.spot.SpotPublishBtn;
import org.come.Jpanel.spot.box.SpotBuyBoxJpanel;
import org.come.Jpanel.spot.commodity.SpotShowLayoutJpanel;
import com.tool.btn.spot.CommoditySwitchBtn;
import org.come.Jpanel.spot.BuyBox;

public class SpotBuySellJpanel extends SpotBuyBaseJpanel implements BuyBox
{
    private final ChangeBtn[] changeBtns;
    private final CommoditySwitchBtn[] commoditySwitchBtns;
    protected SpotShowLayoutJpanel spotShowLayoutJpanel;
    private final SortBtn sortBtn;
    private boolean isReverse;
    private int index;
    
    public SpotBuySellJpanel(SpotBuyBoxJpanel spotBuyBoxJpanel) {
        super(spotBuyBoxJpanel, "inkImg/background/S338.png", "sell");
        this.changeBtns = new ChangeBtn[5];
        this.commoditySwitchBtns = new CommoditySwitchBtn[3];
        this.index = 0;
        this.spotShowLayoutJpanel = new SpotShowLayoutJpanel(this);
        for (int i = 0; i < this.commoditySwitchBtns.length; ++i) {
            (this.commoditySwitchBtns[i] = new CommoditySwitchBtn("inkImg/button/B" + (326 + i * 2) + ".png", "inkImg/button/B" + (325 + i * 2) + ".png", this, i, i == 0)).setBounds(59 + i * 92, 52, 90, 26);
        }
        for (int i = 0; i < this.changeBtns.length; ++i) {
            (this.changeBtns[i] = new ChangeBtn("inkImg/button/" + (41 + i) + ".png", i + 1)).setBounds(656, 86 + i * 36, 39, 31);
        }
        (this.sortBtn = new SortBtn("inkImg/button/B339.png", "inkImg/button/B340.png")).setBounds(620, 56, 34, 17);
        this.spotShowLayoutJpanel.setBounds(44, 76, 612, 338);
        for (int i = 0; i < this.commoditySwitchBtns.length; ++i) {
            this.add(this.commoditySwitchBtns[i]);
        }
        for (int i = 0; i < this.changeBtns.length; ++i) {
            this.add(this.changeBtns[i]);
        }
        this.add(this.sortBtn);
        this.add(this.spotShowLayoutJpanel);
    }
    
    @Override
    public SpotPublishBtn createConductTransactionsBtn() {
        return new SpotPublishBtn("inkImg/button/B409.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, "购买", 10, this);
    }
    
    @Override
    public void setCurrentCommodity(CommodityBean commodity, int sum) {
        this.commodity = commodity;
        if (commodity != null) {
            if (sum > 0) {
                this.textNumber.setText(sum + "");
            }
            if (commodity.getType() == 0 || commodity.getType() == 3) {
                this.labCommodity.setIcon(GoodsListFromServerUntil.imgpathAdaptive(commodity.getCommoditySkin(), 48, 48));
            }
            else if (commodity.getType() == 1) {
                this.labCommodity.setIcon(CutButtonImage.getImage("img/head/p" + commodity.getCommoditySkin() + ".png", 48, 48));
            }
            else if (commodity.getType() == 2) {
                this.labCommodity.setIcon(CutButtonImage.getImage("img/lingbao/" + commodity.getCommoditySkin() + ".png", 48, 48));
            }
            this.setPrice(this.labUnitprice, commodity.getMoney());
            this.setPrice(this.labTotalPrice, commodity.getMoney());
        }
        else {
            this.textNumber.setText("");
            this.labUnitprice.setText("");
            this.labTotalPrice.setText("");
            this.labCommodity.setIcon(null);
        }
    }
    
    @Override
    public void conductTransactions() {
        CommodityBean commodity = this.getCommodity();
        if (commodity == null) {
            ZhuFrame.getZhuJpanel().addPrompt2("你还没有选中商品");
            return;
        }
        int sum = this.getNumber();
        if (sum <= 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("你购买数量为0");
            return;
        }
        if (commodity.getSum() < sum) {
            ZhuFrame.getZhuJpanel().addPrompt2("物品数量不足");
            return;
        }
        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
        if (loginResult.getGold().compareTo(commodity.getMoney().multiply(BigDecimal.valueOf((long)sum))) < 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("金钱不足");
            return;
        }
        StallBuy stallBuy = new StallBuy();
        if (commodity.getType() == 0) {
            Goodstable goods = commodity.getGoods();
            int packSum = GoodsListFromServerUntil.Surplussum("" + goods.getType(), "" + goods.getGoodsid(), sum);
            if (packSum <= 0) {
                ZhuFrame.getZhuJpanel().addPrompt2("你背包已满");
                return;
            }
            stallBuy.setType(0);
        }
        else if (commodity.getType() == 1) {
            ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
            Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
            Configure configure = (Configure)item.get(new BigDecimal(1));
            if (UserMessUntil.getPetListTable() != null && UserMessUntil.getPetListTable().size() >= Integer.parseInt(configure.getXdzhssx())) {
                ZhuFrame.getZhuJpanel().addPrompt2("您的召唤兽可携带的数量已满！！！");
                return;
            }
            stallBuy.setType(1);
        }
        else if (commodity.getType() == 2) {
            stallBuy.setType(2);
        }
        Stall stall = this.getStall();
        stallBuy.setId(stall.getId());
        stallBuy.setBuyid(commodity.getCommodityId());
        stallBuy.setSum(sum);
        stallBuy.setRoleid(loginResult.getRole_id());
        SendMessageUntil.toServer(Agreement.getAgreement().stallbuyAgreement(GsonUtil.getGsonUtil().getgson().toJson(stallBuy)));
    }
    
    @Override
    public void updateCommoditys() {
        List<CommodityBean> commodityList = null;
        if (this.index == 0) {
            commodityList = this.getStall().getGoodsList();
        }
        else if (this.index == 1) {
            commodityList = this.getStall().getPetList();
        }
        else if (this.index == 2) {
            commodityList = this.getStall().getBaoList();
        }
        if (this.isReverse) {
            commodityList = (List)commodityList.stream().sorted(Comparator.comparingLong(CommodityBean::getMoneyToLong).reversed()).collect(Collectors.toList());
        }
        else {
            commodityList = (List)commodityList.stream().sorted(Comparator.comparingLong(CommodityBean::getMoneyToLong)).collect(Collectors.toList());
        }
        BtnUtil.btnBinding(this.changeBtns, this.pageNumber - 1);
        this.spotShowLayoutJpanel.updateCommoditys(commodityList, this.pageNumber);
    }
    
    @Override
    public CommoditySwitchBtn[] getCommoditySwitchBtns() {
        return this.commoditySwitchBtns;
    }
    
    @Override
    public void switchTo(int index) {
        this.index = index;
        this.spotShowLayoutJpanel.cardLayoutShow((index == 0) ? "goods" : ((index == 1) ? "pet" : "bao"));
        this.updateCommoditys();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Util.drawMoney(g, 372, 466);
    }
    
    @Override
    public int getGoodsNumber() {
        return this.commodity.getSum();
    }
    
    private class ChangeBtn extends MoBanBtn
    {
        private int index;
        
        public ChangeBtn(String iconPath, int index) {
            super(iconPath, 2);
            this.index = index;
            if (index == 0) {
                this.btnchange(2);
            }
        }
        
        @Override
        public void chooseyes() {
            SpotBuySellJpanel.this.pageNumber = this.index;
            SpotBuySellJpanel.this.updateCommoditys();
        }
        
        @Override
        public void chooseno() {
        }
        
        @Override
        public void nochoose(MouseEvent e) {
        }
    }
    
    private class SortBtn extends MoBanBtn
    {
        private ImageIcon[][] reverseIcons;
        
        public SortBtn(String path1, String path2) {
            super("", 1);
            this.setBackIcons(path1, path2);
            this.selectBtnt(SpotBuySellJpanel.this.isReverse);
        }
        
        private void setBackIcons(String path1, String path2) {
            try {
                (this.reverseIcons = new ImageIcon[2][3])[0] = CutButtonImage.cuts(path1);
                this.reverseIcons[1] = CutButtonImage.cuts(path2);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        private void selectBtnt() {
            SpotBuySellJpanel.this.isReverse = !SpotBuySellJpanel.this.isReverse;
            this.selectBtnt(SpotBuySellJpanel.this.isReverse);
        }
        
        private void selectBtnt(boolean isReverse) {
            if (isReverse) {
                this.setIcons(this.reverseIcons[1]);
            }
            else {
                this.setIcons(this.reverseIcons[0]);
            }
        }
        
        @Override
        public void chooseyes() {
        }
        
        @Override
        public void chooseno() {
        }
        
        @Override
        public void nochoose(MouseEvent e) {
            this.selectBtnt();
            SpotBuySellJpanel.this.updateCommoditys();
        }
    }
}
