package org.come.action.gang;

import come.tool.newGang.GangDomain;
import org.come.until.GsonUtil;
import org.come.bean.GangChangeBean;
import come.tool.newGang.GangUtil;
import org.come.until.AllServiceUtil;
import org.come.entity.RoleTable;
import come.tool.Role.RolePool;
import java.math.BigDecimal;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class GangChangeAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult roleInfo = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (!roleInfo.getGangpost().equals("帮主")) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你不是帮主无法卸任"));
            return;
        }
        BigDecimal roleId = new BigDecimal(message);
        LoginResult result = RolePool.getLoginResult(roleId);
        RoleTable roleTable = null;
        if (result != null) {
            roleTable = new RoleTable(0, result);
        }
        else {
            roleTable = AllServiceUtil.getRoleTableService().selectGang(roleId);
            if (roleTable == null) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("没有这个玩家"));
                return;
            }
        }
        if (roleInfo.getGang_id().compareTo(roleTable.getGang_id()) != 0) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("他不是你们帮的成员无法卸任给他"));
            return;
        }
        GangDomain gangDomain = GangUtil.getGangDomain(roleInfo.getGang_id());
        gangDomain.upGangMaster(roleTable.getRolename());
        RoleTable role = new RoleTable(0, roleInfo);
        roleInfo.setGangpost(roleTable.getGangpost());
        role.setGangpost(roleTable.getGangpost());
        AllServiceUtil.getRoleTableService().updateGang(role);
        if (result != null) {
            result.setGangpost("帮主");
        }
        roleTable.setGangpost("帮主");
        AllServiceUtil.getRoleTableService().updateGang(roleTable);
        GangChangeBean gangChangeBean = new GangChangeBean(result, "你的职位调整为#G帮主");
        SendMessage.sendMessageByRoleName(result.getRolename(), Agreement.GangChangeAgreement(GsonUtil.getGsonUtil().getgson().toJson(gangChangeBean)));
        gangChangeBean = new GangChangeBean(roleInfo, "你的职位调整为#G" + roleInfo.getGangpost());
        SendMessage.sendMessageByRoleName(roleInfo.getRolename(), Agreement.GangChangeAgreement(GsonUtil.getGsonUtil().getgson().toJson(gangChangeBean)));
    }
}
