package org.come.action.vip;

import org.come.handler.SendMessage;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import org.come.entity.LimitedTimeShopBean;
import java.text.ParseException;
import org.come.entity.ChongjipackBean;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import org.come.server.GameServer;
import java.util.List;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class LimitedTimeLshopAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        List<ChongjipackBean> limitedTimeLshop = (List<ChongjipackBean>)GameServer.getPackgradecontrol().get(Integer.valueOf(8));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();
        List<ChongjipackBean> chongjipackBeans = new ArrayList<>();
        for (int i = 0; i < limitedTimeLshop.size(); ++i) {
            try {
                Date startTime = sdf.parse(((ChongjipackBean)limitedTimeLshop.get(i)).getHuitime());
                Date endTime = sdf.parse(((ChongjipackBean)limitedTimeLshop.get(i)).getEndtime());
                if (currentDate.getTime() >= startTime.getTime() && currentDate.getTime() <= endTime.getTime()) {
                    chongjipackBeans.add(limitedTimeLshop.get(i));
                }
            }
            catch (ParseException ex) {}
        }
        if (chongjipackBeans.size() > 0) {
            LimitedTimeShopBean limitedTimeShopBean = new LimitedTimeShopBean();
            limitedTimeShopBean.setChongjipackBeans(chongjipackBeans);
            String sendmes = Agreement.getAgreement().LimitedTimeLshopAgreement(GsonUtil.getGsonUtil().getgson().toJson(limitedTimeShopBean));
            SendMessage.sendMessageToSlef(ctx, sendmes);
        }
    }
}
