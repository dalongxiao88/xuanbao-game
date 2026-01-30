package org.come.action.gang;

import org.come.bean.GangChangeBean;
import org.come.handler.SendMessage;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import org.come.until.AllServiceUtil;
import java.math.BigDecimal;
import org.come.entity.RoleTable;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class GangRetreatrestAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult roleInfo = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        RoleTable roleTable = new RoleTable(0, roleInfo);
        roleTable.setGang_id(new BigDecimal(0));
        roleTable.setGangpost(null);
        roleTable.setGangname(null);
        AllServiceUtil.getRoleTableService().updateGang(roleTable);
        roleInfo.setGang_id(new BigDecimal(0));
        roleInfo.setGangpost(null);
        roleInfo.setGangname(null);
        String sendMes = Agreement.getAgreement().upRoleShowAgreement(GsonUtil.getGsonUtil().getgson().toJson(roleInfo.getRoleShow()));
        SendMessage.sendMessageToMapRoles(ctx, roleInfo.getMapid(), sendMes);
        GangChangeBean gangChangeBean = new GangChangeBean(roleInfo, "成功退出帮派");
        SendMessage.sendMessageByRoleName(roleInfo.getRolename(), Agreement.GangChangeAgreementrest(GsonUtil.getGsonUtil().getgson().toJson(gangChangeBean)));
    }
}
