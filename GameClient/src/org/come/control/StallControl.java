package org.come.control;

import org.come.until.UserStallUntil;
import org.come.until.GsonUtil;
import com.tool.Stall.Stall;
import org.come.action.FromServerAction;

public class StallControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        Stall stall = (Stall)GsonUtil.getGsonUtil().getgson().fromJson(mes, Stall.class);
        UserStallUntil.updateBuyStall(stall);
    }
}
