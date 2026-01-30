package org.come.control;

import org.come.Jpanel.DreamlandTrialMainJpanel;
import org.come.Frame.DreamlandTrialMainJframe;
import come.tool.Fighting.FightingMixDeal;
import org.come.until.GsonUtil;
import come.tool.FightingData.BattleEnd;
import org.come.action.FromServerAction;

public class BattleEndControl implements FromServerAction
{
    private int fber;
    
    public BattleEndControl() {
        this.fber = 0;
    }
    
    @Override
    public void controlMessFromServer(String mes, String type) {
        BattleEnd battleEnd = (BattleEnd)GsonUtil.getGsonUtil().getgson().fromJson(mes, BattleEnd.class);
        if (FightingMixDeal.FightingNumber != battleEnd.getFightingNumber() || FightingMixDeal.FightingNumber == -1) {
            return;
        }
        if (this.fber == battleEnd.getFightingNumber()) {
            return;
        }
        this.fber = battleEnd.getFightingNumber();
        if (mes.indexOf("你获得勇者积分") != -1) {
            try {
                DreamlandTrialMainJpanel dreamlandTrialMainJpanel = DreamlandTrialMainJframe.getDreamlandTrialMainJframe().getDreamlandTrialMainJpanel();
                dreamlandTrialMainJpanel.setNowLvl(dreamlandTrialMainJpanel.getNowLvl() + 1);
            }
            catch (Exception ex) {}
        }
        while (FightingMixDeal.State == 5) {
            FightingMixDeal.PalyProgress(36L);
        }
        FightingMixDeal.FightingEnd(battleEnd);
    }
}
