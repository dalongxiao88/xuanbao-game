package org.cbg.panel;

import org.come.until.CutButtonImage;
import org.cbg.frame.TrslationMainJframe;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import org.cbg.btn.TrslationMainBtn;
import javax.swing.JPanel;

public class TraslationWantBuyJpanel extends JPanel
{
    private TrslationMainBtn shouye;
    private TrslationMainBtn dahuabi;
    private TrslationMainBtn daoju;
    private TrslationMainBtn zhaohuansou;
    private TrslationMainBtn zhuangbei;
    private TrslationMainBtn lingbao;
    private TrslationMainBtn gongshiqi;
    private TrslationMainBtn shoucang;
    private TraslationWantBuyCardJapel traslationWantBuyCardJapel;
    
    public TraslationWantBuyJpanel() {
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(590, 380));
            this.setLayout(null);
            this.setOpaque(false);
            this.add(this.traslationWantBuyCardJapel = new TraslationWantBuyCardJapel());
            this.shouye = new TrslationMainBtn("inkImg/button/B142.png", 1, 5, this);
            this.daoju = new TrslationMainBtn("inkImg/button/B129.png", 1, 7, this);
            this.zhaohuansou = new TrslationMainBtn("inkImg/button/B149.png", 1, 8, this);
            this.zhuangbei = new TrslationMainBtn("inkImg/button/B153.png", 1, 9, this);
            this.lingbao = new TrslationMainBtn("inkImg/button/B137.png", 1, 10, this);
            this.gongshiqi = new TrslationMainBtn("inkImg/button/B131.png", 1, 11, this);
            this.shoucang = new TrslationMainBtn("inkImg/button/B139.png", 1, 12, this);
            this.shouye.setBounds(20, 0, 63, 26);
            this.daoju.setBounds(85, 0, 63, 26);
            this.zhaohuansou.setBounds(150, 0, 63, 26);
            this.zhuangbei.setBounds(215, 0, 63, 26);
            this.lingbao.setBounds(280, 0, 63, 26);
            this.gongshiqi.setBounds(345, 0, 63, 26);
            this.shoucang.setBounds(410, 0, 63, 26);
            this.add(this.shouye);
            this.add(this.daoju);
            this.add(this.zhaohuansou);
            this.add(this.zhuangbei);
            this.add(this.lingbao);
            this.add(this.gongshiqi);
            this.add(this.shoucang);
        }
        else {
            this.setPreferredSize(new Dimension(590, 380));
            this.setLayout(null);
            this.setOpaque(false);
            this.add(this.traslationWantBuyCardJapel = new TraslationWantBuyCardJapel());
            this.shouye = new TrslationMainBtn("img/xy2uiimg/B142.png", 1, 5, this);
            this.daoju = new TrslationMainBtn("img/xy2uiimg/B129.png", 1, 7, this);
            this.zhaohuansou = new TrslationMainBtn("img/xy2uiimg/B149.png", 1, 8, this);
            this.zhuangbei = new TrslationMainBtn("img/xy2uiimg/B234.png", 1, 9, this);
            this.lingbao = new TrslationMainBtn("img/xy2uiimg/B137.png", 1, 10, this);
            this.gongshiqi = new TrslationMainBtn("img/xy2uiimg/B131.png", 1, 11, this);
            this.shoucang = new TrslationMainBtn("img/xy2uiimg/B139.png", 1, 12, this);
            this.shouye.setBounds(20, 0, 75, 26);
            this.daoju.setBounds(85, 0, 75, 26);
            this.zhaohuansou.setBounds(150, 0, 75, 26);
            this.zhuangbei.setBounds(215, 0, 75, 26);
            this.lingbao.setBounds(280, 0, 75, 26);
            this.gongshiqi.setBounds(345, 0, 75, 26);
            this.shoucang.setBounds(410, 0, 75, 26);
            this.add(this.shouye);
            this.add(this.daoju);
            this.add(this.zhaohuansou);
            this.add(this.zhuangbei);
            this.add(this.lingbao);
            this.add(this.gongshiqi);
            this.add(this.shoucang);
        }
    }
    
    public void changeOrder() {
        TraslationWantBuyCardJapel traslationWantBuyCardJapel = TrslationMainJframe.getTrslationMainJframe().getTrslationMainJpanel().getTranslationMainCardJpanel().getTraslationWantBuyJpanel().getTraslationWantBuyCardJapel();
        traslationWantBuyCardJapel.getTraslationWantBuyDaojuJpanel().setShowOrder(0);
        traslationWantBuyCardJapel.getTraslationWantBuyDahuabiJpanel().setShowOrder(0);
        traslationWantBuyCardJapel.getTraslationWantBuyZhaohuanshouJpanel().setShowOrder(0);
        traslationWantBuyCardJapel.getTraslationWantBuyZhaungbeiJpanel().setShowOrder(0);
        traslationWantBuyCardJapel.getTraslationWantBuyLingbaoJpanel().setShowOrder(0);
        traslationWantBuyCardJapel.getTraslationWantBuyGongshiqiJpanel().setShowOrder(0);
        traslationWantBuyCardJapel.getTraslationWantBuyDaojuJpanel().getUpSort().setIcon(CutButtonImage.getImage("img/xy2uiimg/↑(白色)w6,h11px.png", 6, 11));
        traslationWantBuyCardJapel.getTraslationWantBuyDaojuJpanel().getDownSort().setIcon(CutButtonImage.getImage("img/xy2uiimg/↓(白色)w6,h11px.png", 6, 11));
        traslationWantBuyCardJapel.getTraslationWantBuyDahuabiJpanel().getUpSort().setIcon(CutButtonImage.getImage("img/xy2uiimg/↑(白色)w6,h11px.png", 6, 11));
        traslationWantBuyCardJapel.getTraslationWantBuyDahuabiJpanel().getDownSort().setIcon(CutButtonImage.getImage("img/xy2uiimg/↓(白色)w6,h11px.png", 6, 11));
        traslationWantBuyCardJapel.getTraslationWantBuyZhaohuanshouJpanel().getUpSort().setIcon(CutButtonImage.getImage("img/xy2uiimg/↑(白色)w6,h11px.png", 6, 11));
        traslationWantBuyCardJapel.getTraslationWantBuyZhaohuanshouJpanel().getDownSort().setIcon(CutButtonImage.getImage("img/xy2uiimg/↓(白色)w6,h11px.png", 6, 11));
        traslationWantBuyCardJapel.getTraslationWantBuyZhaungbeiJpanel().getSortUp().setIcon(CutButtonImage.getImage("img/xy2uiimg/↑(白色)w6,h11px.png", 6, 11));
        traslationWantBuyCardJapel.getTraslationWantBuyZhaungbeiJpanel().getSortDown().setIcon(CutButtonImage.getImage("img/xy2uiimg/↓(白色)w6,h11px.png", 6, 11));
        traslationWantBuyCardJapel.getTraslationWantBuyLingbaoJpanel().getUpSort().setIcon(CutButtonImage.getImage("img/xy2uiimg/↑(白色)w6,h11px.png", 6, 11));
        traslationWantBuyCardJapel.getTraslationWantBuyLingbaoJpanel().getDownSort().setIcon(CutButtonImage.getImage("img/xy2uiimg/↓(白色)w6,h11px.png", 6, 11));
        traslationWantBuyCardJapel.getTraslationWantBuyGongshiqiJpanel().getUpSort().setIcon(CutButtonImage.getImage("img/xy2uiimg/↑(白色)w6,h11px.png", 6, 11));
        traslationWantBuyCardJapel.getTraslationWantBuyGongshiqiJpanel().getDownSort().setIcon(CutButtonImage.getImage("img/xy2uiimg/↓(白色)w6,h11px.png", 6, 11));
    }
    
    public TrslationMainBtn getShouye() {
        return this.shouye;
    }
    
    public void setShouye(TrslationMainBtn shouye) {
        this.shouye = shouye;
    }
    
    public TrslationMainBtn getDahuabi() {
        return this.dahuabi;
    }
    
    public void setDahuabi(TrslationMainBtn dahuabi) {
        this.dahuabi = dahuabi;
    }
    
    public TrslationMainBtn getDaoju() {
        return this.daoju;
    }
    
    public void setDaoju(TrslationMainBtn daoju) {
        this.daoju = daoju;
    }
    
    public TrslationMainBtn getZhaohuansou() {
        return this.zhaohuansou;
    }
    
    public void setZhaohuansou(TrslationMainBtn zhaohuansou) {
        this.zhaohuansou = zhaohuansou;
    }
    
    public TrslationMainBtn getZhuangbei() {
        return this.zhuangbei;
    }
    
    public void setZhuangbei(TrslationMainBtn zhuangbei) {
        this.zhuangbei = zhuangbei;
    }
    
    public TrslationMainBtn getLingbao() {
        return this.lingbao;
    }
    
    public void setLingbao(TrslationMainBtn lingbao) {
        this.lingbao = lingbao;
    }
    
    public TrslationMainBtn getGongshiqi() {
        return this.gongshiqi;
    }
    
    public void setGongshiqi(TrslationMainBtn gongshiqi) {
        this.gongshiqi = gongshiqi;
    }
    
    public TrslationMainBtn getShoucang() {
        return this.shoucang;
    }
    
    public void setShoucang(TrslationMainBtn shoucang) {
        this.shoucang = shoucang;
    }
    
    public TraslationWantBuyCardJapel getTraslationWantBuyCardJapel() {
        return this.traslationWantBuyCardJapel;
    }
    
    public void setTraslationWantBuyCardJapel(TraslationWantBuyCardJapel traslationWantBuyCardJapel) {
        this.traslationWantBuyCardJapel = traslationWantBuyCardJapel;
    }
}
