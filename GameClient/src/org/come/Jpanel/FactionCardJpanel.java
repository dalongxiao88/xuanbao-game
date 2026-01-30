package org.come.Jpanel;

import java.awt.Dimension;
import org.come.bean.GangResultBean;
import java.awt.CardLayout;
import javax.swing.JPanel;

public class FactionCardJpanel extends JPanel
{
    private CardLayout cardLayout;
    private FactionPandectJpanel factionPandectJpanel;
    private FactionMemberJpanel factionMemberJpanel;
    private FactionWarJpanel factionWarJpanel;
    private GangResultBean gangResultBean;
    
    public FactionCardJpanel() {
        this.setPreferredSize(new Dimension(662, 475));
        this.setOpaque(false);
        this.setLayout(this.cardLayout = new CardLayout());
        this.add(this.factionPandectJpanel = new FactionPandectJpanel(this), "pandect");
        this.add(this.factionMemberJpanel = new FactionMemberJpanel(this), "member");
        this.add(this.factionWarJpanel = new FactionWarJpanel(), "war");
    }
    
    public void showMessage(GangResultBean gangResultBean) {
        this.gangResultBean = gangResultBean;
        this.factionMemberJpanel.showMenuMessage(gangResultBean);
        this.factionMemberJpanel.showBtn(false);
        this.factionMemberJpanel.showBtn(true);
        this.factionPandectJpanel.showFactionMessage(gangResultBean.getGang(), gangResultBean.getGangGroup());
    }
    
    public CardLayout getCardLayout() {
        return this.cardLayout;
    }
    
    public void setCardLayout(CardLayout cardLayout) {
        this.cardLayout = cardLayout;
    }
    
    public FactionPandectJpanel getFactionPandectJpanel() {
        return this.factionPandectJpanel;
    }
    
    public void setFactionPandectJpanel(FactionPandectJpanel factionPandectJpanel) {
        this.factionPandectJpanel = factionPandectJpanel;
    }
    
    public FactionMemberJpanel getFactionMemberJpanel() {
        return this.factionMemberJpanel;
    }
    
    public void setFactionMemberJpanel(FactionMemberJpanel factionMemberJpanel) {
        this.factionMemberJpanel = factionMemberJpanel;
    }
    
    public FactionWarJpanel getFactionWarJpanel() {
        return this.factionWarJpanel;
    }
    
    public void setFactionWarJpanel(FactionWarJpanel factionWarJpanel) {
        this.factionWarJpanel = factionWarJpanel;
    }
    
    public GangResultBean getGangResultBean() {
        return this.gangResultBean;
    }
    
    public void setGangResultBean(GangResultBean gangResultBean) {
        this.gangResultBean = gangResultBean;
    }
}
