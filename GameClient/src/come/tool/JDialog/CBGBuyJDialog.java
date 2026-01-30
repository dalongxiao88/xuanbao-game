package come.tool.JDialog;

import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;

public class CBGBuyJDialog implements TiShiChuLi
{
    @Override
    public void tipBox(boolean tishi, Object object) {
        if (tishi) {
            String sendmes = Agreement.getAgreement().goodsBuyAgreement(object + "");
            SendMessageUntil.toServer(sendmes);
        }
    }
}
