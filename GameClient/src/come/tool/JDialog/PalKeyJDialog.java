package come.tool.JDialog;

import org.come.Frame.ZhuFrame;
import org.come.entity.Pal;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.model.PalData;

public class PalKeyJDialog implements TiShiChuLi
{
    @Override
    public void tipBox(boolean tishi, Object object) {
        if (tishi) {
            if (object instanceof PalData) {
                PalData palDate = (PalData)object;
                String sendmes = Agreement.getAgreement().userpalAgreement("C" + palDate.getPalId());
                SendMessageUntil.toServer(sendmes);
            }
            else if (object instanceof Pal) {
                ZhuFrame.getZhuJpanel().addPrompt2("该道具已经失效");
                return;
            }
        }
    }
}
