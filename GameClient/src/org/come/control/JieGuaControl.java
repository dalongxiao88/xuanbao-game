package org.come.control;

import org.come.Frame.JieGuaJframe;
import org.come.Frame.DuiHuanLingLiJframe;
import org.come.action.NpcMenuAction;

public class JieGuaControl implements NpcMenuAction
{
    @Override
    public void menuControl(String type) {
        if (type.equals("兑换")) {
            DuiHuanLingLiJframe.showShop();
        }
        else if (type.equals("解卦")) {
            JieGuaJframe.open();
        }
    }
}
