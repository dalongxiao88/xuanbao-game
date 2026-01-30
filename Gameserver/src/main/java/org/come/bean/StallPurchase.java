package org.come.bean;

import java.util.stream.Collectors;
import org.apache.commons.lang.StringUtils;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

public class StallPurchase
{
    private BigDecimal goodsId;
    private String goodsName;
    private String goodsSkin;
    private String containsGoodsId;
    
    public List<BigDecimal> getContainsGoodsIds() {
        List<BigDecimal> containsGoodsIds = new ArrayList<>();
        containsGoodsIds.add(this.goodsId);
        if (StringUtils.isNotBlank(this.containsGoodsId)) {
            String[] vals = this.containsGoodsId.split("\\|");
            for (int i = 0; i < vals.length; ++i) {
                containsGoodsIds.add(new BigDecimal(vals[i]));
            }
        }
        return (List)containsGoodsIds.stream().distinct().collect(Collectors.toList());
    }
    
    public BigDecimal getGoodsId() {
        return this.goodsId;
    }
    
    public void setGoodsId(BigDecimal goodsId) {
        this.goodsId = goodsId;
    }
    
    public String getGoodsName() {
        return this.goodsName;
    }
    
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
    
    public String getGoodsSkin() {
        return this.goodsSkin;
    }
    
    public void setGoodsSkin(String goodsSkin) {
        this.goodsSkin = goodsSkin;
    }
    
    public String getContainsGoodsId() {
        return this.containsGoodsId;
    }
    
    public void setContainsGoodsId(String containsGoodsId) {
        this.containsGoodsId = containsGoodsId;
    }
}
