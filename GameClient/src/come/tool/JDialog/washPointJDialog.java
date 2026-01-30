package come.tool.JDialog;

import org.skill.frame.SkillMainFrame;
import com.tool.role.RoleProperty;
import org.come.until.SendRoleAndRolesummingUntil;
import org.come.until.UserData;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.Frame.ZhuFrame;
import java.math.BigDecimal;
import com.tool.role.RoleData;

public class washPointJDialog implements TiShiChuLi
{
    @Override
    public void tipBox(boolean tishi, Object object) {
        if (tishi) {
            if (object instanceof String) {
                if (RoleData.getRoleData().getLoginResult().getGold().compareTo(new BigDecimal(500000)) < 0) {
                    ZhuFrame.getZhuJpanel().addPrompt2("金钱不足");
                    return;
                }
                String mes = Agreement.getAgreement().rolechangeAgreement((String)object);
                SendMessageUntil.toServer(mes);
            }
            else {
                int dian = (int)object;
                if (UserData.uptael((long)dian)) {
                    RoleData.getRoleData().getPrivateData().setSkills("T", (String)null);
                    SendRoleAndRolesummingUntil.sendRole(RoleData.getRoleData().getPrivateData());
                    RoleProperty.ResetEw();
                    SkillMainFrame.getSkillMainFrame().getSkillMainPanel().changeBtnPanel(1);
                }
            }
        }
    }
}
