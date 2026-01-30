package org.come.action.gang;

import org.come.entity.Gang;
import come.tool.newGang.GangDomain;
import org.come.entity.Gangapply;
import org.come.bean.GangChangeBean;
import org.come.until.AchievemUtil;
import org.come.until.GsonUtil;
import come.tool.newGang.GangUtil;
import org.come.entity.RoleTable;
import come.tool.Role.RolePool;
import org.come.until.AllServiceUtil;
import java.math.BigDecimal;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class GangAgreeAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult roleInfo = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (!roleInfo.getGangpost().equals("帮主") && !roleInfo.getGangpost().equals("护法")) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("帮主或者护法才有权限"));
            return;
        }
        BigDecimal roleid = new BigDecimal(message);
        Gangapply gangapply = AllServiceUtil.getGangapplyService().selectGangApply(roleid, roleInfo.getGang_id());
        if (gangapply == null) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("他已经加入别的帮派了"));
            return;
        }
        LoginResult result = RolePool.getLoginResult(roleid);
        RoleTable roleTable = null;
        if (result != null) {
            roleTable = new RoleTable(0, result);
        }
        else {
            roleTable = AllServiceUtil.getRoleTableService().selectGang(roleid);
            if (roleTable == null) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("没有这个玩家"));
                return;
            }
        }
        if (roleTable.getGang_id().intValue() != 0) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("他已经加入别的帮派了"));
            return;
        }
        GangDomain gangDomain = GangUtil.getGangDomain(roleInfo.getGang_id());
        Gang gang = gangDomain.getGang();
        int lvl = gang.getGanggrade().intValue();
        int sum = gang.getGangnumber().intValue();
        if ((sum >= 100 && lvl <= 1) || (sum >= 150 && lvl <= 2) || (sum >= 200 && lvl <= 3) || (sum >= 250 && lvl <= 4) || (sum >= 1000 && lvl <= 5)) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("帮派已达到人数上限"));
            return;
        }
        AllServiceUtil.getGangapplyService().deleteGangappleAll(roleid);
        gangDomain.addGangRole();
        roleTable.setGang_id(gang.getGangid());
        roleTable.setGangpost("帮众");
        roleTable.setGangname(gang.getGangname());
        AllServiceUtil.getRoleTableService().updateGang(roleTable);
        if (result != null) {
            ChannelHandlerContext ctxA = (ChannelHandlerContext)GameServer.getRoleNameMap().get(result.getRolename());
            if (ctxA != null) {
                gangDomain.upGangRole(result.getRole_id(), ctxA);
            }
            result.setGang_id(gang.getGangid());
            result.setGangpost("帮众");
            result.setGangname(gang.getGangname());
            if (ctxA != null) {
                String sendMes = Agreement.getAgreement().upRoleShowAgreement(GsonUtil.getGsonUtil().getgson().toJson(result.getRoleShow()));
                SendMessage.sendMessageToMapRoles(ctxA, result.getMapid(), sendMes);
            }
            GangChangeBean gangChangeBean = new GangChangeBean(result, "你加入了#G" + gang.getGangname());
            SendMessage.sendMessageToSlef(ctxA, Agreement.GangChangeAgreement(GsonUtil.getGsonUtil().getgson().toJson(gangChangeBean)));
            AchievemUtil.detectionAchievem(result, "加入帮派");
        }
    }
}
