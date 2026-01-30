package come.tool.JDialog;

import org.come.socket.Agreement;
import org.come.socket.SendMessageUntil;

public class JikaJDialog  implements TiShiChuLi
{
    @Override
    public void tipBox(boolean tishi, Object object) {
        if (tishi) {
            String mes = Agreement.getAgreement().usercardAgreement("JIII");
            SendMessageUntil.toServer(mes);
        }
    }
}
