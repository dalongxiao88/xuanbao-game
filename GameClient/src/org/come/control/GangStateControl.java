package org.come.control;

import come.tool.BangBattle.BangFight;
import org.come.action.FromServerAction;

public class GangStateControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        BangFight.getBangFight().init(mes);
    }
}
