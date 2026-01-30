package org.come.Jpanel;

import java.awt.Graphics;
import org.come.until.FormsManagement;
import org.come.bean.ChongjipackBean;
import org.come.bean.LimitedTimeShopBean;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import com.tool.tcpimg.UIUtils;
import java.awt.Color;
import com.tool.btn.FormsOnOffBtn;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import org.come.model.Shop;
import java.util.List;
import javax.swing.JLabel;
import com.tool.btn.TaoBaoBtn;
import javax.swing.JPanel;

public class LimitedTimeShopJpanel extends JPanel
{
    private GoodsMinJpanel[] goodsMinJpanels;
    private TaoBaoBtn btnhp;
    private TaoBaoBtn btnep;
    private TaoBaoBtn btnsyy;
    private TaoBaoBtn btnxyy;
    private JLabel labpage;
    private int nowPage;
    private int maxPage;
    private List<Shop> shopList;
    private ImageIcon icon;
    
    public LimitedTimeShopJpanel() {
        this.goodsMinJpanels = new GoodsMinJpanel[10];
        this.nowPage = 1;
        this.maxPage = 1;
        this.setPreferredSize(new Dimension(735, 451));
        this.setOpaque(false);
        this.setLayout(null);
        FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 113);
        offBtn.setBounds(527, -5, 30, 30);
        this.add(offBtn);
        (this.labpage = new JLabel("1/1", 0)).setForeground(Color.white);
        this.labpage.setBounds(250, 408, 58, 17);
        this.labpage.setFont(UIUtils.TEXT_FONT1);
        this.add(this.labpage);
        (this.btnhp = new TaoBaoBtn("inkImg/button1/B30.png", 1, "首页", 40, this)).setBounds(210, 407, 34, 17);
        this.add(this.btnhp);
        (this.btnsyy = new TaoBaoBtn("inkImg/button/10.png", 1, "", 41, this)).setBounds(246, 407, 19, 20);
        this.add(this.btnsyy);
        (this.btnxyy = new TaoBaoBtn("inkImg/button/9.png", 1, "", 42, this)).setBounds(295, 408, 19, 20);
        this.add(this.btnxyy);
        (this.btnep = new TaoBaoBtn("inkImg/button1/B30.png", 1, "末页", 43, this)).setBounds(314, 408, 34, 17);
        this.add(this.btnep);
    }
    
    public void getGoods() {
        String sendmes = Agreement.getAgreement().LimitedTimeLshopAgreement();
        SendMessageUntil.toServer(sendmes);
    }
    
    public void showShopGoodsView(LimitedTimeShopBean limitedTimeShopBean) {
        this.shopList = ChongjipackBean.getShopList(limitedTimeShopBean.getChongjipackBeans());
        this.nowPage = 1;
        this.changeShopGoodsView();
        FormsManagement.showForm(113);
    }
    
    public void changeShopGoodsView() {
        this.changeShopGoodsView(this.shopList);
    }
    
    public void changeShopGoodsView(List<Shop> shopList) {
        for (int i = 0; i < this.goodsMinJpanels.length; ++i) {
            if (this.goodsMinJpanels[i] != null) {
                this.goodsMinJpanels[i].clearView();
                this.goodsMinJpanels[i].setVisible(false);
            }
        }
        this.maxPage = ((shopList.size() % 10 == 0) ? (shopList.size() / 10) : (shopList.size() / 10 + 1));
        this.labpage.setText(this.nowPage + "/" + this.maxPage);
        int num = 0;
        for (int j = 10 * (this.nowPage - 1); j < 10 * this.nowPage && j < shopList.size(); ++j) {
            int row = j / 5;
            int rank = j % 5;
            if (this.goodsMinJpanels[num] == null) {
                this.goodsMinJpanels[num] = new GoodsMinJpanel((Shop)shopList.get(j));
                this.goodsMinJpanels[j].setBounds(65 + rank * 100, 137 + row * 117, 114, 155);
                this.add(this.goodsMinJpanels[num]);
            }
            else {
                this.goodsMinJpanels[num].setVisible(true);
                this.goodsMinJpanels[num].addEshopMessage((Shop)shopList.get(j));
            }
            ++num;
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.icon == null) {
            this.icon = new ImageIcon("inkImg/background1/xg1.png");
        }
        g.drawImage(this.icon.getImage(), 0, 0, 552, 451, this);
        for (int i = 10 * (this.nowPage - 1); i < 10 * this.nowPage; ++i) {
            int row = i / 5;
            int rank = i % 5;
            if (this.goodsMinJpanels[i] != null) {
                this.goodsMinJpanels[i].setBounds(65 + rank * 100, 137 + row * 117, 114, 155);
            }
        }
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
    
    public JLabel getLabpage() {
        return this.labpage;
    }
    
    public void setLabpage(JLabel labpage) {
        this.labpage = labpage;
    }
    
    public int getNowPage() {
        return this.nowPage;
    }
    
    public void setNowPage(int nowPage) {
        this.nowPage = nowPage;
    }
    
    public int getMaxPage() {
        return this.maxPage;
    }
    
    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }
}
