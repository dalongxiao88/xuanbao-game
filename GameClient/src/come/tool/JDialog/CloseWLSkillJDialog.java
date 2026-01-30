package come.tool.JDialog;

import org.come.Jpanel.WuLingPanel;
import org.come.socket.Agreement;
import org.come.socket.SendMessageUntil;
import org.come.until.UserMessUntil;

public class CloseWLSkillJDialog implements TiShiChuLi {
    public void tipBox(boolean tishi, Object object) {
        if (tishi) {
            final String sendmes = Agreement.getAgreement().userpetAgreement("CLOSEWL|" + UserMessUntil.getChosePetMes().getSid() + "|" + WuLingPanel.skill.getSkillid());
            SendMessageUntil.toServer(sendmes);
        }

    }
}
