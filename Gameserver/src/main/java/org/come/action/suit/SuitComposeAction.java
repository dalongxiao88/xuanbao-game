package org.come.action.suit;

import org.come.model.TeJiLH;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import org.come.model.Skill;
import org.come.readUtil.MountShouhu;
import java.util.Iterator;
import come.tool.FightingData.Battlefield;
import org.come.readUtil.ReadACardUtil;
import io.netty.util.internal.StringUtil;
import java.text.DecimalFormat;
import org.come.model.Alchemy;
import com.google.common.collect.Lists;
import org.come.action.npc.NpcComposeAction;
import java.util.Objects;
import java.util.ArrayList;
import come.tool.Mixdeal.AnalysisString;
import java.util.concurrent.ConcurrentHashMap;
import org.come.model.Configure;
import com.gl.util.Config;
import org.come.action.buy.AddGoodUtil;
import org.come.bean.QualityClBean;
import org.come.action.reward.DrawnitemsAction;
import org.come.entity.Suit;
import come.tool.Role.PartJade;
import org.come.entity.Goodstable;
import org.come.entity.PackRecord;
import come.tool.Role.RoleData;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.action.monitor.MonitorUtil;
import come.tool.Stall.AssetUpdate;
import org.come.tool.Goodtype;
import org.come.until.AchievemUtil;
import org.come.until.AllServiceUtil;
import java.math.BigDecimal;
import come.tool.Role.RolePool;
import org.come.until.GsonUtil;
import org.come.bean.SuitOperBean;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import java.util.List;
import java.util.Random;
import org.come.action.IAction;

public class SuitComposeAction implements IAction
{
    public static String[] Extras;
    public static String CHECKTS1;
    public static String CHECKTS2;
    public static String CHECKTS3;
    public static String CHECKTS4;
    public static String CHECKTS5;
    public static String CHECKTS6;
    public static Random random;
    private static List<String> TJ;
    private static List<String> YFTJ;
    private static List<String> XLTJ;
    private static List<String> MZTJ;
    private static List<String> XTJ;
    private static List<String> HSTJ;
    
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (loginResult == null) {
            return;
        }
        SuitOperBean suitOperBean = (SuitOperBean)GsonUtil.getGsonUtil().getgson().fromJson(message, SuitOperBean.class);
        switch (suitOperBean.getType()) {
            case 0: {
                this.type0(loginResult, ctx, suitOperBean);
                break;
            }
            case 1:
            case 2: {
                this.type1(loginResult, ctx, suitOperBean);
                break;
            }
            case 3: {
                this.type3(loginResult, ctx, suitOperBean);
                break;
            }
            case 4: {
                this.type4(loginResult, ctx, suitOperBean);
                break;
            }
            case 5: {
                this.type5(loginResult, ctx, suitOperBean);
                break;
            }
            case 6: {
                this.type6(loginResult, ctx, suitOperBean);
                break;
            }
            case 7: {
                this.type7(loginResult, ctx, suitOperBean);
                break;
            }
            case 8: {
                this.type8(loginResult, ctx, suitOperBean);
                break;
            }
            case 9: {
                this.type9(loginResult, ctx, suitOperBean);
                break;
            }
            case 10: {
                this.type10(loginResult, ctx, suitOperBean);
                break;
            }
            case 11:
            case 12:
            case 13: {
                this.type13(loginResult, ctx, suitOperBean);
                break;
            }
            case 14: {
                this.type14(loginResult, ctx, suitOperBean);
                break;
            }
            case 15: {
                this.type15(loginResult, ctx, suitOperBean);
                break;
            }
            case 16: {
                this.type16(loginResult, ctx, suitOperBean);
                break;
            }
            case 17: {
                GemCompose.type17(loginResult, ctx, suitOperBean);
                break;
            }
            case 18: {
                GemCompose.type18(loginResult, ctx, suitOperBean);
                break;
            }
            case 19: {
                GemCompose.type19(loginResult, ctx, suitOperBean);
                break;
            }
            case 20: {
                GemCompose.type20(loginResult, ctx, suitOperBean);
                break;
            }
            case 21: {
                GemCompose.type21(loginResult, ctx, suitOperBean);
                break;
            }
            case 31: {
                WingCompose.type31(loginResult, ctx, suitOperBean);
                break;
            }
            case 32: {
                WingCompose.type32(loginResult, ctx, suitOperBean);
                break;
            }
            case 33: {
                WingCompose.type33(loginResult, ctx, suitOperBean);
                break;
            }
            case 34: {
                WingCompose.type34(loginResult, ctx, suitOperBean);
                break;
            }
            case 35: {
                WingCompose.type35(loginResult, ctx, suitOperBean);
                break;
            }
            case 361:
            case 362:
            case 363:
            case 364: {
                WingCompose.type355(loginResult, ctx, suitOperBean);
                break;
            }
            case 36: {
                WingCompose.type36(loginResult, ctx, suitOperBean);
                break;
            }
            case 41: {
                SuitPetEquip.type41(loginResult, ctx, suitOperBean);
                break;
            }
            case 42: {
                SuitPetEquip.type42(loginResult, ctx, suitOperBean);
                break;
            }
            case 43: {
                SuitPetEquip.type43(loginResult, ctx, suitOperBean);
                break;
            }
            case 44: {
                SuitPetEquip.type44(loginResult, ctx, suitOperBean);
                break;
            }
            case 45: {
                SuitPetEquip.type45(loginResult, ctx, suitOperBean);
                break;
            }
            case 46: {
                SuitPetEquip.type46(loginResult, ctx, suitOperBean);
                break;
            }
            case 51: {
                StarCard.type51(loginResult, ctx, suitOperBean);
                break;
            }
            case 52: {
                StarCard.type52(loginResult, ctx, suitOperBean);
                break;
            }
            case 53: {
                StarCard.type53(loginResult, ctx, suitOperBean);
                break;
            }
            case 54: {
                StarCard.type54(loginResult, ctx, suitOperBean);
                break;
            }
            case 55: {
                StarCard.type55(loginResult, ctx, suitOperBean);
                break;
            }
            case 56: {
                StarCard.type56(loginResult, ctx, suitOperBean);
                break;
            }
            case 57: {
                StarCard.type57(loginResult, ctx, suitOperBean);
                break;
            }
            case 58: {
                StarCard.type58(loginResult, ctx, suitOperBean);
                break;
            }
            case 59: {
                StarCard.type59(loginResult, ctx, suitOperBean);
                break;
            }
            case 62: {
                SuitPalEquip.type62(loginResult, ctx, suitOperBean);
                break;
            }
            case 71:
            case 72:
            case 73:
            case 74:
            case 75:
            case 76: {
                SuitPalEquip.type70(loginResult, ctx, suitOperBean);
                break;
            }
            case 101: {
                GemIntensify.type101(loginResult, ctx, suitOperBean);
                break;
            }
            case 102: {
                GemIntensify.type102(loginResult, ctx, suitOperBean, null);
                break;
            }
            case 103: {
                GemIntensify.type103(loginResult, ctx, suitOperBean);
                break;
            }
            case 104: {
                GemIntensify.type104(loginResult, ctx, suitOperBean);
                break;
            }
            case 120: {
                this.type120(loginResult, ctx, suitOperBean);
                break;
            }
            case 127: {
                this.type127(loginResult, ctx, suitOperBean);
                break;
            }
            case 2255: {
                this.type123(loginResult, ctx, suitOperBean);
                break;
            }
            case 2256: {
                this.type124(loginResult, ctx, suitOperBean);
                break;
            }
        }
    }
    
    public void type0(LoginResult loginResult, ChannelHandlerContext ctx, SuitOperBean suitOperBean) {
        RoleData data = RolePool.getRoleData(loginResult.getRole_id());
        if (data == null) {
            return;
        }
        PackRecord record = data.getPackRecord();
        BigDecimal money = new BigDecimal(100000);
        if (suitOperBean.getGoods() == null || suitOperBean.getGoods().size() == 0) {
            return;
        }
        Goodstable goodstable = AllServiceUtil.getGoodsTableService().getGoodsByRgID((BigDecimal)suitOperBean.getGoods().get(0));
        if (goodstable == null) {
            return;
        }
        if (loginResult.getGold().compareTo(money) < 0) {
            return;
        }
        PartJade jade = suitOperBean.getJade();
        int pz = 0;
        int i = 1;
        while (i < 6) {
            if (jade.getJade(i) > 0) {
                pz = i;
                break;
            }
            else {
                ++i;
            }
        }
        if (pz == 0) {
            return;
        }
        PartJade partJade = record.getPartJade(jade.getSuitid(), jade.getPartId());
        if (partJade.getJade(pz) < 1) {
            return;
        }
        int type = Goodtype.EquipmentType(goodstable.getType());
        if (jade.getPartId() == 11) {
            if (type != 10) {
                return;
            }
        }
        else if (type != jade.getPartId()) {
            return;
        }
        if (getExtra(goodstable.getValue(), SuitComposeAction.Extras[3]) != null) {
            return;
        }
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setType(AssetUpdate.SUIT);
        partJade.setJade(pz, -1);
        record.setPartJade(partJade);
        jade = partJade;
        loginResult.setGold(loginResult.getGold().subtract(money));
        MonitorUtil.getMoney().useD(money.longValue());
        Suit suit = GameServer.getSuit(jade.getSuitid());
        Goodstable good = GameServer.getGood(goodstable.getGoodsid());
        goodstable.setGoodsname(suit.getSname() + "·" + good.getGoodsname());
        goodstable.setSkin("tz" + jade.getSuitid() + "_" + jade.getPartId());
        String SuitV = this.SuitV(suit.getSuitID(), jade.getPartId(), pz);
        goodstable.setValue(this.suitGenerate(goodstable.getValue(), SuitV));
        assetUpdate.setGood(goodstable);
        AllServiceUtil.getGoodsTableService().updateGoodRedis(goodstable);
        AllServiceUtil.getGoodsrecordService().insert(goodstable, null, Integer.valueOf(1), Integer.valueOf(13));
        assetUpdate.setJade(jade);
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public void type1(LoginResult loginResult, ChannelHandlerContext ctx, SuitOperBean suitOperBean) {
        if (suitOperBean.getGoods() == null || suitOperBean.getGoods().size() == 0) {
            return;
        }
        Goodstable goodstable = AllServiceUtil.getGoodsTableService().getGoodsByRgID((BigDecimal)suitOperBean.getGoods().get(0));
        if (goodstable == null) {
            return;
        }
        BigDecimal money = new BigDecimal(1000000);
        if (suitOperBean.getType() == 2) {
            money = new BigDecimal(1000000);
        }
        if (loginResult.getGold().compareTo(money) < 0) {
            return;
        }
        BigDecimal lxz = loginResult.getScoretype("灵修值");
        if (lxz.compareTo(new BigDecimal(30)) < 0) {
            return;
        }
        String extra = getExtra(goodstable.getValue(), SuitComposeAction.Extras[3]);
        if (extra == null) {
            return;
        }
        loginResult.setGold(loginResult.getGold().subtract(money));
        MonitorUtil.getMoney().useD(money.longValue());
        loginResult.setScore(DrawnitemsAction.Splice(loginResult.getScore(), "灵修值=30", 3));
        String[] extras = extra.split("&");
        int suitid = Integer.parseInt(extras[1]);
        int partid = Integer.parseInt(extras[2]);
        int pz = SuitMixdeal.getPZlvl(extras[3]);
        String SuitV = this.SuitV(suitid, partid, pz);
        String value = newExtra(goodstable.getValue().split("\\|"), 3, SuitV);
        if (suitOperBean.getType() == 1) {
            goodstable.setValue(value);
            AllServiceUtil.getGoodsTableService().updateGoodRedis(goodstable);
            AllServiceUtil.getGoodsrecordService().insert(goodstable, null, Integer.valueOf(1), Integer.valueOf(13));
        }
        QualityClBean clBean = new QualityClBean();
        clBean.setRgid(goodstable.getRgid());
        clBean.setType(-4);
        clBean.setNewAttr(SuitV);
        if (suitOperBean.getType() == 2) {
            clBean.setType(4);
            QualityCPool.getcPool().addExtra(clBean);
        }
        SendMessage.sendMessageToSlef(ctx, Agreement.ExtrattroperAgreement(GsonUtil.getGsonUtil().getgson().toJson(clBean)));
    }
    
    public void type3(LoginResult loginResult, ChannelHandlerContext ctx, SuitOperBean suitOperBean) {
        RoleData data = RolePool.getRoleData(loginResult.getRole_id());
        if (data == null) {
            return;
        }
        PackRecord record = data.getPackRecord();
        if (suitOperBean.getGoods() == null || suitOperBean.getGoods().size() == 0) {
            return;
        }
        Goodstable goodstable = AllServiceUtil.getGoodsTableService().getGoodsByRgID((BigDecimal)suitOperBean.getGoods().get(0));
        if (goodstable == null) {
            return;
        }
        BigDecimal money = new BigDecimal(10000000);
        if (loginResult.getGold().compareTo(money) < 0) {
            return;
        }
        String extra = getExtra(goodstable.getValue(), SuitComposeAction.Extras[3]);
        if (extra == null) {
            return;
        }
        String[] vs = extra.split("&");
        int suitid = Integer.parseInt(vs[1]);
        int partid = Integer.parseInt(vs[2]);
        int pz = SuitMixdeal.getPZlvl(vs[3]) + 1;
        PartJade partJade = record.getPartJade(suitid, partid);
        if (partJade.getJade(pz) <= 0) {
            return;
        }
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setType(AssetUpdate.SUIT);
        partJade.setJade(pz, -1);
        record.setPartJade(partJade);
        assetUpdate.setJade(partJade);
        loginResult.setGold(loginResult.getGold().subtract(money));
        MonitorUtil.getMoney().useD(money.longValue());
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < vs.length; ++i) {
            if (i != 0) {
                buffer.append("&");
            }
            if (i != 3) {
                buffer.append(vs[i]);
            }
            else {
                buffer.append(SuitMixdeal.getPZName(pz));
            }
        }
        extra = buffer.toString();
        String[] vss = goodstable.getValue().split("\\|");
        vss[0] = "套装品质=" + SuitMixdeal.getPZName(pz);
        goodstable.setValue(newExtra(vss, 3, extra));
        AllServiceUtil.getGoodsTableService().updateGoodRedis(goodstable);
        AllServiceUtil.getGoodsrecordService().insert(goodstable, null, Integer.valueOf(1), Integer.valueOf(13));
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public void type4(LoginResult loginResult, ChannelHandlerContext ctx, SuitOperBean suitOperBean) {
        RoleData data = RolePool.getRoleData(loginResult.getRole_id());
        if (data == null) {
            return;
        }
        PackRecord record = data.getPackRecord();
        PartJade jade = suitOperBean.getJade();
        PartJade partJade = record.getPartJade(jade.getSuitid(), jade.getPartId());
        int pz = 0;
        int i = 1;
        while (i < 6) {
            if (jade.getJade(i) > 0) {
                pz = i;
                break;
            }
            else {
                ++i;
            }
        }
        if (pz == 0) {
            return;
        }
        BigDecimal money = SuitMixdeal.returnJadeMoney(pz);
        int sum = SuitMixdeal.returnJadeNum(pz);
        if (loginResult.getGold().compareTo(money) < 0) {
            return;
        }
        if (partJade.getJade(pz) < sum) {
            return;
        }
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setType(AssetUpdate.SUIT);
        loginResult.setGold(loginResult.getGold().subtract(money));
        MonitorUtil.getMoney().useD(money.longValue());
        partJade.setJade(pz, -sum);
        partJade.setJade(++pz, 1);
        record.setPartJade(partJade);
        assetUpdate.setJade(partJade);
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public void type5(LoginResult loginResult, ChannelHandlerContext ctx, SuitOperBean suitOperBean) {
        if (suitOperBean.getGoods() == null || suitOperBean.getGoods().size() == 0) {
            return;
        }
        Goodstable goodstable = AllServiceUtil.getGoodsTableService().getGoodsByRgID((BigDecimal)suitOperBean.getGoods().get(0));
        if (goodstable == null) {
            return;
        }
        if (getExtra(goodstable.getValue(), SuitComposeAction.Extras[3]) == null) {
            return;
        }
        BigDecimal money = new BigDecimal(100000);
        if (loginResult.getGold().compareTo(money) < 0) {
            return;
        }
        loginResult.setGold(loginResult.getGold().subtract(money));
        MonitorUtil.getMoney().useD(money.longValue());
        Goodstable good = GameServer.getGood(goodstable.getGoodsid());
        goodstable.setGoodsname(good.getGoodsname());
        goodstable.setSkin(good.getSkin());
        goodstable.setValue(this.suitCancel(goodstable.getValue()));
        AllServiceUtil.getGoodsTableService().updateGoodRedis(goodstable);
        AllServiceUtil.getGoodsrecordService().insert(goodstable, null, Integer.valueOf(1), Integer.valueOf(13));
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setType(AssetUpdate.SUIT);
        assetUpdate.setGood(goodstable);
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public void type6(LoginResult loginResult, ChannelHandlerContext ctx, SuitOperBean suitOperBean) {
        if (suitOperBean.getGoods() == null || suitOperBean.getGoods().size() == 0) {
            return;
        }
        Goodstable good = AllServiceUtil.getGoodsTableService().getGoodsByRgID((BigDecimal)suitOperBean.getGoods().get(0));
        if (good == null) {
            return;
        }
        Goodstable good2 = AllServiceUtil.getGoodsTableService().getGoodsByRgID((BigDecimal)suitOperBean.getGoods().get(1));
        if (good2 == null) {
            return;
        }
        String extra = getExtra(good.getValue(), SuitComposeAction.Extras[3]);
        if (extra == null) {
            return;
        }
        if (getExtra(good2.getValue(), SuitComposeAction.Extras[3]) != null) {
            return;
        }
        String[] vs = extra.split("&");
        int lxzx = SuitMixdeal.getSxlxz(SuitMixdeal.getPZlvl(vs[3]));
        BigDecimal lxz = loginResult.getScoretype("灵修值");
        if (lxz.compareTo(new BigDecimal(lxzx)) < 0) {
            return;
        }
        BigDecimal money = new BigDecimal(10000000);
        if (loginResult.getGold().compareTo(money) < 0) {
            return;
        }
        loginResult.setScore(DrawnitemsAction.Splice(loginResult.getScore(), "灵修值=" + lxzx, 3));
        loginResult.setGold(loginResult.getGold().subtract(money));
        MonitorUtil.getMoney().useD(money.longValue());
        String name = good.getGoodsname();
        String skin = good.getSkin();
        Goodstable goodstable = GameServer.getGood(good.getGoodsid());
        good.setGoodsname(goodstable.getGoodsname());
        good.setSkin(goodstable.getSkin());
        good.setValue(this.suitCancel(good.getValue()));
        AllServiceUtil.getGoodsTableService().updateGoodRedis(good);
        AllServiceUtil.getGoodsrecordService().insert(good, null, Integer.valueOf(1), Integer.valueOf(13));
        good2.setGoodsname(name.split("·")[0] + "·" + good2.getGoodsname());
        good2.setSkin(skin);
        good2.setValue(this.suitGenerate(good2.getValue(), extra));
        AllServiceUtil.getGoodsTableService().updateGoodRedis(good2);
        AllServiceUtil.getGoodsrecordService().insert(good2, null, Integer.valueOf(1), Integer.valueOf(13));
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setType(AssetUpdate.SUIT);
        assetUpdate.setGood(good);
        assetUpdate.setGood(good2);
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public void type7(LoginResult loginResult, ChannelHandlerContext ctx, SuitOperBean suitOperBean) {
        RoleData data = RolePool.getRoleData(loginResult.getRole_id());
        if (data == null) {
            return;
        }
        PackRecord record = data.getPackRecord();
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setType(AssetUpdate.SUIT);
        PartJade jade = suitOperBean.getJade();
        int lxzv = 0;
        if (suitOperBean.getGoods() == null || suitOperBean.getGoods().size() == 0) {
            int pz = 0;
            int i = 1;
            while (i < 6) {
                if (jade.getJade(i) > 0) {
                    pz = i;
                    break;
                }
                else {
                    ++i;
                }
            }
            if (pz == 0) {
                return;
            }
            PartJade partJade = record.getPartJade(jade.getSuitid(), jade.getPartId());
            if (jade.getJade(pz) <= 0 || jade.getJade(pz) > partJade.getJade(pz)) {
                return;
            }
            partJade.setJade(pz, -jade.getJade(pz));
            record.setPartJade(partJade);
            assetUpdate.setJade(partJade);
            lxzv = SuitMixdeal.returnExcNum(pz) * jade.getJade(pz);
        }
        else {
            Goodstable good = AllServiceUtil.getGoodsTableService().getGoodsByRgID((BigDecimal)suitOperBean.getGoods().get(0));
            if (good == null) {
                return;
            }
            if (jade.getJade1() > (int)good.getUsetime() || jade.getJade1() <= 0) {
                return;
            }
            good.setUsetime(Integer.valueOf((int)good.getUsetime() - jade.getJade1()));
            lxzv = SuitMixdeal.returnExcNum(6) * jade.getJade1();
            AllServiceUtil.getGoodsTableService().updateGoodRedis(good);
            AllServiceUtil.getGoodsrecordService().insert(good, null, Integer.valueOf(jade.getJade1()), Integer.valueOf(13));
        }
        loginResult.setScore(DrawnitemsAction.Splice(loginResult.getScore(), "灵修值=" + lxzv, 2));
        assetUpdate.updata("灵修值=" + lxzv);
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public void type8(LoginResult loginResult, ChannelHandlerContext ctx, SuitOperBean suitOperBean) {
        RoleData data = RolePool.getRoleData(loginResult.getRole_id());
        if (data == null) {
            return;
        }
        int type = 0;
        PackRecord record = data.getPackRecord();
        PartJade jade = suitOperBean.getJade();
        int pz = 0;
        if (jade.getPartId() != 0) {
            int i = 1;
            while (i < 6) {
                if (jade.getJade(i) > 0) {
                    pz = i;
                    break;
                }
                else {
                    ++i;
                }
            }
            if (pz == 0) {
                type = 1;
            }
        }
        else {
            type = 2;
        }
        PartJade partJade = null;
        BigDecimal sxlxz = null;
        BigDecimal money = null;
        if (type == 0) {
            partJade = record.getPartJade(jade.getSuitid(), jade.getPartId());
            if (partJade.getJade(pz) <= 0) {
                return;
            }
            int sum = 0;
            String[] vs = record.getCollect(partJade.getSuitid());
            if (vs != null) {
                sum = vs.length;
            }
            sxlxz = new BigDecimal(50);
            money = new BigDecimal((sum + 1) * 10000000);
            BigDecimal lxz = loginResult.getScoretype("灵修值");
            if (lxz.compareTo(sxlxz) < 0) {
                return;
            }
            if (loginResult.getGold().compareTo(money) < 0) {
                return;
            }
        }
        else if (type == 1) {
            sxlxz = new BigDecimal(200);
            money = new BigDecimal(50000000);
            BigDecimal lxz2 = loginResult.getScoretype("灵修值");
            if (lxz2.compareTo(sxlxz) < 0) {
                return;
            }
            if (loginResult.getGold().compareTo(money) < 0) {
                return;
            }
        }
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setType(AssetUpdate.SUIT);
        if (type == 0) {
            partJade.setJade(pz, -1);
            record.setPartJade(partJade);
            assetUpdate.setJade(partJade);
            loginResult.setScore(DrawnitemsAction.Splice(loginResult.getScore(), "灵修值=" + sxlxz, 3));
            loginResult.setGold(loginResult.getGold().subtract(money));
            MonitorUtil.getMoney().useD(money.longValue());
            record.setCollect(partJade.getSuitid(), partJade.getPartId());
        }
        else if (type == 1) {
            loginResult.setScore(DrawnitemsAction.Splice(loginResult.getScore(), "灵修值=" + sxlxz, 3));
            loginResult.setGold(loginResult.getGold().subtract(money));
            MonitorUtil.getMoney().useD(money.longValue());
            record.setCollect(jade.getSuitid(), jade.getPartId());
        }
        else {
            record.setCollect(jade.getSuitid());
        }
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().packRecordAgreement(4 + record.getCollect()));
    }
    
    public void type9(LoginResult loginResult, ChannelHandlerContext ctx, SuitOperBean suitOperBean) {
        RoleData data = RolePool.getRoleData(loginResult.getRole_id());
        if (data == null) {
            return;
        }
        PackRecord record = data.getPackRecord();
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setType(AssetUpdate.SUIT);
        PartJade jade = suitOperBean.getJade();
        PartJade partJade = record.getPartJade(jade.getSuitid(), jade.getPartId());
        if (jade.getJade1() <= 0) {
            return;
        }
        BigDecimal sxlxz = new BigDecimal(jade.getJade1() * 10);
        BigDecimal money = new BigDecimal(jade.getJade1() * 10000000);
        BigDecimal lxz = loginResult.getScoretype("灵修值");
        if (lxz.compareTo(sxlxz) < 0) {
            return;
        }
        if (loginResult.getGold().compareTo(money) < 0) {
            return;
        }
        partJade.setJade(1, jade.getJade1());
        record.setPartJade(partJade);
        assetUpdate.setJade(partJade);
        loginResult.setScore(DrawnitemsAction.Splice(loginResult.getScore(), "灵修值=" + sxlxz, 3));
        loginResult.setGold(loginResult.getGold().subtract(money));
        MonitorUtil.getMoney().useD(money.longValue());
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public void type10(LoginResult loginResult, ChannelHandlerContext ctx, SuitOperBean suitOperBean) {
        BigDecimal money = new BigDecimal(100000);
        if (loginResult.getGold().compareTo(money) < 0) {
            return;
        }
        List<Goodstable> goods = getGoods(suitOperBean.getGoods(), loginResult.getRole_id(), 2);
        if (goods == null) {
            return;
        }
        Goodstable goodstable = AllServiceUtil.getGoodsTableService().getGoodsByRgID((BigDecimal)suitOperBean.getGoods().get(0));
        int type = Goodtype.EquipmentType(goodstable.getType());
        if (type != 0) {
            SendMessage.sendMessageToSlef(ctx, SuitComposeAction.CHECKTS6);
            return;
        }
        String extra = getExtra(((Goodstable)goods.get(0)).getValue(), SuitComposeAction.Extras[1]);
        if (extra == null) {
            extra = "炼器属性&0";
        }
        String[] v = extra.split("&");
        int kglvl = Integer.parseInt(v[1]);
        if (kglvl >= 5) {
            return;
        }
        int gl = 0;
        switch (kglvl) {
            case 0: {
                gl = 32;
                break;
            }
            case 1: {
                gl = 24;
                break;
            }
            case 2: {
                gl = 18;
                break;
            }
            case 3: {
                gl = 12;
                break;
            }
            case 4: {
                gl = 6;
                break;
            }
        }
        for (int i = 1; i < goods.size(); ++i) {
            ((Goodstable)goods.get(i)).goodxh(1);
        }
        loginResult.setGold(loginResult.getGold().subtract(money));
        MonitorUtil.getMoney().useD(money.longValue());
        if (gl > SuitComposeAction.random.nextInt(108)) {
            ++kglvl;
            ((Goodstable)goods.get(0)).setUsetime(Integer.valueOf(1));
            StringBuffer buffer = new StringBuffer();
            for (int j = 0; j < v.length; ++j) {
                if (buffer.length() != 0) {
                    buffer.append("&");
                }
                if (j == 1) {
                    buffer.append(kglvl);
                }
                else {
                    buffer.append(v[j]);
                }
            }
            ((Goodstable)goods.get(0)).setValue(newExtra(((Goodstable)goods.get(0)).getValue().split("\\|"), 1, buffer.toString()));
            AddGoodUtil.addGood(ctx, (Goodstable)goods.get(0));
            SendMessage.sendMessageToSlef(ctx, SuitComposeAction.CHECKTS1);
            saveGoods(goods, false);
        }
        else {
            ((Goodstable)goods.get(0)).setUsetime(Integer.valueOf(1));
            SendMessage.sendMessageToSlef(ctx, SuitComposeAction.CHECKTS2);
            saveGoods(goods, true);
        }
    }
    
    public void type11(LoginResult loginResult, ChannelHandlerContext ctx, SuitOperBean suitOperBean) {
        BigDecimal money = new BigDecimal(100000);
        int money2 = 0;
        if (loginResult.getGold().compareTo(money) < 0) {
            return;
        }
        List<Goodstable> goods = getGoods(suitOperBean.getGoods(), loginResult.getRole_id(), (suitOperBean.getType() == 13) ? 1 : 4);
        if (goods == null) {
            return;
        }
        Goodstable goodstable = AllServiceUtil.getGoodsTableService().getGoodsByRgID((BigDecimal)suitOperBean.getGoods().get(0));
        int type = Goodtype.EquipmentType(goodstable.getType());
        if (type != 0) {
            SendMessage.sendMessageToSlef(ctx, SuitComposeAction.CHECKTS6);
            return;
        }
        if (suitOperBean.getType() != 13) {
            int lock = 0;
            int size = 0;
            PartJade jade = suitOperBean.getJade();
            if (jade != null) {
                lock = jade.getSuitid();
                for (int i = 0; i < 6; ++i) {
                    if ((int)((double)lock / Math.pow(10.0, (double)i)) % 10 != 0) {
                        ++size;
                    }
                }
                if (size > 3) {
                    return;
                }
                if (size > 0) {
                    money2 += GameServer.getAllLianHua().get2000(size).getMoney();
                }
            }
            if (money2 > 0) {
                BigDecimal m2 = new BigDecimal(money2);
                loginResult.setCodecard(loginResult.getCodecard().subtract(m2));
                MonitorUtil.getMoney().useX(m2.longValue());
            }
            loginResult.setGold(loginResult.getGold().subtract(money));
            MonitorUtil.getMoney().useD(money.longValue());
            String extra = getExtra(((Goodstable)goods.get(0)).getValue(), SuitComposeAction.Extras[1]);
            if (extra == null) {
                extra = "炼器属性&0";
            }
            String[] v = extra.split("&");
            int kglvl = Integer.parseInt(v[1]);
            if (kglvl > 5) {
                return;
            }
            ++size;
            extra = RefinersV(v, kglvl, lock, size);
            String value = newExtra(((Goodstable)goods.get(0)).getValue().split("\\|"), 1, extra);
            ((Goodstable)goods.get(0)).setValue(value);
            QualityClBean clBean = new QualityClBean();
            clBean.setRgid(((Goodstable)goods.get(0)).getRgid());
            clBean.setType(-2);
            clBean.setNewAttr(extra);
            SendMessage.sendMessageToSlef(ctx, Agreement.ExtrattroperAgreement(GsonUtil.getGsonUtil().getgson().toJson(clBean)));
        }
        else {
            loginResult.setGold(loginResult.getGold().subtract(money));
            MonitorUtil.getMoney().useD(money.longValue());
            String value2 = newExtra(((Goodstable)goods.get(0)).getValue().split("\\|"), 1, null);
            ((Goodstable)goods.get(0)).setValue(value2);
            AddGoodUtil.addGood(ctx, (Goodstable)goods.get(0));
        }
        for (int j = 1; j < goods.size(); ++j) {
            ((Goodstable)goods.get(j)).goodxh(1);
        }
        saveGoods(goods, false);
    }
    
    public void type13(LoginResult loginResult, ChannelHandlerContext ctx, SuitOperBean suitOperBean) {
        BigDecimal money = new BigDecimal(100000);
        int money2 = 0;
        if (loginResult.getGold().compareTo(money) < 0) {
            return;
        }
        List<Goodstable> goods = getGoods(suitOperBean.getGoods(), loginResult.getRole_id(), (suitOperBean.getType() == 13) ? 1 : 4);
        if (goods == null) {
            return;
        }
        Goodstable goodstable = AllServiceUtil.getGoodsTableService().getGoodsByRgID((BigDecimal)suitOperBean.getGoods().get(0));
        int type = Goodtype.EquipmentType(goodstable.getType());
        if (type != 0) {
            SendMessage.sendMessageToSlef(ctx, SuitComposeAction.CHECKTS6);
            return;
        }
        if (suitOperBean.getType() != 13) {
            int lock = 0;
            int size = 0;
            PartJade jade = suitOperBean.getJade();
            if (jade != null) {
                lock = jade.getSuitid();
                for (int i = 0; i < 6; ++i) {
                    if ((int)((double)lock / Math.pow(10.0, (double)i)) % 10 != 0) {
                        ++size;
                    }
                }
                if (size > 3) {
                    return;
                }
                if (size > 0) {
                    money2 += GameServer.getAllLianHua().get2000(size).getMoney();
                }
            }
            if (money2 > 0) {
                BigDecimal m2 = new BigDecimal(money2);
                loginResult.setCodecard(loginResult.getCodecard().subtract(m2));
                MonitorUtil.getMoney().useX(m2.longValue());
            }
            loginResult.setGold(loginResult.getGold().subtract(money));
            MonitorUtil.getMoney().useD(money.longValue());
            String extra = getExtra(((Goodstable)goods.get(0)).getValue(), SuitComposeAction.Extras[1]);
            if (extra == null) {
                extra = "炼器属性&0";
            }
            String[] v = extra.split("&");
            int kglvl = Integer.parseInt(v[1]);
            if (kglvl > 5) {
                return;
            }
            ++size;
            extra = RefinersV(v, kglvl, lock, size);
            String value = newExtra(((Goodstable)goods.get(0)).getValue().split("\\|"), 1, extra);
            QualityClBean clBean = new QualityClBean();
            clBean.setRgid(((Goodstable)goods.get(0)).getRgid());
            clBean.setType(2);
            clBean.setNewAttr(extra);
            QualityCPool.getcPool().addExtra(clBean);
            SendMessage.sendMessageToSlef(ctx, Agreement.ExtrattroperAgreement(GsonUtil.getGsonUtil().getgson().toJson(clBean)));
        }
        else {
            loginResult.setGold(loginResult.getGold().subtract(money));
            MonitorUtil.getMoney().useD(money.longValue());
            String value2 = newExtra(((Goodstable)goods.get(0)).getValue().split("\\|"), 1, null);
            ((Goodstable)goods.get(0)).setValue(value2);
            AddGoodUtil.addGood(ctx, (Goodstable)goods.get(0));
        }
        for (int j = 1; j < goods.size(); ++j) {
            ((Goodstable)goods.get(j)).goodxh(1);
        }
        saveGoods(goods, false);
    }
    
    public void type14(LoginResult loginResult, ChannelHandlerContext ctx, SuitOperBean suitOperBean) {
        BigDecimal money = new BigDecimal(100000);
        int money2 = 0;
        if (loginResult.getGold().compareTo(money) < 0) {
            return;
        }
        List<Goodstable> goods = getGoods(suitOperBean.getGoods(), loginResult.getRole_id(), 2);
        if (goods == null || goods.size() < 1) {
            return;
        }
        int max = 5;
        int lock = 0;
        if (Goodtype.GodEquipment_xian(((Goodstable)goods.get(0)).getType()) || Goodtype.GodEquipment_Ding(((Goodstable)goods.get(0)).getType())) {
            max = Config.getInt("xq_lianhua");
            long type = ((Goodstable)goods.get(1)).getType();
            if (!Goodtype.GodEquipment_xian(type) && !Goodtype.xianlihe(type) && type != 7010L) {
                return;
            }
        }
        else if (Goodtype.GodEquipment_God(((Goodstable)goods.get(0)).getType())) {
            max = Config.getInt("sb_lianhua");
            if (((Goodstable)goods.get(1)).getType() != 191L) {
                return;
            }
        }
        else if (Goodtype.OrdinaryEquipment(((Goodstable)goods.get(0)).getType())) {
            max = Config.getInt("pt_lianhua");
        }
        else if (Goodtype.Amulet(((Goodstable)goods.get(0)).getType())) {
            max = 5;
        }
        else if (goods.size() == 6) {
            if (((Goodstable)goods.get(1)).getType() != 497L || ((Goodstable)goods.get(2)).getType() != 499L || ((Goodstable)goods.get(3)).getType() != 498L || ((Goodstable)goods.get(4)).getType() != 498L || ((Goodstable)goods.get(5)).getType() != 498L) {
                return;
            }
        }
        else {
            return;
        }
        if (GameServer.lianhua == 0 && max != 1) {
            PartJade jade = suitOperBean.getJade();
            if (jade != null) {
                lock = jade.getSuitid();
                int size = 0;
                for (int i = 0; i < 8; ++i) {
                    if ((int)((double)lock / Math.pow(10.0, (double)i)) % 10 != 0) {
                        ++size;
                    }
                }
                if (size > 0) {
                    money2 += GameServer.getAllLianHua().get1000(size).getMoney();
                }
            }
        }
        loginResult.setGold(loginResult.getGold().subtract(money));
        MonitorUtil.getMoney().useD(money.longValue());
        if (money2 != 0) {
            BigDecimal m2 = new BigDecimal(money2);
            loginResult.setCodecard(loginResult.getCodecard().subtract(m2));
            MonitorUtil.getMoney().useX(m2.longValue());
        }
        String extra = RefinersV((Goodstable)goods.get(0), max, lock);
        QualityClBean clBean = new QualityClBean();
        clBean.setRgid(((Goodstable)goods.get(0)).getRgid());
        clBean.setType(1);
        if (Goodtype.OrdinaryEquipment(((Goodstable)goods.get(0)).getType())) {
            clBean.setNewAttr(extra + "|制作人=" + loginResult.getRolename());
        }
        else {
            clBean.setNewAttr(extra);
        }
        QualityCPool.getcPool().addExtra(clBean);
        SendMessage.sendMessageToSlef(ctx, Agreement.ExtrattroperAgreement(GsonUtil.getGsonUtil().getgson().toJson(clBean)));
        for (int i = 1; i < goods.size(); ++i) {
            ((Goodstable)goods.get(i)).goodxh(1);
        }
        saveGoods(goods, true);
    }
    
    public void type15(LoginResult loginResult, ChannelHandlerContext ctx, SuitOperBean suitOperBean) {
        BigDecimal money = new BigDecimal(100000);
        List<Goodstable> goods = getGoods(suitOperBean.getGoods(), loginResult.getRole_id(), 2);
        if (goods == null) {
            return;
        }
        if (suitOperBean.getJade() == null) {
            return;
        }
        int sum = suitOperBean.getJade().getJade1();
        if (sum <= 0) {
            return;
        }
        if (goods.get(1).getUsetime() < sum) {
            return;
        }
        money = new BigDecimal(money.longValue() * (long)sum);
        if (loginResult.getGold().compareTo(money) < 0) {
            return;
        }
        loginResult.setGold(loginResult.getGold().subtract(money));
        MonitorUtil.getMoney().useD(money.longValue());
        goods.get(1).goodxh(sum);
        BigDecimal ysid = goods.get(0).getGoodsid();
        AccC(goods.get(0), sum);
        BigDecimal nsid = goods.get(0).getGoodsid();
        goods.get(0).setGoodsid(nsid);
        Goodstable good = GameServer.getGood(nsid);
        goods.get(0).setGoodsname(good.getGoodsname());
        saveGoods(goods, true);
        AllServiceUtil.getGoodsTableService().updateGoodsIndex(goods.get(0), null, nsid, null);
        AllServiceUtil.getGoodsrecordService().insert(goods.get(0), null, Integer.valueOf(1), Integer.valueOf(13));
        AddGoodUtil.addGood(ctx, goods.get(0));
    }
    
    public void type16(LoginResult loginResult, ChannelHandlerContext ctx, SuitOperBean suitOperBean) {
        ConcurrentHashMap<Integer, Configure> s = GameServer.getAllConfigure();
        Configure configure = s.get(Integer.valueOf(1));
        BigDecimal money = new BigDecimal(100000);
        List<Goodstable> goods = getGoods(suitOperBean.getGoods(), loginResult.getRole_id(), 2);
        if (goods == null) {
            return;
        }
        if (loginResult.getGold().compareTo(money) < 0) {
            return;//金钱不足
        }
        int l = 0;
        if (Goodtype.GodEquipment_God(goods.get(1).getType()) || goods.get(1).getGoodsid().longValue() == 80042L) {
            l = 2;
        }
        String[] vs = goods.get(0).getValue().split("\\|");
        int ylvl = Integer.parseInt(vs[0].split("=")[1]);
        if (l == 2 && ylvl < Integer.parseInt(configure.getSbjldj())) {//一次性培养的数量
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement(configure.getSbjldj() + "级及以上神兵才可以精炼"));
            return;
        }
        int v = 1;
        switch (ylvl) {
            case 1: {
                v = ((l == 2) ? 7000 : 7000);
                break;
            }
            case 2: {
                v = ((l == 2) ? 3500 : 3500);
                break;
            }
            case 3: {
                v = ((l == 2) ? 1500 : 1500);
                break;
            }
            case 4: {
                v = ((l == 2) ? 150 : 150);
                break;
            }
            case 5: {
                v = ((l == 2) ? 55 : 55);
                break;
            }
            case 6: {
                v = ((l == 2) ? 1 : 1);
                break;
            }
            case 7: {
                v = ((l == 2) ? 300 : 300);
                break;
            }
            case 8: {
                v = ((l == 2) ? 300 : 300);
                break;
            }
            case 9: {
                v = ((l == 2) ? 300 : 300);
                break;
            }
            case 10: {
                v = ((l == 2) ? 150 : 150);
                break;
            }
            case 11: {
                v = ((l == 2) ? 75 : 75);
                break;
            }
            case 12: {
                v = ((l == 2) ? 35 : 35);
                break;
            }
            case 13: {
                v = ((l == 2) ? 17 : 17);
                break;
            }
            case 14: {
                v = ((l == 2) ? 8 : 8);
                break;
            }
        }
        int jv = 8000;
        if (configure.getSbjlsz() != null && configure.getSbjlsz() != "") {
            jv = Integer.parseInt(configure.getSbjlsz());
        }
        if (v > SuitComposeAction.random.nextInt(jv)) {//成功
            if (++ylvl > Integer.parseInt(configure.getSbzddj())) {
                ylvl = Integer.parseInt(configure.getSbzddj());
            }
            goods.get(0).setValue(SuitMixdeal.GetGodEquipment(vs, ylvl));
            SuitMixdeal.sbsj(ylvl, loginResult.getRolename(), goods.get(0).getGoodsname());
            l = 1;
            SendMessage.sendMessageToSlef(ctx, SuitComposeAction.CHECKTS3);
            AchievemUtil.detectionAchievem(loginResult, "升级一件神兵");
//            AchievemUtil.detectionAchievem(loginResult, "升级十件神兵");
            if (ylvl >= 2) {
                AchievemUtil.detectionAchievem(loginResult, "神兵升到"+ylvl+"级");
            }
        }
        else {//失败
            SendMessage.sendMessageToSlef(ctx, SuitComposeAction.CHECKTS4);
            AchievemUtil.detectionAchievem(loginResult, "升级失败一件神兵");
        }
        loginResult.setGold(loginResult.getGold().subtract(money));
        MonitorUtil.getMoney().useD(money.longValue());
        for (int i = (l != 0) ? 1 : 0; i < goods.size(); ++i) {
            goods.get(i).goodxh(1);
        }
        saveGoods(goods, l == 2);
        if (l != 0) {
            AddGoodUtil.addGood(ctx, goods.get(0));
        }
    }
    /**禁交易判断 is0不能禁交易与非禁交易合成  1可以 -1存在禁交易物品 0不修改物品贵重   其他值修改物品对应贵重*/
    public static int isTrans(List<Goodstable> list, int is) {
        boolean isT = AnalysisString.jiaoyi(list.get(0).getQuality());
        int value = -1;
        int i = 1;
        int length = list.size();
        while (i < length) {
            if (isT != AnalysisString.jiaoyi((long)((Goodstable)list.get(i)).getQuality())) {
                value = ((Goodstable)list.get(i)).getQuality().intValue();
                break;
            }
            else {
                ++i;
            }
        }
        if (value == -1) {
            return 0;
        }
        if (is == 0) {
            return -1;
        }
        if (isT) {
            return 0;
        }
        return value;
    }
    
    public static List<Goodstable> getGoods(List<BigDecimal> rgids, BigDecimal role_id, int min) {
        if (rgids == null)
            return null; 
          List<Goodstable> goods = new ArrayList<>();
          int i;
          LOOP: for (i = 0; i < rgids.size(); i++) {
            BigDecimal id = rgids.get(i);
            for (int j = 0; j < goods.size(); j++) {
              if (((Goodstable)goods.get(j)).getRgid().compareTo(id) == 0) {
                goods.add(goods.get(j));
                continue LOOP;
              } 
            } 
            Goodstable good = AllServiceUtil.getGoodsTableService().getGoodsByRgID(id);
            if (good == null || good.getRole_id().compareTo(role_id) != 0)
              return null; 
            goods.add(good);
          } 
          if (min > goods.size())
            return null; 
          return goods;
    }
    
    public static void saveGoods(List<Goodstable> goods, boolean l) {
    	int i;
        LOOP: for (i = l ? 1 : 0; i < goods.size(); i++) {
          Goodstable good = goods.get(i);
          BigDecimal id = good.getRgid();
          for (int j = i + 1; j < goods.size(); j++) {
            if (((Goodstable)goods.get(j)).getRgid().compareTo(id) == 0)
              continue LOOP; 
          } 
          AllServiceUtil.getGoodsTableService().updateGoodRedis(good);
          long gType = good.getType();
          if (gType != 212L && (gType < 497L || gType > 500L) && gType != 505L && gType != 7005L && gType != 191L && gType != 915L)
            AllServiceUtil.getGoodsrecordService().insert(good, null, Integer.valueOf(1), Integer.valueOf(13)); 
        } 
    }
    
    public static void AccC(Goodstable good, int size) {
        String[] qs = good.getValue().split("\\|");
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < qs.length; ++i) {
            if (buffer.length() != 0) {
                buffer.append("|");
            }
            if (qs[i].length() >= 2 && qs[i].substring(0, 2).equals("培养")) {
                String[] num = qs[i].split("=")[1].split("/");
                int value = Integer.parseInt(num[0]);
                int max = Integer.parseInt(num[1]);
                value += size;
                if (value > max) {
                    Goodstable good2 = GameServer.getGood(good.getGoodsid().add(new BigDecimal(1)));
                    if (((Goodstable)Objects.requireNonNull(good2)).getValue().startsWith(qs[0])) {
                        good2 = GameServer.getGood(good.getGoodsid().add(new BigDecimal(2)));
                    }
                    good.setGoodsid(((Goodstable)Objects.requireNonNull(good2)).getGoodsid());
                    good.setSkin(good2.getSkin());
                    buffer.setLength(0);
                    buffer.append(NpcComposeAction.getGrouGoodsAttribute(good, good2));
                    break;
                }
                else {
                    buffer.append("培养=");
                    buffer.append(value);
                    buffer.append("/");
                    buffer.append(max);
                }
            }
            else {
                buffer.append(qs[i]);
            }
        }
        good.setValue(buffer.toString());
    }
    
    public static String LHRefinersV(String value, int max, int lock) {
        double xs = 0.0;
        List<String> TJS = null;
        StringBuffer buffer = new StringBuffer();
        buffer.append("炼化属性");
        String[] vl = value.split("\\|");
        long Gtype = 9999L;
        int lvl = 0;
        if (Gtype == 9999L) {
            lvl = 5;
            lvl = lvl / 2 + 3;
        }
        else if (Goodtype.isPalEquip(Gtype)) {
            lvl = 3 + Integer.parseInt(vl[0].split("=")[1]) / 2;
        }
        else {
            lvl = Integer.parseInt(vl[0].split("=")[1]);
            if (Goodtype.OrdinaryEquipment(Gtype)) {
                lvl -= 10;
            }
        }
        if (Gtype == 612L) {
            for (int i = 0; i < vl.length; ++i) {
                String[] vsz = vl[i].split("=");
                if (vsz[0].equals("等级")) {
                    lvl = Integer.parseInt(vsz[1]);
                }
                else if (vsz[0].equals("品质")) {
                    xs = (double)Integer.parseInt(vsz[1].split("/")[0]);
                }
            }
            xs = xs / 1000.0 * (double)lvl / 6.0;
        }
        int ZBType = Goodtype.EquipmentType(Gtype);
        String etype = SuitMixdeal.lianhua(Gtype);
        if (lvl > 6 || lvl <= 0) {
            lvl = 1;
        }
        if (etype == null) {
            return null;
        }
        List<Alchemy> alchemies = (List<Alchemy>)GameServer.getAllAlchemy().get(etype);
        int size = SuitMixdeal.getAlchemySum(Gtype, max);
        String[] as = new String[5];
        int p = 0;
        if (lock != 0) {
            String extra = getExtra(vl, SuitComposeAction.Extras[0]);
            if (extra != null) {
                int t = -1;
                String[] extras = extra.split("&");
                if (extras[extras.length - 1].startsWith("特技")) {
                    t = extras.length - 1;
                }
                for (int j = 0; j < 6; ++j) {
                    if ((int)((double)lock / Math.pow(10.0, (double)j)) % 10 != 0) {
                        if (t == -1 || j < t - 1) {
                            if (j < extras.length - 1 && !extras[j + 1].startsWith("特技")) {
                                as[p++] = extras[j + 1];
                            }
                        }
                        else if (t != -1) {
                            String[] tjs = extras[t].split("=");
                            if (j - t + 2 < tjs.length) {
                                if (TJS == null) {
                                    TJS = Lists.newArrayList(new String[] { null, null });
                                }
                                int k = 0;
                                while (k < TJS.size()) {
                                    if (TJS.get(k) == null) {
                                        TJS.set(k, tjs[j - t + 2]);
                                        break;
                                    }
                                    else {
                                        ++k;
                                    }
                                }
                            }
                        }
                    }
                }
                if (p >= size) {
                    size = p + 1;
                }
                if (size > max) {
                    size = max;
                }
            }
        }
        else if (lvl > 1 && SuitComposeAction.random.nextInt(5) <= 1) {
            --lvl;
        }
        int u = 0;
        for (int l = p; l < as.length; ++l) {
            Alchemy alchemy = null;
            boolean a = true;
            while (a) {
                u = 0;
                a = false;
                int v = SuitComposeAction.random.nextInt(alchemies.size());
                alchemy = (Alchemy)alchemies.get(v);
                for (int k = 0; k < as.length; ++k) {
                    if (as[k] != null && as[k].startsWith(alchemy.getAlchemykey())) {
                        ++u;
                        if (Gtype == 612L) {
                            if (u == 1) {
                                a = true;
                            }
                            else if (SuitComposeAction.random.nextInt(11) <= 4) {
                                a = true;
                            }
                        }
                        else if (u >= 4) {
                            a = true;
                        }
                        else if (SuitComposeAction.random.nextInt(11) <= 4) {
                            a = true;
                        }
                    }
                }
            }
            as[p++] = lh(lvl, alchemy, xs);
        }
        for (int l = 0; l < size; ++l) {
            if (as[l] != null) {
                buffer.append("&");
                buffer.append(as[l]);
            }
        }
        if (TJS == null) {
            if (ZBType == 0) {
                TJS = SuitComposeAction.TJ;
            }
            else if (ZBType == 4) {
                TJS = SuitComposeAction.HSTJ;
            }
            else if (ZBType == 3) {
                TJS = SuitComposeAction.YFTJ;
            }
            else if (ZBType == 2) {
                TJS = SuitComposeAction.XLTJ;
            }
            else if (ZBType == 1) {
                TJS = SuitComposeAction.MZTJ;
            }
            else if (ZBType == 5) {
                TJS = SuitComposeAction.XTJ;
            }
            if (TJS != null) {
                size = SuitMixdeal.getTJSum(Gtype);
                if (size != 0) {
                    int y = -1;
                    buffer.append("&特技");
                    for (int m = 0; m < size; ++m) {
                        int sj = SuitComposeAction.random.nextInt(TJS.size());
                        if (sj != y) {
                            y = sj;
                            buffer.append("=");
                            String s = (String)TJS.get(sj);
                            buffer.append(s);
                        }
                    }
                    if (buffer.toString().contains("8055")) {
                        buffer.append("&冰刃术伤害=" + new Random().nextInt(20000));
                    }
                }
            }
        }
        else {
            buffer.append("&特技");
            for (int l = 0; l < TJS.size(); ++l) {
                if (TJS.get(l) != null) {
                    buffer.append("=");
                    buffer.append((String)TJS.get(l));
                }
            }
        }
        return buffer.toString();
    }
    
    public static String RefinersV(Goodstable good, int max, int lock) {
        double xs = 0.0;
        List<String> TJS = null;
        StringBuffer buffer = new StringBuffer();
        buffer.append("炼化属性");
        String[] vl = good.getValue().split("\\|");
        long Gtype = good.getType();
        int lvl = 0;
        if (Gtype == 8888L) {
            lvl = SuitMixdeal.getPZlvl(vl[0].split("=")[1]);
            lvl = lvl / 2 + 3;
        }
        else if (Goodtype.isPalEquip(Gtype)) {
            lvl = 3 + Integer.parseInt(vl[0].split("=")[1]) / 2;
        }
        else {
            lvl = Integer.parseInt(vl[0].split("=")[1]);
            if (Goodtype.OrdinaryEquipment(Gtype)) {
                lvl -= 10;
            }
        }
        if (Gtype == 612L) {
            for (int i = 0; i < vl.length; ++i) {
                String[] vsz = vl[i].split("=");
                if (vsz[0].equals("等级")) {
                    lvl = Integer.parseInt(vsz[1]);
                }
                else if (vsz[0].equals("品质")) {
                    xs = (double)Integer.parseInt(vsz[1].split("/")[0]);
                }
            }
            xs = xs / 1000.0 * (double)lvl / 6.0;
        }
        int ZBType = Goodtype.EquipmentType(Gtype);
        String etype = SuitMixdeal.lianhua(Gtype);
        if (lvl > 6 || lvl <= 0) {
            lvl = 1;
        }
        if (etype == null) {
            return null;
        }
        List<Alchemy> alchemies = (List<Alchemy>)GameServer.getAllAlchemy().get(etype);
        int size = SuitMixdeal.getAlchemySum(Gtype, max);
        String[] as = new String[5];
        int p = 0;
        if (lock != 0) {
            String extra = getExtra(vl, SuitComposeAction.Extras[0]);
            if (extra != null) {
                int t = -1;
                String[] extras = extra.split("&");
                if (extras[extras.length - 1].startsWith("特技")) {
                    t = extras.length - 1;
                }
                for (int j = 0; j < 6; ++j) {
                    if ((int)((double)lock / Math.pow(10.0, (double)j)) % 10 != 0) {
                        if (t == -1 || j < t - 1) {
                            if (j < extras.length - 1 && !extras[j + 1].startsWith("特技")) {
                                as[p++] = extras[j + 1];
                            }
                        }
                        else if (t != -1) {
                            String[] tjs = extras[t].split("=");
                            if (j - t + 2 < tjs.length) {
                                if (TJS == null) {
                                    TJS = Lists.newArrayList(new String[] { null, null });
                                }
                                int k = 0;
                                while (k < TJS.size()) {
                                    if (TJS.get(k) == null) {
                                        TJS.set(k, tjs[j - t + 2]);
                                        break;
                                    }
                                    else {
                                        ++k;
                                    }
                                }
                            }
                        }
                    }
                }
                if (p >= size) {
                    size = p + 1;
                }
                if (size > max) {
                    size = max;
                }
            }
        }
        else if (lvl > 1 && SuitComposeAction.random.nextInt(5) <= 1) {
            --lvl;
        }
        int u = 0;
        for (int l = p; l < as.length; ++l) {
            Alchemy alchemy = null;
            boolean a = true;
            while (a) {
                u = 0;
                a = false;
                int v = SuitComposeAction.random.nextInt(alchemies.size());
                alchemy = (Alchemy)alchemies.get(v);
                for (int k = 0; k < as.length; ++k) {
                    if (as[k] != null && as[k].startsWith(alchemy.getAlchemykey())) {
                        ++u;
                        if (Gtype == 612L) {
                            if (u == 1) {
                                a = true;
                            }
                            else if (SuitComposeAction.random.nextInt(11) <= 4) {
                                a = true;
                            }
                        }
                        else if (u >= 2) {
                            a = true;
                        }
                        else if (SuitComposeAction.random.nextInt(11) <= 4) {
                            a = true;
                        }
                    }
                }
            }
            as[p++] = lh(lvl, alchemy, xs);
        }
        for (int l = 0; l < size; ++l) {
            if (as[l] != null) {
                buffer.append("&");
                buffer.append(as[l]);
            }
        }
        if (TJS == null) {
            if (ZBType == 0) {
                TJS = SuitComposeAction.TJ;
            }
            else if (ZBType == 4) {
                TJS = SuitComposeAction.HSTJ;
            }
            else if (ZBType == 3) {
                TJS = SuitComposeAction.YFTJ;
            }
            else if (ZBType == 2) {
                TJS = SuitComposeAction.XLTJ;
            }
            else if (ZBType == 1) {
                TJS = SuitComposeAction.MZTJ;
            }
            else if (ZBType == 5) {
                TJS = SuitComposeAction.XTJ;
            }
            if (TJS != null) {
                size = SuitMixdeal.getTJSum(Gtype);
                if (size != 0) {
                    int y = -1;
                    buffer.append("&特技");
                    for (int m = 0; m < size; ++m) {
                        int sj = SuitComposeAction.random.nextInt(TJS.size());
                        if (sj != y) {
                            y = sj;
                            buffer.append("=");
                            String s = (String)TJS.get(sj);
                            buffer.append(s);
                        }
                    }
                    if (buffer.toString().contains("8055")) {
                        buffer.append("&冰刃术伤害=" + new Random().nextInt(20000));
                    }
                }
            }
        }
        else {
            buffer.append("&特技");
            for (int l = 0; l < TJS.size(); ++l) {
                if (TJS.get(l) != null) {
                    buffer.append("=");
                    buffer.append((String)TJS.get(l));
                }
            }
        }
        return buffer.toString();
    }
    
    public static String RefinersV(String[] vs, int lvl, int lock, int min) {
        if (min > 5) {
            min = 5;
        }
        int sum = RefinersS(lvl);
        if (min > sum) {
            sum = min;
        }
        List<Alchemy> alchemies = (List<Alchemy>)GameServer.getAllAlchemy().get("武器开光");
        StringBuffer buffer = new StringBuffer();
        buffer.append("炼器属性&");
        buffer.append(lvl);
        List<String> a = new ArrayList<>();
        for (int i = 0; i < 5; ++i) {
            if ((int)((double)lock / Math.pow(10.0, (double)i)) % 10 != 0) {
                int v = i + 2;
                if (v < vs.length) {
                    a.add(vs[v].split("=")[0]);
                }
            }
        }
        int vvv = 0;
 
	    for (int j = 0; j < sum; j++) {
	      if ((int)(lock / Math.pow(10.0D, j)) % 10 != 0) {
	        int v = j + 2;
	        if (v < vs.length) {
	          buffer.append("&");
	          buffer.append(vs[v]);
	          continue;
	        } 
	      } 
	      Alchemy alchemy = null;
	      vvv++;
	      LOOP: while (vvv <= 50000) {
	        int size = 0;
	        int v = random.nextInt(alchemies.size());
	        alchemy = alchemies.get(v);
	        for (int k = 0; k < a.size(); k++) {
	          if (((String)a.get(k)).equals(alchemy.getAlchemykey())) {
//	            size++;//炼器双叠加
//	            if (random.nextInt(5) <= 1)
//	              continue LOOP;
//	            if (size >= 2)
	              continue LOOP; 
	          } 
	        } 
	        break;
	      } 
	      a.add(alchemy.getAlchemykey());
	      buffer.append("&");
	      buffer.append(lh(2 + (lvl + 1) / 2, alchemy, 0.0D));
	      continue;
	    } 
    
        for (int j = sum; j < 5; ++j) {
            if ((int)((double)lock / Math.pow(10.0, (double)j)) % 10 != 0) {
                int v2 = j + 2;
                if (v2 < vs.length) {
                    buffer.append("&");
                    buffer.append(vs[v2]);
                }
            }
        }
        return buffer.toString();
    }
    
    public static int RefinersS(int max) {
        int size = 1;
        if (SuitComposeAction.random.nextInt(100) < 80) {
            ++size;
        }
        if (SuitComposeAction.random.nextInt(100) < 70) {
            ++size;
        }
        if (max > 3) {
            if (SuitComposeAction.random.nextInt(100) < 60) {
                ++size;
            }
            if (max > 4 && SuitComposeAction.random.nextInt(100) < 50) {
                ++size;
            }
        }
        return (max > size) ? size : max;
    }
    
    public String SuitV(int suitid, int partid, int pz) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("套装属性&");
        buffer.append(suitid);
        buffer.append("&");
        buffer.append(partid);
        buffer.append("&");
        buffer.append(SuitMixdeal.getPZName(pz));
        List<Alchemy> alchemies = (List<Alchemy>)GameServer.getAllAlchemy().get("套装");
        int v = SuitComposeAction.random.nextInt(alchemies.size());
        buffer.append("&");
        buffer.append(lh(pz, (Alchemy)alchemies.get(v), 0.0));
        if (40 > SuitComposeAction.random.nextInt(100)) {
            int v2 = SuitComposeAction.random.nextInt(alchemies.size());
            if (v2 != v) {
                buffer.append("&");
                buffer.append(lh(pz, (Alchemy)alchemies.get(v2), 0.0));
            }
        }
        return buffer.toString();
    }
    
    public static void main(String[] args) {
        double min = 1.0;
        double max = 10.0;
        int lvl = 5;
        max = (max - min) / 6.0 * (double)lvl + min;
        for (int i = 0; i < 100; ++i) {
            System.out.println(max + ":" + getDouble(min, max, 0));
        }
    }
    
    public static String lh(int lvl, Alchemy alchemy, double xs) {
        String key = alchemy.getAlchemykey();
        int size = 1;
        if (key.equals("力量") || key.equals("根骨") || key.equals("灵性") || key.equals("敏捷") || key.equals("附加攻击") || key.equals("加速度") || key.equals("附加速度") || key.equals("附加气血") || key.equals("加气血") || key.equals("加魔法") || key.equals("附加魔法") || key.equals("反击次数") || key.equals("连击次数") || key.equals("加强三尸虫")) {
            size = 0;
        }
        String newv = null;
        double min = Double.parseDouble(alchemy.getAlchemysv());
        double max = Double.parseDouble(alchemy.getAlchemymv());
        if (xs != 0.0) {
            max *= xs;
            max = getDouble(max, max, size);
        }
        else {
            min += (max - min) / 6.0 * (double)(lvl - 1) / 2.0;
            max = getDouble(min, max, size);
        }
        if (size == 0) {
            newv = key + "=" + (max + "").split("\\.")[0];
        }
        else {
            newv = key + "=" + max;
        }
        return newv;
    }
    
    public static double getDouble(double min, double max, int type) {
        StringBuffer a = new StringBuffer();
        for (int i = 0; i < type; ++i) {
            a.append("0");
        }
        max -= min;
        max = max / 4.0 * (double)SuitComposeAction.random.nextInt(5);
        DecimalFormat df = new DecimalFormat("#." + a);
        double b = (double)Double.valueOf(df.format(SuitComposeAction.random.nextDouble() * max + min));
        return b;
    }
    
    public static String newExtra(String[] v, int type, String newEx) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < v.length; ++i) {
            if (v[i].startsWith(SuitComposeAction.Extras[type])) {
                if (newEx != null && !newEx.equals("")) {
                    if (i != 0) {
                        buffer.append("|");
                    }
                    buffer.append(newEx);
                    newEx = null;
                }
            }
            else {
                if (i != 0) {
                    buffer.append("|");
                }
                buffer.append(v[i]);
            }
        }
        if (newEx != null) {
            buffer.append("|");
            buffer.append(newEx);
        }
        return buffer.toString();
    }
    
    public static String getExtra(String value, String extra) {
        String[] v = value.split("\\|");
        return getExtra(v, extra);
    }
    
    public static String getExtra(String[] v, String extra) {
        for (int i = 0; i < v.length; ++i) {
            if (v[i].startsWith(extra)) {
                return v[i];
            }
        }
        return null;
    }
    
    public String suitGenerate(String value, String extra) {
        String[] extras = extra.split("&");
        int suitid = Integer.parseInt(extras[1]);
        Suit suit = GameServer.getSuit(suitid);
        StringBuffer buffer = new StringBuffer();
        buffer.append("套装品质=");
        buffer.append(extras[3]);
        buffer.append("|装备部位=");
        buffer.append(extras[2]);
        if (value.indexOf("性别要求") == -1) {
            buffer.append("|性别要求=");
            buffer.append(suit.getSysex());
        }
        buffer.append("|");
        buffer.append(value);
        buffer.append("|");
        buffer.append(extra);
        return buffer.toString();
    }
    
    public String suitCancel(String value) {
        String[] vs = value.split("\\|");
        StringBuffer buffer = new StringBuffer();
        String e = "0";
        for (int i = 0; i < vs.length; ++i) {
            if (!StringUtil.isNullOrEmpty(vs[i]) && !vs[i].startsWith("套装品质")) {
                if (vs[i].startsWith("装备部位")) {
                    e = vs[i].split("=")[1];
                }
                else if ((i != 2 || !vs[i].startsWith("性别要求")) && !vs[i].startsWith(SuitComposeAction.Extras[3])) {
                    if (buffer.toString().length() > 0) {
                        buffer.append("|");
                    }
                    buffer.append(vs[i]);
                }
            }
        }
        return buffer.toString();
    }
    
    public void type120(LoginResult loginResult, ChannelHandlerContext ctx, SuitOperBean suitOperBean) {
        BigDecimal money = new BigDecimal(100000);
        if (loginResult.getGold().compareTo(money) < 0) {
            return;
        }
        List<Goodstable> goods = getGoods(suitOperBean.getGoods(), loginResult.getRole_id(), 3);
        if (goods == null || goods.size() < 1) {
            return;
        }
        String[] vvs = ((Goodstable)goods.get(0)).getValue().split("\\|");
        List<String> value = new ArrayList<>();
        int buwei = 0;
        buwei = Goodtype.EquipmentType(((Goodstable)goods.get(0)).getType());
        for (String dengji : vvs) {
            if (dengji.split("=")[0].equals("装备部位")) {
                String s2 = dengji.split("=")[1];
                int n = -1;
                switch (s2.hashCode()) {
                    case 23904155: {
                        if (s2.equals("左戒指")) {
                            n = 0;
                            break;
                        }
                        else {
                            break;
                        }
                    }
                    case 21456488: {
                        if (s2.equals("右戒指")) {
                            n = 1;
                            break;
                        }
                        else {
                            break;
                        }
                    }
                    case 683136: {
                        if (s2.equals("全部")) {
                            n = 2;
                            break;
                        }
                        else {
                            break;
                        }
                    }
                    case 1222229: {
                        if (s2.equals("面具")) {
                            n = 3;
                            break;
                        }
                        else {
                            break;
                        }
                    }
                    case 822147: {
                        if (s2.equals("披风")) {
                            n = 4;
                            break;
                        }
                        else {
                            break;
                        }
                    }
                    case 1051318: {
                        if (s2.equals("腰带")) {
                            n = 5;
                            break;
                        }
                        else {
                            break;
                        }
                    }
                    case 805940: {
                        if (s2.equals("挂件")) {
                            n = 6;
                            break;
                        }
                        else {
                            break;
                        }
                    }
                }
                switch (n) {
                    case 0:
                    case 1:
                    case 2: {
                        buwei = 10;
                        break;
                    }
                    case 3: {
                        buwei = 6;
                        break;
                    }
                    case 4: {
                        buwei = 8;
                        break;
                    }
                    case 5: {
                        buwei = 7;
                        break;
                    }
                    case 6: {
                        buwei = 9;
                        break;
                    }
                    default: {
                        buwei = Integer.parseInt(dengji.split("=")[1]);
                        break;
                    }
                }
            }
        }
        if (buwei == -1) {
            buwei = 10;
        }
        for (String s : vvs) {
            if (!s.startsWith("点粹属性") && !s.startsWith("炼化属性&特技") && !s.startsWith("点翠属性")) {
                value.add(s);
            }
        }
        value.removeIf(bb/* java.lang.String, */ -> bb.equals(""));
        StringBuilder mes = null;
        String extra = null;
        boolean jiezhi = false;
        boolean mianju = false;
        boolean yaodai = false;
        boolean guajian = false;
        boolean pifeng = false;
        for (String vv : value) {
            if (vv.startsWith("培养")) {
                String mes2 = null;
                String mes3 = null;
                String s3 = (GameServer.getPartsName(buwei) != null) ? GameServer.getPartsName(buwei) : null;
                int n2 = -1;
                switch (s3.hashCode()) {
                    case 23904155: {
                        if (s3.equals("左戒指")) {
                            n2 = 0;
                            break;
                        }
                        else {
                            break;
                        }
                    }
                    case 21456488: {
                        if (s3.equals("右戒指")) {
                            n2 = 1;
                            break;
                        }
                        else {
                            break;
                        }
                    }
                    case 1222229: {
                        if (s3.equals("面具")) {
                            n2 = 2;
                            break;
                        }
                        else {
                            break;
                        }
                    }
                    case 822147: {
                        if (s3.equals("披风")) {
                            n2 = 3;
                            break;
                        }
                        else {
                            break;
                        }
                    }
                    case 1051318: {
                        if (s3.equals("腰带")) {
                            n2 = 4;
                            break;
                        }
                        else {
                            break;
                        }
                    }
                    case 805940: {
                        if (s3.equals("挂件")) {
                            n2 = 5;
                            break;
                        }
                        else {
                            break;
                        }
                    }
                }
                switch (n2) {
                    case 0:
                    case 1: {
                        mes2 = (String)ReadACardUtil.jiezhi.get(Battlefield.random.nextInt(ReadACardUtil.jiezhi.size()));
                        do {
                            mes3 = (String)ReadACardUtil.jiezhi.get(Battlefield.random.nextInt(ReadACardUtil.jiezhi.size()));
                        } while (mes2.equals(mes3));
                        if (Battlefield.random.nextInt(100) < 5) {
                            jiezhi = true;
                            break;
                        }
                        else {
                            break;
                        }
                    }
                    case 2: {
                        mes2 = (String)ReadACardUtil.mianju.get(Battlefield.random.nextInt(ReadACardUtil.mianju.size()));
                        do {
                            mes3 = (String)ReadACardUtil.mianju.get(Battlefield.random.nextInt(ReadACardUtil.mianju.size()));
                        } while (mes2.equals(mes3));
                        if (Battlefield.random.nextInt(100) < 5) {
                            mianju = true;
                            break;
                        }
                        else {
                            break;
                        }
                    }
                    case 3: {
                        mes2 = (String)ReadACardUtil.pifeng.get(Battlefield.random.nextInt(ReadACardUtil.pifeng.size()));
                        do {
                            mes3 = (String)ReadACardUtil.pifeng.get(Battlefield.random.nextInt(ReadACardUtil.pifeng.size()));
                        } while (mes2.equals(mes3));
                        if (Battlefield.random.nextInt(100) < 5) {
                            pifeng = true;
                            break;
                        }
                        else {
                            break;
                        }
                    }
                    case 4: {
                        mes2 = (String)ReadACardUtil.yaodai.get(Battlefield.random.nextInt(ReadACardUtil.yaodai.size()));
                        do {
                            mes3 = (String)ReadACardUtil.yaodai.get(Battlefield.random.nextInt(ReadACardUtil.yaodai.size()));
                        } while (mes2.equals(mes3));
                        if (Battlefield.random.nextInt(100) < 5) {
                            yaodai = true;
                            break;
                        }
                        else {
                            break;
                        }
                    }
                    case 5: {
                        mes2 = (String)ReadACardUtil.guajian.get(Battlefield.random.nextInt(ReadACardUtil.guajian.size()));
                        do {
                            mes3 = (String)ReadACardUtil.guajian.get(Battlefield.random.nextInt(ReadACardUtil.guajian.size()));
                        } while (mes2.equals(mes3));
                        if (Battlefield.random.nextInt(100) < 5) {
                            guajian = true;
                            break;
                        }
                        else {
                            break;
                        }
                    }
                }
                String mix = (String)ReadACardUtil.shuxing1.get(mes2);
                String mix2 = (String)ReadACardUtil.shuxing1.get(mes3);
                if (jiezhi) {
                    mix = String.valueOf(Double.parseDouble(mix) + Double.parseDouble(mix) * 0.25);
                    mix2 = String.valueOf(Double.parseDouble(mix2) + Double.parseDouble(mix2) * 0.25);
                }
                mes.append("|点粹属性&").append(mes2).append("=").append(mix).append("&").append(mes3).append("=").append(mix2).append("&总点粹值=2|");
                extra = "点粹属性&" + mes2 + "=" + mix + "&" + mes3 + "=" + mix2 + "&总点粹值=2";
            }
            if (mes == null) {
                mes = new StringBuilder(vv);
            }
            else {
                mes.append("|").append(vv);
            }
        }
        if (jiezhi) {
            extra += "|炼化属性&特技=8080";
        }
        if (pifeng) {
            String skill = String.valueOf(8069 + Battlefield.random.nextInt(3));
            if (Battlefield.random.nextInt(100) >= 20) {
                if (Battlefield.random.nextInt(100) < 40) {
                    skill = "8074";
                }
                else if (Battlefield.random.nextInt(100) < 60) {
                    skill = "8075";
                }
                else if (Battlefield.random.nextInt(100) < 80) {
                    skill = "8076";
                }
            }
            extra = extra + "|炼化属性&特技=" + skill;
        }
        if (yaodai) {
            String skill = String.valueOf(8069 + Battlefield.random.nextInt(3));
            extra = extra + "|炼化属性&特技=" + skill;
        }
        if (mianju) {
            String skill = String.valueOf(8072 + Battlefield.random.nextInt(8));
            extra = extra + "|炼化属性&特技=" + skill;
        }
        if (guajian) {
            String skill = String.valueOf(8069 + Battlefield.random.nextInt(3));
            extra = extra + "|炼化属性&特技=" + skill;
        }
        QualityClBean clBean = new QualityClBean();
        clBean.setRgid(((Goodstable)goods.get(0)).getRgid());
        clBean.setType(8);
        clBean.setNewAttr(extra);
        QualityCPool.getcPool().addExtra(clBean);
        SendMessage.sendMessageToSlef(ctx, Agreement.ExtrattroperAgreement(GsonUtil.getGsonUtil().getgson().toJson(clBean)));
        for (int i = 1; i < goods.size(); ++i) {
            ((Goodstable)goods.get(i)).goodxh(1);
        }
        saveGoods(goods, true);
    }
    
    public void type127(LoginResult loginResult, ChannelHandlerContext ctx, SuitOperBean suitOperBean) {
        BigDecimal money = new BigDecimal(100000);
        if (loginResult.getGold().compareTo(money) < 0) {
            return;
        }
        List<Goodstable> goods = getGoods(suitOperBean.getGoods(), loginResult.getRole_id(), 2);
        if (goods == null || goods.size() < 1) {
            return;
        }
        String[] vvs = ((Goodstable)goods.get(0)).getValue().split("\\|");
        List<String> value = new ArrayList<>();
        int zong = 0;
        String shu1 = null;
        String shu2 = null;
        int shuxing1 = 0;
        int shuxing2 = 0;
        boolean jiezhi = false;
        String teji = null;
        for (String s : vvs) {
            if (!s.startsWith("点粹属性") && !s.startsWith("点翠属性")) {
                value.add(s);
            }
            else if (s.startsWith("点粹属性")) {
                String[] shu3 = s.split("&");
                zong = Integer.parseInt(shu3[3].split("=")[1]);
                shu1 = shu3[1].split("=")[0];
                shu2 = shu3[2].split("=")[0];
            }
            if (s.startsWith("炼化属性&特技")) {
                if (s.split("=")[1].equals("8080")) {
                    jiezhi = true;
                }
                teji = s;
            }
        }
        shuxing1 = zong / 2;
        shuxing2 = zong / 2;
        value.removeIf(bb/* java.lang.String, */ -> bb.equals(""));
        StringBuilder mes = null;
        String extra = null;
        for (String vv : value) {
            if (vv.startsWith("培养")) {
                double mix = Double.parseDouble((String)ReadACardUtil.shuxing1.get(shu1));
                double max = Double.parseDouble((String)ReadACardUtil.shuxing2.get(shu1));
                double mix2 = Double.parseDouble((String)ReadACardUtil.shuxing1.get(shu2));
                double max2 = Double.parseDouble((String)ReadACardUtil.shuxing2.get(shu2));
                if (zong < 5) {
                    if (Battlefield.random.nextInt(100) < 70) {
                        shuxing1 += Battlefield.random.nextInt(5);
                        shuxing2 += Battlefield.random.nextInt(5);
                    }
                    else {
                        shuxing1 += Battlefield.random.nextInt(5) - 2;
                        shuxing2 += Battlefield.random.nextInt(5) - 2;
                    }
                }
                else if (zong < 10) {
                    if (Battlefield.random.nextInt(100) < 50) {
                        shuxing1 += Battlefield.random.nextInt(5);
                        shuxing2 += Battlefield.random.nextInt(5);
                    }
                    else {
                        shuxing1 += Battlefield.random.nextInt(6) - 3;
                        shuxing2 += Battlefield.random.nextInt(6) - 3;
                    }
                }
                else if (zong < 20) {
                    if (Battlefield.random.nextInt(100) < 30) {
                        shuxing1 += Battlefield.random.nextInt(5);
                        shuxing2 += Battlefield.random.nextInt(3);
                    }
                    else {
                        shuxing1 += Battlefield.random.nextInt(6) - 3;
                        shuxing2 += Battlefield.random.nextInt(6) - 3;
                    }
                }
                else if (zong < 30) {
                    if (Battlefield.random.nextInt(100) < 10) {
                        shuxing1 += Battlefield.random.nextInt(3);
                        shuxing2 += Battlefield.random.nextInt(3);
                    }
                    else {
                        shuxing1 += Battlefield.random.nextInt(6) - 3;
                        shuxing2 += Battlefield.random.nextInt(6) - 3;
                    }
                }
                else {
                    shuxing1 += Battlefield.random.nextInt(4) - 3;
                    shuxing2 += Battlefield.random.nextInt(4) - 3;
                }
                while (shuxing1 + shuxing2 > 30) {
                    shuxing1 -= shuxing1;
                    shuxing2 -= shuxing2;
                }
                if (shuxing1 <= 2) {
                    shuxing1 = 2;
                }
                if (shuxing2 <= 2) {
                    shuxing2 = 2;
                }
                zong = shuxing1 + shuxing2;
                double shuzhi3 = (max - mix) * (double)shuxing1 / 30.0;
                double shuzhi4 = (max2 - mix2) * (double)shuxing1 / 30.0;
                if (jiezhi) {
                    shuzhi3 *= 1.25;
                    shuzhi4 *= 1.25;
                }
                String rest = String.format("%.1f", new Object[] { Double.valueOf(shuzhi3) });
                String rest2 = String.format("%.1f", new Object[] { Double.valueOf(shuzhi4) });
                mes.append("|点粹属性&").append(shu1).append("=").append(rest).append("&").append(shu2).append("=").append(rest2).append("&总点粹值=").append(zong).append("|");
                extra = "点粹属性&" + shu1 + "=" + rest + "&" + shu2 + "=" + rest2 + "&总点粹值=" + zong;
            }
            if (mes == null) {
                mes = new StringBuilder(vv);
            }
            else {
                mes.append("|").append(vv);
            }
        }
        if (teji != null) {
            extra = extra + "|" + teji;
        }
        QualityClBean clBean = new QualityClBean();
        clBean.setRgid(((Goodstable)goods.get(0)).getRgid());
        clBean.setType(9);
        clBean.setNewAttr(extra);
        QualityCPool.getcPool().addExtra(clBean);
        SendMessage.sendMessageToSlef(ctx, Agreement.ExtrattroperAgreement(GsonUtil.getGsonUtil().getgson().toJson(clBean)));
        for (int i = 1; i < goods.size(); ++i) {
            ((Goodstable)goods.get(i)).goodxh(1);
        }
        saveGoods(goods, false);
    }
    
    public void type123(LoginResult loginResult, ChannelHandlerContext ctx, SuitOperBean suitOperBean) {
        List<Goodstable> goods = getGoods(suitOperBean.getGoods(), loginResult.getRole_id(), 2);
        if (goods == null) {
            return;
        }
        Goodstable goodstable = (Goodstable)goods.get(0);
        List<MountShouhu> mountShouhuList = GameServer.getAllmountshouhu();
        int k2 = Battlefield.random.nextInt(4) + 1;
        int dengji = 0;
        if (k2 <= 2) {
            dengji = 1;
        }
        else if (k2 <= 3) {
            dengji = 2;
        }
        else {
            dengji = 3;
        }
        Skill skill1 = null;
        Skill skill2 = null;
        if (Battlefield.isV(30.0)) {
            skill1 = GameServer.getSkill(4000 + Battlefield.random.nextInt(45) + "");
        }
        if (Battlefield.isV(20.0)) {
            skill2 = GameServer.getSkill(4000 + Battlefield.random.nextInt(45) + "");
            if (skill1 != null) {
                while (skill1 == skill2) {
                    skill2 = GameServer.getSkill(4000 + Battlefield.random.nextInt(45) + "");
                }
            }
        }
        if (skill1 != null || skill2 != null) {
            dengji = 3;
        }
        StringBuilder mes = new StringBuilder("等级=" + dengji + "|");
        for (int i = 0; i <= k2; ++i) {
            int j = Battlefield.random.nextInt(mountShouhuList.size());
            int p = Battlefield.random.nextInt(20) + 10;
            DecimalFormat df = new DecimalFormat("#.#");
            mes.append(((MountShouhu)mountShouhuList.get(j)).type).append("=").append(df.format(((MountShouhu)mountShouhuList.get(j)).getValue() * (double)p / 100.0)).append("|");
        }
        for (int l = 0; l < 5 - k2 - 1; ++l) {
            mes.append("0|");
        }
        if (skill1 != null) {
            int randomNumber = SuitComposeAction.random.nextInt(100);
            int skill1lvl;
            if (randomNumber < 60) {
                skill1lvl = 1;
            }
            else if (randomNumber < 80) {
                skill1lvl = 2;
            }
            else if (randomNumber < 90) {
                skill1lvl = 3;
            }
            else if (randomNumber < 95) {
                skill1lvl = 4;
            }
            else {
                skill1lvl = 5;
            }
            mes.append(skill1.getSkillid()).append("=").append(skill1lvl).append("|");
        }
        if (skill2 != null) {
            int randomNumber = SuitComposeAction.random.nextInt(100);
            int skill2lvl;
            if (randomNumber < 60) {
                skill2lvl = 1;
            }
            else if (randomNumber < 80) {
                skill2lvl = 2;
            }
            else if (randomNumber < 90) {
                skill2lvl = 3;
            }
            else if (randomNumber < 95) {
                skill2lvl = 4;
            }
            else {
                skill2lvl = 5;
            }
            mes.append(skill2.getSkillid()).append("=").append(skill2lvl).append("|");
        }
        if (skill1 == null && skill2 == null) {
            mes.append("|");
        }
        ((Goodstable)goods.get(0)).setValue(mes.toString());
        String extra = null;
        extra = ((Goodstable)goods.get(0)).getValue();
        saveGoods(goods, false);
        QualityClBean clBean = new QualityClBean();
        clBean.setRgid(((Goodstable)goods.get(0)).getRgid());
        clBean.setType(47);
        clBean.setNewAttr(extra);
        SendMessage.sendMessageToSlef(ctx, Agreement.ExtrattroperAgreement(GsonUtil.getGsonUtil().getgson().toJson(clBean)));
        ((Goodstable)goods.get(1)).goodxh(1);
        saveGoods(goods, false);
        if (skill1 != null) {
            AssetUpdate assetUpdate = new AssetUpdate();
            assetUpdate.setType(AssetUpdate.USEGOOD);
            assetUpdate.updata("T悟技");
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
        }
    }
    
    public void type124(LoginResult loginResult, ChannelHandlerContext ctx, SuitOperBean suitOperBean) {
        List<Goodstable> goods = getGoods(suitOperBean.getGoods(), loginResult.getRole_id(), 2);
        if (goods != null) {
            loginResult.setShouhu(loginResult.getShouhu() - 50);
            String[] values1 = ((Goodstable)goods.get(0)).getValue().split("\\|");
            String[] values2 = ((Goodstable)goods.get(1)).getValue().split("\\|");
            List<MountShouhu> mountShouhuList = GameServer.getAllmountshouhu();
            int lvl = Integer.parseInt(values2[0].split("=")[1]) - Integer.parseInt(values1[0].split("=")[1]) + 2;
            Set<String> processedAttributes = new HashSet<>();
            while (lvl > 0) {
                int randomNumber = SuitComposeAction.random.nextInt(7) + 1;
                if (randomNumber < values2.length) {
                    for (int p = 1; p < values1.length; ++p) {
                        String[] attribute1 = values1[p].split("=");
                        String[] attribute2 = values2[randomNumber].split("=");
                        double maxValue = 0.0;
                        for (MountShouhu mountShouhu : mountShouhuList) {
                            if (mountShouhu.type.equals(attribute1[0])) {
                                maxValue = mountShouhu.getValue();
                                break;
                            }
                        }
                        if (!attribute1[0].equals("0") && !attribute2[0].equals("0") && !attribute1[1].equals(String.valueOf(maxValue)) && (!processedAttributes.contains(attribute1[0]) && attribute1[0].equals(attribute2[0]))) {
                            double lvl2 = Double.parseDouble(attribute1[1]);
                            double lvl3 = Double.parseDouble(attribute2[1]);
                            if (mountShouhuList.stream().noneMatch(mount/* org.come.readUtil.MountShouhu, */ -> mount.type.equals(attribute1[0]))) {
                                int newLvl = Math.min((int)lvl2 + (int)lvl3, 15);
                                values1[p] = attribute1[0] + "=" + newLvl;
                            }
                            else {
                                double value1 = Double.parseDouble(attribute1[1]);
                                double value2 = Double.parseDouble(attribute2[1]);
                                String attributeName = attribute1[0] + "=" + (value1 + value2);
                                for (MountShouhu mountShouhu2 : mountShouhuList) {
                                    if (mountShouhu2.type.equals(attribute1[0])) {
                                        maxValue = mountShouhu2.getValue();
                                        attributeName = attribute1[0] + "=" + Math.min(value1 + value2, maxValue);
                                        break;
                                    }
                                }
                                values1[p] = attributeName;
                            }
                            processedAttributes.add(attribute1[0]);
                        }
                    }
                }
                --lvl;
            }
            StringBuilder updatedValue = new StringBuilder();
            for (String value3 : values1) {
                updatedValue.append(value3).append("|");
            }
            ((Goodstable)goods.get(0)).setValue(updatedValue.substring(0, updatedValue.length() - 1));
            String extra = ((Goodstable)goods.get(0)).getValue();
            saveGoods(goods, false);
            QualityClBean clBean = new QualityClBean();
            clBean.setRgid(((Goodstable)goods.get(0)).getRgid());
            clBean.setType(47);
            clBean.setNewAttr(extra);
            SendMessage.sendMessageToSlef(ctx, Agreement.ExtrattroperAgreement(GsonUtil.getGsonUtil().getgson().toJson(clBean)));
        }
    }
    
    static {
        SuitComposeAction.Extras = new String[] { "炼化属性", "炼器属性", "神兵属性", "套装属性", "宝石属性", "觉醒技", "五行属性", "星阵属性", "赞助属性", "巫铸属性", "点粹属性" };
        SuitComposeAction.CHECKTS1 = Agreement.getAgreement().PromptAgreement("开光成功");
        SuitComposeAction.CHECKTS2 = Agreement.getAgreement().PromptAgreement("开光失败");
        SuitComposeAction.CHECKTS3 = Agreement.getAgreement().PromptAgreement("升级成功");
        SuitComposeAction.CHECKTS4 = Agreement.getAgreement().PromptAgreement("升级失败");
        SuitComposeAction.CHECKTS5 = Agreement.getAgreement().PromptAgreement("材料里面存在绑定和非绑定材料");
        SuitComposeAction.CHECKTS6 = Agreement.getAgreement().PromptAgreement("不是武器也想开光？");
        SuitComposeAction.random = new Random();
        SuitComposeAction.TJ = Lists.newArrayList();
        SuitComposeAction.YFTJ = Lists.newArrayList();
        SuitComposeAction.XLTJ = Lists.newArrayList();
        SuitComposeAction.MZTJ = Lists.newArrayList();
        SuitComposeAction.XTJ = Lists.newArrayList();
        SuitComposeAction.HSTJ = Lists.newArrayList();
        ConcurrentHashMap<Integer, TeJiLH> alllhtj = GameServer.getAlllhtj();
        if (alllhtj != null && !alllhtj.isEmpty()) {
            alllhtj.entrySet().stream().forEach(e/* java.util.Map.Entry, */ -> {
                if (((TeJiLH)e.getValue()).getWQ() != 0) {
                    SuitComposeAction.TJ.add(String.valueOf(e.getKey()));
                }
                if (((TeJiLH)e.getValue()).getYF() != 0) {
                    SuitComposeAction.YFTJ.add(String.valueOf(e.getKey()));
                }
                if (((TeJiLH)e.getValue()).getXL() != 0) {
                    SuitComposeAction.XLTJ.add(String.valueOf(e.getKey()));
                }
                if (((TeJiLH)e.getValue()).getMZ() != 0) {
                    SuitComposeAction.MZTJ.add(String.valueOf(e.getKey()));
                }
                if (((TeJiLH)e.getValue()).getXZ() != 0) {
                    SuitComposeAction.XTJ.add(String.valueOf(e.getKey()));
                }
                if (((TeJiLH)e.getValue()).getHSF() != 0) {
                    SuitComposeAction.HSTJ.add(String.valueOf(e.getKey()));
                }
                return;
            });
        }
    }
}
