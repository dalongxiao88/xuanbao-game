package org.come.service;

import come.tool.Stall.AssetUpdate;
import java.util.ArrayList;
import io.netty.channel.ChannelHandlerContext;
import org.come.bean.LoginResult;
import org.come.bean.SellXianyu;
import java.util.List;
import org.come.entity.SellXianYuOrder;
import java.math.BigDecimal;

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
