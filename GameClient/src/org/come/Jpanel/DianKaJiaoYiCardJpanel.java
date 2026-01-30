package org.come.Jpanel;

import java.awt.Dimension;
import java.awt.CardLayout;
import javax.swing.JPanel;

public class DianKaJiaoYiCardJpanel extends JPanel
{
    private static final long serialVersionUID = 1L;
    private CardLayout car;
    private DianKaJiaoYiGouMaiJpanel goumaiJpanel;
    private DianKaJiaoYiJiShouJpanel jishouJpanel;
    private DianKaJiaoYiChouJiangJpanel choujiangJpanel;
    
    public DianKaJiaoYiCardJpanel() {
        this.car = new CardLayout();
        this.setPreferredSize(new Dimension(480, 520));
        this.setLayout(this.car);
        this.setOpaque(false);
        this.add(this.goumaiJpanel = new DianKaJiaoYiGouMaiJpanel(), "2");
        this.add(this.jishouJpanel = new DianKaJiaoYiJiShouJpanel(), "3");
        this.add(this.choujiangJpanel = new DianKaJiaoYiChouJiangJpanel(), "4");
    }
    
    public CardLayout getCar() {
        return this.car;
    }
    
    public void setCar(CardLayout car) {
        this.car = car;
    }
    
    public DianKaJiaoYiGouMaiJpanel getGoumaiJpanel() {
        return this.goumaiJpanel;
    }
    
    public void setGoumaiJpanel(DianKaJiaoYiGouMaiJpanel goumaiJpanel) {
        this.goumaiJpanel = goumaiJpanel;
    }
    
    public DianKaJiaoYiJiShouJpanel getJishouJpanel() {
        return this.jishouJpanel;
    }
    
    public void setJishouJpanel(DianKaJiaoYiJiShouJpanel jishouJpanel) {
        this.jishouJpanel = jishouJpanel;
    }
    
    public DianKaJiaoYiChouJiangJpanel getChoujiangJpanel() {
        return this.choujiangJpanel;
    }
    
    public void setChoujiangJpanel(DianKaJiaoYiChouJiangJpanel choujiangJpanel) {
        this.choujiangJpanel = choujiangJpanel;
    }
}
