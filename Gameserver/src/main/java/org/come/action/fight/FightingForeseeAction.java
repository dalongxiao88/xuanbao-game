package org.come.action.fight;

import org.come.model.Gamemap;
import come.tool.Role.RoleData;
import come.tool.Battle.BattleThreadPool;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.tool.WriteOut;
import come.tool.Role.RolePool;
import org.come.until.GsonUtil;
import come.tool.Battle.FightingForesee;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class FightingForeseeAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (loginResult == null) {
            return;
        }
        FightingForesee fightingResponse = (FightingForesee)GsonUtil.getGsonUtil().getgson().fromJson(message, FightingForesee.class);
        if (fightingResponse.getRobotid() != null) {
            LoginResult role = (LoginResult)GameServer.getAllLoginRole().get(ctx);
            if (role == null) {
                return;
            }
            RoleData roleData = RolePool.getRoleData(role.getRole_id());
            if (roleData.isRobotId(Integer.parseInt(fightingResponse.getRobotid()))) {
                if(fightingResponse.getType()!=0) {
                    WriteOut.addtxt("非法robotID:" + fightingResponse.getRobotid() + ":" + role.getRole_id() + ":" + role.getRolename(), 9999L);
                    return;
                }
            }
        }
        if (fightingResponse.getType() == 5) {
            if (loginResult.getRoleData().getLimit("杀人香") != null) {
                fightingResponse.setType(10);
                Gamemap gamemap = GameServer.getMap(loginResult.getMapid().toString());
                if (gamemap == null || (gamemap.getPolice() != null && !gamemap.getPolice().equals("1"))) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("本地图不允许强行杀人"));
                    return;
                }
            }
            else if ((int)loginResult.getRoleData().getRoleSystem().getIsPk() == 0) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("先设置允许切磋"));
                return;
            }
        }
        BattleThreadPool.addBattle(loginResult, fightingResponse);
    }
}
