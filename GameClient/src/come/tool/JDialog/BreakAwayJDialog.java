package come.tool.JDialog;

import org.come.bean.LoginResult;
import org.come.bean.RoleShow;
import org.come.until.FormsManagement;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import com.tool.role.RoleProperty;
import java.math.BigDecimal;
import com.tool.role.RoleData;
import com.tool.image.ImageMixDeal;

public class BreakAwayJDialog implements TiShiChuLi
{
    @Override
    public void tipBox(boolean tishi, Object object) {
        if (tishi) {
            try {
                RoleShow roleShow = ImageMixDeal.userimg.getRoleShow();
                LoginResult loginResult = RoleData.getRoleData().getLoginResult();
                loginResult.setGang_id(new BigDecimal(0));
                loginResult.setGangname("");
                loginResult.setGangpost("");
                loginResult.setAchievement(new BigDecimal(0));
                loginResult.setResistance("主-|副-");
                roleShow.setGang_id(loginResult.getGang_id());
                roleShow.setGangname(loginResult.getGangname());
                RoleProperty.ResetBp();
                String sendMes = Agreement.GangRetreatAgreement("");
                SendMessageUntil.toServer(sendMes);
            }
            catch (Exception ex) {}
            FormsManagement.HideForm(48);
        }
    }
}
