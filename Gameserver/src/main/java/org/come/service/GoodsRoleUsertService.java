package org.come.service;

import java.util.List;
import org.come.entity.GoodsRoleUser;

/**
 * 商品与角色关联分页查询服务接口。
 */
public interface GoodsRoleUsertService
{
    List<GoodsRoleUser> selectGoodsByPage(GoodsRoleUser goodsRoleUser);
    
    Integer selectGoodsCount(GoodsRoleUser goodsRoleUser);
}
