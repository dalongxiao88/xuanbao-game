package org.come.action.gang;

import org.come.entity.Gang;
import org.come.until.GsonUtil;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.until.AllServiceUtil;
import org.come.action.monitor.MonitorUtil;
import org.come.entity.GangGiveMoneyBean;
import java.math.BigDecimal;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class GangGiveMoneyAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult roleInfo = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        BigDecimal gold = new BigDecimal(message);
        if (gold.longValue() <= 0L) {
            return;
        }
        if (((LoginResult)GameServer.getAllLoginRole().get(ctx)).getGold().compareTo(gold) == -1) {
            return;
        }
        GangGiveMoneyBean giveMoneyBean = new GangGiveMoneyBean();
        roleInfo.setGold(roleInfo.getGold().subtract(gold));
        MonitorUtil.getMoney().useD(gold.longValue());
        roleInfo.setAchievement(roleInfo.getAchievement().add(gold));
        roleInfo.setContribution(roleInfo.getContribution().add(gold.divide(new BigDecimal("100000"), 0, 1)));
        roleInfo.setUptime("在线");
        Gang gang = AllServiceUtil.getGangService().findRoleGangByGangID(roleInfo.getGang_id());
        if (gang.getBuilder() == null) {
            gang.setBuilder(gold.divide(new BigDecimal("100000"), 0, 1));
        }
        else {
            gang.setBuilder(gang.getBuilder().add(gold.divide(new BigDecimal("100000"), 0, 1)));
        }
        if (gang.getProperty() == null) {
            gang.setProperty(gold);
        }
        else {
            gang.setProperty(gang.getProperty().add(gold));
        }
        AllServiceUtil.getGangService().updateGang(gang);
        giveMoneyBean.setGang(gang);
        giveMoneyBean.setLoginResult(roleInfo);
        int moneyLenth = gold.toString().length();
        String moneyColor = (moneyLenth > 9) ? "#R" : ((moneyLenth > 8) ? "#G" : ((moneyLenth > 7) ? "#c00EFEF" : ((moneyLenth > 6) ? "#Y" : "#W")));
        String msg = Agreement.getAgreement().chatAgreement("{\"id\":2,\"message\":\"#Y" + roleInfo.getRolename() + "#G向帮派账房捐赠了金钱" + moneyColor + gold.toString() + "#G两，为帮派做出了巨大贡献。\"}");
        SendMessage.sendMessageToAllRoles(msg);
        SendMessage.sendMessageToSlef(ctx, Agreement.givemoneyAgreement(GsonUtil.getGsonUtil().getgson().toJson(giveMoneyBean)));
    }
}
