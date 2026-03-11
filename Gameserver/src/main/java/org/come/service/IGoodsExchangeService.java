package org.come.service;

import java.util.List;
import org.come.entity.Goodsexchange;
import org.come.entity.GoodsexchangeExample;
/**
 * IGoodsExchangeService 接口/数据访问定义。
 *
 * 当前文件已完成首轮反编译命名收口，后续继续按业务链路补充更细的行为注释。
 */

public interface IGoodsExchangeService
{
    int countByExample(GoodsexchangeExample goodsExchangeExample);
    
    int deleteByExample(GoodsexchangeExample goodsExchangeExample);
    
    int deleteByPrimaryKey(String goodsGuid);
    
    int insert(Goodsexchange goodsExchange);
    
    int insertSelective(Goodsexchange goodsExchange);
    
    List<Goodsexchange> selectByExample(GoodsexchangeExample goodsExchangeExample);
    
    Goodsexchange selectByPrimaryKey(String goodsGuid);
    
    int updateByExampleSelective(Goodsexchange goodsExchange, GoodsexchangeExample goodsExchangeExample);
    
    int updateByExample(Goodsexchange goodsExchange, GoodsexchangeExample goodsExchangeExample);
    
    int updateByPrimaryKeySelective(Goodsexchange goodsExchange);
    
    int updateByPrimaryKey(Goodsexchange goodsExchange);
    
    List<Goodsexchange> selectListAll();
}

