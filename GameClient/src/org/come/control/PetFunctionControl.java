package org.come.control;

import org.come.Jpanel.TestPetJpanel;
import com.tool.role.RoleData;
import org.come.until.UserMessUntil;
import org.come.until.GsonUtil;
import org.come.entity.RoleSummoning;
import org.come.action.FromServerAction;

public class PetFunctionControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        RoleSummoning roleSummoning = (RoleSummoning)GsonUtil.getGsonUtil().getgson().fromJson(mes, RoleSummoning.class);
        this.petOperation(roleSummoning);
    }
    
    public void petOperation(RoleSummoning roleSummoning) {
        for (int i = 0; i < UserMessUntil.getPetListTable().size(); ++i) {
            if (((RoleSummoning)UserMessUntil.getPetListTable().get(i)).getSid().compareTo(roleSummoning.getSid()) == 0) {
                UserMessUntil.getPetListTable().set(i, roleSummoning);
                return;
            }
        }
        UserMessUntil.getPetListTable().add(roleSummoning);
        TestPetJpanel.showListModel(UserMessUntil.getPetListTable(), RoleData.getRoleData().getLoginResult().getSummoning_id());
    }
}
