package org.come.Jpanel;

import com.updateNew.MyIsif;
import com.tool.btn.WorkshopRefiningBtn;
import javax.swing.JPanel;

public class SetsynthesisJpanel extends JPanel
{
    private SetsynthesisCardJpanel cardJpanel;
    private WorkshopRefiningBtn labSynthesis;
    private WorkshopRefiningBtn labWash;
    private WorkshopRefiningBtn labUpgrade;
    private WorkshopRefiningBtn labJadeUp;
    private WorkshopRefiningBtn labTransfer;
    
    public SetsynthesisJpanel() {
        this.setLayout(null);
        this.setOpaque(false);
        if (MyIsif.getStyle().equals("水墨")) {
            (this.cardJpanel = new SetsynthesisCardJpanel()).setBounds(47, 74, 490, 428);
            this.add(this.cardJpanel);
            (this.labSynthesis = new WorkshopRefiningBtn("inkImg/button1/K38.png", 1, 10, this)).setBounds(55, 49, 90, 24);
            this.add(this.labSynthesis);
            (this.labWash = new WorkshopRefiningBtn("inkImg/button1/K39.png", 1, 11, this)).setBounds(147, 49, 90, 24);
            this.add(this.labWash);
            (this.labUpgrade = new WorkshopRefiningBtn("inkImg/button1/K41.png", 1, 12, this)).setBounds(239, 49, 90, 24);
            this.add(this.labUpgrade);
            (this.labJadeUp = new WorkshopRefiningBtn("inkImg/button1/K43.png", 1, 13, this)).setBounds(331, 49, 90, 24);
            this.add(this.labJadeUp);
            (this.labTransfer = new WorkshopRefiningBtn("inkImg/button1/K45.png", 1, 14, this)).setBounds(423, 49, 90, 24);
            this.add(this.labTransfer);
        }
        else {
            (this.cardJpanel = new SetsynthesisCardJpanel()).setBounds(22, 86, 490, 428);
            this.add(this.cardJpanel);
            (this.labSynthesis = new WorkshopRefiningBtn("img/xy2uiimg/setsynthesis_1.png", 1, 10, this)).setBounds(31, 60, 86, 26);
            this.add(this.labSynthesis);
            (this.labWash = new WorkshopRefiningBtn("img/xy2uiimg/setsynthesis_2.png", 1, 11, this)).setBounds(119, 60, 86, 26);
            this.add(this.labWash);
            (this.labUpgrade = new WorkshopRefiningBtn("img/xy2uiimg/setsynthesis_4.png", 1, 12, this)).setBounds(207, 60, 86, 26);
            this.add(this.labUpgrade);
            (this.labJadeUp = new WorkshopRefiningBtn("img/xy2uiimg/setsynthesis_6.png", 1, 13, this)).setBounds(295, 60, 86, 26);
            this.add(this.labJadeUp);
            (this.labTransfer = new WorkshopRefiningBtn("img/xy2uiimg/setsynthesis_8.png", 1, 14, this)).setBounds(383, 60, 86, 26);
            this.add(this.labTransfer);
        }
    }
    
    public SetsynthesisCardJpanel getCardJpanel() {
        return this.cardJpanel;
    }
    
    public void setCardJpanel(SetsynthesisCardJpanel cardJpanel) {
        this.cardJpanel = cardJpanel;
    }
    
    public WorkshopRefiningBtn getLabSynthesis() {
        return this.labSynthesis;
    }
    
    public void setLabSynthesis(WorkshopRefiningBtn labSynthesis) {
        this.labSynthesis = labSynthesis;
    }
    
    public WorkshopRefiningBtn getLabWash() {
        return this.labWash;
    }
    
    public void setLabWash(WorkshopRefiningBtn labWash) {
        this.labWash = labWash;
    }
    
    public WorkshopRefiningBtn getLabUpgrade() {
        return this.labUpgrade;
    }
    
    public void setLabUpgrade(WorkshopRefiningBtn labUpgrade) {
        this.labUpgrade = labUpgrade;
    }
    
    public WorkshopRefiningBtn getLabJadeUp() {
        return this.labJadeUp;
    }
    
    public void setLabJadeUp(WorkshopRefiningBtn labJadeUp) {
        this.labJadeUp = labJadeUp;
    }
    
    public WorkshopRefiningBtn getLabTransfer() {
        return this.labTransfer;
    }
    
    public void setLabTransfer(WorkshopRefiningBtn labTransfer) {
        this.labTransfer = labTransfer;
    }
}
