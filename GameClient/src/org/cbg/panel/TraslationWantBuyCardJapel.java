package org.cbg.panel;

import java.awt.Dimension;
import java.awt.CardLayout;
import javax.swing.JPanel;

public class TraslationWantBuyCardJapel extends JPanel
{
    private CardLayout cardLayout;
    private TraslationWantBuyShouyeCardJpanel traslationWantBuyShouyeCardJpanel;
    private TraslationWantBuyDahuabiJpanel traslationWantBuyDahuabiJpanel;
    private TraslationWantBuyDaojuJpanel traslationWantBuyDaojuJpanel;
    private TraslationWantBuyZhaohuanshouJpanel traslationWantBuyZhaohuanshouJpanel;
    private TraslationWantBuyZhaungbeiJpanel traslationWantBuyZhaungbeiJpanel;
    private TraslationWantBuyLingbaoJpanel traslationWantBuyLingbaoJpanel;
    private TraslationWantBuyGongshiqiJpanel traslationWantBuyGongshiqiJpanel;
    private TraslationWantBuyShoucangJpanel traslationWantBuyShoucangJpanel;
    
    public void setTraslationWantBuyShouyeCardJpanel(TraslationWantBuyShouyeCardJpanel traslationWantBuyShouyeCardJpanel) {
        this.traslationWantBuyShouyeCardJpanel = traslationWantBuyShouyeCardJpanel;
    }
    
    public CardLayout getCardLayout() {
        return this.cardLayout;
    }
    
    public void setCardLayout(CardLayout cardLayout) {
        this.cardLayout = cardLayout;
    }
    
    public TraslationWantBuyShouyeCardJpanel getTraslationWantBuyShouyeCardJpanel() {
        return this.traslationWantBuyShouyeCardJpanel;
    }
    
    public void setTraslationWantBuyShouyeCardJpanel(TraslationWantBuyShouyeJpanel traslationWantBuyShouyeJpanel) {
    }
    
    public TraslationWantBuyDahuabiJpanel getTraslationWantBuyDahuabiJpanel() {
        return this.traslationWantBuyDahuabiJpanel;
    }
    
    public void setTraslationWantBuyDahuabiJpanel(TraslationWantBuyDahuabiJpanel traslationWantBuyDahuabiJpanel) {
        this.traslationWantBuyDahuabiJpanel = traslationWantBuyDahuabiJpanel;
    }
    
    public TraslationWantBuyDaojuJpanel getTraslationWantBuyDaojuJpanel() {
        return this.traslationWantBuyDaojuJpanel;
    }
    
    public void setTraslationWantBuyDaojuJpanel(TraslationWantBuyDaojuJpanel traslationWantBuyDaojuJpanel) {
        this.traslationWantBuyDaojuJpanel = traslationWantBuyDaojuJpanel;
    }
    
    public TraslationWantBuyZhaohuanshouJpanel getTraslationWantBuyZhaohuanshouJpanel() {
        return this.traslationWantBuyZhaohuanshouJpanel;
    }
    
    public void setTraslationWantBuyZhaohuanshouJpanel(TraslationWantBuyZhaohuanshouJpanel traslationWantBuyZhaohuanshouJpanel) {
        this.traslationWantBuyZhaohuanshouJpanel = traslationWantBuyZhaohuanshouJpanel;
    }
    
    public TraslationWantBuyZhaungbeiJpanel getTraslationWantBuyZhaungbeiJpanel() {
        return this.traslationWantBuyZhaungbeiJpanel;
    }
    
    public void setTraslationWantBuyZhaungbeiJpanel(TraslationWantBuyZhaungbeiJpanel traslationWantBuyZhaungbeiJpanel) {
        this.traslationWantBuyZhaungbeiJpanel = traslationWantBuyZhaungbeiJpanel;
    }
    
    public TraslationWantBuyLingbaoJpanel getTraslationWantBuyLingbaoJpanel() {
        return this.traslationWantBuyLingbaoJpanel;
    }
    
    public void setTraslationWantBuyLingbaoJpanel(TraslationWantBuyLingbaoJpanel traslationWantBuyLingbaoJpanel) {
        this.traslationWantBuyLingbaoJpanel = traslationWantBuyLingbaoJpanel;
    }
    
    public TraslationWantBuyGongshiqiJpanel getTraslationWantBuyGongshiqiJpanel() {
        return this.traslationWantBuyGongshiqiJpanel;
    }
    
    public void setTraslationWantBuyGongshiqiJpanel(TraslationWantBuyGongshiqiJpanel traslationWantBuyGongshiqiJpanel) {
        this.traslationWantBuyGongshiqiJpanel = traslationWantBuyGongshiqiJpanel;
    }
    
    public TraslationWantBuyShoucangJpanel getTraslationWantBuyShoucangJpanel() {
        return this.traslationWantBuyShoucangJpanel;
    }
    
    public void setTraslationWantBuyShoucangJpanel(TraslationWantBuyShoucangJpanel traslationWantBuyShoucangJpanel) {
        this.traslationWantBuyShoucangJpanel = traslationWantBuyShoucangJpanel;
    }
    
    public TraslationWantBuyCardJapel() {
        this.cardLayout = new CardLayout();
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(590, 380));
        this.traslationWantBuyShouyeCardJpanel = new TraslationWantBuyShouyeCardJpanel();
        this.traslationWantBuyDahuabiJpanel = new TraslationWantBuyDahuabiJpanel();
        this.traslationWantBuyDaojuJpanel = new TraslationWantBuyDaojuJpanel();
        this.traslationWantBuyZhaohuanshouJpanel = new TraslationWantBuyZhaohuanshouJpanel();
        this.traslationWantBuyZhaungbeiJpanel = new TraslationWantBuyZhaungbeiJpanel();
        this.traslationWantBuyLingbaoJpanel = new TraslationWantBuyLingbaoJpanel();
        this.traslationWantBuyGongshiqiJpanel = new TraslationWantBuyGongshiqiJpanel();
        this.traslationWantBuyShoucangJpanel = new TraslationWantBuyShoucangJpanel();
        this.setLayout(this.cardLayout);
        this.add(this.traslationWantBuyShouyeCardJpanel, "shouye");
        this.add(this.traslationWantBuyDahuabiJpanel, "dahuabi");
        this.add(this.traslationWantBuyDaojuJpanel, "daoju");
        this.add(this.traslationWantBuyZhaungbeiJpanel, "zhuangbei");
        this.add(this.traslationWantBuyLingbaoJpanel, "lingbao");
        this.add(this.traslationWantBuyZhaohuanshouJpanel, "zhaohuanshou");
        this.add(this.traslationWantBuyGongshiqiJpanel, "gongshiqi");
        this.add(this.traslationWantBuyShoucangJpanel, "shoucang");
        this.setBounds(0, 27, 590, 380);
    }
}
