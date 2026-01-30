package org.come.Jpanel;

import java.awt.Dimension;
import java.awt.CardLayout;
import javax.swing.JPanel;

public class TestChildCardJpanel extends JPanel
{
    private CardLayout car;
    private TestChildAttributeJpanel childAttributeJpanel;
    private TestChildTalentJpanel childTalentJpanel;
    
    public TestChildCardJpanel() {
        this.car = new CardLayout();
        this.setPreferredSize(new Dimension(240, 214));
        this.setLayout(this.car);
        this.setOpaque(false);
        this.add(this.childAttributeJpanel = new TestChildAttributeJpanel(), "l1");
        this.add(this.childTalentJpanel = new TestChildTalentJpanel(), "l2");
    }
    
    public CardLayout getCar() {
        return this.car;
    }
    
    public void setCar(CardLayout car) {
        this.car = car;
    }
    
    public TestChildAttributeJpanel getChildAttributeJpanel() {
        return this.childAttributeJpanel;
    }
    
    public void setChildAttributeJpanel(TestChildAttributeJpanel childAttributeJpanel) {
        this.childAttributeJpanel = childAttributeJpanel;
    }
    
    public TestChildTalentJpanel getChildTalentJpanel() {
        return this.childTalentJpanel;
    }
    
    public void setChildTalentJpanel(TestChildTalentJpanel childTalentJpanel) {
        this.childTalentJpanel = childTalentJpanel;
    }
}
