package com.tool.btn;

import org.come.entity.Goodstable;
import org.come.model.Shop;
import org.lottery.frame.LotteryIntegralMainJframe;
import org.come.socket.SendMessageUntil;
import org.come.until.GsonUtil;
import org.come.socket.Agreement;
import java.math.BigDecimal;
import org.come.bean.BuyShopBean;
import com.tool.role.RoleLingFa;
import com.gl.util.Xy2Util;
import com.tool.role.RoleData;
import org.come.until.GoodsListFromServerUntil;
import org.come.until.UserMessUntil;
import org.come.Frame.ZhuFrame;
import org.come.until.FormsManagement;
import org.come.until.Util;
import java.awt.event.MouseEvent;
import com.tool.tcpimg.UIUtils;
import java.awt.Font;
import java.awt.Color;
import org.lottery.panel.LotteryIntegralGoodsJpanel;
import org.come.Jpanel.ShoppingBuyJpanel;

public class ShoppingBuyBtn extends MoBanBtn
{
    private ShoppingBuyJpanel buyJpanel;
    private int caozuo;
    private LotteryIntegralGoodsJpanel lotteryIntegralGoodsJpanel;
    
    public ShoppingBuyBtn(String iconpath, int type, int caozuo, ShoppingBuyJpanel buyJpanel) {
        super(iconpath, type);
        this.buyJpanel = buyJpanel;
        this.caozuo = caozuo;
    }
    
    public ShoppingBuyBtn(String iconpath, int type, Color[] colors, Font font, String text, int caozuo, ShoppingBuyJpanel buyJpanel) {
        super(iconpath, type, colors);
        this.setText(text);
        this.setFont(font);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.buyJpanel = buyJpanel;
        this.caozuo = caozuo;
    }
    
    public ShoppingBuyBtn(String iconpath, int type, String text, int caozuo, LotteryIntegralGoodsJpanel lotteryIntegralGoodsJpanel) {
        super(iconpath, type, UIUtils.COLOR_BTNPUTONG);
        this.setText(text);
        this.setFont(UIUtils.TEXT_HY88);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.lotteryIntegralGoodsJpanel = lotteryIntegralGoodsJpanel;
        this.caozuo = caozuo;
    }
    
    public ShoppingBuyBtn(String iconpath, int type, String text, int caozuo, LotteryIntegralGoodsJpanel lotteryIntegralGoodsJpanel, String sm) {
        super(iconpath, type, UIUtils.COLOR_BLACK);
        this.setText(text);
        this.setFont(UIUtils.TEXT_HY88);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.lotteryIntegralGoodsJpanel = lotteryIntegralGoodsJpanel;
        this.caozuo = caozuo;
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        switch (this.caozuo) {
            case 1: {
                if (Util.isCanBuyOrno()) {
                    return;
                }
                if (FormsManagement.getframe(14).isVisible()) {
                    ZhuFrame.getZhuJpanel().addPrompt2("交易时不能购买物品");
                    return;
                }
                int sum = Integer.parseInt(this.buyJpanel.getTextNumber().getText());
                if (sum <= 0) {
                    ZhuFrame.getZhuJpanel().addPrompt2("你购买数量为零");
                    return;
                }
                Shop shop = this.buyJpanel.getShop();
                if (shop != null) {
                    Goodstable goodstable = UserMessUntil.getgoodstable(shop.getShopiid());
                    sum = GoodsListFromServerUntil.Surplussum((goodstable != null) ? (goodstable.getType() + "") : "6500", shop.getShopid(), sum);
                    if (sum <= 0) {
                        ZhuFrame.getZhuJpanel().addPrompt2("你背包已满");
                        return;
                    }
                    try {
                        long fa = 0L;
                        long gjg = shop.getShopprice();
                        long yy = RoleData.getRoleData().getLoginResult().getGold().longValue();
                        if (this.buyJpanel.getCount() == 0) {
                            if (shop.getShoptype() == 1) {
                                yy = RoleData.getRoleData().getLoginResult().getCodecard().longValue();
                            }
                        }
                        else if (this.buyJpanel.Balance != null) {
                            yy = this.buyJpanel.Balance.longValue();
                        }
                        else if (this.buyJpanel.Currency.equals("师贡")) {
                            yy = RoleData.getRoleData().getLoginResult().getSavegold().longValue();
                        }
                        fa = yy / gjg;
                        if (fa > (long)sum) {
                            fa = (long)sum;
                        }
                        yy = gjg * fa;
                        if (fa > 0L) {
                            if (Xy2Util.isOnly(shop.getShopname())) {
                                sum = 1;
                                fa = 1L;
                                yy = gjg * fa;
                                if (Xy2Util.isExist(shop.getShopname())) {
                                    return;
                                }
                            }
                            if (RoleLingFa.isFB2(shop.getShopname())) {
                                sum = 1;
                                fa = 1L;
                                yy = gjg * fa;
                                if (!RoleLingFa.getRoleLingFa().addfb(shop.getShopname())) {
                                    return;
                                }
                            }
                            BuyShopBean bean = new BuyShopBean();
                            if (this.buyJpanel.getCount() == 0) {
                                bean.setAte(3);
                                bean.setCd(shop.getShopid());
                                bean.setSum((int)fa);
                                bean.setPrice(yy);
                                bean.setnId(this.buyJpanel.getnId());
                            }
                            else {
                                bean.setAte(1);
                                bean.setCd(shop.getShopid());
                                bean.setSum((int)fa);
                                bean.setPrice(yy);
                                if (this.buyJpanel.Balance != null) {
                                    this.buyJpanel.Balance = new BigDecimal(this.buyJpanel.Balance.longValue() - yy);
                                }
                            }
                            String senmes = Agreement.getAgreement().nbuyAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
                            SendMessageUntil.toServer(senmes);
                        }
                        else {
                            ZhuFrame.getZhuJpanel().addPrompt2("你没有足够的" + this.buyJpanel.Currency);
                        }
                    }
                    catch (NumberFormatException e2) {
                        e2.printStackTrace();
                    }
                    break;
                }
                else {
                    break;
                }
            }
            case 2: {
                this.buyJpanel.setGoldType(1);
                this.buyJpanel.changeGoodsView();
                break;
            }
            case 3: {
                this.buyJpanel.setGoldType(0);
                this.buyJpanel.changeGoodsView();
                break;
            }
            case 4: {
                if (FormsManagement.getframe(14).isVisible()) {
                    ZhuFrame.getZhuJpanel().addPrompt2("交易时不能购买物品");
                    return;
                }
                Shop shop2 = this.lotteryIntegralGoodsJpanel.getShop();
                if (shop2 != null) {
                    Goodstable goodstable2 = UserMessUntil.getgoodstable(shop2.getShopiid());
                    int sum = GoodsListFromServerUntil.Surplussum((goodstable2 != null) ? (goodstable2.getType() + "") : "6500", shop2.getShopid(), 1);
                    if (sum <= 0) {
                        ZhuFrame.getZhuJpanel().addPrompt2("你背包已满");
                        return;
                    }
                    try {
                        long fa2 = 0L;
                        long gjg2 = shop2.getShopprice();
                        long yy2 = RoleData.getRoleData().getLoginResult().getScoretype("幸运奖池积分").longValue();
                        fa2 = yy2 / gjg2;
                        if (fa2 > (long)sum) {
                            fa2 = (long)sum;
                        }
                        yy2 = gjg2 * fa2;
                        if (fa2 > 0L) {
                            if (RoleLingFa.isFB2(shop2.getShopname())) {
                                sum = 1;
                                fa2 = 1L;
                                yy2 = gjg2 * fa2;
                                if (!RoleLingFa.getRoleLingFa().addfb(shop2.getShopname())) {
                                    return;
                                }
                            }
                            BuyShopBean bean2 = new BuyShopBean();
                            bean2.setAte(1);
                            bean2.setCd(shop2.getShopid());
                            bean2.setSum((int)fa2);
                            bean2.setPrice(gjg2);
                            LotteryIntegralMainJframe.getLotteryIntegralMainJframe().getLotteryIntegralMainPanel().getIntegralTypeLab().setText("幸运奖池积分:" + (RoleData.getRoleData().getLoginResult().getScoretype("幸运奖池积分").longValue() - yy2));
                            String senmes2 = Agreement.getAgreement().nbuyAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean2));
                            SendMessageUntil.toServer(senmes2);
                        }
                        else {
                            ZhuFrame.getZhuJpanel().addPrompt2("你没有足够的幸运奖池积分");
                        }
                    }
                    catch (NumberFormatException e3) {
                        e3.printStackTrace();
                    }
                    break;
                }
                else {
                    break;
                }
            }
        }
    }
}
