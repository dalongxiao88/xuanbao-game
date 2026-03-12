package org.come.service;

import come.tool.Stall.AssetUpdate;
import java.util.ArrayList;
import io.netty.channel.ChannelHandlerContext;
import org.come.bean.LoginResult;
import org.come.bean.SellXianyu;
import java.util.List;
import org.come.entity.SellXianYuOrder;
import java.math.BigDecimal;
/**
 * ISellXianYuOrderService 接口/数据访问定义。
 *
 * 当前文件已完成首轮反编译命名收口，后续继续按业务链路补充更细的行为注释。
 */

public interface ISellXianYuOrderService
{
    SellXianYuOrder selectOneByID(BigDecimal orderId);
    
    List<SellXianYuOrder> selectAll();
    
    List<SellXianYuOrder> selectAllByRoleId(BigDecimal roleId);
    
    List<SellXianyu> selectSellListByRoleId(BigDecimal roleId);
    
    List<SellXianyu> selectSellListNotDeposit();
    
    SellXianyu selectSellByIdNotDeposit(BigDecimal saleId);
    
    int insertOrder(SellXianYuOrder sellXianYuOrder);
    
    int updateOrder(SellXianYuOrder sellXianYuOrder);
    
    void addReidsSellxx(SellXianyu sellXianyu);
    
    void updateRedisSellxx(SellXianyu sellXianyu);
    
    ArrayList<AssetUpdate> downSellXianyu(String saleId, LoginResult loginResult, ChannelHandlerContext channelHandlerContext);
    
    void calDeposit();
    
    void calSelfDeposit(ChannelHandlerContext channelHandlerContext);
}

