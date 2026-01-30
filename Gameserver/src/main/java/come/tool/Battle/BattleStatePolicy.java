package come.tool.Battle;

import java.util.Iterator;
import come.tool.FightingData.FightingManData;
import come.tool.FightingData.FightingEvents;
import java.util.List;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.until.GsonUtil;
import come.tool.FightingData.FightingRound;

public class BattleStatePolicy implements BattleState
{
    public static boolean lingbao;
    
    @Override
    public boolean handle(BattleData battleData) {
        int size1 = battleData.getRoundPolicy(battleData.getRound()).size();
        int size2 = battleData.getBattlefield().noyesum();
        long Pass = System.currentTimeMillis() - battleData.getBattletime();
        if (size1 >= size2 || Pass > 63000L) {
            List<FightingEvents> events = battleData.getBattlefield().ReceiveEvent();
            battleData.setPlayTime(events.size() * 400);
            battleData.getBattlefieldPlay().put(battleData.getRound(), events);
            List<FightingManData> playdatas = battleData.getBattlefield().Transformation();
            battleData.getPlayDatas().put(battleData.getRound() + 1, playdatas);
            FightingRound fightingRound = new FightingRound();
            fightingRound.setRound(events);
            fightingRound.setCurrentRound(battleData.getRound());
            fightingRound.setMansState(battleData.getBattlefield().getManState());
            fightingRound.setFightingsumber(battleData.getBattleNumber());
            String msg = Agreement.FightingRoundDealAgreement(GsonUtil.getGsonUtil().getgson().toJson(fightingRound));
            for (String string : battleData.getParticipantlist()) {
                SendMessage.sendMessageByRoleName(string, msg);
            }
            battleData.changeState(2);
        }
        return false;
    }
    
    static {
        BattleStatePolicy.lingbao = true;
    }
}
