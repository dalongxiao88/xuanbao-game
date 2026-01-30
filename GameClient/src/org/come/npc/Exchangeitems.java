package org.come.npc;

import java.util.List;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.until.GsonUtil;
import org.come.bean.SuitOperBean;
import org.come.Frame.ZhuFrame;
import org.come.Jpanel.FrameMessageChangeJpanel;
import org.come.until.GoodsListFromServerUntil;
import org.come.entity.PartJade;
import org.come.action.NpcMenuAction;

public class Exchangeitems implements NpcMenuAction
{
    @Override
    public void menuControl(String type) {
        // TODO Auto-generated method stub

        try {
            String name = type;
            PartJade partJade = null;
            List<Integer> integers = null;
            int[] goodids = null;
            switch (name) {

                case "兑换超级龙之骨":
                    partJade=new PartJade(0,92381);
                    goodids = new int[3];
                    for (int i = 0; i < 3; i++) {
                        goodids[i] = 188;
                    }
                    integers = GoodsListFromServerUntil.chaxuns(goodids);
                    break;
                case "兑换高级聚魄丹":
                    partJade=new PartJade(0,92383);
                    goodids = new int[3];
                    for (int i = 0; i < 3; i++) {
                        goodids[i] = 772;
                    }
                    integers = GoodsListFromServerUntil.chaxuns(goodids);
                    break;
                default:
                    break;
            }
            if (partJade == null || integers == null||goodids == null) {
                FrameMessageChangeJpanel.addtext(6, name + " 数量不对需要3个", null, null);
                return;
            }
            else if (partJade.getSuitid() == 0) {
                // 先判断背包是否还有空位、
                int packNumber = GoodsListFromServerUntil.Surplussum("-1", "-1", 999);
                if (packNumber <= 0) {
                    ZhuFrame.getZhuJpanel().addPrompt2("背包已满");
                    return;
                }
            }
            SuitOperBean operBean = new SuitOperBean();
            operBean.setType(21);
            operBean.setJade(partJade);
            operBean.setGoods(GoodsListFromServerUntil.Delete2(integers));
            String senmes = Agreement.suitOperateAgreement(GsonUtil.getGsonUtil().getgson().toJson(operBean));
            SendMessageUntil.toServer(senmes);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
