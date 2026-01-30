package org.come.control;

import come.tool.Fighting.FightingMixDeal;
import org.come.Frame.ZhuFrame;
import come.tool.Fighting.Fightingimage;
import org.come.until.GsonUtil;
import come.tool.Fighting.FightingRevocation;
import org.come.action.FromServerAction;

public class MovedControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        try {
            if (mes == null || mes.equals("")) {
                return;
            }
            FightingRevocation fightingRevocation = (FightingRevocation)GsonUtil.getGsonUtil().getgson().fromJson(mes, FightingRevocation.class);
            if ((int)fightingRevocation.getType() == 3) {
                Fightingimage.moved.add(Integer.valueOf((int)fightingRevocation.getMan()));
            }
            else {
                int i = ((int)fightingRevocation.getType() == 0) ? 1 : 2;
                if ((int)fightingRevocation.getType() == 0) {
                    ZhuFrame.getZhuJpanel().ShowManBtn(FightingMixDeal.isLL());
                    FightingMixDeal.changeState(1);
                    ZhuFrame.getZhuJpanel().HideBeastBtn();
                    ZhuFrame.getZhuJpanel().ShowBeastBtn();
                    if (FightingMixDeal.camp != -1) {
                        ZhuFrame.getZhuJpanel().ShowManBtn(FightingMixDeal.isLL());
                        FightingMixDeal.changeState(FightingMixDeal.State = i);
                    }
                    else {
                        FightingMixDeal.changeState(FightingMixDeal.State = 3);
                    }
                }
                else if ((int)fightingRevocation.getType() == 1) {
                    FightingMixDeal.changeState(FightingMixDeal.State = i);
                    ZhuFrame.getZhuJpanel().HideBeastBtn();
                    ZhuFrame.getZhuJpanel().ShowBeastBtn();
                }
                int i2 = Fightingimage.moved.indexOf(fightingRevocation.getMan());
                Fightingimage.moved.remove(i2);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
