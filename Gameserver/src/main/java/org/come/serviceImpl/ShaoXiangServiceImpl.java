package org.come.serviceImpl;

import java.util.Iterator;
import java.text.DateFormat;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;
import org.come.redis.RedisControl;
import org.come.redis.RedisParameterUtil;
import org.come.model.ShaoXiangLimit;
import java.util.concurrent.ConcurrentHashMap;
import org.come.service.IShaoXiangService;

public class ShaoXiangServiceImpl implements IShaoXiangService
{
    @Override
    public ConcurrentHashMap<String, ShaoXiangLimit> getAllList(String roleId) {
        List<ShaoXiangLimit> shaoxianglimits = RedisControl.getS(RedisParameterUtil.SHAOXIANGLIMMIT, roleId, ShaoXiangLimit.class);
        ConcurrentHashMap<String, ShaoXiangLimit> map = new ConcurrentHashMap<>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String nowdayTime = dateFormat.format(new Date());
        if (shaoxianglimits != null && shaoxianglimits.size() > 0) {
            for (ShaoXiangLimit item : shaoxianglimits) {
                if (nowdayTime.equals(item.getDate())) {
                    map.put(item.getName(), item);
                }
            }
        }
        return map;
    }
    
    @Override
    public ShaoXiangLimit selectByID(String roleId, String id) {
        ShaoXiangLimit shaoXiangLimit = (ShaoXiangLimit)RedisControl.getV(RedisParameterUtil.SHAOXIANGLIMMIT, roleId + id, ShaoXiangLimit.class);
        return shaoXiangLimit;
    }
    
    @Override
    public void deleteByID(String roleId, String id) {
        RedisControl.deletrValue(RedisParameterUtil.SHAOXIANGLIMMIT, roleId, roleId + id);
        RedisControl.delForKey(RedisParameterUtil.SHAOXIANGLIMMIT, roleId + id);
    }
    
    @Override
    public void addReidsLimit(ShaoXiangLimit shaoXiangLimit) {
        RedisControl.insertKeyT(RedisParameterUtil.SHAOXIANGLIMMIT, shaoXiangLimit.getRoleId().toString() + shaoXiangLimit.getId(), shaoXiangLimit);
        RedisControl.insertListRedis(RedisParameterUtil.SHAOXIANGLIMMIT, shaoXiangLimit.getRoleId().toString(), shaoXiangLimit.getRoleId() + shaoXiangLimit.getId());
    }
    
    @Override
    public void updateReidsLimit(ShaoXiangLimit shaoXiangLimit) {
        RedisControl.insertKeyT(RedisParameterUtil.SHAOXIANGLIMMIT, shaoXiangLimit.getRoleId().toString() + shaoXiangLimit.getId(), shaoXiangLimit);
    }
}
