package come.tool.JDialog;

import org.come.until.FormsManagement;
import org.come.mouslisten.ChangeMouseSymbolMouslisten;
import org.come.Frame.ZhuFrame;
import com.tool.role.RoleData;
import org.come.entity.Baby;

public class BabyaseJDialog implements TiShiChuLi
{
    @Override
    public void tipBox(boolean tishi, Object object) {
        if (tishi) {
            if (object instanceof Baby) {
                Baby baby = (Baby)object;
                if (RoleData.getRoleData().getLoginResult().getBabyId() != null && RoleData.getRoleData().getLoginResult().getBabyId().compareTo(baby.getBabyid()) == 0) {
                    ZhuFrame.getZhuJpanel().addPrompt2("这只宝宝已在参战中！！！");
                    return;
                }
                ChangeMouseSymbolMouslisten.BabyasePet(baby);
            }
            FormsManagement.HideForm(20);
        }
    }
}
