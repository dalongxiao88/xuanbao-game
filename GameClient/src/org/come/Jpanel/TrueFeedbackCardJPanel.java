package org.come.Jpanel;

import org.come.bean.LaborRank;
import java.awt.Dimension;
import java.awt.CardLayout;
import javax.swing.JPanel;

public class TrueFeedbackCardJPanel extends JPanel
{
    private CardLayout cardLayout;
    private TrueFeedbackLotteyJPanel trueFeedbackLotteyJPanel;
    private TrueFeedbackScPanel trueFeedbackScPanel;
    private int typeMenu;
    
    public TrueFeedbackCardJPanel() {
        this.setPreferredSize(new Dimension(430, 440));
        this.setOpaque(false);
        this.setLayout(this.cardLayout = new CardLayout());
        this.trueFeedbackLotteyJPanel = new TrueFeedbackLotteyJPanel();
        this.add(this.trueFeedbackScPanel = new TrueFeedbackScPanel(), "sc");
        this.add(this.trueFeedbackLotteyJPanel, "lottey");
    }
    
    public void changeShowView(int caozuo) {
        if (caozuo == 0 || caozuo == 1) {
            if (this.typeMenu == 2) {
                this.cardLayout.show(this, "sc");
            }
            this.trueFeedbackScPanel.changeViewImg(caozuo);
        }
        else if (caozuo == 2) {
            this.cardLayout.show(this, "lottey");
        }
        this.typeMenu = caozuo;
    }
    
    public void showViewData(LaborRank laborRank) {
        int type = laborRank.getType();
        if (type <= 1) {
            this.trueFeedbackScPanel.showScpane(laborRank);
        }
        else {
            this.trueFeedbackLotteyJPanel.showViewData(laborRank);
        }
        this.changeShowView(laborRank.getType());
    }
    
    public CardLayout getCardLayout() {
        return this.cardLayout;
    }
    
    public void setCardLayout(CardLayout cardLayout) {
        this.cardLayout = cardLayout;
    }
    
    public TrueFeedbackLotteyJPanel getTrueFeedbackLotteyJPanel() {
        return this.trueFeedbackLotteyJPanel;
    }
    
    public void setTrueFeedbackLotteyJPanel(TrueFeedbackLotteyJPanel trueFeedbackLotteyJPanel) {
        this.trueFeedbackLotteyJPanel = trueFeedbackLotteyJPanel;
    }
    
    public TrueFeedbackScPanel getTrueFeedbackScPanel() {
        return this.trueFeedbackScPanel;
    }
    
    public void setTrueFeedbackScPanel(TrueFeedbackScPanel trueFeedbackScPanel) {
        this.trueFeedbackScPanel = trueFeedbackScPanel;
    }
    
    public int getTypeMenu() {
        return this.typeMenu;
    }
    
    public void setTypeMenu(int typeMenu) {
        this.typeMenu = typeMenu;
    }
}
