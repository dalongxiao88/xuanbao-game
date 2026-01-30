package com.tool.btn;

import org.come.bean.LoginResult;
import org.come.model.Shop;
import org.come.entity.Goodstable;
import org.come.model.Eshop;
import come.tool.JDialog.TiShiUtil;
import org.come.Frame.OptionsJframe;
import java.net.URI;
import java.awt.Desktop;
import io.netty.util.internal.StringUtil;
import org.come.socket.DownLoadTxt;
import org.come.socket.SendMessageUntil;
import org.come.until.FormsManagement;
import org.come.until.GsonUtil;
import org.come.socket.Agreement;
import com.tool.role.RoleData;
import org.come.until.GoodsListFromServerUntil;
import org.come.until.UserMessUntil;
import java.math.BigDecimal;
import org.come.bean.BuyShopBean;
import org.come.Frame.ZhuFrame;
import java.awt.event.MouseEvent;
import java.awt.Color;
import com.tool.tcpimg.UIUtils;
import org.come.Jpanel.YuekaJpanel;
import org.come.Jpanel.MonthlyCardJpanel;
import org.come.Jpanel.GoodDetailedJpanel;

public class CommonBtn extends MoBanBtn
{
    private int caozuo;
    private GoodDetailedJpanel goodDetailedJpanel;
    private MonthlyCardJpanel monthlyCardJpanel;
    private YuekaJpanel yuekaJpanel;
    private int wupinCount;
    
    public CommonBtn(String iconpath, int type) {
        super(iconpath, type);
    }
    
    public CommonBtn(String iconpath, int type, String text, int caozuo) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.setText(text);
        if (caozuo != 8 && caozuo != 9) {
            this.setFont(UIUtils.TEXT_HY16);
            this.setForeground(UIUtils.COLOR_BTNPUTONG[0]);
        }
        else {
            this.setFont(UIUtils.TEXT_FONT);
            this.setForeground(Color.white);
        }
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    public CommonBtn(String iconpath, int type, String text, int caozuo, GoodDetailedJpanel goodDetailedJpanel) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.goodDetailedJpanel = goodDetailedJpanel;
        this.setText(text);
        this.setFont(UIUtils.TEXT_HY88);
        this.setForeground(UIUtils.COLOR_BTNPUTONG[0]);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    public CommonBtn(String iconpath, int type, String text, int caozuo, GoodDetailedJpanel goodDetailedJpanel, String SM) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.goodDetailedJpanel = goodDetailedJpanel;
        this.setText(text);
        this.setFont(UIUtils.TEXT_HY88);
        this.setForeground(UIUtils.COLOR_BLACK[0]);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    public CommonBtn(String iconpath, int type, String text, int caozuo, MonthlyCardJpanel monthlyCardJpanel) {
        super(iconpath, type);
        this.setText(text);
        this.setFont(UIUtils.TEXT_HY16);
        this.setForeground(UIUtils.COLOR_BTNPUTONG[0]);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.caozuo = caozuo;
        this.monthlyCardJpanel = monthlyCardJpanel;
    }
    
    public CommonBtn(String iconpath, int type, String text, int caozuo, MonthlyCardJpanel monthlyCardJpanel, String sm) {
        super(iconpath, type);
        this.setText(text);
        this.setFont(UIUtils.TEXT_HY16);
        this.setForeground(UIUtils.COLOR_BLACK[0]);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.caozuo = caozuo;
        this.monthlyCardJpanel = monthlyCardJpanel;
    }
    
    public CommonBtn(String iconpath, int type, String text, int caozuo, YuekaJpanel yuekaJpanel, String sm) {
        super(iconpath, type);
        this.setText(text);
        this.setFont(UIUtils.TEXT_COM_FONT);
        this.setForeground(UIUtils.Black[1]);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.caozuo = caozuo;
        this.yuekaJpanel = yuekaJpanel;
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        if (this.caozuo >= 1 && this.caozuo <= 3) {
            if ("".equals(this.goodDetailedJpanel.getGoumaiCount().getText().trim())) {
                ZhuFrame.getZhuJpanel().addPrompt2("数量不能为空");
                return;
            }
            this.wupinCount = (int)Integer.valueOf(this.goodDetailedJpanel.getGoumaiCount().getText());
            int moneyType = 0;
            BuyShopBean bean = new BuyShopBean();
            Goodstable goodstable;
            if (this.goodDetailedJpanel.getType() == 0) {
                Eshop eshop = this.goodDetailedJpanel.getEshop();
                if (eshop == null) {
                    return;
                }
                goodstable = UserMessUntil.getgoodstable(new BigDecimal(eshop.getEshopiid()));
                if (this.goodDetailedJpanel.getEshop().getEshoptype().equals("5")) {
                    moneyType = 2;
                }
                else if (this.goodDetailedJpanel.getEshop().getEshoptype().equals("9")) {
                    moneyType = 3;
                }
                else {
                    moneyType = 1;
                }
                bean.setAte(0);
                bean.setCd(eshop.getEshopid());
            }
            else {
                Shop shop = this.goodDetailedJpanel.getShop();
                if (shop == null) {
                    return;
                }
                goodstable = UserMessUntil.getgoodstable(shop.getShopiid());
                moneyType = shop.getShoptype();
                bean.setAte(4);
                bean.setCd(shop.getShopid());
                bean.setnId(shop.getNid());
            }
            if (this.caozuo == 1) {
                int sum = Integer.parseInt(this.goodDetailedJpanel.getGoumaiCount().getText());
                if (sum <= 0) {
                    ZhuFrame.getZhuJpanel().addPrompt2("购买数量必须大于零");
                    return;
                }
                int max = GoodsListFromServerUntil.Surplussum((goodstable == null) ? "-1" : ("" + goodstable.getType()), (goodstable == null) ? "-1" : ("" + goodstable.getGoodsid()), sum);
                if (max < sum) {
                    ZhuFrame.getZhuJpanel().addPrompt2("你背包已不足");
                    return;
                }
                LoginResult loginResult = RoleData.getRoleData().getLoginResult();
                int jg = Integer.parseInt(this.goodDetailedJpanel.getXiaohaoXianyu().getText());
                if (moneyType == 2) {
                    if ((int)loginResult.getMoney() < jg) {
                        ZhuFrame.getZhuJpanel().addPrompt2("没有足够的积分!");
                        return;
                    }
                }
                else if (moneyType == 3) {
                    if (loginResult.getTransfergold().longValue() < (long)jg) {
                        ZhuFrame.getZhuJpanel().addPrompt2("没有足够的转区币!");
                        return;
                    }
                }
                else if (moneyType == 1) {
                    if (loginResult.getCodecard().longValue() < (long)jg) {
                        ZhuFrame.getZhuJpanel().addPrompt2("没有足够的仙玉!");
                        return;
                    }
                }
                else if (loginResult.getGold().longValue() < (long)jg) {
                    ZhuFrame.getZhuJpanel().addPrompt2("没有足够的金钱!");
                    return;
                }
                try {
                    bean.setSum(sum);
                    bean.setPrice((long)jg);
                    String senmes = Agreement.getAgreement().nbuyAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
                    SendMessageUntil.toServer(senmes);
                }
                catch (Exception var12) {
                    var12.printStackTrace();
                }
            }
            else if (this.caozuo == 2) {
                if (this.wupinCount >= 999) {
                    ZhuFrame.getZhuJpanel().addPrompt2("最大数量999");
                    return;
                }
                int goodsPrice = (int)Integer.valueOf(this.goodDetailedJpanel.getWupinPrice().getText());
                ++this.wupinCount;
                this.goodDetailedJpanel.getGoumaiCount().setText(String.valueOf(this.wupinCount));
                this.goodDetailedJpanel.getXiaohaoXianyu().setText(String.valueOf(goodsPrice * this.wupinCount));
                this.goodDetailedJpanel.getPriceVal().setText(goodsPrice * this.wupinCount + this.goodDetailedJpanel.getNowMoneyType());
            }
            else if (this.caozuo == 3) {
                if (this.wupinCount <= 0) {
                    ZhuFrame.getZhuJpanel().addPrompt2("数量为零了");
                    return;
                }
                int goodsPrice = (int)Integer.valueOf(this.goodDetailedJpanel.getWupinPrice().getText());
                --this.wupinCount;
                this.goodDetailedJpanel.getGoumaiCount().setText(String.valueOf(this.wupinCount));
                this.goodDetailedJpanel.getXiaohaoXianyu().setText(String.valueOf(goodsPrice * this.wupinCount));
                this.goodDetailedJpanel.getPriceVal().setText(goodsPrice * this.wupinCount + this.goodDetailedJpanel.getNowMoneyType());
            }
        }
        else if (this.caozuo >= 20 && this.caozuo <= 22) {
            if (this.caozuo == 20) {
                if (!FormsManagement.getframe(3005).isVisible()) {

                }else{
                    ZhuFrame.getZhuJpanel().addPrompt2("请先完成季卡抽奖后再点击");
                    return;
                }
                FormsManagement.HideForm(3005);

                if (!FormsManagement.getframe(3004).isVisible()) {
                    // 打开面板
                    FormsManagement.showForm (3004);
                }else{
                    ZhuFrame.getZhuJpanel().addPrompt2("#R注意#Y：请先完成抽奖，关闭后将无效！！");
                    return;
//                    FormsManagement.HideForm(3004);
                }
//                String url = Agreement.getAgreement().TaskNAgreement("Y10=6");
//                SendMessageUntil.toServer(url);
            }
            else {
                if (this.caozuo != 21 && this.caozuo != 22) {
                    return;
                }
                try {
                    String url = new DownLoadTxt().GetServerTxt("yueka.txt");
                    if (StringUtil.isNullOrEmpty(url)) {
                        ZhuFrame.getZhuJpanel().addPrompt2("暂未开放");
                        return;
                    }
                    StringBuffer buffer = new StringBuffer();
                    buffer.append(url);
                    Desktop desktop = Desktop.getDesktop();
                    URI uri = new URI(buffer.toString());
                    desktop.browse(uri);
                }
                catch (Exception var13) {
                    var13.printStackTrace();
                }
            }
        }
        else if (this.caozuo == 23) {//月卡购买介绍
            OptionsJframe.getOptionsJframe().getOptionsJpanel().showBox(TiShiUtil.YUE, e, "#Y确定要花58积分开通月卡吗？？？");
        }
        else if (this.caozuo == 24) {//月卡购买介绍
            OptionsJframe.getOptionsJframe().getOptionsJpanel().showBox(TiShiUtil.JIII, e, "#Y确定要花150积分开通季卡吗？？？");
        }
        else if (this.caozuo == 25) {
            if (!FormsManagement.getframe(3004).isVisible()) {

            }else{
                ZhuFrame.getZhuJpanel().addPrompt2("请先完成月卡抽奖后再点击");
                return;
            }
            FormsManagement.HideForm(3004);

            if (!FormsManagement.getframe(3005).isVisible()) {
                // 打开面板
                FormsManagement.showForm (3005);
            }else{
                ZhuFrame.getZhuJpanel().addPrompt2("#R注意#Y：请先完成抽奖，关闭后将无效！！");

            }
        }
    }
}
