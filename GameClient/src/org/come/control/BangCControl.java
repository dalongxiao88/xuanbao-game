package org.come.control;

import come.tool.BangBattle.BangTZ;
import come.tool.BangBattle.BangFight;
import come.tool.Fighting.FightingMixDeal;
import org.come.action.FromServerAction;

public class BangCControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        String[] v = mes.split("\\|");
        if (v.length > 1 && FightingMixDeal.State == 0) {
            BangFight.getBangFight().tz = new BangTZ(v);
        }
    }
}
