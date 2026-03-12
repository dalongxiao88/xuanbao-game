package org.come.serviceImpl;

import java.util.List;
import org.come.entity.GoodsRoleUser;
import org.springframework.context.ApplicationContext;
import org.come.until.MybatisUntil;
import org.come.mapper.GoodsRoleUsertMapper;
import org.come.service.GoodsRoleUsertService;

public class GoodsRoleUsertServiceImpl implements GoodsRoleUsertService {
    private GoodsRoleUsertMapper goodsRoleUsertMapper;
    private final Integer limit;

    public GoodsRoleUsertServiceImpl() {
        this.limit = Integer.valueOf(10);
        ApplicationContext ctx = MybatisUntil.getApplicationContext();
        this.goodsRoleUsertMapper = (GoodsRoleUsertMapper)ctx.getBean("goodsRoleUsertMapper", GoodsRoleUsertMapper.class);
    }

    @Override
    public List<GoodsRoleUser> selectGoodsByPage(GoodsRoleUser queryCondition) {
        Integer start = Integer.valueOf(((int)queryCondition.getPageNow() - 1) * (int)this.limit + 1);
        Integer end = Integer.valueOf((int)start + (int)this.limit);
        queryCondition.setStart(start);
        queryCondition.setEnd(end);
        List<GoodsRoleUser> goodsList = this.goodsRoleUsertMapper.selectGoodsByPage(queryCondition);
        return goodsList;
    }

    @Override
    public Integer selectGoodsCount(GoodsRoleUser queryCondition) {
        Integer goodsCount = this.goodsRoleUsertMapper.selectGoodsCount(queryCondition);
        return goodsCount;
    }
}
