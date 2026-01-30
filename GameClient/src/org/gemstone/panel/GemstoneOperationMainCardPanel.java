package org.gemstone.panel;

import com.updateNew.MyIsif;
import java.awt.CardLayout;
import javax.swing.JPanel;

public class GemstoneOperationMainCardPanel extends JPanel
{
    private CardLayout cardLayout;
    private GemstoneOperationSynthesisPanel gemstoneOperationSynthesisPanel;
    private GemstoneOperationAppraisalAndRecastPanel gemstoneOperationAppraisalAndRecastPanel;
    
    public GemstoneOperationMainCardPanel() {
        this.cardLayout = new CardLayout();
        if (MyIsif.getStyle().equals("水墨")) {
            this.setBounds(47, 45, 310, 260);
        }
        else {
            this.setBounds(23, 55, 310, 260);
        }
        this.setOpaque(false);
        this.setLayout(this.cardLayout);
        this.gemstoneOperationSynthesisPanel = new GemstoneOperationSynthesisPanel();
        this.gemstoneOperationAppraisalAndRecastPanel = new GemstoneOperationAppraisalAndRecastPanel();
        this.add(this.gemstoneOperationSynthesisPanel, "synthesis");
        this.add(this.gemstoneOperationAppraisalAndRecastPanel, "appraisalAndRecast");
    }
    
    public CardLayout getCardLayout() {
        return this.cardLayout;
    }
    
    public void setCardLayout(CardLayout cardLayout) {
        this.cardLayout = cardLayout;
    }
    
    public GemstoneOperationSynthesisPanel getGemstoneOperationSynthesisPanel() {
        return this.gemstoneOperationSynthesisPanel;
    }
    
    public void setGemstoneOperationSynthesisPanel(GemstoneOperationSynthesisPanel gemstoneOperationSynthesisPanel) {
        this.gemstoneOperationSynthesisPanel = gemstoneOperationSynthesisPanel;
    }
    
    public GemstoneOperationAppraisalAndRecastPanel getGemstoneOperationAppraisalAndRecastPanel() {
        return this.gemstoneOperationAppraisalAndRecastPanel;
    }
    
    public void setGemstoneOperationAppraisalAndRecastPanel(GemstoneOperationAppraisalAndRecastPanel gemstoneOperationAppraisalAndRecastPanel) {
        this.gemstoneOperationAppraisalAndRecastPanel = gemstoneOperationAppraisalAndRecastPanel;
    }
}
