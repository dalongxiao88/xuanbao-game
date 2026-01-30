package org.come.Jpanel;

import java.awt.Color;
import com.tool.tcpimg.UIUtils;
import java.awt.Graphics;
import org.come.entity.Goodstable;
import org.come.until.FormsManagement;
import org.come.until.CutButtonImage;
import org.come.until.GoodsListFromServerUntil;
import org.come.until.UserMessUntil;
import org.come.bean.XXGDBean;
import org.come.Frame.ZhuFrame;
import org.come.until.Util;
import java.util.ArrayList;
import org.come.bean.ChongjipackBean;
import java.util.List;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import com.tool.btn.FormsOnOffBtn;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import org.come.mouslisten.VipGoodsMouselistener;
import com.tool.btn.VipShopBtn;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EveryDayOddsJpanel extends JPanel
{
    private JLabel goodsImg;
    private String name;
    private String money;
    private VipShopBtn buyBtn;
    private VipGoodsMouselistener mouselistener;
    private ImageIcon iconBack;
    private ImageIcon iconTop;
    
    public EveryDayOddsJpanel() {
        this.name = "VIP会员-超级圣兽丹";
        this.money = "150000仙玉";
        this.setPreferredSize(new Dimension(298, 316));
        this.setOpaque(false);
        this.setLayout(null);
        FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/hongmu/s74.png", 1, 102);
        offBtn.setBounds(273, 0, 25, 25);
        this.add(offBtn);
        this.getGoodsImg();
        this.getBuyBtn();
    }
    
    public void getGoods() {
        String sendmes = Agreement.getAgreement().DayforonegetAgreement();
        SendMessageUntil.toServer(sendmes);
    }
    
    public void showGoods(List<ChongjipackBean> chongjipackBeans) {
        if (chongjipackBeans == null) {
            chongjipackBeans = new ArrayList<>();
        }
        ChongjipackBean chongjipackBean = null;
        long time = Util.getTime();
        int i = 0;
        if (i < chongjipackBeans.size()) {
            chongjipackBean = (ChongjipackBean)chongjipackBeans.get(i);
        }
        if (chongjipackBean == null) {
            ZhuFrame.getZhuJpanel().addPrompt2("暂无每日特惠");
            return;
        }
        List<XXGDBean> goods = ChongjipackBean.getGoods(chongjipackBean.getPackgoods());
        for (int j = 0; j < goods.size(); ++j) {}
        Goodstable goodstable = UserMessUntil.getgoodstable(((XXGDBean)goods.get(0)).getId());
        this.goodsImg.setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 107, 107));
        this.name = goodstable.getGoodsname();
        this.money = chongjipackBean.getCanpaymoney() + "仙玉";
        this.mouselistener.setGoodstable(goodstable);
        this.buyBtn.setText("购买");
        this.buyBtn.setIcon(CutButtonImage.getImage("inkImg/hongmu/21_png.button.tab_nex.png", -1, -1));
        this.buyBtn.setBtn(1);
        if (!FormsManagement.getInternalForm(102).getFrame().isVisible()) {
            FormsManagement.showForm(102);
        }
    }
    
    public void exchangeGoods() {
        String sendmes = Agreement.getAgreement().DayforonesureAgreement();
        SendMessageUntil.toServer(sendmes);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.iconBack == null) {
            this.iconBack = CutButtonImage.getImage("inkImg/background/S10.png", -1, -1);
        }
        g.drawImage(this.iconBack.getImage(), 0, 0, 298, 316, this);
        if (this.iconTop == null) {
            this.iconTop = CutButtonImage.getImage("inkImg/background/S12.png", -1, -1);
        }
        g.drawImage(this.iconTop.getImage(), 35, 33, 230, 77, this);
        if (this.name != null) {
            g.setFont(UIUtils.TEXT_FONT1);
            g.drawString(this.name, 100, 238);
        }
        if (this.money != null) {
            g.setFont(UIUtils.TEXT_FONT1);
            g.setColor(Color.red);
            g.drawString(this.money, 100, 258);
        }
    }
    
    public JLabel getGoodsImg() {
        if (this.goodsImg == null) {
            (this.goodsImg = new JLabel()).setBounds(102, 112, 107, 107);
            this.mouselistener = new VipGoodsMouselistener();
            this.goodsImg.addMouseListener(this.mouselistener);
            this.add(this.goodsImg);
        }
        return this.goodsImg;
    }
    
    public void setGoodsImg(JLabel goodsImg) {
        this.goodsImg = goodsImg;
    }
    
    @Override
    public String getName() {
        return this.name;
    }
    
    @Override
    public void setName(String name) {
        this.name = name;
    }
    
    public String getMoney() {
        return this.money;
    }
    
    public void setMoney(String money) {
        this.money = money;
    }
    
    public VipShopBtn getBuyBtn() {
        if (this.buyBtn == null) {
            (this.buyBtn = new VipShopBtn("inkImg/hongmu/21_png.button.tab_nex.png", 1, 4, "购买", this)).setBounds(180, 244, 34, 17);
            this.add(this.buyBtn);
        }
        return this.buyBtn;
    }
    
    public void setBuyBtn(VipShopBtn buyBtn) {
        this.buyBtn = buyBtn;
    }
    
    public ImageIcon getIconBack() {
        return this.iconBack;
    }
    
    public void setIconBack(ImageIcon iconBack) {
        this.iconBack = iconBack;
    }
    
    public ImageIcon getIconTop() {
        return this.iconTop;
    }
    
    public void setIconTop(ImageIcon iconTop) {
        this.iconTop = iconTop;
    }
    
    public VipGoodsMouselistener getMouselistener() {
        return this.mouselistener;
    }
    
    public void setMouselistener(VipGoodsMouselistener mouselistener) {
        this.mouselistener = mouselistener;
    }
}
