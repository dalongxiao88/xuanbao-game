package org.come.action.fight;

import java.util.Iterator;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import come.tool.FightingData.ManData;
import come.tool.FightingData.FightingRevocation;
import come.tool.Battle.BattleThreadPool;
import come.tool.Battle.BattleData;
import org.come.until.GsonUtil;
import come.tool.FightingData.FightingEvents;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class FightRoundAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        FightingEvents fightingEvents = (FightingEvents)GsonUtil.getGsonUtil().getgson().fromJson(message, FightingEvents.class);
        BattleData battleData = (BattleData)BattleThreadPool.BattleDatas.get(Integer.valueOf(fightingEvents.getFightingsum()));
        if (battleData == null) {
            return;
        }
        if ((int)fightingEvents.getType() != -1) {
            battleData.removePolicy(fightingEvents);
            FightingRevocation fightingRevocation = new FightingRevocation();
            fightingRevocation.setMan(Integer.valueOf(fightingEvents.getOriginator().getMan()));
            for (ManData fightingdatum : battleData.getBattlefield().getFightingdata()) {
                if (fightingdatum.getCamp() == fightingEvents.getOriginator().getCamp()) {
                    if (fightingdatum.getMan() == fightingEvents.getOriginator().getMan()) {
                        fightingRevocation.setType(fightingEvents.getType());
                    }
                    else {
                        fightingRevocation.setType(Integer.valueOf(3));
                    }
                    String msg = Agreement.getAgreement().movedAgreement(GsonUtil.getGsonUtil().getgson().toJson(fightingRevocation));
                    if (fightingdatum.getType() == 1) {
                        ManData parents = battleData.getBattlefield().getPetParents(fightingdatum);
                        SendMessage.sendMessageByRoleName(parents.getManname(), msg);
                    }
                    else {
                        SendMessage.sendMessageByRoleName(fightingdatum.getManname(), msg);
                    }
                }
            }
        }
        else {
            battleData.addPolicy(fightingEvents);
            if (battleData.getRoundPolicy(battleData.getRound()).size() == battleData.getBattlefield().noyesum()) {}
            String msg2 = Agreement.battleroundAgreement(message);
            for (String string : battleData.getParticipantlist()) {
                SendMessage.sendMessageByRoleName(string, msg2);
            }
        }
    }
}
