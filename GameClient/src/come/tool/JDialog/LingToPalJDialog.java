package come.tool.JDialog;

import org.come.entity.Pal;
import org.come.Jpanel.PartnerMainJpanel;
import com.tool.role.RoleLingFa;
import org.come.until.UserData;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.Frame.ZhuFrame;
import org.come.Frame.PartnerJframe;
import org.come.model.Lingbao;

public class LingToPalJDialog implements TiShiChuLi
{
    @Override
    public void tipBox(boolean tishi, Object object) {
        Lingbao lingbao = (Lingbao)object;
        if (tishi) {
            PartnerMainJpanel mainJpanel = PartnerJframe.getPartnerJframe().getPartnerMainJpanel();
            if (mainJpanel.getPalDataChooseId() < 0) {
                ZhuFrame.getZhuJpanel().addPrompt2("请选择一个伙伴");
                return;
            }
            Pal pidGetPal = mainJpanel.pidGetPal(mainJpanel.getPalDataChooseId());
            if (pidGetPal == null) {
                return;
            }
            String sendmes = Agreement.getAgreement().userpalAgreement(pidGetPal.getId() + "|lingbao|" + lingbao.getBaoid());
            SendMessageUntil.toServer(sendmes);
            lingbao.setOperation("删除");
            UserData.upling(lingbao);
            RoleLingFa.getRoleLingFa().deleteling(lingbao);
        }
    }
}
