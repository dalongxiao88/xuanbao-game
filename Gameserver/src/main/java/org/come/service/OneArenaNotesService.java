package org.come.service;

import come.tool.oneArena.OneArenaNotes;
import java.util.List;
import java.math.BigDecimal;

public interface OneArenaNotesService
{
    BigDecimal selectMaxID(String p0);
    
    List<OneArenaNotes> selectRole(BigDecimal p0, BigDecimal p1);
    
    int insertOneArenaNotes(OneArenaNotes p0);
}
