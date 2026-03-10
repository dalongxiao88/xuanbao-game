package org.come.service;

import org.come.entity.LingbaoRoleUser;
import java.util.List;
import org.come.entity.Lingbao;
import java.math.BigDecimal;

public interface ILingbaoService
{
    Lingbao selectByPrimaryKey(BigDecimal lingbaoId);
    
    List<Lingbao> selectAllLingbao();
    
    void insertLingbao(Lingbao lingbao);
    
    List<Lingbao> selectLingbaoByRoleID(BigDecimal roleId);
    
    Lingbao selectLingbaoByID(BigDecimal lingbaoId);
    
    void updateLingbaoIndex(Lingbao lingbao, BigDecimal roleId);
    
    void updateLingbaoRedis(Lingbao lingbao);
    
    void deleteLingbao(BigDecimal lingbaoId);
    
    BigDecimal selectMaxID();
    
    void updateLingbaosql(Lingbao lingbao);
    
    void deleteLingbaosql(BigDecimal lingbaoId);
    
    void insertLingbaosql(Lingbao lingbao);
    
    List<LingbaoRoleUser> selectLingBaoRU(LingbaoRoleUser lingbaoRoleUser);
    
    Integer selectLingBaoRUCount(LingbaoRoleUser lingbaoRoleUser);
    
    void deleteLingbaoList(List<BigDecimal> lingbaoIds);
    
    void insertLingbaoList(List<Lingbao> lingbaoList);
    
    void updateLingbaoList(List<Lingbao> lingbaoList);
}
