package come.tool.JDialog;

import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.until.SendRoleAndRolesummingUntil;
import com.tool.role.RoleData;
import org.skill.frame.SkillMainFrame;

public class FmczJDialog implements TiShiChuLi
{
    @Override
    public void tipBox(boolean tishi, Object object) {
        if (tishi) {
            for (int i = 0; i < SkillMainFrame.getSkillMainFrame().getSkillMainPanel().getDivisionGatePanel2().getFaMenItemViews().length; ++i) {
                SkillMainFrame.getSkillMainFrame().getSkillMainPanel().getDivisionGatePanel2().getFaMenItemViews()[i].init();
                SkillMainFrame.getSkillMainFrame().getSkillMainPanel().getDivisionGatePanel2().getjLabels()[i].setVisible(false);
                SkillMainFrame.getSkillMainFrame().getSkillMainPanel().getDivisionGatePanel2().getSkillTYCBtn5s()[i].setVisible(true);
            }
            RoleData.getRoleData().getPrivateData().setSkills("F", null);
            SendRoleAndRolesummingUntil.sendRole(RoleData.getRoleData().getPrivateData());
            String mes = Agreement.getAgreement().rolechangeAgreement("XDFM=0");
            SendMessageUntil.toServer(mes);
        }
    }
}
