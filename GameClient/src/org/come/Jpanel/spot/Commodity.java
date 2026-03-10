package org.come.Jpanel.spot;

import java.math.BigDecimal;

/**
 * 摆摊/交易界面中的商品抽象。
 */
public interface Commodity
{
    void setCommodityId(BigDecimal commodityId);
    
    BigDecimal getCommodityId();
}
