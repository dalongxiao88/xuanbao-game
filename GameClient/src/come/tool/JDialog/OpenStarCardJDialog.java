package come.tool.JDialog;

import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;

public class OpenStarCardJDialog implements TiShiChuLi
{
    @Override
    public void tipBox(boolean tishi, Object object) {
        if (tishi) {
            SendMessageUntil.toServer(Agreement.getAgreement().rolechangeAgreement(String.valueOf(object)));
        }
    }
}
