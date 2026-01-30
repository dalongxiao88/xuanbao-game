package org.come.mapper;

import org.come.entity.ImportantgoodssumrecordEntity;
import org.come.entity.BuytypeEntity;
import org.come.entity.ImportantgoodsluEntity;
import java.math.BigDecimal;
import org.come.bean.ImportantgoodssumrecordBean;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.come.annotation.MyBatisAnnotation;

@MyBatisAnnotation
public interface ImportantgoodtrcordMapper
{
    List<ImportantgoodssumrecordBean> selectImportantgoodsrecordList(@Param("time") String p0, @Param("weekendsum") String p1);
    
    List<ImportantgoodssumrecordBean> selectImportantgoodsrecordGoods(BigDecimal p0);
    
    List<ImportantgoodsluEntity> selectImportantGoodsLuList(@Param("goodsid") String p0, @Param("goodsname") String p1);
    
    int updateImportantGoodsLu(ImportantgoodsluEntity p0);
    
    int addImportantGoodsLu(ImportantgoodsluEntity p0);
    
    int deleteImportantGoodsLu(ImportantgoodsluEntity p0);
    
    List<BuytypeEntity> selectBuyTypeList(@Param("typename") String p0, @Param("type") String p1);
    
    int updateBuyType(BuytypeEntity p0);
    
    int deleteBuyType(BuytypeEntity p0);
    
    int addBuyType(BuytypeEntity p0);
    
    List<ImportantgoodssumrecordEntity> selectImportantGoods();
    
    int addImporatantGoodsSum(ImportantgoodssumrecordEntity p0);
    
    void addImporatantGoodsLuTableSpace(@Param("time") String p0);
    
    void addTableImporatantGoodsLuTableSpace(@Param("time") String p0);
    
    int selectTableSapce(@Param("tableSpaceName") String p0);
    
    int selectTablePartition(@Param("partitionName") String p0, @Param("tableName") String p1);
}
