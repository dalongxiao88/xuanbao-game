package org.come.control;

import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.Jpanel.FrameMessageChangeJpanel;
import org.come.until.UserData;
import com.tool.role.RoleData;
import org.come.action.NpcMenuAction;

public class CompulsoryDivorceControl implements NpcMenuAction
{
    @Override
    public void menuControl(String type) {
        if (type.equals("确定")) {
            this.compulsoryDivorce(RoleData.getRoleData().getLoginResult().getMarryObject());
        }
        else if (type.equals("我再考虑考虑")) {}
    }
    
    public void compulsoryDivorce(String former) {
        if (!UserData.uptael(50000000L)) {
            FrameMessageChangeJpanel.addtext(6, "您支付不起5000W的离婚费用！！", null, null);
            return;
        }
        String mes = Agreement.getAgreement().unMarry("");
        SendMessageUntil.toServer(mes);
    }
}
