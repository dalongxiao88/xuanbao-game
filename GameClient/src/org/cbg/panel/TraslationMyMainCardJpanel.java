package org.cbg.panel;

import java.awt.CardLayout;
import javax.swing.JPanel;

public class TraslationMyMainCardJpanel extends JPanel
{
    private CardLayout cardLayout;
    private TraslationMyMainMyorderJpanel traslationMyMainMyorderJpanel;
    private TraslationMyMainZhidinggoumaiJpanel traslationMyMainZhidinggoumaiJpanel;
    private TraslationMyMainMygoodsJpanel traslationMyMainMygoodsJpanel;
    
    public TraslationMyMainCardJpanel() {
        this.setLayout(this.cardLayout = new CardLayout());
        this.setBounds(0, 27, 590, 380);
        this.setOpaque(false);
        this.traslationMyMainMyorderJpanel = new TraslationMyMainMyorderJpanel();
        this.traslationMyMainZhidinggoumaiJpanel = new TraslationMyMainZhidinggoumaiJpanel();
        this.traslationMyMainMygoodsJpanel = new TraslationMyMainMygoodsJpanel();
        this.add(this.traslationMyMainMyorderJpanel, "myorder");
        this.add(this.traslationMyMainZhidinggoumaiJpanel, "zhidinggoumai");
        this.add(this.traslationMyMainMygoodsJpanel, "mygoods");
    }
    
    public CardLayout getCardLayout() {
        return this.cardLayout;
    }
    
    public void setCardLayout(CardLayout cardLayout) {
        this.cardLayout = cardLayout;
    }
    
    public TraslationMyMainMyorderJpanel getTraslationMyMainMyorderJpanel() {
        return this.traslationMyMainMyorderJpanel;
    }
    
    public void setTraslationMyMainMyorderJpanel(TraslationMyMainMyorderJpanel traslationMyMainMyorderJpanel) {
        this.traslationMyMainMyorderJpanel = traslationMyMainMyorderJpanel;
    }
    
    public TraslationMyMainZhidinggoumaiJpanel getTraslationMyMainZhidinggoumaiJpanel() {
        return this.traslationMyMainZhidinggoumaiJpanel;
    }
    
    public void setTraslationMyMainZhidinggoumaiJpanel(TraslationMyMainZhidinggoumaiJpanel traslationMyMainZhidinggoumaiJpanel) {
        this.traslationMyMainZhidinggoumaiJpanel = traslationMyMainZhidinggoumaiJpanel;
    }
    
    public TraslationMyMainMygoodsJpanel getTraslationMyMainMygoodsJpanel() {
        return this.traslationMyMainMygoodsJpanel;
    }
    
    public void setTraslationMyMainMygoodsJpanel(TraslationMyMainMygoodsJpanel traslationMyMainMygoodsJpanel) {
        this.traslationMyMainMygoodsJpanel = traslationMyMainMygoodsJpanel;
    }
}
