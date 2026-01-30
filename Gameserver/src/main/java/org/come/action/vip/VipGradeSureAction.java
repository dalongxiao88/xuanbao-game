package org.come.action.vip;

import java.math.BigDecimal;
import java.util.List;
import come.tool.Good.DropUtil;
import org.come.bean.LoginResult;
import org.come.entity.PayvipBean;
import org.come.server.GameServer;
import org.come.until.VipRewardUtils;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class VipGradeSureAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        if (message == null || "".equals(message)) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("操作错误"));
            return;
        }
        String[] condition = VipRewardUtils.gradeAndMoney(message);
        Integer paynum = Integer.valueOf(1000000);
        List<PayvipBean> payvipList = GameServer.getPayvipList();
        int i = 0;
        while (i < payvipList.size()) {
            if (condition[0].equals(((PayvipBean)payvipList.get(i)).getGrade() + "")) {
                paynum = ((PayvipBean)payvipList.get(i)).getPaynum();
                break;
            }
            else {
                ++i;
            }
        }
        LoginResult roleInfo = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (roleInfo == null) {
            return;
        }
        BigDecimal paysum = roleInfo.getPaysum();
        if (paysum.doubleValue() < (double)Double.valueOf((double)(int)paynum)) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("您当前不满足领取条件!"));
            return;
        }
        String roleVipInfo = roleInfo.getVipget();
        if (VipRewardUtils.checkYesOrNo(roleVipInfo, "1", condition[0])) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("您已经领取该礼包!"));
            return;
        }
        StringBuffer vipInfo = new StringBuffer();
        VipRewardUtils.setVipget(roleInfo, "1", condition[0] + "");
        vipInfo.append(roleInfo.getVipget());
        int j = 0;
        while (j < payvipList.size()) {
            if (condition[0].equals(((PayvipBean)payvipList.get(j)).getGrade() + "")) {
                String givegoods = ((PayvipBean)payvipList.get(j)).getGivegoods();
                DropUtil.getDrop(roleInfo, givegoods, "", 26, 1.0, vipInfo.toString());
                break;
            }
            else {
                ++j;
            }
        }
    }
}
