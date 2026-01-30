package org.come.good;

import org.come.mouslisten.GoodsMouslisten;
import org.come.npc.TP;
import org.come.Frame.NPCJfram;
import org.come.until.Util;
import com.tool.ModerateTask.TaskMixDeal;
import org.come.Frame.ZhuFrame;
import com.tool.image.ImageMixDeal;
import org.come.entity.Goodstable;

public class Flight
{
    public static void Flightchess(Goodstable goodstable, long type) {
        if (ImageMixDeal.userimg.getRoleShow().getBooth_id() != null) {
            ZhuFrame.getZhuJpanel().addPrompt2("摆摊状态不可以移动！");
            return;
        }
        if (!TaskMixDeal.istp()) {
            ZhuFrame.getZhuJpanel().addPrompt2("传送限制");
        }
        else {
            String flag = Util.mapmodel.getGamemap().getMapflag();
            if (flag != null && flag.equals("1") && ImageMixDeal.userimg.getTeams() != null) {
                if (goodstable.getValue() != null && !goodstable.getValue().equals("")) {
                    NPCJfram.getNpcJfram().getNpcjpanel().FlightMsgS(goodstable);
                }
                else {
                    int sum = 0;
                    if (type == 2010L) {
                        sum = 10;
                    }
                    else if (type == 2011L) {
                        sum = 1;
                    }
                    else if (type == 2012L) {
                        sum = 99;
                    }
                    TP.FlightRedraw(goodstable, sum);
                    GoodsMouslisten.gooduse(goodstable, 2);
                }
            }
            else {
                ZhuFrame.getZhuJpanel().addPrompt2("飞行棋使用限制");
            }
        }
    }
}
