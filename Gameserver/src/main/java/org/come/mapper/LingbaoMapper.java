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
    
    void insertLingbao(Lingbao p0);
    
    List<Lingbao> selectLingbaoByRoleID(BigDecimal p0);
    
    void updateLingbao(Lingbao p0);
    
    void deleteLingbao(BigDecimal p0);
    
    BigDecimal selectMaxID();
    
    List<LingbaoRoleUser> selectLingBaoRU(@Param("lru") LingbaoRoleUser p0);
    
    Integer selectLingBaoRUCount(@Param("lru") LingbaoRoleUser p0);
    
    void deleteLingbaoList(List<BigDecimal> p0);
    
    void insertLingbaoList(List<Lingbao> p0);
    
    void updateLingbaoList(List<Lingbao> p0);
}
