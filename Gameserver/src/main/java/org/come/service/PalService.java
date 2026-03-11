package org.come.service;

import org.come.entity.Pal;
import java.util.List;
import java.math.BigDecimal;

public interface PalService
{
    List<Pal> selectPalByRoleID(BigDecimal roleId);
    
    Pal selectPalByID(BigDecimal palId);
    
    void deletePal(BigDecimal palId);
    
    void updatePal(Pal pal);
    
    void insertPal(Pal pal);
    
    List<Pal> selectAllPalSql();
    
    List<Pal> selectPalByRoleIDSql(BigDecimal roleId);
    
    void deletePalSql(BigDecimal palId);
    
    void updatePalSql(Pal pal);
    
    void insertPalSql(Pal pal);
    
    void deletePalList(List<BigDecimal> palIds);
    
    void updatePalList(List<Pal> palList);
    
    void insertPalList(List<Pal> palList);
}
