package org.come.npc;

import org.come.Frame.ZhuFrame;
import org.come.until.FormsManagement;
import java.math.BigDecimal;
import com.tool.role.RoleData;
import org.come.action.NpcMenuAction;

public class GangCreate implements NpcMenuAction
{
    @Override
    public void menuControl(String type) {
        if (RoleData.getRoleData().getLoginResult().getGang_id() != null) {
            if (RoleData.getRoleData().getLoginResult().getGang_id().compareTo(BigDecimal.ZERO) == 0) {
                FormsManagement.showForm(25);
            }
            else {
                ZhuFrame.getZhuJpanel().addPrompt2("你已经有帮派了");
            }
        }
        else {
            FormsManagement.showForm(25);
        }
    }
}
