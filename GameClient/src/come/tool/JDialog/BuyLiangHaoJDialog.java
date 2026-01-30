package come.tool.JDialog;

import org.come.socket.SendMessageUntil;
import org.come.until.GsonUtil;
import org.come.socket.Agreement;

public class BuyLiangHaoJDialog implements TiShiChuLi
{
    @Override
    public void tipBox(boolean tishi, Object object) {
        if (tishi) {
            String mes = Agreement.getAgreement().selllianghaoAgreement("BUYLIANGHAO|" + GsonUtil.getGsonUtil().getgson().toJson(object));
            SendMessageUntil.toServer(mes);
        }
    }
}
