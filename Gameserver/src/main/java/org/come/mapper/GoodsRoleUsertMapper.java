package org.come.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.come.entity.GoodsRoleUser;
import org.come.annotation.MyBatisAnnotation;

@MyBatisAnnotation
public interface GoodsRoleUsertMapper
{
    List<GoodsRoleUser> selectGoodsByPage(@Param("gRU") GoodsRoleUser p0);
    
    Integer selectGoodsCount(@Param("gRU") GoodsRoleUser p0);
}
