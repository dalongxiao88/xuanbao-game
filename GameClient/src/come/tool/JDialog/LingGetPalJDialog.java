package come.tool.JDialog;

import org.come.Jpanel.PartnerMainJpanel;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.Frame.ZhuFrame;
import org.come.Frame.PartnerJframe;
import org.come.entity.Pal;

public class LingGetPalJDialog implements TiShiChuLi
{
    @Override
    public void tipBox(boolean tishi, Object object) {
        Pal pal = (Pal)object;
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
            String sendmes = Agreement.getAgreement().userpalAgreement(pidGetPal.getId() + "|getLingbao|" + pal.getLingbao().getBaoid());
            SendMessageUntil.toServer(sendmes);
        }
    }
}
