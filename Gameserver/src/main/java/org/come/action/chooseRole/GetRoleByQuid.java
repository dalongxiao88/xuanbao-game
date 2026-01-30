package org.come.action.chooseRole;

import org.come.entity.RoleTableNew;
import java.util.List;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.entity.RoleTableList;
import org.come.until.AllServiceUtil;
import java.math.BigDecimal;
import org.come.until.GsonUtil;
import org.come.entity.ChooseArea;
import org.come.server.GameServer;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class GetRoleByQuid implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        if (GameServer.OPEN) {
            return;
        }
        ChooseArea mes = (ChooseArea)GsonUtil.getGsonUtil().getgson().fromJson(message, ChooseArea.class);
        BigDecimal uid = new BigDecimal(mes.getUid());
        Integer qid = Integer.valueOf(mes.getQid());
        String belongId = AllServiceUtil.getOpenareatableService().selectBelong(qid + "");
        if (belongId == null) {
            return;
        }
        List<RoleTableNew> role = AllServiceUtil.getRegionService().selectRole(uid, Integer.valueOf(belongId));
        RoleTableList list = new RoleTableList();
        list.setRoleList(role);
        String content = GsonUtil.getGsonUtil().getgson().toJson(list);
        String msg = Agreement.getAgreement().uidAndQidForRoleAgreement(content);
        SendMessage.sendMessageToSlef(ctx, msg);
    }
}
