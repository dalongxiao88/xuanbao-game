package org.come.action.suit;

import org.come.action.npc.NpcComposeAction;
import come.tool.Role.PartJade;
import org.come.model.Skill;
import org.come.bean.QualityClBean;
import org.come.model.Alchemy;
import java.util.ArrayList;
import come.tool.FightingData.Battlefield;
import org.come.action.reward.DrawnitemsAction;
import java.util.List;
import come.tool.Role.RoleData;
import org.come.handler.SendMessage;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import org.come.action.monitor.MonitorUtil;
import come.tool.Stall.AssetUpdate;
import org.come.server.GameServer;
import org.come.tool.Goodtype;
import org.come.entity.Goodstable;
import java.math.BigDecimal;
import come.tool.Role.RolePool;
import org.come.bean.SuitOperBean;
import io.netty.channel.ChannelHandlerContext;
import org.come.bean.LoginResult;

public class SuitPetEquip
{
    public static void type41(LoginResult loginResult, ChannelHandlerContext ctx, SuitOperBean suitOperBean) {
        RoleData roleData = RolePool.getRoleData(loginResult.getRole_id());
        if (roleData == null) {
            return;
        }
        List<Goodstable> goods = SuitComposeAction.getGoods(suitOperBean.getGoods(), loginResult.getRole_id(), 0);
        if (goods == null) {
            return;
        }
        BigDecimal money = new BigDecimal(1000000);
        if (loginResult.getGold().compareTo(money) < 0) {
            return;
        }
        if (goods.size() < 2) {
            return;
        }
        Goodstable summonEquip = (Goodstable)goods.get(0);
        if (!Goodtype.isSummonEquip(summonEquip.getType())) {
            return;
        }
        Goodstable summonGoods = (Goodstable)goods.get(1);
        if (summonGoods.getType() != 513L) {
            return;
        }
        summonGoods.goodxh(1);
        if ((int)summonGoods.getUsetime() < 0) {
            return;
        }
        String[] value = summonEquip.getValue().split("\\|");
        Integer mysticism = Integer.valueOf(Integer.parseInt(value[5].split("=")[1]));
        Integer level = Integer.valueOf(Integer.parseInt(value[0].split("=")[1]));
        Integer quality = Integer.valueOf(Integer.parseInt(value[4].split("=")[1]));
        if (mysticism == null || level == null || quality == null) {
            return;
        }
        if ((int)mysticism >= 6000) {
            return;
        }
        int num = 500;
        mysticism = Integer.valueOf((int)mysticism + num);
        if ((int)level != 6 && (int)mysticism >= (int)level * 1000) {
            mysticism = Integer.valueOf(0);
            level = Integer.valueOf((int)level + 1);
            getbasics(value, quality, level, null);
            Goodstable nextSummonEquip = (Goodstable)GameServer.getAllGoodsMap().get(summonEquip.getGoodsid().add(new BigDecimal(1)));
            summonEquip.setSkin(nextSummonEquip.getSkin());
            summonEquip.setInstruction(nextSummonEquip.getInstruction());
            summonEquip.setGoodsname(nextSummonEquip.getGoodsname());
            summonEquip.setGoodsid(nextSummonEquip.getGoodsid());
        }
        String[] refinins = value[6].split("&");
        String amendRefiningAttribute = amendRefiningAttribute(refinins, summonEquip.getType(), quality, level, (int)mysticism);
        value[6] = amendRefiningAttribute;
        value[5] = "通灵=" + mysticism;
        value[0] = "等级=" + level;
        AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
        loginResult.setGold(loginResult.getGold().subtract(money));
        MonitorUtil.getMoney().useD(money.longValue());
        assetUpdate.updata("D=-" + money);
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < value.length; ++i) {
            if (buffer.length() != 0) {
                buffer.append("|");
            }
            buffer.append(value[i]);
        }
        summonEquip.setValue(buffer.toString());
        assetUpdate.setGood(summonEquip);
        assetUpdate.setMsg("培养成功,获得" + num + "点通灵值");
        SuitComposeAction.saveGoods(goods, false);
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public static void type42(LoginResult loginResult, ChannelHandlerContext ctx, SuitOperBean suitOperBean) {
        RoleData roleData = RolePool.getRoleData(loginResult.getRole_id());
        if (roleData == null) {
            return;
        }
        List<Goodstable> goods = SuitComposeAction.getGoods(suitOperBean.getGoods(), loginResult.getRole_id(), 0);
        if (goods == null) {
            return;
        }
        Goodstable summonEquip = (Goodstable)goods.get(0);
        if (!Goodtype.isSummonEquip(summonEquip.getType())) {
            return;
        }
        summonEquip.goodxh(1);
        if ((int)summonEquip.getUsetime() < 0) {
            return;
        }
        int num = 30;
        loginResult.setScore(DrawnitemsAction.Splice(loginResult.getScore(), "比斗奖章=" + num, 2));
        AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
        assetUpdate.setData("比斗奖章=" + num);
        assetUpdate.setMsg("分解成功,获得了" + num + "个比斗奖章");
        SuitComposeAction.saveGoods(goods, false);
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public static void type43(LoginResult loginResult, ChannelHandlerContext ctx, SuitOperBean suitOperBean) {
        RoleData roleData = RolePool.getRoleData(loginResult.getRole_id());
        if (roleData == null) {
            return;
        }
        List<Goodstable> goods = SuitComposeAction.getGoods(suitOperBean.getGoods(), loginResult.getRole_id(), 0);
        if (goods == null) {
            return;
        }
        BigDecimal money = new BigDecimal(1000000);
        if (loginResult.getGold().compareTo(money) < 0) {
            return;
        }
        if (goods.size() < 6) {
            return;
        }
        Goodstable summonEquip = (Goodstable)goods.get(0);
        if (!Goodtype.isSummonEquip(summonEquip.getType())) {
            return;
        }
        Goodstable summonGoods = (Goodstable)goods.get(1);
        if (summonGoods.getType() != 513L) {
            return;
        }
        Goodstable summonElixir = (Goodstable)goods.get(2);
        if (summonElixir.getType() != 497L) {
            return;
        }
        for (int i = 3; i < goods.size(); ++i) {
            if (((Goodstable)goods.get(i)).getType() != 498L) {
                return;
            }
        }
        for (int i = 1; i < goods.size(); ++i) {
            ((Goodstable)goods.get(i)).goodxh(1);
            if ((int)((Goodstable)goods.get(i)).getUsetime() < 0) {
                return;
            }
        }
        String[] value = summonEquip.getValue().split("\\|");
        Integer level = Integer.valueOf(Integer.parseInt(value[0].split("=")[1]));
        Integer mysticism = Integer.valueOf(Integer.parseInt(value[5].split("=")[1]));
        int newQuality = Battlefield.random.nextInt(21) + 80;
        String randomBasics = randomBasics();
        getbasics(value, Integer.valueOf(newQuality), level, randomBasics);
        value[4] = "品质=" + newQuality;
        value[6] = petAlchemy(summonEquip.getType(), newQuality, (int)level, (int)mysticism);
        AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
        StringBuffer buffer1 = new StringBuffer();
        for (int j = 0; j < value.length; ++j) {
            if (buffer1.length() != 0) {
                buffer1.append("|");
            }
            buffer1.append(value[j]);
        }
        summonEquip.setValue(buffer1.toString());
        assetUpdate.setGood(summonEquip);
        loginResult.setGold(loginResult.getGold().subtract(money));
        MonitorUtil.getMoney().useD(money.longValue());
        assetUpdate.updata("D=-" + money);
        assetUpdate.setMsg("#G重铸成功");
        SuitComposeAction.saveGoods(goods, false);
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public static String petAlchemy(long type, int pz, int lvl, int mysticism) {
        List<Alchemy> alchemies = (List<Alchemy>)GameServer.getAllAlchemy().get(getTypeName(type));
        int nextInt = Battlefield.random.nextInt(3) + 1;
        ArrayList<Integer> integers = new ArrayList<>();
        int num = 0;
        while (alchemies.size() > 3 && nextInt != integers.size() && ++num <= 100000) {
            int nextInt2 = Battlefield.random.nextInt(alchemies.size());
            if (integers.contains(Integer.valueOf(nextInt2))) {
                continue;
            }
            else {
                integers.add(Integer.valueOf(nextInt2));
            }
        }
        StringBuffer buffer = new StringBuffer();
        buffer.append("炼化属性");
        for (int i = 0; i < integers.size(); ++i) {
            buffer.append("&");
            Alchemy alchemy = (Alchemy)alchemies.get((int)integers.get(i));
            if (alchemy.getAlchemykey().endsWith("等级")) {
                buffer.append(alchemy.getAlchemykey() + "=" + lvl);
            }
            else {
                BigDecimal add = calculateRefining(alchemy, Integer.valueOf(pz), mysticism);
                buffer.append(alchemy.getAlchemykey() + "=" + add);
            }
        }
        return buffer.toString();
    }
    
    public static void type44(LoginResult loginResult, ChannelHandlerContext ctx, SuitOperBean suitOperBean) {
        RoleData roleData = RolePool.getRoleData(loginResult.getRole_id());
        if (roleData == null) {
            return;
        }
        List<Goodstable> goods = SuitComposeAction.getGoods(suitOperBean.getGoods(), loginResult.getRole_id(), 0);
        if (goods == null) {
            return;
        }
        BigDecimal money = new BigDecimal(50000);
        if (loginResult.getGold().compareTo(money) < 0) {
            return;
        }
        if (goods.size() < 2) {
            return;
        }
        Goodstable summonEquip = (Goodstable)goods.get(0);
        if (!Goodtype.isSummonEquip(summonEquip.getType())) {
            return;
        }
        Goodstable summonGoods = (Goodstable)goods.get(1);
        if (summonGoods.getType() != 515L) {
            return;
        }
        summonGoods.goodxh(1);
        if ((int)summonGoods.getUsetime() < 0) {
            return;
        }
        String[] value = summonEquip.getValue().split("\\|");
        String summonEquipSkill = isSummonEquipSkill(value);
        if (summonEquipSkill == null) {
            return;
        }
        boolean is = Battlefield.random.nextInt(100) < 80;
        if (is) {
            // // 重悟技能1300-1337
            int id = Battlefield.random.nextInt(38) + 1300;
            while (id == 1300 || id == 1301 || id == 1306 || id == 1307 || id == 1313 || id == 1314|| id == 1335 || id == 1336 || id == 1337) {
                if (id == 1314 && Battlefield.random.nextInt(100) < 40) {//小于40几率不保存
                    break;
                }
                else if (id == 1337 && Battlefield.random.nextInt(100) < 30) { // 为新技能添加特殊概率//1337灯火阑珊
                    break;
                }
                else if (Battlefield.random.nextInt(100) < 60) {
                    break;
                }
                else {
                    id = Battlefield.random.nextInt(38) + 1300;
                }
            }
            Skill skill = GameServer.getSkill(id + "");
            if (skill == null) {
                return;
            }
            int skillQuality = Battlefield.random.nextInt(50) + 1;
            BigDecimal divide = new BigDecimal(skillQuality).divide(new BigDecimal(10), 1, 4);
            value[7] = "觉醒技&" + skill.getSkillid() + "&" + divide + "&" + summonEquipSkill.split("&")[3];
            QualityClBean qualityClBean = new QualityClBean();
            qualityClBean.setRgid(summonEquip.getRgid());
            qualityClBean.setType(44);
            StringBuffer buffer1 = new StringBuffer();
            for (int i = 0; i < value.length; ++i) {
                if (buffer1.length() != 0) {
                    buffer1.append("|");
                }
                buffer1.append(value[i]);
            }
            qualityClBean.setNewAttr(value[7]);
            QualityCPool.getcPool().addExtra(qualityClBean);
            SendMessage.sendMessageToSlef(ctx, Agreement.ExtrattroperAgreement(GsonUtil.getGsonUtil().getgson().toJson(qualityClBean)));
        }
        AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
        loginResult.setGold(loginResult.getGold().subtract(money));
        MonitorUtil.getMoney().useD(money.longValue());
        assetUpdate.updata("D=-" + money);
        assetUpdate.setMsg(is ? null : "重悟技能失败");
        SuitComposeAction.saveGoods(goods, false);
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public static void type45(LoginResult loginResult, ChannelHandlerContext ctx, SuitOperBean suitOperBean) {
        RoleData roleData = RolePool.getRoleData(loginResult.getRole_id());
        if (roleData == null) {
            return;
        }
        List<Goodstable> goods = SuitComposeAction.getGoods(suitOperBean.getGoods(), loginResult.getRole_id(), 0);
        if (goods == null) {
            return;
        }
        BigDecimal money = new BigDecimal(10000000);
        if (loginResult.getGold().compareTo(money) < 0) {
            return;
        }
        if (goods.size() < 2) {
            return;
        }
        Goodstable summonEquip = goods.get(0);
        if (!Goodtype.isSummonEquip(summonEquip.getType())) {
            return;
        }
        Goodstable summonGoods = goods.get(1);
        if (summonGoods.getType() != 514L) {
            return;
        }
        summonGoods.goodxh(1);
        if ((int)summonGoods.getUsetime() < 0) {
            return;
        }
        String[] value = summonEquip.getValue().split("\\|");
        if (isSummonEquipSkill(value) != null) {
            return;
        }
        AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
        loginResult.setGold(loginResult.getGold().subtract(money));
        MonitorUtil.getMoney().useD(money.longValue());
        assetUpdate.updata("D=-" + money);
        int nextInt = Battlefield.random.nextInt(100);
        if (nextInt < 15) {
            int id = Battlefield.random.nextInt(36) + 1300;
            Skill skill = GameServer.getSkill(id + "");
            if (skill == null) {
                return;
            }
            int skillQuality = Battlefield.random.nextInt(50) + 1;
            BigDecimal divide = new BigDecimal(skillQuality).divide(new BigDecimal(10), 1, 4);
            String skillMessage = "觉醒技&" + skill.getSkillid() + "&" + divide + "&" + 0;
            StringBuffer buffer1 = new StringBuffer();
            for (int i = 0; i < value.length; ++i) {
                if (buffer1.length() != 0) {
                    buffer1.append("|");
                }
                buffer1.append(value[i]);
            }
            buffer1.append("|" + skillMessage);
            summonEquip.setValue(buffer1.toString());
            assetUpdate.setMsg("#G开启成功" + skill.getSkillname());
            assetUpdate.setGood(summonEquip);
            SuitComposeAction.saveGoods(goods, false);
        }
        else {
            assetUpdate.setMsg("开启失败");
            SuitComposeAction.saveGoods(goods, true);
        }
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public static void type46(LoginResult loginResult, ChannelHandlerContext ctx, SuitOperBean suitOperBean) {
        RoleData roleData = RolePool.getRoleData(loginResult.getRole_id());
        if (roleData == null) {
            return;
        }
        List<Goodstable> goods = SuitComposeAction.getGoods(suitOperBean.getGoods(), loginResult.getRole_id(), 0);
        if (goods == null) {
            return;
        }
        PartJade jade = suitOperBean.getJade();
        if (jade == null) {
            return;
        }
        int suitid = jade.getSuitid();
        if (new BigDecimal(suitid).compareTo(loginResult.getScoretype("比斗奖章")) > 0) {
            return;
        }
        BigDecimal money = new BigDecimal(suitid * 6000);
        if (money.compareTo(loginResult.getGold()) > 0) {
            return;
        }
        if (goods.size() < 1) {
            return;
        }
        Goodstable summonEquip = (Goodstable)goods.get(0);
        if (!Goodtype.isSummonEquip(summonEquip.getType())) {
            return;
        }
        String[] value = summonEquip.getValue().split("\\|");
        String summonEquipSkill = isSummonEquipSkill(value);
        if (summonEquipSkill == null) {
            return;
        }
        String[] skillMessage = summonEquipSkill.split("&");
        long parseLong = Long.parseLong(skillMessage[3]);
        if (parseLong >= 48450L) {
            return;
        }
        if (Battlefield.random.nextInt(100) < 5) {
            parseLong += (long)suitid;
        }
        parseLong += (long)suitid;
        if (parseLong > 48450L) {
            parseLong = 48450L;
        }
        skillMessage[3] = String.valueOf(parseLong);
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < skillMessage.length; ++i) {
            if (buffer.length() != 0) {
                buffer.append("&");
            }
            buffer.append(skillMessage[i]);
        }
        value[7] = buffer.toString();
        StringBuffer buffer2 = new StringBuffer();
        for (int j = 0; j < value.length; ++j) {
            if (buffer2.length() != 0) {
                buffer2.append("|");
            }
            buffer2.append(value[j]);
        }
        summonEquip.setValue(buffer2.toString());
        QualityClBean qualityClBean = new QualityClBean();
        qualityClBean.setRgid(summonEquip.getRgid());
        qualityClBean.setType(46);
        qualityClBean.setNewAttr(value[7]);
        loginResult.setScore(DrawnitemsAction.Splice(loginResult.getScore(), "比斗奖章=" + suitid, 3));
        AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
        assetUpdate.updata("比斗奖章=" + -suitid);
        assetUpdate.setGood(summonEquip);
        assetUpdate.setMsg("此次升级了" + suitid + "经验");
        SuitComposeAction.saveGoods(goods, false);
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
        SendMessage.sendMessageToSlef(ctx, Agreement.ExtrattroperAgreement(GsonUtil.getGsonUtil().getgson().toJson(qualityClBean)));
    }
    
    public static void getbasics(String[] value, Integer quality, Integer level, String basicsName) {
        String[] basics = value[3].split("=");
        basics[1] = (int)((double)(int)quality / 100.0 * (double)(int)level * 5.0) + "";
        if (basicsName != null) {
            value[3] = basicsName + "=" + basics[1];
        }
        else {
            value[3] = basics[0] + "=" + basics[1];
        }
    }
    
    public static String getTypeName(long type) {
        if (type == 510L) {
            return "兽环";
        }
        if (type == 511L) {
            return "兽铃";
        }
        if (type == 512L) {
            return "兽甲";
        }
        return null;
    }
    
    public static String amendRefiningAttribute(String[] refinins, long type, Integer quality, Integer level, int mysticism) {
        for (int i = 1; i < refinins.length; ++i) {
            String typeName = getTypeName(type);
            if (typeName == null) {
                typeName = "兽环";
            }
            if (typeName != null) {
                if (!refinins[i].split("=")[0].endsWith("等级")) {
                    Alchemy alchemy = NpcComposeAction.getAlchemy(typeName, refinins[i].split("=")[0]);
                    if (alchemy != null) {
                        BigDecimal add = calculateRefining(alchemy, quality, mysticism);
                        refinins[i] = refinins[i].split("=")[0] + "=" + add;
                    }
                }
                else {
                    refinins[i] = refinins[i].split("=")[0] + "=" + level;
                }
            }
        }
        StringBuffer buffer = new StringBuffer();
        for (int j = 0; j < refinins.length; ++j) {
            if (buffer.length() != 0) {
                buffer.append("&");
            }
            buffer.append(refinins[j]);
        }
        return buffer.toString();
    }
    
    public static String isSummonEquipSkill(String[] value) {
        for (int i = 0; i < value.length; ++i) {
            if (value[i].startsWith("觉醒技")) {
                return value[i];
            }
        }
        return null;
    }
    
    public static String randomBasics() {
        int nextInt = Battlefield.random.nextInt(4);
        if (nextInt == 0) {
            return "根骨";
        }
        if (nextInt == 1) {
            return "灵性";
        }
        if (nextInt == 2) {
            return "力量";
        }
        return "敏捷";
    }
    
    public static BigDecimal calculateRefining(Alchemy alchemy, Integer quality, int mysticism) {
        BigDecimal sv = new BigDecimal(alchemy.getAlchemysv());
        BigDecimal mv = new BigDecimal(alchemy.getAlchemymv());
        BigDecimal add = mv.subtract(sv).multiply(new BigDecimal(mysticism)).divide(new BigDecimal(6000), 10, 4).add(sv).multiply(new BigDecimal((int)quality)).divide(new BigDecimal(100), 10, 4).setScale(1, 1);
        return add;
    }
    
    public static int expChangeLevel(long exp) {
        for (int i = 1; i <= 20; ++i) {
            long num = (long)((i * 250 + 50 + 300) * i / 2);
            if (num > exp) {
                return i;
            }
        }
        return 20;
    }
    
    public static long getMaxExp(long level) {
        return level * 250L + 50L;
    }
    
    public static long getNowExp(long exp) {
        long maxExp = getMaxExp((long)expChangeLevel(exp));
        long num = (maxExp * 250L + 50L + 300L) * maxExp / 2L;
        return exp - num;
    }
}
