package come.tool.JDialog;

import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;

public class TransJDialog implements TiShiChuLi
{
    @Override
    public void tipBox(boolean tishi, Object object) {
        String roleName = (String)object;
        if (tishi) {
            String send = Agreement.getAgreement().TransStateAgreement("1|" + roleName);
            SendMessageUntil.toServer(send);
        }
    }
}
