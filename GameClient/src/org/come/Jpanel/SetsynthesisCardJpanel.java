package org.come.Jpanel;

import java.awt.Dimension;
import java.awt.CardLayout;
import javax.swing.JPanel;

public class SetsynthesisCardJpanel extends JPanel
{
    private CardLayout car;
    private SynthesisJpanel synthesisJpanel;
    private WashJpanel washJpanel;
    private UpgradeJpanel upgradeJpanel;
    private JadeUpJpanel jadeUpJpanel;
    private TransferJpanel transferJpanel;
    
    public SetsynthesisCardJpanel() {
        this.car = new CardLayout();
        this.setPreferredSize(new Dimension(490, 428));
        this.setLayout(this.car);
        this.setOpaque(false);
        this.add(this.synthesisJpanel = new SynthesisJpanel(), "l1");
        this.add(this.washJpanel = new WashJpanel(), "l2");
        this.add(this.upgradeJpanel = new UpgradeJpanel(), "l3");
        this.add(this.jadeUpJpanel = new JadeUpJpanel(), "l4");
        this.add(this.transferJpanel = new TransferJpanel(), "l5");
    }
    
    public CardLayout getCar() {
        return this.car;
    }
    
    public void setCar(CardLayout car) {
        this.car = car;
    }
    
    public SynthesisJpanel getSynthesisJpanel() {
        return this.synthesisJpanel;
    }
    
    public void setSynthesisJpanel(SynthesisJpanel synthesisJpanel) {
        this.synthesisJpanel = synthesisJpanel;
    }
    
    public WashJpanel getWashJpanel() {
        return this.washJpanel;
    }
    
    public void setWashJpanel(WashJpanel washJpanel) {
        this.washJpanel = washJpanel;
    }
    
    public UpgradeJpanel getUpgradeJpanel() {
        return this.upgradeJpanel;
    }
    
    public void setUpgradeJpanel(UpgradeJpanel upgradeJpanel) {
        this.upgradeJpanel = upgradeJpanel;
    }
    
    public JadeUpJpanel getJadeUpJpanel() {
        return this.jadeUpJpanel;
    }
    
    public void setJadeUpJpanel(JadeUpJpanel jadeUpJpanel) {
        this.jadeUpJpanel = jadeUpJpanel;
    }
    
    public TransferJpanel getTransferJpanel() {
        return this.transferJpanel;
    }
    
    public void setTransferJpanel(TransferJpanel transferJpanel) {
        this.transferJpanel = transferJpanel;
    }
}
