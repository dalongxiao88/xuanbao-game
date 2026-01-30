package org.come.action.vip;

import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.until.GsonUtil;
import org.come.entity.ChongjipackBean;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.come.server.GameServer;
import io.netty.channel.ChannelHandlerContext;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import org.come.action.IAction;

public class DayForOneGetAction implements IAction
{
    private final Log log;
    
    public DayForOneGetAction() {
        this.log = LogFactory.getLog(this.getClass());
    }
    
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        this.log.info("每日特惠，获取每日特惠信息：" + GameServer.getPackgradecontrol().size());
        List<ChongjipackBean> chongjipack = (List<ChongjipackBean>)GameServer.getPackgradecontrol().get(Integer.valueOf(6));
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(d);
        this.log.info("每日特惠，获取每日特惠信息 chongjipack.size() ：" + chongjipack.size());
        int i = 0;
        while (i < chongjipack.size()) {
            this.log.info("每日特惠，获取每日特惠信息规定释放日期：" + ((ChongjipackBean)chongjipack.get(i)).getHuitime());
            if (format.equals(((ChongjipackBean)chongjipack.get(i)).getHuitime())) {
                this.log.info("每日特惠，发送每日特惠信息 明文信息 ：" + GsonUtil.getGsonUtil().getgson().toJson(chongjipack.get(i)));
                String sendmes = Agreement.getAgreement().DayforonegetAgreement(GsonUtil.getGsonUtil().getgson().toJson(chongjipack.get(i)));
                this.log.info("每日特惠，发送每日特惠信息 ：" + sendmes);
                SendMessage.sendMessageToSlef(ctx, sendmes);
                break;
            }
            else {
                ++i;
            }
        }
    }
}
