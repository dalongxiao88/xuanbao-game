package org.come.mapper;

import java.math.BigDecimal;
import org.come.entity.Pal;
import java.util.List;
import org.come.annotation.MyBatisAnnotation;

@MyBatisAnnotation
public interface PalMapper
{
    List<Pal> selectAllPal();
    
    List<Pal> selectPalByRoleID(BigDecimal p0);
    
    void deletePal(BigDecimal p0);
    
    void updatePal(Pal p0);
    
    void insertPal(Pal p0);
    
    void deletePalList(List<BigDecimal> p0);
    
    void updatePalList(List<Pal> p0);
    
    void insertPalList(List<Pal> p0);
}
