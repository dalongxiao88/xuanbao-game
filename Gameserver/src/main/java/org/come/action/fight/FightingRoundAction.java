package org.come.action.fight;

import come.tool.Battle.BattleThreadPool;
import come.tool.Battle.BattleData;
import org.come.until.GsonUtil;
import come.tool.FightingData.FightingEvents;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class FightingRoundAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        FightingEvents fightingEvents = (FightingEvents)GsonUtil.getGsonUtil().getgson().fromJson(message, FightingEvents.class);
        BattleData battleData = (BattleData)BattleThreadPool.BattleDatas.get(Integer.valueOf(fightingEvents.getFightingsum()));
        if (battleData == null) {
            return;
        }
        battleData.addPlayEnd(fightingEvents.getCurrentRound());
    }
}
