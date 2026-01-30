package org.come.service;

import org.come.entity.Pal;
import java.util.List;
import java.math.BigDecimal;

public interface PalService
{
    List<Pal> selectPalByRoleID(BigDecimal p0);
    
    Pal selectPalByID(BigDecimal p0);
    
    void deletePal(BigDecimal p0);
    
    void updatePal(Pal p0);
    
    void insertPal(Pal p0);
    
    List<Pal> selectAllPalSql();
    
    List<Pal> selectPalByRoleIDSql(BigDecimal p0);
    
    void deletePalSql(BigDecimal p0);
    
    void updatePalSql(Pal p0);
    
    void insertPalSql(Pal p0);
    
    void deletePalList(List<BigDecimal> p0);
    
    void updatePalList(List<Pal> p0);
    
    void insertPalList(List<Pal> p0);
}
