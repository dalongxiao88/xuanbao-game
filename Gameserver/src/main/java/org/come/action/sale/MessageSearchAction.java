package org.come.action.sale;

import org.come.entity.Message;
import java.util.List;
import org.come.handler.SendMessage;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import org.come.bean.SearchMesResultBean;
import com.github.pagehelper.PageInfo;
import org.come.until.AllServiceUtil;
import com.github.pagehelper.BasePageHelper;
import org.come.entity.MessageExample;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class MessageSearchAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult roleInfo = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        MessageExample example = new MessageExample();
        MessageExample.Criteria c = example.createCriteria();
        c.andRoleidEqualTo(roleInfo.getRole_id());
        example.setOrderByClause("gettime desc");
        int pageNum = 1;
        if (message != null) {
            pageNum = Integer.parseInt(message);
        }
        BasePageHelper.startPage(pageNum, 15);
        List<Message> list = AllServiceUtil.getMessageService().selectByExample(example);
        PageInfo<Message> pageInfo = new PageInfo(list);
        SearchMesResultBean resultBean = new SearchMesResultBean();
        resultBean.setMessages(pageInfo.getList());
        resultBean.setTotal(Integer.valueOf(pageInfo.getPages()));
        String msg = Agreement.getAgreement().CBGSearch6Agreement(GsonUtil.getGsonUtil().getgson().toJson(resultBean));
        SendMessage.sendMessageToSlef(ctx, msg);
    }
}
