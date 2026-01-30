package org.come.serviceImpl;

import org.come.redis.RedisControl;
import org.come.until.GsonUtil;
import org.come.model.Lshop;
import java.math.BigDecimal;
import org.come.service.LimitedTimeLshopService;

public class LimitedTimeLshopServiceImpl implements LimitedTimeLshopService
{
    @Override
    public void addReidsLimit(BigDecimal roleId, String packId, Lshop lshop, int expireTimeInSeconds) {
        RedisControl.insertValue("LIMITED_TIME_LSHOP", roleId.toString() + "_" + packId + "_" + lshop.getId(), GsonUtil.getGsonUtil().getgson().toJson(lshop), expireTimeInSeconds);
    }
    
    @Override
    public Lshop selectByID(BigDecimal roleId, String packId, int id) {
        Lshop lshop = (Lshop)RedisControl.getValue("LIMITED_TIME_LSHOP", roleId.toString() + "_" + packId + "_" + id, Lshop.class);
        return lshop;
    }
}
