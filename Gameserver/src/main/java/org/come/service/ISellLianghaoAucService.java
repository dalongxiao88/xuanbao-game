package org.come.service;

import io.netty.channel.ChannelHandlerContext;
import java.util.List;
import org.come.entity.SellLianghaoAuc;
import java.math.BigDecimal;

public interface ISellLianghaoAucService
{
    SellLianghaoAuc selectOneByID(BigDecimal auctionId);
    
    int insertOrder(SellLianghaoAuc sellLianghaoAuc);
    
    List<SellLianghaoAuc> selectAllByToday(String today, Short status);
    
    List<SellLianghaoAuc> selectAllByDateAndLhAndStatus(String date, String lianghao, Short status);
    
    List<SellLianghaoAuc> selectAllByDateAndRoleIdLhAndStatus(BigDecimal roleId, String startDate, String lianghao, Short status);
    
    List<SellLianghaoAuc> selectAllByDateAndRoleIdLh(BigDecimal roleId, String startDate, String lianghao);
    
    int updateStauts(SellLianghaoAuc sellLianghaoAuc);
    
    void calSelfBuyLh(ChannelHandlerContext channelHandlerContext);
    
    List<SellLianghaoAuc> selectAllByRoleIdAndStatus(BigDecimal roleId, Short status);
    
    List<SellLianghaoAuc> selectAllByRoleId(BigDecimal roleId);
    
    List<SellLianghaoAuc> selectAllByStatus(Short status);
    
    List<SellLianghaoAuc> selectByPrice(SellLianghaoAuc sellLianghaoAuc);
    
    void updateByPrimaryKeySelective(SellLianghaoAuc sellLianghaoAuc);
    
    void deleteByPrimaryKey(BigDecimal auctionId);
}
