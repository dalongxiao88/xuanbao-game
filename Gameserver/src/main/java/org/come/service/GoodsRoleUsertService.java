package org.come.service;

import java.util.List;
import org.come.entity.GoodsRoleUser;

/**
 * 商品与角色关联分页查询服务接口。
 */
public interface GoodsRoleUsertService {
    /**
     * 按查询条件分页拉取商品与角色关联数据。
     */
    List<GoodsRoleUser> selectGoodsByPage(GoodsRoleUser queryCondition);

    /**
     * 统计查询条件下的商品总数。
     */
    Integer selectGoodsCount(GoodsRoleUser queryCondition);
}
