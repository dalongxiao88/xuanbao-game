package org.cbg.panel;

import java.awt.CardLayout;
import javax.swing.JPanel;

public class TraslationWantSendCardJpanel extends JPanel
{
    private CardLayout vcarCardLayout;
    private TraslationWantSendJishoushangpinJpanel traslationWantSendJishoushangpinJpanel;
    private TraslationWantSentjishoujinqianJpanel traslationWantSentjishoujinqianJpanel;
    private TraslationWantSentYijishangpinJpanel traslationWantSentYijishangpinJpanel;
    
    public TraslationWantSendCardJpanel() {
        this.setLayout(this.vcarCardLayout = new CardLayout());
        this.setBounds(0, 27, 590, 380);
        this.setOpaque(false);
        this.traslationWantSendJishoushangpinJpanel = new TraslationWantSendJishoushangpinJpanel();
        this.traslationWantSentjishoujinqianJpanel = new TraslationWantSentjishoujinqianJpanel();
        this.traslationWantSentYijishangpinJpanel = new TraslationWantSentYijishangpinJpanel();
        this.add(this.traslationWantSendJishoushangpinJpanel, "jishoushangpin");
        this.add(this.traslationWantSentYijishangpinJpanel, "yijishoupin");
    }
    
    public CardLayout getVcarCardLayout() {
        return this.vcarCardLayout;
    }
    
    public void setVcarCardLayout(CardLayout vcarCardLayout) {
        this.vcarCardLayout = vcarCardLayout;
    }
    
    public TraslationWantSendJishoushangpinJpanel getTraslationWantSendJishoushangpinJpanel() {
        return this.traslationWantSendJishoushangpinJpanel;
    }
    
    public void setTraslationWantSendJishoushangpinJpanel(TraslationWantSendJishoushangpinJpanel traslationWantSendJishoushangpinJpanel) {
        this.traslationWantSendJishoushangpinJpanel = traslationWantSendJishoushangpinJpanel;
    }
    
    public TraslationWantSentjishoujinqianJpanel getTraslationWantSentjishoujinqianJpanel() {
        return this.traslationWantSentjishoujinqianJpanel;
    }
    
    public void setTraslationWantSentjishoujinqianJpanel(TraslationWantSentjishoujinqianJpanel traslationWantSentjishoujinqianJpanel) {
        this.traslationWantSentjishoujinqianJpanel = traslationWantSentjishoujinqianJpanel;
    }
    
    public TraslationWantSentYijishangpinJpanel getTraslationWantSentYijishangpinJpanel() {
        return this.traslationWantSentYijishangpinJpanel;
    }
    
    public void setTraslationWantSentYijishangpinJpanel(TraslationWantSentYijishangpinJpanel traslationWantSentYijishangpinJpanel) {
        this.traslationWantSentYijishangpinJpanel = traslationWantSentYijishangpinJpanel;
    }
}
