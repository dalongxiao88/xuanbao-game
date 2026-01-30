package come.tool.FightingEffect;

import java.util.Map;
import org.come.bean.ConfigureBean;
import org.come.Jpanel.FrameMessageChangeJpanel;
import org.come.socket.SendMessageUntil;
import org.come.until.GsonUtil;
import org.come.socket.Agreement;
import org.come.bean.SummonPetBean;
import java.math.BigDecimal;
import org.come.model.Configure;
import org.come.until.UserMessUntil;
import come.tool.Fighting.FightingMixDeal;
import org.come.Frame.ZhuFrame;
import come.tool.Fighting.SkillSpell;
import org.come.until.ScrenceUntil;
import com.tool.tcp.GetTcpPath;
import come.tool.Fighting.StateProgress;
import come.tool.Fighting.FightingState;

public class PetCatch implements Effect
{
    @Override
    public StateProgress analysisAction(FightingState State, int path) {
        StateProgress Active = EffectType.getProgress(State, path);
        SkillSpell skillSpell = new SkillSpell(GetTcpPath.getSkillTcp("捕捉"), ScrenceUntil.Screen_x / 2, ScrenceUntil.Screen_y / 2);
        skillSpell.setSkinpath(1);
        Active.setSpell(skillSpell);
        Active.setType(7);
        if (State.getStartState().equals("捕捉失败")) {
            ZhuFrame.getZhuJpanel().addPrompt2("很遗憾，捕捉失败！");
        }
        else {
            Active.setEscape(1);
            String[] v = State.getEndState().split("\\|");
            int camp = Integer.parseInt(v[0]);
            int man = Integer.parseInt(v[1]);
            int myman = FightingMixDeal.myman();
            if (camp == FightingMixDeal.camp && myman == man) {
                ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
                Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
                Configure configure = (Configure)item.get(new BigDecimal(1));
                if (UserMessUntil.getPetListTable() != null && UserMessUntil.getPetListTable().size() >= Integer.parseInt(configure.getXdzhssx())) {
                    ZhuFrame.getZhuJpanel().addPrompt2("您的召唤兽可携带的数量已满！！！");
                    return Active;
                }
                SummonPetBean summonPetBean = new SummonPetBean();
                summonPetBean.setPetid(new BigDecimal(v[2]));
                String mes = Agreement.getAgreement().summonpetAgreement(GsonUtil.getGsonUtil().getgson().toJson(summonPetBean));
                SendMessageUntil.toServer(mes);
                FrameMessageChangeJpanel.addtext(5, "恭喜：成功捕获了召唤兽", null, null);
            }
        }
        return Active;
    }
}
