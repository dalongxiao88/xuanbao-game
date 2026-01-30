package org.come.serviceImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.text.DateFormat;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;
import org.come.redis.RedisControl;
import org.come.redis.RedisParameterUtil;
import org.come.model.AutoActiveReidsBase;
import java.util.concurrent.ConcurrentHashMap;
import org.come.service.IAutoTaskService;

public class AutoTaskServiceImpl implements IAutoTaskService
{
    @Override
    public ConcurrentHashMap<String, AutoActiveReidsBase> getAllList(String roleId) {
        List<AutoActiveReidsBase> autotasklimits = RedisControl.getS(RedisParameterUtil.AUTOTASKLIMIT, roleId, AutoActiveReidsBase.class);
        ConcurrentHashMap<String, AutoActiveReidsBase> map = new ConcurrentHashMap<>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String nowdayTime = dateFormat.format(new Date());
        if (autotasklimits != null && autotasklimits.size() > 0) {
            for (AutoActiveReidsBase item : autotasklimits) {
                if (nowdayTime.equals(item.getNumData())) {
                    map.put(String.valueOf(item.getId()), item);
                }
            }
        }
        return map;
    }
    
    @Override
    public List<AutoActiveReidsBase> getAllComList(String roleId) {
        List<AutoActiveReidsBase> autotasklimits = RedisControl.getS(RedisParameterUtil.AUTOTASKLIMIT, roleId, AutoActiveReidsBase.class);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<AutoActiveReidsBase> result = new ArrayList<>();
        String nowdayTime = dateFormat.format(new Date());
        if (autotasklimits != null && autotasklimits.size() > 0) {
            for (AutoActiveReidsBase item : autotasklimits) {
                if (nowdayTime.equals(item.getNumData())) {
                    result.add(item);
                }
            }
        }
        return result;
    }
    
    @Override
    public AutoActiveReidsBase selectByID(String roleId, String id) {
        AutoActiveReidsBase autotaskLimit = (AutoActiveReidsBase)RedisControl.getV(RedisParameterUtil.AUTOTASKLIMIT, roleId + id, AutoActiveReidsBase.class);
        return autotaskLimit;
    }
    
    @Override
    public void deleteByID(String roleId, String id) {
        RedisControl.deletrValue(RedisParameterUtil.AUTOTASKLIMIT, roleId, roleId + id);
        RedisControl.delForKey(RedisParameterUtil.AUTOTASKLIMIT, roleId + id);
    }
    
    @Override
    public void addReidsLimit(AutoActiveReidsBase autotaskLimit) {
        RedisControl.insertKeyT(RedisParameterUtil.AUTOTASKLIMIT, autotaskLimit.getRoleId().toString() + autotaskLimit.getId(), autotaskLimit);
        RedisControl.insertListRedis(RedisParameterUtil.AUTOTASKLIMIT, autotaskLimit.getRoleId().toString(), autotaskLimit.getRoleId().toString() + String.valueOf(autotaskLimit.getId()));
    }
    
    @Override
    public void updateReidsLimit(AutoActiveReidsBase autotaskLimit) {
        RedisControl.insertKeyT(RedisParameterUtil.AUTOTASKLIMIT, autotaskLimit.getRoleId().toString() + autotaskLimit.getId(), autotaskLimit);
    }
}
