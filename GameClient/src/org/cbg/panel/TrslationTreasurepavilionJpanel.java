package org.cbg.panel;

import java.awt.Graphics;
import com.tool.btn.FormsOnOffBtn;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import org.cbg.btn.TrslationMainBtn;
import javax.swing.JPanel;

public class TrslationTreasurepavilionJpanel extends JPanel
{
    private TranslationMainCardJpanel translationMainCardJpanel;
    private TrslationMainBtn iwantbuy;
    private TrslationMainBtn iwantsent;
    private TrslationMainBtn icangbaoge;
    private TrslationMainBtn imes;
    private ImageIcon icon1;
    
    public TrslationTreasurepavilionJpanel() {
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(662, 515));
            this.setLayout(null);
            this.setOpaque(false);
            this.add(this.translationMainCardJpanel = new TranslationMainCardJpanel());
            (this.iwantbuy = new TrslationMainBtn("inkImg/button/B113.png", 1, 1, this)).setBounds(52, 23, 110, 35);
            (this.iwantsent = new TrslationMainBtn("inkImg/button/B114.png", 1, 2, this)).setBounds(164, 23, 110, 35);
            (this.icangbaoge = new TrslationMainBtn("inkImg/button/B110.png", 1, 3, this)).setBounds(276, 23, 110, 35);
            (this.imes = new TrslationMainBtn("inkImg/button/B116.png", 1, 4, this)).setBounds(388, 23, 110, 35);
            FormsOnOffBtn guanbi = new FormsOnOffBtn("inkImg/button/1.png", 1, 78);
            guanbi.setBounds(625, 10, 25, 25);
            this.add(guanbi);
            this.add(this.iwantbuy);
            this.add(this.icangbaoge);
            this.add(this.imes);
            this.add(this.iwantsent);
        }
        else {
            this.setPreferredSize(new Dimension(636, 537));
            this.setLayout(null);
            this.setOpaque(false);
            this.add(this.translationMainCardJpanel = new TranslationMainCardJpanel());
            (this.iwantbuy = new TrslationMainBtn("img/xy2uiimg/B113.png", 1, 1, this)).setBounds(52, 23, 110, 35);
            (this.iwantsent = new TrslationMainBtn("img/xy2uiimg/B114.png", 1, 2, this)).setBounds(164, 23, 110, 35);
            (this.icangbaoge = new TrslationMainBtn("img/xy2uiimg/B110.png", 1, 3, this)).setBounds(276, 23, 110, 35);
            (this.imes = new TrslationMainBtn("img/xy2uiimg/B116.png", 1, 4, this)).setBounds(388, 23, 110, 35);
            FormsOnOffBtn guanbi = new FormsOnOffBtn("img/xy2uiimg/8_jpg.button.btn_close.jpg", 1, 78);
            guanbi.setBounds(613, 0, 23, 23);
            this.add(guanbi);
            this.add(this.iwantbuy);
            this.add(this.icangbaoge);
            this.add(this.imes);
            this.add(this.iwantsent);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        if (MyIsif.getStyle().equals("水墨")) {
            super.paintComponent(g);
            if (this.icon1 == null) {
                this.icon1 = new ImageIcon("inkImg/background/14.png");
            }
            g.drawImage(this.icon1.getImage(), 0, 0, 662, 515, this);
        }
        else {
            super.paintComponent(g);
            if (this.icon1 == null) {
                this.icon1 = new ImageIcon("img/xy2uiimg/物集集市总底板.png");
            }
            g.drawImage(this.icon1.getImage(), 0, 0, 636, 537, this);
        }
    }
    
    public TranslationMainCardJpanel getTranslationMainCardJpanel() {
        return this.translationMainCardJpanel;
    }
    
    public void setTranslationMainCardJpanel(TranslationMainCardJpanel translationMainCardJpanel) {
        this.translationMainCardJpanel = translationMainCardJpanel;
    }
    
    public TrslationMainBtn getIwantbuy() {
        return this.iwantbuy;
    }
    
    public void setIwantbuy(TrslationMainBtn iwantbuy) {
        this.iwantbuy = iwantbuy;
    }
    
    public TrslationMainBtn getIwantsent() {
        return this.iwantsent;
    }
    
    public void setIwantsent(TrslationMainBtn iwantsent) {
        this.iwantsent = iwantsent;
    }
    
    public TrslationMainBtn getIcangbaoge() {
        return this.icangbaoge;
    }
    
    public void setIcangbaoge(TrslationMainBtn icangbaoge) {
        this.icangbaoge = icangbaoge;
    }
    
    public TrslationMainBtn getImes() {
        return this.imes;
    }
    
    public void setImes(TrslationMainBtn imes) {
        this.imes = imes;
    }
    
    public ImageIcon getIcon1() {
        return this.icon1;
    }
    
    public void setIcon1(ImageIcon icon1) {
        this.icon1 = icon1;
    }
}
