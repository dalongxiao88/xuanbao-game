package org.cbg.panel;

import java.awt.Dimension;
import java.awt.CardLayout;
import javax.swing.JPanel;

public class TraslationWantBuyShouyeCardJpanel extends JPanel
{
    private CardLayout cardLayout;
    private TraslationWantBuyShouyeJpanel traslationWantBuyShouyeJpanel;
    private TraslationWantBuyShouyeSousuojieguoJpanel traslationWantBuyShouyeSousuojieguoJpanel;
    
    public TraslationWantBuyShouyeCardJpanel() {
        this.setPreferredSize(new Dimension(590, 380));
        this.setOpaque(false);
        this.setLayout(this.cardLayout = new CardLayout());
        this.traslationWantBuyShouyeJpanel = new TraslationWantBuyShouyeJpanel();
        this.traslationWantBuyShouyeSousuojieguoJpanel = new TraslationWantBuyShouyeSousuojieguoJpanel();
        this.add(this.traslationWantBuyShouyeJpanel, "sousuo");
        this.add(this.traslationWantBuyShouyeSousuojieguoJpanel, "sousuojieguo");
    }
    
    public CardLayout getCardLayout() {
        return this.cardLayout;
    }
    
    public void setCardLayout(CardLayout cardLayout) {
        this.cardLayout = cardLayout;
    }
    
    public TraslationWantBuyShouyeJpanel getTraslationWantBuyShouyeJpanel() {
        return this.traslationWantBuyShouyeJpanel;
    }
    
    public void setTraslationWantBuyShouyeJpanel(TraslationWantBuyShouyeJpanel traslationWantBuyShouyeJpanel) {
        this.traslationWantBuyShouyeJpanel = traslationWantBuyShouyeJpanel;
    }
    
    public TraslationWantBuyShouyeSousuojieguoJpanel getTraslationWantBuyShouyeSousuojieguoJpanel() {
        return this.traslationWantBuyShouyeSousuojieguoJpanel;
    }
    
    public void setTraslationWantBuyShouyeSousuojieguoJpanel(TraslationWantBuyShouyeSousuojieguoJpanel traslationWantBuyShouyeSousuojieguoJpanel) {
        this.traslationWantBuyShouyeSousuojieguoJpanel = traslationWantBuyShouyeSousuojieguoJpanel;
    }
}
