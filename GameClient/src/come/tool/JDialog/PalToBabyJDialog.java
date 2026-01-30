package come.tool.JDialog;

import org.come.entity.Pal;
import org.come.Jpanel.PartnerMainJpanel;
import org.come.bean.LoginResult;
import org.come.Jpanel.TestChildAttributeJpanel;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import com.tool.role.RoleProperty;
import org.come.Frame.ZhuFrame;
import org.come.Frame.PartnerJframe;
import com.tool.role.RoleData;
import org.come.entity.Baby;

public class PalToBabyJDialog implements TiShiChuLi
{
    @Override
    public void tipBox(boolean tishi, Object object) {
        Baby baby = (Baby)object;
        if (tishi) {
            LoginResult loginResult = RoleData.getRoleData().getLoginResult();
            PartnerMainJpanel mainJpanel = PartnerJframe.getPartnerJframe().getPartnerMainJpanel();
            if (mainJpanel.getPalDataChooseId() < 0) {
                ZhuFrame.getZhuJpanel().addPrompt2("请选择一个伙伴");
                return;
            }
            Pal pidGetPal = mainJpanel.pidGetPal(mainJpanel.getPalDataChooseId());
            if (pidGetPal == null) {
                return;
            }
            loginResult.setBabyId(null);
            RoleProperty.ResetBaby(null);
            String sendmes = Agreement.getAgreement().userpalAgreement(pidGetPal.getId() + "|baby|" + baby.getBabyid());
            SendMessageUntil.toServer(sendmes);
            TestChildAttributeJpanel.btnfollow.setText("领回");
        }
    }
}
