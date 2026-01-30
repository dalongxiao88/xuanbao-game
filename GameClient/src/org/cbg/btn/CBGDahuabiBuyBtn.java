package org.cbg.btn;

import org.cbg.panel.TraslationCommodityMainJPane;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import java.math.BigDecimal;
import org.come.until.CutButtonImage;
import org.cbg.frame.TrslationMainJframe;
import org.cbg.frame.TraslationCommodityJFrame;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
import org.cbg.panel.TraslationWantBuyDaojuModelJpanel;
import org.cbg.panel.TraslationWantBuyDahuabiModelJpanel;
import com.tool.btn.MoBanBtn;

public class CBGDahuabiBuyBtn extends MoBanBtn
{
    private int buyOrDa;
    private TraslationWantBuyDahuabiModelJpanel traslationWantBuyDahuabiModelJpanel;
    private TraslationWantBuyDaojuModelJpanel traslationWantBuyDaojuModelJpanel;
    
    public CBGDahuabiBuyBtn(String iconpath, int type, Color[] colors, Font font, int buyOrDa, String text, TraslationWantBuyDahuabiModelJpanel traslationWantBuyDahuabiModelJpanel) {
        super(iconpath, type, colors);
        this.setText(text);
        this.buyOrDa = buyOrDa;
        this.traslationWantBuyDahuabiModelJpanel = traslationWantBuyDahuabiModelJpanel;
        this.setFont(font);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    public CBGDahuabiBuyBtn(String iconpath, int type, Color[] colors, Font font, int buyOrDa, String text, TraslationWantBuyDaojuModelJpanel traslationWantBuyDaojuModelJpanel) {
        super(iconpath, type, colors);
        this.setText(text);
        this.buyOrDa = buyOrDa;
        this.traslationWantBuyDaojuModelJpanel = traslationWantBuyDaojuModelJpanel;
        this.setFont(font);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        TraslationCommodityMainJPane traslationCommodityMainJPane = TraslationCommodityJFrame.getTraslationCommodityJFrame().getTraslationCommodityMainJPane();
        if (this.buyOrDa == 1) {
            CBGDahuabiBuyBtn source = (CBGDahuabiBuyBtn)e.getSource();
            TrslationMainJframe.getTrslationMainJframe().setShoucangBtn(source.getParent().getComponent(2));
            traslationCommodityMainJPane.cleanNeirong();
            traslationCommodityMainJPane.setName(this.traslationWantBuyDahuabiModelJpanel.getTupian().getName());
            traslationCommodityMainJPane.getNumber().setText(this.traslationWantBuyDahuabiModelJpanel.getTupian().getName());
            if (TrslationMainJframe.getTrslationMainJframe().getPanelOpen() == 8) {
                traslationCommodityMainJPane.getTupian().setIcon(CutButtonImage.getCBG((int)this.traslationWantBuyDahuabiModelJpanel.getCollection().getSaletype(), this.traslationWantBuyDahuabiModelJpanel.getCollection().getSaleskin(), 55, 53));
            }
            else {
                traslationCommodityMainJPane.getTupian().setIcon(CutButtonImage.getCBG((int)this.traslationWantBuyDahuabiModelJpanel.getSalegoods().getSaletype(), this.traslationWantBuyDahuabiModelJpanel.getSalegoods().getSaleskin(), 55, 53));
            }
            traslationCommodityMainJPane.getWupinming().setText(this.traslationWantBuyDahuabiModelJpanel.getDahuabixia().getText());
            traslationCommodityMainJPane.getJine().setText(this.traslationWantBuyDahuabiModelJpanel.getPrice().getText());
            traslationCommodityMainJPane.getWupinming().setName(this.traslationWantBuyDahuabiModelJpanel.getDahuabixia().getName());
            traslationCommodityMainJPane.getTupian().setName(this.traslationWantBuyDahuabiModelJpanel.getBuy().getName());
            if (this.traslationWantBuyDahuabiModelJpanel.getGongshiqiBiaoshi() == 0) {
                traslationCommodityMainJPane.getPublicitytime().setText(this.traslationWantBuyDahuabiModelJpanel.getDahuabishang().getName());
                traslationCommodityMainJPane.getSellingtime().setText("--");
                traslationCommodityMainJPane.setViewTrue(true);
                traslationCommodityMainJPane.getIncreaseAmount().setText("<html><body>同意支付<font color='#FF0000' >" + new BigDecimal(this.traslationWantBuyDahuabiModelJpanel.getPrice().getText()).multiply(new BigDecimal(0.2)).setScale(0, 4) + "</font>元预定费</body></html>");
                traslationCommodityMainJPane.getJine().setText(this.traslationWantBuyDahuabiModelJpanel.getPrice().getText() + "+" + new BigDecimal(this.traslationWantBuyDahuabiModelJpanel.getPrice().getText()).multiply(new BigDecimal(0.2)).setScale(0, 4));
            }
            else {
                traslationCommodityMainJPane.getPublicitytime().setText("--");
                traslationCommodityMainJPane.getSellingtime().setText(this.traslationWantBuyDahuabiModelJpanel.getDahuabishang().getName());
                traslationCommodityMainJPane.setViewTrue(false);
            }
            traslationCommodityMainJPane.getSellerID().setText(this.traslationWantBuyDahuabiModelJpanel.getPrice().getName());
            if (this.traslationWantBuyDahuabiModelJpanel.getDanjia().getName() != null) {
                traslationCommodityMainJPane.getAssignID().setText(this.traslationWantBuyDahuabiModelJpanel.getDanjia().getName());
            }
            if (this.traslationWantBuyDahuabiModelJpanel.getTupian().getName().equals(traslationCommodityMainJPane.getName())) {
                traslationCommodityMainJPane.getShoucang().setIcons(this.traslationWantBuyDahuabiModelJpanel.getShoucang().getIcons());
            }
            String sendmes = Agreement.getAgreement().searchBuyAgreement(this.traslationWantBuyDahuabiModelJpanel.getTupian().getName());
            SendMessageUntil.toServer(sendmes);
        }
        else {
            CBGDahuabiBuyBtn source = (CBGDahuabiBuyBtn)e.getSource();
            TrslationMainJframe.getTrslationMainJframe().setShoucangBtn(source.getParent().getComponent(2));
            traslationCommodityMainJPane.cleanNeirong();
            traslationCommodityMainJPane.setName(this.traslationWantBuyDaojuModelJpanel.getTupian().getName());
            traslationCommodityMainJPane.getNumber().setText(this.traslationWantBuyDaojuModelJpanel.getTupian().getName());
            if (this.traslationWantBuyDaojuModelJpanel.getSalegoods() == null) {
                traslationCommodityMainJPane.getTupian().setIcon(CutButtonImage.getCBG((int)this.traslationWantBuyDaojuModelJpanel.getCollection().getSaletype(), this.traslationWantBuyDaojuModelJpanel.getCollection().getSaleskin(), 55, 53));
            }
            else {
                traslationCommodityMainJPane.getTupian().setIcon(CutButtonImage.getCBG((int)this.traslationWantBuyDaojuModelJpanel.getSalegoods().getSaletype(), this.traslationWantBuyDaojuModelJpanel.getSalegoods().getSaleskin(), 55, 53));
            }
            traslationCommodityMainJPane.getWupinming().setText(this.traslationWantBuyDaojuModelJpanel.getDahuabixia().getText());
            traslationCommodityMainJPane.getJine().setText(this.traslationWantBuyDaojuModelJpanel.getPrice().getText());
            traslationCommodityMainJPane.getWupinming().setName(this.traslationWantBuyDaojuModelJpanel.getDahuabixia().getName());
            traslationCommodityMainJPane.getTupian().setName(this.traslationWantBuyDaojuModelJpanel.getBuy().getName());
            if (this.traslationWantBuyDaojuModelJpanel.getGongshiqiBiaoshi() == 0) {
                traslationCommodityMainJPane.getPublicitytime().setText(this.traslationWantBuyDaojuModelJpanel.getDanjia().getText());
                traslationCommodityMainJPane.getSellingtime().setText("--");
                traslationCommodityMainJPane.setViewTrue(true);
                traslationCommodityMainJPane.getIncreaseAmount().setText("<html><body>同意支付<font color='#FF0000' >" + new BigDecimal(this.traslationWantBuyDaojuModelJpanel.getPrice().getText()).multiply(new BigDecimal(0.2)).setScale(0, 4) + "</font>元宝预定费</body></html>");
                traslationCommodityMainJPane.getJine().setText(this.traslationWantBuyDaojuModelJpanel.getPrice().getText() + "+" + new BigDecimal(this.traslationWantBuyDaojuModelJpanel.getPrice().getText()).multiply(new BigDecimal(0.2)).setScale(0, 4));
            }
            else {
                traslationCommodityMainJPane.getPublicitytime().setText("--");
                traslationCommodityMainJPane.getSellingtime().setText(this.traslationWantBuyDaojuModelJpanel.getDanjia().getText());
                traslationCommodityMainJPane.setViewTrue(false);
            }
            traslationCommodityMainJPane.getSellerID().setText(this.traslationWantBuyDaojuModelJpanel.getPrice().getName());
            if (this.traslationWantBuyDaojuModelJpanel.getDanjia().getName() != null) {
                traslationCommodityMainJPane.getAssignID().setText(this.traslationWantBuyDaojuModelJpanel.getDanjia().getName());
            }
            if (this.traslationWantBuyDaojuModelJpanel.getTupian().getName().equals(traslationCommodityMainJPane.getName())) {
                traslationCommodityMainJPane.getShoucang().setIcons(this.traslationWantBuyDaojuModelJpanel.getShoucang().getIcons());
            }
            String sendmes = Agreement.getAgreement().searchBuyAgreement(this.traslationWantBuyDaojuModelJpanel.getTupian().getName());
            SendMessageUntil.toServer(sendmes);
        }
    }
}
