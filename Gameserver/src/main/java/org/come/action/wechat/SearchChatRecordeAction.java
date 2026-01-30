package org.come.action.wechat;

import org.come.entity.Wechatrecord;
import java.util.List;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import com.github.pagehelper.PageInfo;
import org.come.until.AllServiceUtil;
import com.github.pagehelper.BasePageHelper;
import java.util.ArrayList;
import org.come.entity.WechatrecordExample;
import java.math.BigDecimal;
import org.come.until.GsonUtil;
import org.come.bean.WechatFriendListBean;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class SearchChatRecordeAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult roleInfo = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        WechatFriendListBean friendListBean = (WechatFriendListBean)GsonUtil.getGsonUtil().getgson().fromJson(message, WechatFriendListBean.class);
        BigDecimal otherID = new BigDecimal(friendListBean.getGetId());
        WechatrecordExample example = new WechatrecordExample();
        WechatrecordExample.Criteria ct = example.createCriteria();
        List<BigDecimal> values = new ArrayList<>();
        values.add(otherID);
        values.add(roleInfo.getRole_id());
        ct.andChatGetidIn(values);
        ct.andChatSendidIn(values);
        if (friendListBean.getTime() != null && !"".equals(friendListBean.getTime())) {
            ct.andTimeLike(friendListBean.getTime() + "%");
        }
        example.setOrderByClause("to_date(time,'yyyy-mm-dd hh24:mi:ss')");
        BasePageHelper.startPage(friendListBean.getSearchPage(), friendListBean.getPageNumber());
        List<Wechatrecord> list = AllServiceUtil.getWechatrecordService().selectByExample(example);
        PageInfo<Wechatrecord> pageInfo = new PageInfo(list);
        friendListBean.setWechatFriendList(pageInfo.getList());
        friendListBean.setSumPage((int)pageInfo.getTotal());
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().searchChatRecordeAgreement(GsonUtil.getGsonUtil().getgson().toJson(friendListBean)));
    }
}
