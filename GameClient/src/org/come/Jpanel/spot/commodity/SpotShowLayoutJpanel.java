package org.come.Jpanel.spot.commodity;

import java.awt.Component;
import com.tool.Stall.CommodityBean;
import java.util.List;
import org.come.Jpanel.spot.buy.SpotBuyBaseJpanel;
import java.awt.CardLayout;
import javax.swing.JPanel;

public class SpotShowLayoutJpanel extends JPanel
{
    private CardLayout cardLayout;
    private SpotShowJpanel[] showJpanels;
    
    public SpotShowLayoutJpanel(SpotBuyBaseJpanel spotBuyBaseJpanel) {
        this.setLayout(this.cardLayout = new CardLayout());
        this.showJpanels = new SpotShowJpanel[3];
        this.add(this.showJpanels[0] = new SpotShowJpanel(spotBuyBaseJpanel, 0), "goods");
        this.add(this.showJpanels[1] = new SpotShowJpanel(spotBuyBaseJpanel, 1), "pet");
        this.add(this.showJpanels[2] = new SpotShowJpanel(spotBuyBaseJpanel, 2), "bao");
        this.setOpaque(false);
    }
    
    public void cardLayoutShow(String name) {
        this.cardLayout.show(this, name);
    }
    
    public void updateCommoditys(List<CommodityBean> list, int pageNumber) {
        for (int i = 0; i < this.getComponentCount(); ++i) {
            Component component = this.getComponent(i);
            if (component.isVisible() && component instanceof SpotShowJpanel) {
                ((SpotShowJpanel)component).updateCommoditys(list, pageNumber);
            }
        }
    }
}
