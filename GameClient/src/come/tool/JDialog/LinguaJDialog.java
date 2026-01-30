package come.tool.JDialog;

import org.come.Frame.AlchemyJframe;
import org.come.Frame.PetSkillsJframe;
import org.come.Frame.ZhuFrame;
import org.come.Jpanel.PetSkillsJpanel;
import org.come.Jpanel.SpiritualJpanel;
import org.come.Jpanel.ZhuJpanel;
import org.come.entity.Goodstable;
import org.come.entity.RoleSummoning;
import org.come.socket.Agreement;
import org.come.socket.SendMessageUntil;
import org.come.until.GoodsListFromServerUntil;
import org.come.until.UserMessUntil;

public class LinguaJDialog implements TiShiChuLi
{
    @Override
    public void tipBox(final boolean tishi, final Object object) {
        final PetSkillsJpanel petSkillsJpanel = PetSkillsJframe.getPetSkillsJframe().getPetSkillsJpanel();
        final SpiritualJpanel ss = AlchemyJframe.getAlchemyjframe().getAlchemyCardJpanel().getAlchemyMainJpanel().getSpiritualJpanel();
        final int index = ss.getListpet().getSelectedIndex();
        final RoleSummoning pet = (RoleSummoning)UserMessUntil.getPetListTable().get(index);
        if (pet.getFriendliness()<200000) {
            ZhuFrame.getZhuJpanel().addPrompt2("召唤兽亲密不够20万");
            return;
        }
        if (ss.getPetskillID().equals("3034") || ss.getPetskillID().equals("1606") || ss.getPetskillID().equals("1607") || ss.getPetskillID().equals("1608") || ss.getPetskillID().equals("1828") || ss.getPetskillID().equals("1829") || ss.getPetskillID().equals("1830") || ss.getPetskillID().equals("1841") || ss.getPetskillID().equals("1842") || ss.getPetskillID().equals("1867") || ss.getPetskillID().equals("1868") || ss.getPetskillID().equals("1869")) {
            ZhuFrame.getZhuJpanel().addPrompt("终极技能无法灵返");
            return;
        }
        else {
            if (ss.getPetskillID().equals("1600") || ss.getPetskillID().equals("1601") || ss.getPetskillID().equals("1602") || ss.getPetskillID().equals("1603")
                    || ss.getPetskillID().equals("1604") || ss.getPetskillID().equals("1611") || ss.getPetskillID().equals("1612") || ss.getPetskillID().equals("1820")
                    || ss.getPetskillID().equals("1822") || ss.getPetskillID().equals("1831") || ss.getPetskillID().equals("1833") || ss.getPetskillID().equals("1834")
                    || ss.getPetskillID().equals("1835") || ss.getPetskillID().equals("1836") || ss.getPetskillID().equals("1838") || ss.getPetskillID().equals("1839")
                    || ss.getPetskillID().equals("1850") || ss.getPetskillID().equals("1852") || ss.getPetskillID().equals("1854") || ss.getPetskillID().equals("1858")
                    || ss.getPetskillID().equals("1887") || ss.getPetskillID().equals("1872") || ss.getPetskillID().equals("1873") || ss.getPetskillID().equals("1874")
                    || ss.getPetskillID().equals("1878") || ss.getPetskillID().equals("1880") || ss.getPetskillID().equals("1887") || ss.getPetskillID().equals("1888")
                    || ss.getPetskillID().equals("1889")) {
                final String sendmes = Agreement.getAgreement().userpetAgreement("PS|" + UserMessUntil.getChosePetMes().getSid() + "|" + ss.getPetskillID());
                SendMessageUntil.toServer(sendmes);
                petSkillsJpanel.setPetskillID("");
                petSkillsJpanel.getLabPetskills()[petSkillsJpanel.getPetskillNO()].setIcon(null);
                petSkillsJpanel.getShowPetSkills()[petSkillsJpanel.getPetskillNO()].setSkill(null);
                PetSkillsJframe.getPetSkillsJframe().getPetSkillsJpanel().getBox().setText(null);

                final Goodstable goodstable = ZhuJpanel.getGoodstableAlf();
                if (goodstable.getUsetime().intValue() <= 0) {
                    GoodsListFromServerUntil.Deletebiaoid(goodstable.getRgid());
                }
                ZhuJpanel.setGoodstableAlf(null);
                ss.getLabRefined().setIcon(null);
                final String sendmesx = Agreement.getAgreement().userpetAgreement(goodstable.getRgid().toString() + "|" + pet.getSid() + "|" + ss.getPetskillID());
                SendMessageUntil.toServer(sendmesx);
                ZhuFrame.getZhuJpanel().addPrompt("#Y恭喜你，灵返成功!");
                ss.setPetskillID("");
                ss.getLabPetskills()[ss.getPetskillNO()].setIcon(null);
                ss.getShowPetSkills()[ss.getPetskillNO()].setSkill(null);
            }
            else {
                ZhuFrame.getZhuJpanel().addPrompt("获取技能出错，请重新选择");
            }
            return;
        }
    }
}
