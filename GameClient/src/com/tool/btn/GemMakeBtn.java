package com.tool.btn;

import java.util.List;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.until.GsonUtil;
import org.come.bean.SuitOperBean;
import org.come.Jpanel.GameJpanel;
import com.tool.role.RoleData;
import java.math.BigDecimal;
import org.come.until.GoodsListFromServerUntil;
import org.come.Frame.ZhuFrame;
import java.util.ArrayList;
import org.come.until.Goodtype;
import come.tool.JDialog.TiShiUtil;
import org.come.Frame.OptionsJframe;
import org.come.entity.Goodstable;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
import com.tool.tcpimg.UIUtils;
import org.come.Jpanel.GemMakeJpanel;

public class GemMakeBtn extends MoBanBtn
{
    private GemMakeJpanel makeJpanel;
    private int p;
    private Integer b;
    
    public GemMakeBtn(String iconpath, int type, int p, GemMakeJpanel makeJpanel) {
        super(iconpath, type);
        this.p = p;
        this.makeJpanel = makeJpanel;
        if (p == 2) {
            this.b = Integer.valueOf(-1);
        }
        if (this.p == 8) {
            this.setText("打造");
            this.setFont(UIUtils.TEXT_FONT1);
            this.setForeground(Color.yellow);
            this.setVerticalTextPosition(0);
            this.setHorizontalTextPosition(0);
        }
        if (this.p >= 9 && this.p <= 11) {
            this.setText("可打造");
            this.setFont(UIUtils.TEXT_FONT);
            this.setVerticalTextPosition(0);
            this.setHorizontalTextPosition(0);
            this.setForeground(Color.GRAY);
        }
    }
    
    public GemMakeBtn(String iconpath, int type, Color[] colors, String text, Font font, int p, GemMakeJpanel makeJpanel) {
        super(iconpath, type, colors);
        this.p = p;
        this.makeJpanel = makeJpanel;
        if (p == 2) {
            this.b = Integer.valueOf(-1);
        }
        this.setText(text);
        this.setFont(font);
        this.setHorizontalTextPosition(0);
        this.setVerticalTextPosition(0);
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        if (this.p == 0 || this.p == 1) {
            this.makeJpanel.qh(this.p);
        }
        else if (this.p >= 2 && this.p <= 7) {
            int v = this.makeJpanel.option(this.p);
            if (v != -2) {
                this.makeJpanel.chang(v);
            }
        }
        else if (this.p == 8) {
            Goodstable good1 = this.makeJpanel.getGood(0);
            Goodstable good2 = this.makeJpanel.getGood(1);
            if (good1 == null) {
                return;
            }
            if (good2 == null) {
                return;
            }
            GemXXX(true, new Goodstable[] { good1, good2 });
        }
        else if (this.p >= 9 && this.p <= 11) {
            Goodstable good1 = this.makeJpanel.getGood(0);
            Goodstable good2 = this.makeJpanel.getGood(this.p - 7);
            if (good1 == null) {
                return;
            }
            if (good2 == null) {
                return;
            }
            StringBuffer buffer = new StringBuffer();
            buffer.append("#Y你确定花费#R ");
            buffer.append(Integer.parseInt(good2.getValue().split("\\|")[0].split("=")[1]) * 3200000);
            buffer.append(" #Y金钱拆卸该宝石吗?");
            OptionsJframe.getOptionsJframe().getOptionsJpanel().showBox(TiShiUtil.GemOff, new Goodstable[] { good1, good2 }, buffer.toString());
        }
        if (this.p == 20) {
            this.makeJpanel.lingFan(false);
        }
        if (this.p == 21) {
            this.makeJpanel.lingFan(true);
        }
    }
    
    public static void GemXXX(boolean l, Goodstable... goods) {
        if (goods.length != 2) {
            return;
        }
        int lvl = 0;
        if (Goodtype.baoshi((long)goods[1].getType())) {
            String[] vs = goods[1].getValue().split("\\|");
            lvl = Integer.parseInt(vs[0].split("=")[1]);
            List<BigDecimal> rgids = new ArrayList<>();
            for (int i = 0; i < goods.length; ++i) {
                if (goods[i].getGoodlock() == 1) {
                    ZhuFrame.getZhuJpanel().addPrompt("该物品已被加锁");
                    return;
                }
                if (i != 0 && (((int)goods[i].getStatus() != 0 && l) || ((int)goods[i].getStatus() == 0 && !l))) {
                    return;
                }
                if ((int)goods[i].getStatus() == 0) {
                    if (GoodsListFromServerUntil.isExist(goods[i])) {
                        return;
                    }
                }
                else if (GoodsListFromServerUntil.ischoseExist(goods[i])) {
                    return;
                }
                rgids.add(goods[i].getRgid());
            }
            BigDecimal money = null;
            if (l) {
                money = new BigDecimal(5000000 + lvl * 1000000);
                if (RoleData.getRoleData().getLoginResult().getGold().longValue() < money.longValue()) {
                    GameJpanel.getGameJpanel().addPrompt("#Y金钱不足");
                    return;
                }
                List<Integer> integers = null;
                int[] goodids = new int[(lvl <= 4) ? 1 : (lvl - 3)];
                for (int j = 0; j < goodids.length; ++j) {
                    goodids[j] = 81095;
                }
                integers = GoodsListFromServerUntil.chaxuns(goodids);
                if (integers == null) {
                    ZhuFrame.getZhuJpanel().addPrompt("宝石精华不足");
                    return;
                }

                if (Goodtype.EquipmentType(goods[0].getType())==5){
                    if (goods[1].getType()!=752
                            &&goods[1].getType()!=758
                    ) {
                        ZhuFrame.getZhuJpanel() .addPrompt2("此宝石不能用于打造这个装备");
                        return;
                    }
                }
                if (Goodtype.EquipmentType(goods[0].getType())==3){
                    if (goods[1].getType()!=761
                            &&goods[1].getType()!=764
                    ) {
                        ZhuFrame.getZhuJpanel() .addPrompt2("此宝石不能用于打造这个装备");
                        return;
                    }
                }
                if (Goodtype.EquipmentType(goods[0].getType())==2){
                    if (goods[1].getType()!=764
                            &&goods[1].getType()!=749
                    ) {
                        ZhuFrame.getZhuJpanel() .addPrompt2("此宝石不能用于打造这个装备");
                        return;
                    }
                }
                if (Goodtype.EquipmentType(goods[0].getType())==1){
                    if (goods[1].getType()!=761
                            &&goods[1].getType()!=749
                    ) {
                        ZhuFrame.getZhuJpanel() .addPrompt2("此宝石不能用于打造这个装备");
                        return;
                    }
                }
                if (Goodtype.EquipmentType(goods[0].getType())==0){
                    if (goods[1].getType()!=746
                            &&goods[1].getType()!=755
                            &&goods[1].getType()!=767
                    ) {
                        ZhuFrame.getZhuJpanel() .addPrompt2("此宝石不能用于打造这个装备");
                        return;
                    }
                }

                for (int j = 0; j < integers.size(); ++j) {
                    Goodstable good = GoodsListFromServerUntil.getGoodslist()[(int)integers.get(j)];
                    if (good != null) {
                        good.goodxh(1);
                        if ((int)good.getUsetime() <= 0) {
                            GoodsListFromServerUntil.Deletebiaoid(good.getRgid());
                        }
                        rgids.add(good.getRgid());
                    }
                }
                goods[1].setStatus(Integer.valueOf(1));
                GoodsListFromServerUntil.fushis.put(goods[1].getRgid(), goods[1]);
                GoodsListFromServerUntil.Deletebiaoid(goods[1].getRgid());
            }
            else {
                money = new BigDecimal(3200000 * lvl);
                if (RoleData.getRoleData().getLoginResult().getGold().longValue() < money.longValue()) {
                    GameJpanel.getGameJpanel().addPrompt("#Y金钱不足");
                    return;
                }
                if (!GoodsListFromServerUntil.newgood(goods[1])) {
                    return;
                }
                GoodsListFromServerUntil.fushis.remove(goods[1].getRgid());
            }
            RoleData.getRoleData().getLoginResult().setGold(RoleData.getRoleData().getLoginResult().getGold().subtract(money));
            SuitOperBean operBean = new SuitOperBean();
            operBean.setType(20);
            operBean.setGoods(rgids);
            String senmes = Agreement.suitOperateAgreement(GsonUtil.getGsonUtil().getgson().toJson(operBean));
            SendMessageUntil.toServer(senmes);
            return;
        }
        else {
            return;
        }
    }
    
    public Integer getB() {
        return this.b;
    }
    
    public void setB(Integer b) {
        this.b = b;
    }
}
