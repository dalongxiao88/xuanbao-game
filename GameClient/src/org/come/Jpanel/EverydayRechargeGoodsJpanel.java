package org.come.Jpanel;

import com.tool.tcpimg.UIUtils;
import java.awt.Color;
import org.come.until.CutButtonImage;
import java.awt.Graphics;
import org.come.entity.Goodstable;
import java.util.List;
import org.come.until.UserMessUntil;
import org.come.bean.XXGDBean;
import com.updateNew.MyIsif;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.Frame.ZhuFrame;
import org.come.until.GoodsListFromServerUntil;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import com.tool.tcpimg.RichLabel;
import org.come.bean.ChongjipackBean;
import com.tool.btn.VipShopBtn;
import org.come.mouslisten.VipGoodsMouselistener;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EverydayRechargeGoodsJpanel extends JPanel
{
    private JLabel goodsImg;
    private JLabel goodsNum;
    private VipGoodsMouselistener mouselistener;
    private VipShopBtn getBtn;
    private ChongjipackBean chongjipackBean;
    private RichLabel nameRich;
    private int maxNum;
    private ImageIcon icon;
    
    public EverydayRechargeGoodsJpanel() {
        this.setPreferredSize(new Dimension(562, 60));
        this.setOpaque(false);
        this.setLayout(null);
        this.getGoodsNum();
        this.getGetBtn();
        this.getGoodsImg();
        this.getNameRich();
    }
    
    public void exchangeGoods() {
        if (this.chongjipackBean == null) {
            return;
        }
        if (GoodsListFromServerUntil.Surplussum("-1", "-1", this.maxNum) < this.maxNum) {
            ZhuFrame.getZhuJpanel().addPrompt2("你的背包不够");
            return;
        }
        String sendmes = Agreement.getAgreement().PaydaygradesureAgreement("v" + this.chongjipackBean.getPackgradetype());
        SendMessageUntil.toServer(sendmes);
    }
    
    public void showGoods(ChongjipackBean chongjipackBean) {
        if (MyIsif.getStyle().equals("水墨")) {
            this.chongjipackBean = chongjipackBean;
            this.maxNum = 0;
            if (chongjipackBean != null) {
                this.nameRich.setText("#K每日充值满#R" + chongjipackBean.getCanpaymoney() + "#K元");
                List<XXGDBean> goods = ChongjipackBean.getGoods(chongjipackBean.getPackgoods());
                Goodstable goodstable = UserMessUntil.getgoodstable(((XXGDBean)goods.get(0)).getId());
                for (int i = 0; i < goods.size(); ++i) {
                    this.maxNum += ((XXGDBean)goods.get(i)).getSum();
                }
                this.goodsNum.setText(((XXGDBean)goods.get(0)).getSum() + "");
                if (goodstable != null) {
                    this.goodsImg.setIcon(GoodsListFromServerUntil.imgpath2(goodstable.getSkin()));
                }
                this.mouselistener.setGoodstable(goodstable);
                this.goodsNum.setVisible(true);
                this.getBtn.setBtn(1);
            }
            else {
                this.nameRich.setText("");
                this.goodsImg.setIcon(null);
                this.goodsNum.setVisible(false);
                this.mouselistener.setGoodstable(null);
                this.getBtn.setBtn(0);
            }
        }
        else {
            this.chongjipackBean = chongjipackBean;
            this.maxNum = 0;
            if (chongjipackBean != null) {
                this.nameRich.setText("#W每日充值满#R" + chongjipackBean.getCanpaymoney() + "#W元");
                List<XXGDBean> goods = ChongjipackBean.getGoods(chongjipackBean.getPackgoods());
                Goodstable goodstable = UserMessUntil.getgoodstable(((XXGDBean)goods.get(0)).getId());
                for (int i = 0; i < goods.size(); ++i) {
                    this.maxNum += ((XXGDBean)goods.get(i)).getSum();
                }
                this.goodsNum.setText(((XXGDBean)goods.get(0)).getSum() + "");
                if (goodstable != null) {
                    this.goodsImg.setIcon(GoodsListFromServerUntil.imgpath2(goodstable.getSkin()));
                }
                this.mouselistener.setGoodstable(goodstable);
                this.goodsNum.setVisible(true);
                this.getBtn.setBtn(1);
            }
            else {
                this.nameRich.setText("");
                this.goodsImg.setIcon(null);
                this.goodsNum.setVisible(false);
                this.mouselistener.setGoodstable(null);
                this.getBtn.setBtn(0);
            }
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        if (MyIsif.getStyle().equals("水墨")) {
            super.paintComponent(g);
            if (this.icon == null) {
                this.icon = CutButtonImage.getImage("inkImg/background/S178-1.png", 562, 60);
            }
            g.drawImage(this.icon.getImage(), 0, 0, 562, 60, null);
        }
        else {
            super.paintComponent(g);
            if (this.icon == null) {
                this.icon = CutButtonImage.getImage("inkImg/background/S178.png", 562, 60);
            }
            g.drawImage(this.icon.getImage(), 0, 0, 562, 60, null);
        }
    }
    
    public JLabel getGoodsImg() {
        if (this.goodsImg == null) {
            (this.goodsImg = new JLabel()).setOpaque(false);
            this.goodsImg.setBounds(10, 10, 43, 43);
            this.mouselistener = new VipGoodsMouselistener();
            this.goodsImg.addMouseListener(this.mouselistener);
            this.add(this.goodsImg);
        }
        return this.goodsImg;
    }
    
    public void setGoodsImg(JLabel goodsImg) {
        this.goodsImg = goodsImg;
    }
    
    public JLabel getGoodsNum() {
        if (this.goodsNum == null) {
            (this.goodsNum = new JLabel()).setBounds(38, 6, 16, 16);
            this.goodsNum.setIcon(CutButtonImage.getImage("inkImg/background/S24.png", -1, -1));
            this.goodsNum.setVerticalTextPosition(0);
            this.goodsNum.setHorizontalTextPosition(0);
            this.goodsNum.setForeground(Color.GREEN);
            this.goodsNum.setFont(UIUtils.TEXT_FONT10);
            this.add(this.goodsNum);
        }
        return this.goodsNum;
    }
    
    public void setGoodsNum(JLabel goodsNum) {
        this.goodsNum = goodsNum;
    }
    
    public VipShopBtn getGetBtn() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.getBtn == null) {
                (this.getBtn = new VipShopBtn("inkImg/button/B12.png", 1, 3, this)).setBounds(365, 17, 60, 26);
                this.add(this.getBtn);
            }
            return this.getBtn;
        }
        else {
            if (this.getBtn == null) {
                (this.getBtn = new VipShopBtn("inkImg/hongmu/lingqu.png", 1, 3, this)).setBounds(365, 17, 60, 26);
                this.add(this.getBtn);
            }
            return this.getBtn;
        }
    }
    
    public void setGetBtn(VipShopBtn getBtn) {
        this.getBtn = getBtn;
    }
    
    public ChongjipackBean getChongjipackBean() {
        return this.chongjipackBean;
    }
    
    public void setChongjipackBean(ChongjipackBean chongjipackBean) {
        this.chongjipackBean = chongjipackBean;
    }
    
    public RichLabel getNameRich() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.nameRich == null) {
                this.nameRich = new RichLabel("#K每日充值满#R1000#K元", UIUtils.TEXT_HY19);
                Dimension d = this.nameRich.computeSize(136);
                this.nameRich.setSize(d);
                this.nameRich.setPreferredSize(d);
                this.nameRich.setBounds(65, 15, 200, 30);
                this.add(this.nameRich);
            }
            return this.nameRich;
        }
        else {
            if (this.nameRich == null) {
                this.nameRich = new RichLabel("#W每日充值满#R1000#W元", UIUtils.TEXT_HY19);
                Dimension d = this.nameRich.computeSize(136);
                this.nameRich.setSize(d);
                this.nameRich.setPreferredSize(d);
                this.nameRich.setBounds(65, 15, 200, 30);
                this.add(this.nameRich);
            }
            return this.nameRich;
        }
    }
    
    public void setNameRich(RichLabel nameRich) {
        this.nameRich = nameRich;
    }
    
    public VipGoodsMouselistener getMouselistener() {
        return this.mouselistener;
    }
    
    public void setMouselistener(VipGoodsMouselistener mouselistener) {
        this.mouselistener = mouselistener;
    }
    
    public int getMaxNum() {
        return this.maxNum;
    }
    
    public void setMaxNum(int maxNum) {
        this.maxNum = maxNum;
    }
}
