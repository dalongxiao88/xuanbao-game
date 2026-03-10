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
    
    List<Goodstable> getGoodsByRoleID(BigDecimal roleId);
    
    void insertGoods(Goodstable goodstable);
    
    void insertGoodssqlS(List<Goodstable> goodsList);
    
    void updateGoods(Goodstable goodstable);
    
    void deleteGoodsByRgid(BigDecimal rgid);
    
    Goodstable getGoodsByRgID(BigDecimal rgid);
    
    List<Goodstable> selectGoodsByRoleIDAndGoodsID(@Param("roleid") BigDecimal roleId, @Param("goodsid") BigDecimal goodsId);
    
    BigDecimal selectMaxID();
    
    List<Goodsbuyrecordsumbean> selectXianYuGoodsbuy(@Param("time") String timeRange, @Param("goodsname") String goodsName, @Param("type") String type);
    
    List<Goodsbuyrecordsumbean> selectXianYuGoodsbuyZhuZhuangTu(BigDecimal goodsId);
    
    List<ShangchengshopEntity> selectShangChengShopList(@Param("goodsid") String goodsId, @Param("goodsname") String goodsName);
    
    int updateShangChengShop(ShangchengshopEntity shopEntity);
    
    int deleteShangChengShop(ShangchengshopEntity shopEntity);
    
    int addShangChengShop(ShangchengshopEntity shopEntity);
    
    List<GoodssaledayrecordEntity> selectGoodsBuyRecordSumList();
    
    int addGoodssaledayrecord(GoodssaledayrecordEntity saleDayRecord);
    
    int addGoodsBuyRecord(GoodsbuyrecordEntity buyRecord);
    
    int deleteGoodsByRgids(List<BigDecimal> rgidList);
    
    int updateGoodsList(List<Goodstable> goodsList);
}
