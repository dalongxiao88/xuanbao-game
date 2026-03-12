package org.come.mapper;

import org.come.entity.ImportantgoodssumrecordEntity;
import org.come.entity.BuytypeEntity;
import org.come.entity.ImportantgoodsluEntity;
import java.math.BigDecimal;
import org.come.bean.ImportantgoodssumrecordBean;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.come.annotation.MyBatisAnnotation;
/**
 * ImportantgoodtrcordMapper 接口/数据访问定义。
 *
 * 当前文件已完成首轮反编译命名收口，后续继续按业务链路补充更细的行为注释。
 */

@MyBatisAnnotation
public interface ImportantgoodtrcordMapper
{
    List<ImportantgoodssumrecordBean> selectImportantgoodsrecordList(@Param("time") String timeRange, @Param("weekendsum") String weekendSummary);
    
    List<ImportantgoodssumrecordBean> selectImportantgoodsrecordGoods(BigDecimal goodsId);
    
    List<ImportantgoodsluEntity> selectImportantGoodsLuList(@Param("goodsid") String goodsId, @Param("goodsname") String goodsName);
    
    int updateImportantGoodsLu(ImportantgoodsluEntity importantGoodsLu);
    
    int addImportantGoodsLu(ImportantgoodsluEntity importantGoodsLu);
    
    int deleteImportantGoodsLu(ImportantgoodsluEntity importantGoodsLu);
    
    List<BuytypeEntity> selectBuyTypeList(@Param("typename") String typeName, @Param("type") String type);
    
    int updateBuyType(BuytypeEntity buyType);
    
    int deleteBuyType(BuytypeEntity buyType);
    
    int addBuyType(BuytypeEntity buyType);
    
    List<ImportantgoodssumrecordEntity> selectImportantGoods();
    
    int addImporatantGoodsSum(ImportantgoodssumrecordEntity importantGoodsSumRecord);
    
    void addImporatantGoodsLuTableSpace(@Param("time") String time);
    
    void addTableImporatantGoodsLuTableSpace(@Param("time") String time);
    
    int selectTableSapce(@Param("tableSpaceName") String tableSpaceName);
    
    int selectTablePartition(@Param("partitionName") String partitionName, @Param("tableName") String tableName);
}

