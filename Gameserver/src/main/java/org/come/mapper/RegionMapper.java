package org.come.mapper;

import org.come.entity.RoleTableNew;
import org.come.entity.Region;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import java.math.BigDecimal;
import org.come.annotation.MyBatisAnnotation;

@MyBatisAnnotation
public interface RegionMapper {
    /**
     * 按分区 ID 和模糊名称查询可选分区列表。
     */
    List<Region> selectRegion(@Param("quId") BigDecimal regionId, @Param("raName") String regionNameKeyword);

    /**
     * 查询指定用户在指定分区下的角色列表。
     */
    List<RoleTableNew> selectRole(@Param("userId") BigDecimal userId, @Param("quid") Integer serverAreaId);

    /**
     * 查询全部已配置的分区标识。
     */
    List<String> selectRegionAll();
}
