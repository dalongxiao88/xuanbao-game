package org.come.mapper;

import org.apache.ibatis.annotations.Param;
import org.come.entity.CollectionExample;
import org.come.entity.Collection;
import java.util.List;
import java.math.BigDecimal;
import org.come.annotation.MyBatisAnnotation;

@MyBatisAnnotation
public interface CollectionMapper
{
    List<Collection> selectRoleCollect(BigDecimal p0);
    
    List<BigDecimal> selectUserCollection(BigDecimal p0);
    
    int countByExample(CollectionExample p0);
    
    int deleteByExample(CollectionExample p0);
    
    int deleteByPrimaryKey(BigDecimal p0);
    
    int insert(Collection p0);
    
    int insertSelective(Collection p0);
    
    List<Collection> selectByExample(CollectionExample p0);
    
    Collection selectByPrimaryKey(BigDecimal p0);
    
    int updateByExampleSelective(@Param("record") Collection p0, @Param("example") CollectionExample p1);
    
    int updateByExample(@Param("record") Collection p0, @Param("example") CollectionExample p1);
    
    int updateByPrimaryKeySelective(Collection p0);
    
    int updateByPrimaryKey(Collection p0);
}
