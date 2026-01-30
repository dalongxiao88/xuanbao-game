package org.cbg.panel;

import org.come.entity.RoleSummoning;
import org.come.until.UserMessUntil;
import java.awt.CardLayout;
import javax.swing.JPanel;

public class TraslationWantSendJishoushangpinFenleiJpanel extends JPanel
{
    private CardLayout cardLayout;
    private TraslationWantSendJishoushangpinFenleiWupinJpanel traslationWantSendJishoushangpinFenleiWupinJpanel;
    private TraslationWantSendJishoushangpinFenleiZhaohuanshouJpanel traslationWantSendJishoushangpinFenleiZhaohuanshouJpanel;
    
    public TraslationWantSendJishoushangpinFenleiJpanel() {
        this.cardLayout = new CardLayout();
        this.setBackground(null);
        this.setLayout(this.cardLayout);
        this.traslationWantSendJishoushangpinFenleiWupinJpanel = new TraslationWantSendJishoushangpinFenleiWupinJpanel();
        this.traslationWantSendJishoushangpinFenleiZhaohuanshouJpanel = new TraslationWantSendJishoushangpinFenleiZhaohuanshouJpanel();
        this.add(this.traslationWantSendJishoushangpinFenleiWupinJpanel, "wupin");
        this.add(this.traslationWantSendJishoushangpinFenleiZhaohuanshouJpanel, "zhaohuanshou");
        this.setBounds(38, 10, 310, 259);
        this.setOpaque(false);
    }
    
    public void change(int i) {
        if (i == 0 || i == 1) {
            this.cardLayout.show(this, "wupin");
            this.traslationWantSendJishoushangpinFenleiWupinJpanel.change(i);
        }
        else if (i == 2) {
            for (int k = 0; k < UserMessUntil.getPetListTable().size(); ++k) {
                this.traslationWantSendJishoushangpinFenleiZhaohuanshouJpanel.chang((RoleSummoning)UserMessUntil.getPetListTable().get(k), true);
            }
            this.cardLayout.show(this, "zhaohuanshou");
        }
    }
    
    public CardLayout getCardLayout() {
        return this.cardLayout;
    }
    
    public void setCardLayout(CardLayout cardLayout) {
        this.cardLayout = cardLayout;
    }
    
    public TraslationWantSendJishoushangpinFenleiWupinJpanel getTraslationWantSendJishoushangpinFenleiWupinJpanel() {
        return this.traslationWantSendJishoushangpinFenleiWupinJpanel;
    }
    
    public void setTraslationWantSendJishoushangpinFenleiWupinJpanel(TraslationWantSendJishoushangpinFenleiWupinJpanel traslationWantSendJishoushangpinFenleiWupinJpanel) {
        this.traslationWantSendJishoushangpinFenleiWupinJpanel = traslationWantSendJishoushangpinFenleiWupinJpanel;
    }
    
    public TraslationWantSendJishoushangpinFenleiZhaohuanshouJpanel getTraslationWantSendJishoushangpinFenleiZhaohuanshouJpanel() {
        return this.traslationWantSendJishoushangpinFenleiZhaohuanshouJpanel;
    }
    
    public void setTraslationWantSendJishoushangpinFenleiZhaohuanshouJpanel(TraslationWantSendJishoushangpinFenleiZhaohuanshouJpanel traslationWantSendJishoushangpinFenleiZhaohuanshouJpanel) {
        this.traslationWantSendJishoushangpinFenleiZhaohuanshouJpanel = traslationWantSendJishoushangpinFenleiZhaohuanshouJpanel;
    }
}
