package org.come.Jpanel;

import com.tool.tcpimg.UIUtils;
import java.awt.Color;
import org.come.until.CutButtonImage;
import java.awt.Graphics;
import org.come.entity.Goodstable;
import java.util.List;
import org.come.until.EquipTool;
import org.come.until.UserMessUntil;
import org.come.bean.XXGDBean;
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

public class ContinuousRechargeGoodsJpanel extends JPanel
{
    private JLabel[] GoodsList;
    private JLabel[] goodsNums;
    private VipGoodsMouselistener[] mouselistener;
    private VipShopBtn getBtn;
    private ChongjipackBean chongjipackBean;
    private RichLabel richLabel;
    private int numMax;
    private ImageIcon iconBack;
    
    public ContinuousRechargeGoodsJpanel() {
        this.setPreferredSize(new Dimension(562, 60));
        this.setOpaque(false);
        this.setLayout(null);
        this.getGoodsNums();
        this.getGoodsList();
        this.getGetBtn();
        this.getRichLabel();
    }
    
    public void exchangeGoods() {
        if (this.chongjipackBean == null) {
            return;
        }
        if (GoodsListFromServerUntil.Surplussum("-1", "-1", this.numMax) < this.numMax) {
            ZhuFrame.getZhuJpanel().addPrompt2("你的背包不够");
            return;
        }
        String sendmes = Agreement.getAgreement().DayforweekgradesureAgreement("v" + this.chongjipackBean.getPackgradetype());
        SendMessageUntil.toServer(sendmes);
    }
    
    public void showGoods(ChongjipackBean chongjipackBean) {
        this.chongjipackBean = chongjipackBean;
        if (chongjipackBean != null) {
            this.richLabel.setText("#W连续充值#R" + chongjipackBean.getPackgradetype() + "#W天");
            List<XXGDBean> goods = ChongjipackBean.getGoods(chongjipackBean.getPackgoods());
            this.numMax = 0;
            this.changeView();
            for (int i = 0; i < goods.size(); ++i) {
                Goodstable goodstable = UserMessUntil.getgoodstable(((XXGDBean)goods.get(i)).getId());
                this.goodsNums[i].setVisible(true);
                this.goodsNums[i].setText(((XXGDBean)goods.get(i)).getSum() + "");
                this.GoodsList[i].setIcon(GoodsListFromServerUntil.imgpath2(goodstable.getSkin()));
                this.getMouselistener()[i].setGoodstable(goodstable);
                if (EquipTool.isEquip(goodstable.getType())) {
                    this.numMax += ((XXGDBean)goods.get(i)).getSum();
                }
                else {
                    ++this.numMax;
                }
            }
        }
    }
    
    public void changeView() {
        for (int i = 0; i < 5; ++i) {
            this.goodsNums[i].setText("");
            this.goodsNums[i].setVisible(false);
            this.GoodsList[i].setIcon(null);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.iconBack == null) {
            this.iconBack = CutButtonImage.getImage("inkImg/background/S11.png", -1, -1);
        }
        g.drawImage(this.iconBack.getImage(), 0, 0, 562, 60, this);
    }
    
    public JLabel[] getGoodsList() {
        if (this.GoodsList == null) {
            this.GoodsList = new JLabel[5];
            for (int i = 0; i < this.GoodsList.length; ++i) {
                (this.GoodsList[i] = new JLabel()).setOpaque(false);
                this.GoodsList[i].setBounds(10 + i * 51, 8, 43, 43);
                this.GoodsList[i].addMouseListener(this.getMouselistener()[i]);
                this.add(this.GoodsList[i]);
            }
        }
        return this.GoodsList;
    }
    
    public void setGoodsList(JLabel[] goodsList) {
        this.GoodsList = goodsList;
    }
    
    public JLabel[] getGoodsNums() {
        if (this.goodsNums == null) {
            this.goodsNums = new JLabel[5];
            for (int i = 0; i < this.goodsNums.length; ++i) {
                (this.goodsNums[i] = new JLabel()).setBounds(38 + i * 51, 6, 16, 16);
                this.goodsNums[i].setIcon(CutButtonImage.getImage("inkImg/background/S24.png", -1, -1));
                this.goodsNums[i].setVerticalTextPosition(0);
                this.goodsNums[i].setHorizontalTextPosition(0);
                this.goodsNums[i].setForeground(Color.GREEN);
                this.goodsNums[i].setFont(UIUtils.TEXT_FONT10);
                this.add(this.goodsNums[i]);
            }
        }
        return this.goodsNums;
    }
    
    public void setGoodsNums(JLabel[] goodsNums) {
        this.goodsNums = goodsNums;
    }
    
    public ImageIcon getIconBack() {
        return this.iconBack;
    }
    
    public void setIconBack(ImageIcon iconBack) {
        this.iconBack = iconBack;
    }
    
    public VipShopBtn getGetBtn() {
        if (this.getBtn == null) {
            (this.getBtn = new VipShopBtn("inkImg/hongmu/lingqu.png", 1, 2, this)).setBounds(410, 17, 65, 26);
            this.add(this.getBtn);
        }
        return this.getBtn;
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
    
    public RichLabel getRichLabel() {
        if (this.richLabel == null) {
            this.richLabel = new RichLabel("#W连续充值#R21#W天", UIUtils.TEXT_FONT1);
            Dimension d = this.richLabel.computeSize(136);
            this.richLabel.setSize(d);
            this.richLabel.setPreferredSize(d);
            this.richLabel.setBounds(270, 15, 120, 40);
            this.add(this.richLabel);
        }
        return this.richLabel;
    }
    
    public void setRichLabel(RichLabel richLabel) {
        this.richLabel = richLabel;
    }
    
    public VipGoodsMouselistener[] getMouselistener() {
        if (this.mouselistener == null) {
            this.mouselistener = new VipGoodsMouselistener[5];
            for (int i = 0; i < this.mouselistener.length; ++i) {
                this.mouselistener[i] = new VipGoodsMouselistener();
            }
        }
        return this.mouselistener;
    }
    
    public void setMouselistener(VipGoodsMouselistener[] mouselistener) {
        this.mouselistener = mouselistener;
    }
    
    public int getNumMax() {
        return this.numMax;
    }
    
    public void setNumMax(int numMax) {
        this.numMax = numMax;
    }
}
