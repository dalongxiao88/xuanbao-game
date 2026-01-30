package org.come.Jpanel;

import java.util.Map;
import org.come.bean.ConfigureBean;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import java.math.BigDecimal;
import org.come.model.Configure;
import org.come.until.UserMessUntil;
import java.awt.CardLayout;
import javax.swing.JPanel;

public class WorkshopRefiningCardJpanel extends JPanel
{
    private CardLayout car;
    private RefiningEquiJpanel equiJpanel;
    private RefinersJpanel refinersJpanel;
    private SetsynthesisJpanel setsynthesisJpanel;
    private GemRefineryMainJpanel gemRefineryMainJpanel;
    
    public WorkshopRefiningCardJpanel() {
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure)item.get(new BigDecimal(1));
        this.car = new CardLayout();
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(562, 520));
        }
        else {
            this.setPreferredSize(new Dimension(562, 542));
        }
        this.setLayout(this.car);
        this.setOpaque(false);
        this.add(this.equiJpanel = new RefiningEquiJpanel(), "l1");
        if (!configure.getLzjskg().equals("3")) {
            this.add(this.refinersJpanel = new RefinersJpanel(), "l3");
            this.add(this.setsynthesisJpanel = new SetsynthesisJpanel(), "l4");
            if (configure.getBsqhkg().equals("开")) {
                this.add(this.gemRefineryMainJpanel = new GemRefineryMainJpanel(), "l5");
            }
        }
    }
    
    public CardLayout getCar() {
        return this.car;
    }
    
    public void setCar(CardLayout car) {
        this.car = car;
    }
    
    public RefinersJpanel getRefinersJpanel() {
        return this.refinersJpanel;
    }
    
    public void setRefinersJpanel(RefinersJpanel refinersJpanel) {
        this.refinersJpanel = refinersJpanel;
    }
    
    public SetsynthesisJpanel getSetsynthesisJpanel() {
        return this.setsynthesisJpanel;
    }
    
    public void setSetsynthesisJpanel(SetsynthesisJpanel setsynthesisJpanel) {
        this.setsynthesisJpanel = setsynthesisJpanel;
    }
    
    public RefiningEquiJpanel getEquiJpanel() {
        return this.equiJpanel;
    }
    
    public void setEquiJpanel(RefiningEquiJpanel equiJpanel) {
        this.equiJpanel = equiJpanel;
    }
    
    public GemRefineryMainJpanel getGemRefineryMainJpanel() {
        return this.gemRefineryMainJpanel;
    }
    
    public void setGemRefineryMainJpanel(GemRefineryMainJpanel gemRefineryMainJpanel) {
        this.gemRefineryMainJpanel = gemRefineryMainJpanel;
    }
}
