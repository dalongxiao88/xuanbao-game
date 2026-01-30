package org.come.serviceImpl;

import org.come.entity.ImportantgoodssumrecordEntity;
import org.come.entity.BuytypeEntity;
import org.come.entity.ImportantgoodsluEntity;
import java.math.BigDecimal;
import com.github.pagehelper.BasePageHelper;
import org.come.bean.ImportantgoodssumrecordBean;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.come.until.MybatisUntil;
import org.come.mapper.ImportantgoodtrcordMapper;
import org.come.service.IImportantgoodtrcordService;

public class ImportantgoodtrcordImpl implements IImportantgoodtrcordService
{
    private ImportantgoodtrcordMapper importantgoodtrcordMapper;
    
    public ImportantgoodtrcordImpl() {
        ApplicationContext ctx = MybatisUntil.getApplicationContext();
        this.importantgoodtrcordMapper = (ImportantgoodtrcordMapper)ctx.getBean("importantgoodtrcordMapper");
    }
    
    @Override
    public List<ImportantgoodssumrecordBean> selectImportantgoodsrecordList(String time, String weekendsum, int page) {
        BasePageHelper.startPage(page, 10);
        return this.importantgoodtrcordMapper.selectImportantgoodsrecordList(time, weekendsum);
    }
    
    @Override
    public List<ImportantgoodssumrecordBean> selectImportantgoodsrecordGoods(BigDecimal goodsid) {
        return this.importantgoodtrcordMapper.selectImportantgoodsrecordGoods(goodsid);
    }
    
    @Override
    public List<ImportantgoodsluEntity> selectImportantGoodsLuList(String goodsid, String goodsname, int page) {
        BasePageHelper.startPage(page, 10);
        return this.importantgoodtrcordMapper.selectImportantGoodsLuList(goodsid, goodsname);
    }
    
    @Override
    public int updateImportantGoodsLu(ImportantgoodsluEntity importantGoodsLuEntity) {
        return this.importantgoodtrcordMapper.updateImportantGoodsLu(importantGoodsLuEntity);
    }
    
    @Override
    public int deleteImportantGoodsLu(ImportantgoodsluEntity importantGoodsLuEntity) {
        return this.importantgoodtrcordMapper.deleteImportantGoodsLu(importantGoodsLuEntity);
    }
    
    @Override
    public List<BuytypeEntity> selectBuyTypeList(String typename, String type, int page) {
        BasePageHelper.startPage(page, 10);
        return this.importantgoodtrcordMapper.selectBuyTypeList(typename, type);
    }
    
    @Override
    public int updateBuyType(BuytypeEntity buytypeEntity) {
        return this.importantgoodtrcordMapper.updateBuyType(buytypeEntity);
    }
    
    @Override
    public int deleteBuyType(BuytypeEntity buytypeEntity) {
        return this.importantgoodtrcordMapper.deleteBuyType(buytypeEntity);
    }
    
    @Override
    public int addImportantGoodsLu(ImportantgoodsluEntity importantGoodsLuEntity) {
        return this.importantgoodtrcordMapper.addImportantGoodsLu(importantGoodsLuEntity);
    }
    
    @Override
    public int addBuyType(BuytypeEntity buytypeEntity) {
        return this.importantgoodtrcordMapper.addBuyType(buytypeEntity);
    }
    
    @Override
    public List<ImportantgoodssumrecordEntity> selectImportantGoods() {
        return this.importantgoodtrcordMapper.selectImportantGoods();
    }
    
    @Override
    public int addImporatantGoodsSum(ImportantgoodssumrecordEntity importantgoodssumrecordEntity) {
        return this.importantgoodtrcordMapper.addImporatantGoodsSum(importantgoodssumrecordEntity);
    }
    
    @Override
    public void addImporatantGoodsLuTableSpace(String time, String tableSpaceName, String path) {
        String sql = "create tablespace " + tableSpaceName + " datafile '" + path + tableSpaceName + ".dbf' size 10M autoextend on next 10M maxsize unlimited ";
        this.importantgoodtrcordMapper.addImporatantGoodsLuTableSpace(sql);
    }
    
    @Override
    public void addTableImporatantGoodsLuTableSpace(String time, String partitionName, String tableName) {
        StringBuffer buffer = new StringBuffer(time);
        buffer.insert(6, "-");
        buffer.insert(4, "-");
        String sql = "ALTER TABLE " + tableName + " ADD PARTITION " + partitionName + " VALUES ('" + buffer + "') TABLESPACE " + partitionName + "";
        this.importantgoodtrcordMapper.addTableImporatantGoodsLuTableSpace(sql);
    }
    
    @Override
    public int selectTableSapce(String tableSpaceName) {
        return this.importantgoodtrcordMapper.selectTableSapce(tableSpaceName);
    }
    
    @Override
    public int selectTablePartition(String partitionName, String tableName) {
        return this.importantgoodtrcordMapper.selectTablePartition(partitionName, tableName);
    }
}
