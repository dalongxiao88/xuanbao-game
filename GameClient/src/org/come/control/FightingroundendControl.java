package org.come.control;

import come.tool.Fighting.FightingMixDeal;
import org.come.action.FromServerAction;

public class FightingroundendControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        String[] vs = mes.split("\\|");
        int fight = Integer.parseInt(vs[0]);
        if (FightingMixDeal.FightingNumber != fight) {
            return;
        }
        while (FightingMixDeal.State == 5) {
            FightingMixDeal.PalyProgress(36L);
        }
        FightingMixDeal.RoundDecision();
    }
}
