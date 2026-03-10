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
    List<Collection> selectRoleCollect(BigDecimal roleId);
    
    List<BigDecimal> selectUserCollection(BigDecimal userId);
    
    int countByExample(CollectionExample collectionExample);
    
    int deleteByExample(CollectionExample collectionExample);
    
    int deleteByPrimaryKey(BigDecimal collectionId);
    
    int insert(Collection collection);
    
    int insertSelective(Collection collection);
    
    List<Collection> selectByExample(CollectionExample collectionExample);
    
    Collection selectByPrimaryKey(BigDecimal collectionId);
    
    int updateByExampleSelective(@Param("record") Collection collection, @Param("example") CollectionExample collectionExample);
    
    int updateByExample(@Param("record") Collection collection, @Param("example") CollectionExample collectionExample);
    
    int updateByPrimaryKeySelective(Collection collection);
    
    int updateByPrimaryKey(Collection collection);
}
