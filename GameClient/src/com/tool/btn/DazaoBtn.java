package com.tool.btn;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Map;

import org.come.Frame.NewRefiningJframe;
import org.come.bean.ConfigureBean;
import org.come.model.Configure;
import org.come.until.*;
import org.come.Jpanel.FrameMessageChangeJpanel;
import java.util.List;
import org.come.entity.Goodstable;
import java.math.BigDecimal;
import org.come.bean.SuitOperBean;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.bean.NpcComposeBean;
import java.util.ArrayList;

import com.tool.role.RoleData;
import org.come.mouslisten.TransmuteMouslisten;
import org.come.Frame.ZhuFrame;
import org.come.Jpanel.NewRefiningJpanel;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
import org.come.Jpanel.RuneOperateJpanel;
import org.come.Jpanel.ForgeJpanel;

public class DazaoBtn extends MoBanBtn
{
    private ForgeJpanel forgeJpanel;
    private RuneOperateJpanel runeOperateJpanel;
    
    public DazaoBtn(String iconpath, int type, Color[] colors, String text, Font font, ForgeJpanel forgeJpanel) {
        super(iconpath, type, colors);
        this.setText(text);
        this.setFont(font);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.forgeJpanel = forgeJpanel;
    }
    
    public DazaoBtn(String iconpath, int type, Color[] colors, Font font, String text, RuneOperateJpanel runeOperateJpanel) {
        super(iconpath, type, colors);
        this.runeOperateJpanel = runeOperateJpanel;
        this.setText(text);
        this.setFont(font);
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
//        FormsManagement.HideForm(11);

        if (NewRefiningJpanel.isLH) {
            ZhuFrame.getZhuJpanel().addPrompt("炼化中无法打开其他操作");
            return;
        }
        if (!Util.isCanBuyOrno()) {
            String type = null;
            BigDecimal money = null;
            Goodstable[] goods = null;
            if (this.forgeJpanel != null) {
                type = this.forgeJpanel.getType();
                money = this.forgeJpanel.getMoney1();
                goods = TransmuteMouslisten.goods2;
            } else if (this.runeOperateJpanel != null && this.runeOperateJpanel.getRuneType() == 1) {
                type = "我要洗练符石";
                money = this.runeOperateJpanel.getMoney();
                goods = TransmuteMouslisten.goods3;
            }
            else {
                if (this.runeOperateJpanel == null || this.runeOperateJpanel.getRuneType() != 2) {
                    return;
                }
                type = "我要合成符石";
                money = this.runeOperateJpanel.getMoney();
                goods = TransmuteMouslisten.goods4;
            }

            // 单独处理玄印合成逻辑
            if (type.equals("我要合成玄印")) {
                // 检查是否有足够的物品
                if (goods[0] == null || goods[1] == null) {
                    ZhuFrame.getZhuJpanel().addPrompt2("请凑齐物品再来");
                    return;
                }

                if (goods[0].getRgid() == goods[1].getRgid()) {
                    ZhuFrame.getZhuJpanel().addPrompt("不能合成相同物品");
                    return;
                }
                for (int i = 0; i < goods.length; i++) {
                    if (goods[i].getType().longValue() != 10018L && goods[i].getType().longValue() != 10012L && goods[i].getType().longValue() != 10013L && goods[i].getType().longValue() != 10014L){
                        return;
                    }
                }
                int Max = 0;
                if (goods.length == 2) {
                    if (!Objects.equals(goods[0].getGoodsid(), goods[1].getGoodsid())) {
                        ZhuFrame.getZhuJpanel().addPrompt2("请选择相同属性的玄印");
                        return;
                    }
                    int ore_1 = Numerical(Goodtype.StringParsing(goods[0].getValue())[0]);
                    int ore_2 = Numerical(Goodtype.StringParsing(goods[1].getValue())[0]);
                    if (Math.abs(ore_1 - ore_2) > 1) {
                        ZhuFrame.getZhuJpanel().addPrompt2("玄印等级差过大，无法合成");
                        return;
                    }
                    Max = Math.max(ore_1, ore_2);
                    if (Max < 10) {
                        if (Max != 1 && Math.abs(ore_1 - ore_2) != 0) {
                            ZhuFrame.getZhuJpanel().addPrompt2("玄印等级差过大，无法合成");
                            return;
                        }
                    } else if (ore_1 != ore_2) {
                        ZhuFrame.getZhuJpanel().addPrompt2("10级玄印以上必须相同等级玄印才可以合成");
                        return;
                    }
                    if (Max == 15) {
                        ZhuFrame.getZhuJpanel().addPrompt2("15级玄印无法合成");
                        return;
                    }
                }
                if (Max == 0){
                    return;
                }

                if (RoleData.getRoleData().getLoginResult().getGold().longValue() < money.longValue()) {
                    ZhuFrame.getZhuJpanel().addPrompt2("金钱不足");
                    return;
                }

                UserData.uptael(money.intValue());
                List<BigDecimal> goodstables = new ArrayList<>();
                for (int j = 0; j < goods.length; j++) {
                    if (goods[j] != null) {
                        if (j == 0) {
                            goods[j].setUsetime(Integer.valueOf(0));
                        } else {
                            goods[j].setUsetime(Integer.valueOf(goods[j].getUsetime().intValue() - 1));
                        }
                        goodstables.add(goods[j].getRgid());
                        if (goods[j].getUsetime().intValue() <= 0) {
                            GoodsListFromServerUntil.Deletebiaoid(goods[j].getRgid());
                            goods[j] = null;
                            qkth(type, j);
                        }
                    }
                }
                NpcComposeBean npcComposeBean = new NpcComposeBean();
                npcComposeBean.setComposetype(type);
                npcComposeBean.setGoodstables(goodstables);
                String senmes = Agreement.getAgreement().npccomposeAgreement(GsonUtil.getGsonUtil().getgson().toJson(npcComposeBean));
                SendMessageUntil.toServer(senmes);
                return; // 处理完玄印合成后直接返回，避免继续执行下面的通用逻辑
            }

            if (RoleData.getRoleData().getLoginResult().getGold().longValue() < money.longValue()) {
                ZhuFrame.getZhuJpanel().addPrompt2("金钱不足");
            }
            else {
                int i = 0;
                while (i < goods.length) {
                    if (goods[i] == null) {
                        ZhuFrame.getZhuJpanel().addPrompt2("请凑齐物品再来");
                        return;
                    }
                    if (goods[i].getGoodlock() == 1) {
                        ZhuFrame.getZhuJpanel().addPrompt2("该物品已被加锁");
                        return;
                    }
                    if (type.equals("我要分解仙器")) {
                        break;
                    }
                    else {
                        if (GoodsListFromServerUntil.isExist(goods[i])) {
                            return;
                        }
                        if (Goodtype.EquipmentType((long)goods[i].getType()) != -1) {
                            if (AccessSuitMsgUntil.getExtra(goods[i].getValue(), BaptizeBtn.Extras[3]) != null) {
                                ZhuFrame.getZhuJpanel().addPrompt2("#W套装无法用于打造系列");
                                return;
                            }
                            if (AccessSuitMsgUntil.getExtra(goods[i].getValue(), BaptizeBtn.Extras[4]) != null || (goods[i].getQhv() != null && (int)goods[i].getQhv() > 0)) {
                                ZhuFrame.getZhuJpanel().addPrompt2("#W已镶嵌宝石无法用于打造系列");
                                return;
                            }
                        }
                        ++i;
                    }
                }
                if (!this.type(type, goods)) {
                    if (!type.equals("我要升级神兵") && !type.equals("精炼神兵")) {
                        UserData.uptael((long)money.intValue());
                        List<BigDecimal> goodstables = new ArrayList<>();
                        for (int j = 0; j < goods.length; ++j) {
                            if (goods[j] != null) {
                                if (j == 0) {
                                    goods[j].setUsetime(Integer.valueOf(0));
                                }
                                else {
                                    goods[j].setUsetime(Integer.valueOf((int)goods[j].getUsetime() - 1));
                                }
                                goodstables.add(goods[j].getRgid());
                                if ((int)goods[j].getUsetime() <= 0) {
                                    GoodsListFromServerUntil.Deletebiaoid(goods[j].getRgid());
                                    goods[j] = null;
                                    this.qkth(type, j);
                                }
                            }
                        }
                        NpcComposeBean npcComposeBean = new NpcComposeBean();
                        npcComposeBean.setComposetype(type);
                        npcComposeBean.setGoodstables(goodstables);
                        String senmes = Agreement.getAgreement().npccomposeAgreement(GsonUtil.getGsonUtil().getgson().toJson(npcComposeBean));
                        SendMessageUntil.toServer(senmes);
                    }
                    else {
                        RoleData.getRoleData().getLoginResult().setGold(RoleData.getRoleData().getLoginResult().getGold().subtract(money));
                        SuitOperBean operBean = new SuitOperBean();
                        operBean.setType(16);
                        List<BigDecimal> rgids = new ArrayList<>();
                        for (int k = 0; k < goods.length; ++k) {
                            if (k == 0) {
                                goods[k].setUsetime(Integer.valueOf(0));
                            }
                            else {
                                goods[k].setUsetime(Integer.valueOf((int)goods[k].getUsetime() - 1));
                            }
                            rgids.add(goods[k].getRgid());
                            if ((int)goods[k].getUsetime() <= 0) {
                                GoodsListFromServerUntil.Deletebiaoid(goods[k].getRgid());
                                goods[k] = null;
                                this.qkth(type, k);
                            }
                        }
                        operBean.setGoods(rgids);
                        String senmes = Agreement.suitOperateAgreement(GsonUtil.getGsonUtil().getgson().toJson(operBean));
                        SendMessageUntil.toServer(senmes);
                    }
                }
            }
        }
    }
    
    public void qkth(String type, int i) {
        if (this.forgeJpanel != null) {
            this.forgeJpanel.getGoodsListLabel()[i + 24].setIcon(null);
        }
        else if (this.runeOperateJpanel != null && this.runeOperateJpanel.getRuneType() == 1) {
            this.runeOperateJpanel.getGoodsListLabel()[i + 24].setIcon(null);
        }
        else if (this.runeOperateJpanel != null && this.runeOperateJpanel.getRuneType() == 2) {
            this.runeOperateJpanel.getGoodsListLabel()[i + 26].setIcon(null);
        }
    }
    
    public boolean type(String type, Goodstable[] goods) {
        if (type != null) {
            if (type.equals("我要合成仙器")) {
                return this.GoodItem_1(goods);
            }
            if (type.equals("我要升级仙器")) {
                return this.GoodItem_2(goods);
            }
            if (type.equals("洗炼仙器-超级悔梦")) {
                return this.GoodItem_3(goods);
            }
            if (type.equals("我要分解仙器")) {
                return this.GoodItem_3(goods);
            }
            if (type.equals("改变仙器模型")) {
                return this.GoodItem_34(goods);
            }
            if (type.equals("重铸仙器-悔梦石")) {
                return this.GoodItem_33(goods);
            }
            if (type.equals("打造11-16级装备")) {
                return this.GoodItem_4(goods);
            }
            if (type.equals("我要打造普通装备")) {
                return this.GoodItem_5(goods);
            }
            if (type.equals("我要升级神兵")) {
                return this.GoodItem_6(goods);
            }
            if (type.equals("我要合成炼妖石")) {
                return this.GoodItem_7(goods);
            }
            if (type.equals("我要炼器")) {
                return this.GoodItem_8(goods);
            }
            if (type.equals("我要培养饰品")) {
                return this.GoodItem_9(goods);
            }
            if (type.equals("我要解封神饰")) {
                return this.GoodItem_35(goods);
            }
            if (type.equals("我要重铸饰品")) {
                return this.GoodItem_10(goods);
            }
            if (type.equals("我要合成符石")) {
                return this.GoodItem_11(goods);
            }
            if (type.equals("我要洗练符石")) {
                return this.GoodItem_12(goods);
            }
            if (type.equals("炼化装备")) {
                return this.GoodItem_13(goods);
            }
            if (type.equals("我要上神兵石") || type.equals("炼化神兵")) {
                return this.GoodItem_14(goods);
            }
            if (type.equals("我要培养护身符")) {
                return this.GoodItem_15(goods);
            }
            if (type.equals("我要重铸护身符")) {
                return this.GoodItem_16(goods);
            }
            if (type.equals("炼化仙器")) {
                return this.GoodItem_17(goods);
            }
            if (type.equals("培养彩晶石")) {
                return this.GoodItem_18(goods);
            }
            if (type.equals("精炼神兵")) {
                return this.GoodItem_19(goods);
            }
            if (type.equals("普通打造")) {
                return this.GoodItem_4(goods);
            }
        }
        return true;
    }
    
    public boolean GoodItem_1(Goodstable[] goods) {
        String Bottletext = goods[0].getValue();
        String[] gongneng = null;
        if (Bottletext != null && !Bottletext.equals("")) {
            gongneng = Goodtype.StringParsing(Bottletext);
        }
        String god = Goodtype.StringParsing(goods[1].getValue())[0];
        if (gongneng == null) {
            if (god.equals("阶数=6")) {
                FrameMessageChangeJpanel.addtext(6, "6阶打进瓶子???哎!还是卖太便宜了", null, null);
                return true;
            }
        }
        else {
            if (!gongneng[0].equals(god)) {
                FrameMessageChangeJpanel.addtext(6, "阶数不相等", null, null);
                return true;
            }
            if (this.ReikiFull(gongneng)) {
                FrameMessageChangeJpanel.addtext(6, "灵气已经满了", null, null);
                return true;
            }
            if (AnalysisString.jiaoyi((long)goods[0].getQuality()) != AnalysisString.jiaoyi((long)goods[1].getQuality())) {
                FrameMessageChangeJpanel.addtext(6, "绑定和不绑定不能混合", null, null);
                return true;
            }
        }
        return false;
    }
    
    public boolean GoodItem_2222(Goodstable[] goods) {
        String Bottletext = goods[0].getValue();
        String[] gongneng = Goodtype.StringParsing(Bottletext);
        int kslvl = goods[1].getGoodsid().intValue() - 300;
        if (gongneng.equals("")) {
            FrameMessageChangeJpanel.addtext(6, "瓶子没有灵气", null, null);
            return true;
        }
        if (!this.ReikiFull(gongneng)) {
            FrameMessageChangeJpanel.addtext(6, "瓶子灵气未满！", null, null);
            return true;
        }
        int lvl = Integer.parseInt(gongneng[0].split("=")[1]);
        if (lvl + 5 != kslvl) {
            FrameMessageChangeJpanel.addtext(6, this.zw(lvl) + "阶仙器请用" + (lvl + 5) + "级矿石升级!", null, null);
            return true;
        }
        if (lvl >= 6) {
            FrameMessageChangeJpanel.addtext(6, "不支持六阶仙器升级!", null, null);
            return true;
        }
        ++lvl;
        return false;
    }
    
    public boolean GoodItem_2(Goodstable[] goods) {
        String Bottletext = goods[0].getValue();
        String[] gongneng = Goodtype.StringParsing(Bottletext);
        int kslvl = goods[1].getGoodsid().intValue() - 300;
        if (gongneng.equals("")) {
            FrameMessageChangeJpanel.addtext(6, "瓶子没有灵气", null, null);
            return true;
        }
        if (!this.ReikiFull(gongneng)) {
            FrameMessageChangeJpanel.addtext(6, "瓶子灵气未满！", null, null);
            return true;
        }
        int lvl = Integer.parseInt(gongneng[0].split("=")[1]);
        if (lvl + 5 != kslvl) {
            FrameMessageChangeJpanel.addtext(6, this.zw(lvl) + "阶仙器请用" + (lvl + 5) + "级矿石升级!", null, null);
            return true;
        }
        if (lvl >= 6) {
            FrameMessageChangeJpanel.addtext(6, "不支持六阶仙器升级!", null, null);
            return true;
        }
        ++lvl;
        return false;
    }
    
    public boolean GoodItem_3(Goodstable[] goods) {
        return false;
    }
    
    public boolean GoodItem_33(Goodstable[] goods) {
        return false;
    }
    
    public boolean GoodItem_34(Goodstable[] goods) {
        return false;
    }
    
    public boolean GoodItem_4(Goodstable[] goods) {
        int goodid = goods[0].getGoodsid().intValue();
        int zblvl = Integer.parseInt(goods[0].getValue().split("\\|")[0].split("=")[1]);
        int kslvl = Integer.parseInt(goods[1].getValue().split("=")[1]);
        if (zblvl < 10) {
            FrameMessageChangeJpanel.addtext(6, "打造1-10级装备去长安城打铁铺找冯铁匠!", null, null);
            return true;
        }
        boolean up = false;
        if (zblvl >= 10 && zblvl <= 13) {
            if (kslvl != 8 && kslvl != 9) {
                FrameMessageChangeJpanel.addtext(6, "打造11-14级装备使用补天神石!", null, null);
                FrameMessageChangeJpanel.addtext(6, "重铸10-13级装备使用盘古精铁!", null, null);
                return true;
            }
            if (kslvl == 9) {
                up = true;
            }
        }
        else if (zblvl == 14) {
            if (kslvl != 9 && kslvl != 10) {
                FrameMessageChangeJpanel.addtext(6, "打造15级装备使用六魂之玉!", null, null);
                FrameMessageChangeJpanel.addtext(6, "重铸14级装备使用补天神石!", null, null);
                return true;
            }
            if (kslvl == 10) {
                up = true;
            }
        }
        else if (zblvl == 15) {
            if (kslvl != 10 && kslvl != 11) {
                FrameMessageChangeJpanel.addtext(6, "打造16级装备使用无量琉璃!", null, null);
                FrameMessageChangeJpanel.addtext(6, "重铸15级装备使用六魂之玉!", null, null);
                return true;
            }
            if (kslvl == 11) {
                up = true;
            }
        }
        else {
            if (zblvl != 16) {
                FrameMessageChangeJpanel.addtext(6, "错误公式", null, null);
                return true;
            }
            if (kslvl != 11) {
                FrameMessageChangeJpanel.addtext(6, "重铸16级装备使用11级矿石!", null, null);
                return true;
            }
        }
        return false;
    }
    
    public boolean GoodItem_5(Goodstable[] goods) {
        int goodid = goods[0].getGoodsid().intValue();
        int zblvl = Integer.parseInt(goods[0].getValue().split("\\|")[0].split("=")[1]);
        int kslvl = Integer.parseInt(goods[1].getValue().split("=")[1]);
        if (zblvl >= 10) {
            FrameMessageChangeJpanel.addtext(6, "打造11-16级装备去长安桥头!", null, null);
            return true;
        }
        if (kslvl > 9) {
            FrameMessageChangeJpanel.addtext(6, "打造1-10级装备最高只能使用9级矿石!", null, null);
            return true;
        }
        return false;
    }
    
    public boolean GoodItem_6(Goodstable[] goods) {
        String Bottletext = goods[0].getValue();
        String[] gongneng = Goodtype.StringParsing(Bottletext);
        int godlvl = Numerical(gongneng[0]);
        int kslvl = Integer.parseInt(goods[1].getValue().split("=")[1]);
        if (godlvl < 1 || godlvl > 12) {
            FrameMessageChangeJpanel.addtext(6, "不支持13级神兵升级!", null, null);
            return true;
        }
        if (godlvl != kslvl - 5) {
            FrameMessageChangeJpanel.addtext(6, "请使用" + (godlvl + 5) + "级矿石进行升级!", null, null);
            return true;
        }
        return false;
    }
    
    public boolean GoodItem_7(Goodstable[] goods) {
        return false;
    }
    
    public boolean GoodItem_35(Goodstable[] goods) {
        return false;
    }
    
    public boolean GoodItem_8(Goodstable[] goods) {
        return true;
    }
    
    public boolean GoodItem_9(Goodstable[] goods) {
        String Bottletext = goods[0].getValue();
        String[] gongneng = Goodtype.StringParsing(Bottletext);
        int max = 0;
        int flvl = 0;
        for (int i = 0; i < gongneng.length; ++i) {
            if (gongneng[i].length() >= 2 && gongneng[i].startsWith("培养")) {
                String[] num = gongneng[i].split("=")[1].split("/");
                max = Integer.parseInt(num[1]);
            }
        }
        if (max == 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("该配饰无法培养");
            return true;
        }
        if (Goodtype.Accessories((long)goods[1].getType())) {
            String[] vs = goods[1].getValue().split("\\|");
            for (int j = 0; j < vs.length; ++j) {
                String[] v = vs[j].split("=");
                if (v[0].equals("等级")) {
                    flvl = Integer.parseInt(v[1]);
                }
            }
            if (flvl > 2) {
                ZhuFrame.getZhuJpanel().addPrompt2("无法用2级以上的配饰培养");
                return true;
            }
        }
        if (Numerical(gongneng[0]) <= 6) {
            return false;
        }
        FrameMessageChangeJpanel.addtext(6, "当前饰品无法升级!", null, null);
        return true;
    }
    
    public boolean GoodItem_10(Goodstable[] goods) {
        String Bottletext = goods[0].getValue();
        String[] gongneng = Goodtype.StringParsing(Bottletext);
        int kslvl = Integer.parseInt(goods[1].getValue().split("=")[1]);
        if (Numerical(gongneng[0]) == kslvl - 3) {
            return false;
        }
        if ((long)goods[0].getType() == 928L) {
            return true;
        }
        FrameMessageChangeJpanel.addtext(6, "请使用相应的矿石进行重铸!", null, null);
        return true;
    }
    
    public boolean GoodItem_11(Goodstable[] goods) {
        int lvl = Numerical(Goodtype.StringParsing(goods[0].getValue())[0]);
        if (lvl >= 5) {
            FrameMessageChangeJpanel.addtext(6, "不支持5级以上合成", null, null);
            return true;
        }
        for (int i = 1; i < goods.length; ++i) {
            if (lvl != Numerical(Goodtype.StringParsing(goods[i].getValue())[0])) {
                FrameMessageChangeJpanel.addtext(6, "合成物品需要同等级", null, null);
                return true;
            }
        }
        ++lvl;
        return false;
    }
    
    public boolean GoodItem_12(Goodstable[] goods) {
        int ore_1 = Numerical(Goodtype.StringParsing(goods[0].getValue())[0]);
        if (ore_1 == 1 || ore_1 == 2 || ore_1 == 3) {
            FrameMessageChangeJpanel.addtext(6, "1,2,3级符石不能洗练", null, null);
            return true;
        }
        if ((long)goods[1].getType() == 188L) {
            int ore_2 = Numerical(Goodtype.StringParsing(goods[1].getValue())[0]);
            if (ore_1 - 3 != ore_2) {
                FrameMessageChangeJpanel.addtext(6, ore_1 + "级符石用" + (ore_1 - 3) + "符石洗练", null, null);
                return true;
            }
        }
        return false;
    }
    
    private boolean GoodItem_13(Goodstable[] goods) {
        return false;
    }
    
    private boolean GoodItem_14(Goodstable[] goods) {
        if (this.forgeJpanel.getType().equals("我要上神兵石")) {
            String value = goods[0].getValue();
            if (value.indexOf("神兵属性") != -1) {
                FrameMessageChangeJpanel.addtext(6, "该装备已经有上了神兵石了", null, null);
                return true;
            }
        }
        return false;
    }
    
    private boolean GoodItem_15(Goodstable[] goods) {
        String[] vs = goods[0].getValue().split("\\|");
        int pz = 0;
        int i = 0;
        while (i < vs.length) {
            String[] vsz = vs[i].split("=");
            if (vsz[0].equals("品质")) {
                pz = Integer.parseInt(vsz[1].split("/")[0]);
                break;
            }
            else {
                ++i;
            }
        }
        int up = 800;
        String extra = AccessSuitMsgUntil.getExtra(goods[0].getValue(), "炼化属性");
        LOOP: {
            if (extra != null) {
                String[] vvs = extra.split("&");
                for (int j = 0; j < vvs.length; ++j) {
                    String[] vvvs = vvs[j].split("=");
                    if (vvvs[0].equals("特技")) {
                        int k = 1;
                        while (k < vvvs.length) {
                            if (vvvs[k].equals("8031")) {
                                up = 900;
                                break LOOP;
                            }
                            else {
                                ++k;
                            }
                        }
                    }
                }
            }
        }
        if (pz > up) {
            FrameMessageChangeJpanel.addtext(6, "该护身符品质大于" + up + "后无法培养", null, null);
            return true;
        }
        return false;
    }
    
    private boolean GoodItem_16(Goodstable[] goods) {
        String[] vs = goods[0].getValue().split("\\|");
        int pz = 0;
        int kslvl = 0;
        while (kslvl < vs.length) {
            String[] vsz = vs[kslvl].split("=");
            if (vsz[0].equals("品质")) {
                pz = Integer.parseInt(vsz[1].split("/")[0]);
                break;
            }
            else {
                ++kslvl;
            }
        }
        if (pz < 300) {
            FrameMessageChangeJpanel.addtext(6, "该护身符品质低于300的需要培养", null, null);
            return true;
        }
        kslvl = Integer.parseInt(goods[1].getValue().split("=")[1]);
        if (kslvl != 9 && kslvl != 10) {
            FrameMessageChangeJpanel.addtext(6, "护身符重铸使用9级矿石!", null, null);
            FrameMessageChangeJpanel.addtext(6, "护身符升级使用10级矿石!", null, null);
            return true;
        }
        int lvl = Integer.parseInt(goods[0].getValue().split("\\|")[0].split("=")[1]);
        if (kslvl == 10 && ++lvl > 9) {
            FrameMessageChangeJpanel.addtext(6, "护身符等级最高为9级", null, null);
            return true;
        }
        return false;
    }
    
    private boolean GoodItem_17(Goodstable[] goods) {
        if ((long)goods[1].getType() == 7005L) {
            String god = Goodtype.StringParsing(goods[1].getValue())[1];
            if (!god.equals("阶数=1")) {
                FrameMessageChangeJpanel.addtext(6, "使用一阶作为炼化材料太掉价了吗?", null, null);
                return true;
            }
        }
        else {
            String god = Goodtype.StringParsing(goods[1].getValue())[0];
            if (!god.equals("阶数=1")) {
                FrameMessageChangeJpanel.addtext(6, "使用一阶作为炼化材料太掉价了吗?", null, null);
                return true;
            }
        }
        return false;
    }
    
    private boolean GoodItem_18(Goodstable[] goods) {
        return false;
    }
    
    private boolean GoodItem_19(Goodstable[] goods) {
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure)item.get(new BigDecimal(1));
        String Bottletext = goods[0].getValue();
        String[] gongneng = Goodtype.StringParsing(Bottletext);
        int godlvl = Numerical(gongneng[0]);
        if (godlvl < 1 || godlvl >= Integer.parseInt(configure.getSbzddj())) {
            FrameMessageChangeJpanel.addtext(6, "暂不支持#R" + Integer.parseInt(configure.getSbzddj()) + "级#W神兵升级!", null, null);
            return true;
        }
        int godlvl2 = Numerical(goods[1].getValue().split("\\|")[0]);
        if (godlvl2 > 3 && goods[1].getGoodsid().longValue() != 80042L) {
            FrameMessageChangeJpanel.addtext(6, "用3级以下的神兵精练", null, null);
            return true;
        }
        return false;
    }
    
    public int zblvl(int id) {
        return (id - 1001) % 16 + 1;
    }
    
    public boolean ReikiFull(String[] vlaue) {
        if (vlaue[0].equals("阶数=1") || vlaue[0].equals("阶数=2")) {
            if (this.Reikisum(vlaue[1]) >= 8) {
                return true;
            }
        }
        else if (vlaue[0].equals("阶数=3")) {
            if (this.Reikisum(vlaue[1]) >= 6) {
                return true;
            }
        }
        else if (vlaue[0].equals("阶数=4")) {
            if (this.Reikisum(vlaue[1]) >= 5) {
                return true;
            }
        }
        else if (this.Reikisum(vlaue[1]) >= 3) {
            return true;
        }
        return false;
    }
    
    public String zw(int lvl) {
        switch (lvl) {
            case 1: {
                return "一";
            }
            case 2: {
                return "二";
            }
            case 3: {
                return "三";
            }
            case 4: {
                return "四";
            }
            case 5: {
                return "五";
            }
            case 6: {
                return "六";
            }
            case 7: {
                return "七";
            }
            case 8: {
                return "八";
            }
            case 9: {
                return "九";
            }
            case 10: {
                return "十";
            }
            default: {
                return "零";
            }
        }
    }
    
    public static int Numerical(String vlaue) {
        return (vlaue.split("\\=").length == 1) ? 0 : Integer.parseInt(vlaue.split("\\=")[1]);
    }
    
    public int Reikisum(String vlaue) {
        Pattern pattern = Pattern.compile("=(.*?)点");
        Matcher m = pattern.matcher(vlaue);
        if (m.find()) {
            int i = 1;
            return Integer.parseInt(m.group(1));
        }
        return 0;
    }
}
