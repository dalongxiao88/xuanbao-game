package org.come.service;

import org.come.entity.ImportantgoodssumrecordEntity;
import org.come.entity.BuytypeEntity;
import org.come.entity.ImportantgoodsluEntity;
import java.math.BigDecimal;
import org.come.bean.ImportantgoodssumrecordBean;
import java.util.List;

public interface IImportantgoodtrcordService
{
    List<ImportantgoodssumrecordBean> selectImportantgoodsrecordList(String p0, String p1, int p2);
    
    List<ImportantgoodssumrecordBean> selectImportantgoodsrecordGoods(BigDecimal p0);
    
    List<ImportantgoodsluEntity> selectImportantGoodsLuList(String p0, String p1, int p2);
    
    int updateImportantGoodsLu(ImportantgoodsluEntity p0);
    
    int addImportantGoodsLu(ImportantgoodsluEntity p0);
    
    int deleteImportantGoodsLu(ImportantgoodsluEntity p0);
    
    List<BuytypeEntity> selectBuyTypeList(String p0, String p1, int p2);
    
    int updateBuyType(BuytypeEntity p0);
    
    int deleteBuyType(BuytypeEntity p0);
    
    int addBuyType(BuytypeEntity p0);
    
    List<ImportantgoodssumrecordEntity> selectImportantGoods();
    
    int addImporatantGoodsSum(ImportantgoodssumrecordEntity p0);
    
    void addImporatantGoodsLuTableSpace(String p0, String p1, String p2);
    
    void addTableImporatantGoodsLuTableSpace(String p0, String p1, String p2);
    
    int selectTableSapce(String p0);
    
    int selectTablePartition(String p0, String p1);
}
