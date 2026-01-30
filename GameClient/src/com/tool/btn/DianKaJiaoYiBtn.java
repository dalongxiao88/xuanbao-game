package com.tool.btn;

import org.come.bean.LoginResult;
import org.come.entity.SellXianyu;
import org.come.until.GsonUtil;
import java.math.BigDecimal;
import org.come.entity.SellXianYuOrder;
import org.come.Frame.ZhuFrame;
import org.come.until.FormsManagement;
import com.tool.role.RoleData;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.until.CutButtonImage;
import com.updateNew.MyIsif;
import java.awt.event.MouseEvent;
import com.tool.tcpimg.UIUtils;
import org.come.Jpanel.DianKaJiaoYiChouJiangJpanel;
import org.come.Jpanel.DianKaJiaoYiJiShouJpanel;
import org.come.Jpanel.DianKaJiaoYiGouMaiJpanel;
import org.come.Jpanel.DianKaJiaoYiJpanel;
import org.come.Jpanel.DianKaJiaoYiCardJpanel;

public class DianKaJiaoYiBtn extends MoBanBtn
{
    private int caozuo;
    private DianKaJiaoYiCardJpanel cardJpanel;
    private DianKaJiaoYiJpanel dkjyJpanel;
    private DianKaJiaoYiGouMaiJpanel goumaiJpanel;
    private DianKaJiaoYiJiShouJpanel jishouJpanel;
    private DianKaJiaoYiChouJiangJpanel choujiangJpanel;
    
    public DianKaJiaoYiBtn(String iconpath, int type, int caozuo, DianKaJiaoYiCardJpanel cardJpanel, DianKaJiaoYiJpanel dkjyJpanel) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.cardJpanel = cardJpanel;
        this.dkjyJpanel = dkjyJpanel;
    }
    
    public DianKaJiaoYiBtn(String iconpath, int type, String text, int caozuo, DianKaJiaoYiGouMaiJpanel goumaiJpanel) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.goumaiJpanel = goumaiJpanel;
        this.setText(text);
        this.setFont(UIUtils.TEXT_HY16);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    public DianKaJiaoYiBtn(String iconpath, int type, String text, int caozuo, DianKaJiaoYiJiShouJpanel jishouJpanel) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.jishouJpanel = jishouJpanel;
        this.setText(text);
        if (caozuo == 11) {
            this.setFont(UIUtils.TEXT_HY16);
        }
        else {
            this.setFont(UIUtils.TEXT_FONT11);
            this.setColors(UIUtils.COLOR_BTNTEXT);
        }
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    public DianKaJiaoYiBtn(String iconpath, int type, String text, int caozuo, DianKaJiaoYiChouJiangJpanel choujiangJpanel) {
        super(iconpath, type, UIUtils.COLOR_WHITE2);
        this.caozuo = caozuo;
        this.choujiangJpanel = choujiangJpanel;
        this.setText(text);
        this.setFont(UIUtils.TEXT_FONT_17);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    public DianKaJiaoYiBtn(String iconpath, int type, String text, int caozuo, DianKaJiaoYiChouJiangJpanel choujiangJpanel, String sm) {
        super(iconpath, type, UIUtils.COLOR_BTNPUTONG2);
        this.caozuo = caozuo;
        this.choujiangJpanel = choujiangJpanel;
        this.setText(text);
        this.setFont(UIUtils.TEXT_FONT_17);
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
        try {
            switch (this.caozuo) {
                case 2: {
                    if (this.dkjyJpanel.getBtnJiShou() != null) {
                        if (MyIsif.getStyle().equals("水墨")) {
                            this.dkjyJpanel.getBtnGouMai().setIcons(CutButtonImage.cuts("Client/神通天演册/40×40/仙/B400.png"));
                            this.dkjyJpanel.getBtnJiShou().setIcons(CutButtonImage.cuts("Client/神通天演册/40×40/仙/B405.png"));
                            this.dkjyJpanel.getBtnChouJiang().setIcons(CutButtonImage.cuts("Client/神通天演册/40×40/仙/B403.png"));
                        }
                        else {
                            this.dkjyJpanel.getBtnGouMai().setIcons(CutButtonImage.cuts("Client/神通天演册/40×40/仙/B113.png"));
                            this.dkjyJpanel.getBtnJiShou().setIcons(CutButtonImage.cuts("Client/神通天演册/40×40/仙/B114.png"));
                            this.dkjyJpanel.getBtnChouJiang().setIcons(CutButtonImage.cuts("Client/神通天演册/40×40/仙/B116.png"));
                        }
                    }
                    this.cardJpanel.getGoumaiJpanel().changeFrom(this.caozuo);
                    this.cardJpanel.getCar().show(this.cardJpanel, "2");
                    String mes2 = Agreement.getAgreement().sellxianyuAgreement("ALLLIST|1");
                    SendMessageUntil.toServer(mes2);
                    break;
                }
                case 3: {
                    if (this.dkjyJpanel.getBtnJiShou() != null) {
                        if (MyIsif.getStyle().equals("水墨")) {
                            LoginResult loginResult = RoleData.getRoleData().getLoginResult();
                            this.dkjyJpanel.getBtnGouMai().setIcons(CutButtonImage.cuts("Client/神通天演册/40×40/仙/B401.png"));
                            this.dkjyJpanel.getBtnJiShou().setIcons(CutButtonImage.cuts("Client/神通天演册/40×40/仙/B404.png"));
                            this.dkjyJpanel.getBtnChouJiang().setIcons(CutButtonImage.cuts("Client/神通天演册/40×40/仙/B403.png"));
                        }
                        else {
                            this.dkjyJpanel.getBtnGouMai().setIcons(CutButtonImage.cuts("Client/神通天演册/40×40/仙/B112.png"));
                            this.dkjyJpanel.getBtnJiShou().setIcons(CutButtonImage.cuts("Client/神通天演册/40×40/仙/B115.png"));
                            this.dkjyJpanel.getBtnChouJiang().setIcons(CutButtonImage.cuts("Client/神通天演册/40×40/仙/B116.png"));
                        }
                    }
                    this.cardJpanel.getJishouJpanel().changeFrom(this.caozuo);
                    this.cardJpanel.getCar().show(this.cardJpanel, "3");
                    String sendmes = Agreement.getAgreement().sellxianyuAgreement("MYLIST|1");
                    SendMessageUntil.toServer(sendmes);
                    break;
                }
                case 4: {
                    if (this.dkjyJpanel.getBtnJiShou() != null) {
                        if (MyIsif.getStyle().equals("水墨")) {
                            this.dkjyJpanel.getBtnGouMai().setIcons(CutButtonImage.cuts("Client/神通天演册/40×40/仙/B401.png"));
                            this.dkjyJpanel.getBtnJiShou().setIcons(CutButtonImage.cuts("Client/神通天演册/40×40/仙/B405.png"));
                            this.dkjyJpanel.getBtnChouJiang().setIcons(CutButtonImage.cuts("Client/神通天演册/40×40/仙/B402.png"));
                        }
                        else {
                            this.dkjyJpanel.getBtnGouMai().setIcons(CutButtonImage.cuts("Client/神通天演册/40×40/仙/B112.png"));
                            this.dkjyJpanel.getBtnJiShou().setIcons(CutButtonImage.cuts("Client/神通天演册/40×40/仙/B114.png"));
                            this.dkjyJpanel.getBtnChouJiang().setIcons(CutButtonImage.cuts("Client/神通天演册/40×40/仙/B117.png"));
                        }
                    }
                    this.cardJpanel.getChoujiangJpanel().changeFrom(this.caozuo);
                    this.cardJpanel.getCar().show(this.cardJpanel, "4");
                    break;
                }
                case 10: {
                    if (FormsManagement.getframe(14).isVisible()) {
                        ZhuFrame.getZhuJpanel().addPrompt2("交易时不能购买物品#45");
                        return;
                    }
                    if (this.goumaiJpanel.getDianshuJTF().getText().equals("0")) {
                        ZhuFrame.getZhuJpanel().addPrompt2("请输入要购买的点数#45");
                        return;
                    }
                    SellXianYuOrder sellXianYuOrder = new SellXianYuOrder();
                    sellXianYuOrder.setSellId(this.goumaiJpanel.getSelectSellId());
                    sellXianYuOrder.setPricePoint(this.goumaiJpanel.getUnitprice());
                    sellXianYuOrder.setTotalPrice(this.goumaiJpanel.getTotalsum());
                    sellXianYuOrder.setXianYuPoint(new BigDecimal(this.goumaiJpanel.getDianshuJTF().getText()));
                    if (sellXianYuOrder.getSellId() == null) {
                        ZhuFrame.getZhuJpanel().addPrompt2("点卡发生变化，请重新刷新后选择需要购买的点卡");
                        return;
                    }
                    String mes3 = Agreement.getAgreement().sellxianyuAgreement("BUY|" + GsonUtil.getGsonUtil().getgson().toJson(sellXianYuOrder));
                    SendMessageUntil.toServer(mes3);
                    break;
                }
                case 11: {
                    if (FormsManagement.getframe(14).isVisible()) {
                        ZhuFrame.getZhuJpanel().addPrompt2("交易时不能购买物品#45");
                        return;
                    }
                    if (this.jishouJpanel.getJishoudianshuJTF().getText().equals("0")) {
                        ZhuFrame.getZhuJpanel().addPrompt2("请输入要寄售的点数#45");
                        return;
                    }
                    if (this.jishouJpanel.getShoujiaJTF().getText().equals("0")) {
                        ZhuFrame.getZhuJpanel().addPrompt2("请输入每点售价#45");
                        return;
                    }
                    if (Integer.parseInt(this.jishouJpanel.getJishoudianshuJTF().getText()) > this.jishouJpanel.getMyDianShu().intValue()) {
                        ZhuFrame.getZhuJpanel().addPrompt2("寄售点不足#45");
                        return;
                    }
                    BigDecimal money = RoleData.getRoleData().getLoginResult().getGold();
                    if (money.compareTo(this.jishouJpanel.getYajin()) < 0) {
                        ZhuFrame.getZhuJpanel().addPrompt2("现金不足，无法支付寄售押金#45");
                        return;
                    }
                    SellXianyu sellxianyu = new SellXianyu();
                    sellxianyu.setDeposit(this.jishouJpanel.getYajin());
                    sellxianyu.setPricePoint(new BigDecimal(this.jishouJpanel.getShoujiaJTF().getText()));
                    sellxianyu.setTotalPrice(this.jishouJpanel.getTotalsum());
                    sellxianyu.setXianYuPoint(new BigDecimal(this.jishouJpanel.getJishoudianshuJTF().getText()));
                    String mes4 = Agreement.getAgreement().sellxianyuAgreement("ADD|" + GsonUtil.getGsonUtil().getgson().toJson(sellxianyu));
                    SendMessageUntil.toServer(mes4);
                    break;
                }
                case 12: {
                    if (this.jishouJpanel.getTab_row() == -1) {
                        ZhuFrame.getZhuJpanel().addPrompt2("请选择要下架的数据#45");
                        return;
                    }
                    BigDecimal id = this.jishouJpanel.getSelectSellId();
                    String mes5 = Agreement.getAgreement().sellxianyuAgreement("DOWN|" + id);
                    SendMessageUntil.toServer(mes5);
                    break;
                }
                case 50: {
                    DianKaJiaoYiChouJiangJpanel choujiangJpanel = this.choujiangJpanel;
                    DianKaJiaoYiChouJiangJpanel.jifen = 50;
                    BigDecimal czjf = RoleData.getRoleData().getLoginResult().getScoretype("充值积分");
                    if (czjf.intValue() < 50) {
                        ZhuFrame.getZhuJpanel().addPrompt("积分不足,无法操作！");
                        return;
                    }
                    new Thread(this.choujiangJpanel).start();
                    this.choujiangJpanel.getF50().btntypechange(-1);
                    this.choujiangJpanel.getF150().btntypechange(-1);
                    this.choujiangJpanel.getF1000().btntypechange(-1);
                    this.choujiangJpanel.getF50().setEnabled(false);
                    this.choujiangJpanel.getF150().setEnabled(false);
                    this.choujiangJpanel.getF1000().setEnabled(false);
                    break;
                }
                case 150: {
                    BigDecimal czjf = RoleData.getRoleData().getLoginResult().getScoretype("充值积分");
                    if (czjf.intValue() < 150) {
                        ZhuFrame.getZhuJpanel().addPrompt("积分不足,无法操作！");
                        return;
                    }
                    DianKaJiaoYiChouJiangJpanel choujiangJpanel2 = this.choujiangJpanel;
                    DianKaJiaoYiChouJiangJpanel.jifen = 150;
                    new Thread(this.choujiangJpanel).start();
                    this.choujiangJpanel.getF50().btntypechange(-1);
                    this.choujiangJpanel.getF150().btntypechange(-1);
                    this.choujiangJpanel.getF1000().btntypechange(-1);
                    this.choujiangJpanel.getF50().btntypechange(-1);
                    this.choujiangJpanel.getF150().btntypechange(-1);
                    this.choujiangJpanel.getF1000().btntypechange(-1);
                    this.choujiangJpanel.getF50().setEnabled(false);
                    this.choujiangJpanel.getF150().setEnabled(false);
                    this.choujiangJpanel.getF1000().setEnabled(false);
                    break;
                }
                case 1000: {
                    BigDecimal czjf = RoleData.getRoleData().getLoginResult().getScoretype("充值积分");
                    if (czjf.intValue() < 1000) {
                        ZhuFrame.getZhuJpanel().addPrompt("积分不足,无法操作！");
                        return;
                    }
                    DianKaJiaoYiChouJiangJpanel choujiangJpanel3 = this.choujiangJpanel;
                    DianKaJiaoYiChouJiangJpanel.jifen = 1000;
                    new Thread(this.choujiangJpanel).start();
                    this.choujiangJpanel.getF50().btntypechange(-1);
                    this.choujiangJpanel.getF150().btntypechange(-1);
                    this.choujiangJpanel.getF1000().btntypechange(-1);
                    this.choujiangJpanel.getF50().setEnabled(false);
                    this.choujiangJpanel.getF150().setEnabled(false);
                    this.choujiangJpanel.getF1000().setEnabled(false);
                    break;
                }
                case 9999: {
                    ZhuFrame.getZhuJpanel().addPrompt2("正在抽奖，请稍等#12");
                    break;
                }
                case 61: {
                    FormsManagement.showForm(39);
                    FormsManagement.HideForm(114);
                    break;
                }
            }
        }
        catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
