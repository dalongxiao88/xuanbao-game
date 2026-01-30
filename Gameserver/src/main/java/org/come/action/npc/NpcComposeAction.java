package org.come.action.npc;

import org.come.model.Alchemy;
import org.come.action.suit.WitchComposeAction;
import org.come.model.Newequip;
import com.github.pagehelper.util.StringUtil;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;
import org.come.model.Decorate;
import org.come.model.GodStone;
import org.come.action.suit.SuitComposeAction;
import come.tool.Role.RoleData;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import come.tool.Good.AddGoodAction;
import come.tool.Stall.AssetUpdate;
import come.tool.Role.RolePool;
import org.come.bean.XXGDBean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.come.action.buy.AddGoodUtil;
import come.tool.Mixdeal.AnalysisString;
import org.come.tool.Goodtype;
import java.util.List;
import java.math.BigDecimal;
import org.come.until.AllServiceUtil;
import org.come.entity.Goodstable;
import java.util.ArrayList;
import org.come.until.GsonUtil;
import org.come.bean.NpcComposeBean;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import java.util.Map;
import java.util.Random;
import org.come.action.IAction;

public class NpcComposeAction implements IAction
{
    private static Random random;
    private static String[] TJ;
    static Map<String, String> map;
    static String hsf;
    static String Hhsf;
    
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (loginResult == null) {
            return;
        }
        NpcComposeBean npcComposeBean = (NpcComposeBean)GsonUtil.getGsonUtil().getgson().fromJson(message, NpcComposeBean.class);
        List<BigDecimal> goodlist = npcComposeBean.getGoodstables();
        List<BigDecimal> goodstables = new ArrayList<>();
        for (int i = 0; i < goodlist.size(); ++i) {
            if (!goodstables.contains(goodlist.get(i))) {
                goodstables.add(goodlist.get(i));
            }
        }
        Goodstable[] goods = new Goodstable[goodstables.size()];
        if (goods.length < 2 && !npcComposeBean.getComposetype().equals("我要分解仙器")) {
            return;
        }
        for (int j = 0; j < goodstables.size(); ++j) {
            Goodstable good = AllServiceUtil.getGoodsTableService().getGoodsByRgID((BigDecimal)goodstables.get(j));
            if (good == null || (int)good.getUsetime() <= 0 || good.getRole_id().compareTo(loginResult.getRole_id()) != 0) {
                return;
            }
            goods[j] = good;
            if (j != 0) {
                good.goodxh(1);
            } else {
                good.setUsetime(Integer.valueOf(1));
            }
        }
        String type = npcComposeBean.getComposetype();
        if (type.equals("我要合成仙器")) {
            this.Compose_1(goods, ctx, loginResult);
        }
        else if (type.equals("我要升级仙器")) {
            this.Compose_2(goods, ctx, loginResult);
        }
        else if (type.equals("洗炼仙器-超级悔梦")) {
            this.Compose_33(goods, ctx, loginResult);
        }
        else if (type.equals("重铸仙器-悔梦石")) {
            this.Compose_3(goods, ctx, loginResult);
        }
        else if (type.equals("我要分解仙器")) {
            this.Compose_2222(goods, ctx, loginResult);
        }
        else if (type.equals("我要打造普通装备") || type.equals("打造11-16级装备")) {
            this.Compose_4(goods, ctx, loginResult);
        }
        else if (type.equals("我要合成炼妖石")) {
            this.Compose_5(goods, ctx, loginResult);
        }
        else if (type.equals("我要培养饰品")) {
            this.Compose_6(goods, ctx, loginResult);
        }
        else if (type.equals("我要重铸饰品")) {
            this.Compose_7(goods, ctx, loginResult);
        }
        else if (type.equals("我要合成符石") || type.equals("我要洗练符石")) {
            this.Compose_8(goods, ctx, loginResult);
        }
        else if (type.equals("我要上神兵石")) {
            this.Compose_9(goods, ctx, loginResult);
        }
        else if (type.equals("我要培养护身符")) {
            this.Compose_10(goods, ctx, loginResult);
        }
        else if (type.equals("我要重铸护身符")) {
            this.Compose_11(goods, ctx, loginResult);
        }
        else if (type.equals("培养彩晶石")) {
            this.Compose_12(goods, ctx, loginResult);
        }
        else if (type.equals("我要解封神饰")) {
            this.Compose_35(goods, ctx, loginResult);
        }
        else if (type.equals("改变仙器模型")) {
            this.Compose_39(goods, ctx, loginResult);
        } else if (type.equals("我要合成玄印")) {
            Compose_xuanyin(goods, ctx, loginResult);
        }
    }

    public void Compose_xuanyin(Goodstable[] goods, ChannelHandlerContext ctx, LoginResult login) {
        for (Goodstable goodstable : goods) {
            if (goodstable.getType() != 10018L && goodstable.getType() != 10012L && goodstable.getType() != 10013L && goodstable.getType() != 10014L) {
                return;
            }
        }
        int Max = 0;
        if (goods.length == 2) {
            if (!Objects.equals(goods[0].getGoodsid(), goods[1].getGoodsid())) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("请选择相同的符石"));
                return;
            }
            int ore_1 = Numerical(Goodtype.StringParsing(goods[0].getValue())[0]);
            int ore_2 = Numerical(Goodtype.StringParsing(goods[1].getValue())[0]);
            if (Math.abs(ore_1 - ore_2) > 1) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("玄印等级差过大，无法合成"));
                return;
            }
            Max = Math.max(ore_1, ore_2);
            if (Max < 10) {
                if (Max != 1 && Math.abs(ore_1 - ore_2) != 0) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("玄印等级差过大，无法合成"));
                    return;
                }
            } else if (ore_1 != ore_2) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("10级玄印以上必须相同等级玄印才可以合成"));
                return;
            }
            if (Max == 15) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("15级玄印无法合成"));
                return;
            }
        }
        if (Max == 0) return;
        Goodstable good = GameServer.getGood_xuanbao(Max + 1, goods[0].getGoodsid().intValue());
        if (good == null) return;
        BigDecimal nsid = good.getGoodsid();
        good.setRgid(goods[0].getRgid());
        good.setRole_id(login.getRole_id());
        good.setQuality(goods[0].getQuality());
        good.setGoodsid(goods[0].getGoodsid());
        good.setStatus(goods[0].getStatus());
        AllServiceUtil.getGoodsTableService().updateGoodsIndex(good, null, nsid, null);
        AllServiceUtil.getGoodsrecordService().insert(good, null, 1, 8);
        goods[0] = null;
        updata(goods);
        AddGoodUtil.addGood(ctx, good);
    }

    public void Compose_1(Goodstable[] goods, ChannelHandlerContext ctx, LoginResult login) {
        if (goods[0].getType() != 7099L) {
            return;
        }
        if (!Goodtype.GodEquipment_xian(goods[1].getType())) {
            return;
        }
        String[] gongneng = null;
        if (goods[0].getValue() != null && !goods[0].getValue().equals("")) {
            gongneng = goods[0].getValue().split("\\|");
        }
        String[] str = goods[1].getValue().split("\\|");
        String god = "";
        int length = str.length;
        int i = 0;
        while (i < length) {
            String s = str[i];
            if (s.startsWith("阶数")) {
                god = s;
                break;
            }
            else {
                ++i;
            }
        }
        if (gongneng == null) {
            if (god.equals("阶数=8")) {
                return;
            }
            goods[0].setValue(god + "|灵气=1点");
            goods[0].setQuality(goods[1].getQuality());
        }
        else {
            if (!gongneng[0].equals(god)) {
                return;
            }
            if (this.ReikiFull(gongneng)) {
                return;
            }
            if (AnalysisString.jiaoyi((long)goods[0].getQuality()) != AnalysisString.jiaoyi((long)goods[1].getQuality())) {
                return;
            }
            goods[0].setValue(god + "|灵气=" + (Integer.parseInt(gongneng[1].split("=")[1].split("点")[0]) + 1) + "点");
        }
        this.updata(goods);
        AddGoodUtil.addGood(ctx, goods[0]);
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
    
    public int Reikisum(String vlaue) {
        Pattern pattern = Pattern.compile("=(.*?)点");
        Matcher m = pattern.matcher(vlaue);
        if (m.find()) {
            int i = 1;
            return Integer.parseInt(m.group(i));
        }
        return 0;
    }
    
    public static int Numerical(String vlaue) {
        if (vlaue.split("\\=").length == 1) {
            return 0;
        }
        return Integer.parseInt(vlaue.split("\\=")[1]);
    }
    
    public void Compose_2(Goodstable[] goods, ChannelHandlerContext ctx, LoginResult login) {
        if (goods[0].getType() != 7099L) {
            return;
        }
        if (!Goodtype.Ore(goods[1].getType())) {
            return;
        }
        String[] gongneng = null;
        if (goods[0].getValue() != null && !goods[0].getValue().equals("")) {
            gongneng = goods[0].getValue().split("\\|");
        }
        if (gongneng == null) {
            return;
        }
        if (!this.ReikiFull(gongneng)) {
            return;
        }
        int kslvl = Integer.parseInt(goods[1].getValue().split("=")[1]);
        int lvl = Integer.parseInt(gongneng[0].split("=")[1]);
        if (lvl + 5 != kslvl) {
            return;
        }
        if (lvl >= 8) {
            return;
        }
        ++lvl;
        BigDecimal id = new BigDecimal(GameServer.random.nextInt(22) + 7000);
        Goodstable good = GameServer.getGood(id);
        BigDecimal nsid = good.getGoodsid();
        List<String> xianqis = (List<String>)GameServer.getXianqiTypeValue().get(id + "|" + lvl);
        int[] a = randomArray(0, xianqis.size() - 1, 1);
        good.setValue((String)xianqis.get(a[0]));
        good.setRgid(goods[0].getRgid());
        good.setRole_id(login.getRole_id());
        good.setQuality(goods[0].getQuality());
        good.setGoodsid(goods[0].getGoodsid());
        good.setStatus(goods[0].getStatus());
        AllServiceUtil.getGoodsTableService().updateGoodsIndex(good, null, nsid, null);
        AllServiceUtil.getGoodsrecordService().insert(good, null, Integer.valueOf(1), Integer.valueOf(8));
        goods[0] = null;
        this.updata(goods);
        AddGoodUtil.addGood(ctx, good);
    }
    
    public void Compose_2222(Goodstable[] goods, ChannelHandlerContext ctx, LoginResult login) {
        if (!Goodtype.GodEquipment_xian(goods[0].getType())) {
            return;
        }
        Goodstable goodstable = GameServer.getGood(new BigDecimal("81239"));
        if (goodstable == null) {
            return;
        }
        String[] str = goods[0].getValue().split("\\|");
        String god = "";
        int length = str.length;
        int i = 0;
        while (i < length) {
            String s = str[i];
            if (s.startsWith("阶数")) {
                god = s;
                break;
            }
            else {
                ++i;
            }
        }
        Integer js = Integer.valueOf(Integer.parseInt(god.split("=")[1]));
        int sum = 0;
        switch ((int)js) {
            case 1: {
                sum = 1;
                break;
            }
            case 2: {
                sum = 8;
                break;
            }
            case 3: {
                sum = 64;
                break;
            }
            case 4: {
                sum = 384;
                break;
            }
            case 5: {
                sum = 1920;
                break;
            }
            case 6: {
                sum = 5760;
                break;
            }
        }
        XXGDBean xxgdBean = new XXGDBean();
        xxgdBean.setId(goodstable.getGoodsid().toString());
        xxgdBean.setSum(sum);
        RoleData roleData = RolePool.getRoleData(login.getRole_id());
        AssetUpdate assetUpdate = new AssetUpdate();
        AddGoodAction.addGood(assetUpdate, goodstable, login, roleData, xxgdBean, 66);
        goods[0].setUsetime(Integer.valueOf(0));
        this.updata(goods);
        assetUpdate.setMsg("少侠,你通过分解仙器获得" + sum + "个" + goodstable.getGoodsname());
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public void Compose_3(Goodstable[] goods, ChannelHandlerContext ctx, LoginResult login) {
        if (!Goodtype.GodEquipment_xian(goods[0].getType())) {
            return;
        }
        if (goods[1].getType() != 212L) {
            return;
        }
        BigDecimal id = new BigDecimal(GameServer.random.nextInt(24) + 7000);
        String js = goods[0].getValue().split("\\|")[0].split("=")[1];
        Goodstable good = GameServer.getGood(id);
        BigDecimal nsid = good.getGoodsid();
        List<String> xianqis = (List<String>)GameServer.getXianqiTypeValue().get(id + "|" + js);
        int[] a = randomArray(0, xianqis.size() - 1, 1);
        good.setValue((String)xianqis.get(a[0]));
        good.setRgid(goods[0].getRgid());
        good.setRole_id(login.getRole_id());
        good.setQuality(goods[0].getQuality());
        good.setGoodsid(goods[0].getGoodsid());
        good.setStatus(goods[0].getStatus());
        AllServiceUtil.getGoodsTableService().updateGoodsIndex(good, null, nsid, null);
        AllServiceUtil.getGoodsrecordService().insert(good, null, Integer.valueOf(1), Integer.valueOf(8));
        goods[0] = null;
        this.updata(goods);
        AddGoodUtil.addGood(ctx, good);
    }
    
    public void Compose_39(Goodstable[] goods, ChannelHandlerContext ctx, LoginResult login) {
        if (!Goodtype.GodEquipment_xian(goods[0].getType())) {
            return;
        }
        if (goods[1].getType() != 923L) {
            return;
        }
        Goodstable good = new Goodstable();
        Goodstable goods2 = goods[0];
        int ids = 0;
        int i = 0;
        while (i < 100) {
            BigDecimal id = new BigDecimal(GameServer.random.nextInt(22) + 7000);
            good = GameServer.getGood(id);
            if (good.getType() == goods2.getType()) {
                ids = Integer.parseInt(id + "");
                break;
            }
            else {
                ++i;
            }
        }
        String js = goods2.getValue().split("\\|")[0].split("=")[1];
        List<String> xianqis = (List<String>)GameServer.getXianqiTypeValue().get(ids + "|" + js);
        BigDecimal nsid = good.getGoodsid();
        String newvalue = (String)xianqis.get(GameServer.random.nextInt(xianqis.size()));
        String[] newvalues = newvalue.split("\\|");
        String value = goods2.getValue();
        String[] values = value.split("\\|");
        String zvalue = "";
        int newi = 0;
        int oldi = 0;
        for (int j = 0; j < newvalues.length; ++j) {
            boolean status = newvalues[j].contains("等级要求");
            if (status) {
                newi = j;
            }
        }
        for (int k = 0; k < values.length; ++k) {
            boolean status = values[k].contains("等级要求");
            if (status) {
                oldi = k;
            }
        }
        for (int x = 0; x < newi + 1; ++x) {
            zvalue = zvalue + newvalues[x] + "|";
        }
        for (int y = oldi + 1; y < values.length; ++y) {
            zvalue = zvalue + values[y] + "|";
        }
        goods2.setValue(zvalue);
        goods2.setGoodsname(good.getGoodsname());
        goods2.setInstruction(good.getInstruction());
        goods2.setSkin(good.getSkin());
        AllServiceUtil.getGoodsTableService().updateGoodsIndex(goods2, null, nsid, null);
        goods[0] = null;
        this.updata(goods);
        AddGoodUtil.addGood(ctx, goods2);
    }
    
    public void Compose_4(Goodstable[] goods, ChannelHandlerContext ctx, LoginResult login) {
        if (!Goodtype.OrdinaryEquipment(goods[0].getType())) {
            return;
        }
        if (!Goodtype.Ore(goods[1].getType())) {
            return;
        }
        int goodid = goods[0].getGoodsid().intValue();
        int zblvl = Integer.parseInt(goods[0].getValue().split("\\|")[0].split("=")[1]);
        int kslvl = Integer.parseInt(goods[1].getValue().split("=")[1]);
        Goodstable good = null;
        if (zblvl < 10) {
            BigDecimal id = new BigDecimal(goodid - zblvl + kslvl + 1);
            good = GameServer.getGood(id);
            BigDecimal nsid = good.getGoodsid();
            good.setRgid(goods[0].getRgid());
            good.setRole_id(login.getRole_id());
            good.setQuality(goods[0].getQuality());
            good.setGoodsid(goods[0].getGoodsid());
            good.setStatus(goods[0].getStatus());
            AllServiceUtil.getGoodsTableService().updateGoodsIndex(good, null, nsid, null);
            AllServiceUtil.getGoodsrecordService().insert(good, null, Integer.valueOf(1), Integer.valueOf(8));
            goods[0] = null;
        }
        else {
            boolean up = false;
            if (zblvl >= 10 && zblvl <= 13) {
                if (kslvl != 8 && kslvl != 9) {
                    return;
                }
                if (kslvl == 9) {
                    up = true;
                }
            }
            else if (zblvl == 14) {
                if (kslvl != 9 && kslvl != 10) {
                    return;
                }
                if (kslvl == 10) {
                    up = true;
                }
            }
            else if (zblvl == 15) {
                if (kslvl != 10 && kslvl != 11) {
                    return;
                }
                if (kslvl == 11) {
                    up = true;
                }
            }
            else if (zblvl == 16) {
                if (kslvl != 11) {
                    return;
                }
            }
            else {
                return;
            }
            if (up) {
                ++goodid;
            }
            BigDecimal id2 = new BigDecimal(goodid);
            good = GameServer.getGood(id2);
            assert good != null;
            BigDecimal nsid2 = good.getGoodsid();
            good.setRgid(goods[0].getRgid());
            good.setRole_id(login.getRole_id());
            good.setValue(good.getValue() + this.getNewequip(good));
            good.setQuality(goods[0].getQuality());
            good.setGoodsid(goods[0].getGoodsid());
            good.setStatus(goods[0].getStatus());
            AllServiceUtil.getGoodsTableService().updateGoodsIndex(good, null, nsid2, null);
            AllServiceUtil.getGoodsrecordService().insert(good, null, Integer.valueOf(1), Integer.valueOf(8));
            goods[0] = null;
        }
        this.updata(goods);
        AddGoodUtil.addGood(ctx, good);
    }
    
    public void Compose_5(Goodstable[] goods, ChannelHandlerContext ctx, LoginResult login) {
        if (!Goodtype.ExerciseMonsterOre(goods[0].getType())) {
            return;
        }
        if (!Goodtype.ExerciseMonsterOre(goods[1].getType())) {
            return;
        }
        if (goods[0].getType() != goods[1].getType()) {
            return;
        }
        String getType = SynthesisOre(goods[0], goods[1]);
        if (getType != null) {
            goods[0].setValue(getType);
            this.updata(goods);
            AddGoodUtil.addGood(ctx, goods[0]);
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("合成成功"));
        }
        else {
            goods[0].setUsetime(Integer.valueOf(0));
            this.updata(goods);
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("合成失败"));
        }
    }
    
    public void Compose_6(Goodstable[] goods, ChannelHandlerContext ctx, LoginResult login) {
        if (!Goodtype.Accessories(goods[0].getType())) {
            return;
        }
        if ((goods[1].getType() != 1008L || goods[1].getType() != 702L || goods[1].getType() != 703L || goods[1].getType() != 704L || goods[1].getType() != 705L || goods[1].getType() != 706L || goods[1].getType() != 707L || goods[1].getType() != 708L || goods[1].getType() != 709L || goods[1].getType() != 710L || goods[1].getType() != 711L || goods[1].getType() != 722L || goods[1].getType() != 723L) && !Goodtype.Accessories(goods[1].getType())) {
            return;
        }
        String[] gongneng = goods[0].getValue().split("\\|");
        int max = 0;
        for (int i = 0; i < gongneng.length; ++i) {
            if (gongneng[i].length() >= 2 && gongneng[i].substring(0, 2).equals("培养")) {
                String[] num = gongneng[i].split("=")[1].split("/");
                max = Integer.parseInt(num[1]);
            }
        }
        if (max == 0) {
            return;
        }
        if (Numerical(gongneng[0]) > 6) {
            return;
        }
        BigDecimal ysid = goods[0].getGoodsid();
        SuitComposeAction.AccC(goods[0], 1);
        BigDecimal nsid = goods[0].getGoodsid();
        goods[0].setGoodsid(ysid);
        Goodstable good = goods[0];
        AllServiceUtil.getGoodsTableService().updateGoodsIndex(good, null, nsid, null);
        AllServiceUtil.getGoodsrecordService().insert(good, null, Integer.valueOf(1), Integer.valueOf(8));
        goods[0] = null;
        this.updata(goods);
        AddGoodUtil.addGood(ctx, good);
    }
    
    public void Compose_7(Goodstable[] goods, ChannelHandlerContext ctx, LoginResult login) {
        if (goods[0].getType() == 927L || goods[0].getType() == 928L || goods[0].getType() == 929L || goods[0].getType() == 930L || goods[0].getType() == 931L) {
            this.updata(goods);
            AddGoodUtil.addGood(ctx, goods[0]);
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("神饰无法使用普通矿石重铸"));
            return;
        }
        if (!Goodtype.Accessories(goods[0].getType())) {
            return;
        }
        if (!Goodtype.Ore(goods[1].getType())) {
            return;
        }
        String[] gongneng = goods[0].getValue().split("\\|");
        int kslvl = Integer.parseInt(goods[1].getValue().split("=")[1]);
        if (Numerical(gongneng[0]) != kslvl - 3) {
            return;
        }
        goods[0].setValue(getGoodsAttribute(goods[0]));
        this.updata(goods);
        AddGoodUtil.addGood(ctx, goods[0]);
    }
    
    public void Compose_8(Goodstable[] goods, ChannelHandlerContext ctx, LoginResult login) {
        for (int i = 0; i < goods.length; ++i) {
            if (goods[i].getType() != 188L && (i == 0 || goods[i].getType() != 1881L)) {
                return;
            }
        }
        BigDecimal id = null;
        if (goods.length == 2) {
            int ore_1 = Numerical(Goodtype.StringParsing(goods[0].getValue())[0]);
            if (ore_1 == 1 || ore_1 == 2 || ore_1 == 3) {
                return;
            }
            if (goods[1].getType() == 188L) {
                int ore_2 = Numerical(Goodtype.StringParsing(goods[1].getValue())[0]);
                if (ore_1 - 3 != ore_2) {
                    return;
                }
            }
            id = new BigDecimal(GameServer.random.nextInt(5) * 5 + ore_1);
        }
        else if (goods.length == 5) {
            int lvl = Numerical(Goodtype.StringParsing(goods[0].getValue())[0]);
            if (lvl >= 5) {
                return;
            }
            for (int j = 1; j < goods.length; ++j) {
                if (lvl != Numerical(Goodtype.StringParsing(goods[j].getValue())[0])) {
                    return;
                }
            }
            ++lvl;
            id = new BigDecimal(GameServer.random.nextInt(5) * 5 + lvl);
        }
        if (id == null) {
            return;
        }
        Goodstable good = GameServer.getGood(id);
        BigDecimal nsid = good.getGoodsid();
        good.setRgid(goods[0].getRgid());
        good.setRole_id(login.getRole_id());
        good.setQuality(goods[0].getQuality());
        good.setGoodsid(goods[0].getGoodsid());
        good.setStatus(goods[0].getStatus());
        AllServiceUtil.getGoodsTableService().updateGoodsIndex(good, null, nsid, null);
        AllServiceUtil.getGoodsrecordService().insert(good, null, Integer.valueOf(1), Integer.valueOf(8));
        goods[0] = null;
        this.updata(goods);
        AddGoodUtil.addGood(ctx, good);
    }
    
    public void Compose_9(Goodstable[] goods, ChannelHandlerContext ctx, LoginResult login) {
        if (!Goodtype.GodEquipment_God(goods[0].getType())) {
            return;
        }
        if (goods[1].getType() != 191L) {
            return;
        }
        String[] v = goods[0].getValue().split("\\|");
        GodStone godStone = GameServer.getGodStone(goods[0].getGoodsname(), v);
        if (godStone != null) {
            String value = SuitComposeAction.newExtra(v, 2, "神兵属性&" + godStone.getValue());
            goods[0].setValue(value);
        }
        this.updata(goods);
        AddGoodUtil.addGood(ctx, goods[0]);
    }
    
    public void Compose_10(Goodstable[] goods, ChannelHandlerContext ctx, LoginResult login) {
        if (!Goodtype.Amulet2(goods[0].getType())) {
            return;
        }
        if (!Goodtype.Amulet2(goods[1].getType())) {
            return;
        }
        goods[0].setValue(resetHSF(goods[0], NpcComposeAction.random.nextInt(170) + 31, 0, 0));
        this.updata(goods);
        AddGoodUtil.addGood(ctx, goods[0]);
    }
    
    public void Compose_11(Goodstable[] goods, ChannelHandlerContext ctx, LoginResult login) {
        if (!Goodtype.Amulet2(goods[0].getType())) {
            return;
        }
        if (!Goodtype.Ore(goods[1].getType())) {
            return;
        }
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
        if (pz < 300) {
            return;
        }
        int kslvl = Integer.parseInt(goods[1].getValue().split("=")[1]);
        if (kslvl != 9 && kslvl != 10) {
            return;
        }
        int lvl = Integer.parseInt(vs[0].split("=")[1]);
        BigDecimal id = null;
        Goodstable good = null;
        if (kslvl == 10) {
            if (++lvl > 9) {
                return;
            }
            id = goods[0].getGoodsid().add(new BigDecimal(1));
            good = GameServer.getGood(id);
            assert good != null;
            BigDecimal nsid = good.getGoodsid();
            good.setRgid(goods[0].getRgid());
            good.setRole_id(login.getRole_id());
            good.setQuality(goods[0].getQuality());
            String[] hsvs = vs;
            String[] hsvss = good.getValue().split("\\|");
            hsvs[0] = hsvss[0];
            hsvs[1] = hsvss[1];
            hsvs[2] = hsvss[2];
            StringBuffer buffer = new StringBuffer();
            for (int j = 0; j < hsvs.length; ++j) {
                if (j != 0) {
                    buffer.append("|");
                }
                buffer.append(hsvs[j]);
            }
            good.setValue(buffer.toString());
            good.setValue(resetHSF(good, 0, 0, 0));
            good.setGoodsid(goods[0].getGoodsid());
            good.setStatus(goods[0].getStatus());
            AllServiceUtil.getGoodsTableService().updateGoodsIndex(good, null, nsid, null);
            AllServiceUtil.getGoodsrecordService().insert(good, null, Integer.valueOf(1), Integer.valueOf(8));
            goods[0] = null;
        }
        else {
            int sj = NpcComposeAction.random.nextInt(900);
            if (sj > 800) {
                sj = NpcComposeAction.random.nextInt(850) + 100;
                if (sj > 850) {
                    sj = NpcComposeAction.random.nextInt(800) + 200;
                }
            }
            goods[0].setValue(resetHSF(goods[0], 0, sj, 0));
        }
        this.updata(goods);
        AddGoodUtil.addGood(ctx, (good != null) ? good : goods[0]);
    }
    
    public void Compose_12(Goodstable[] goods, ChannelHandlerContext ctx, LoginResult login) {
        if (goods[0].getType() != 729L) {
            return;
        }
        if (goods[1].getType() != 729L) {
            return;
        }
        this.resetCJS(goods[0], 2 + NpcComposeAction.random.nextInt(5));
        this.updata(goods);
        AddGoodUtil.addGood(ctx, goods[0]);
    }
    
    public void Compose_33(Goodstable[] goods, ChannelHandlerContext ctx, LoginResult login) {
        if (!Goodtype.GodEquipment_xian(goods[0].getType())) {
            return;
        }
        if (goods[1].getType() != 915L) {
            return;
        }
        int id = goods[0].getGoodsid().intValue();
        String js = goods[0].getValue().split("\\|")[0].split("=")[1];
        Goodstable good = GameServer.getGood(BigDecimal.valueOf((long)id));
        BigDecimal nsid = good.getGoodsid();
        List<String> xianqis = (List<String>)GameServer.getXianqiTypeValue().get(id + "|" + js);
        int[] a = randomArray(0, xianqis.size() - 1, 1);
        good.setValue((String)xianqis.get(a[0]));
        good.setRgid(goods[0].getRgid());
        good.setRole_id(login.getRole_id());
        good.setQuality(goods[0].getQuality());
        good.setGoodsid(goods[0].getGoodsid());
        good.setStatus(goods[0].getStatus());
        AllServiceUtil.getGoodsTableService().updateGoodsIndex(good, null, nsid, null);
        AllServiceUtil.getGoodsrecordService().insert(good, null, Integer.valueOf(1), Integer.valueOf(8));
        goods[0] = null;
        this.updata(goods);
        AddGoodUtil.addGood(ctx, good);
    }
    
    public void Compose_35(Goodstable[] goods, ChannelHandlerContext ctx, LoginResult login) {
        if (goods[0].getType() != 927L && goods[0].getType() != 928L && goods[0].getType() != 929L && goods[0].getType() != 930L && goods[0].getType() != 931L) {
            this.updata(goods);
            AddGoodUtil.addGood(ctx, goods[0]);
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("请仔细检查你的饰品，貌似不是神饰品"));
            return;
        }
        if (!Goodtype.Accessories(goods[0].getType())) {
            return;
        }
        if (goods[1].getType() != 932L) {
            return;
        }
        String[] gongneng = goods[0].getValue().split("\\|");
        goods[0].setValue(getGoodsAttributex7(goods[0]));
        this.updata(goods);
        AddGoodUtil.addGood(ctx, goods[0]);
    }
    
    public static String getGoodsAttributex7(Goodstable goods) {
        List<Decorate> decorates = (List<Decorate>)GameServer.getAllDecorate().get(goods.getGoodsid().toString());
        if (!((Decorate)decorates.get(0)).getDecotatebvalue().startsWith(goods.getValue().substring(0, 4))) {
            decorates = (List<Decorate>)GameServer.getAllDecorate().get(goods.getGoodsid().add(new BigDecimal(1)).toString());
        }
        int[] a = randomArray(0, decorates.size() - 1, 1);
        Decorate decorate = (Decorate)decorates.get(a[0]);
        String bvalue = decorate.getDecotatebvalue();
        String type = "";
        if (decorate.getDecotatetype().indexOf("|") != -1) {
            type = getAttribute(decorate.getDecotatetype(), 2);
        }
        else {
            type = getAttribute(decorate.getDecotatetype(), 1);
        }
        String value = getAttribute(decorate.getDecotatepvalue(), 1);
        String fvalue = getAttribute(decorate.getDecotatefvalue(), 1);
        String kvalue = getAttribute(decorate.getDecotatekvalue(), 1);
        String pv1 = getAttribute(decorate.getDecotatepv1(), 1);
        String pv2 = getAttribute(decorate.getDecotatepv2(), 1);
        String mv = decorate.getDecotatemv();
        String v = decorate.getDecotatev();
        String ssx = getAttribute(decorate.getSlsx(), 1);
        String ssx2 = getAttribute(decorate.getSlsx1(), 1);
        String ssx3 = getAttribute(decorate.getSlsx2(), 1);
        String ssx4 = getAttribute(decorate.getSlsx3(), 1);
        String ssx5 = getAttribute(decorate.getSlsx4(), 1);
        String ssx6 = getAttribute(decorate.getSlsx5(), 1);
        String ssx7 = getAttribute(decorate.getSlsx6(), 1);
        String ssx8 = getAttribute(decorate.getSlsx7(), 1);
        String[] vvs = goods.getValue().split("\\|");
        int i = 0;
        while (i < vvs.length) {
            String[] vvv = vvs[i].split("=");
            if (vvv[0].equals("培养")) {
                v = vvv[1].split("/")[0];
                break;
            }
            else {
                ++i;
            }
        }
        String lianhua = "炼化属性&" + pv1.replace("|", "") + "&" + pv2.replace("|", "");
        String attribute = bvalue + type + value + fvalue + kvalue;
        if (ssx.equals("0") || ssx.equals("神类属性")) {
            attribute = newlianhua(attribute.split("\\|"), lianhua, null, null);
        }
        if (mv.equals("0") || mv.equals("")) {
            attribute = newlianhua(attribute.split("\\|"), lianhua, null, null) + ssx + ssx2 + ssx3 + ssx4 + ssx5 + ssx6 + ssx7 + ssx8;
        }
        else {
            attribute = newlianhua(attribute.split("\\|"), lianhua, null, null) + "|培养=" + v + "/" + mv + "#G" + ssx + ssx2 + ssx3 + ssx4 + ssx5 + ssx6 + ssx7 + ssx8;
        }
        return attribute;
    }
    
    public static String getGoodsAttribute(Goodstable goods) {
        String pv1;
        String pv2;
        String attribute;
        do {
            List<Decorate> decorates = (List<Decorate>)GameServer.getAllDecorate().get(goods.getGoodsid().toString());
            if (!((Decorate)decorates.get(0)).getDecotatebvalue().startsWith(goods.getValue().substring(0, 4))) {
                decorates = (List<Decorate>)GameServer.getAllDecorate().get(goods.getGoodsid().add(new BigDecimal(1)).toString());
            }
            int[] a = randomArray(0, decorates.size() - 1, 1);
            Decorate decorate = (Decorate)decorates.get(((int[])Objects.requireNonNull(a))[0]);
            String bvalue = decorate.getDecotatebvalue();
            String type = "";
            if (decorate.getDecotatetype().contains("|")) {
                type = getAttribute(decorate.getDecotatetype(), 2);
            }
            else {
                type = getAttribute(decorate.getDecotatetype(), 1);
            }
            String value = getAttribute(decorate.getDecotatepvalue(), 1);
            String fvalue = getAttribute(decorate.getDecotatefvalue(), 1);
            String kvalue = getAttribute(decorate.getDecotatekvalue(), 1);
            pv1 = getAttribute(decorate.getDecotatepv1(), 1);
            pv2 = getAttribute(decorate.getDecotatepv2(), 1);
            String mv = decorate.getDecotatemv();
            String v = decorate.getDecotatev();
            String[] vvs = goods.getValue().split("\\|");
            int i = 0;
            while (i < vvs.length) {
                String[] vvv = vvs[i].split("=");
                if (vvv[0].equals("培养")) {
                    v = vvv[1].split("/")[0];
                    break;
                }
                else {
                    ++i;
                }
            }
            String lianhua = "炼化属性&" + pv1.replace("|", "") + "&" + pv2.replace("|", "");
            attribute = bvalue + type + value + fvalue + kvalue;
            if (mv.equals("0") || mv.equals("")) {
                attribute = newlianhua(attribute.split("\\|"), lianhua, null, null);
            }
            else {
                attribute = newlianhua(attribute.split("\\|"), lianhua, null, null) + "|培养=" + v + "/" + mv;
            }
        } while (pv1.equals(pv2));
        return attribute;
    }
    
    public static ArrayList<String> getGoodsNeed(String value) {
        ArrayList<String> result = new ArrayList<>();
        if (value != null && value.length() > 0) {
            String[] vvs = value.split("\\|");
            if (vvs != null && vvs.length > 0) {
                for (int i = 0; i < vvs.length; ++i) {
                    String[] vvv = vvs[i].split("=");
                    if (vvv[0].equals("根骨要求") || vvv[0].equals("灵性要求") || vvv[0].equals("力量要求") || vvv[0].equals("敏捷要求")) {
                        result.add(vvv[0]);
                    }
                }
            }
        }
        return (ArrayList)result;
    }
    
    public static Decorate getDecorateFromType(ArrayList<String> types, List<Decorate> decorates) {
        if (types != null && types.size() > 0 && decorates != null && decorates.size() > 0) {
            for (Decorate decorate : decorates) {
                if (decorate.getDecotatetype().contains("|") && types.size() > 1) {
                    return decorate;
                }
                if (!decorate.getDecotatetype().contains("|") && types.size() == 1 && decorate.getDecotatetype().startsWith((String)types.get(0))) {
                    return decorate;
                }
            }
        }
        return null;
    }
    
    public static HashMap<String, String> getOldAttribute(String value) {
        HashMap<String, String> result = new HashMap<>();
        if (value != null && value.length() > 0) {
            String[] vvs = value.split("\\|");
            if (vvs != null && vvs.length > 0) {
                for (int i = 0; i < vvs.length; ++i) {
                    if (vvs[i].startsWith("炼化属性")) {
                        String[] lianhuas = vvs[i].split("&");
                        if (lianhuas != null && lianhuas.length == 3) {
                            result.put("pv1", lianhuas[1].split("&")[0].split("=")[0]);
                            result.put("pv2", lianhuas[2].split("&")[0].split("=")[0]);
                        }
                    }
                    else {
                        String[] vvv = vvs[i].split("=");
                        if (vvv[0].equals("附加气血") || vvv[0].equals("附加攻击") || vvv[0].equals("附加法力") || vvv[0].equals("附加魔法") || vvv[0].equals("附加速度")) {
                            result.put("fvalue", vvv[0].split("=")[0]);
                            String[] vv1 = vvs[i - 1].split("=");
                            if (vv1[0].equals("根骨要求") || vv1[0].equals("灵性要求") || vv1[0].equals("力量要求") || vv1[0].equals("敏捷要求")) {
                                result.put("pvalue", null);
                            }
                            else {
                                result.put("pvalue", vvs[i - 1].split("=")[0]);
                            }
                            if (vvs[i + 1] != null && vvs[i + 1].startsWith("炼化属性")) {
                                result.put("kvalue", null);
                            }
                            else if (vvs[i + 1] != null && vvs[i + 1].startsWith("抗")) {
                                String[] vv2 = vvs[i + 1].split("=");
                                result.put("kvalue", vvs[i + 1].split("=")[0]);
                            }
                        }
                    }
                }
            }
        }
        return result;
    }
    
    public static String getGrouAttrByOldAttr(HashMap<String, String> oldAttr, Decorate decorate, ArrayList<String> oldtypes, int type) {
        String typestr = "";
        switch (type) {
            case 1: {
                if (decorate.getDecotatetype().contains("|")) {
                    String[] types = decorate.getDecotatetype().split("\\|");
                    if (types != null && types.length > 0) {
                        for (String tp : types) {
                            for (String otp : oldtypes) {
                                if (tp.startsWith(otp)) {
                                    typestr = typestr + tp + "|";
                                }
                            }
                        }
                    }
                    break;
                }
                else {
                    return decorate.getDecotatetype() + "|";
                }
            }
            case 2: {
                String pv = (String)oldAttr.get("pvalue");
                if (pv == null) {
                    return typestr;
                }
                if (decorate.getDecotatepvalue().contains("|")) {
                    String[] pvs = decorate.getDecotatepvalue().split("\\|");
                    if (pvs != null && pvs.length > 0) {
                        for (String item : pvs) {
                            if (item.startsWith(pv)) {
                                typestr = typestr + item + "|";
                            }
                        }
                    }
                    break;
                }
                else {
                    return decorate.getDecotatepvalue() + "|";
                }
            }
            case 3: {
                String fv = (String)oldAttr.get("fvalue");
                if (fv == null) {
                    return typestr;
                }
                if (decorate.getDecotatefvalue().contains("|")) {
                    String[] fvs = decorate.getDecotatefvalue().split("\\|");
                    if (fvs != null && fvs.length > 0) {
                        for (String item2 : fvs) {
                            if (item2.startsWith(fv)) {
                                typestr = typestr + item2 + "|";
                            }
                        }
                    }
                    break;
                }
                else {
                    return decorate.getDecotatefvalue() + "|";
                }
            }
            case 4: {
                String kv = (String)oldAttr.get("kvalue");
                if (kv == null) {
                    return typestr;
                }
                if (decorate.getDecotatekvalue().contains("|")) {
                    String[] kvs = decorate.getDecotatekvalue().split("\\|");
                    if (kvs != null && kvs.length > 0) {
                        for (String item3 : kvs) {
                            if (item3.startsWith(kv)) {
                                typestr = typestr + item3 + "|";
                            }
                        }
                    }
                    break;
                }
                else {
                    return decorate.getDecotatekvalue() + "|";
                }
            }
            case 5: {
                String pv2 = (String)oldAttr.get("pv1");
                if (pv2 == null) {
                    return typestr;
                }
                if (decorate.getDecotatepv1().contains("|")) {
                    String[] pv1s = decorate.getDecotatepv1().split("\\|");
                    if (pv1s != null && pv1s.length > 0) {
                        for (String item4 : pv1s) {
                            if (item4.startsWith(pv2)) {
                                typestr = typestr + item4 + "|";
                            }
                        }
                    }
                    break;
                }
                else {
                    return decorate.getDecotatepv1() + "|";
                }
            }
            case 6: {
                String pv3 = (String)oldAttr.get("pv2");
                if (pv3 == null) {
                    return typestr;
                }
                if (decorate.getDecotatepv2().contains("|")) {
                    String[] pv2s = decorate.getDecotatepv2().split("\\|");
                    if (pv2s != null && pv2s.length > 0) {
                        for (String item5 : pv2s) {
                            if (item5.startsWith(pv3)) {
                                typestr = typestr + item5 + "|";
                            }
                        }
                    }
                    break;
                }
                else {
                    return decorate.getDecotatepv2() + "|";
                }
            }
        }
        return typestr;
    }
    
    public static String getGrouGoodsAttribute(Goodstable good, Goodstable goods) {
        String pv1;
        String pv2;
        String attribute;
        do {
            List<Decorate> decorates = (List<Decorate>)GameServer.getAllDecorate().get(goods.getGoodsid().toString());
            if (!((Decorate)decorates.get(0)).getDecotatebvalue().startsWith(goods.getValue().substring(0, 4))) {
                decorates = (List<Decorate>)GameServer.getAllDecorate().get(goods.getGoodsid().add(new BigDecimal(1)).toString());
            }
            ArrayList<String> types = getGoodsNeed(good.getValue());
            Decorate decorate = getDecorateFromType(types, decorates);
            HashMap<String, String> attMap = getOldAttribute(good.getValue());
            String bvalue = decorate.getDecotatebvalue();
            String type = "";
            type = "|" + getGrouAttrByOldAttr(attMap, decorate, types, 1);
            String value = getGrouAttrByOldAttr(attMap, decorate, types, 2);
            String fvalue = getGrouAttrByOldAttr(attMap, decorate, types, 3);
            String kvalue = getGrouAttrByOldAttr(attMap, decorate, types, 4);
            pv1 = getGrouAttrByOldAttr(attMap, decorate, types, 5);
            if (StringUtil.isEmpty(pv1)) {
                pv1 = getAttribute(decorate.getDecotatepv1(), 1);
            }
            pv2 = getGrouAttrByOldAttr(attMap, decorate, types, 6);
            if (StringUtil.isEmpty(pv2)) {
                pv2 = getAttribute(decorate.getDecotatepv2(), 1);
            }
            String mv = decorate.getDecotatemv();
            String v = decorate.getDecotatev();
            String[] vvs = goods.getValue().split("\\|");
            int i = 0;
            while (i < vvs.length) {
                String[] vvv = vvs[i].split("=");
                if (vvv[0].equals("培养")) {
                    v = vvv[1].split("/")[0];
                    break;
                }
                else {
                    ++i;
                }
            }
            String lianhua = "炼化属性&" + pv1.replace("|", "") + "&" + pv2.replace("|", "");
            attribute = bvalue + type + value + fvalue + kvalue;
            if (mv.equals("0") || mv.equals("")) {
                attribute = newlianhua(attribute.split("\\|"), lianhua, null, null);
            }
            else {
                attribute = newlianhua(attribute.split("\\|"), lianhua, null, null) + "|培养=" + v + "/" + mv;
            }
        } while (pv1.equals(pv2));
        return attribute;
    }
    
    public static String newlianhua(String[] v, String newlh, String newlq, String newsb) {
        for (int i = 0; i < v.length; ++i) {
            if (v[i] != null && v[i].indexOf("炼化属性") != -1) {
                if (newlh == null) {
                    newlh = v[i];
                }
                v[i] = null;
            }
            if (v[i] != null && v[i].indexOf("炼器属性") != -1) {
                if (newlq == null) {
                    newlq = v[i];
                }
                v[i] = null;
            }
            if (v[i] != null && v[i].indexOf("神兵属性") != -1) {
                if (newsb == null) {
                    newsb = v[i];
                }
                v[i] = null;
            }
        }
        StringBuffer buffer = new StringBuffer();
        for (int j = 0; j < v.length; ++j) {
            if (j == 0) {
                buffer.append(v[j]);
            }
            else if (v[j] != null) {
                buffer.append("|" + v[j]);
            }
        }
        if (newlh != null) {
            buffer.append("|" + newlh);
        }
        if (newlq != null) {
            buffer.append("|" + newlq);
        }
        if (newsb != null) {
            buffer.append("|" + newsb);
        }
        return buffer.toString();
    }
    
    public static String getAttribute(String decorateType, int i) {
        String typees = "";
        if (decorateType.indexOf("|") != -1) {
            String[] decorateTypes = decorateType.split("\\|");
            int[] a = randomArray(0, decorateTypes.length - 1, i);
            for (int j = 0; j < a.length; ++j) {
                typees = typees + "|" + decorateTypes[a[j]];
            }
            return typees;
        }
        else {
            return "|" + decorateType;
        }
    }
    
    public String getNewequip(Goodstable goods) {
        Map<String, List<Newequip>> sameNewequipMap = GameServer.getSameNewequipMap();
        Long type = Long.valueOf(goods.getType());
        String value = goods.getValue();
        String[] goodsInfo = value.split("\\|");
        String grade = goodsInfo[0].split("=")[1];
        String goodsType = getEquipType(type);
        List<Newequip> hatList = (List<Newequip>)sameNewequipMap.get(goodsType + grade);
        Newequip newequip = null;
        if (hatList.size() != 0) {
            newequip = (Newequip)hatList.get(NpcComposeAction.random.nextInt(hatList.size()));
            String types = this.getEquipRequire(newequip.getEquiptype());
            String[] vs = new String[12];
            List<String> vss = new ArrayList<>();
            for (int i = 0; i < vs.length; ++i) {
                String v = null;
                switch (i) {
                    case 0: {
                        v = newequip.getEquipfq();
                        break;
                    }
                    case 1: {
                        v = newequip.getEquipfskb();
                        break;
                    }
                    case 2: {
                        v = newequip.getEquipkwl();
                        break;
                    }
                    case 3: {
                        v = newequip.getEquipwla();
                        break;
                    }
                    case 4: {
                        v = newequip.getEquipkrf();
                        break;
                    }
                    case 5: {
                        v = newequip.getEquipqsc();
                        break;
                    }
                    case 6: {
                        v = newequip.getEquipksc();
                        break;
                    }
                    case 7: {
                        v = newequip.getEquipkxf();
                        break;
                    }
                    case 8: {
                        v = newequip.getEquipfsh();
                        break;
                    }
                    case 9: {
                        v = newequip.getEquipwle();
                        break;
                    }
                    case 10: {
                        v = newequip.getEquipqhp();
                        break;
                    }
                    case 11: {
                        v = newequip.getEquipqmp();
                        break;
                    }
                }
                if (v != null && !v.equals("")) {
                    String[] attrStrings = v.split("\\|");
                    if (attrStrings.length != 1 && NpcComposeAction.random.nextInt(100) <= Integer.parseInt(attrStrings[0])) {
                        String val = "0";
                        if (attrStrings[1].startsWith("-") || attrStrings[1].indexOf(".") > 0) {
                            val = WitchComposeAction.randomDoubleNum(attrStrings[1], false);
                        }
                        else {
                            String[] num = attrStrings[1].split("-");
                            int[] a = randomArray(Integer.parseInt(num[0]), Integer.parseInt(num[1]), 1);
                            val = String.valueOf(a[0]);
                        }
                        String[] attrbutes = attrStrings[2].split("&");
                        String attrs = attrbutes[NpcComposeAction.random.nextInt(attrbutes.length)];
                        int k = 0;
                        while (vss.contains(attrs) || (NpcComposeAction.map.get(attrs) != null && vss.contains(NpcComposeAction.map.get(attrs)))) {
                            attrs = attrbutes[NpcComposeAction.random.nextInt(attrbutes.length)];
                            if (++k > 10000) {
                                break;
                            }
                        }
                        vss.add(attrs);
                        if (val == null) {
                            System.out.println(GsonUtil.getGsonUtil().getgson().toJson(newequip));
                        }
                        vs[i] = attrs + "=" + val;
                    }
                }
            }
            StringBuffer buffer = new StringBuffer();
            buffer.append(types);
            for (int j = 0; j < vs.length; ++j) {
                if (vs[j] != null && !vs[j].equals("")) {
                    buffer.append("|");
                    buffer.append(vs[j]);
                }
            }
            return buffer.toString();
        }
        else {
            System.err.println("打造装备报错" + goodsType + grade);
            return "";
        }
    }
    
    public static String getEquipType(Long type) {
        String goodsType = null;
        if ((long)type == 601L || (long)type == 600L || (long)type == 6600L || (long)type == 6601L || (long)type == 7001L) {
            goodsType = "帽子";
        }
        else if ((long)type == 603L || (long)type == 7002L) {
            goodsType = "项链";
        }
        else if ((long)type == 605L || (long)type == 604L || (long)type == 6700L || (long)type == 6701L || (long)type == 7000L) {
            goodsType = "衣服";
        }
        else if ((long)type == 800L || (long)type == 6500L || (long)type == 7004L) {
            goodsType = "武器";
        }
        else if ((long)type == 602L || (long)type == 6900L || (long)type == 7003L) {
            goodsType = "鞋子";
        }
        else if ((long)type == 612L) {
            goodsType = "护身符";
        }
        return goodsType;
    }
    
    public String getEquipRequire(String equipTypes) {
        String[] types = equipTypes.split("\\|");
        String type = new String("|");
        if (types.length != 1) {
            String[] requString = types[1].split("&");
            int[] a = randomArray(0, requString.length - 1, Integer.parseInt(types[0]));
            for (int j = 0; j < a.length; ++j) {
                type += requString[a[j]];
            }
            return type;
        }
        else {
            return type;
        }
    }
    
    public static int[] randomArray(int min, int max, int n) {
        int len = max - min + 1;
        if (max < min || n > len) {
            return null;
        }
        int[] source = new int[len];
        for (int i = min; i < min + len; ++i) {
            source[i - min] = i;
        }
        int[] result = new int[n];
        int index = 0;
        for (int j = 0; j < result.length; ++j) {
            index = Math.abs(NpcComposeAction.random.nextInt() % len--);
            result[j] = source[index];
            source[index] = source[len];
        }
        return result;
    }
    
    public static String SynthesisOre(Goodstable ore, Goodstable ore1) {
        String Bottletext = ore.getValue();
        String[] gongneng = Bottletext.split("\\|");
        int ore_1 = Integer.parseInt(gongneng[0].split("\\=")[1]);
        String Bottletext2 = ore1.getValue();
        String[] gongneng2 = Bottletext2.split("\\|");
        int ore_2 = Integer.parseInt(gongneng2[0].split("\\=")[1]);
        long type = ore.getType();
        int ore_max;
        int ore_min;
        if (ore_1 > ore_2) {
            ore_max = ore_1;
            ore_min = ore_2;
        }
        else {
            ore_max = ore_2;
            ore_min = ore_1;
        }
        if (oreUp(ore_max, ore_min)) {
            if (type != 711L) {
                int ore_new = ore_max + 1;
                if (ore_new > 11) {
                    ore_new = 11;
                }
                int s = ore_new + 3;
                return "等级=" + ore_new + "|" + gongneng[1].split("\\=")[0] + "=" + s;
            }
            else {
                int ore_new = ore_max + 1;
                if (ore_new > 11) {
                    ore_new = 11;
                }
                double s2 = 0.5 + (double)ore_new * 0.5;
                return "等级=" + ore_new + "|抗风=" + s2 + "|抗雷=" + s2 + "|抗水=" + s2 + "|抗火=" + s2;
            }
        }
        else {
            return null;
        }
    }
    
    public static boolean oreUp(int ore_max, int ore_min) {
        int ore_cha = ore_max - ore_min;
        int Probability = 100 - 5 * ore_cha;
        if (Probability <= 1) {
            Probability = 1;
        }
        return NpcComposeAction.random.nextInt(ore_max * (ore_max - ore_min + 1) * (100 + ore_max * 5)) <= Probability + 420 - ore_max * 10;
    }
    
    public static String resetHSF(Goodstable goodstable, int add, int sj, int lvl) {
        String[] vs = goodstable.getValue().split("\\|");
        int pz = 0;
        for (int i = 0; i < vs.length; ++i) {
            String[] vsz = vs[i].split("=");
            if (lvl == 0 && vsz[0].equals("等级")) {
                lvl = Integer.parseInt(vsz[1]);
            }
            else if (vsz[0].equals("品质")) {
                pz = Integer.parseInt(vsz[1].split("/")[0]);
                break;
            }
        }
        if (sj != 0) {
            pz = sj;
        }
        else if (add > 0 && pz <= 900) {
            if (pz > 800) {
                add = (int)((double)add * 0.9);
            }
            if (pz > 850) {
                add = (int)((double)add * 0.9);
                if (add < 50) {
                    add = 51;
                }
            }
            if (pz > 880) {
                add = (int)((double)add * 0.8);
                if (add < 20) {
                    add = 20;
                }
            }
            pz += add;
            if (pz < 0) {
                pz = 0;
            }
        }
        if (add == -50) {
            sj = add;
        }
        if (pz > 1000) {
            pz = 1000;
        }
        StringBuffer buffer = new StringBuffer();
        buffer.append("等级=");
        buffer.append(lvl);
        buffer.append("|");
        buffer.append(vs[1]);
        buffer.append("|");
        buffer.append(vs[2]);
        for (int j = 3; j < vs.length; ++j) {
            if (vs[j].indexOf("炼化属性") != -1) {
                buffer.append("|");
                buffer.append("炼化属性");
                String[] vss = vs[j].split("\\&");
                for (int k = 1; k < vss.length; ++k) {
                    buffer.append("&");
                    String[] vsz2 = vss[k].split("=");
                    if (vsz2[0].equals("特技")) {
                        buffer.append(vss[k]);
                    }
                    else {
                        buffer.append(vsz2[0]);
                        buffer.append("=");
                        Alchemy alchemy = getAlchemy(NpcComposeAction.hsf, vsz2[0]);
                        if (alchemy != null) {
                            int size = 1;
                            if (vsz2[0].equals("力量") || vsz2[0].equals("根骨") || vsz2[0].equals("灵性") || vsz2[0].equals("敏捷") || vsz2[0].equals("加攻击") || vsz2[0].equals("加速度") || vsz2[0].equals("附加速度") || vsz2[0].equals("加气血") || vsz2[0].equals("加魔法") || vsz2[0].equals("反击次数") || vsz2[0].equals("连击次数")) {
                                size = 0;
                            }
                            double max = Double.parseDouble(alchemy.getAlchemymv());
                            max = max * (double)pz / 1000.0 * (double)lvl / 6.0;
                            BigDecimal big = new BigDecimal(max);
                            buffer.append(big.setScale(size, 4));
                        }
                        else {
                            buffer.append(vsz2[1]);
                        }
                    }
                }
            }
            else {
                String[] vsz3 = vs[j].split("=");
                if (vsz3[0].equals("品质")) {
                    buffer.append("|");
                    buffer.append(vsz3[0]);
                    buffer.append("=");
                    buffer.append(pz);
                    buffer.append("/");
                    buffer.append(1000);
                }
                else {
                    Alchemy alchemy2 = getAlchemy(NpcComposeAction.Hhsf, vsz3[0]);
                    if (alchemy2 != null) {
                        if (sj == 0) {
                            buffer.append("|");
                            String type = alchemy2.getAlchemykey();
                            buffer.append(type);
                            buffer.append("=");
                            int size2 = 1;
                            if (type.equals("力量") || type.equals("根骨") || type.equals("灵性") || type.equals("敏捷") || type.equals("加攻击") || type.equals("附加攻击") || type.equals("加速度") || type.equals("附加速度") || type.equals("加气血") || type.equals("附加气血") || type.equals("加魔法") || type.equals("反击次数") || type.equals("连击次数")) {
                                size2 = 0;
                            }
                            double max2 = Double.parseDouble(alchemy2.getAlchemymv());
                            max2 = max2 * (double)pz / 1000.0 * (double)lvl / 6.0;
                            BigDecimal big2 = new BigDecimal(max2);
                            buffer.append(big2.setScale(size2, 4));
                            int p = type.indexOf("上限");
                            if (p != -1) {
                                buffer.append("|");
                                type = type.substring(0, p);
                                buffer.append(type);
                                buffer.append("=-");
                                buffer.append(big2.setScale(size2, 4));
                            }
                        }
                        else if (sj != -1) {
                            sj = -1;
                            List<Alchemy> als = getAlchemys();
                            for (int l = 0; l < als.size(); ++l) {
                                buffer.append("|");
                                alchemy2 = (Alchemy)als.get(l);
                                String type2 = alchemy2.getAlchemykey();
                                buffer.append(type2);
                                buffer.append("=");
                                int size3 = 1;
                                if (type2.equals("力量") || type2.equals("根骨") || type2.equals("灵性") || type2.equals("敏捷") || type2.equals("附加攻击") || type2.equals("加攻击") || type2.equals("加速度") || type2.equals("附加速度") || type2.equals("加气血") || type2.equals("附加气血") || type2.equals("加魔法") || type2.equals("附加魔法") || type2.equals("反击次数") || type2.equals("连击次数")) {
                                    size3 = 0;
                                }
                                double max3 = Double.parseDouble(alchemy2.getAlchemymv());
                                max3 = max3 * (double)pz / 1000.0 * (double)lvl / 6.0;
                                BigDecimal big3 = new BigDecimal(max3);
                                buffer.append(big3.setScale(size3, 4));
                            }
                        }
                    }
                }
            }
        }
        return buffer.toString();
    }
    
    public static Alchemy getAlchemy(String type, String lei) {
        List<Alchemy> alchemies = (List<Alchemy>)GameServer.getAllAlchemy().get(type);
        if (alchemies != null) {
            for (int i = alchemies.size() - 1; i >= 0; --i) {
                if (((Alchemy)alchemies.get(i)).getAlchemykey().equals(lei)) {
                    return (Alchemy)alchemies.get(i);
                }
            }
        }
        return null;
    }
    
    public static List<Alchemy> getAlchemys() {
        List<Alchemy> alchemies = new ArrayList<>();
        List<Alchemy> als = (List<Alchemy>)GameServer.getAllAlchemy().get(NpcComposeAction.Hhsf);
        int a;
        int b;
        int size;
        for (a = 0, b = 0, size = als.size(), a = NpcComposeAction.random.nextInt(size), b = NpcComposeAction.random.nextInt(size); a == b && size > 1; b = NpcComposeAction.random.nextInt(size)) {}
        Alchemy alchemy = (Alchemy)als.get(a);
        alchemies.add(alchemy);
        int p = alchemy.getAlchemykey().indexOf("上限");
        if (p != -1) {
            Alchemy alc2 = new Alchemy();
            alc2.setAlchemyid(alchemy.getAlchemyid());
            alc2.setAlchemykey(alchemy.getAlchemykey().substring(0, p));
            alc2.setAlchemymv(-Double.parseDouble(alchemy.getAlchemymv()) + "");
            alc2.setAlchemysv(alchemy.getAlchemysv());
            alc2.setAlchemytype(alchemy.getAlchemytype());
            alchemies.add(alc2);
        }
        alchemy = (Alchemy)als.get(b);
        alchemies.add(alchemy);
        p = alchemy.getAlchemykey().indexOf("上限");
        if (p != -1) {
            Alchemy alc2 = new Alchemy();
            alc2.setAlchemyid(alchemy.getAlchemyid());
            alc2.setAlchemykey(alchemy.getAlchemykey().substring(0, p));
            alc2.setAlchemymv(-Double.parseDouble(alchemy.getAlchemymv()) + "");
            alc2.setAlchemysv(alchemy.getAlchemysv());
            alc2.setAlchemytype(alchemy.getAlchemytype());
            alchemies.add(alc2);
        }
        return alchemies;
    }
    
    public void resetCJS(Goodstable goodstable, int add) {
        String[] vs = goodstable.getValue().split("\\|");
        int pz = 0;
        int size = 0;
        int tf = 0;
        int sx = 0;
        int w = -1;
        List<Integer> a = new ArrayList<>();
        for (int i = 0; i < vs.length; ++i) {
            String[] vss = vs[i].split("=");
            if (vss[0].equals("品质")) {
                pz = Integer.parseInt(vss[1]);
            }
            else if (vss[0].equals("根骨") || vss[0].equals("灵性") || vss[0].equals("力量") || vss[0].equals("敏捷")) {
                int p = Integer.parseInt(vss[1]);
                size += p;
                if (p < 10) {
                    a.add(Integer.valueOf(i));
                }
                ++tf;
            }
        }
        size -= tf;
        if (tf == 4) {
            sx = 1962;
        }
        else if (tf == 3) {
            sx = 952;
        }
        else if (tf == 2) {
            sx = 383;
        }
        else {
            sx = 117;
        }
        pz += add;
        if (pz > sx) {
            pz = sx;
        }
        for (int i = getdian(pz) - size; i > 0; --i) {
            if (a.size() != 0) {
                int p2 = NpcComposeAction.random.nextInt(a.size());
                w = (int)a.get(p2);
                String[] vss2 = vs[w].split("=");
                int vv = Integer.parseInt(vss2[1]) + 1;
                vs[w] = vss2[0] + "=" + vv;
                if (vv >= 10) {
                    a.remove(p2);
                }
            }
        }
        StringBuffer buffer = new StringBuffer();
        for (int j = 0; j < vs.length; ++j) {
            if (j != 0) {
                buffer.append("|");
            }
            if (vs[j].startsWith("品质")) {
                buffer.append("品质=");
                buffer.append(pz);
            }
            else {
                buffer.append(vs[j]);
            }
        }
        goodstable.setValue(buffer.toString());
    }
    
    public static int getdian(int pz) {
        double s = 0.0;
        double dz = 1.0;
        for (int i = 0; i < 196; ++i) {
            if (i >= 160) {
                dz = 22.0;
            }
            else if (i >= 122) {
                dz = 15.0;
            }
            else if (i >= 81) {
                dz = 8.0;
            }
            else if (i >= 40) {
                dz = 4.0;
            }
            else {
                dz = 2.0;
            }
            s += dz;
            if ((double)pz < s) {
                return i;
            }
        }
        return 196;
    }
    
    public void updata(Goodstable[] goods) {
        for (int i = 0; i < goods.length; ++i) {
            if (goods[i] != null) {
                AllServiceUtil.getGoodsTableService().updateGoodRedis(goods[i]);
                if (i == 0) {
                    AllServiceUtil.getGoodsrecordService().insert(goods[i], null, Integer.valueOf(1), Integer.valueOf(8));
                }
                else {
                    long gType = goods[i].getType();
                    if (gType != 212L && (gType < 497L || gType > 500L) && gType != 505L && gType != 915L && gType != 923L) {
                        AllServiceUtil.getGoodsrecordService().insert(goods[i], null, Integer.valueOf(1), Integer.valueOf(6));
                    }
                }
            }
        }
    }
    
    static {
        NpcComposeAction.random = new Random();
        NpcComposeAction.map = new HashMap<>();
        NpcComposeAction.TJ = new String[21];
        for (int i = 0; i < NpcComposeAction.TJ.length; ++i) {
            NpcComposeAction.TJ[i] = 8001 + i + "";
        }
        NpcComposeAction.map.put("忽视抗混", "加强混乱");
        NpcComposeAction.map.put("忽视抗封", "加强封印");
        NpcComposeAction.map.put("忽视抗睡", "加强昏睡");
        NpcComposeAction.map.put("忽视抗毒", "加强中毒");
        NpcComposeAction.map.put("忽视抗风", "加强风");
        NpcComposeAction.map.put("忽视抗水", "加强水");
        NpcComposeAction.map.put("忽视抗雷", "加强雷");
        NpcComposeAction.map.put("忽视抗火", "加强火");
        NpcComposeAction.map.put("忽视鬼火", "加强鬼火");
        NpcComposeAction.map.put("忽视遗忘", "加强遗忘");
        NpcComposeAction.map.put("加强混乱", "忽视抗混");
        NpcComposeAction.map.put("加强封印", "忽视抗封");
        NpcComposeAction.map.put("加强昏睡", "忽视抗睡");
        NpcComposeAction.map.put("加强中毒", "忽视抗毒");
        NpcComposeAction.map.put("加强风", "忽视抗风");
        NpcComposeAction.map.put("加强水", "忽视抗水");
        NpcComposeAction.map.put("加强雷", "忽视抗雷");
        NpcComposeAction.map.put("加强火", "忽视抗火");
        NpcComposeAction.map.put("加强鬼火", "忽视鬼火");
        NpcComposeAction.map.put("加强遗忘", "忽视遗忘");
        NpcComposeAction.hsf = "护身符";
        NpcComposeAction.Hhsf = "黄护身符";
    }
}
