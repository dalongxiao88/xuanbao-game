package org.come.Jpanel;

import com.tool.tcpimg.UIUtils;
import java.awt.Color;
import java.awt.CardLayout;
import javax.swing.JPanel;

public class TaobaoCourtCardJpanel extends JPanel
{
    private CardLayout cardLayout;
    private RechargeJpanel rechargeJpanel;
    private QuotaJpanel quotaJpanel;
    private FundBuyJpanel fundBuyJpanel;
    private MonthlyCardJpanel monthlyCardJpanel;
    private CumulativeJpanel cumulativeJpanel;
    private TaobaoCourtSplendidJpanel taobaoCourtSplendidJpanel;
    private ShopOnlineJpanel shopOnlineJpanel;
    private VIPShopJpanel vipShopJpanel;
    private int menuType;
    
    public TaobaoCourtCardJpanel() {
        this.menuType = 2;
        this.cumulativeJpanel = new CumulativeJpanel();
        this.cardLayout = new CardLayout();
        this.rechargeJpanel = new RechargeJpanel();
        this.quotaJpanel = new QuotaJpanel();
        this.fundBuyJpanel = new FundBuyJpanel();
        this.monthlyCardJpanel = new MonthlyCardJpanel();
        this.taobaoCourtSplendidJpanel = new TaobaoCourtSplendidJpanel();
        this.shopOnlineJpanel = new ShopOnlineJpanel();
        this.vipShopJpanel = new VIPShopJpanel();
        this.setBounds(0, 57, 702, 445);
        this.setOpaque(false);
        this.setBackground(Color.red);
        this.setLayout(this.cardLayout);
        this.setBackground(UIUtils.Color_BACK);
        this.add(this.shopOnlineJpanel, "shopOnline");
        this.add(this.taobaoCourtSplendidJpanel, "taobaoCourt");
        this.add(this.rechargeJpanel, "recharge");
        this.add(this.quotaJpanel, "quota");
        this.add(this.fundBuyJpanel, "fundBuy");
        this.add(this.monthlyCardJpanel, "monthCard");
        this.add(this.cumulativeJpanel, "leichong");
        this.add(this.vipShopJpanel, "vipshop");
    }
    
    public CardLayout getCardLayout() {
        return this.cardLayout;
    }
    
    public RechargeJpanel getRechargeJpanel() {
        return this.rechargeJpanel;
    }
    
    public void setRechargeJpanel(RechargeJpanel rechargeJpanel) {
        this.rechargeJpanel = rechargeJpanel;
    }
    
    public QuotaJpanel getQuotaJpanel() {
        return this.quotaJpanel;
    }
    
    public void setQuotaJpanel(QuotaJpanel quotaJpanel) {
        this.quotaJpanel = quotaJpanel;
    }
    
    public FundBuyJpanel getFundBuyJpanel() {
        return this.fundBuyJpanel;
    }
    
    public void setFundBuyJpanel(FundBuyJpanel fundBuyJpanel) {
        this.fundBuyJpanel = fundBuyJpanel;
    }
    
    public MonthlyCardJpanel getMonthlyCardJpanel() {
        return this.monthlyCardJpanel;
    }
    
    public void setMonthlyCardJpanel(MonthlyCardJpanel monthlyCardJpanel) {
        this.monthlyCardJpanel = monthlyCardJpanel;
    }
    
    public CumulativeJpanel getCumulativeJpanel() {
        return this.cumulativeJpanel;
    }
    
    public void setCumulativeJpanel(CumulativeJpanel cumulativeJpanel) {
        this.cumulativeJpanel = cumulativeJpanel;
    }
    
    public TaobaoCourtSplendidJpanel getTaobaoCourtSplendidJpanel() {
        return this.taobaoCourtSplendidJpanel;
    }
    
    public void setTaobaoCourtSplendidJpanel(TaobaoCourtSplendidJpanel taobaoCourtSplendidJpanel) {
        this.taobaoCourtSplendidJpanel = taobaoCourtSplendidJpanel;
    }
    
    public void setCardLayout(CardLayout cardLayout) {
        this.cardLayout = cardLayout;
    }
    
    public ShopOnlineJpanel getShopOnlineJpanel() {
        return this.shopOnlineJpanel;
    }
    
    public void setShopOnlineJpanel(ShopOnlineJpanel shopOnlineJpanel) {
        this.shopOnlineJpanel = shopOnlineJpanel;
    }
    
    public VIPShopJpanel getVipShopJpanel() {
        return this.vipShopJpanel;
    }
    
    public void setVipShopJpanel(VIPShopJpanel vipShopJpanel) {
        this.vipShopJpanel = vipShopJpanel;
    }
    
    public int getMenuType() {
        return this.menuType;
    }
    
    public void setMenuType(int menuType) {
        this.menuType = menuType;
    }
}
