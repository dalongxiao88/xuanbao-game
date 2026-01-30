package come.tool.JDialog;

import org.come.entity.Goodstable;
import org.come.Jpanel.PetSkillsJpanel;
import org.come.bean.Skill;
import javax.swing.ImageIcon;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.Frame.ZhuFrame;
import org.come.until.GoodsListFromServerUntil;
import org.come.Frame.PetSkillsJframe;
import com.tool.btn.DeleteSkillBtn;
import org.come.until.UserMessUntil;

public class PracticeJDialog implements TiShiChuLi
{
    @Override
    public void tipBox(boolean tishi, Object object) {
        if (UserMessUntil.getChosePetMes() != DeleteSkillBtn.spet) {
            return;
        }
        Skill skill = UserMessUntil.getSkillId("3034");
        PetSkillsJpanel petSkillsJpanel = PetSkillsJframe.getPetSkillsJframe().getPetSkillsJpanel();
        if (tishi) {
            Goodstable goodstable = null;
            int i = 0;
            while (i < GoodsListFromServerUntil.getGoodslist().length) {
                if (GoodsListFromServerUntil.getGoodslist()[i] != null && (long)GoodsListFromServerUntil.getGoodslist()[i].getType() == 925L) {
                    goodstable = GoodsListFromServerUntil.getGoodslist()[i];
                    break;
                }
                else {
                    ++i;
                }
            }
            if (goodstable == null) {
                ZhuFrame.getZhuJpanel().addPrompt("你的包裹里没有#G终极技能修炼丹");
            }
            else {
                int xhSum = DeleteSkillBtn.getXHSum(UserMessUntil.getChosePetMes(), PetSkillsJframe.getPetSkillsJframe().getPetSkillsJpanel().getPetskillID());
                if ((int)goodstable.getUsetime() < xhSum) {
                    ZhuFrame.getZhuJpanel().addPrompt("重新修炼该技能需要" + xhSum + "个终极重修丹！！！");
                    return;
                }
                String sendmes = Agreement.getAgreement().userpetAgreement("CX|" + UserMessUntil.getChosePetMes().getSid() + "|" + petSkillsJpanel.getPetskillID());
                SendMessageUntil.toServer(sendmes);
                petSkillsJpanel.setPetskillID("");
                petSkillsJpanel.getLabPetskills()[petSkillsJpanel.getPetskillNO()].setIcon(null);
                petSkillsJpanel.getShowPetSkills()[petSkillsJpanel.getPetskillNO()].setSkill(null);
                PetSkillsJframe.getPetSkillsJframe().getPetSkillsJpanel().getBox().setText(null);
                ImageIcon icon = new ImageIcon("img/fighting-skill/wxs_" + skill.getSkillid() + ".png");
                PetSkillsJpanel petSkillsJpanelx = PetSkillsJframe.getPetSkillsJframe().getPetSkillsJpanel();
                String sendmesx = Agreement.getAgreement().userpetAgreement("TJJ|" + UserMessUntil.getChosePetMes().getSid() + "|" + 3034);
                SendMessageUntil.toServer(sendmesx);
                petSkillsJpanelx.getShowPetSkills()[petSkillsJpanelx.getPetskillNO()].setSkill(skill);
                petSkillsJpanelx.getLabPetskills()[petSkillsJpanelx.getPetskillNO()].setIcon(icon);
            }
        }
    }
}
