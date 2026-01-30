package come.tool.handle;

import org.come.bean.RoleShow;
import org.come.until.FormsManagement;
import com.tool.image.ImageMixDeal;
import java.util.List;
import org.come.Frame.ZhuFrame;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.until.GsonUtil;
import java.util.ArrayList;
import come.tool.Fighting.SkillSpell;
import come.tool.Fighting.StateProgress;
import come.tool.Fighting.Fightingimage;
import come.tool.Fighting.FightingEvents;
import come.tool.Fighting.FightingMixDeal;
import come.tool.Fighting.Statelist;

public class HandlePlay implements Handle
{
    @Override
    public void handle(long DieTime) {
        boolean end = true;
        Statelist statelist = (Statelist)FightingMixDeal.BattlefieldPlay.get(Integer.valueOf(FightingMixDeal.CurrentRound));
        if (statelist == null) {
            FightingMixDeal.qingchusiwang();
            FightingMixDeal.changeState(6);
            return;
        }
        if (statelist.getProgress() < statelist.getRound().size()) {
            if (statelist.getState() == null) {
                statelist.setState(FightingMixDeal.AnalysisFightingEvents((FightingEvents)statelist.getRound().get(statelist.getProgress()), statelist.getProgress()));
            }
            List<StateProgress> list = statelist.getState();
            try {
                for (int i = list.size() - 1; i >= 0; --i) {
                    Fightingimage man = (Fightingimage)FightingMixDeal.CurrentData.get(((StateProgress)list.get(i)).getMan());
                    if (man.zhixnig((StateProgress)list.get(i))) {
                        end = false;
                    }
                }
                int i = FightingMixDeal.skills.size() - 1;
                while (i >= 0) {
                    if (((SkillSpell)FightingMixDeal.skills.get(i)).getShadowMode() != null) {
                        end = false;
                        break;
                    }
                    else {
                        --i;
                    }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            if (end) {
                statelist.setProgress(statelist.getProgress() + 1);
                statelist.setState(null);
                FightingMixDeal.Music1 = "";
                List<Fightingimage> datas = null;
                for (int j = list.size() - 1; j >= 0; --j) {
                    try {
                        Fightingimage mandata = (Fightingimage)FightingMixDeal.CurrentData.get(((StateProgress)list.get(j)).getMan());
                        if (((StateProgress)list.get(j)).getEscape() == 1) {
                            if (datas == null) {
                                datas = new ArrayList<>();
                            }
                            datas.add(mandata);
                        }
                    }
                    catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                if (datas != null) {
                    for (int j = datas.size() - 1; j >= 0; --j) {
                        if (this.OutFight((Fightingimage)datas.get(j))) {
                            return;
                        }
                    }
                }
            }
        }
        if (statelist.getProgress() >= statelist.getRound().size()) {
            if (FightingMixDeal.roundType == 0L) {
                FightingMixDeal.qingchusiwang();
                FightingMixDeal.changeState(6);
                FightingEvents fightingEvents = new FightingEvents();
                fightingEvents.setFightingsum(FightingMixDeal.FightingNumber);
                fightingEvents.setCurrentRound(FightingMixDeal.CurrentRound);
                String sendMes = Agreement.FightingRoundEndAgreement(GsonUtil.getGsonUtil().getgson().toJson(fightingEvents));
                SendMessageUntil.toServer(sendMes);
            }
            else {
                FightingMixDeal.State = 1;
                ZhuFrame.getZhuJpanel().ShowManBtn(FightingMixDeal.isLL());
            }
        }
        else if (end && FightingMixDeal.State == 5) {
            this.handle(0L);
        }
    }
    
    public boolean OutFight(Fightingimage mandata) {
        int type = mandata.getFightingManData().getType();
        if (type == 0 && mandata.getFightingManData().getManname().equals(ImageMixDeal.userimg.getRoleShow().getRolename())) {
            try {
                this.jieli();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            FightingMixDeal.FightingEnd(null);
            return true;
        }
        else {
            int camp = mandata.getFightingManData().getCamp();
            int man = mandata.getFightingManData().getMan();
            if (type == 0) {
                FightingMixDeal.depath(camp, man);
                FightingMixDeal.depath(camp, man + 5);
                FightingMixDeal.depath(camp, man + 10);
                FightingMixDeal.depath(camp, man + 15);
            }
            else {
                FightingMixDeal.depath(camp, man);
            }
            return false;
        }
    }
    
    public void jieli() throws Exception {
        RoleShow roleShow = ImageMixDeal.userimg.getRoleShow();
        if (roleShow.getTroop_id() == null) {
            return;
        }
        if (ImageMixDeal.userimg.getTeams() != null && roleShow.getCaptian() != 1) {
            return;
        }
        String sendmes = Agreement.getAgreement().team5Agreement("D");
        SendMessageUntil.toServer(sendmes);
        FormsManagement.HideForm(13);
    }
}
