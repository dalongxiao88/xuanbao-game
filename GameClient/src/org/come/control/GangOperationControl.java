package org.come.control;

import org.come.Jpanel.RewardHallJpanel;
import org.come.until.FormsManagement;
import java.math.BigDecimal;
import org.come.Frame.RewardHallJframe;
import com.tool.role.RoleData;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.Frame.ZhuFrame;
import org.come.action.NpcMenuAction;

public class GangOperationControl implements NpcMenuAction
{
    @Override
    public void menuControl(String type) {
        if (type.equals("我要抽奖")) {
            ZhuFrame.getZhuJpanel().addPrompt2("还未开放");
            if (type.equals("我要抽奖")) {
                return;
            }
            String senmes = null;
            try {
                senmes = Agreement.getAgreement().obtainarticleAgreement();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            SendMessageUntil.toServer(senmes);
            BigDecimal decimal = RoleData.getRoleData().getLoginResult().getScoretype("帮派积分");
            RewardHallJpanel jpane = RewardHallJframe.getRewardHallJframe().getRewardHallJpanel();
            jpane.setTotalsum(decimal);
            jpane.setLotteryNum(new BigDecimal(decimal.divide(jpane.getUnitprice()).intValue()));
            FormsManagement.showForm(59);
        }
        else if (type.equals("我要投放功绩物品")) {
            ZhuFrame.getZhuJpanel().addPrompt2("还未开放");
        }
    }
}
