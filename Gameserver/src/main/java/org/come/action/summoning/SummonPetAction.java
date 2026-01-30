package org.come.action.summoning;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.come.tool.WriteOut;
import org.come.model.GoodsExchange;
import java.util.List;
import org.come.model.PetExchange;
import org.come.bean.NChatBean;
import org.come.action.monitor.MonitorUtil;
import come.tool.Stall.AssetUpdate;
import org.come.entity.Goodstable;
import java.util.ArrayList;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.entity.RoleSummoning;
import org.come.entity.Mount;
import org.come.until.AchievemUtil;
import org.come.until.AllServiceUtil;
import java.math.BigDecimal;
import org.come.bean.SummonGoodsBean;
import org.come.until.GsonUtil;
import org.come.bean.SummonPetBean;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import java.util.Random;
import org.come.action.IAction;

public class SummonPetAction implements IAction
{
    public static Random random;
    public static String[] kxs;
    
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (loginResult == null) {
            return;
        }
        SummonPetBean petBean = (SummonPetBean)GsonUtil.getGsonUtil().getgson().fromJson(message, SummonPetBean.class);
        SummonGoodsBean goodsBean = (SummonGoodsBean)GsonUtil.getGsonUtil().getgson().fromJson(message, SummonGoodsBean.class);
        if (petBean.getOpertype() == 0) {
            this.addPet(petBean, ctx);
        }
        else if (petBean.getOpertype() == 1) {
            this.jll(petBean, ctx);
        }
        else if (petBean.getOpertype() == 2) {
            this.exchange(petBean, ctx);
        }
        else if (goodsBean.getOpertype() == 3) {
            this.goodschange(goodsBean, ctx);
        }
    }
    
    public static boolean validateMount(ChannelHandlerContext ctx, BigDecimal mountId) {
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (loginResult == null) {
            return false;
        }
        BigDecimal roleId = loginResult.getRole_id();
        Mount mount = AllServiceUtil.getMountService().selectMountsByMID(mountId);
        return mount != null && mount.getRoleid().compareTo(roleId) == 0;
    }
    
    public static boolean validateSummoning(ChannelHandlerContext ctx, BigDecimal summoningId) {
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (loginResult == null) {
            return false;
        }
        BigDecimal roleId = loginResult.getRole_id();
        RoleSummoning roleSummoning = AllServiceUtil.getRoleSummoningService().selectRoleSummoningsByRgID(summoningId);
        return roleSummoning != null && roleSummoning.getRoleid().compareTo(roleId) == 0;
    }
    
    public void exchange(SummonPetBean petBean, ChannelHandlerContext ctx) {
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (loginResult == null) {
            return;
        }
        PetExchange exchange = GameServer.getPetExchange(petBean.getPetid().intValue());
        String pids = exchange.getPid();
        String[] ids = pids.split("\\|");
        BigDecimal newPid = BigDecimal.ZERO;
        if (ids.length > 1) {
            int idx = SummonPetAction.random.nextInt(ids.length);
            newPid = new BigDecimal(ids[idx]);
        }
        else {
            newPid = new BigDecimal(ids[0]);
        }
        RoleSummoning roleSummoning;
        RoleSummoning pet = roleSummoning = ((exchange != null) ? GameServer.getPet(newPid) : null);
        if (pet == null) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("错误兑换公式"));
            return;
        }
        ArrayList<Goodstable> list = new ArrayList<>();
        long money = 0L;
        String goodName = null;
        String[] v = exchange.getConsume().split("\\|");
        for (int i = 0; i < v.length; ++i) {
            if (v[i].startsWith("D")) {
                money = Long.parseLong(v[i].substring(2));
                if (loginResult.getGold().longValue() < money) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("金钱不够" + money));
                    return;
                }
            }
            else if (v[i].startsWith("G")) {
                String[] vs = v[i].split("=");
                BigDecimal goodid = new BigDecimal(vs[1]);
                int sum = Integer.parseInt(vs[2]);
                Goodstable goodstable = (Goodstable)GameServer.getAllGoodsMap().get(goodid);
                if (goodstable == null) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("错误兑换公式"));
                    return;
                }
                List<Goodstable> goods = AllServiceUtil.getGoodsTableService().selectGoodsByRoleIDAndGoodsID(loginResult.getRole_id(), goodid);
                int SYsum = sum;
                int k = 0;
                while (k < goods.size()) {
                    Goodstable good = (Goodstable)goods.get(k);
                    if (goodName == null) {
                        goodName = good.getGoodsname();
                    }
                    if ((int)good.getUsetime() >= SYsum) {
                        good.setUsetime(Integer.valueOf((int)good.getUsetime() - SYsum));
                        SYsum = 0;
                        list.add(good);
                        break;
                    }
                    else {
                        SYsum -= (int)good.getUsetime();
                        good.setUsetime(Integer.valueOf(0));
                        list.add(good);
                        ++k;
                    }
                }
                if (SYsum > 0) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("不够" + sum + "个" + goodstable.getGoodsname()));
                    return;
                }
            }
        }
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setType(AssetUpdate.USEGOOD);
        if (money != 0L) {
            loginResult.setGold(loginResult.getGold().add(new BigDecimal(-money)));
            MonitorUtil.getMoney().useD(money);
        }
        assetUpdate.updata("D=-" + money);
        if (list.size() != 0) {
            for (int j = 0; j < list.size(); ++j) {
                Goodstable good2 = (Goodstable)list.get(j);
                AllServiceUtil.getGoodsTableService().updateGoodRedis(good2);
                assetUpdate.updata("G" + good2.getRgid() + "=" + good2.getUsetime());
            }
        }
        this.initPet(pet);
        pet.setRoleid(loginResult.getRole_id());
        AllServiceUtil.getRoleSummoningService().insertRoleSummoning(pet);
        assetUpdate.setPet(pet);
        assetUpdate.setMsg("成功兑换#R" + pet.getSummoningname());
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
        StringBuffer buffer = new StringBuffer();
        buffer.append("#Y天空一声巨响,#G");
        buffer.append(loginResult.getRolename());
        buffer.append("#Y终于集齐#G");
        buffer.append(goodName);
        buffer.append("#Y,获得一只#G");
        buffer.append(pet.getSummoningname());
        buffer.append("#Y赶紧将此召唤兽收于麾下！");
        NChatBean bean = new NChatBean();
        bean.setId(4);
        bean.setMessage(buffer.toString());
        AchievemUtil.detectionAchievem(loginResult, "神兽");
        String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
        SendMessage.sendMessageToAllRoles(msg);
    }
    
    public void goodschange(SummonGoodsBean goodsBean, ChannelHandlerContext ctx) {
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (loginResult == null) {
            return;
        }
        GoodsExchange exchange = GameServer.getGoodsExchange(goodsBean.getGoodsid().intValue());
        Goodstable goodstable;
        Goodstable goodss = goodstable = ((exchange != null) ? GameServer.getGood(exchange.getGid()) : null);
        if (goodss == null) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("错误兑换公式"));
            return;
        }
        ArrayList<Goodstable> list = new ArrayList<>();
        long money = 0L;
        String goodName = null;
        String[] v = exchange.getConsume().split("\\|");
        for (int i = 0; i < v.length; ++i) {
            if (v[i].startsWith("D")) {
                money = Long.parseLong(v[i].substring(2));
                if (loginResult.getGold().longValue() < money) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("金钱不够" + money));
                    return;
                }
            }
            else if (v[i].startsWith("G")) {
                String[] vs = v[i].split("=");
                BigDecimal goodid = new BigDecimal(vs[1]);
                int sum = Integer.parseInt(vs[2]);
                Goodstable goodstable2 = (Goodstable)GameServer.getAllGoodsMap().get(goodid);
                if (goodstable2 == null) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("错误兑换公式"));
                    return;
                }
                List<Goodstable> goods = AllServiceUtil.getGoodsTableService().selectGoodsByRoleIDAndGoodsID(loginResult.getRole_id(), goodid);
                int SYsum = sum;
                int k = 0;
                while (k < goods.size()) {
                    Goodstable good = (Goodstable)goods.get(k);
                    if (goodName == null) {
                        goodName = good.getGoodsname();
                    }
                    if ((int)good.getUsetime() >= SYsum) {
                        good.setUsetime(Integer.valueOf((int)good.getUsetime() - SYsum));
                        SYsum = 0;
                        list.add(good);
                        break;
                    }
                    else {
                        SYsum -= (int)good.getUsetime();
                        good.setUsetime(Integer.valueOf(0));
                        list.add(good);
                        ++k;
                    }
                }
                if (SYsum > 0) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("不够" + sum + "个" + goodstable2.getGoodsname()));
                    return;
                }
            }
        }
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setType(AssetUpdate.USEGOOD);
        if (money != 0L) {
            loginResult.setGold(loginResult.getGold().add(new BigDecimal(-money)));
            MonitorUtil.getMoney().useD(money);
        }
        assetUpdate.updata("D=-" + money);
        if (list.size() != 0) {
            for (int j = 0; j < list.size(); ++j) {
                Goodstable good2 = (Goodstable)list.get(j);
                AllServiceUtil.getGoodsTableService().updateGoodRedis(good2);
                assetUpdate.updata("G" + good2.getRgid() + "=" + good2.getUsetime());
            }
        }
        goodss.setGoodsid(loginResult.getRole_id());
        AllServiceUtil.getGoodsTableService().updateGoodRedis(goodss);
        assetUpdate.setGood(goodss);
        assetUpdate.setMsg("成功兑换#R" + goodss.getGoodsname());
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
        StringBuffer buffer = new StringBuffer();
        buffer.append("#Y天空一声巨响,#G");
        buffer.append(loginResult.getRolename());
        buffer.append("#Y终于集齐#G");
        buffer.append(goodName);
        buffer.append("#Y,获得一只#G");
        buffer.append(goodss.getGoodsname());
        buffer.append("#Y赶紧将此召唤兽收于麾下！");
        NChatBean bean = new NChatBean();
        bean.setId(4);
        bean.setMessage(buffer.toString());
        String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
        SendMessage.sendMessageToAllRoles(msg);
    }
    
    public void jll(SummonPetBean petBean, ChannelHandlerContext ctx) {
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (loginResult == null) {
            return;
        }
        RoleSummoning pet = AllServiceUtil.getRoleSummoningService().selectRoleSummoningsByRgID(petBean.getPetid());
        if (pet == null || pet.getTurnRount() != 0) {
            return;
        }
        if (pet.getRoleid().compareTo(loginResult.getRole_id()) != 0) {
            return;
        }
        if (pet.getSsn() != null && !pet.getSsn().equals("0")) {
            return;
        }
        RoleSummoning pet2 = (RoleSummoning)GameServer.getAllPet().get(new BigDecimal(pet.getSummoningid()));
        if (pet2 == null) {
            return;
        }
        Goodstable good = AllServiceUtil.getGoodsTableService().getGoodsByRgID(petBean.getGoodid());
        if (good == null) {
            return;
        }
        if (good.getRole_id().compareTo(loginResult.getRole_id()) != 0) {
            return;
        }
        if ((int)good.getUsetime() <= 0 || good.getType() != 701L) {
            return;
        }
        String msg = null;
        pet.setHp(Integer.parseInt(pet2.getHp() + ""));
        pet.setMp(pet2.getMp());
        pet.setAp(pet2.getAp());
        pet.setSp(pet2.getSp());
        if (good.getValue().equals("1")) {
            pet.setGrowlevel(getgroup(pet2.getGrowlevel()));
            double vOne = Double.parseDouble(pet.getGrowlevel());
            double vTwo = Double.parseDouble(pet2.getGrowlevel());
            int v1 = (int)(vOne * 1000.0);
            int v2 = (int)(vTwo * 1000.0);
            BigDecimal sx = new BigDecimal((double)(v1 += (int)((double)v2 * 0.05)) / 1000.0);
            sx = sx.setScale(3, 4);
            pet.setGrowlevel(sx.toString());
            msg = ((sx.doubleValue() > vTwo) ? "你的召唤兽体内一丝金光闪现" : "你的召唤兽发生了变化");
        }
        else {
            pet.setGrowlevel(getgroup(pet2.getGrowlevel()));
            msg = "你的召唤兽发生了变化";
        }
        pet.setLyk("");
        pet.setGlyk("");
        pet.setGrade(Integer.valueOf(0));
        pet.setTurnRount(0);
        pet.setExp(new BigDecimal(0));
        pet.setBone(Integer.valueOf(0));
        pet.setSpir(Integer.valueOf(0));
        pet.setPower(Integer.valueOf(0));
        pet.setSpeed(Integer.valueOf(0));
        pet.setCalm(Integer.valueOf(0));
        pet.setDragon(0);
        pet.setSpdragon(0);
        pet.setAlchemynum(0);
        pet.setGAlchemynum(0);
        pet.setBasishp(pet.getHp());
        pet.setBasismp(pet.getMp());
        good.goodxh(1);
        AllServiceUtil.getGoodsTableService().updateGoodRedis(good);
        AllServiceUtil.getRoleSummoningService().updatePetRedis(pet);
        AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
        assetUpdate.updata("G" + good.getRgid() + "=" + good.getUsetime());
        assetUpdate.setPet(pet);
        assetUpdate.setMsg(msg);
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public void addPet(SummonPetBean petBean, ChannelHandlerContext ctx) {
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (loginResult == null) {
            return;
        }
        if (petBean.getPetid().longValue() < 200000L || petBean.getPetid().longValue() > 200091L) {
            WriteOut.addtxt(loginResult.getRole_id() + ":非法添加召唤兽:" + petBean.getPetid(), 9999L);
            return;
        }
        RoleSummoning pet = GameServer.getPet(petBean.getPetid());
        this.initPet(pet);
        pet.setRoleid(loginResult.getRole_id());
        AllServiceUtil.getRoleSummoningService().insertRoleSummoning(pet);
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setType(AssetUpdate.USERGOOD);
        assetUpdate.setPet(pet);
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
        AchievemUtil.detectionAchievem(loginResult, "捕捉获得一只召唤兽");
    }
    
    public void initPet(RoleSummoning pet) {
        pet.setBasishp(pet.getHp());
        pet.setBasismp(pet.getMp());
        pet.setFaithful(100);
        pet.setGrade(0);
        pet.setTurnRount(0);
        pet.setBone(0);
        pet.setSpir(0);
        pet.setPower(0);
        pet.setSpeed(0);
        pet.setCalm(0);
        pet.setDragon(0);
        pet.setSpdragon(0);
        pet.setAlchemynum(0);
        pet.setGAlchemynum(0);
        pet.setExp(new BigDecimal(0));
        pet.setOpenSeal(1);
        pet.setOpenql(0);
        if (pet.getQuality().equals("1")) {
            Long c = new Date().getTime();
            c = c + Long.parseLong(Integer.parseInt(pet.getSurplusTimes()) * 60 + "000");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date(c);
            String res = simpleDateFormat.format(date);
            pet.setSurplusTimes(res);
        }
        String yb = pet.getResistance();
        if (yb == null || yb.equals("")) {
            int p;
            int p2;
            for (p = SummonPetAction.random.nextInt(SummonPetAction.kxs.length), p2 = SummonPetAction.random.nextInt(SummonPetAction.kxs.length); p2 == p; p2 = SummonPetAction.random.nextInt(SummonPetAction.kxs.length)) {}
            pet.setResistance(SummonPetAction.kxs[p] + "|" + SummonPetAction.kxs[p2]);
        }
    }
    
    public static int getchu(int v) {
        int f = (v >> 2) + 1;
        if (f <= 0) {
            return v;
        }
        return v - SummonPetAction.random.nextInt(f);
    }
    
    public static String getgroup(String group) {
        try {
            double v = Double.parseDouble(group);
            int v2 = (int)(v * 1000.0);
            BigDecimal sx = new BigDecimal((double)getchu(v2) / 1000.0);
            sx = sx.setScale(3, 4);
            return sx.toString();
        }
        catch (Exception e) {
            e.printStackTrace();
            return group;
        }
    }
    
    static {
        SummonPetAction.random = new Random();
        SummonPetAction.kxs = new String[] { "抗混乱=30", "抗封印=30", "抗昏睡=30", "抗中毒=30", "物理吸收率=30", "抗风=30", "抗火=30", "抗水=30", "抗雷=30", "抗鬼火=30", "抗遗忘=30" };
    }
}
