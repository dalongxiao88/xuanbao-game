package org.come.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.come.entity.GoodsRoleUser;
import org.come.annotation.MyBatisAnnotation;

@MyBatisAnnotation
public interface GoodsRoleUsertMapper {
    /**
     * 按后台筛选条件分页查询商品与角色关联视图。
     */
    List<GoodsRoleUser> selectGoodsByPage(@Param("gRU") GoodsRoleUser queryCondition);

    /**
     * 统计后台筛选条件下的商品总数。
     */
    Integer selectGoodsCount(@Param("gRU") GoodsRoleUser queryCondition);
}
