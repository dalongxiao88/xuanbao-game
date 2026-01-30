package org.come.Jpanel.spot.box;

import java.awt.Component;
import org.come.Jpanel.spot.SpotJpanel;
import org.come.Jpanel.spot.stall.SpotStallRecordJpanel;
import org.come.Jpanel.spot.stall.SpotStallPurchaseJpanel;
import org.come.Jpanel.spot.stall.SpotStallSellJpanel;
import java.awt.CardLayout;
import org.come.Jpanel.spot.SpotBox;
import javax.swing.JPanel;

public class SpotBoxJpanel extends JPanel implements SpotBox
{
    private CardLayout cardLayout;
    private SpotStallSellJpanel spotSellBoxJpanel;
    private SpotStallPurchaseJpanel spotPurchaseBoxJpanel;
    private SpotStallRecordJpanel spotRecordBoxJpanel;
    
    public SpotBoxJpanel() {
        this.setLayout(this.cardLayout = new CardLayout());
        this.spotSellBoxJpanel = new SpotStallSellJpanel(this);
        this.spotPurchaseBoxJpanel = new SpotStallPurchaseJpanel(this);
        this.spotRecordBoxJpanel = new SpotStallRecordJpanel(this);
        this.add(this.spotSellBoxJpanel, "sell");
        this.add(this.spotPurchaseBoxJpanel, "purchase");
        this.add(this.spotRecordBoxJpanel, "record");
        this.setOpaque(false);
    }
    
    public void updateCommoditys() {
        for (int i = 0; i < this.getComponentCount(); ++i) {
            Component component = this.getComponent(i);
            if (component.isVisible() && component instanceof SpotJpanel) {
                ((SpotJpanel)component).updateCommoditys();
            }
        }
    }
    
    @Override
    public void cardLayoutShow(String name) {
        this.cardLayout.show(this, name);
        this.updateCommoditys();
    }
    
    public SpotStallSellJpanel getSpotSellBoxJpanel() {
        return this.spotSellBoxJpanel;
    }
    
    public SpotStallPurchaseJpanel getSpotPurchaseBoxJpanel() {
        return this.spotPurchaseBoxJpanel;
    }
    
    public SpotStallRecordJpanel getSpotRecordBoxJpanel() {
        return this.spotRecordBoxJpanel;
    }
}
