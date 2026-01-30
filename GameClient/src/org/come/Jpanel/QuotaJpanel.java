package org.come.Jpanel;

import org.come.model.Eshop;
import java.util.List;
import java.awt.Color;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import javax.swing.JLabel;
import com.tool.btn.TaoBaoBtn;
import javax.swing.JPanel;

public class QuotaJpanel extends JPanel
{
    private TaoBaoBtn exchangeMenuBtn;
    private TaoBaoBtn levelMenuBtn;
    private TaoBaoBtn discountMenuBtn;
    private TaoBaoBtn btnhp;
    private TaoBaoBtn btnep;
    private TaoBaoBtn btnsyy;
    private TaoBaoBtn btnxyy;
    private int maxPage;
    private int nowpage;
    private static JLabel labpage;
    private RechargeLeftGoodsJpanel[] imageJpanel;
    
    public QuotaJpanel() {
        this.maxPage = 1;
        this.nowpage = 1;
        this.imageJpanel = new RechargeLeftGoodsJpanel[9];
        this.setPreferredSize(new Dimension(682, 445));
        this.setLayout(null);
        this.setOpaque(false);
        this.setBackground(UIUtils.Color_BACK);
        (QuotaJpanel.labpage = new JLabel(this.nowpage + "/" + this.maxPage, 0)).setForeground(Color.white);
        QuotaJpanel.labpage.setBounds(311, 392, 58, 16);
        QuotaJpanel.labpage.setFont(UIUtils.TEXT_FONT1);
        this.add(QuotaJpanel.labpage);
        (this.btnhp = new TaoBaoBtn("img/xy2uiimg/homepage.png", 1, "", 50)).setBounds(253, 391, 34, 17);
        this.add(this.btnhp);
        (this.btnsyy = new TaoBaoBtn("img/xy2uiimg/29_png.button.btn_8.png", 1, "", 51)).setBounds(289, 390, 19, 20);
        this.add(this.btnsyy);
        (this.btnxyy = new TaoBaoBtn("img/xy2uiimg/36_png.button.btn_7.png", 1, "", 52)).setBounds(373, 390, 19, 20);
        this.add(this.btnxyy);
        (this.btnep = new TaoBaoBtn("img/xy2uiimg/endpage.png", 1, "末页", 53)).setBounds(392, 391, 34, 17);
        this.add(this.btnep);
        for (int i = 0; i < this.imageJpanel.length; ++i) {
            int row = i % 3;
            int col = i / 3;
            (this.imageJpanel[i] = new RechargeLeftGoodsJpanel(null)).setBounds(54 + row * 205, 52 + col * 100, 200, 90);
            this.add(this.imageJpanel[i]);
        }
        this.getExchangeMenuBtn();
        this.getLevelMenuBtn();
        this.getDiscountMenuBtn();
    }
    
    public void changeShopGoodsView(List<Eshop> eshopList) {
        for (int i = 0; i < this.imageJpanel.length; ++i) {
            this.imageJpanel[i].clearWindow();
        }
        this.maxPage = ((eshopList.size() % 9 == 0) ? (eshopList.size() / 9) : (eshopList.size() / 9 + 1));
        QuotaJpanel.labpage.setText(this.nowpage + "/" + this.maxPage);
        int num = 0;
        for (int j = (this.nowpage - 1) * 9; j < 9 * this.nowpage && eshopList.size() > j; ++j) {
            this.imageJpanel[num].addEshopMessage((Eshop)eshopList.get(j));
            ++num;
        }
    }
    
    public static JLabel getLabpage() {
        return QuotaJpanel.labpage;
    }
    
    public static void setLabpage(JLabel labpage) {
        QuotaJpanel.labpage = labpage;
    }
    
    public TaoBaoBtn getBtnhp() {
        return this.btnhp;
    }
    
    public void setBtnhp(TaoBaoBtn btnhp) {
        this.btnhp = btnhp;
    }
    
    public TaoBaoBtn getBtnep() {
        return this.btnep;
    }
    
    public void setBtnep(TaoBaoBtn btnep) {
        this.btnep = btnep;
    }
    
    public TaoBaoBtn getBtnsyy() {
        return this.btnsyy;
    }
    
    public void setBtnsyy(TaoBaoBtn btnsyy) {
        this.btnsyy = btnsyy;
    }
    
    public TaoBaoBtn getBtnxyy() {
        return this.btnxyy;
    }
    
    public void setBtnxyy(TaoBaoBtn btnxyy) {
        this.btnxyy = btnxyy;
    }
    
    public RechargeLeftGoodsJpanel[] getImageJpanel() {
        return this.imageJpanel;
    }
    
    public void setImageJpanel(RechargeLeftGoodsJpanel[] imageJpanel) {
        this.imageJpanel = imageJpanel;
    }
    
    public int getMaxPage() {
        return this.maxPage;
    }
    
    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }
    
    public int getNowpage() {
        return this.nowpage;
    }
    
    public void setNowpage(int nowpage) {
        this.nowpage = nowpage;
    }
    
    public TaoBaoBtn getExchangeMenuBtn() {
        if (this.exchangeMenuBtn == null) {
            (this.exchangeMenuBtn = new TaoBaoBtn("img/xy2uiimg/二级选项卡_商城_限购_赠送好礼_选中_w100,h78.png", 1, "", 30, this)).setBounds(54, 15, 100, 26);
            this.add(this.exchangeMenuBtn);
        }
        return this.exchangeMenuBtn;
    }
    
    public void setExchangeMenuBtn(TaoBaoBtn exchangeMenuBtn) {
        this.exchangeMenuBtn = exchangeMenuBtn;
    }
    
    public TaoBaoBtn getLevelMenuBtn() {
        if (this.levelMenuBtn == null) {
            (this.levelMenuBtn = new TaoBaoBtn("img/xy2uiimg/二级选项卡_商城_限购_冲级必备_未选中_w100,h78.png", 1, "", 31, this)).setBounds(156, 15, 100, 26);
            this.add(this.levelMenuBtn);
        }
        return this.levelMenuBtn;
    }
    
    public void setLevelMenuBtn(TaoBaoBtn levelMenuBtn) {
        this.levelMenuBtn = levelMenuBtn;
    }
    
    public TaoBaoBtn getDiscountMenuBtn() {
        if (this.discountMenuBtn == null) {
            (this.discountMenuBtn = new TaoBaoBtn("img/xy2uiimg/二级选项卡_商城_限购_显示折扣_未选中_w100,h78.png", 1, "", 32, this)).setBounds(258, 15, 100, 26);
            this.add(this.discountMenuBtn);
        }
        return this.discountMenuBtn;
    }
    
    public void setDiscountMenuBtn(TaoBaoBtn discountMenuBtn) {
        this.discountMenuBtn = discountMenuBtn;
    }
}
