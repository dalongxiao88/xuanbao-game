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
    SellXianYuOrder selectOneByID(BigDecimal p0);
    
    List<SellXianYuOrder> selectAll();
    
    List<SellXianYuOrder> selectAllByRoleId(BigDecimal p0);
    
    List<SellXianyu> selectSellListByRoleId(BigDecimal p0);
    
    List<SellXianyu> selectSellListNotDeposit();
    
    SellXianyu selectSellByIdNotDeposit(BigDecimal p0);
    
    int insertOrder(SellXianYuOrder p0);
    
    int updateOrder(SellXianYuOrder p0);
    
    void addReidsSellxx(SellXianyu p0);
    
    void updateRedisSellxx(SellXianyu p0);
    
    ArrayList<AssetUpdate> downSellXianyu(String p0, LoginResult p1, ChannelHandlerContext p2);
    
    void calDeposit();
    
    void calSelfDeposit(ChannelHandlerContext p0);
}
