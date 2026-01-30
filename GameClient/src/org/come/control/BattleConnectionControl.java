package org.come.control;

import com.tool.image.ImageMixDeal;
import come.tool.Fighting.FightingManData;
import java.util.List;
import com.tool.time.TimeLimit;
import org.come.until.Music;
import org.come.until.Util;
import org.come.mouslisten.PetAddPointMouslisten;
import org.come.Jpanel.ZhuJpanel;
import org.come.Frame.ZhuFrame;
import come.tool.Fighting.Statelist;
import org.come.until.GsonUtil;
import come.tool.FightingData.BattleConnection;
import come.tool.Fighting.FightingMixDeal;
import org.come.action.FromServerAction;

public class BattleConnectionControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        FightingMixDeal.State = 0;
        BattleConnection battleConnection = GsonUtil.getGsonUtil().getgson().fromJson(mes, BattleConnection.class);
        if (battleConnection.getFightingNumber() == -1) {
            FightingMixDeal.FightingEnd();
            return;
        }
        FightingMixDeal.BattleType = battleConnection.getBattleType();
        FightingMixDeal.FightingNumber = battleConnection.getFightingNumber();
        FightingMixDeal.CurrentRound = battleConnection.getRound();
        FightingMixDeal.BattlefieldPlay.clear();
        Statelist statelist = new Statelist();
        statelist.setRound(battleConnection.getPlayeEvents());
        FightingMixDeal.BattlefieldPlay.clear();
        FightingMixDeal.BattlefieldPlay.put(Integer.valueOf(battleConnection.getRound()), statelist);
        FightingMixDeal.CorrectTime = 0L;
        FightingMixDeal.systime = System.currentTimeMillis();
        FightingMixDeal.CurrentData(battleConnection.getDatas(), battleConnection.getBuff(), this.getcamp(battleConnection.getDatas()));
        FightingMixDeal.CurrentRound = battleConnection.getRound();
        if (battleConnection.getState() == 0) {
            FightingMixDeal.State = 3;
            FightingMixDeal.time = battleConnection.getTime();
        }
        else if (battleConnection.getState() == 1) {
            FightingMixDeal.State = battleConnection.getEventState();
            FightingMixDeal.time = battleConnection.getTime();
            if (FightingMixDeal.State == 0) {
                FightingMixDeal.State = 3;
            }
        }
        else if (battleConnection.getState() == 2) {
            FightingMixDeal.State = 5;
            for (long pass = battleConnection.getTime(); pass > 0L; pass -= 36L) {
                FightingMixDeal.PalyProgress(36L);
            }
        }
        else if (battleConnection.getState() == 3) {
            FightingMixDeal.State = 6;
        }
        else if (battleConnection.getState() == 4) {
            FightingMixDeal.roundType = 1L;
            FightingMixDeal.State = 5;
            FightingMixDeal.PalyProgress(36L);
        }
        ZhuJpanel zhuJpanel = ZhuFrame.getZhuJpanel();
        ZhuFrame.getZhuJpanel();
        zhuJpanel.add(ZhuJpanel.getZidong());
        ZhuFrame.getZhuJpanel().HideBeastBtn();
        PetAddPointMouslisten.getplayerValue();
        if (FightingMixDeal.State == 1) {
            ZhuFrame.getZhuJpanel().ShowManBtn(FightingMixDeal.isLL());
        }
        else if (FightingMixDeal.State == 2) {
            ZhuFrame.getZhuJpanel().ShowBeastBtn();
        }
        Music.addbeijing("m" + (Util.random.nextInt(4) + 4) + ".mp3");
        if (FightingMixDeal.BattleType == 10 && FightingMixDeal.CurrentRound == 1 && FightingMixDeal.camp == 1) {
            TimeLimit.getLimits().removeLimit(TimeLimit.getLimits().getLimit("杀人香"));
        }
        if (FightingMixDeal.BattleType <= 2) {
            ZhuJpanel zhuJpanel2 = ZhuFrame.getZhuJpanel();
            ZhuFrame.getZhuJpanel();
            zhuJpanel2.add(ZhuJpanel.getqiangtui());
        }
    }
    
    public FightingManData getcamp(List<FightingManData> fightingManDatas) {
        String rolename = ImageMixDeal.userimg.getRoleShow().getRolename();
        for (int i = 0; i < fightingManDatas.size(); ++i) {
            FightingManData data = (FightingManData)fightingManDatas.get(i);
            if (data.getType() == 0 && data.getManname().equals(rolename)) {
                return data;
            }
        }
        return null;
    }
}
