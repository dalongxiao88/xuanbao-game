package org.come.action.fight;

import java.util.List;
import org.come.handler.SendMessage;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import come.tool.FightingData.ManData;
import come.tool.Battle.BattleThreadPool;
import come.tool.Battle.BattleData;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class FightQlAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult result = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (result == null || (int)result.getFighting() == 0) {
            return;
        }
        BattleData battleData = (BattleData)BattleThreadPool.BattleDatas.get(result.getFighting());
        if (battleData == null) {
            return;
        }
        List<ManData> datas = battleData.getBattlefield().fightingdata;
        for (int i = datas.size() - 1; i >= 0; --i) {
            ManData data = (ManData)datas.get(i);
            if (data.getManname().equals(message)) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().fightQlAgreement(GsonUtil.getGsonUtil().getgson().toJson(data.getQuality())));
                return;
            }
        }
        SendMessage.sendMessageToSlef(ctx, message);
    }
}
