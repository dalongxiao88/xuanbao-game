package org.come.control;

import java.io.IOException;
import org.come.mouslisten.PetAddPointMouslisten;
import org.come.until.Article;
import org.come.Frame.ZhuFrame;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.until.GsonUtil;
import org.come.bean.NpcCureBean;
import org.come.until.UserData;
import java.math.BigDecimal;
import com.tool.pet.PetProperty;
import org.come.entity.RoleSummoning;
import com.tool.image.ImageMixDeal;
import org.come.Jpanel.FrameMessageChangeJpanel;
import org.come.until.UserMessUntil;
import org.come.action.NpcMenuAction;

public class RoleSumminCloseControl implements NpcMenuAction
{
    @Override
    public void menuControl(String type) {
        if (type.equals("治疗")) {
            this.cureRolesumming();
        }
        else if (type.equals("治疗所有")) {
            this.cureAllPet();
        }
        else {
            this.getRolesummingFaithFful();
        }
    }
    
    public void getRolesummingFaithFful() {
        if (UserMessUntil.getChosePetMes() != null) {
            FrameMessageChangeJpanel.addtext(6, "您召唤兽的亲密度是" + UserMessUntil.getChosePetMes().getFriendliness(), null, null);
            ImageMixDeal.userimg.Dialogue("您召唤兽的亲密度是" + UserMessUntil.getChosePetMes().getFriendliness());
        }
        else {
            FrameMessageChangeJpanel.addtext(6, "您没有携带召唤兽！！", null, null);
            ImageMixDeal.userimg.Dialogue("您没有携带召唤兽！！");
        }
    }
    
    public void cureRolesumming() {
        if (UserMessUntil.getChosePetMes() != null) {
            try {
                this.addPetFathiful(UserMessUntil.getChosePetMes());
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            FrameMessageChangeJpanel.addtext(6, "您没有携带召唤兽！！", null, null);
            ImageMixDeal.userimg.Dialogue("您没有携带召唤兽！！");
        }
    }
    
    public void addPetFathiful(RoleSummoning roleSummoning) throws IOException {
        int[] pets = PetProperty.getPetHMASp(roleSummoning);
        long petFaiful = (long)(int)roleSummoning.getFaithful();
        long petHp = roleSummoning.getBasishp();
        long sumhp = (long)pets[0];
        long petMp = roleSummoning.getBasismp();
        int summp = pets[1];
        BigDecimal sumMoney = new BigDecimal((sumhp - petHp + ((long)summp - petMp) + (100L - petFaiful)) / 15L);
        if (petMp < (long)summp || petHp < sumhp || petFaiful != 100L) {
            if (sumMoney.longValue() <= 0L) {
                sumMoney = new BigDecimal(10000);
            }
            if (UserData.uptael(sumMoney.longValue())) {
                roleSummoning.setFaithful(Integer.valueOf(100));
                roleSummoning.setBasishp(sumhp);
                roleSummoning.setBasismp((long)summp);
                NpcCureBean npcCureBean = new NpcCureBean();
                npcCureBean.setRoleSummoning(roleSummoning);
                UserMessUntil.setChosePetMes(roleSummoning);
                String messString = Agreement.npccureAgreement(GsonUtil.getGsonUtil().getgson().toJson(npcCureBean));
                SendMessageUntil.toServer(messString);
                ZhuFrame.getZhuJpanel().addPrompt2("您的召唤兽忠诚度和血量已经回复了。。");
                ZhuFrame.getZhuJpanel().addPrompt2("您扣除了" + sumMoney + "两金币！！");
                Article.souxie(UserMessUntil.getChosePetMes());
                PetAddPointMouslisten.showPetValue();
            }
            else {
                ZhuFrame.getZhuJpanel().addPrompt2("您没有足够的金钱修复您的召唤兽忠诚和血量！！");
            }
        }
    }
    
    public void cureAllPet() {
        if (UserMessUntil.getPetListTable().size() > 0) {
            for (int i = 0; i < UserMessUntil.getPetListTable().size(); ++i) {
                try {
                    this.addPetFathiful((RoleSummoning)UserMessUntil.getPetListTable().get(i));
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        else {
            ZhuFrame.getZhuJpanel().addPrompt2("你还没有召唤兽！！！");
        }
    }
}
