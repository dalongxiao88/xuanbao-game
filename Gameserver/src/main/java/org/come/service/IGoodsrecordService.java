package org.come.service;

import com.gl.model.Param;
import java.util.List;
import org.come.entity.Goodstable;
import java.math.BigDecimal;
import org.come.entity.GoodsrecordExample;
import org.come.entity.Goodsrecord;
import com.github.pagehelper.PageInfo;

public interface IGoodsrecordService
{
    PageInfo<Goodsrecord> selectGoodsRecord(Integer pageNum, String condition);
    /**
     * 查询物品记录
     * @param goodsrecord
     * @return
     */
    List<Goodsrecord> selectGoodsRecordByType(  Integer type);
    int countByExample(GoodsrecordExample goodsrecordExample);
    
    int deleteByExample(GoodsrecordExample goodsrecordExample);
    
    int deleteByPrimaryKey(BigDecimal recordId);
    
    int insert(Goodstable goodstable, BigDecimal roleId, Integer goodsNumber, Integer recordType);
    
    int insertGoodsRecordNew(Goodstable goodstable, BigDecimal roleId, BigDecimal saleId, Integer goodsNumber, Integer recordType, String goodsName, String remark);
    
    int insertGoodsrecord(BigDecimal roleId, BigDecimal goodsId, int goodsNumber, BigDecimal saleId, String goodsName, String remark, int recordType);
    
    int insertSelective(Goodsrecord goodsrecord);
    
    List<Goodsrecord> selectByExample(GoodsrecordExample goodsrecordExample);
    
    Goodsrecord selectByPrimaryKey(Integer recordId);
    
    int updateByExampleSelective(Goodsrecord goodsrecord, GoodsrecordExample goodsrecordExample);
    
    int updateByExample(Goodsrecord goodsrecord, GoodsrecordExample goodsrecordExample);
    
    int updateByPrimaryKeySelective(Goodsrecord goodsrecord);
    
    int updateByPrimaryKey(Goodsrecord goodsrecord);
    
    List<Goodsrecord> selectGoodsrecordList(Goodsrecord goodsrecord);
    
    int insertGoodsrecordRoel(Goodsrecord goodsrecord);
    
    PageInfo<Goodsrecord> selectGoodsRecordNew(Param queryParam);
}
