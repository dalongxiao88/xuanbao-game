package org.come.until;

import org.come.bean.PrivateData;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.entity.RoleSummoning;

public class SendRoleAndRolesummingUntil
{
    public static void sendRoleSumming(RoleSummoning roleSummoning) {
        if (roleSummoning != null) {
            String serverMes = Agreement.getAgreement().petchangeAgreement(GsonUtil.getGsonUtil().getgson().toJson(roleSummoning));
            SendMessageUntil.toServer(serverMes);
        }
    }
    
    public static void sendRoleSummingSkill(RoleSummoning roleSummoning) {
        if (roleSummoning != null) {
            String serverMes = Agreement.getAgreement().petchangeSkillAgreement(GsonUtil.getGsonUtil().getgson().toJson(roleSummoning));
            SendMessageUntil.toServer(serverMes);
        }
    }
    
    public static void sendRole(PrivateData data) {
        if (data != null) {
            String serverMes = Agreement.getAgreement().rolePrivateAgreement(GsonUtil.getGsonUtil().getgson().toJson(data));
            SendMessageUntil.toServer(serverMes);
        }
    }
}
