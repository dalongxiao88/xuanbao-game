package come.tool.JDialog;

import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
//月卡
public class YuekaJDialog implements TiShiChuLi
{
    @Override
    public void tipBox(boolean tishi, Object object) {
        if (tishi) {
            String mes = Agreement.getAgreement().usercardAgreement("YUE");
            SendMessageUntil.toServer(mes);
        }
    }
}
