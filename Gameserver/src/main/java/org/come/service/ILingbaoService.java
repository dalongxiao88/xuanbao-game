package org.come.service;

import org.come.entity.LingbaoRoleUser;
import java.util.List;
import org.come.entity.Lingbao;
import java.math.BigDecimal;

public interface ILingbaoService
{
    Lingbao selectByPrimaryKey(BigDecimal p0);
    
    List<Lingbao> selectAllLingbao();
    
    void insertLingbao(Lingbao p0);
    
    List<Lingbao> selectLingbaoByRoleID(BigDecimal p0);
    
    Lingbao selectLingbaoByID(BigDecimal p0);
    
    void updateLingbaoIndex(Lingbao p0, BigDecimal p1);
    
    void updateLingbaoRedis(Lingbao p0);
    
    void deleteLingbao(BigDecimal p0);
    
    BigDecimal selectMaxID();
    
    void updateLingbaosql(Lingbao p0);
    
    void deleteLingbaosql(BigDecimal p0);
    
    void insertLingbaosql(Lingbao p0);
    
    List<LingbaoRoleUser> selectLingBaoRU(LingbaoRoleUser p0);
    
    Integer selectLingBaoRUCount(LingbaoRoleUser p0);
    
    void deleteLingbaoList(List<BigDecimal> p0);
    
    void insertLingbaoList(List<Lingbao> p0);
    
    void updateLingbaoList(List<Lingbao> p0);
}
