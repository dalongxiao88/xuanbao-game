package org.come.service;

import come.tool.oneArena.OneArenaNotes;
import java.util.List;
import java.math.BigDecimal;

/**
 * 单人竞技记录服务接口。
 */
public interface OneArenaNotesService {
    /**
     * 查询指定时间点之前的最大战报 ID。
     */
    BigDecimal selectMaxID(String maxTimeExclusive);

    /**
     * 查询角色在指定最小战报 ID 之后的战报列表。
     */
    List<OneArenaNotes> selectRole(BigDecimal roleId, BigDecimal minimumNoteId);

    /**
     * 写入新的单人竞技战报。
     */
    int insertOneArenaNotes(OneArenaNotes notes);
}
