package org.come.action.sale;

import org.come.entity.Salegoods;
import java.util.List;
import org.come.entity.Collection;
import org.come.until.AllServiceUtil;
import java.math.BigDecimal;
import org.come.entity.CollectionExample;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class CollectionControlAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult roleInfo = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (message == null) {
            return;
        }
        CollectionExample example = new CollectionExample();
        CollectionExample.Criteria c = example.createCriteria();
        c.andSaleidEqualTo(new BigDecimal(message));
        c.andRoleidEqualTo(roleInfo.getRole_id());
        List<Collection> list = AllServiceUtil.getCollectionService().selectByExample(example);
        if (list != null && list.size() != 0) {
            AllServiceUtil.getCollectionService().deleteByPrimaryKey(((Collection)list.get(0)).getColid());
        }
        else {
            Collection collection = new Collection();
            collection.setRoleid(roleInfo.getRole_id());
            collection.setSaleid(new BigDecimal(message));
            Salegoods sale = AllServiceUtil.getSalegoodsService().selectByPrimaryKey(new BigDecimal(message));
            if (sale == null) {
                return;
            }
            AllServiceUtil.getCollectionService().insert(collection);
        }
    }
}
