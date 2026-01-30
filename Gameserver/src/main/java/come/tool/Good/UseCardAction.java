package come.tool.Good;

import org.come.model.aCard;
import org.come.action.monitor.MonitorUtil;
import java.math.BigDecimal;
import come.tool.Stall.AssetUpdate;
import org.come.bean.UseCardBean;
import come.tool.Role.RoleData;
import org.come.handler.SendMessage;
import org.come.until.AchievemUtil;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import come.tool.Role.RolePool;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class UseCardAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (loginResult == null) {
            return;
        }
        RoleData roleData = RolePool.getRoleData(loginResult.getRole_id());
        if (message.startsWith("R")) {
            this.ReceiveDBExp(ctx, message.substring(1), loginResult, roleData);
        }
        else if (message.startsWith("S")) {
            this.SuspendDBExp(ctx, loginResult, roleData);
        }
        else if (message.startsWith("Q")) {
            this.QueryDBExp(ctx, loginResult, roleData);
        }
        else if (message.startsWith("T")) {
            String[] vs = message.split("\\|");
            vs[0] = vs[0].substring(1);
            for (int i = 0; i < vs.length; ++i) {
                roleData.removeLimit(vs[i]);
                if (vs[0].equals("sxf")) {
                    loginResult.setDivineRune(Boolean.valueOf(false));
                    loginResult.getRoleShow().setDivineRune(Boolean.valueOf(false));
                    SendMessage.sendMessageToMapRoles(loginResult.getMapid(), Agreement.getAgreement().upRoleShowAgreement(GsonUtil.getGsonUtil().getgson().toJson(loginResult.getRoleShow())));
                }
            }
        }
        else if (message.startsWith("YUE")) {
            YKbuy(ctx, loginResult, roleData);
        }
        else if (message.startsWith("JIII")) {
            JKbuy(ctx, loginResult, roleData);
        }
        else {
            this.UCard(ctx, message, loginResult, roleData);
        }
    }
    
    public void ReceiveDBExp(ChannelHandlerContext ctx, String message, LoginResult login, RoleData data) {
        int dbexp = (int)data.getPrivateData().getDBExp();
        int time = Integer.parseInt(message);
        int exp = 21600 - dbexp;
        if (exp < time) {
            time = exp;
        }
        if (exp == 0) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你双倍时间已经用完了,明天请早"));
            return;
        }
        data.getPrivateData().setDBExp(Integer.valueOf(dbexp + time));
        UseCardBean limit = data.getLimit("经验");
        if (limit == null) {
            limit = new UseCardBean("双倍经验", "经验", "shuang", (long)(time * 1000) + System.currentTimeMillis(), null);
            data.addLimit(limit);
        }
        else {
            limit.setTime(limit.getTime() + (long)(time * 1000));
        }
        AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
        assetUpdate.setUseCard(limit);
        assetUpdate.setMsg("#W你领取了#R " + time / 60 + " #W分钟的双倍时间");
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public void SuspendDBExp(ChannelHandlerContext ctx, LoginResult login, RoleData data) {
        UseCardBean limit = data.removeLimit("经验");
        if (limit == null) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你没有双倍时间"));
            return;
        }
        int surplus = fenzhong(limit.getTime());
        data.getPrivateData().setDBExp(Integer.valueOf((int)data.getPrivateData().getDBExp() - surplus * 60));
        AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
        limit.setTime(-1L);
        assetUpdate.setUseCard(limit);
        assetUpdate.setMsg("你冻结了#G " + surplus + " #Y分钟的双倍时间");
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public void QueryDBExp(ChannelHandlerContext ctx, LoginResult login, RoleData data) {
        int dbexp = (int)data.getPrivateData().getDBExp();
        int exp = (21600 - dbexp) / 60;
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你剩余#G " + exp + " #Y分钟的双倍时间"));
    }
    
    public static int fenzhong(long time) {
        return (int)((time - System.currentTimeMillis()) / 60000L);
    }
    
    public void UCard(ChannelHandlerContext ctx, String message, LoginResult loginResult, RoleData roleData) {
        String[] vs = message.split("\\|");
        if (vs[0].equals("0") || vs[0].equals("1")) {
            boolean is = vs[0].equals("0");
            int id = Integer.parseInt(vs[1]);
            aCard card = GameServer.getCard(id);
            if (card == null) {
                return;
            }
            AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
            if (card.getType() == 0) {
                if (loginResult.getGold().longValue() < (long)card.getMoney()) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("金钱不足"));
                    return;
                }
                loginResult.setGold(new BigDecimal(loginResult.getGold().longValue() - (long)card.getMoney()));
                assetUpdate.updata("D=-" + card.getMoney());
                MonitorUtil.getMoney().useD((long)card.getMoney());
            }
            else if (card.getType() == 1) {
                if (loginResult.getCodecard().longValue() < (long)card.getMoney()) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("仙玉不足"));
                    return;
                }
                loginResult.setCodecard(new BigDecimal(loginResult.getCodecard().longValue() - (long)card.getMoney()));
                MonitorUtil.getMoney().useX((long)card.getMoney());
                assetUpdate.updata("X=-" + card.getMoney());
            }
            MonitorUtil.addCard(card.getType(), card.getMoney());
            String value = is ? ("皮肤=" + card.getZskin() + "|亲和力=" + card.getQhl() + "|种族=" + card.getZz() + "|" + card.getValue()) : ("皮肤不变=" + card.getZskin() + "|亲和力=" + card.getQhl() + "|种族=" + card.getZz() + "|" + card.getValue());
            UseCardBean limit = roleData.getLimit("变身卡");
            if (limit == null) {
                limit = new UseCardBean(card.getName(), "变身卡", card.getSkin(), System.currentTimeMillis() + (long)card.getTime() * 60000L, value, card.getZz());
                roleData.addLimit(limit);
            }
            else if (card.getName().equals(limit.getName()) && value.equals(limit.getValue())) {
                limit.setTime(limit.getTime() + (long)card.getTime() * 60000L);
            }
            else {
                limit.setName(card.getName());
                limit.setSkin(card.getSkin());
                limit.setZz(card.getZz());
                limit.setValue(value);
                limit.setTime(System.currentTimeMillis() + (long)card.getTime() * 60000L);
            }
            assetUpdate.setMsg("你成功使用了" + card.getName());
            limit.setlCard(roleData.getPackRecord().isCard(vs[1]));
            assetUpdate.setUseCard(limit);
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
            limit.setlCard(null);
            AchievemUtil.detectionAchievem(loginResult, "使用一次变身卡");
        }
    }
    //y月卡购买条件
    public static void YKbuy(ChannelHandlerContext ctx, LoginResult loginResult, RoleData roleData) {
        long time = 2592000000L;
        int Money = 58;//58积分购买月卡
        if (loginResult.getMoney().intValue() < 58) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你的积分不足：" + Money + "，无法购买"));
            return;
        }
        UseCardBean limit = roleData.getLimit("VIP");
        if (limit == null) {
            limit = new UseCardBean("月卡", "VIP", "1", System.currentTimeMillis() + time, "掉落率=1|经验加成=5|加强全系法术=5|召唤兽死亡不掉忠诚,血法|人物死亡惩罚减半");
            roleData.addLimit(limit);
        }
        else {
            limit.setTime(limit.getTime() + time);
        }
        AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
        loginResult.setMoney((int) (loginResult.getMoney() - Money));
        MonitorUtil.getMoney().useC(Money);
        assetUpdate.setData("C=" + (-Money));

        assetUpdate.setUseCard(limit);
        assetUpdate.setMsg("激活了" + time / 1000L / 60L / 60L / 24L + "天月卡特权");
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    //y月卡购买条件
    public static void JKbuy(ChannelHandlerContext ctx, LoginResult loginResult, RoleData roleData) {
        long time = 2592000000L*3;
        int Money = 158;//158积分购买季卡
        if (loginResult.getMoney().intValue() < 150) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你的积分不足：" + Money + "，无法购买"));
            return;
        }
        UseCardBean limit = roleData.getLimit("JVIP");
        if (limit == null) {
            limit = new UseCardBean("季卡", "JVIP", "2", System.currentTimeMillis() + time, "掉落率=5|经验加成=10|加强全系法术=10|召唤兽死亡不掉忠诚,血法|人物死亡惩罚减半");
            roleData.addLimit(limit);
        }
        else {
            limit.setTime(limit.getTime() + time);
        }
        AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
        loginResult.setMoney((int) (loginResult.getMoney() - Money));
        MonitorUtil.getMoney().useC(Money);
        assetUpdate.setData("C=" + (-Money));

        assetUpdate.setUseCard(limit);
        assetUpdate.setMsg("激活了" + time / 1000L / 60L / 60L / 24L + "天季卡特权");
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
}
