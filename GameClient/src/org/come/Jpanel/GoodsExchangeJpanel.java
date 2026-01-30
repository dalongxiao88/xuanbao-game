package org.come.Jpanel;

import javax.swing.ImageIcon;
import java.awt.Graphics;
import org.come.mouslisten.GoodsForGoodsChooseGetMouslisten;
import javax.swing.BorderFactory;
import org.come.until.ScrollUI;
import com.tool.btn.FormsOnOffBtn;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import org.come.bean.GoodsForGoodsBean;
import java.util.List;
import com.tool.btn.GoodsExchangeBtn;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JPanel;

public class GoodsExchangeJpanel extends JPanel
{
    private JScrollPane goodScrollPane1;
    private JScrollPane goodScrollPane2;
    private JTextArea srmc;
    private GoodsExchangeBtn ssBtn;
    private GoodsExchangeBtn btnsurebuy;
    private GoodsExchangeBtn qbBtn;
    private GoodsExchangeBtn tzBtn;
    private GoodsExchangeBtn cwBtn;
    private GoodsExchangeBtn xqBtn;
    private GoodsExchangeBtn zhBtn;
    private List<GoodsForGoodsBean> goodgodbean;
    private GoodForGoodJpanel[] goodForGoodJpanel;
    private GoodsForDelGoodsJpanel goodsForDelGoodsJpanel;
    
    public GoodsExchangeJpanel() {
        this.setPreferredSize(new Dimension(622, 486));
        this.setLayout(null);
        this.setBackground(Color.BLACK);
    }
    
    public void initJlabel() {
        this.srmc = new JTextArea("");
        Font f = new Font("宋体", 0, 16);
        this.srmc.setFont(f);
        this.srmc.setForeground(Color.yellow);
        this.srmc.setOpaque(false);
        this.srmc.setBounds(96, 40, 150, 18);
        this.add(this.srmc);
        (this.ssBtn = new GoodsExchangeBtn("img/xy2uiimg/111.png", 1, "搜索", 1, this)).setBounds(265, 38, 85, 24);
        this.add(this.ssBtn);
        (this.btnsurebuy = new GoodsExchangeBtn("img/xy2uiimg/111.png", 1, "确认兑换", 2, this)).setBounds(475, 425, 85, 24);
        this.add(this.btnsurebuy);
        (this.qbBtn = new GoodsExchangeBtn("img/xy2uiimg/112.png", 1, "全部", 3, this)).setBounds(35, 65, 65, 24);
        this.add(this.qbBtn);
        (this.tzBtn = new GoodsExchangeBtn("img/xy2uiimg/112.png", 1, "套装", 4, this)).setBounds(105, 65, 65, 24);
        this.add(this.tzBtn);
        (this.cwBtn = new GoodsExchangeBtn("img/xy2uiimg/112.png", 1, "宠物", 5, this)).setBounds(175, 65, 65, 24);
        this.add(this.cwBtn);
        (this.xqBtn = new GoodsExchangeBtn("img/xy2uiimg/112.png", 1, "仙器", 6, this)).setBounds(245, 65, 65, 24);
        this.add(this.xqBtn);
        (this.zhBtn = new GoodsExchangeBtn("img/xy2uiimg/112.png", 1, "杂货", 7, this)).setBounds(315, 65, 65, 24);
        this.add(this.zhBtn);
        FormsOnOffBtn offBtn = new FormsOnOffBtn("img/xy2uiimg/8_jpg.button.btn_close.jpg", 1, 90);
        offBtn.setBounds(600, 0, 23, 23);
        this.add(offBtn);
    }
    
    public void initGoodsList(List<GoodsForGoodsBean> goodgodbean) {
        this.initJlabel();
        this.goodgodbean = goodgodbean;
        (this.goodsForDelGoodsJpanel = new GoodsForDelGoodsJpanel(this)).setPreferredSize(new Dimension(180, 600));
        (this.goodScrollPane2 = new JScrollPane()).setVerticalScrollBarPolicy(22);
        this.goodScrollPane2.getVerticalScrollBar().setUI(new ScrollUI());
        this.goodScrollPane2.getViewport().setOpaque(false);
        this.goodScrollPane2.setOpaque(false);
        this.goodScrollPane2.setBounds(420, 81, 180, 324);
        this.goodScrollPane2.setBorder(BorderFactory.createLineBorder(new Color(31, 7, 8)));
        this.goodScrollPane2.setHorizontalScrollBarPolicy(31);
        this.goodScrollPane2.getViewport().add(this.goodsForDelGoodsJpanel);
        this.goodScrollPane2.validate();
        this.add(this.goodScrollPane2);
        JPanel jpa = new JPanel();
        jpa.setPreferredSize(new Dimension(160, 600));
        jpa.setOpaque(false);
        (this.goodScrollPane1 = new JScrollPane()).setVerticalScrollBarPolicy(22);
        this.goodScrollPane1.getVerticalScrollBar().setUI(new ScrollUI());
        this.goodScrollPane1.getViewport().setOpaque(false);
        this.goodScrollPane1.setOpaque(false);
        this.goodScrollPane1.setBounds(26, 119, 384, 338);
        this.goodScrollPane1.setBorder(BorderFactory.createLineBorder(new Color(31, 7, 8)));
        this.goodScrollPane1.setHorizontalScrollBarPolicy(31);
        this.goodScrollPane1.getViewport().add(jpa);
        this.goodScrollPane1.validate();
        this.goodForGoodJpanel = new GoodForGoodJpanel[goodgodbean.size()];
        for (int i = 0; i < goodgodbean.size(); ++i) {
            (this.goodForGoodJpanel[i] = new GoodForGoodJpanel((GoodsForGoodsBean)goodgodbean.get(i))).setPreferredSize(new Dimension(355, 40));
            this.goodForGoodJpanel[i].addMouseListener(new GoodsForGoodsChooseGetMouslisten(this.goodForGoodJpanel, i, this.goodsForDelGoodsJpanel, (GoodsForGoodsBean)goodgodbean.get(i)));
            jpa.add(this.goodForGoodJpanel[i]);
        }
        this.add(this.goodScrollPane1);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(new ImageIcon("img/xy2uiimg/11_png.xy2uiimg.cash_bg.png").getImage(), 0, 0, 622, 486, this);
    }
    
    public JScrollPane getgoodScrollPane1() {
        return this.goodScrollPane1;
    }
    
    public void setgoodScrollPane1(JScrollPane goodScrollPane1) {
        this.goodScrollPane1 = goodScrollPane1;
    }
    
    public JScrollPane getgoodScrollPane2() {
        return this.goodScrollPane2;
    }
    
    public void setgoodScrollPane2(JScrollPane goodScrollPane2) {
        this.goodScrollPane2 = goodScrollPane2;
    }
    
    public GoodsExchangeBtn getBtnsurebuy() {
        return this.btnsurebuy;
    }
    
    public void setBtnsurebuy(GoodsExchangeBtn btnsurebuy) {
        this.btnsurebuy = btnsurebuy;
    }
    
    public GoodsExchangeBtn getBtnssBtn() {
        return this.ssBtn;
    }
    
    public void setBtnss(GoodsExchangeBtn ssBtn) {
        this.ssBtn = ssBtn;
    }
    
    public GoodsExchangeBtn getBtnqbBtn() {
        return this.qbBtn;
    }
    
    public void setBtnqb(GoodsExchangeBtn qbBtn) {
        this.qbBtn = qbBtn;
    }
    
    public GoodsExchangeBtn getBtntzBtn() {
        return this.tzBtn;
    }
    
    public void setBtntz(GoodsExchangeBtn tzBtn) {
        this.tzBtn = tzBtn;
    }
    
    public GoodsExchangeBtn getBtncwBtn() {
        return this.cwBtn;
    }
    
    public void setBtncw(GoodsExchangeBtn cwBtn) {
        this.cwBtn = cwBtn;
    }
    
    public GoodsExchangeBtn getBtnxqBtn() {
        return this.xqBtn;
    }
    
    public void setBtnxq(GoodsExchangeBtn xqBtn) {
        this.xqBtn = xqBtn;
    }
    
    public GoodsExchangeBtn getBtnzhBtn() {
        return this.zhBtn;
    }
    
    public void setBtnzh(GoodsExchangeBtn zhBtn) {
        this.zhBtn = zhBtn;
    }
    
    public JScrollPane getGoodScrollPane1() {
        return this.goodScrollPane1;
    }
    
    public void setGoodScrollPane1(JScrollPane goodScrollPane1) {
        this.goodScrollPane1 = goodScrollPane1;
    }
    
    public JScrollPane getGoodScrollPane2() {
        return this.goodScrollPane2;
    }
    
    public void setGoodScrollPane2(JScrollPane goodScrollPane2) {
        this.goodScrollPane2 = goodScrollPane2;
    }
    
    public JTextArea getSrmc() {
        return this.srmc;
    }
    
    public void setSrmc(JTextArea srmc) {
        this.srmc = srmc;
    }
    
    public GoodsExchangeBtn getSsBtn() {
        return this.ssBtn;
    }
    
    public void setSsBtn(GoodsExchangeBtn ssBtn) {
        this.ssBtn = ssBtn;
    }
    
    public GoodsExchangeBtn getQbBtn() {
        return this.qbBtn;
    }
    
    public void setQbBtn(GoodsExchangeBtn qbBtn) {
        this.qbBtn = qbBtn;
    }
    
    public GoodsExchangeBtn getTzBtn() {
        return this.tzBtn;
    }
    
    public void setTzBtn(GoodsExchangeBtn tzBtn) {
        this.tzBtn = tzBtn;
    }
    
    public GoodsExchangeBtn getCwBtn() {
        return this.cwBtn;
    }
    
    public void setCwBtn(GoodsExchangeBtn cwBtn) {
        this.cwBtn = cwBtn;
    }
    
    public GoodsExchangeBtn getXqBtn() {
        return this.xqBtn;
    }
    
    public void setXqBtn(GoodsExchangeBtn xqBtn) {
        this.xqBtn = xqBtn;
    }
    
    public GoodsExchangeBtn getZhBtn() {
        return this.zhBtn;
    }
    
    public void setZhBtn(GoodsExchangeBtn zhBtn) {
        this.zhBtn = zhBtn;
    }
    
    public List<GoodsForGoodsBean> getGoodgodbean() {
        return this.goodgodbean;
    }
    
    public void setGoodgodbean(List<GoodsForGoodsBean> goodgodbean) {
        this.goodgodbean = goodgodbean;
    }
    
    public GoodForGoodJpanel[] getGoodForGoodJpanel() {
        return this.goodForGoodJpanel;
    }
    
    public void setGoodForGoodJpanel(GoodForGoodJpanel[] goodForGoodJpanel) {
        this.goodForGoodJpanel = goodForGoodJpanel;
    }
    
    public GoodsForDelGoodsJpanel getGoodsForDelGoodsJpanel() {
        return this.goodsForDelGoodsJpanel;
    }
    
    public void setGoodsForDelGoodsJpanel(GoodsForDelGoodsJpanel goodsForDelGoodsJpanel) {
        this.goodsForDelGoodsJpanel = goodsForDelGoodsJpanel;
    }
}
