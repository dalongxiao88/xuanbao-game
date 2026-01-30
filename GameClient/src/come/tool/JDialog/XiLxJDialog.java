package come.tool.JDialog;

import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.Frame.ZhuFrame;
import com.tool.role.RoleData;

public class XiLxJDialog implements TiShiChuLi
{
    @Override
    public void tipBox(boolean tishi, Object object) {
        if (tishi) {
            if (180000000L > RoleData.getRoleData().getLoginResult().getGold().longValue()) {
                ZhuFrame.getZhuJpanel().addPrompt2("你的大话币不足以支付洗点费用");
                return;
            }
            String sendmes = Agreement.getAgreement().LingXiAgreement("D&" + object);
            SendMessageUntil.toServer(sendmes);
        }
    }
}
