package come.tool.JDialog;

import com.tool.role.RoleData;
import org.come.Frame.ZhuFrame;
import org.come.socket.Agreement;
import org.come.socket.SendMessageUntil;
import org.come.until.UserMessUntil;

import java.math.BigDecimal;

public class QlJDialog implements TiShiChuLi {
    @Override
    public void tipBox(boolean tishi, Object object) {


        Integer qlNum = UserMessUntil.getChosePetMes().getOpenql() + 1;
        int qlMoney = qlNum * 5000;
        int qlQm = qlNum * 20;
        if (RoleData.getRoleData().getLoginResult().getGold().compareTo(new BigDecimal(50000000 * qlNum)) < 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("银两不足"+qlMoney+"万两!#91");//开启悟灵金额
            return;
        }
        if (UserMessUntil.getChosePetMes().getFriendliness() < (200000 * qlNum)) {
            ZhuFrame.getZhuJpanel().addPrompt2("您的神兽亲密值不足"+qlQm+"W!!!#91");
            return;
        }

        if (tishi) {
            final String sendmes = Agreement.getAgreement().userpetAgreement("QL|" + UserMessUntil.getChosePetMes().getSid());
            SendMessageUntil.toServer(sendmes);
        }
    }
}
