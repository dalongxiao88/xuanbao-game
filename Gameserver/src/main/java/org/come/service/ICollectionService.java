package org.come.service;

import org.come.entity.CollectionExample;
import org.come.entity.Collection;
import java.util.List;
import java.math.BigDecimal;

public interface ICollectionService
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
    
    int updateByExampleSelective(Collection collection, CollectionExample collectionExample);
    
    int updateByExample(Collection collection, CollectionExample collectionExample);
    
    int updateByPrimaryKeySelective(Collection collection);
    
    int updateByPrimaryKey(Collection collection);
}
