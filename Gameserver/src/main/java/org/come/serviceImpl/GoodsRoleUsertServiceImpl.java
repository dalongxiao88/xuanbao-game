package org.come.serviceImpl;

import java.util.List;
import org.come.entity.GoodsRoleUser;
import org.springframework.context.ApplicationContext;
import org.come.until.MybatisUntil;
import org.come.mapper.GoodsRoleUsertMapper;
import org.come.service.GoodsRoleUsertService;

public class GoodsRoleUsertServiceImpl implements GoodsRoleUsertService
{
    private GoodsRoleUsertMapper goodsRoleUsertMapper;
    private final Integer limit;
    
    public GoodsRoleUsertServiceImpl() {
        this.limit = Integer.valueOf(10);
        ApplicationContext ctx = MybatisUntil.getApplicationContext();
        this.goodsRoleUsertMapper = (GoodsRoleUsertMapper)ctx.getBean("goodsRoleUsertMapper", GoodsRoleUsertMapper.class);
    }
    
    @Override
    public List<GoodsRoleUser> selectGoodsByPage(GoodsRoleUser goodsRoleUser) {
        Integer start = Integer.valueOf(((int)goodsRoleUser.getPageNow() - 1) * (int)this.limit + 1);
        Integer end = Integer.valueOf((int)start + (int)this.limit);
        goodsRoleUser.setStart(start);
        goodsRoleUser.setEnd(end);
        List<GoodsRoleUser> goodsList = this.goodsRoleUsertMapper.selectGoodsByPage(goodsRoleUser);
        return goodsList;
    }
    
    @Override
    public Integer selectGoodsCount(GoodsRoleUser goodsRoleUser) {
        Integer goodsCount = this.goodsRoleUsertMapper.selectGoodsCount(goodsRoleUser);
        return goodsCount;
    }
}
