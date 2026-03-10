package org.come.mapper;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import org.come.entity.SellLianghaoAuc;
import java.math.BigDecimal;
import org.come.entity.SellLianghaoAucExample;
import org.come.annotation.MyBatisAnnotation;

@MyBatisAnnotation
public interface SellLianghaoAucMapper
{
    int countByExample(SellLianghaoAucExample sellLianghaoAucExample);
    
    int deleteByExample(SellLianghaoAucExample sellLianghaoAucExample);
    
    int deleteByPrimaryKey(BigDecimal auctionId);
    
    int insert(SellLianghaoAuc sellLianghaoAuc);
    
    int insertSelective(SellLianghaoAuc sellLianghaoAuc);
    
    List<SellLianghaoAuc> selectByExample(SellLianghaoAucExample sellLianghaoAucExample);
    
    SellLianghaoAuc selectByPrimaryKey(BigDecimal auctionId);
    
    int updateByExampleSelective(@Param("record") SellLianghaoAuc sellLianghaoAuc, @Param("example") SellLianghaoAucExample sellLianghaoAucExample);
    
    int updateByExample(@Param("record") SellLianghaoAuc sellLianghaoAuc, @Param("example") SellLianghaoAucExample sellLianghaoAucExample);
    
    int updateByPrimaryKeySelective(SellLianghaoAuc sellLianghaoAuc);
    
    int updateByPrimaryKey(SellLianghaoAuc sellLianghaoAuc);
    
    List<SellLianghaoAuc> selectByPrice(SellLianghaoAuc sellLianghaoAuc);
}
