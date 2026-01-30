package org.come.npc;

import org.come.Frame.ZhuFrame;
import com.tool.role.RoleData;
import org.come.action.NpcMenuAction;

public class GangInto implements NpcMenuAction
{
    @Override
    public void menuControl(String type) {
        if (RoleData.getRoleData().getLoginResult().getGang_id() != null && RoleData.getRoleData().getLoginResult().getGang_id().intValue() != 0) {
            TP.tp(TP.scdoor("3000", 2940, 2217), 4);
        }
        else {
            ZhuFrame.getZhuJpanel().addPrompt2("你还没有帮派");
        }
    }
}
