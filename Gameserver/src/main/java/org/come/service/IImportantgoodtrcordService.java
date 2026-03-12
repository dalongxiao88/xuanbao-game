package org.come.service;

import org.come.entity.ImportantgoodssumrecordEntity;
import org.come.entity.BuytypeEntity;
import org.come.entity.ImportantgoodsluEntity;
import java.math.BigDecimal;
import org.come.bean.ImportantgoodssumrecordBean;
import java.util.List;
/**
 * IImportantgoodtrcordService 接口/数据访问定义。
 *
 * 当前文件已完成首轮反编译命名收口，后续继续按业务链路补充更细的行为注释。
 */

public interface IImportantgoodtrcordService
{
    List<ImportantgoodssumrecordBean> selectImportantgoodsrecordList(String timeRange, String weekendSummary, int pageNum);
    
    List<ImportantgoodssumrecordBean> selectImportantgoodsrecordGoods(BigDecimal goodsId);
    
    List<ImportantgoodsluEntity> selectImportantGoodsLuList(String goodsId, String goodsName, int pageNum);
    
    int updateImportantGoodsLu(ImportantgoodsluEntity importantGoodsLu);
    
    int addImportantGoodsLu(ImportantgoodsluEntity importantGoodsLu);
    
    int deleteImportantGoodsLu(ImportantgoodsluEntity importantGoodsLu);
    
    List<BuytypeEntity> selectBuyTypeList(String typeName, String type, int pageNum);
    
    int updateBuyType(BuytypeEntity buyType);
    
    int deleteBuyType(BuytypeEntity buyType);
    
    int addBuyType(BuytypeEntity buyType);
    
    List<ImportantgoodssumrecordEntity> selectImportantGoods();
    
    int addImporatantGoodsSum(ImportantgoodssumrecordEntity importantGoodsSumRecord);
    
    void addImporatantGoodsLuTableSpace(String time, String goodsId, String goodsName);
    
    void addTableImporatantGoodsLuTableSpace(String time, String goodsId, String goodsName);
    
    int selectTableSapce(String tableSpaceName);
    
    int selectTablePartition(String partitionName, String tableName);
}

