package org.come.service;

import org.come.entity.GoodsbuyrecordEntity;
import org.come.entity.GoodssaledayrecordEntity;
import org.come.entity.ShangchengshopEntity;
import org.come.bean.Goodsbuyrecordsumbean;
import java.math.BigDecimal;
import org.come.entity.Goodstable;
import java.util.List;

public interface IGoodsTableService
{
    List<Goodstable> getAllGoods();
    
    List<Goodstable> getGoodsByRoleID(BigDecimal p0);
    
    void insertGoods(Goodstable p0);
    
    void updateGoodsIndex(Goodstable p0, BigDecimal p1, BigDecimal p2, Integer p3);
    
    String updateGoodsNum(Goodstable p0, int p1);
    
    void deleteGoodsByRgid(BigDecimal p0);
    
    Goodstable getGoodsByRgID(BigDecimal p0);
    
    List<Goodstable> selectGoodsByRoleIDAndGoodsID(BigDecimal p0, BigDecimal p1);
    
    List<Goodstable> selectGoodsByRoleIDAndGoodsIDAndState(BigDecimal p0, BigDecimal p1, int p2);
    
    BigDecimal selectMaxID();
    
    void insertGoodssql(Goodstable p0);
    
    void insertGoodssqlS(List<Goodstable> p0);
    
    void updateGoodssql(Goodstable p0);
    
    void deleteGoodsByRgidsql(BigDecimal p0);
    
    void updateGoodRedis(Goodstable p0);
    
    List<Goodsbuyrecordsumbean> selectXianYuGoodsbuy(String p0, String p1, int p2, String p3);
    
    List<Goodsbuyrecordsumbean> selectXianYuGoodsbuyZhuZhuangTu(BigDecimal p0);
    
    List<ShangchengshopEntity> selectShangChengShopList(String p0, String p1, int p2);
    
    int updateShangChengShop(ShangchengshopEntity p0);
    
    int deleteShangChengShop(ShangchengshopEntity p0);
    
    int addShangChengShop(ShangchengshopEntity p0);
    
    List<GoodssaledayrecordEntity> selectGoodsBuyRecordSumList();
    
    int addGoodssaledayrecord(GoodssaledayrecordEntity p0);
    
    int addGoodsBuyRecord(GoodsbuyrecordEntity p0);
    
    int updateGoodssqlS(List<Goodstable> p0);
    
    void deleteGoodsByRgidsqlS(List<BigDecimal> p0);
    
    Goodstable getGoodsByHashKey(String p0);
}
