package come.tool.JDialog;

import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;

public class ChildRearingJDialog implements TiShiChuLi
{
    @Override
    public void tipBox(boolean tishi, Object object) {
        String serverMes = Agreement.getAgreement().BabyCustodayAgreement(tishi ? "yes" : "no");
        SendMessageUntil.toServer(serverMes);
    }
}
