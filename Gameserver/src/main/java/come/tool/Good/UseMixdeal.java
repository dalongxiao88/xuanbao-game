package come.tool.Good;

import java.math.BigDecimal;
import org.come.entity.RoleSummoning;
import come.tool.Role.RoleData;
import org.come.handler.SendMessage;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import come.tool.Stall.AssetUpdate;
import org.come.bean.UseCardBean;
import come.tool.Role.RolePool;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.entity.Goodstable;

public class UseMixdeal
{
    public static void TimingGood(Goodstable good, ChannelHandlerContext ctx, LoginResult login) {
        RoleData roleData = RolePool.getRoleData(login.getRole_id());
        String goodname = "";
        if (good.getType() == 493L) {
            goodname = "六脉化神丸";
        }
        else if (good.getType() == 492L) {
            goodname = "玉枢返虚丸";
        }
        UseCardBean limit = roleData.getLimit(goodname);
        if (limit == null) {
            limit = new UseCardBean(goodname, goodname, good.getSkin(), (long)Integer.parseInt(good.getValue()) * 60000L + System.currentTimeMillis(), null);
            roleData.addLimit(limit);
        }
        else {
            limit.setTime(limit.getTime() + (long)(Integer.parseInt(good.getValue()) * 60000));
        }
        AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
        assetUpdate.updata("G" + good.getRgid() + "=" + good.getUsetime());
        assetUpdate.setUseCard(limit);
        assetUpdate.setMsg("你使用了" + good.getGoodsname());
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public static void BlueBack(Goodstable good, ChannelHandlerContext ctx, LoginResult login) {
        RoleData roleData = RolePool.getRoleData(login.getRole_id());
        UseCardBean limit = roleData.getLimit("回蓝符");
        if (limit == null) {
            limit = new UseCardBean(good.getGoodsname(), "回蓝符", good.getSkin(), 21600000L + System.currentTimeMillis(), good.getValue());
            roleData.addLimit(limit);
        }
        else if (!limit.getName().equals(good.getGoodsname())) {
            limit.setName(good.getGoodsname());
            limit.setSkin(good.getSkin());
            limit.setValue(good.getValue());
            limit.setTime(21600000L + System.currentTimeMillis());
        }
        else {
            limit.setTime(limit.getTime() + 21600000L);
        }
        AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
        assetUpdate.updata("G" + good.getRgid() + "=" + good.getUsetime());
        assetUpdate.setUseCard(limit);
        assetUpdate.setMsg("你使用了" + good.getGoodsname());
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public static void divineWalk(Goodstable good, ChannelHandlerContext ctx, LoginResult login) {
        long time = 3600000L;
        RoleData roleData = RolePool.getRoleData(login.getRole_id());
        UseCardBean limit = roleData.getLimit("sxf");
        if (limit == null) {
            limit = new UseCardBean("神行符", "sxf", "fz06", System.currentTimeMillis() + time, "能增加行走速度,持续效果60分钟。");
            roleData.addLimit(limit);
        }
        else if (!limit.getName().equals(good.getGoodsname())) {
            limit.setName(good.getGoodsname());
            limit.setSkin(good.getSkin());
            limit.setValue(good.getValue());
            limit.setTime(time + System.currentTimeMillis());
        }
        else {
            limit.setTime(limit.getTime() + time);
        }
        AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
        assetUpdate.updata("G" + good.getRgid() + "=" + good.getUsetime());
        assetUpdate.setUseCard(limit);
        assetUpdate.setMsg("你的身体瞬间感到了轻盈！");
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
        login.getRoleShow().setDivineRune(Boolean.valueOf(true));
        SendMessage.sendMessageToMapRoles(login.getMapid(), Agreement.getAgreement().upRoleShowAgreement(GsonUtil.getGsonUtil().getgson().toJson(login.getRoleShow())));
    }
    
    public static void baseCard(Goodstable good, ChannelHandlerContext ctx, LoginResult login) {
        RoleData roleData = RolePool.getRoleData(login.getRole_id());
        String type = null;
        long time = 0L;
        if (good.getGoodsname().indexOf("返老还童丹") != -1) {
            type = "童卡";
            time = 3600000L;
        }
        else if (good.getGoodsname().indexOf("变身卡") != -1 || good.getGoodsname().indexOf("属性卡") != -1) {
            type = "变身卡";
            time = 3600000L;
        }
        else if (good.getInstruction().indexOf("强法型") != -1) {
            type = "强法型";
            time = 21600000L;
        }
        else if (good.getInstruction().indexOf("加抗型") != -1) {
            type = "加抗型";
            time = 21600000L;
        }
        else {
            type = "增益型";
            time = 21600000L;
        }
        UseCardBean limit = roleData.getLimit(type);
        if (limit == null) {
            limit = new UseCardBean(good.getGoodsname(), type, good.getSkin(), time + System.currentTimeMillis(), good.getValue());
            roleData.addLimit(limit);
        }
        else if (limit.getName().equals(good.getGoodsname()) && limit.getValue().equals(good.getValue())) {
            limit.setTime(limit.getTime() + time);
        }
        else {
            limit.setName(good.getGoodsname());
            limit.setSkin(good.getSkin());
            limit.setValue(good.getValue());
            limit.setTime(time + System.currentTimeMillis());
        }
        AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
        assetUpdate.updata("G" + good.getRgid() + "=" + good.getUsetime());
        assetUpdate.setUseCard(limit);
        assetUpdate.setMsg("你使用了" + good.getGoodsname());
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public static void qtCard(Goodstable good, ChannelHandlerContext ctx, LoginResult login) {
        RoleData roleData = RolePool.getRoleData(login.getRole_id());
        if (good.getType() == 113L) {
            AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
            assetUpdate.updata("G" + good.getRgid() + "=" + good.getUsetime());
            UseCardBean limit = roleData.removeLimit("童卡");
            if (limit == null) {
                roleData.removeLimit("变身卡");
            }
            if (limit != null) {
                assetUpdate.setUseCard(limit);
            }
            assetUpdate.setMsg("你使用了" + good.getGoodsname());
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
        }
        else if (good.getType() == 111L) {
            AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
            assetUpdate.updata("G" + good.getRgid() + "=" + good.getUsetime());
            UseCardBean limit = roleData.removeLimit("摄妖香");
            if (limit != null) {
                limit.setTime(-1L);
                assetUpdate.setUseCard(limit);
            }
            assetUpdate.setMsg("你使用了" + good.getGoodsname());
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
        }
        else {
            String type = null;
            long time = 0L;
            if (good.getType() == 88L) {
                type = "摄妖香";
                time = 21600000L;
            }
            else {
                type = "杀人香";
                time = 3600000L;
            }
            UseCardBean limit2 = roleData.getLimit(type);
            if (limit2 == null) {
                limit2 = new UseCardBean(type, type, good.getSkin(), time + System.currentTimeMillis(), null);
                roleData.addLimit(limit2);
            }
            else {
                limit2.setTime(limit2.getTime() + time);
            }
            AssetUpdate assetUpdate2 = new AssetUpdate(AssetUpdate.USEGOOD);
            assetUpdate2.updata("G" + good.getRgid() + "=" + good.getUsetime());
            assetUpdate2.setUseCard(limit2);
            assetUpdate2.setMsg("你使用了" + good.getGoodsname());
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate2)));
        }
    }
    
    public static void gld(Goodstable good, RoleSummoning pet, ChannelHandlerContext ctx, LoginResult login) {
        RoleData roleData = RolePool.getRoleData(login.getRole_id());
        UseCardBean limit = roleData.getLimit("枯荣丹");
        if (limit == null) {
            limit = new UseCardBean(good.getGoodsname(), "枯荣丹", good.getSkin(), 10800000L + System.currentTimeMillis(), pet.getSid().toString());
            roleData.addLimit(limit);
        }
        else if (limit.getValue().equals(pet.getSid().toString())) {
            limit.setTime(limit.getTime() + 10800000L);
        }
        else {
            limit.setName(good.getGoodsname());
            limit.setSkin(good.getSkin());
            limit.setValue(pet.getSid().toString());
            limit.setTime(10800000L + System.currentTimeMillis());
        }
        AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
        assetUpdate.updata("G" + good.getRgid() + "=" + good.getUsetime());
        assetUpdate.setUseCard(limit);
        assetUpdate.setMsg("你使用了" + good.getGoodsname());
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public static void vipSss(Goodstable good, ChannelHandlerContext ctx, LoginResult login) {
        long time = 2592000000L;
        if (good.getType() == 66669L) {
            time = 172800000L;
        }
        RoleData roleData = RolePool.getRoleData(login.getRole_id());
        UseCardBean limit = roleData.getLimit("VIP");
        good.setGoodsid(new BigDecimal(92373));
        good.setGoodsname("月卡VIP");
        good.setType(66668L);
        if (limit == null) {
            limit = new UseCardBean("月卡", "VIP", "1", System.currentTimeMillis() + time, good.getValue());
            roleData.addLimit(limit);
        }
        else {
            limit.setTime(limit.getTime() + time);
        }
        AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
        assetUpdate.updata("G" + good.getRgid() + "=" + good.getUsetime());
        assetUpdate.setUseCard(limit);
        assetUpdate.setMsg("激活了" + time / 1000L / 60L / 60L / 24L + "天月卡特权");
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    public static void vipJjj(Goodstable good, ChannelHandlerContext ctx, LoginResult login) {
        long time = 2592000000L*3;
        if (good.getType() == 66779L) {
            time = 172800000L;
        }
        RoleData roleData = RolePool.getRoleData(login.getRole_id());
        UseCardBean limit = roleData.getLimit("JVIP");
        good.setGoodsid(new BigDecimal(92374));
        good.setGoodsname("季卡VIP");
        good.setType(66778L);
        if (limit == null) {
            limit = new UseCardBean("季卡", "JVIP", "2", System.currentTimeMillis() + time, good.getValue());
            roleData.addLimit(limit);
        }
        else {
            limit.setTime(limit.getTime() + time);
        }
        AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
        assetUpdate.updata("G" + good.getRgid() + "=" + good.getUsetime());
        assetUpdate.setUseCard(limit);
        assetUpdate.setMsg("激活了" + time / 1000L / 60L / 60L / 24L + "天季卡特权");
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    public static void Medicine(Goodstable good, ChannelHandlerContext ctx, LoginResult login) {
        long i = Long.parseLong(good.getValue());
        long time = 86400000L;
        RoleData roleData = RolePool.getRoleData(login.getRole_id());
        String goodname = "";
        if (good.getType() == 921L) {
            goodname = "超级六脉化神丸_月";
        }
        else if (good.getType() == 922L) {
            goodname = "超级玉枢返虚丸_月";
        }
        UseCardBean limit = roleData.getLimit(goodname);
        if (limit == null) {
            limit = new UseCardBean(goodname, goodname, good.getSkin(), (long)Integer.parseInt(good.getValue()) * time + System.currentTimeMillis(), null);
            roleData.addLimit(limit);
        }
        else {
            limit.setTime(limit.getTime() + (long)Integer.parseInt(good.getValue()) * time);
        }
        AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
        assetUpdate.updata("G" + good.getRgid() + "=" + good.getUsetime());
        assetUpdate.setUseCard(limit);
        assetUpdate.setMsg("你使用了" + good.getGoodsname());
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public static void Potion(Goodstable good, ChannelHandlerContext ctx, LoginResult login) {
        RoleData data = RolePool.getRoleData(login.getRole_id());
        UseCardBean limit = data.getLimit("经验");
        if (limit == null) {
            limit = new UseCardBean("双倍经验", "经验", "shuang", 3600000L + System.currentTimeMillis(), null);
            data.addLimit(limit);
        }
        else {
            limit.setTime(limit.getTime() + 3600000L);
        }
        AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
        assetUpdate.updata("G" + good.getRgid() + "=" + good.getUsetime());
        assetUpdate.setUseCard(limit);
        assetUpdate.setMsg("你使用了" + good.getGoodsname());
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
}
