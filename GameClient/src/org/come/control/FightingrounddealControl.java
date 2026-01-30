package org.come.control;

import org.come.Frame.ZhuFrame;
import come.tool.Fighting.Statelist;
import org.come.until.GsonUtil;
import come.tool.Fighting.FightingRound;
import org.come.Jpanel.FrameMessageChangeJpanel;
import come.tool.Fighting.FightingMixDeal;
import org.come.action.FromServerAction;

public class FightingrounddealControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        FrameMessageChangeJpanel.addtext(11, "—*—第#Y " + FightingMixDeal.CurrentRound + " #W回合战斗指令结束—*—", null, null);
        FightingRound fightingRound = (FightingRound)GsonUtil.getGsonUtil().getgson().fromJson(mes, FightingRound.class);
        if (FightingMixDeal.FightingNumber != fightingRound.getFightingsumber()) {
            return;
        }
        Statelist statelist = new Statelist();
        statelist.setRound(fightingRound.getRound());
        FightingMixDeal.mansState = fightingRound.getMansState();
        FightingMixDeal.CurrentRound = fightingRound.getCurrentRound();
        FightingMixDeal.BattlefieldPlay.put(Integer.valueOf(fightingRound.getCurrentRound()), statelist);
        FightingMixDeal.changeState(5);
        FightingMixDeal.roundType = 0L;
        ZhuFrame.getZhuJpanel().remove(ZhuFrame.getZhuJpanel().getChehui());
    }
}
