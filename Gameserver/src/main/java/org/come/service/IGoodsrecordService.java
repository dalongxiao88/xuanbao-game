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
    PageInfo<Goodsrecord> selectGoodsRecord(Integer p0, String p1);
    /**
     * 查询物品记录
     * @param goodsrecord
     * @return
     */
    List<Goodsrecord> selectGoodsRecordByType(  Integer type);
    int countByExample(GoodsrecordExample p0);
    
    int deleteByExample(GoodsrecordExample p0);
    
    int deleteByPrimaryKey(BigDecimal p0);
    
    int insert(Goodstable p0, BigDecimal p1, Integer p2, Integer p3);
    
    int insertGoodsRecordNew(Goodstable p0, BigDecimal p1, BigDecimal p2, Integer p3, Integer p4, String p5, String p6);
    
    int insertGoodsrecord(BigDecimal p0, BigDecimal p1, int p2, BigDecimal p3, String p4, String p5, int p6);
    
    int insertSelective(Goodsrecord p0);
    
    List<Goodsrecord> selectByExample(GoodsrecordExample p0);
    
    Goodsrecord selectByPrimaryKey(Integer p0);
    
    int updateByExampleSelective(Goodsrecord p0, GoodsrecordExample p1);
    
    int updateByExample(Goodsrecord p0, GoodsrecordExample p1);
    
    int updateByPrimaryKeySelective(Goodsrecord p0);
    
    int updateByPrimaryKey(Goodsrecord p0);
    
    List<Goodsrecord> selectGoodsrecordList(Goodsrecord p0);
    
    int insertGoodsrecordRoel(Goodsrecord p0);
    
    PageInfo<Goodsrecord> selectGoodsRecordNew(Param p0);
}
