package org.cbg.panel;

import com.updateNew.MyIsif;
import java.awt.CardLayout;
import javax.swing.JPanel;

public class TranslationMainCardJpanel extends JPanel
{
    private CardLayout cardLayout;
    private TraslationWantBuyJpanel traslationWantBuyJpanel;
    private TraslationWantSentJpanel traslationWantSentJpanel;
    private TraslationMyMainJpanel traslationMyMainJpanel;
    private TraslationMyMessageJpanel traslationMyMessageJpanel;
    
    public TranslationMainCardJpanel() {
        if (MyIsif.getStyle().equals("水墨")) {
            this.setLayout(this.cardLayout = new CardLayout());
            this.traslationWantBuyJpanel = new TraslationWantBuyJpanel();
            this.traslationWantSentJpanel = new TraslationWantSentJpanel();
            this.traslationMyMainJpanel = new TraslationMyMainJpanel();
            this.traslationMyMessageJpanel = new TraslationMyMessageJpanel();
            this.add(this.traslationWantBuyJpanel, "iwantbuy");
            this.add(this.traslationWantSentJpanel, "iwantsent");
            this.add(this.traslationMyMainJpanel, "icangbaoge");
            this.add(this.traslationMyMessageJpanel, "imes");
            this.setBounds(47, 62, 590, 410);
            this.setOpaque(false);
        }
        else {
            this.setLayout(this.cardLayout = new CardLayout());
            this.traslationWantBuyJpanel = new TraslationWantBuyJpanel();
            this.traslationWantSentJpanel = new TraslationWantSentJpanel();
            this.traslationMyMainJpanel = new TraslationMyMainJpanel();
            this.traslationMyMessageJpanel = new TraslationMyMessageJpanel();
            this.add(this.traslationWantBuyJpanel, "iwantbuy");
            this.add(this.traslationWantSentJpanel, "iwantsent");
            this.add(this.traslationMyMainJpanel, "icangbaoge");
            this.add(this.traslationMyMessageJpanel, "imes");
            this.setBounds(22, 72, 590, 410);
            this.setOpaque(false);
        }
    }
    
    public CardLayout getCardLayout() {
        return this.cardLayout;
    }
    
    public void setCardLayout(CardLayout cardLayout) {
        this.cardLayout = cardLayout;
    }
    
    public TraslationWantBuyJpanel getTraslationWantBuyJpanel() {
        return this.traslationWantBuyJpanel;
    }
    
    public void setTraslationWantBuyJpanel(TraslationWantBuyJpanel traslationWantBuyJpanel) {
        this.traslationWantBuyJpanel = traslationWantBuyJpanel;
    }
    
    public TraslationWantSentJpanel getTraslationWantSentJpanel() {
        return this.traslationWantSentJpanel;
    }
    
    public void setTraslationWantSentJpanel(TraslationWantSentJpanel traslationWantSentJpanel) {
        this.traslationWantSentJpanel = traslationWantSentJpanel;
    }
    
    public TraslationMyMainJpanel getTraslationMyMainJpanel() {
        return this.traslationMyMainJpanel;
    }
    
    public void setTraslationMyMainJpanel(TraslationMyMainJpanel traslationMyMainJpanel) {
        this.traslationMyMainJpanel = traslationMyMainJpanel;
    }
    
    public TraslationMyMessageJpanel getTraslationMyMessageJpanel() {
        return this.traslationMyMessageJpanel;
    }
    
    public void setTraslationMyMessageJpanel(TraslationMyMessageJpanel traslationMyMessageJpanel) {
        this.traslationMyMessageJpanel = traslationMyMessageJpanel;
    }
}
