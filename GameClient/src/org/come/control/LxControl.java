package org.come.control;

import org.come.mouslisten.ChosePetLxMouslisten;
import org.skill.frame.LXiulianMainFrame;
import org.come.until.GsonUtil;
import org.come.entity.RoleSummoning;
import org.come.action.FromServerAction;

public class LxControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        RoleSummoning pet = (RoleSummoning)GsonUtil.getGsonUtil().getgson().fromJson(mes, RoleSummoning.class);
        LXiulianMainFrame.getLXiulianMainFrame().getLXiulianMainPanel().panelGetData(pet);
        ChosePetLxMouslisten.refreshPetSkills();
    }
}
