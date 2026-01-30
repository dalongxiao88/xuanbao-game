package org.come.action.suit;

import org.come.action.npc.NpcComposeAction;
import java.util.ArrayList;
import org.come.action.reward.DrawnitemsAction;
import come.tool.Role.PartJade;
import org.come.bean.QualityClBean;
import come.tool.FightingData.Battlefield;
import org.come.model.Alchemy;
import org.come.server.GameServer;
import java.util.List;
import come.tool.Role.RoleData;
import org.come.handler.SendMessage;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import org.come.action.monitor.MonitorUtil;
import come.tool.Stall.AssetUpdate;
import org.come.entity.Goodstable;
import java.math.BigDecimal;
import come.tool.Role.RolePool;
import org.come.bean.SuitOperBean;
import io.netty.channel.ChannelHandlerContext;
import org.come.bean.LoginResult;

public class StarCard
{
    public static void type51(LoginResult loginResult, ChannelHandlerContext ctx, SuitOperBean suitOperBean) {
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
        Goodstable starCardEquip = (Goodstable)goods.get(0);
        if (starCardEquip.getType() != 520L) {
            return;
        }
        Goodstable materialGoods = (Goodstable)goods.get(1);
        if (materialGoods.getType() != 522L) {
            return;
        }
        materialGoods.goodxh(1);
        if ((int)materialGoods.getUsetime() < 0) {
            return;
        }
        String[] value = starCardEquip.getValue().split("\\|");
        String[] level = value[0].split("=")[1].split("/");
        Integer lvlNow = Integer.valueOf(Integer.parseInt(level[0]));
        int power = Integer.parseInt(value[1].split("=")[1]);
        if (power >= (int)lvlNow * 200) {
            return;
        }
        int addPower = 100;
        power += addPower;
        if (power >= (int)lvlNow * 200) {
            power = (int)lvlNow * 200;
        }
        value[1] = "神力=" + power;
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
        starCardEquip.setValue(buffer.toString());
        assetUpdate.setGood(starCardEquip);
        assetUpdate.setMsg("培养成功,获得" + addPower + "点神力");
        SuitComposeAction.saveGoods(goods, false);
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public static void type52(LoginResult loginResult, ChannelHandlerContext ctx, SuitOperBean suitOperBean) {
        RoleData roleData = RolePool.getRoleData(loginResult.getRole_id());
        if (roleData == null) {
            return;
        }
        List<Goodstable> goods = SuitComposeAction.getGoods(suitOperBean.getGoods(), loginResult.getRole_id(), 0);
        if (goods == null) {
            return;
        }
        if (goods.size() < 2) {
            return;
        }
        Goodstable starCardEquip = (Goodstable)goods.get(0);
        if (starCardEquip.getType() != 520L) {
            return;
        }
        Goodstable materialGoods = (Goodstable)goods.get(1);
        if (materialGoods.getType() != 500L) {
            return;
        }
        int materialsLvl = Integer.parseInt(materialGoods.getValue().split("=")[1]);
        if (materialsLvl != 11) {
            return;
        }
        materialGoods.goodxh(1);
        if ((int)materialGoods.getUsetime() < 0) {
            return;
        }
        String[] value = starCardEquip.getValue().split("\\|");
        String[] level = value[0].split("=")[1].split("/");
        Integer lvlNow = Integer.valueOf(Integer.parseInt(level[0]));
        Integer lvlMax = Integer.valueOf(Integer.parseInt(level[1]));
        int power = Integer.parseInt(value[1].split("=")[1]);
        if (power < (int)lvlNow * 200) {
            return;
        }
        if ((int)lvlNow >= (int)lvlMax) {
            return;
        }
        BigDecimal money = new BigDecimal((int)lvlNow * 20000000);
        if (loginResult.getGold().compareTo(money) < 0) {
            return;
        }
        lvlNow = Integer.valueOf((int)lvlNow + 1);
        value[3] = upgradeAttribute(value, (int)lvlNow);
        value[1] = "神力=0";
        value[0] = "等级=" + lvlNow + "/" + lvlMax;
        changeStarCardSkin(starCardEquip, (int)lvlNow);
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
        starCardEquip.setValue(buffer.toString());
        assetUpdate.setGood(starCardEquip);
        assetUpdate.setMsg("升级成功");
        SuitComposeAction.saveGoods(goods, false);
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public static void type53(LoginResult loginResult, ChannelHandlerContext ctx, SuitOperBean suitOperBean) {
        RoleData roleData = RolePool.getRoleData(loginResult.getRole_id());
        if (roleData == null) {
            return;
        }
        List<Goodstable> goods = SuitComposeAction.getGoods(suitOperBean.getGoods(), loginResult.getRole_id(), 0);
        if (goods == null) {
            return;
        }
        BigDecimal money = new BigDecimal(100000);
        if (loginResult.getGold().compareTo(money) < 0) {
            return;
        }
        if (goods.size() < 2) {
            return;
        }
        Goodstable starCardEquip = (Goodstable)goods.get(0);
        if (starCardEquip.getType() != 520L) {
            return;
        }
        Goodstable materialGoods = (Goodstable)goods.get(1);
        if (materialGoods.getType() != 524L) {
            return;
        }
        materialGoods.goodxh(1);
        if ((int)materialGoods.getUsetime() < 0) {
            return;
        }
        String[] value = starCardEquip.getValue().split("\\|");
        String refining = value[3];
        if (!refining.startsWith("炼化属性")) {
            return;
        }
        String[] level = value[0].split("=")[1].split("/");
        Integer lvlNow = Integer.valueOf(Integer.parseInt(level[0]));
        List<Alchemy> list = (List<Alchemy>)GameServer.getAllAlchemy().get("星卡");
        Alchemy alchemy1 = (Alchemy)list.get(Battlefield.random.nextInt(list.size()));
        int randomValue = Battlefield.random.nextInt(100);
        Alchemy alchemy2;
        if (randomValue < 5) {
            alchemy2 = alchemy1;
        }
        else {
            do {
                alchemy2 = (Alchemy)list.get(Battlefield.random.nextInt(list.size()));
            } while (alchemy2 == alchemy1);
        }
        int aptitude = Battlefield.random.nextInt(31) + 70;
        value[3] = anewRefining((int)lvlNow, aptitude, alchemy1, alchemy2, 1);
        AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
        StringBuffer buffer1 = new StringBuffer();
        for (int i = 0; i < value.length; ++i) {
            if (buffer1.length() != 0) {
                buffer1.append("|");
            }
            buffer1.append(value[i]);
        }
        QualityClBean qualityClBean = new QualityClBean();
        qualityClBean.setRgid(starCardEquip.getRgid());
        qualityClBean.setType(53);
        qualityClBean.setNewAttr(value[3]);
        QualityCPool.getcPool().addExtra(qualityClBean);
        loginResult.setGold(loginResult.getGold().subtract(money));
        MonitorUtil.getMoney().useD(money.longValue());
        assetUpdate.updata("D=-" + money);
        SuitComposeAction.saveGoods(goods, false);
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
        SendMessage.sendMessageToSlef(ctx, Agreement.ExtrattroperAgreement(GsonUtil.getGsonUtil().getgson().toJson(qualityClBean)));
    }
    
    public static void type54(LoginResult loginResult, ChannelHandlerContext ctx, SuitOperBean suitOperBean) {
        RoleData roleData = RolePool.getRoleData(loginResult.getRole_id());
        if (roleData == null) {
            return;
        }
        List<Goodstable> goods = SuitComposeAction.getGoods(suitOperBean.getGoods(), loginResult.getRole_id(), 0);
        if (goods == null) {
            return;
        }
        BigDecimal money = new BigDecimal(100000);
        if (loginResult.getGold().compareTo(money) < 0) {
            return;
        }
        if (goods.size() < 2) {
            return;
        }
        Goodstable starCardEquip = (Goodstable)goods.get(0);
        if (starCardEquip.getType() != 520L) {
            return;
        }
        Goodstable materialGoods = (Goodstable)goods.get(1);
        if (materialGoods.getType() != 523L) {
            return;
        }
        materialGoods.goodxh(1);
        if ((int)materialGoods.getUsetime() < 0) {
            return;
        }
        String[] value = starCardEquip.getValue().split("\\|");
        value[4] = anewFiveElements();
        QualityClBean qualityClBean = new QualityClBean();
        qualityClBean.setRgid(starCardEquip.getRgid());
        qualityClBean.setType(54);
        qualityClBean.setNewAttr(value[4]);
        AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
        loginResult.setGold(loginResult.getGold().subtract(money));
        MonitorUtil.getMoney().useD(money.longValue());
        assetUpdate.updata("D=-" + money);
        SuitComposeAction.saveGoods(goods, false);
        QualityCPool.getcPool().addExtra(qualityClBean);
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
        SendMessage.sendMessageToSlef(ctx, Agreement.ExtrattroperAgreement(GsonUtil.getGsonUtil().getgson().toJson(qualityClBean)));
    }
    
    public static void type55(LoginResult loginResult, ChannelHandlerContext ctx, SuitOperBean suitOperBean) {
        RoleData roleData = RolePool.getRoleData(loginResult.getRole_id());
        if (roleData == null) {
            return;
        }
        List<Goodstable> goods = SuitComposeAction.getGoods(suitOperBean.getGoods(), loginResult.getRole_id(), 0);
        if (goods == null) {
            return;
        }
        if (goods.size() < 2) {
            return;
        }
        Goodstable starCardEquip = (Goodstable)goods.get(0);
        if (starCardEquip.getType() != 520L) {
            return;
        }
        Goodstable materialGoods = (Goodstable)goods.get(1);
        if (materialGoods.getType() != 521L) {
            return;
        }
        String[] value = starCardEquip.getValue().split("\\|");
        String[] level = value[0].split("=")[1].split("/");
        Integer lvlNow = Integer.valueOf(Integer.parseInt(level[0]));
        Integer lvlMax = Integer.valueOf(Integer.parseInt(level[1]));
        if ((int)lvlMax >= 14) {
            return;
        }
        int num = (int)lvlMax / 2 + 2;
        materialGoods.goodxh(num);
        if ((int)materialGoods.getUsetime() < 0) {
            return;
        }
        boolean is = false;
        if (Battlefield.isV((double)(45 - (int)lvlMax / 2))) {
            lvlMax = Integer.valueOf((int)lvlMax + 1);
            value[0] = "等级=" + lvlNow + "/" + lvlMax;
            is = true;
        }
        AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
        StringBuffer buffer1 = new StringBuffer();
        for (int i = 0; i < value.length; ++i) {
            if (buffer1.length() != 0) {
                buffer1.append("|");
            }
            buffer1.append(value[i]);
        }
        starCardEquip.setValue(buffer1.toString());
        assetUpdate.setGood(starCardEquip);
        SuitComposeAction.saveGoods(goods, false);
        assetUpdate.setMsg(is ? "提升等级成功" : "提升等级失败");
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
        if (is && (int)lvlMax > 13) {
            StringBuffer sb = new StringBuffer();
            sb.append("#G扬天长啸！");
            sb.append("#Y");
            sb.append(loginResult.getRolename());
            sb.append("#G看着手里刚出炉的");
            sb.append("#R14级星符，");
            sb.append("#G，心潮澎湃，大吼道：此物在手，天下我有！");
            SuitMixdeal.jpdC(sb.toString());
        }
    }
    
    public static void type56(LoginResult loginResult, ChannelHandlerContext ctx, SuitOperBean suitOperBean) {
        RoleData roleData = RolePool.getRoleData(loginResult.getRole_id());
        if (roleData == null) {
            return;
        }
        List<Goodstable> goods = SuitComposeAction.getGoods(suitOperBean.getGoods(), loginResult.getRole_id(), 0);
        if (goods == null) {
            return;
        }
        if (goods.size() < 2) {
            return;
        }
        Goodstable starCardEquip = (Goodstable)goods.get(0);
        if (starCardEquip.getType() != 520L) {
            return;
        }
        Goodstable materialGoods = (Goodstable)goods.get(1);
        if (materialGoods.getType() != 520L) {
            return;
        }
        if (starCardEquip.getRgid().compareTo(materialGoods.getRgid()) == 0) {
            return;
        }
        PartJade jade = suitOperBean.getJade();
        if (jade == null) {
            return;
        }
        int suitid = jade.getSuitid();
        int partId = jade.getPartId();
        if (suitid < 0 || suitid > 3 || partId < 0 || partId > 3) {
            return;
        }
        String[] value = starCardEquip.getValue().split("\\|");
        String[] splitOne = value[3].split("&");
        String[] splitTwo = materialGoods.getValue().split("\\|")[3].split("&");
        if (splitOne.length < 5 || splitTwo.length < 5) {
            return;
        }
        String[] splitSatrArrayOne = splitOne[4].split("=");
        String[] splitSatrArrayTwo = splitTwo[4].split("=");
        if (!isfiveElements(splitSatrArrayOne[1]) || !isfiveElements(splitSatrArrayTwo[1])) {
            return;
        }
        splitSatrArrayOne[4 + suitid] = splitSatrArrayTwo[4 + partId];
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < splitSatrArrayOne.length; ++i) {
            if (buffer.length() != 0) {
                buffer.append("=");
            }
            buffer.append(splitSatrArrayOne[i]);
        }
        splitOne[4] = buffer.toString();
        StringBuffer stringBuffer = new StringBuffer();
        for (int j = 0; j < splitOne.length; ++j) {
            if (stringBuffer.length() != 0) {
                stringBuffer.append("&");
            }
            stringBuffer.append(splitOne[j]);
        }
        value[3] = stringBuffer.toString();
        AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
        StringBuffer buffer2 = new StringBuffer();
        for (int k = 0; k < value.length; ++k) {
            if (buffer2.length() != 0) {
                buffer2.append("|");
            }
            buffer2.append(value[k]);
        }
        starCardEquip.setValue(buffer2.toString());
        assetUpdate.setGood(starCardEquip);
        materialGoods.setUsetime(Integer.valueOf((int)materialGoods.getUsetime() - 1));
        SuitComposeAction.saveGoods(goods, false);
        assetUpdate.setMsg("成功斗转星移");
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public static void type57(LoginResult loginResult, ChannelHandlerContext ctx, SuitOperBean suitOperBean) {
        RoleData roleData = RolePool.getRoleData(loginResult.getRole_id());
        if (roleData == null) {
            return;
        }
        List<Goodstable> goods = SuitComposeAction.getGoods(suitOperBean.getGoods(), loginResult.getRole_id(), 0);
        if (goods == null) {
            return;
        }
        if (goods.size() < 1) {
            return;
        }
        int num = 0;
        for (int i = 0; i < goods.size(); ++i) {
            Goodstable goodstable = (Goodstable)goods.get(i);
            if (goodstable.getType() != 520L) {
                return;
            }
            goodstable.goodxh(1);
            if ((int)goodstable.getUsetime() < 0) {
                return;
            }
            num += 20;
        }
        loginResult.setScore(DrawnitemsAction.Splice(loginResult.getScore(), "星芒=" + num, 2));
        AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
        SuitComposeAction.saveGoods(goods, false);
        assetUpdate.updata("星芒=" + num);
        assetUpdate.setGoods(goods);
        assetUpdate.setMsg("魂归成功");
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public static void type58(LoginResult loginResult, ChannelHandlerContext ctx, SuitOperBean suitOperBean) {
        RoleData roleData = RolePool.getRoleData(loginResult.getRole_id());
        if (roleData == null) {
            return;
        }
        List<Goodstable> goods = SuitComposeAction.getGoods(suitOperBean.getGoods(), loginResult.getRole_id(), 0);
        if (goods == null) {
            return;
        }
        if (goods.size() < 1) {
            return;
        }
        Goodstable goodstable = (Goodstable)goods.get(0);
        if (goodstable.getType() != 520L) {
            return;
        }
        String[] value = goodstable.getValue().split("\\|");
        String[] string = value[3].split("&");
        if (string.length < 4) {
            return;
        }
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < string.length - 1; ++i) {
            if (buffer.length() != 0) {
                buffer.append("&");
            }
            buffer.append(string[i]);
        }
        value[3] = buffer.toString();
        StringBuffer buffer2 = new StringBuffer();
        for (int j = 0; j < value.length; ++j) {
            if (buffer2.length() != 0) {
                buffer2.append("|");
            }
            buffer2.append(value[j]);
        }
        goodstable.setValue(buffer2.toString());
        AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
        assetUpdate.setGood(goodstable);
        SuitComposeAction.saveGoods(goods, false);
        assetUpdate.setMsg("删除成功");
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public static void type59(LoginResult loginResult, ChannelHandlerContext ctx, SuitOperBean suitOperBean) {
        RoleData roleData = RolePool.getRoleData(loginResult.getRole_id());
        if (roleData == null) {
            return;
        }
        List<Goodstable> goods = SuitComposeAction.getGoods(suitOperBean.getGoods(), loginResult.getRole_id(), 0);
        if (goods == null) {
            return;
        }
        if (goods.size() < 1) {
            return;
        }
        Goodstable goodstable = (Goodstable)goods.get(0);
        if (goodstable.getType() != 520L) {
            return;
        }
        PartJade jade = suitOperBean.getJade();
        if (jade == null) {
            return;
        }
        String[] value = goodstable.getValue().split("\\|");
        BigDecimal money = null;
        if (jade.getSuitid() == 0) {
            money = new BigDecimal(50000000);
            if (loginResult.getGold().compareTo(money) < 0) {
                return;
            }
            loginResult.setGold(loginResult.getGold().subtract(money));
            MonitorUtil.getMoney().useD(money.longValue());
        }
        else if (jade.getSuitid() == 1) {
            money = new BigDecimal(100);
            if (loginResult.getScoretype("星芒").compareTo(money) < 0) {
                return;
            }
            loginResult.setScore(DrawnitemsAction.Splice(loginResult.getScore(), "星芒=" + money.toString(), 3));
        }
        int parseInt = Integer.parseInt(value[2].split("=")[1]);
        parseInt += 1000;
        value[2] = "战力=" + parseInt;
        StringBuffer buffer2 = new StringBuffer();
        for (int i = 0; i < value.length; ++i) {
            if (buffer2.length() != 0) {
                buffer2.append("|");
            }
            buffer2.append(value[i]);
        }
        goodstable.setValue(buffer2.toString());
        AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
        if (jade.getSuitid() == 0) {
            assetUpdate.updata("D=-" + money);
        }
        else {
            assetUpdate.updata("星芒=-" + money.toString());
        }
        assetUpdate.setGood(goodstable);
        SuitComposeAction.saveGoods(goods, false);
        assetUpdate.setMsg("补充成功");
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public static BigDecimal calculateRefining(Alchemy alchemy, int aptitude, int lvlNow, boolean type) {
        try {
            BigDecimal sv = new BigDecimal(lvlNow);
            BigDecimal mv = new BigDecimal(alchemy.getAlchemymv());
            BigDecimal add;
            if (type) {
                add = mv.multiply(sv).multiply(new BigDecimal(aptitude)).divide(new BigDecimal(100), 10, 4).setScale(1, 1);
            }
            else {
                add = mv.multiply(sv).multiply(new BigDecimal(aptitude)).divide(new BigDecimal(100), 10, 4).multiply(new BigDecimal(14)).divide(new BigDecimal(15), 10, 4).setScale(1, 1);
            }
            return add;
        }
        catch (Exception NumberFormatException) {
            NumberFormatException.printStackTrace();
            return null;
        }
    }
    
    public static String anewRefining(int lvlNow, int aptitude, Alchemy alchemy1, Alchemy alchemy2, int type) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("炼化属性&资质=" + aptitude + "&");
        if (alchemy2.getAlchemykey().equals(alchemy1.getAlchemykey())) {
            BigDecimal add1 = calculateRefining(alchemy1, aptitude, lvlNow, true);
            buffer.append(alchemy1.getAlchemykey() + "=" + add1 + "&");
            BigDecimal add2 = calculateRefining(alchemy2, aptitude, lvlNow, false);
            buffer.append(alchemy2.getAlchemykey() + "=" + add2);
        }
        else {
            BigDecimal add1 = calculateRefining(alchemy1, aptitude, lvlNow, true);
            buffer.append(alchemy1.getAlchemykey() + "=" + add1 + "&");
            BigDecimal add2 = calculateRefining(alchemy2, aptitude, lvlNow, true);
            buffer.append(alchemy2.getAlchemykey() + "=" + add2);
        }
        if (type == 1 && Battlefield.isV(3.5)) {
            String starArrayName = getStarArrayName();
            buffer.append("&星阵属性=" + starArrayName);
        }
        return buffer.toString();
    }
    
    public static String getStarArrayName() {
        int nextInt = Battlefield.random.nextInt(9);
        if (nextInt == 0) {
            return "朱雀=火=忽视抗鬼火,抵抗灵宝伤害" + randomfourPlace();
        }
        if (nextInt == 1) {
            return "玄武=土=忽视抗遗忘,忽视抗仙法" + randomfourPlace();
        }
        if (nextInt == 2) {
            return "白虎=金=忽视抗封印,强毒伤害" + randomfourPlace();
        }
        if (nextInt == 3) {
            return "青龙=木=忽视抗混乱,强三尸虫" + randomfourPlace();
        }
        if (nextInt == 4) {
            return "火猿=火";
        }
        if (nextInt == 5) {
            return "黄鹤=水";
        }
        if (nextInt == 6) {
            return "赤马=金";
        }
        if (nextInt == 7) {
            return "苍狼=木";
        }
        return "金牛=土";
    }
    
    public static String randomfourPlace() {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < 4; ++i) {
            buffer.append("=" + GameServer.randomStarPalace());
        }
        return buffer.toString();
    }
    
    public static String anewFiveElements() {
        int num = Battlefield.random.nextInt(5);
        ArrayList<String> list = new ArrayList<>();
        list.add("金");
        list.add("木");
        list.add("水");
        list.add("火");
        list.add("土");
        for (int i = 0; i < num; ++i) {
            list.remove(SuitComposeAction.random.nextInt(list.size()));
        }
        StringBuffer buffer = new StringBuffer();
        buffer.append("五行属性");
        for (int j = 0; j < list.size(); ++j) {
            int aptitude = Battlefield.random.nextInt(100) + 1;
            buffer.append("&" + (String)list.get(j) + "=" + aptitude);
        }
        return buffer.toString();
    }
    
    public static String upgradeAttribute(String[] value, int lvlNow) {
        String[] split = value[3].split("&");
        if (!split[0].equals("炼化属性")) {
            return null;
        }
        Alchemy alchemy1 = NpcComposeAction.getAlchemy("星卡", split[2].split("=")[0]);
        Alchemy alchemy2 = NpcComposeAction.getAlchemy("星卡", split[3].split("=")[0]);
        String anewRefining = anewRefining(lvlNow, Integer.parseInt(split[1].split("=")[1]), alchemy1, alchemy2, 0);
        StringBuffer buffer = new StringBuffer();
        buffer.append(anewRefining);
        if (split[split.length - 1].startsWith("星阵属性")) {
            buffer.append("&" + split[split.length - 1]);
        }
        return buffer.toString();
    }
    /** 判断是否是四神兽 */
    public static boolean isfiveElements(String value) {
        return value.equals("朱雀") || value.equals("玄武") || value.equals("白虎") || value.equals("青龙");
    }
    /** 升级更换名称皮肤 */
    public static void changeStarCardSkin(Goodstable goodstable, int level) {
        if (level == 1) {
            goodstable.setGoodsname("地劣星");
            goodstable.setSkin("xk_01");
        }
        else if (level == 2) {
            goodstable.setGoodsname("地恶星");
            goodstable.setSkin("xk_02");
        }
        else if (level == 3) {
            goodstable.setGoodsname("地囚星");
            goodstable.setSkin("xk_03");
        }
        else if (level == 4) {
            goodstable.setGoodsname("地魔星");
            goodstable.setSkin("xk_04");
        }
        else if (level == 5) {
            goodstable.setGoodsname("地周星");
            goodstable.setSkin("xk_05");
        }
        else if (level == 6) {
            goodstable.setGoodsname("地猖星");
            goodstable.setSkin("xk_06");
        }
        else if (level == 7) {
            goodstable.setGoodsname("地轴星");
            goodstable.setSkin("xk_07");
        }
        else if (level == 8) {
            goodstable.setGoodsname("地威星");
            goodstable.setSkin("xk_08");
        }
        else if (level == 9) {
            goodstable.setGoodsname("地壮星");
            goodstable.setSkin("xk_09");
        }
        else if (level == 10) {
            goodstable.setGoodsname("地藏星");
            goodstable.setSkin("xk_10");
        }
        else if (level == 11) {
            goodstable.setGoodsname("地速星");
            goodstable.setSkin("xk_11");
        }
        else if (level == 12) {
            goodstable.setGoodsname("地走星");
            goodstable.setSkin("xk_12");
        }
        else if (level == 13) {
            goodstable.setGoodsname("地暗星");
            goodstable.setSkin("xk_13");
        }
        else if (level == 14) {
            goodstable.setGoodsname("地魁星");
            goodstable.setSkin("xk_14");
        }
    }
}
