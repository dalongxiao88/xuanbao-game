package org.come.action.fight;

import come.tool.Battle.BattleThreadPool;
import come.tool.Battle.BattleData;
import org.come.until.GsonUtil;
import come.tool.FightingData.FightingResponse;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class FightingResponseAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        FightingResponse fightingResponse = (FightingResponse)GsonUtil.getGsonUtil().getgson().fromJson(message, FightingResponse.class);
        BattleData battleData = (BattleData)BattleThreadPool.BattleDatas.get(Integer.valueOf(fightingResponse.getFightingNumber()));
        if (battleData == null) {
            return;
        }
        battleData.addPreview(fightingResponse.getManData());
    }
}
