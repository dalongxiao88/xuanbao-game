package come.tool.FightingEffect;

import org.come.model.Lingbao;
import org.come.entity.RoleSummoning;
import come.tool.Fighting.FightingManData;
import com.tool.tcp.GetTcpPath;
import come.tool.Fighting.SkillSpell;
import come.tool.Fighting.Fightingimage;
import java.math.BigDecimal;
import com.tool.role.RoleLingFa;
import org.come.Jpanel.TestPetJpanel;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import com.tool.role.RoleData;
import com.tool.PanelDisplay.PetPanelShow;
import org.come.until.UserMessUntil;
import org.come.until.Article;
import org.come.Jpanel.ZhuJpanel;
import come.tool.Fighting.FightingMixDeal;
import come.tool.Fighting.StateProgress;
import come.tool.Fighting.FightingState;

public class Pet implements Effect
{
    @Override
    public StateProgress analysisAction(FightingState State, int path) {
        if ("-1|-1".equals(State.getEndState())) {
            return null;
        }
        if (State.getStartState().equals("召回")) {
            FightingMixDeal.depath(State.getCamp(), State.getMan());
            FightingMixDeal.buffUtil.CS(State.getBuff(), FightingMixDeal.camp);
            if (State.getCamp() == FightingMixDeal.camp) {
                if (State.getMan() == FightingMixDeal.myman() + 5) {
                    ZhuJpanel.setLabpetimg(null);
                }
                else if (State.getMan() == FightingMixDeal.myman() + 10) {
                    ZhuJpanel.setLabLingImg(null);
                }
            }
            return null;
        }
        else {
            FightingManData bb = State.getFightingManData();
            int type = bb.getType();
            if (type == 1) {
                int myman = FightingMixDeal.myman() + 5;
                if (bb.getCamp() == FightingMixDeal.camp && bb.getMan() == myman) {
                    RoleSummoning pet = Article.bb(bb.getId());
                    if (pet != null) {
                        UserMessUntil.setChosePetMes(pet);
                        ZhuJpanel.setLabpetimg("img/head/p" + pet.getSummoningskin() + ".png");
                        Article.souxie(pet);
                        PetPanelShow.ShowMesForJpanel();
                        if (!State.getStartState().equals("闪现") && RoleData.getRoleData().getLoginResult().getScoretype("首发").intValue() == 0) {
                            String mes = Agreement.getAgreement().rolechangeAgreement("P" + pet.getSid().toString());
                            SendMessageUntil.toServer(mes);
                            RoleData.getRoleData().getLoginResult().setSummoning_id(pet.getSid());
                            TestPetJpanel.showStar();
                        }
                    }
                    else {
                        ZhuJpanel.setLabpetimg(null);
                    }
                }
            }
            else if (type == 3) {
                int myman = FightingMixDeal.myman() + 10;
                if (bb.getCamp() == FightingMixDeal.camp && bb.getMan() == myman) {
                    Lingbao lingBao = RoleLingFa.getRoleLingFa().getLingBao(BigDecimal.valueOf((long)bb.getId()));
                    if (lingBao != null) {
                        ZhuJpanel.setLabLingImg(lingBao.getSkin());
                    }
                    else {
                        ZhuJpanel.setLabLingImg(null);
                    }
                }
            }
            FightingMixDeal.depath(bb.getCamp(), bb.getMan());
            Fightingimage fightingimage = new Fightingimage(bb, FightingMixDeal.camp);
            fightingimage.addTime(2000L);
            FightingMixDeal.CurrentData.add(fightingimage);
            path = FightingMixDeal.CurrentData.size() - 1;
            StateProgress progress = EffectType.getProgress(State, path);
            if (State.getStartState().equals("闪现")) {
                SkillSpell skillSpell = new SkillSpell();
                skillSpell = new SkillSpell(GetTcpPath.getSkillTcp("1806"), fightingimage.getX(), fightingimage.getY());
                progress.setSpell(skillSpell);
            }
            if ("闪现".equals(progress.getData2())) {
                progress.setData2("召唤");
            }
            else if ("召唤灵宝".equals(progress.getData2())) {
                progress.setData2("召唤");
            }
            return progress;
        }
    }
}
