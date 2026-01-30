package org.come.service;

import java.util.List;
import org.come.entity.GoodsRoleUser;

public interface GoodsRoleUsertService
{
    List<GoodsRoleUser> selectGoodsByPage(GoodsRoleUser p0);
    
    Integer selectGoodsCount(GoodsRoleUser p0);
}
