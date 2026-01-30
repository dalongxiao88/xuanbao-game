package org.come.Jpanel;

import java.awt.Dimension;
import com.updateNew.MyIsif;
import java.awt.CardLayout;
import javax.swing.JPanel;

public class LingbaoCardJpanel extends JPanel
{
    private CardLayout car;
    private LingbaoEquipmentJpanel equipmentJpanel;
    private LingbaoAttributeJpanel attributeJpanel;
    
    public LingbaoCardJpanel() {
        if (MyIsif.getStyle().equals("姘村ⅷ")) {
            this.car = new CardLayout();
            this.setPreferredSize(new Dimension(538, 469));
            this.setLayout(this.car);
            this.setOpaque(false);
            this.add(this.equipmentJpanel = new LingbaoEquipmentJpanel(), "l1");
            this.add(this.attributeJpanel = new LingbaoAttributeJpanel(), "l2");
        }
        else {
            this.car = new CardLayout();
            this.setPreferredSize(new Dimension(512, 496));
            this.setLayout(this.car);
            this.setOpaque(false);
            this.add(this.equipmentJpanel = new LingbaoEquipmentJpanel(), "l1");
            this.add(this.attributeJpanel = new LingbaoAttributeJpanel(), "l2");
        }
    }
    
    public CardLayout getCar() {
        return this.car;
    }
    
    public void setCar(CardLayout car) {
        this.car = car;
    }
    
    public LingbaoEquipmentJpanel getEquipmentJpanel() {
        return this.equipmentJpanel;
    }
    
    public void setEquipmentJpanel(LingbaoEquipmentJpanel equipmentJpanel) {
        this.equipmentJpanel = equipmentJpanel;
    }
    
    public LingbaoAttributeJpanel getAttributeJpanel() {
        return this.attributeJpanel;
    }
    
    public void setAttributeJpanel(LingbaoAttributeJpanel attributeJpanel) {
        this.attributeJpanel = attributeJpanel;
    }
}
