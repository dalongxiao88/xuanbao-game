package org.come.action.fight;

import come.tool.Battle.BattleThreadPool;
import come.tool.Battle.BattleData;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class FightingendAction implements IAction
{
    static int size;
    
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult roleinfo = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (roleinfo == null) {
            return;
        }
        BattleData battleData = (BattleData)BattleThreadPool.BattleDatas.get(roleinfo.getFighting());
        if (battleData != null) {
            battleData.getParticipantlist().remove(roleinfo.getRolename());
            BattleThreadPool.sendBattleState(0, roleinfo.getRolename());
        }
        roleinfo.setFighting(Integer.valueOf(0));
    }
    
    static {
        FightingendAction.size = 0;
    }
}
