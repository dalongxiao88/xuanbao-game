package org.come.control;

import org.come.Frame.TjJframe;
import org.come.until.GsonUtil;
import come.tool.FightingData.FightingForesee;
import org.come.action.FromServerAction;

public class FightInfolControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        try {
            if (mes == null || mes.equals("")) {
                return;
            }
            FightingForesee fightingForesee = (FightingForesee)GsonUtil.getGsonUtil().getgson().fromJson(mes, FightingForesee.class);
            TjJframe.getSupportListJframe().getTjJpanel().setFightingForesee(fightingForesee);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
