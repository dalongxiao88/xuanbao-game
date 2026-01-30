package com.tool.btn;

import javax.swing.table.DefaultTableModel;
import java.awt.Dimension;
import org.come.Jpanel.TaobaoCourtCardJpanel;
import java.util.Vector;
import com.tool.tcpimg.RichLabel;
import org.come.until.CutButtonImage;
import com.updateNew.MyIsif;
import java.awt.event.MouseEvent;
import java.awt.Color;
import com.tool.tcpimg.UIUtils;
import org.come.Jpanel.ShopOnlineJpanel;
import org.come.Jpanel.TaobaoCourtMainJpanel;
import org.come.Jpanel.LimitedTimeShopJpanel;
import org.come.Jpanel.SeventyTwoChangesJpanel;
import org.come.Jpanel.DailyLoginJPanel;
import org.come.Jpanel.QuotaJpanel;

public class TaoBaoBtn extends MoBanBtn
{
    private int caozuo;
    private QuotaJpanel quotaJpanel;
    private DailyLoginJPanel dailyLoginJPanel;
    private SeventyTwoChangesJpanel seventyTwoChangesJpanel;
    private LimitedTimeShopJpanel limitedTimeShopJpanel;
    private TaobaoCourtMainJpanel taobaoCourtMainJpanel;
    private ShopOnlineJpanel shopOnlineJpanel;
    //商城新增加
    public TaoBaoBtn(String iconpath, int type, String text, int caozuo) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.setText(text);
        if (caozuo > 9 && caozuo < 20) {
            this.setFont(UIUtils.TEXT_HY16);
            this.setForeground(Color.white);
        }
        else if (caozuo > 19 && caozuo < 24) {
            this.setFont(UIUtils.TEXT_FONT);
            this.setForeground(Color.white);
        }
        else if (caozuo > 23 && caozuo < 30) {
            this.setFont(UIUtils.TEXT_HY12);
            this.setForeground(Color.white);
        }
        else {
            this.setFont(UIUtils.TEXT_HY19);
            this.setForeground(UIUtils.COLOR_BTNXUANXIANGKA[0]);
        }
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    public TaoBaoBtn(String iconpath, int type, String text, int caozuo, ShopOnlineJpanel shopOnlineJpanel) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.shopOnlineJpanel = shopOnlineJpanel;
        this.setText(text);
        if (caozuo > 19 && caozuo < 24) {
            this.setFont(UIUtils.TEXT_FONT);
            this.setForeground(Color.white);
        }
        else if (caozuo > 23 && caozuo < 30) {
            this.setFont(UIUtils.TEXT_HY12);
            this.setForeground(Color.white);
        }
        else {
            this.setFont(UIUtils.TEXT_HY19);
            this.setForeground(UIUtils.COLOR_BTNXUANXIANGKA[0]);
        }
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    public TaoBaoBtn(String iconpath, int type, String text, int caozuo, TaobaoCourtMainJpanel taobaoCourtMainJpanel) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.taobaoCourtMainJpanel = taobaoCourtMainJpanel;
        this.setText(text);
        if (caozuo > 9 && caozuo < 20) {
            this.setFont(UIUtils.TEXT_HY16);
            this.setForeground(Color.white);
        }
        else if (caozuo > 19 && caozuo < 30) {
            this.setFont(UIUtils.TEXT_HY12);
            this.setForeground(Color.white);
        }
        else {
            this.setFont(UIUtils.TEXT_HY19);
            this.setForeground(UIUtils.COLOR_BTNXUANXIANGKA[0]);
        }
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    public TaoBaoBtn(String iconpath, int type, String text, int caozuo, QuotaJpanel quotaJpanel) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.setText(text);
        this.quotaJpanel = quotaJpanel;
        if (caozuo > 9) {
            this.setFont(UIUtils.TEXT_HY16);
            this.setForeground(Color.white);
        }
        else {
            this.setFont(UIUtils.TEXT_HY19);
            this.setForeground(UIUtils.COLOR_BTNXUANXIANGKA[0]);
        }
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    public TaoBaoBtn(String iconpath, int type, String text, int caozuo, DailyLoginJPanel dailyLoginJPanel) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.setText(text);
        this.dailyLoginJPanel = dailyLoginJPanel;
        if (caozuo > 59 && caozuo < 70) {
            this.setFont(UIUtils.TEXT_FONT1);
            this.setForeground(Color.black);
        }
        else if (caozuo > 69 && caozuo < 80) {
            this.setFont(UIUtils.TEXT_FONT2);
            this.setForeground(Color.white);
        }
        else {
            this.setFont(UIUtils.TEXT_HY19);
            this.setForeground(UIUtils.COLOR_BTNXUANXIANGKA[0]);
        }
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    public TaoBaoBtn(String iconpath, int type, String text, int caozuo, SeventyTwoChangesJpanel seventyTwoChangesJpanel) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.setText(text);
        this.seventyTwoChangesJpanel = seventyTwoChangesJpanel;
        if (caozuo > 79 && caozuo < 90) {
            this.setFont(UIUtils.TEXT_FONT);
            this.setForeground(Color.white);
        }
        else {
            this.setFont(UIUtils.TEXT_HY14);
        }
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    public TaoBaoBtn(String iconpath, int type, String text, int caozuo, LimitedTimeShopJpanel limitedTimeShopJpanel) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.setText(text);
        this.limitedTimeShopJpanel = limitedTimeShopJpanel;
        this.setFont(UIUtils.TEXT_FONT);
        this.setForeground(Color.white);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        if (MyIsif.getStyle().equals("水墨")) {
            try {
                switch (this.caozuo) {
                    case 2://商城
                    case 3://锦绣
                    case 6://月卡
                    case 7: {
                        int menuType = this.taobaoCourtMainJpanel.getTaobaoCourtCardJpanel().getMenuType();
                        TaobaoCourtCardJpanel taobaoCourtCardJpanel = this.taobaoCourtMainJpanel.getTaobaoCourtCardJpanel();
                        if (menuType != this.caozuo) {
                            if (menuType == 2) {
                                this.taobaoCourtMainJpanel.getShopMenuBtn().setIcons(CutButtonImage.cuts("inkImg/button/B262.png"));
                            }
                            else if (menuType == 3) {
                                this.taobaoCourtMainJpanel.getTaobaoMenuBtn().setIcons(CutButtonImage.cuts("inkImg/button/B260.png"));
                            }
                            else if (menuType == 6) {
                                this.taobaoCourtMainJpanel.getMonthCardBtn().setIcons(CutButtonImage.cuts("inkImg/button/B264.png"));
                            }
                            this.taobaoCourtMainJpanel.getTaobaoCourtCardJpanel().setMenuType(this.caozuo);
                            menuType = this.taobaoCourtMainJpanel.getTaobaoCourtCardJpanel().getMenuType();
                        }
                        if (menuType == 2) {
                            this.taobaoCourtMainJpanel.getShopMenuBtn().setIcons(CutButtonImage.cuts("inkImg/button/B263.png"));
                            taobaoCourtCardJpanel.getCardLayout().show(taobaoCourtCardJpanel, "shopOnline");
                            break;
                        }
                        else if (menuType == 3) {
                            this.taobaoCourtMainJpanel.getTaobaoMenuBtn().setIcons(CutButtonImage.cuts("inkImg/button/B261.png"));
                            taobaoCourtCardJpanel.getCardLayout().show(taobaoCourtCardJpanel, "taobaoCourt");
                            taobaoCourtCardJpanel.getTaobaoCourtSplendidJpanel().showTaobao();
                            break;
                        }
                        else if (menuType == 6) {
                            this.taobaoCourtMainJpanel.getMonthCardBtn().setIcons(CutButtonImage.cuts("inkImg/button/B265.png"));
                            taobaoCourtCardJpanel.getMonthlyCardJpanel().changeTime();
                            taobaoCourtCardJpanel.getCardLayout().show(taobaoCourtCardJpanel, "monthCard");
                            break;
                        }
                        else {
                            break;
                        }
                    }
                    case 10:
                    case 11:
                    case 12:
                    case 13:
                    case 14:
                    case 15:
                    case 16:
                    case 17:
                    case 18:
                    case 19: {
                        int shopType = this.shopOnlineJpanel.getShopType();
                        if (shopType != this.caozuo - 4) {
                            if (shopType == 6) {
                                this.shopOnlineJpanel.getTreasureMenuBtn().setIcons(CutButtonImage.cuts("inkImg/button/B280.png"));
                            }
                            else if (shopType == 7) {
                                this.shopOnlineJpanel.getSkillMenuBtn().setIcons(CutButtonImage.cuts("inkImg/button/B282.png"));
                            }
                            else if (shopType == 8) {
                                this.shopOnlineJpanel.getMythicalMenuBtn().setIcons(CutButtonImage.cuts("inkImg/button/B252.png"));
                            }
                            else if (shopType == 9) {
                                this.shopOnlineJpanel.getDeviceMenuBtn().setIcons(CutButtonImage.cuts("inkImg/button/B288.png"));
                            }
                            else if (shopType == 10) {
                                this.shopOnlineJpanel.getBaldricMenuBtn().setIcons(CutButtonImage.cuts("inkImg/button/B250.png"));
                            }
                            else if (shopType == 11) {
                                this.shopOnlineJpanel.getIntegralMenuBtn().setIcons(CutButtonImage.cuts("inkImg/button/B246.png"));
                            }
                            else if (shopType == 12) {
                                this.shopOnlineJpanel.getSkillBookMenuBtn().setIcons(CutButtonImage.cuts("inkImg/button/B248.png"));
                            }
                            else if (shopType == 13) {
                                this.shopOnlineJpanel.getQuestMenuBtn().setIcons(CutButtonImage.cuts("inkImg/button/B254.png"));
                            }
                            else if (shopType == 14) {
                                this.shopOnlineJpanel.getTrepanningMenuBtn().setIcons(CutButtonImage.cuts("inkImg/button/B256.png"));
                            }
                            else if (shopType == 15) {
                                this.shopOnlineJpanel.getTransfergoldMenuBtn().setIcons(CutButtonImage.cuts("inkImg/button/zqb1.png"));
                            }
                            shopType = this.caozuo - 4;
                            if (shopType == 6) {//热销
                                this.shopOnlineJpanel.getTreasureMenuBtn().setIcons(CutButtonImage.cuts("inkImg/button/B281.png"));
                            }
                            else if (shopType == 7) {//材料
                                this.shopOnlineJpanel.getSkillMenuBtn().setIcons(CutButtonImage.cuts("inkImg/button/B283.png"));
                            }
                            else if (shopType == 8) {//套装
                                this.shopOnlineJpanel.getMythicalMenuBtn().setIcons(CutButtonImage.cuts("inkImg/button/B253.png"));
                            }
                            else if (shopType == 9) {//召唤兽
                                this.shopOnlineJpanel.getDeviceMenuBtn().setIcons(CutButtonImage.cuts("inkImg/button/B289.png"));
                            }
                            else if (shopType == 10) {//配饰
                                this.shopOnlineJpanel.getBaldricMenuBtn().setIcons(CutButtonImage.cuts("inkImg/button/B251.png"));
                            }
                            else if (shopType == 11) {//积分
                                this.shopOnlineJpanel.getIntegralMenuBtn().setIcons(CutButtonImage.cuts("inkImg/button/B247.png"));
                            }
                            else if (shopType == 12) {//技能
                                this.shopOnlineJpanel.getSkillBookMenuBtn().setIcons(CutButtonImage.cuts("inkImg/button/B249.png"));
                            }
                            else if (shopType == 13) {//仙器
                                this.shopOnlineJpanel.getQuestMenuBtn().setIcons(CutButtonImage.cuts("inkImg/button/B255.png"));
                            }
                            else if (shopType == 14) {//珍品
                                this.shopOnlineJpanel.getTrepanningMenuBtn().setIcons(CutButtonImage.cuts("inkImg/button/B257.png"));
                            }
                            else if (shopType == 15) {//转区币
                                this.shopOnlineJpanel.getTransfergoldMenuBtn().setIcons(CutButtonImage.cuts("inkImg/button/zqb2.png"));
                            }
                        }
                        this.shopOnlineJpanel.setNowPage(1);
                        this.shopOnlineJpanel.isShopGoods(this.caozuo - 4);
                        break;
                    }
                    case 20: {
                        if (this.shopOnlineJpanel.getNowPage() <= 1) {
                            return;
                        }
                        this.shopOnlineJpanel.setNowPage(1);
                        this.shopOnlineJpanel.isShopGoods(this.shopOnlineJpanel.getShopType());
                        break;
                    }
                    case 21: {
                        if (this.shopOnlineJpanel.getNowPage() <= 1) {
                            return;
                        }
                        this.shopOnlineJpanel.setNowPage(this.shopOnlineJpanel.getNowPage() - 1);
                        this.shopOnlineJpanel.isShopGoods(this.shopOnlineJpanel.getShopType());
                        break;
                    }
                    case 22: {
                        if (this.shopOnlineJpanel.getNowPage() >= this.shopOnlineJpanel.getMaxPage()) {
                            return;
                        }
                        this.shopOnlineJpanel.setNowPage(this.shopOnlineJpanel.getNowPage() + 1);
                        this.shopOnlineJpanel.isShopGoods(this.shopOnlineJpanel.getShopType());
                        break;
                    }
                    case 23: {
                        if (this.shopOnlineJpanel.getNowPage() >= this.shopOnlineJpanel.getMaxPage()) {
                            return;
                        }
                        this.shopOnlineJpanel.setNowPage(this.shopOnlineJpanel.getMaxPage());
                        this.shopOnlineJpanel.isShopGoods(this.shopOnlineJpanel.getShopType());
                        break;
                    }
                    case 30: {
                        this.quotaJpanel.getExchangeMenuBtn().setIcons(CutButtonImage.cuts("img/xy2uiimg/二级选项卡_商城_限购_赠送好礼_选中_w100,h78.png"));
                        this.quotaJpanel.getLevelMenuBtn().setIcons(CutButtonImage.cuts("img/xy2uiimg/二级选项卡_商城_限购_冲级必备_未选中_w100,h78.png"));
                        this.quotaJpanel.getDiscountMenuBtn().setIcons(CutButtonImage.cuts("img/xy2uiimg/二级选项卡_商城_限购_显示折扣_未选中_w100,h78.png"));
                        break;
                    }
                    case 31: {
                        this.quotaJpanel.getExchangeMenuBtn().setIcons(CutButtonImage.cuts("img/xy2uiimg/二级选项卡_商城_限购_赠送好礼_未选中_w100,h78.png"));
                        this.quotaJpanel.getLevelMenuBtn().setIcons(CutButtonImage.cuts("img/xy2uiimg/二级选项卡_商城_限购_冲级必备_选中_w100,h78.png"));
                        this.quotaJpanel.getDiscountMenuBtn().setIcons(CutButtonImage.cuts("img/xy2uiimg/二级选项卡_商城_限购_显示折扣_未选中_w100,h78.png"));
                        break;
                    }
                    case 32: {
                        this.quotaJpanel.getExchangeMenuBtn().setIcons(CutButtonImage.cuts("img/xy2uiimg/二级选项卡_商城_限购_赠送好礼_未选中_w100,h78.png"));
                        this.quotaJpanel.getLevelMenuBtn().setIcons(CutButtonImage.cuts("img/xy2uiimg/二级选项卡_商城_限购_冲级必备_未选中_w100,h78.png"));
                        this.quotaJpanel.getDiscountMenuBtn().setIcons(CutButtonImage.cuts("img/xy2uiimg/二级选项卡_商城_限购_显示折扣_选中_w100,h78.png"));
                        break;
                    }
                    case 40: {
                        if (this.limitedTimeShopJpanel.getNowPage() <= 1) {
                            return;
                        }
                        this.limitedTimeShopJpanel.setNowPage(1);
                        this.limitedTimeShopJpanel.changeShopGoodsView();
                        break;
                    }
                    case 41: {
                        if (this.limitedTimeShopJpanel.getNowPage() <= 1) {
                            return;
                        }
                        this.limitedTimeShopJpanel.setNowPage(this.limitedTimeShopJpanel.getNowPage() - 1);
                        this.limitedTimeShopJpanel.changeShopGoodsView();
                        break;
                    }
                    case 42: {
                        if (this.limitedTimeShopJpanel.getNowPage() >= this.limitedTimeShopJpanel.getMaxPage()) {
                            return;
                        }
                        this.limitedTimeShopJpanel.setNowPage(this.limitedTimeShopJpanel.getNowPage() + 1);
                        this.limitedTimeShopJpanel.changeShopGoodsView();
                        break;
                    }
                    case 43: {
                        if (this.limitedTimeShopJpanel.getNowPage() >= this.shopOnlineJpanel.getMaxPage()) {
                            return;
                        }
                        this.limitedTimeShopJpanel.setNowPage(this.limitedTimeShopJpanel.getMaxPage());
                        this.limitedTimeShopJpanel.changeShopGoodsView();
                        break;
                    }
                    case 60: {
                        this.changeMenuBtnDailyLogin();
                        break;
                    }
                    case 61: {
                        this.changeMenuBtnDailyLogin();
                        break;
                    }
                    case 80: {
                        this.changeMenuBtnSeventyTwoChanges();
                        break;
                    }
                    case 81: {
                        this.changeMenuBtnSeventyTwoChanges();
                        break;
                    }
                    case 90: {
                        RichLabel view = (RichLabel)this.seventyTwoChangesJpanel.getScrollPaneAttribute().getViewport().getView();
                        StringBuffer buffer = new StringBuffer();
                        buffer.append("#cffffff11111111111111111#r");
                        buffer.append("#cff000022222222222222222");
                        buffer.append("#cffff0033333333333333333");
                        buffer.append("#c00000013215121651#r");
                        buffer.append("#cffffff651111235123511561321");
                        buffer.append("#cffffff3216511111132151321332");
                        buffer.append("#cffffff11111111111111111#r");
                        buffer.append("#cffffff11111111111111111");
                        buffer.append("#cffffff11111111111111111");
                        buffer.append("#cffffff11111111111111111#r");
                        buffer.append("#cff000022222222222222222");
                        buffer.append("#cffff0033333333333333333");
                        buffer.append("#c00000013215121651#r");
                        buffer.append("#cffffff651111235123511561321");
                        buffer.append("#cffffff3216511111132151321332");
                        buffer.append("#cffffff11111111111111111#r");
                        buffer.append("#cffffff11111111111111111");
                        buffer.append("#cffffff11111111111111111");
                        view = new RichLabel(buffer.toString(), UIUtils.TEXT_FONT2);
                        Dimension d = view.computeSize(130);
                        view.setSize(d);
                        view.setPreferredSize(d);
                        this.seventyTwoChangesJpanel.getScrollPaneAttribute().setViewportView(view);
                        break;
                    }
                    case 91: {
                        DefaultTableModel tableModel = this.seventyTwoChangesJpanel.getTableModel();
                        tableModel.getDataVector().clear();
                        for (int i = 0; i < 20; ++i) {
                            Vector<String> vectorRank = new Vector<>();
                            vectorRank.add("弟" + i);
                            vectorRank.add("弟" + i);
                            vectorRank.add("弟" + i);
                            vectorRank.add("弟" + i);
                            tableModel.addRow(vectorRank);
                        }
                        break;
                    }
                }
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        else {
            try {
                switch (this.caozuo) {
                    case 2:
                    case 3:
                    case 6:
                    case 7: {
                        int menuType = this.taobaoCourtMainJpanel.getTaobaoCourtCardJpanel().getMenuType();
                        TaobaoCourtCardJpanel taobaoCourtCardJpanel = this.taobaoCourtMainJpanel.getTaobaoCourtCardJpanel();
                        if (menuType != this.caozuo) {
                            if (menuType == 2) {
                                this.taobaoCourtMainJpanel.getShopMenuBtn().setIcons(CutButtonImage.cuts("img/xy2uiimg/一级选项卡_商城_未选中_w80,h120.png"));
                                this.taobaoCourtMainJpanel.getjfNum().setVisible(true);
                            }
                            else if (menuType == 3) {
                                this.taobaoCourtMainJpanel.getTaobaoMenuBtn().setIcons(CutButtonImage.cuts("img/xy2uiimg/一级选项卡_锦绣_未选中_w80,h120.png"));
                                this.taobaoCourtMainJpanel.getjfNum().setVisible(true);
                            }
                            else if (menuType == 6) {
                                this.taobaoCourtMainJpanel.getMonthCardBtn().setIcons(CutButtonImage.cuts("img/xy2uiimg/yuekao.png"));
                                this.taobaoCourtMainJpanel.getjfNum().setVisible(false);
                            }
                            this.taobaoCourtMainJpanel.getTaobaoCourtCardJpanel().setMenuType(this.caozuo);
                            menuType = this.taobaoCourtMainJpanel.getTaobaoCourtCardJpanel().getMenuType();
                        }
                        if (menuType == 2) {
                            this.taobaoCourtMainJpanel.getShopMenuBtn().setIcons(CutButtonImage.cuts("img/xy2uiimg/一级选项卡_商城_选中_w80,h120.png"));
                            taobaoCourtCardJpanel.getCardLayout().show(taobaoCourtCardJpanel, "shopOnline");
                            this.taobaoCourtMainJpanel.getjfNum().setVisible(true);
                            break;
                        }
                        else if (menuType == 3) {
                            this.taobaoCourtMainJpanel.getTaobaoMenuBtn().setIcons(CutButtonImage.cuts("img/xy2uiimg/一级选项卡_锦绣_选中_w80,h120.png"));
                            taobaoCourtCardJpanel.getCardLayout().show(taobaoCourtCardJpanel, "taobaoCourt");
                            taobaoCourtCardJpanel.getTaobaoCourtSplendidJpanel().showTaobao();
                            this.taobaoCourtMainJpanel.getjfNum().setVisible(true);
                            break;
                        }
                        else if (menuType == 6) {
                            this.taobaoCourtMainJpanel.getMonthCardBtn().setIcons(CutButtonImage.cuts("img/xy2uiimg/yuekao1.png"));
                            taobaoCourtCardJpanel.getMonthlyCardJpanel().changeTime();
                            taobaoCourtCardJpanel.getCardLayout().show(taobaoCourtCardJpanel, "monthCard");
                            this.taobaoCourtMainJpanel.getjfNum().setVisible(false);
                            break;
                        }
                        else {
                            break;
                        }
                    }
                    case 10:
                    case 11:
                    case 12:
                    case 13:
                    case 14:
                    case 15:
                    case 16:
                    case 17:
                    case 18:
                    case 19: {
                        int shopType = this.shopOnlineJpanel.getShopType();
                        if (shopType != this.caozuo - 4) {
                            if (shopType == 6) {
                                this.shopOnlineJpanel.getTreasureMenuBtn().setIcons(CutButtonImage.cuts("img/xy2uiimg/B280.png"));
                            }
                            else if (shopType == 7) {
                                this.shopOnlineJpanel.getSkillMenuBtn().setIcons(CutButtonImage.cuts("img/xy2uiimg/B296.png"));
                            }
                            else if (shopType == 8) {
                                this.shopOnlineJpanel.getMythicalMenuBtn().setIcons(CutButtonImage.cuts("img/xy2uiimg/B282.png"));
                            }
                            else if (shopType == 9) {
                                this.shopOnlineJpanel.getDeviceMenuBtn().setIcons(CutButtonImage.cuts("img/xy2uiimg/B294.png"));
                            }
                            else if (shopType == 10) {
                                this.shopOnlineJpanel.getBaldricMenuBtn().setIcons(CutButtonImage.cuts("img/xy2uiimg/B290.png"));
                            }
                            else if (shopType == 11) {
                                this.shopOnlineJpanel.getIntegralMenuBtn().setIcons(CutButtonImage.cuts("img/xy2uiimg/B292.png"));
                            }
                            else if (shopType == 12) {
                                this.shopOnlineJpanel.getSkillBookMenuBtn().setIcons(CutButtonImage.cuts("img/xy2uiimg/B284.png"));
                            }
                            else if (shopType == 13) {
                                this.shopOnlineJpanel.getQuestMenuBtn().setIcons(CutButtonImage.cuts("img/xy2uiimg/C286.png"));
                            }
                            else if (shopType == 14) {
                                this.shopOnlineJpanel.getTrepanningMenuBtn().setIcons(CutButtonImage.cuts("img/xy2uiimg/B288.png"));
                            }
                            else if (shopType == 15) {
                                this.shopOnlineJpanel.getTransfergoldMenuBtn().setIcons(CutButtonImage.cuts("inkImg/hongmu1/BZ2.png"));
                            }
                            shopType = this.caozuo - 4;
                            if (shopType == 6) {
                                this.shopOnlineJpanel.getTreasureMenuBtn().setIcons(CutButtonImage.cuts("img/xy2uiimg/B281.png"));
                            }
                            else if (shopType == 7) {
                                this.shopOnlineJpanel.getSkillMenuBtn().setIcons(CutButtonImage.cuts("img/xy2uiimg/B297.png"));
                            }
                            else if (shopType == 8) {
                                this.shopOnlineJpanel.getMythicalMenuBtn().setIcons(CutButtonImage.cuts("img/xy2uiimg/B283.png"));
                            }
                            else if (shopType == 9) {
                                this.shopOnlineJpanel.getDeviceMenuBtn().setIcons(CutButtonImage.cuts("img/xy2uiimg/B295.png"));
                            }
                            else if (shopType == 10) {
                                this.shopOnlineJpanel.getBaldricMenuBtn().setIcons(CutButtonImage.cuts("img/xy2uiimg/B291.png"));
                            }
                            else if (shopType == 11) {
                                this.shopOnlineJpanel.getIntegralMenuBtn().setIcons(CutButtonImage.cuts("img/xy2uiimg/B293.png"));
                            }
                            else if (shopType == 12) {
                                this.shopOnlineJpanel.getSkillBookMenuBtn().setIcons(CutButtonImage.cuts("img/xy2uiimg/V285.png"));
                            }
                            else if (shopType == 13) {
                                this.shopOnlineJpanel.getQuestMenuBtn().setIcons(CutButtonImage.cuts("img/xy2uiimg/B287.png"));
                            }
                            else if (shopType == 14) {
                                this.shopOnlineJpanel.getTrepanningMenuBtn().setIcons(CutButtonImage.cuts("img/xy2uiimg/B289.png"));
                            }
                            else if (shopType == 15) {
                                this.shopOnlineJpanel.getTransfergoldMenuBtn().setIcons(CutButtonImage.cuts("inkImg/hongmu1/BZ1.png"));
                            }
                        }
                        this.shopOnlineJpanel.setNowPage(1);
                        this.shopOnlineJpanel.isShopGoods(this.caozuo - 4);
                        break;
                    }
                    case 20: {
                        if (this.shopOnlineJpanel.getNowPage() <= 1) {
                            return;
                        }
                        this.shopOnlineJpanel.setNowPage(1);
                        this.shopOnlineJpanel.isShopGoods(this.shopOnlineJpanel.getShopType());
                        break;
                    }
                    case 21: {
                        if (this.shopOnlineJpanel.getNowPage() <= 1) {
                            return;
                        }
                        this.shopOnlineJpanel.setNowPage(this.shopOnlineJpanel.getNowPage() - 1);
                        this.shopOnlineJpanel.isShopGoods(this.shopOnlineJpanel.getShopType());
                        break;
                    }
                    case 22: {
                        if (this.shopOnlineJpanel.getNowPage() >= this.shopOnlineJpanel.getMaxPage()) {
                            return;
                        }
                        this.shopOnlineJpanel.setNowPage(this.shopOnlineJpanel.getNowPage() + 1);
                        this.shopOnlineJpanel.isShopGoods(this.shopOnlineJpanel.getShopType());
                        break;
                    }
                    case 23: {
                        if (this.shopOnlineJpanel.getNowPage() >= this.shopOnlineJpanel.getMaxPage()) {
                            return;
                        }
                        this.shopOnlineJpanel.setNowPage(this.shopOnlineJpanel.getMaxPage());
                        this.shopOnlineJpanel.isShopGoods(this.shopOnlineJpanel.getShopType());
                        break;
                    }
                    case 30: {
                        this.quotaJpanel.getExchangeMenuBtn().setIcons(CutButtonImage.cuts("img/xy2uiimg/二级选项卡_商城_限购_赠送好礼_选中_w100,h78.png"));
                        this.quotaJpanel.getLevelMenuBtn().setIcons(CutButtonImage.cuts("img/xy2uiimg/二级选项卡_商城_限购_冲级必备_未选中_w100,h78.png"));
                        this.quotaJpanel.getDiscountMenuBtn().setIcons(CutButtonImage.cuts("img/xy2uiimg/二级选项卡_商城_限购_显示折扣_未选中_w100,h78.png"));
                        break;
                    }
                    case 31: {
                        this.quotaJpanel.getExchangeMenuBtn().setIcons(CutButtonImage.cuts("img/xy2uiimg/二级选项卡_商城_限购_赠送好礼_未选中_w100,h78.png"));
                        this.quotaJpanel.getLevelMenuBtn().setIcons(CutButtonImage.cuts("img/xy2uiimg/二级选项卡_商城_限购_冲级必备_选中_w100,h78.png"));
                        this.quotaJpanel.getDiscountMenuBtn().setIcons(CutButtonImage.cuts("img/xy2uiimg/二级选项卡_商城_限购_显示折扣_未选中_w100,h78.png"));
                        break;
                    }
                    case 32: {
                        this.quotaJpanel.getExchangeMenuBtn().setIcons(CutButtonImage.cuts("img/xy2uiimg/二级选项卡_商城_限购_赠送好礼_未选中_w100,h78.png"));
                        this.quotaJpanel.getLevelMenuBtn().setIcons(CutButtonImage.cuts("img/xy2uiimg/二级选项卡_商城_限购_冲级必备_未选中_w100,h78.png"));
                        this.quotaJpanel.getDiscountMenuBtn().setIcons(CutButtonImage.cuts("img/xy2uiimg/二级选项卡_商城_限购_显示折扣_选中_w100,h78.png"));
                        break;
                    }
                    case 60: {
                        this.changeMenuBtnDailyLogin();
                        break;
                    }
                    case 61: {
                        this.changeMenuBtnDailyLogin();
                        break;
                    }
                    case 80: {
                        this.changeMenuBtnSeventyTwoChanges();
                        break;
                    }
                    case 81: {
                        this.changeMenuBtnSeventyTwoChanges();
                        break;
                    }
                    case 90: {
                        RichLabel view = (RichLabel)this.seventyTwoChangesJpanel.getScrollPaneAttribute().getViewport().getView();
                        StringBuffer buffer = new StringBuffer();
                        buffer.append("#cffffff11111111111111111#r");
                        buffer.append("#cff000022222222222222222");
                        buffer.append("#cffff0033333333333333333");
                        buffer.append("#c00000013215121651#r");
                        buffer.append("#cffffff651111235123511561321");
                        buffer.append("#cffffff3216511111132151321332");
                        buffer.append("#cffffff11111111111111111#r");
                        buffer.append("#cffffff11111111111111111");
                        buffer.append("#cffffff11111111111111111");
                        buffer.append("#cffffff11111111111111111#r");
                        buffer.append("#cff000022222222222222222");
                        buffer.append("#cffff0033333333333333333");
                        buffer.append("#c00000013215121651#r");
                        buffer.append("#cffffff651111235123511561321");
                        buffer.append("#cffffff3216511111132151321332");
                        buffer.append("#cffffff11111111111111111#r");
                        buffer.append("#cffffff11111111111111111");
                        buffer.append("#cffffff11111111111111111");
                        view = new RichLabel(buffer.toString(), UIUtils.TEXT_FONT2);
                        Dimension d = view.computeSize(130);
                        view.setSize(d);
                        view.setPreferredSize(d);
                        this.seventyTwoChangesJpanel.getScrollPaneAttribute().setViewportView(view);
                        break;
                    }
                    case 91: {
                        DefaultTableModel tableModel = this.seventyTwoChangesJpanel.getTableModel();
                        tableModel.getDataVector().clear();
                        for (int i = 0; i < 20; ++i) {
                            Vector<String> vectorRank = new Vector<>();
                            vectorRank.add("弟" + i);
                            vectorRank.add("弟" + i);
                            vectorRank.add("弟" + i);
                            vectorRank.add("弟" + i);
                            tableModel.addRow(vectorRank);
                        }
                        break;
                    }
                    case 88888: {
                        System.out.println("充值");
                        break;
                    }
                }
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
    
    public void changeMenuBtnDailyLogin() throws Exception {
        if (MyIsif.getStyle().equals("水墨")) {
            for (int i = 0; i < this.dailyLoginJPanel.getMenuBtn().length; ++i) {
                if (i + 60 == this.caozuo) {
                    this.dailyLoginJPanel.getMenuBtn()[i].setIcons(CutButtonImage.cuts("inkImg/button/5.png"));
                    this.dailyLoginJPanel.getMenuStr().setText(this.dailyLoginJPanel.getMenuName()[i]);
                }
                else {
                    this.dailyLoginJPanel.getMenuBtn()[i].setIcons(CutButtonImage.cuts("inkImg/button/6.png"));
                }
            }
        }
        else {
            for (int i = 0; i < this.dailyLoginJPanel.getMenuBtn().length; ++i) {
                if (i + 60 == this.caozuo) {
                    this.dailyLoginJPanel.getMenuBtn()[i].setIcons(CutButtonImage.cuts("img/xy2uiimg/5.png"));
                    this.dailyLoginJPanel.getMenuStr().setText(this.dailyLoginJPanel.getMenuName()[i]);
                }
                else {
                    this.dailyLoginJPanel.getMenuBtn()[i].setIcons(CutButtonImage.cuts("img/xy2uiimg/6.png"));
                }
            }
        }
    }
    
    public void changeMenuBtnSeventyTwoChanges() throws Exception {
        if (MyIsif.getStyle().equals("水墨")) {
            for (int i = 0; i < this.seventyTwoChangesJpanel.getMenuBtn().length; ++i) {
                if (i + 80 == this.caozuo) {
                    this.seventyTwoChangesJpanel.getMenuBtn()[i].setIcons(CutButtonImage.cuts("inkImg/button/4.png"));
                }
                else {
                    this.seventyTwoChangesJpanel.getMenuBtn()[i].setIcons(CutButtonImage.cuts("inkImg/button/3.png"));
                }
            }
        }
        else {
            for (int i = 0; i < this.seventyTwoChangesJpanel.getMenuBtn().length; ++i) {
                if (i + 80 == this.caozuo) {
                    this.seventyTwoChangesJpanel.getMenuBtn()[i].setIcons(CutButtonImage.cuts("img/xy2uiimg/4.png"));
                }
                else {
                    this.seventyTwoChangesJpanel.getMenuBtn()[i].setIcons(CutButtonImage.cuts("img/xy2uiimg/3.png"));
                }
            }
        }
    }
}
