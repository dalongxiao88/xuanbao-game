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
    
    List<Goodstable> getGoodsByRoleID(BigDecimal roleId);
    
    void insertGoods(Goodstable goodstable);
    
    void updateGoodsIndex(Goodstable goodstable, BigDecimal roleId, BigDecimal targetIndex, Integer useState);
    
    String updateGoodsNum(Goodstable goodstable, int changeNum);
    
    void deleteGoodsByRgid(BigDecimal rgid);
    
    Goodstable getGoodsByRgID(BigDecimal rgid);
    
    List<Goodstable> selectGoodsByRoleIDAndGoodsID(BigDecimal roleId, BigDecimal goodsId);
    
    List<Goodstable> selectGoodsByRoleIDAndGoodsIDAndState(BigDecimal roleId, BigDecimal goodsId, int state);
    
    BigDecimal selectMaxID();
    
    void insertGoodssql(Goodstable goodstable);
    
    void insertGoodssqlS(List<Goodstable> goodsList);
    
    void updateGoodssql(Goodstable goodstable);
    
    void deleteGoodsByRgidsql(BigDecimal rgid);
    
    void updateGoodRedis(Goodstable goodstable);
    
    List<Goodsbuyrecordsumbean> selectXianYuGoodsbuy(String timeRange, String goodsName, int pageNum, String type);
    
    List<Goodsbuyrecordsumbean> selectXianYuGoodsbuyZhuZhuangTu(BigDecimal goodsId);
    
    List<ShangchengshopEntity> selectShangChengShopList(String goodsId, String goodsName, int pageNum);
    
    int updateShangChengShop(ShangchengshopEntity shopEntity);
    
    int deleteShangChengShop(ShangchengshopEntity shopEntity);
    
    int addShangChengShop(ShangchengshopEntity shopEntity);
    
    List<GoodssaledayrecordEntity> selectGoodsBuyRecordSumList();
    
    int addGoodssaledayrecord(GoodssaledayrecordEntity saleDayRecord);
    
    int addGoodsBuyRecord(GoodsbuyrecordEntity buyRecord);
    
    int updateGoodssqlS(List<Goodstable> goodsList);
    
    void deleteGoodsByRgidsqlS(List<BigDecimal> rgidList);
    
    Goodstable getGoodsByHashKey(String hashKey);
}
