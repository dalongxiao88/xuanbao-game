package org.come.mapper;

import java.math.BigDecimal;
import org.come.entity.Pal;
import java.util.List;
import org.come.annotation.MyBatisAnnotation;

@MyBatisAnnotation
public interface PalMapper
{
    List<Pal> selectAllPal();
    
    List<Pal> selectPalByRoleID(BigDecimal roleId);
    
    void deletePal(BigDecimal palId);
    
    void updatePal(Pal pal);
    
    void insertPal(Pal pal);
    
    void deletePalList(List<BigDecimal> palIds);
    
    void updatePalList(List<Pal> palList);
    
    void insertPalList(List<Pal> palList);
}
