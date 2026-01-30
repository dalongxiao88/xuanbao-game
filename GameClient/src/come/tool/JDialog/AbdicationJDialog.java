package come.tool.JDialog;

import org.come.Jpanel.FactionCardJpanel;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.bean.LoginResult;
import org.come.Frame.FactionMainJframe;

public class AbdicationJDialog implements TiShiChuLi
{
    @Override
    public void tipBox(boolean tishi, Object object) {
        int index = (int)object;
        if (tishi) {
            try {
                FactionCardJpanel factionCardJpanel = FactionMainJframe.getFactionMainJframe().getFactionMainJpanel().getFactionCardJpanel();
                ((LoginResult)factionCardJpanel.getGangResultBean().getRoleTables().get(index)).setGangpost("帮主");
                String sendMes = Agreement.GangChangeAgreement(((LoginResult)factionCardJpanel.getGangResultBean().getRoleTables().get(index)).getRole_id().toString());
                SendMessageUntil.toServer(sendMes);
            }
            catch (Exception ex) {}
        }
    }
}
