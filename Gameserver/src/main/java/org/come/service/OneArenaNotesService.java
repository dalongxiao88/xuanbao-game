package org.come.service;

import come.tool.oneArena.OneArenaNotes;
import java.util.List;
import java.math.BigDecimal;

/**
 * 单人竞技记录服务接口。
 */
public interface OneArenaNotesService
{
    BigDecimal selectMaxID(String time);
    
    List<OneArenaNotes> selectRole(BigDecimal roleId, BigDecimal min);
    
    int insertOneArenaNotes(OneArenaNotes notes);
}
