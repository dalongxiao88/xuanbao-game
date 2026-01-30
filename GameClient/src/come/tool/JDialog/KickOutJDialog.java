package come.tool.JDialog;

import org.come.Jpanel.FactionMemberJpanel;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.bean.LoginResult;
import org.come.Frame.FactionMainJframe;

public class KickOutJDialog implements TiShiChuLi
{
    @Override
    public void tipBox(boolean tishi, Object object) {
        int index = (int)object;
        if (tishi) {
            try {
                FactionMemberJpanel factionMemberJpanel = FactionMainJframe.getFactionMainJframe().getFactionMainJpanel().getFactionCardJpanel().getFactionMemberJpanel();
                String sendMes = Agreement.GangShotAgreement(((LoginResult)factionMemberJpanel.getFactionCardJpanel().getGangResultBean().getRoleTables().get(index)).getRole_id().toString());
                SendMessageUntil.toServer(sendMes);
                factionMemberJpanel.getTableModel().removeRow(index);
                factionMemberJpanel.getFactionCardJpanel().getGangResultBean().getRoleTables().remove(index);
            }
            catch (Exception ex) {}
        }
    }
}
