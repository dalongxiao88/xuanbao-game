package come.tool.JDialog;

import org.come.socket.SendMessageUntil;
import org.come.until.GsonUtil;
import org.come.socket.Agreement;

public class ConfirmDialog implements TiShiChuLi
{
    @Override
    public void tipBox(boolean tishi, Object object) {
        if (tishi) {
            String sendmes = Agreement.getAgreement().confirmAgreement(GsonUtil.getGsonUtil().getgson().toJson(object));
            SendMessageUntil.toServer(sendmes);
        }
    }
}
