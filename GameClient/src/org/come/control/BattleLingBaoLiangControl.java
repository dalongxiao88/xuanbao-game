package org.come.control;

import come.tool.Fighting.Fightingimage;
import come.tool.Fighting.FightingMixDeal;
import org.come.action.FromServerAction;

public class BattleLingBaoLiangControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        if (FightingMixDeal.State != 5) {
            String[] values = mes.split("\\|");
            for (int i = 0; i < values.length; ++i) {
                String[] vals = values[i].split("-");
                Fightingimage lingbao = FightingMixDeal.CurrentDataImage(Integer.parseInt(vals[0]), Integer.parseInt(vals[1]));
                lingbao.getFightingManData().addstate((i == 0) ? "LBFD" : "LBFD2");
            }
        }
    }
}
