package org.come.service;

import org.come.entity.RoleTableNew;
import org.come.entity.Region;
import java.util.List;
import java.math.BigDecimal;

/**
 * 分区查询服务接口。
 */
public interface RegionService {
    /**
     * 按分区 ID 与名称关键字筛选分区。
     */
    List<Region> selectRegion(BigDecimal regionId, String regionNameKeyword);

    /**
     * 查询指定账号在目标分区下的角色列表。
     */
    List<RoleTableNew> selectRole(BigDecimal userId, Integer serverAreaId);

    /**
     * 读取全部分区标识。
     */
    List<String> selectRegionAll();
}
