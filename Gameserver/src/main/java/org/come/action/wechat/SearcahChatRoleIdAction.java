package org.come.action.wechat;

import org.come.handler.SendMessage;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import org.come.bean.LoginResult;
import org.come.server.GameServer;
import org.come.bean.Role_bean;
import org.come.until.AllServiceUtil;
import org.come.entity.RoleTable;
import java.math.BigDecimal;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class SearcahChatRoleIdAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        if (message == null) {
            return;
        }
        BigDecimal roleID = new BigDecimal(message);
        RoleTable rt = new RoleTable();
        rt.setRole_id(roleID);
        rt.setLiangHao(String.valueOf(roleID));
        LoginResult roleInfo = AllServiceUtil.getRoleTableService().selectRoleIdOrLiangHao(rt);
        if (roleInfo != null) {
            Role_bean bean = new Role_bean();
            if (GameServer.getRoleNameMap().get(roleInfo.getRolename()) != null) {
                roleInfo = (LoginResult)GameServer.getAllLoginRole().get(GameServer.getRoleNameMap().get(roleInfo.getRolename()));
            }
            bean.setTurnAround(roleInfo.getTurnAround());
            bean.setSkin(roleInfo.getSkin());
            bean.setSpeciesId(roleInfo.getSpecies_id());
            bean.setGangname(roleInfo.getGangname());
            bean.setGrade(roleInfo.getGrade());
            bean.setRace_name(roleInfo.getRace_name());
            bean.setRole_id(roleInfo.getRole_id());
            bean.setRolename(roleInfo.getRolename());
            bean.setTitle(roleInfo.getTitle());
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().searcahChatRoleIdAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean)));
        }
    }
}
