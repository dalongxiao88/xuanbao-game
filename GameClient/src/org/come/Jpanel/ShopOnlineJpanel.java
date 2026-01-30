package org.come.Jpanel;

import org.come.model.Eshop;
import java.util.List;
import org.come.until.CutButtonImage;
import java.awt.Graphics;
import org.come.until.DeviceEshopUntil;
import com.tool.tcpimg.UIUtils;
import java.awt.Color;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import com.tool.btn.TaoBaoBtn;
import javax.swing.JPanel;
   //多宝阁商城分组显示
public class ShopOnlineJpanel extends JPanel
{
    private TaoBaoBtn treasureMenuBtn;
    private TaoBaoBtn skillMenuBtn;
    private TaoBaoBtn mythicalMenuBtn;
    private TaoBaoBtn deviceMenuBtn;
    private TaoBaoBtn baldricMenuBtn;
    private TaoBaoBtn integralMenuBtn;
    private TaoBaoBtn skillBookMenuBtn;
    private TaoBaoBtn questMenuBtn;
    private TaoBaoBtn trepanningMenuBtn;
    private TaoBaoBtn TransfergoldMenuBtn;
    private GoodsMinJpanel[] goodsMinJpanels;
    private TaoBaoBtn btnhp;//首页
    private TaoBaoBtn btnep;//末页
    private TaoBaoBtn btnsyy;//上一页
    private TaoBaoBtn btnxyy;//下一页
    private JLabel labpage;// 放置当前页数和总页数
    private int nowPage;// 当前页数
    private int maxPage;// 最大页码
    private int shopType;
    private ImageIcon icon;
    
    public ShopOnlineJpanel() {
        this.goodsMinJpanels = new GoodsMinJpanel[10];
        this.nowPage = 1;// 当前页数
        this.maxPage = 1;// 最大页码
        this.shopType = 6;
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(656, 445));
            this.setOpaque(false);
            this.setLayout(null);
            (this.labpage = new JLabel("1/1", 0)).setForeground(Color.white);
            this.labpage.setBounds(335, 378, 58, 17);
            this.labpage.setFont(UIUtils.TEXT_FONT1);
            this.add(this.labpage);
            (this.btnhp = new TaoBaoBtn("inkImg/button1/B30.png", 1, "首页", 20, this)).setBounds(279, 378, 34, 17);
            this.add(this.btnhp);
            (this.btnsyy = new TaoBaoBtn("inkImg/button/10.png", 1, "", 21, this)).setBounds(315, 378, 19, 20);
            this.add(this.btnsyy);
            (this.btnxyy = new TaoBaoBtn("inkImg/button/9.png", 1, "", 22, this)).setBounds(397, 378, 19, 20);
            this.add(this.btnxyy);
            (this.btnep = new TaoBaoBtn("inkImg/button1/B30.png", 1, "末页", 23, this)).setBounds(416, 378, 34, 17);
            this.add(this.btnep);
            this.getTreasureMenuBtn();
            this.getSkillMenuBtn();
            this.getMythicalMenuBtn();
            this.getDeviceMenuBtn();
            this.getBaldricMenuBtn();
            this.getIntegralMenuBtn();
            this.getSkillBookMenuBtn();
            this.getQuestMenuBtn();
            this.getTrepanningMenuBtn();
            this.getTransfergoldMenuBtn();
            this.getShopGoodsView(this, DeviceEshopUntil.getShopingMenuTreasures());
        }
        else {
            this.setPreferredSize(new Dimension(732, 480));
            this.setOpaque(false);
            this.setLayout(null);
            (this.labpage = new JLabel("1/1", 0)).setForeground(Color.white);
            this.labpage.setBounds(570, 378, 58, 17);
            this.labpage.setFont(UIUtils.TEXT_FONT1);
            this.add(this.labpage);
            (this.btnhp = new TaoBaoBtn("inkImg/hongmu/21_png.button.tab_nex.png", 1, "首页", 20, this)).setBounds(516, 378, 34, 17);
            this.add(this.btnhp);
            (this.btnsyy = new TaoBaoBtn("img/xy2uiimg/29_png.button.btn_8.png", 1, "", 21, this)).setBounds(550, 377, 19, 20);
            this.add(this.btnsyy);
            (this.btnxyy = new TaoBaoBtn("img/xy2uiimg//36_png.button.btn_7.png", 1, "", 22, this)).setBounds(630, 377, 19, 20);
            this.add(this.btnxyy);
            (this.btnep = new TaoBaoBtn("inkImg/hongmu/21_png.button.tab_nex.png", 1, "末页", 23, this)).setBounds(650, 378, 34, 17);
            this.add(this.btnep);
            this.getTreasureMenuBtn();
            this.getTreasureMenuBtn();
            this.getSkillMenuBtn();
            this.getMythicalMenuBtn();
            this.getDeviceMenuBtn();
            this.getBaldricMenuBtn();
            this.getIntegralMenuBtn();
            this.getSkillBookMenuBtn();
            this.getQuestMenuBtn();
            this.getTrepanningMenuBtn();
            this.getTransfergoldMenuBtn();
            this.getShopGoodsView(this, DeviceEshopUntil.getShopingMenuTreasures());
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.icon == null) {
                this.icon = CutButtonImage.getImage("inkImg/background/S7.png", -1, -1);
            }
            g.drawImage(this.icon.getImage(), 332, 378, 65, 19, this);
        }
        else {
            if (this.icon == null) {
                this.icon = CutButtonImage.getImage("img/xy2uiimg/border_quac1k.png", -1, -1);
            }
            g.drawImage(this.icon.getImage(), 332, 378, 65, 19, this);
        }
    }
    
    public void getShopGoodsView(ShopOnlineJpanel shopOnlineJpanel, List<Eshop> eshopList) {
        if (MyIsif.getStyle().equals("水墨")) {
            this.maxPage = ((eshopList.size() % 10 == 0) ? (eshopList.size() / 10) : (eshopList.size() / 10 + 1));
            this.labpage.setText(this.nowPage + "/" + this.maxPage);
            int i = 10 * (this.nowPage - 1);
            while (i < 10 * this.nowPage) {
                int row = i / 5;
                int rank = i % 5;
                if (i >= eshopList.size()) {
                    break;
                }
                else {
                    (this.goodsMinJpanels[i] = new GoodsMinJpanel((Eshop)eshopList.get(i))).setBounds(54 + rank * 120, 40 + row * 165, 114, 155);
                    shopOnlineJpanel.add(this.goodsMinJpanels[i]);
                    ++i;
                }
            }
        }
        else {
            this.maxPage = ((eshopList.size() % 10 == 0) ? (eshopList.size() / 10) : (eshopList.size() / 10 + 1));
            this.labpage.setText(this.nowPage + "/" + this.maxPage);
            int i = 10 * (this.nowPage - 1);
            while (i < 10 * this.nowPage) {
                int row = i / 5;
                int rank = i % 5;
                if (i >= eshopList.size()) {
                    break;
                }
                else {
                    (this.goodsMinJpanels[i] = new GoodsMinJpanel((Eshop)eshopList.get(i))).setBounds(50 + rank * 120, 40 + row * 165, 118, 155);
                    shopOnlineJpanel.add(this.goodsMinJpanels[i]);
                    ++i;
                }
            }
        }
    }
    
    public void isShopGoods(int shopType) {
        switch (this.shopType = shopType) {
            //"珍品"-6, "技能"-7, "神兽"-8, "仙器"-9, "配饰"-10, "积分"-11  "技能书"-12  "珍品" -13 ""-14
            case 6: {
                this.changeShopGoodsView(DeviceEshopUntil.getShopingMenuTreasures());
                break;
            }
            case 7: {
                this.changeShopGoodsView(DeviceEshopUntil.getShopingMenuSkillJpanel());
                break;
            }
            case 8: {
                this.changeShopGoodsView(DeviceEshopUntil.getShopingMenuGodBeastJpanel());
                break;
            }
            case 9: {
                this.changeShopGoodsView(DeviceEshopUntil.getShopingMenuFairyDeviceJpanel());
                break;
            }
            case 10: {
                this.changeShopGoodsView(DeviceEshopUntil.getShopingMenuAccessoriesJpanel());
                break;
            }
            case 11: {
                this.changeShopGoodsView(DeviceEshopUntil.getShopingMenuPreferentialTreatmentJpanel());
                break;
            }
            case 12: {
                this.changeShopGoodsView(DeviceEshopUntil.getShopingMenuSkillBookJpanel());
                break;
            }
            case 13: {
                this.changeShopGoodsView(DeviceEshopUntil.getShopingMenuQuestJpanel());
                break;
            }
            case 14: {
                this.changeShopGoodsView(DeviceEshopUntil.getShopingMenutrepanningJpanel());
                break;
            }
            case 15: {
                this.changeShopGoodsView(DeviceEshopUntil.getShopingMenuTransfergoldJpanel());
                break;
            }
        }
    }
    /** 更换展示的商品 */
    
    public void changeShopGoodsView(List<Eshop> eshopList) {
        for (int i = 0; i < this.goodsMinJpanels.length; ++i) {
            this.goodsMinJpanels[i].clearView();
        }
        this.maxPage = ((eshopList.size() % 10 == 0) ? (eshopList.size() / 10) : (eshopList.size() / 10 + 1));
        this.labpage.setText(this.nowPage + "/" + this.maxPage);
        int num = 0;
        for (int j = 10 * (this.nowPage - 1); j < 10 * this.nowPage && j < eshopList.size(); ++j) {
            this.goodsMinJpanels[num].addEshopMessage((Eshop)eshopList.get(j));
            ++num;
        }
    }
    
    public int getShopType() {
        return this.shopType;
    }
    
    public void setShopType(int shopType) {
        this.shopType = shopType;
    }
    
    public GoodsMinJpanel[] getGoodsMinJpanels() {
        return this.goodsMinJpanels;
    }
    
    public void setGoodsMinJpanels(GoodsMinJpanel[] goodsMinJpanels) {
        this.goodsMinJpanels = goodsMinJpanels;
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
    //商城第一个热销
    public TaoBaoBtn getTreasureMenuBtn() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.treasureMenuBtn == null) {
                (this.treasureMenuBtn = new TaoBaoBtn("inkImg/button/B281.png", 1, "", 10, this)).setBounds(54, 14, 63, 23);
                this.add(this.treasureMenuBtn);//商城热销
            }
        }
        else if (this.treasureMenuBtn == null) {
            (this.treasureMenuBtn = new TaoBaoBtn("img/xy2uiimg/B281.png", 1, "", 10, this)).setBounds(54, 14, 75, 26);
            this.add(this.treasureMenuBtn);//红木商城热销
        }
        return this.treasureMenuBtn;
    }
    
    public void setTreasureMenuBtn(TaoBaoBtn treasureMenuBtn) {
        this.treasureMenuBtn = treasureMenuBtn;
    }
    //商城展示材料
    public TaoBaoBtn getSkillMenuBtn() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.skillMenuBtn == null) {
                (this.skillMenuBtn = new TaoBaoBtn("inkImg/button/B282.png", 1, "", 11, this)).setBounds(184, 14, 63, 23);//(119, 14, 63, 23)原位置
            }
            this.add(skillMenuBtn);//商城显示材料
        }
        else if (this.skillMenuBtn == null) {
            (this.skillMenuBtn = new TaoBaoBtn("img/xy2uiimg/B296.png", 1, "", 11, this)).setBounds(184, 14, 75, 26);
            this.add(skillMenuBtn);//红木显示材料
        }
        return this.skillMenuBtn;
    }
    
    public void setSkillMenuBtn(TaoBaoBtn skillMenuBtn) {
        this.skillMenuBtn = skillMenuBtn;
    }
    
    public TaoBaoBtn getMythicalMenuBtn() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.mythicalMenuBtn == null) {
                (this.mythicalMenuBtn = new TaoBaoBtn("inkImg/button/B252.png", 1, "", 12, this)).setBounds(379, 14, 63, 23);
            }
            this.add(mythicalMenuBtn);//商城显示套装
        }
        else if (this.mythicalMenuBtn == null) {
            (this.mythicalMenuBtn = new TaoBaoBtn("img/xy2uiimg/B282.png", 1, "", 12, this)).setBounds(379, 14, 75, 26);
            this.add(mythicalMenuBtn);//红木商城显示套装
        }
        return this.mythicalMenuBtn;
    }
    
    public void setMythicalMenuBtn(TaoBaoBtn mythicalMenuBtn) {
        this.mythicalMenuBtn = mythicalMenuBtn;
    }
    
    public TaoBaoBtn getDeviceMenuBtn() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.deviceMenuBtn == null) {
                (this.deviceMenuBtn = new TaoBaoBtn("inkImg/button/B288.png", 1, "", 13, this)).setBounds(249, 14, 63, 23);
            }
            this.add(deviceMenuBtn);//商城显示召唤兽
        }
        else if (this.deviceMenuBtn == null) {
            (this.deviceMenuBtn = new TaoBaoBtn("img/xy2uiimg/B294.png", 1, "", 13, this)).setBounds(249, 14, 75, 26);
           this.add(deviceMenuBtn);//红木商城显示召唤兽
        }
        return this.deviceMenuBtn;
    }
    
    public void setDeviceMenuBtn(TaoBaoBtn deviceMenuBtn) {
        this.deviceMenuBtn = deviceMenuBtn;
    }
    
    public TaoBaoBtn getBaldricMenuBtn() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.baldricMenuBtn == null) {
                (this.baldricMenuBtn = new TaoBaoBtn("inkImg/button/B250.png", 1, "", 14, this)).setBounds(314, 14, 63, 23);
            }
            this.add(baldricMenuBtn);//商城配饰
        }
        else if (this.baldricMenuBtn == null) {
            (this.baldricMenuBtn = new TaoBaoBtn("img/xy2uiimg/B290.png", 1, "", 14, this)).setBounds(314, 14, 75, 26);
            this.add(baldricMenuBtn);//红木配饰
        }
        return this.baldricMenuBtn;
    }
    
    public void setBaldricMenuBtn(TaoBaoBtn baldricMenuBtn) {
        this.baldricMenuBtn = baldricMenuBtn;
    }
    
    public TaoBaoBtn getIntegralMenuBtn() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.integralMenuBtn == null) {
                (this.integralMenuBtn = new TaoBaoBtn("inkImg/button/B246.png", 1, "", 15, this)).setBounds(444, 14, 63, 23);
                this.add(this.integralMenuBtn);//商城积分
            }
        }
        else if (this.integralMenuBtn == null) {
            (this.integralMenuBtn = new TaoBaoBtn("img/xy2uiimg/B292.png", 1, "", 15, this)).setBounds(444, 14, 75, 26);
            this.add(this.integralMenuBtn);//红木商城积分
        }
        return this.integralMenuBtn;
    }
    
    public void setIntegralMenuBtn(TaoBaoBtn integralMenuBtn) {
        this.integralMenuBtn = integralMenuBtn;
    }
    
    public TaoBaoBtn getSkillBookMenuBtn() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.skillBookMenuBtn == null) {
                (this.skillBookMenuBtn = new TaoBaoBtn("inkImg/button/B248.png", 1, "", 16, this)).setBounds(119, 14, 63, 23);
            }
            this.add(skillBookMenuBtn);//商城技能
        }
        else if (this.skillBookMenuBtn == null) {
            (this.skillBookMenuBtn = new TaoBaoBtn("img/xy2uiimg/V285.png", 1, "", 16, this)).setBounds(119, 14, 75, 26);
            this.add(skillBookMenuBtn);//红木商城技能
        }
        return this.skillBookMenuBtn;
    }
    
    public void setSkillBookMenuBtn(TaoBaoBtn skillMenuBtn) {
        this.skillBookMenuBtn = skillMenuBtn;
    }
    
    public TaoBaoBtn getQuestMenuBtn() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.questMenuBtn == null) {
                (this.questMenuBtn = new TaoBaoBtn("inkImg/button/B254.png", 1, "", 17, this)).setBounds(509, 14, 63, 23);
            }
 //           this.add(questMenuBtn);//商城仙器
        }
        else if (this.questMenuBtn == null) {
            (this.questMenuBtn = new TaoBaoBtn("img/xy2uiimg/B287.png", 1, "", 17, this)).setBounds(509, 14, 75, 26);
 //           this.add(questMenuBtn);//商城仙器红木
        }
        return this.questMenuBtn;
    }
    
    public void setQuestMenuBtn(TaoBaoBtn questMenuBtn) {
        this.questMenuBtn = questMenuBtn;
    }
    
    public TaoBaoBtn getTrepanningMenuBtn() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.trepanningMenuBtn == null) {
                (this.trepanningMenuBtn = new TaoBaoBtn("inkImg/button/B256.png", 1, "", 18, this)).setBounds(444, 14, 63, 23);
            }
//            this.add(trepanningMenuBtn);//商城珍品
        }
        else if (this.trepanningMenuBtn == null) {
            (this.trepanningMenuBtn = new TaoBaoBtn("img/xy2uiimg/B288.png", 1, "", 18, this)).setBounds(444, 14, 75, 26);
 //           this.add(trepanningMenuBtn);//红木商城珍品
        }
        return this.trepanningMenuBtn;
    }
    
    public void setTrepanningMenuBtn(TaoBaoBtn trepanningMenuBtn) {
        this.trepanningMenuBtn = trepanningMenuBtn;
    }
    
    public TaoBaoBtn getTransfergoldMenuBtn() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.TransfergoldMenuBtn == null) {
                (this.TransfergoldMenuBtn = new TaoBaoBtn("inkImg/button/zqb1.png", 1, "", 19, this)).setBounds(509, 14, 63, 23);
            }
            this.add(TransfergoldMenuBtn);//商城转区币
        }
        else if (this.TransfergoldMenuBtn == null) {
            (this.TransfergoldMenuBtn = new TaoBaoBtn("inkImg/hongmu1/BZ2.png", 1, "", 19, this)).setBounds(509, 14, 75, 26);
            this.add(TransfergoldMenuBtn);//红木商城转区币
        }
        return this.TransfergoldMenuBtn;
    }
    
    public void setTransfergoldMenuBtn(TaoBaoBtn transfergoldMenuBtn) {
        this.TransfergoldMenuBtn = transfergoldMenuBtn;
    }
}
