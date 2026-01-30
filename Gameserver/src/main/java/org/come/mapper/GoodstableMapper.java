package org.come.mapper;

import org.come.entity.GoodsbuyrecordEntity;
import org.come.entity.GoodssaledayrecordEntity;
import org.come.entity.ShangchengshopEntity;
import org.come.bean.Goodsbuyrecordsumbean;
import org.apache.ibatis.annotations.Param;
import java.math.BigDecimal;
import org.come.entity.Goodstable;
import java.util.List;
import org.come.annotation.MyBatisAnnotation;

@MyBatisAnnotation
public interface GoodstableMapper
{
    List<Goodstable> getAllGoods();
    
    List<Goodstable> getGoodsByRoleID(BigDecimal p0);
    
    void insertGoods(Goodstable p0);
    
    void insertGoodssqlS(List<Goodstable> p0);
    
    void updateGoods(Goodstable p0);
    
    void deleteGoodsByRgid(BigDecimal p0);
    
    Goodstable getGoodsByRgID(BigDecimal p0);
    
    List<Goodstable> selectGoodsByRoleIDAndGoodsID(@Param("roleid") BigDecimal p0, @Param("goodsid") BigDecimal p1);
    
    BigDecimal selectMaxID();
    
    List<Goodsbuyrecordsumbean> selectXianYuGoodsbuy(@Param("time") String p0, @Param("goodsname") String p1, @Param("type") String p2);
    
    List<Goodsbuyrecordsumbean> selectXianYuGoodsbuyZhuZhuangTu(BigDecimal p0);
    
    List<ShangchengshopEntity> selectShangChengShopList(@Param("goodsid") String p0, @Param("goodsname") String p1);
    
    int updateShangChengShop(ShangchengshopEntity p0);
    
    int deleteShangChengShop(ShangchengshopEntity p0);
    
    int addShangChengShop(ShangchengshopEntity p0);
    
    List<GoodssaledayrecordEntity> selectGoodsBuyRecordSumList();
    
    int addGoodssaledayrecord(GoodssaledayrecordEntity p0);
    
    int addGoodsBuyRecord(GoodsbuyrecordEntity p0);
    
    int deleteGoodsByRgids(List<BigDecimal> p0);
    
    int updateGoodsList(List<Goodstable> p0);
}
