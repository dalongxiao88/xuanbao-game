package org.come.control;

import org.come.bean.GoodTrans;
import com.tool.Stall.Commodity;
import com.tool.Stall.Stall;
import org.come.model.InternalForm;
import org.come.Frame.TransJframe;
import com.tool.Stall.StallBean;
import org.come.Frame.BoothBoxJframe;
import org.come.until.FormsManagement;
import org.come.entity.RoleSummoning;
import java.util.List;
import org.come.Jpanel.TestPetJpanel;
import com.tool.role.RoleData;
import org.come.until.UserMessUntil;
import org.come.until.GsonUtil;
import org.come.bean.PetResultBean;
import org.come.action.FromServerAction;

public class PetControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        PetResultBean petBean = (PetResultBean)GsonUtil.getGsonUtil().getgson().fromJson(mes, PetResultBean.class);
        UserMessUntil.setPetListTable(qc(petBean.getRoleSummonings()));
        TestPetJpanel.showListModel(UserMessUntil.getPetListTable(), RoleData.getRoleData().getLoginResult().getSummoning_id());
        UserMessUntil.setChosePetMes(null);
    }
    
    public static List<RoleSummoning> qc(List<RoleSummoning> roleSummonings) {
        InternalForm form = FormsManagement.getInternalForm2(16);
        if (form != null) {
            Stall stall = ((BoothBoxJframe)form.getFrame()).getBoothboxjpanel().getStall2();
            if (stall != null && (stall.getState() == StallBean.PREPARE || stall.getState() == StallBean.NO)) {
                Commodity[] pets = stall.getPets();
                for (int i = 0; i < pets.length; ++i) {
                    RoleSummoning pet = (pets[i] != null) ? pets[i].getPet() : null;
                    if (pet != null) {
                        int j = roleSummonings.size() - 1;
                        while (j >= 0) {
                            if (pet.getSid().compareTo(((RoleSummoning)roleSummonings.get(j)).getSid()) == 0) {
                                roleSummonings.remove(j);
                                break;
                            }
                            else {
                                --j;
                            }
                        }
                    }
                }
            }
        }
        form = FormsManagement.getInternalForm2(14);
        if (form != null) {
            GoodTrans goodTrans = ((TransJframe)form.getFrame()).getTransJpanel().getGoodTrans();
            if (goodTrans != null && goodTrans.getPets() != null) {
                for (int k = 0; k < goodTrans.getPets().size(); ++k) {
                    RoleSummoning pet2 = (RoleSummoning)goodTrans.getPets().get(k);
                    int l = roleSummonings.size() - 1;
                    while (l >= 0) {
                        if (pet2.getSid().compareTo(((RoleSummoning)roleSummonings.get(l)).getSid()) == 0) {
                            roleSummonings.remove(l);
                            break;
                        }
                        else {
                            --l;
                        }
                    }
                }
            }
        }
        return roleSummonings;
    }
}
