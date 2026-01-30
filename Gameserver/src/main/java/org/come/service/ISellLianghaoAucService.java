package org.come.service;

import io.netty.channel.ChannelHandlerContext;
import java.util.List;
import org.come.entity.SellLianghaoAuc;
import java.math.BigDecimal;

public interface ISellLianghaoAucService
{
    SellLianghaoAuc selectOneByID(BigDecimal p0);
    
    int insertOrder(SellLianghaoAuc p0);
    
    List<SellLianghaoAuc> selectAllByToday(String p0, Short p1);
    
    List<SellLianghaoAuc> selectAllByDateAndLhAndStatus(String p0, String p1, Short p2);
    
    List<SellLianghaoAuc> selectAllByDateAndRoleIdLhAndStatus(BigDecimal p0, String p1, String p2, Short p3);
    
    List<SellLianghaoAuc> selectAllByDateAndRoleIdLh(BigDecimal p0, String p1, String p2);
    
    int updateStauts(SellLianghaoAuc p0);
    
    void calSelfBuyLh(ChannelHandlerContext p0);
    
    List<SellLianghaoAuc> selectAllByRoleIdAndStatus(BigDecimal p0, Short p1);
    
    List<SellLianghaoAuc> selectAllByRoleId(BigDecimal p0);
    
    List<SellLianghaoAuc> selectAllByStatus(Short p0);
    
    List<SellLianghaoAuc> selectByPrice(SellLianghaoAuc p0);
    
    void updateByPrimaryKeySelective(SellLianghaoAuc p0);
    
    void deleteByPrimaryKey(BigDecimal p0);
}
