package org.come.action.sale;

import org.come.entity.Collection;
import java.util.List;
import org.come.handler.SendMessage;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import org.come.bean.SearchCollectionResultBean;
import com.github.pagehelper.PageInfo;
import org.come.until.AllServiceUtil;
import com.github.pagehelper.BasePageHelper;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class CollectionSearchAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult roleInfo = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        int pageNum = 1;
        if (message != null) {
            pageNum = Integer.parseInt(message);
        }
        BasePageHelper.startPage(pageNum, 15);
        List<Collection> list = AllServiceUtil.getCollectionService().selectRoleCollect(roleInfo.getRole_id());
        PageInfo<Collection> pageInfo = new PageInfo(list);
        SearchCollectionResultBean resultBean = new SearchCollectionResultBean();
        resultBean.setCollections(pageInfo.getList());
        resultBean.setTotal(Integer.valueOf(pageInfo.getPages()));
        String msg = Agreement.getAgreement().CBGSearch7Agreement(GsonUtil.getGsonUtil().getgson().toJson(resultBean));
        SendMessage.sendMessageToSlef(ctx, msg);
    }
}
