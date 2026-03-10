package org.come.mapper;

import org.apache.ibatis.annotations.Param;
import org.come.entity.LingbaoRoleUser;
import java.math.BigDecimal;
import org.come.entity.Lingbao;
import java.util.List;
import org.come.annotation.MyBatisAnnotation;

@MyBatisAnnotation
public interface LingbaoMapper
{
    List<Lingbao> selectAllLingbao();
    
    void insertLingbao(Lingbao lingbao);
    
    List<Lingbao> selectLingbaoByRoleID(BigDecimal roleId);
    
    void updateLingbao(Lingbao lingbao);
    
    void deleteLingbao(BigDecimal lingbaoId);
    
    BigDecimal selectMaxID();
    
    List<LingbaoRoleUser> selectLingBaoRU(@Param("lru") LingbaoRoleUser lingbaoRoleUser);
    
    Integer selectLingBaoRUCount(@Param("lru") LingbaoRoleUser lingbaoRoleUser);
    
    void deleteLingbaoList(List<BigDecimal> lingbaoIds);
    
    void insertLingbaoList(List<Lingbao> lingbaoList);
    
    void updateLingbaoList(List<Lingbao> lingbaoList);
}
