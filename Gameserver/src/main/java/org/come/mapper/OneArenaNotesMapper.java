package org.come.mapper;

import come.tool.oneArena.OneArenaNotes;
import java.util.List;
import java.math.BigDecimal;
import org.apache.ibatis.annotations.Param;
import org.come.annotation.MyBatisAnnotation;

@MyBatisAnnotation
public interface OneArenaNotesMapper {
    /**
     * 查询指定时间点之前的最大战报 ID；时间为空时返回全表最大值。
     */
    BigDecimal selectMaxID(@Param("time") String maxTimeExclusive);

    /**
     * 查询指定角色在最小战报 ID 之后的竞技场战报。
     */
    List<OneArenaNotes> selectRole(@Param("roleId") BigDecimal roleId,
                                   @Param("min") BigDecimal minimumNoteId);

    /**
     * 插入一条新的单人竞技战报。
     */
    int insertOneArenaNotes(@Param("notes") OneArenaNotes oneArenaNotes);
}
