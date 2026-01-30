package org.come.action.sale;

import org.come.entity.Message;
import java.util.List;
import java.util.Iterator;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.bean.SearchMesResultBean;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.BasePageHelper;
import org.come.entity.MessageExample;
import org.come.until.AllServiceUtil;
import java.math.BigDecimal;
import org.come.until.GsonUtil;
import org.come.bean.DeleteMsgBean;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class MessageControlAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult roleInfo = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        DeleteMsgBean ids = (DeleteMsgBean)GsonUtil.getGsonUtil().getgson().fromJson(message, DeleteMsgBean.class);
        if (ids != null && ids.getIds() != null && ids.getIds().size() != 0) {
            for (BigDecimal id : ids.getIds()) {
                AllServiceUtil.getMessageService().deleteByPrimaryKey(id);
            }
        }
        MessageExample example = new MessageExample();
        MessageExample.Criteria c = example.createCriteria();
        c.andRoleidEqualTo(roleInfo.getRole_id());
        example.setOrderByClause("gettime desc");
        BasePageHelper.startPage(1, 15);
        List<Message> list = AllServiceUtil.getMessageService().selectByExample(example);
        PageInfo<Message> pageInfo = new PageInfo(list);
        SearchMesResultBean resultBean = new SearchMesResultBean();
        resultBean.setMessages(pageInfo.getList());
        resultBean.setTotal(Integer.valueOf(pageInfo.getPages()));
        String msg = Agreement.getAgreement().CBGSearch6Agreement(GsonUtil.getGsonUtil().getgson().toJson(resultBean));
        SendMessage.sendMessageToSlef(ctx, msg);
    }
}
