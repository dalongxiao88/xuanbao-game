package org.come.control;

import org.come.entity.Goodstable;
import org.come.until.UserData;
import java.math.BigDecimal;
import com.tool.role.RoleData;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.mouslisten.GoodsMouslisten;
import org.come.Frame.ZhuFrame;
import org.come.until.GoodsListFromServerUntil;
import org.come.action.NpcMenuAction;

public class GiveAHandcontrol implements NpcMenuAction
{
    @Override
    public void menuControl(String type) {
        if (type.equals("我来助你一孵之力（需提交一个物品）")) {
            Goodstable goodstable = null;
            for (int i = 0; i < GoodsListFromServerUntil.getGoodslist().length; ++i) {
                if (GoodsListFromServerUntil.getGoodslist()[i] != null && (long)GoodsListFromServerUntil.getGoodslist()[i].getType() == 2327L) {
                    goodstable = GoodsListFromServerUntil.getGoodslist()[i];
                    break;
                }
            }
            if (goodstable == null) {
                ZhuFrame.getZhuJpanel().addPrompt("没有灵兽蛋精华");
                return;
            }
            goodstable.setUsetime(Integer.valueOf(0));
            GoodsMouslisten.gooduse(goodstable, 1);
            GoodsListFromServerUntil.Deletebiaoid(goodstable.getRgid());
            SendMessageUntil.toServer(Agreement.getAgreement().HatchaddAgreement(""));
        }
        else if (type.equals("直接打开")) {
            Goodstable goodstable = null;
            for (int i = 0; i < GoodsListFromServerUntil.getGoodslist().length; ++i) {
                if (GoodsListFromServerUntil.getGoodslist()[i] != null && (long)GoodsListFromServerUntil.getGoodslist()[i].getType() == 506L) {
                    goodstable = GoodsListFromServerUntil.getGoodslist()[i];
                    break;
                }
            }
            if (goodstable == null) {
                return;
            }
        }
        else if (type.equals("祈福（500W金币）")) {
            if (RoleData.getRoleData().getLoginResult().getGold().longValue() < 5000000L) {
                ZhuFrame.getZhuJpanel().addPrompt("金币不足");
                return;
            }
            Goodstable goodstable = null;
            for (int i = 0; i < GoodsListFromServerUntil.getGoodslist().length; ++i) {
                if (GoodsListFromServerUntil.getGoodslist()[i] != null && (long)GoodsListFromServerUntil.getGoodslist()[i].getType() == 506L) {
                    goodstable = GoodsListFromServerUntil.getGoodslist()[i];
                    break;
                }
            }
            if (goodstable == null) {
                return;
            }
            UserData.uptael(new BigDecimal(5000000).longValue());
        }
    }
}
