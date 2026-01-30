package com.tool.btn;

import org.come.bean.WitchComposeBean;
import org.come.until.Util;
import org.come.until.UserData;
import org.come.bean.NpcComposeBean;
import org.come.model.LianHua;
import org.come.Frame.TaobaoCourtMainJframe;
import org.come.Frame.GoodDetailedJframe;
import org.come.entity.PartJade;
import org.come.Jpanel.FrameMessageChangeJpanel;
import org.come.until.Goodtype;
import org.come.bean.SuitOperBean;
import org.come.until.UserMessUntil;
import org.come.Jpanel.GameJpanel;
import org.come.entity.Goodstable;
import java.util.List;
import java.math.BigDecimal;
import org.come.until.GoodsListFromServerUntil;
import java.util.ArrayList;
import com.tool.role.RoleData;
import org.come.Frame.ZhuFrame;
import org.come.Frame.NewRefiningJframe;
import org.come.until.AccessSuitMsgUntil;
import org.come.starcard.JframeStarCardMain;
import org.come.Frame.DdianJframe;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.until.GsonUtil;
import org.come.Frame.DianQJframe;
import org.come.until.FormsManagement;
import org.come.until.RefiningUtil;
import org.come.Frame.WorkshopRefiningJframe;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
import org.come.Jpanel.TestSetupJpanel;
import org.come.Jpanel.RefiningEquiJpanel;
import org.come.Jpanel.RefinersJpanel;
import org.come.Jpanel.NewRefiningJpanel;

public class RefineOperBtn extends MoBanBtn
{
    private NewRefiningJpanel NrJpanel;
    private RefinersJpanel rJpanel;
    private RefiningEquiJpanel eJpanel;
    private TestSetupJpanel tJpanel;
    private int cao;
    
    public RefineOperBtn(String iconpath, int type, Color[] colors, Font font, NewRefiningJpanel nrJpanel, int cao, String text) {
        super(iconpath, type, colors);
        this.setText(text);
        this.setFont(font);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.NrJpanel = nrJpanel;
        this.cao = cao;
    }
    
    public RefineOperBtn(String iconpath, int type, NewRefiningJpanel nrJpanel, int cao) {
        super(iconpath, type);
        this.NrJpanel = nrJpanel;
        this.cao = cao;
    }
    
    public RefineOperBtn(String iconpath, int type, Color[] colors, Font font, RefinersJpanel rJpanel, int cao, String text) {
        super(iconpath, type, colors);
        this.setText(text);
        this.setFont(font);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.rJpanel = rJpanel;
        this.cao = cao;
    }
    
    public RefineOperBtn(String iconpath, int type, Color[] colors, Font font, RefiningEquiJpanel eJpanel, int cao, String text) {
        super(iconpath, type, colors);
        this.setText(text);
        this.setFont(font);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.eJpanel = eJpanel;
        this.cao = cao;
    }
    
    public RefineOperBtn(String iconpath, int type, TestSetupJpanel testSetupJpanel, int cao) {
        super(iconpath, type);
        this.tJpanel = testSetupJpanel;
        this.cao = cao;
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        if (this.NrJpanel != null) {
            if (this.cao == 0) {
                RefiningEquiJpanel EJpanel = WorkshopRefiningJframe.getWorkshopRefiningJframe().getWorkshopRefiningJpanel().getCardJpanel().getEquiJpanel();
                String v = RefiningUtil.detection(EJpanel.goods, EJpanel.getType());
                if (v.equals("点粹")) {
                    FormsManagement.HideForm(127);
                    FormsManagement.HideForm(11);
                    NewRefiningJpanel.isLH = false;
                }
                else if (v.equals("点粹强化")) {
                    if (DianQJframe.getDdianQJframe().getDianJpanel().clBean != null) {
                        String senmes = Agreement.extrAttrOperAgreement(GsonUtil.getGsonUtil().getgson().toJson(DianQJframe.getDdianQJframe().getDianJpanel().clBean));
                        SendMessageUntil.toServer(senmes);
                        DianQJframe.getDdianQJframe().getDianJpanel().TH(DianQJframe.getDdianQJframe().getDianJpanel().clBean);
                    }
                    FormsManagement.HideForm(128);
                }
                else {
                    FormsManagement.HideForm(11);
                }
                NewRefiningJpanel.isLH = false;
            }
            else if (this.cao == 1) {
                RefiningEquiJpanel EJpanel = WorkshopRefiningJframe.getWorkshopRefiningJframe().getWorkshopRefiningJpanel().getCardJpanel().getEquiJpanel();
                if (DdianJframe.getDdianJframe().getDianJpanel().clBean != null && EJpanel.getType() == 2) {
                    String senmes2 = Agreement.extrAttrOperAgreement(GsonUtil.getGsonUtil().getgson().toJson(DdianJframe.getDdianJframe().getDianJpanel().clBean));
                    SendMessageUntil.toServer(senmes2);
                    DdianJframe.getDdianJframe().getDianJpanel().TH(DdianJframe.getDdianJframe().getDianJpanel().clBean);
                }
                else if (DianQJframe.getDdianQJframe().getDianJpanel().clBean != null) {
                    String senmes2 = Agreement.extrAttrOperAgreement(GsonUtil.getGsonUtil().getgson().toJson(DianQJframe.getDdianQJframe().getDianJpanel().clBean));
                    SendMessageUntil.toServer(senmes2);
                    DianQJframe.getDdianQJframe().getDianJpanel().TH(DianQJframe.getDdianQJframe().getDianJpanel().clBean);
                }
                else if (this.NrJpanel.clBean != null) {
                    String senmes2 = Agreement.extrAttrOperAgreement(GsonUtil.getGsonUtil().getgson().toJson(this.NrJpanel.clBean));
                    SendMessageUntil.toServer(senmes2);
                    this.NrJpanel.TH(this.NrJpanel.clBean);
                }
                else {
                    FormsManagement.HideForm(11);
                    NewRefiningJpanel.isLH = false;
                }
            }
            else if (this.cao == 2) {
                if (this.NrJpanel.leixing == 1) {
                    RefinersJpanel rJpanel = WorkshopRefiningJframe.getWorkshopRefiningJframe().getWorkshopRefiningJpanel().getCardJpanel().getRefinersJpanel();
                    String v = rJpanel.detection();
                    if (v.equals("炼器")) {
                        this.cao1(rJpanel.goods, rJpanel.money, 1);
                    }
                }
                else if (this.NrJpanel.leixing == 0) {
                    RefiningEquiJpanel EJpanel = WorkshopRefiningJframe.getWorkshopRefiningJframe().getWorkshopRefiningJpanel().getCardJpanel().getEquiJpanel();
                    String v = RefiningUtil.detection(EJpanel.goods, EJpanel.getType());
                    if (v.equals("炼化装备") || v.equals("炼化仙器") || v.equals("炼化神兵")) {
                        this.cao1(EJpanel.goods, EJpanel.money, 4);
                    }
                    else if (v.equals("点粹")) {
                        this.caozuo5(EJpanel.goods, EJpanel.money, v);
                    }
                    else if (v.equals("点粹强化")) {
                        if (DianQJframe.getDdianQJframe().getDianJpanel().clBean != null) {}
                        this.caozuo5(EJpanel.goods, EJpanel.money, v);
                    }
                }
                else if (this.NrJpanel.leixing == 2 || this.NrJpanel.leixing == 3) {
                    JframeStarCardMain.getJframeSummonEquipMain().getJpanelStarCardMain().caoZuoStarCard();
                }
                else if (this.NrJpanel.leixing == 4) {
                    this.NrJpanel.selectArenaIndex();
                }
            }
            else if (this.cao == 3) {
                this.NrJpanel.showArenaDownLab();
            }
        }
        else if (this.rJpanel != null) {
            if (this.cao == 0) {
                String v2 = this.rJpanel.detection();
                if (v2.equals("炼器")) {
                    String value = AccessSuitMsgUntil.getExtra(this.rJpanel.goods[0].getValue(), "炼器属性");
                    NewRefiningJframe.getNewRefiningJframe().getRefiningJpanel().show(value, 1, true);
                }
                else if (v2.equals("?")) {
                    ZhuFrame.getZhuJpanel().addPrompt2("公式不对");
                }
                else if (v2.equals("清除")) {
                    this.cao1(this.rJpanel.goods, this.rJpanel.money, 3);
                }
                else if (v2.equals("开光")) {
                    this.cao1(this.rJpanel.goods, this.rJpanel.money, 0);
                }
            }
        }
        else if (this.eJpanel != null) {
            String v2 = RefiningUtil.detection(this.eJpanel.goods, this.eJpanel.getType());
            if (v2.equals("炼化装备") || v2.equals("炼化仙器") || v2.equals("炼化神兵")) {
                String value = AccessSuitMsgUntil.getExtra(this.eJpanel.goods[0].getValue(), "炼化属性");
                NewRefiningJframe.getNewRefiningJframe().getRefiningJpanel().show(value, 0, true);
            }
            else if (v2.equals("装备升级") || v2.equals("装备重铸")) {
                if (RoleData.getRoleData().getLoginResult().getGold().longValue() < this.eJpanel.money.longValue()) {
                    ZhuFrame.getZhuJpanel().addPrompt2("金钱不足");
                    return;
                }
                this.palEquipUpgradeOrCultivate(this.eJpanel.goods, v2);
            }
            else {
                if (RoleData.getRoleData().getLoginResult().getGold().longValue() < this.eJpanel.money.longValue()) {
                    ZhuFrame.getZhuJpanel().addPrompt2("金钱不足");
                    return;
                }
                List<BigDecimal> rgids = new ArrayList<>();
                int size = 0;
                int p = -1;
                for (int i = 0; i < this.eJpanel.goods.length; ++i) {
                    if (this.eJpanel.goods[i] != null) {
                        ++size;
                        p = i;
                        if (this.eJpanel.goods[i].getGoodlock() == 1) {
                            ZhuFrame.getZhuJpanel().addPrompt("该物品已被加锁");
                            return;
                        }
                        if (GoodsListFromServerUntil.isExist(this.eJpanel.goods[i])) {
                            return;
                        }
                        int sum = 1;
                        for (int j = 0; j < rgids.size(); ++j) {
                            if (this.eJpanel.goods[i].getRgid().compareTo((BigDecimal)rgids.get(j)) == 0) {
                                ++sum;
                            }
                        }
                        if (sum > (int)this.eJpanel.goods[i].getUsetime()) {
                            ZhuFrame.getZhuJpanel().addPrompt2("请凑齐物品再来");
                            return;
                        }
                        rgids.add(this.eJpanel.goods[i].getRgid());
                    }
                }
                if (p + 1 != size) {
                    ZhuFrame.getZhuJpanel().addPrompt2("#Y请在引导界面查看公式,如果引导界面没有对应公式 联系管理员补充");
                    return;
                }
                if (v2.equals("佩饰培养") || v2.equals("一键培养")) {
                    this.cao3(this.eJpanel.goods, this.eJpanel.money, rgids);
                }
                else if (v2.equals("秘石合成")) {
                    this.cao4(this.eJpanel.goods, this.eJpanel.money, v2, rgids);
                }
                else if (v2.equals("点粹")) {
                    if (this.eJpanel.goods[0].getValue() != null) {
                        String[] mes = this.eJpanel.goods[0].getValue().split("\\|");
                        String value2 = AccessSuitMsgUntil.getExtra(this.eJpanel.goods[0].getValue(), "点粹属性");
                        DdianJframe.getDdianJframe().getDianJpanel().show(value2, 0, value2 != null);
                    }
                }
                else if (v2.equals("点粹强化")) {
                    if (this.eJpanel.goods[0].getValue() != null) {
                        String value3 = AccessSuitMsgUntil.getExtra(this.eJpanel.goods[0].getValue(), "点粹属性");
                        if (value3 == null) {
                            ZhuFrame.getZhuJpanel().addPrompt2("#Y当前装备没有点粹！请先获取点粹");
                            return;
                        }
                        DianQJframe.getDdianQJframe().getDianJpanel().show(value3, 0, false);
                    }
                }
                else {
                    this.cao2(this.eJpanel.goods, this.eJpanel.money, v2, rgids);
                }
            }
        }
        else if (this.tJpanel != null && this.cao == 3) {
            this.tJpanel.showArenaDownLab();
        }
    }
    
    public boolean cao1(Goodstable[] goods, BigDecimal money, int type) {
        NewRefiningJpanel NrJpanel = null;
        if (this.NrJpanel == null) {
            NrJpanel = NewRefiningJframe.getNewRefiningJframe().getRefiningJpanel();
        }
        else {
            NrJpanel = this.NrJpanel;
        }
        int lock = 0;
        int num = 0;
        if (type == 4 || type == 1) {
            lock = NrJpanel.getlock();
            num = lock % 10;
            lock /= 10;
        }
        BigDecimal xy = null;
        if (RoleData.getRoleData().getLoginResult().getGold().longValue() < money.longValue()) {
            GameJpanel.getGameJpanel().addPrompt("#Y金钱不足");
            return false;
        }
        if (num > 4) {
            GameJpanel.getGameJpanel().addPrompt("最多只能锁定4个");
            return false;
        }
        if (num > 0) {
            if (type == 4) {}
            LianHua lianHua1 = UserMessUntil.getAllLianHua().get1000(num);
            LianHua lianHua2 = UserMessUntil.getAllLianHua().get2000(num);
            if (num == 1) {
                if (type == 4) {
                    xy = new BigDecimal(lianHua1.getMoney());
                }
                else {
                    xy = new BigDecimal(lianHua2.getMoney());
                }
            }
            else if (num == 2) {
                if (type == 4) {
                    xy = new BigDecimal(lianHua1.getMoney());
                }
                else {
                    xy = new BigDecimal(lianHua2.getMoney());
                }
            }
            else if (num == 3) {
                if (type == 4) {
                    xy = new BigDecimal(lianHua1.getMoney());
                }
                else {
                    xy = new BigDecimal(lianHua2.getMoney());
                }
            }
            else if (num == 4) {
                if (type == 4) {
                    xy = new BigDecimal(lianHua1.getMoney());
                }
                else {
                    xy = new BigDecimal(lianHua2.getMoney());
                }
            }
            if (RoleData.getRoleData().getLoginResult().getCodecard().longValue() < xy.longValue()) {
                GameJpanel.getGameJpanel().addPrompt("仙玉不足");
                return false;
            }
        }
        List<BigDecimal> rgids = new ArrayList<>();
        int size = 0;
        int p = -1;
        for (int i = 0; i < goods.length; ++i) {
            if (goods[i] != null) {
                ++size;
                p = i;
                if (goods[i].getGoodlock() == 1) {
                    ZhuFrame.getZhuJpanel().addPrompt("该物品已被加锁");
                    return false;
                }
                if (GoodsListFromServerUntil.isExist(goods[i])) {
                    return false;
                }
                int sum = 1;
                for (int j = 0; j < rgids.size(); ++j) {
                    if (goods[i].getRgid().compareTo((BigDecimal)rgids.get(j)) == 0) {
                        ++sum;
                    }
                }
                if (sum > (int)goods[i].getUsetime()) {
                    ZhuFrame.getZhuJpanel().addPrompt2("请凑齐物品再来");
                    return false;
                }
                rgids.add(goods[i].getRgid());
            }
        }
        if (p + 1 != size) {
            GameJpanel.getGameJpanel().addPrompt("#Y请在引导界面查看公式,如果引导界面没有对应公式 联系管理员补充");
            return false;
        }
        SuitOperBean operBean = new SuitOperBean();
        if (type == 0) {
            if (size != 2) {
                GameJpanel.getGameJpanel().addPrompt("#Y请在引导界面查看公式,如果引导界面没有对应公式 联系管理员补充");
                return false;
            }
            if (!Goodtype.Weapons((long)goods[0].getType())) {
                GameJpanel.getGameJpanel().addPrompt("#Y不是武器也想开光？");
                return false;
            }
            String extra = AccessSuitMsgUntil.getExtra(goods[0].getValue(), "炼器属性");
            if (extra != null) {
                String[] extras = extra.split("&");
                if (Integer.parseInt(extras[1]) >= 5) {
                    FrameMessageChangeJpanel.addtext(6, "最大开光次数5", null, null);
                    return false;
                }
            }
            operBean.setType(10);
        }
        else if (type == 1) {
            if (size != 4) {
                GameJpanel.getGameJpanel().addPrompt("#Y请在引导界面查看公式,如果引导界面没有对应公式 联系管理员补充");
                return false;
            }
            String extra = AccessSuitMsgUntil.getExtra(goods[0].getValue(), "炼器属性");
            if (extra == null) {
                FrameMessageChangeJpanel.addtext(6, "先去开光", null, null);
                return false;
            }
            operBean.setType(11);
        }
        else if (type == 3) {
            if (size != 1) {
                GameJpanel.getGameJpanel().addPrompt("#Y请在引导界面查看公式,如果引导界面没有对应公式 联系管理员补充");
                return false;
            }
            operBean.setType(13);
        }
        else if (type == 4) {
            if (AccessSuitMsgUntil.getExtra(goods[0].getValue(), "套装属性") != null) {
                FrameMessageChangeJpanel.addtext(6, "套装无法炼化", null, null);
                return false;
            }
            if (Goodtype.GodEquipment_xian((long)goods[0].getType()) || Goodtype.GodEquipment_Ding((long)goods[0].getType())) {
                if ((long)goods[1].getType() == 7005L) {
                    String god = Goodtype.StringParsing(goods[1].getValue())[1];
                    if (!god.equals("阶数=1")) {
                        FrameMessageChangeJpanel.addtext(6, "使用一阶作为炼化材料太掉价了吗?", null, null);
                        return false;
                    }
                }
                else if (Goodtype.GodEquipment_xian((long)goods[1].getType()) || Goodtype.GodEquipment_Ding((long)goods[1].getType())) {
                    String god = Goodtype.StringParsing(goods[1].getValue())[0];
                    if (!god.equals("阶数=1")) {
                        FrameMessageChangeJpanel.addtext(6, "使用一阶作为炼化材料太掉价了吗?", null, null);
                        return false;
                    }
                }
            }
            else if (Goodtype.GodEquipment_God((long)goods[1].getType())) {
                String god = Goodtype.StringParsing(goods[1].getValue())[0];
                if (!god.equals("阶数=1")) {
                    FrameMessageChangeJpanel.addtext(6, "使用一阶作为炼化材料太掉价了吗?", null, null);
                    return false;
                }
            }
            operBean.setType(14);
        }
        for (int k = 1; k < goods.length; ++k) {
            if (goods[k] != null) {
                goods[k].goodxh(1);
                if ((int)goods[k].getUsetime() <= 0) {
                    GoodsListFromServerUntil.Deletebiaoid(goods[k].getRgid());
                }
            }
        }
        RoleData.getRoleData().getLoginResult().setGold(RoleData.getRoleData().getLoginResult().getGold().subtract(money));
        if (xy != null) {
            RoleData.getRoleData().getLoginResult().setCodecard(RoleData.getRoleData().getLoginResult().getCodecard().subtract(xy));
            operBean.setJade(new PartJade(lock, 0));
            GoodDetailedJframe.getGoodDetailedJframe().getGoodDetailedJpanel().getYonghuXianyu().setText(RoleData.getRoleData().getLoginResult().getCodecard() + "");
            TaobaoCourtMainJframe.getTaobaoCourtJframe().getTaobaoCourtMainJpanel().getJadeNum().setText(RoleData.getRoleData().getLoginResult().getCodecard() + "");
        }
        operBean.setGoods(rgids);
        System.out.println(GsonUtil.getGsonUtil().getgson().toJson(operBean));
        String senmes = Agreement.suitOperateAgreement(GsonUtil.getGsonUtil().getgson().toJson(operBean));
        SendMessageUntil.toServer(senmes);
        return true;
    }
    
    public void cao2(Goodstable[] goods, BigDecimal money, String type, List<BigDecimal> rgids) {
        NpcComposeBean npcComposeBean = new NpcComposeBean();
        if (type.equals("佩饰重铸")) {
            String Bottletext = goods[0].getValue();
            String[] gongneng = Goodtype.StringParsing(Bottletext);
            int kslvl = Integer.parseInt(goods[1].getValue().split("=")[1]);
            if (DazaoBtn.Numerical(gongneng[0]) != kslvl - 3) {
                FrameMessageChangeJpanel.addtext(6, "请使用" + (DazaoBtn.Numerical(gongneng[0]) + 3) + "级矿石进行重铸!", null, null);
                return;
            }
            npcComposeBean.setComposetype("我要重铸饰品");
            UserData.uptael((long)money.intValue());
            List<BigDecimal> goodstables = new ArrayList<>();
            for (int i = 0; i < goods.length; ++i) {
                if (goods[i] != null) {
                    goodstables.add(goods[i].getRgid());
                    goods[i].goodxh(1);
                    if ((int)goods[i].getUsetime() <= 0) {
                        GoodsListFromServerUntil.Deletebiaoid(goods[i].getRgid());
                        this.eJpanel.ClickGood(null, i + 24);
                    }
                }
            }
            npcComposeBean.setGoodstables(goodstables);
            String sendMes = Agreement.getAgreement().npccomposeAgreement(GsonUtil.getGsonUtil().getgson().toJson(npcComposeBean));
            SendMessageUntil.toServer(sendMes);
        }
        else if (type.equals("护身培养")) {
            String[] vs = goods[0].getValue().split("\\|");
            int pz = 0;
            int j = 0;
            while (j < vs.length) {
                String[] vsz = vs[j].split("=");
                if (vsz[0].equals("品质")) {
                    pz = Integer.parseInt(vsz[1].split("/")[0]);
                    break;
                }
                else {
                    ++j;
                }
            }
            int up = 800;
            String extra = AccessSuitMsgUntil.getExtra(goods[0].getValue(), "炼化属性");
            LOOP: {
                if (extra != null) {
                    String[] vvs = extra.split("&");
                    for (int k = 0; k < vvs.length; ++k) {
                        String[] vvvs = vvs[k].split("=");
                        if (vvvs[0].equals("特技")) {
                            int l = 1;
                            while (l < vvvs.length) {
                                if (vvvs[l].equals("8031")) {
                                    up = 900;
                                    break LOOP;
                                }
                                else {
                                    ++l;
                                }
                            }
                        }
                    }
                }
            }
            if (pz > up) {
                FrameMessageChangeJpanel.addtext(6, "该护身符品质大于" + up + "后无法培养", null, null);
                return;
            }
            npcComposeBean.setComposetype("我要培养护身符");
            UserData.uptael((long)money.intValue());
            List<BigDecimal> goodstables2 = new ArrayList<>();
            for (int k = 0; k < goods.length; ++k) {
                if (goods[k] != null) {
                    goodstables2.add(goods[k].getRgid());
                    goods[k].goodxh(1);
                    if ((int)goods[k].getUsetime() <= 0) {
                        GoodsListFromServerUntil.Deletebiaoid(goods[k].getRgid());
                        this.eJpanel.ClickGood(null, k + 24);
                    }
                }
            }
            npcComposeBean.setGoodstables(goodstables2);
            String sendMes2 = Agreement.getAgreement().npccomposeAgreement(GsonUtil.getGsonUtil().getgson().toJson(npcComposeBean));
            SendMessageUntil.toServer(sendMes2);
        }
        else if (type.equals("护身升级") || type.equals("护身重铸")) {
            String[] vs = goods[0].getValue().split("\\|");
            int pz = 0;
            int j = 0;
            while (j < vs.length) {
                String[] vsz = vs[j].split("=");
                if (vsz[0].equals("品质")) {
                    pz = Integer.parseInt(vsz[1].split("/")[0]);
                    break;
                }
                else {
                    ++j;
                }
            }
            if (pz < 300) {
                FrameMessageChangeJpanel.addtext(6, "该护身符品质低于300的需要培养", null, null);
                return;
            }
            int kslvl = Integer.parseInt(goods[1].getValue().split("=")[1]);
            if (kslvl != 9 && kslvl != 10) {
                FrameMessageChangeJpanel.addtext(6, "护身符重铸使用9级矿石,升级使用10级矿石", null, null);
                return;
            }
            npcComposeBean.setComposetype("我要重铸护身符");
            int lvl = Integer.parseInt(goods[0].getValue().split("\\|")[0].split("=")[1]);
            if (kslvl == 10 && ++lvl > 9) {
                FrameMessageChangeJpanel.addtext(6, "护身符等级最高为9级", null, null);
                return;
            }
            UserData.uptael((long)money.intValue());
            List<BigDecimal> goodstables2 = new ArrayList<>();
            for (int k = 0; k < goods.length; ++k) {
                if (goods[k] != null) {
                    goodstables2.add(goods[k].getRgid());
                    goods[k].goodxh(1);
                    if ((int)goods[k].getUsetime() <= 0) {
                        GoodsListFromServerUntil.Deletebiaoid(goods[k].getRgid());
                        this.eJpanel.ClickGood(null, k + 24);
                    }
                }
            }
            npcComposeBean.setGoodstables(goodstables2);
            String sendMes2 = Agreement.getAgreement().npccomposeAgreement(GsonUtil.getGsonUtil().getgson().toJson(npcComposeBean));
            SendMessageUntil.toServer(sendMes2);
        }
        else if (type.equals("巫铸")) {
            if (!Util.isCanBuyOrno()) {
                if (RoleData.getRoleData().getLoginResult().getGold().longValue() < money.longValue()) {
                    ZhuFrame.getZhuJpanel().addPrompt2("金钱不足");
                }
                for (int m = 0; m < 4; ++m) {
                    if (goods[m] == null) {
                        ZhuFrame.getZhuJpanel().addPrompt2("请凑齐物品再来");
                        return;
                    }
                    if (goods[m].getGoodlock() == 1) {
                        ZhuFrame.getZhuJpanel().addPrompt2("该物品已被加锁");
                        return;
                    }
                    if (GoodsListFromServerUntil.isExist(goods[m])) {
                        return;
                    }
                    if (Goodtype.EquipmentType((long)goods[m].getType()) != -1) {
                        if (AccessSuitMsgUntil.getExtra(goods[m].getValue(), BaptizeBtn.Extras[3]) != null) {
                            ZhuFrame.getZhuJpanel().addPrompt2("#W套装无法用于打造系列");
                            return;
                        }
                        if (AccessSuitMsgUntil.getExtra(goods[m].getValue(), BaptizeBtn.Extras[4]) != null || (goods[m].getQhv() != null && (int)goods[m].getQhv() > 0)) {
                            ZhuFrame.getZhuJpanel().addPrompt2("#W已镶嵌宝石无法用于打造系列");
                            return;
                        }
                    }
                }
                if (!this.type(type, goods)) {
                    int zblvl = Integer.parseInt(goods[0].getValue().split("\\|")[0].split("=")[1]);
                    int kslvl2 = Integer.parseInt(goods[1].getValue().split("=")[1]);
                    boolean up2 = false;
                    if (zblvl >= 10 && zblvl <= 13) {
                        if (kslvl2 != 8 && kslvl2 != 9) {
                            return;
                        }
                        if (kslvl2 == 9) {
                            up2 = true;
                        }
                    }
                    else if (zblvl == 14) {
                        if (kslvl2 != 9 && kslvl2 != 10) {
                            return;
                        }
                        if (kslvl2 == 10) {
                            up2 = true;
                        }
                    }
                    else if (zblvl == 15) {
                        if (kslvl2 != 10 && kslvl2 != 11) {
                            return;
                        }
                        if (kslvl2 == 11) {
                            up2 = true;
                        }
                    }
                    else if (zblvl == 16) {
                        if (kslvl2 != 11) {
                            return;
                        }
                    }
                    else {
                        return;
                    }
                    zblvl += (up2 ? 1 : 0);
                    int wzcl = 1;
                    String typeStr = getEquipType(goods[0].getType());
                    if (typeStr == "武器" || typeStr == "衣服" || typeStr == "帽子") {
                        if (zblvl == 15 && (int)goods[3].getUsetime() < wzcl) {
                            ZhuFrame.getZhuJpanel().addPrompt2(goods[3].getGoodsname() + "材料数量不足！需要4个。");
                            return;
                        }
                        if (zblvl == 16 && (int)goods[3].getUsetime() < wzcl) {
                            ZhuFrame.getZhuJpanel().addPrompt2(goods[3].getGoodsname() + "材料数量不足！需要1个。");
                            return;
                        }
                    }
                    else if (typeStr == "鞋子" || typeStr == "项链") {
                        if (zblvl == 15 && (int)goods[3].getUsetime() < wzcl) {
                            ZhuFrame.getZhuJpanel().addPrompt2(goods[3].getGoodsname() + "材料数量不足！需要3个。");
                            return;
                        }
                        if (zblvl == 16 && (int)goods[3].getUsetime() < wzcl) {
                            ZhuFrame.getZhuJpanel().addPrompt2(goods[3].getGoodsname() + "材料数量不足！需要1个。");
                            return;
                        }
                    }
                    if (!type.equals("我要升级神兵") && !type.equals("精炼神兵")) {
                        UserData.uptael((long)money.intValue());
                        List<BigDecimal> goodstables3 = new ArrayList<>();
                        for (int j2 = 0; j2 < 4; ++j2) {
                            if (j2 == 0) {
                                goods[j2].setUsetime(Integer.valueOf(0));
                            }
                            if (j2 != 0 && j2 != 3) {
                                goods[j2].setUsetime(Integer.valueOf((int)goods[j2].getUsetime() - 1));
                            }
                            else if (j2 == 3) {
                                goods[j2].setUsetime(Integer.valueOf((int)goods[j2].getUsetime() - wzcl));
                            }
                            goodstables3.add(goods[j2].getRgid());
                            if ((int)goods[j2].getUsetime() <= 0) {
                                GoodsListFromServerUntil.Deletebiaoid(goods[j2].getRgid());
                                goods[j2] = null;
                                this.eJpanel.ClickGood(null, j2 + 24);
                            }
                        }
                        WitchComposeBean witchComposeBean = new WitchComposeBean();
                        witchComposeBean.setComposetype(type);
                        witchComposeBean.setGoodstables(goodstables3);
                        String senmes = Agreement.getAgreement().witchcomposeAgreement(GsonUtil.getGsonUtil().getgson().toJson(witchComposeBean));
                        SendMessageUntil.toServer(senmes);
                    }
                }
            }
        }
        else if (type.equals("普通打造")) {
            if (!Util.isCanBuyOrno()) {
                if (RoleData.getRoleData().getLoginResult().getGold().longValue() < money.longValue()) {
                    ZhuFrame.getZhuJpanel().addPrompt2("金钱不足");
                }
                else {
                    for (int m = 0; m < 2; ++m) {
                        if (goods[m] == null) {
                            ZhuFrame.getZhuJpanel().addPrompt2("请凑齐物品再来");
                            return;
                        }
                        if (goods[m].getGoodlock() == 1) {
                            ZhuFrame.getZhuJpanel().addPrompt2("该物品已被加锁");
                            return;
                        }
                        if (GoodsListFromServerUntil.isExist(goods[m])) {
                            return;
                        }
                        if (Goodtype.EquipmentType((long)goods[m].getType()) != -1) {
                            if (AccessSuitMsgUntil.getExtra(goods[m].getValue(), BaptizeBtn.Extras[3]) != null) {
                                ZhuFrame.getZhuJpanel().addPrompt2("#W套装无法用于打造系列");
                                return;
                            }
                            if (AccessSuitMsgUntil.getExtra(goods[m].getValue(), BaptizeBtn.Extras[4]) != null || (goods[m].getQhv() != null && (int)goods[m].getQhv() > 0)) {
                                ZhuFrame.getZhuJpanel().addPrompt2("#W已镶嵌宝石无法用于打造系列");
                                return;
                            }
                        }
                    }
                    if (!this.type(type, goods)) {
                        UserData.uptael((long)money.intValue());
                        List<BigDecimal> goodstables4 = new ArrayList<>();
                        for (int j3 = 0; j3 < 2; ++j3) {
                            if (j3 == 0) {
                                goods[j3].setUsetime(Integer.valueOf(0));
                            }
                            else {
                                goods[j3].setUsetime(Integer.valueOf((int)goods[j3].getUsetime() - 1));
                            }
                            goodstables4.add(goods[j3].getRgid());
                            if ((int)goods[j3].getUsetime() <= 0) {
                                GoodsListFromServerUntil.Deletebiaoid(goods[j3].getRgid());
                                goods[j3] = null;
                                this.eJpanel.ClickGood(null, j3 + 24);
                            }
                        }
                        npcComposeBean.setComposetype("打造11-16级装备");
                        npcComposeBean.setGoodstables(goodstables4);
                        String senmes2 = Agreement.getAgreement().npccomposeAgreement(GsonUtil.getGsonUtil().getgson().toJson(npcComposeBean));
                        SendMessageUntil.toServer(senmes2);
                    }
                }
            }
        }
        else {
            return;
        }
    }
    
    public static String getEquipType(Long type) {
        String goodsType = null;
        if ((long)type == 601L || (long)type == 600L) {
            goodsType = "帽子";
        }
        else if ((long)type == 603L) {
            goodsType = "项链";
        }
        else if ((long)type == 605L || (long)type == 604L) {
            goodsType = "衣服";
        }
        else if ((long)type == 800L) {
            goodsType = "武器";
        }
        else if ((long)type == 602L) {
            goodsType = "鞋子";
        }
        else if ((long)type == 612L) {
            goodsType = "护身符";
        }
        return goodsType;
    }
    
    public void cao3(Goodstable[] goods, BigDecimal money, List<BigDecimal> rgids) {
        int mxxh = (int)goods[1].getUsetime();
        long sxm = RoleData.getRoleData().getLoginResult().getGold().longValue();
        if (sxm / money.longValue() < (long)mxxh) {
            mxxh = (int)(sxm / money.longValue());
        }
        int xh = 0;
        String[] qs = goods[0].getValue().split("\\|");
        if (DazaoBtn.Numerical(qs[0]) >= 7) {
            ZhuFrame.getZhuJpanel().addPrompt2("还没开放8级佩饰培养");
            return;
        }
        if (Goodtype.Accessories((long)goods[1].getType())) {
            int flvl = 0;
            String[] vs = goods[1].getValue().split("\\|");
            for (int i = 0; i < vs.length; ++i) {
                String[] v = vs[i].split("=");
                if (v[0].equals("等级")) {
                    flvl = Integer.parseInt(v[1]);
                }
            }
            if (flvl > 2) {
                ZhuFrame.getZhuJpanel().addPrompt2("无法用2级以上的配饰培养");
                return;
            }
        }
        int max = 0;
        for (int j = 0; j < qs.length; ++j) {
            if (qs[j].length() >= 2 && qs[j].substring(0, 2).equals("培养")) {
                String[] num = qs[j].split("=")[1].split("/");
                int value = Integer.parseInt(num[0]);
                max = Integer.parseInt(num[1]);
                xh = max - value + 1;
            }
        }
        if (max == 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("该配饰无法培养");
            return;
        }
        if (xh > mxxh) {
            xh = mxxh;
        }
        if (xh <= 0) {
            GameJpanel.getGameJpanel().addPrompt("#Y请凑齐物品再来");
            return;
        }
        money = new BigDecimal(money.longValue() * (long)xh);
        if (RoleData.getRoleData().getLoginResult().getGold().longValue() < money.longValue()) {
            GameJpanel.getGameJpanel().addPrompt("#Y金钱不足");
            return;
        }
        ZhuFrame.getZhuJpanel().addPrompt2("一键培养了" + xh + "次");
        RoleData.getRoleData().getLoginResult().setGold(RoleData.getRoleData().getLoginResult().getGold().subtract(money));
        SuitOperBean operBean = new SuitOperBean();
        PartJade jade = new PartJade(-1, -1);
        jade.setJade1(xh);
        operBean.setType(15);
        goods[0].setUsetime(Integer.valueOf(0));
        GoodsListFromServerUntil.Deletebiaoid(goods[0].getRgid());
        this.eJpanel.ClickGood(null, 24);
        goods[1].goodxh(xh);
        if ((int)goods[1].getUsetime() <= 0) {
            GoodsListFromServerUntil.Deletebiaoid(goods[1].getRgid());
            this.eJpanel.ClickGood(null, 25);
        }
        operBean.setJade(jade);
        operBean.setGoods(rgids);
        String senmes = Agreement.suitOperateAgreement(GsonUtil.getGsonUtil().getgson().toJson(operBean));
        SendMessageUntil.toServer(senmes);
    }
    
    private void cao4(Goodstable[] goods, BigDecimal money, String v, List<BigDecimal> rgids) {
        SuitOperBean operBean = new SuitOperBean();
        if (v.equals("秘石合成")) {
            operBean.setType(36);
            int value = -1;
            for (int i = 0; i < goods.length; ++i) {
                if (goods[i] != null) {
                    if (value == -1) {
                        value = Integer.parseInt(goods[i].getValue().split("=")[1]);
                    }
                    else if (value != Integer.parseInt(goods[i].getValue().split("=")[1])) {
                        ZhuFrame.getZhuJpanel().addPrompt2("使用等级相同的秘石合成");
                        return;
                    }
                }
            }
            if (value == -1) {
                return;
            }
            if (value >= 5) {
                ZhuFrame.getZhuJpanel().addPrompt2("最高5级");
                return;
            }
        }
        RoleData.getRoleData().getLoginResult().setGold(RoleData.getRoleData().getLoginResult().getGold().subtract(money));
        for (int j = 0; j < goods.length; ++j) {
            if (goods[j] != null) {
                goods[j].goodxh(1);
                if ((int)goods[j].getUsetime() <= 0) {
                    GoodsListFromServerUntil.Deletebiaoid(goods[j].getRgid());
                    this.eJpanel.ClickGood(null, j + 24);
                }
            }
        }
        operBean.setGoods(rgids);
        String senmes = Agreement.suitOperateAgreement(GsonUtil.getGsonUtil().getgson().toJson(operBean));
        SendMessageUntil.toServer(senmes);
    }
    
    public void palEquipUpgradeOrCultivate(Goodstable[] goods, String type) {
        String palEquipAgree = AccessSuitMsgUntil.getPalEquipAgree(goods[0].getValue(), "契合度");
        String[] split = palEquipAgree.split("=");
        String[] agreeArr = split[1].split("/");
        if ("装备升级".equals(type)) {
            if (Integer.parseInt(agreeArr[0]) < Integer.parseInt(agreeArr[1])) {
                ZhuFrame.getZhuJpanel().addPrompt2("该装备契合度不够");
                return;
            }
        }
        else if ("装备培养".equals(type)) {
            if (Integer.parseInt(agreeArr[0]) >= Integer.parseInt(agreeArr[1])) {
                ZhuFrame.getZhuJpanel().addPrompt2("该装备契合度已满");
                return;
            }
            if (Goodtype.isPalEquip((long)goods[1].getType())) {
                String levelEquip = AccessSuitMsgUntil.getPalEquipAgree(goods[1].getValue(), "等级");
                String[] lvlArr = levelEquip.split("=");
                if (Integer.parseInt(lvlArr[1]) >= 4) {
                    ZhuFrame.getZhuJpanel().addPrompt2("装备4级以上包括4级,不能作为培养道具");
                    return;
                }
            }
            else if ((long)goods[1].getType() != 7511L) {
                ZhuFrame.getZhuJpanel().addPrompt2("不是伙伴装备培养道具");
                return;
            }
        }
        SuitOperBean operBean = new SuitOperBean();
        List<BigDecimal> arrayList = new ArrayList<>();
        for (int i = 0; i < goods.length; ++i) {
            if (goods[i] != null) {
                arrayList.add(goods[i].getRgid());
            }
        }
        operBean.setGoods(arrayList);
        operBean.setType(62 - ("装备培养".equals(type) ? 1 : 0));
        String senmes = Agreement.suitOperateAgreement(GsonUtil.getGsonUtil().getgson().toJson(operBean));
        SendMessageUntil.toServer(senmes);
    }
    
    public String isBooleanEquipLvl(String agreeMax) {
        if ("1000".equals(agreeMax)) {
            return "2";
        }
        if ("2000".equals(agreeMax)) {
            return "3";
        }
        if ("4000".equals(agreeMax)) {
            return "4";
        }
        if ("6000".equals(agreeMax)) {
            return "5";
        }
        if ("8000".equals(agreeMax)) {
            return "6";
        }
        return "-1";
    }
    
    public boolean type(String type, Goodstable[] goods) {
        if (type != null) {
            if (type.equals("巫铸")) {
                return this.GoodItemWuzhu(goods);
            }
            if (type.equals("普通打造")) {
                return this.GoodItem_4(goods);
            }
        }
        return true;
    }
    
    public boolean GoodItemWuzhu(Goodstable[] goods) {
        int goodid = goods[0].getGoodsid().intValue();
        int zblvl = Integer.parseInt(goods[0].getValue().split("\\|")[0].split("=")[1]);
        int kslvl = Integer.parseInt(goods[1].getValue().split("=")[1]);
        if (zblvl < 10) {
            FrameMessageChangeJpanel.addtext(6, "巫铸装备只能为11~16级装备!", null, null);
            return true;
        }
        boolean up = false;
        if (zblvl >= 10 && zblvl <= 13) {
            if (kslvl != 8 && kslvl != 9) {
                FrameMessageChangeJpanel.addtext(6, "巫铸11-14级装备使用9级矿石!", null, null);
                FrameMessageChangeJpanel.addtext(6, "重铸10-13级装备使用8级矿石!", null, null);
                return true;
            }
            if (kslvl == 9) {
                up = true;
            }
        }
        else if (zblvl == 14) {
            if (kslvl != 9 && kslvl != 10) {
                FrameMessageChangeJpanel.addtext(6, "巫铸15级装备使用10级矿石!", null, null);
                FrameMessageChangeJpanel.addtext(6, "重铸14级装备使用9级矿石!", null, null);
                return true;
            }
            if (kslvl == 10) {
                up = true;
            }
        }
        else if (zblvl == 15) {
            if (kslvl != 10 && kslvl != 11) {
                FrameMessageChangeJpanel.addtext(6, "巫铸16级装备使用11级矿石!", null, null);
                FrameMessageChangeJpanel.addtext(6, "重铸15级装备使用10级矿石!", null, null);
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
            if (kslvl != 9) {
                FrameMessageChangeJpanel.addtext(6, "打造11-14级装备使用9级矿石!", null, null);
//                FrameMessageChangeJpanel.addtext(6, "重铸10-13级装备使用8级矿石!", null, null);
                return true;
            }
            if (kslvl == 9) {
                up = true;
            }
        }
        else if (zblvl == 14) {
            if (kslvl != 9 && kslvl != 10) {
                FrameMessageChangeJpanel.addtext(6, "打造15级装备使用10级矿石!", null, null);
                FrameMessageChangeJpanel.addtext(6, "重铸14级装备使用9级矿石!", null, null);
                return true;
            }
            if (kslvl == 10) {
                up = true;
            }
        }
        else if (zblvl == 15) {
            if (kslvl != 10 && kslvl != 11) {
                FrameMessageChangeJpanel.addtext(6, "打造16级装备使用11级矿石!", null, null);
                FrameMessageChangeJpanel.addtext(6, "重铸15级装备使用10级矿石!", null, null);
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
    
    public boolean caozuo5(Goodstable[] goods, BigDecimal money, String type2) {
        NewRefiningJpanel NrJpanel = null;
        if (this.NrJpanel == null) {
            NrJpanel = NewRefiningJframe.getNewRefiningJframe().getRefiningJpanel();
        }
        else {
            NrJpanel = this.NrJpanel;
        }
        BigDecimal xy = null;
        if (RoleData.getRoleData().getLoginResult().getGold().longValue() < money.longValue()) {
            GameJpanel.getGameJpanel().addPrompt("#Y金钱不足");
            return false;
        }
        List<BigDecimal> rgids = new ArrayList<>();
        int size = 0;
        int p = -1;
        for (int i = 0; i < goods.length; ++i) {
            if (goods[i] != null) {
                ++size;
                p = i;
                if (goods[i].getGoodlock() == 1) {
                    ZhuFrame.getZhuJpanel().addPrompt("该物品已被加锁");
                    return false;
                }
                if (GoodsListFromServerUntil.isExist(goods[i])) {
                    return false;
                }
                int sum = 1;
                for (int j = 0; j < rgids.size(); ++j) {
                    if (goods[i].getRgid().compareTo((BigDecimal)rgids.get(j)) == 0) {
                        ++sum;
                    }
                }
                if (sum > (int)goods[i].getUsetime()) {
                    ZhuFrame.getZhuJpanel().addPrompt2("请凑齐物品再来");
                    return false;
                }
                rgids.add(goods[i].getRgid());
            }
        }
        if (p + 1 != size) {
            GameJpanel.getGameJpanel().addPrompt("#Y请在引导界面查看公式,如果引导界面没有对应公式 联系管理员补充");
            return false;
        }
        SuitOperBean operBean = new SuitOperBean();
        for (int k = 1; k < goods.length; ++k) {
            if (goods[k] != null) {
                goods[k].goodxh(1);
                if ((int)goods[k].getUsetime() <= 0) {
                    GoodsListFromServerUntil.Deletebiaoid(goods[k].getRgid());
                }
            }
        }
        if (type2.equals("点粹")) {
            operBean.setType(120);
        }
        else {
            operBean.setType(127);
        }
        RoleData.getRoleData().getLoginResult().setGold(RoleData.getRoleData().getLoginResult().getGold().subtract(money));
        operBean.setGoods(rgids);
        String senmes = Agreement.suitOperateAgreement(GsonUtil.getGsonUtil().getgson().toJson(operBean));
        SendMessageUntil.toServer(senmes);
        return true;
    }
}
