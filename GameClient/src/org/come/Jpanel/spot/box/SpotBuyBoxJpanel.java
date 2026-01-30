package org.come.Jpanel.spot.box;

import java.awt.Component;
import org.come.Jpanel.spot.SpotJpanel;
import com.tool.Stall.Stall;
import org.come.Jpanel.spot.buy.SpotBuyRecordJpanel;
import org.come.Jpanel.spot.buy.SpotBuyPurchaseJpanel;
import org.come.Jpanel.spot.buy.SpotBuySellJpanel;
import java.awt.CardLayout;
import org.come.Jpanel.spot.SpotBox;
import javax.swing.JPanel;

public class SpotBuyBoxJpanel extends JPanel implements SpotBox
{
    private CardLayout cardLayout;
    private SpotBuySellJpanel spotBuySellJpanel;
    private SpotBuyPurchaseJpanel spotBuyPurchaseJpanel;
    private SpotBuyRecordJpanel spotBuyRecordJpanel;
    private Stall stall;
    
    public SpotBuyBoxJpanel() {
        this.setLayout(this.cardLayout = new CardLayout());
        this.spotBuySellJpanel = new SpotBuySellJpanel(this);
        this.spotBuyPurchaseJpanel = new SpotBuyPurchaseJpanel(this);
        this.spotBuyRecordJpanel = new SpotBuyRecordJpanel(this);
        this.add(this.spotBuySellJpanel, "sell");
        this.add(this.spotBuyPurchaseJpanel, "purchase");
        this.add(this.spotBuyRecordJpanel, "record");
        this.setOpaque(false);
    }
    
    public boolean closeStall(int id) {
        return this.stall != null && this.stall.getId() == id;
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
    
    public SpotBuySellJpanel getSpotBuySellBoxJpanel() {
        return this.spotBuySellJpanel;
    }
    
    public SpotBuyPurchaseJpanel getSpotBuyPurchaseBoxJpanel() {
        return this.spotBuyPurchaseJpanel;
    }
    
    public SpotBuyRecordJpanel getSpotBuyRecordBoxJpanel() {
        return this.spotBuyRecordJpanel;
    }
    
    public Stall getStall() {
        return this.stall;
    }
    
    public void setStall(Stall stall) {
        this.stall = stall;
        this.updateCommoditys();
    }
}
