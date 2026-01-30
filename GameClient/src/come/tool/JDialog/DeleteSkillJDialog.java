package come.tool.JDialog;

import org.come.Jpanel.PetSkillsJpanel;
import org.come.socket.SendMessageUntil;
import org.come.until.UserMessUntil;
import org.come.socket.Agreement;
import org.come.Frame.PetSkillsJframe;

public class DeleteSkillJDialog implements TiShiChuLi
{
    @Override
    public void tipBox(boolean tishi, Object object) {
        int type = (int)object;
        if (tishi) {
            PetSkillsJpanel petSkillsJpanel = PetSkillsJframe.getPetSkillsJframe().getPetSkillsJpanel();
            String sendmes = Agreement.getAgreement().userpetAgreement("PS|" + UserMessUntil.getChosePetMes().getSid() + "|" + petSkillsJpanel.getPetskillID());
            SendMessageUntil.toServer(sendmes);
            petSkillsJpanel.setPetskillID("");
            petSkillsJpanel.getLabPetskills()[petSkillsJpanel.getPetskillNO()].setIcon(null);
            petSkillsJpanel.getShowPetSkills()[petSkillsJpanel.getPetskillNO()].setSkill(null);
            PetSkillsJframe.getPetSkillsJframe().getPetSkillsJpanel().getBox().setText(null);
        }
    }
}
