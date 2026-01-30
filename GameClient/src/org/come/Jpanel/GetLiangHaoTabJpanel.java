package org.come.Jpanel;

import java.awt.Dimension;
import java.awt.CardLayout;
import javax.swing.JPanel;

public class GetLiangHaoTabJpanel extends JPanel
{
    private static final long serialVersionUID = 1L;
    private CardLayout car;
    private GetLiangHaoOneJpanel getLiangHaoOneJpanel;
    private GetLiangHaoTwoJpanel getLiangHaoTwoJpanel;
    private GetLiangHaoThreeJpanel getLiangHaoThreeJpanel;
    
    public GetLiangHaoTabJpanel() {
        this.car = new CardLayout();
        this.setPreferredSize(new Dimension(697, 538));
        this.setLayout(this.car);
        this.setOpaque(false);
        this.add(this.getLiangHaoOneJpanel = new GetLiangHaoOneJpanel(), "2");
        this.add(this.getLiangHaoTwoJpanel = new GetLiangHaoTwoJpanel(), "3");
        this.add(this.getLiangHaoThreeJpanel = new GetLiangHaoThreeJpanel(), "4");
    }
    
    public CardLayout getCar() {
        return this.car;
    }
    
    public void setCar(CardLayout car) {
        this.car = car;
    }
    
    public GetLiangHaoOneJpanel getGetLiangHaoOneJpanel() {
        return this.getLiangHaoOneJpanel;
    }
    
    public void setGetLiangHaoOneJpanel(GetLiangHaoOneJpanel getLiangHaoOneJpanel) {
        this.getLiangHaoOneJpanel = getLiangHaoOneJpanel;
    }
    
    public GetLiangHaoTwoJpanel getGetLiangHaoTwoJpanel() {
        return this.getLiangHaoTwoJpanel;
    }
    
    public void setGetLiangHaoTwoJpanel(GetLiangHaoTwoJpanel getLiangHaoTwoJpanel) {
        this.getLiangHaoTwoJpanel = getLiangHaoTwoJpanel;
    }
    
    public GetLiangHaoThreeJpanel getGetLiangHaoThreeJpanel() {
        return this.getLiangHaoThreeJpanel;
    }
    
    public void setGetLiangHaoThreeJpanel(GetLiangHaoThreeJpanel getLiangHaoThreeJpanel) {
        this.getLiangHaoThreeJpanel = getLiangHaoThreeJpanel;
    }
}
