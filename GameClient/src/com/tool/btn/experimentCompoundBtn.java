package com.tool.btn;

import org.come.entity.Goodstable;
import org.come.model.ItemExchange;
import java.util.Map;
import org.come.bean.ConfigureBean;
import org.come.socket.SendMessageUntil;
import org.come.until.GsonUtil;
import org.come.socket.Agreement;
import org.come.bean.itemBean;
import org.come.until.GoodsListFromServerUntil;
import com.tool.role.RoleData;
import org.come.Frame.ZhuFrame;
import java.math.BigDecimal;
import org.come.model.Configure;
import org.come.until.UserMessUntil;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
import org.come.Jpanel.experimentCompoundJpanel;

public class experimentCompoundBtn extends MoBanBtn
{
    private int caozuo;
    private experimentCompoundJpanel compoundJpanel;
    
    public experimentCompoundBtn(String iconpath, int type, Color[] colors, String text, Font font, int caozuo, experimentCompoundJpanel compoundJpanel) {
        super(iconpath, type, colors);
        this.setText(text);
        this.setFont(font);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.caozuo = caozuo;
        this.compoundJpanel = compoundJpanel;
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        if (this.caozuo == 1) {
            ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
            Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
            Configure configure = (Configure)item.get(new BigDecimal(1));
            ItemExchange ItemExchange = this.compoundJpanel.getItemExchange();
            if (ItemExchange == null) {
                ZhuFrame.getZhuJpanel().addPrompt2("没有选中装备");
                return;
            }
            if (RoleData.getRoleData().getLoginResult().getGold().compareTo(this.compoundJpanel.getMoney()) < 0) {
                ZhuFrame.getZhuJpanel().addPrompt2("金额不足");
                return;
            }
            String consume = ItemExchange.getConsume();
            if (consume != null && !"".equals(consume)) {
                String[] split = consume.split("\\|");
                for (int i = 0; i < split.length; ++i) {
                    if (split[i].startsWith("G")) {
                        String[] arrMoney = split[i].split("=");
                        Goodstable goodstable = UserMessUntil.getgoodstable(new BigDecimal(arrMoney[1]));
                        int needMum = Integer.parseInt(arrMoney[2]);
                        int goodNum = GoodsListFromServerUntil.getGoodNum(goodstable.getGoodsid());
                        if (needMum > goodNum) {
                            ZhuFrame.getZhuJpanel().addPrompt2("合成材料不足");
                            return;
                        }
                    }
                }
            }
            itemBean itemBean = new itemBean();
            itemBean.setOpertype(100);
            itemBean.setzhuangbeiid(new BigDecimal(ItemExchange.geteId()));
            System.out.println("ItemExchange的id是" + ItemExchange.geteId());
            String mes = Agreement.getAgreement().itemAgreement(GsonUtil.getGsonUtil().getgson().toJson(itemBean));
            System.out.println("json是" + GsonUtil.getGsonUtil().getgson().toJson(itemBean));
            System.out.println("服务器要收到信息了,信息是" + mes);
            SendMessageUntil.toServer(mes);
        }
        else if (this.caozuo == 2) {
            ItemExchange ItemExchange2 = this.compoundJpanel.getItemExchange();
            if (ItemExchange2 == null) {
                ZhuFrame.getZhuJpanel().addPrompt2("没有选中兑换物品");
                return;
            }
            if (RoleData.getRoleData().getLoginResult().getGold().compareTo(this.compoundJpanel.getMoney()) < 0) {
                ZhuFrame.getZhuJpanel().addPrompt2("金额不足");
                return;
            }
            String consume2 = ItemExchange2.getConsume();
            if (consume2 != null && !"".equals(consume2)) {
                String[] split2 = consume2.split("\\|");
                for (int j = 0; j < split2.length; ++j) {
                    if (split2[j].startsWith("G")) {
                        String[] arrMoney2 = split2[j].split("=");
                        Goodstable goodstable2 = UserMessUntil.getgoodstable(new BigDecimal(arrMoney2[1]));
                        int needMum2 = Integer.parseInt(arrMoney2[2]);
                        int goodNum2 = GoodsListFromServerUntil.getGoodNum(goodstable2.getGoodsid());
                        if (needMum2 > goodNum2) {
                            ZhuFrame.getZhuJpanel().addPrompt2("合成材料不足");
                            return;
                        }
                    }
                }
            }
            itemBean itemBean2 = new itemBean();
            itemBean2.setOpertype(101);
            itemBean2.setzhuangbeiid(new BigDecimal(ItemExchange2.geteId()));
            String mes2 = Agreement.getAgreement().itemAgreement(GsonUtil.getGsonUtil().getgson().toJson(itemBean2));
            SendMessageUntil.toServer(mes2);
        }
    }
}
