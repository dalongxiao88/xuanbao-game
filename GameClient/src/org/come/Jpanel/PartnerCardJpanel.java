package org.come.Jpanel;

import java.awt.Dimension;
import java.awt.CardLayout;
import javax.swing.JPanel;

public class PartnerCardJpanel extends JPanel
{
    private CardLayout cardLayout;
    private PartnerTeamJpanel partnerTeamJpanel;
    private PartnerSkillJpanel partnerSkillJpanel;
    private PartnerEquipJpanel partnerEquipJpanel;
    
    public PartnerCardJpanel() {
        this.cardLayout = new CardLayout();
        this.setPreferredSize(new Dimension(356, 301));
        this.setOpaque(false);
        this.setLayout(this.cardLayout);
        this.partnerTeamJpanel = new PartnerTeamJpanel();
        this.partnerSkillJpanel = new PartnerSkillJpanel();
        this.partnerEquipJpanel = new PartnerEquipJpanel();
        this.add("team", this.partnerTeamJpanel);
        this.add("skill", this.partnerSkillJpanel);
        this.add("equip", this.partnerEquipJpanel);
    }
    
    public CardLayout getCardLayout() {
        return this.cardLayout;
    }
    
    public void setCardLayout(CardLayout cardLayout) {
        this.cardLayout = cardLayout;
    }
    
    public PartnerTeamJpanel getPartnerTeamJpanel() {
        return this.partnerTeamJpanel;
    }
    
    public void setPartnerTeamJpanel(PartnerTeamJpanel partnerTeamJpanel) {
        this.partnerTeamJpanel = partnerTeamJpanel;
    }
    
    public PartnerSkillJpanel getPartnerSkillJpanel() {
        return this.partnerSkillJpanel;
    }
    
    public void setPartnerSkillJpanel(PartnerSkillJpanel partnerSkillJpanel) {
        this.partnerSkillJpanel = partnerSkillJpanel;
    }
    
    public PartnerEquipJpanel getPartnerEquipJpanel() {
        return this.partnerEquipJpanel;
    }
    
    public void setPartnerEquipJpanel(PartnerEquipJpanel partnerEquipJpanel) {
        this.partnerEquipJpanel = partnerEquipJpanel;
    }
}
