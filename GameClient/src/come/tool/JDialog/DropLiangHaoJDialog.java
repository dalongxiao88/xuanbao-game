package come.tool.JDialog;

import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;

public class DropLiangHaoJDialog implements TiShiChuLi
{
    @Override
    public void tipBox(boolean tishi, Object object) {
        if (tishi) {
            String mes = Agreement.getAgreement().selllianghaoAgreement("DROPLIANGHAO");
            SendMessageUntil.toServer(mes);
        }
    }
}
