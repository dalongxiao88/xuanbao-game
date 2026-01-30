package org.come.action.vip;

import come.tool.Good.DropUtil;
import java.math.BigDecimal;
import org.come.entity.ChongjipackBean;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class DayForOneSureAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult roleInfo = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        BigDecimal daygetorno = roleInfo.getDaygetorno();
        if (daygetorno.intValue() == 1) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("您已经购买过该礼包!"));
            return;
        }
        List<ChongjipackBean> chongList = (List<ChongjipackBean>)GameServer.getPackgradecontrol().get(Integer.valueOf(6));
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(d);
        for (int i = 0; i < chongList.size(); ++i) {
            if (format.equals(((ChongjipackBean)chongList.get(i)).getHuitime())) {
                String rewardStr = ((ChongjipackBean)chongList.get(i)).getPackgoods();
                Double jg = Double.valueOf((double)(int)((ChongjipackBean)chongList.get(i)).getCanpaymoney());
                if ((double)roleInfo.getCodecard().longValue() < (double)jg) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("仙玉不足"));
                    return;
                }
                roleInfo.setCodecard(new BigDecimal((double)roleInfo.getCodecard().longValue() - (double)jg));
                StringBuffer str = new StringBuffer();
                str.append("X=");
                str.append(-jg.longValue());
                DropUtil.getDrop2(roleInfo, rewardStr, "", 29, 1.0, null, str.toString());
            }
        }
        roleInfo.setDaygetorno(new BigDecimal(1));
    }
    
    public static void main(String[] args) {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("当前时间：" + sdf.format(d));
    }
}
