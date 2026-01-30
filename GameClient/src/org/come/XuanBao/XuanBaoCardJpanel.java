
package org.come.XuanBao;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JPanel;

public class XuanBaoCardJpanel
        extends JPanel {
    private CardLayout car = new CardLayout();
    private XuanBaoEquipmentJpanel equipmentJpanel;
    private XuanBaoAttributeJpanel attributeJpanel;

    public XuanBaoCardJpanel() {
        this.setPreferredSize(new Dimension(619, 482));
        this.setLayout(this.car);
        this.setOpaque(false);
        this.equipmentJpanel = new XuanBaoEquipmentJpanel();
        this.add((Component)this.equipmentJpanel, "l1");
        this.attributeJpanel = new XuanBaoAttributeJpanel();
        this.add((Component)this.attributeJpanel, "l2");
    }

    public CardLayout getCar() {
        return this.car;
    }

    public void setCar(CardLayout car) {
        this.car = car;
    }

    public XuanBaoEquipmentJpanel getEquipmentJpanel() {
        return this.equipmentJpanel;
    }

    public void setEquipmentJpanel(XuanBaoEquipmentJpanel equipmentJpanel) {
        this.equipmentJpanel = equipmentJpanel;
    }

    public XuanBaoAttributeJpanel getAttributeJpanel() {
        return this.attributeJpanel;
    }

    public void setAttributeJpanel(XuanBaoAttributeJpanel attributeJpanel) {
        this.attributeJpanel = attributeJpanel;
    }
}
