package org.come.serviceImpl;

import java.math.BigDecimal;
import org.come.redis.RedisControl;
import org.come.redis.RedisParameterUtil;
import org.come.model.DiceReidsBase;
import org.come.service.IDiceService;

public class DiceServiceImpl implements IDiceService
{
    @Override
    public DiceReidsBase selectByID(String roleId) {
        DiceReidsBase diceReidsBase = (DiceReidsBase)RedisControl.getV(RedisParameterUtil.DICE, roleId, DiceReidsBase.class);
        if (diceReidsBase != null) {
            return diceReidsBase;
        }
        diceReidsBase = new DiceReidsBase();
        diceReidsBase.setRoleId(new BigDecimal(roleId));
        return diceReidsBase;
    }
    
    @Override
    public void deleteByID(String roleId) {
        RedisControl.deletrValue(RedisParameterUtil.DICE, roleId, roleId);
        RedisControl.delForKey(RedisParameterUtil.DICE, roleId);
    }
    
    @Override
    public void updateReidsLimit(DiceReidsBase diceReidsBase) {
        RedisControl.insertKeyT(RedisParameterUtil.DICE, diceReidsBase.getRoleId().toString(), diceReidsBase);
    }
    
    @Override
    public void addReidsLimit(DiceReidsBase diceReidsBase) {
        RedisControl.insertKeyT(RedisParameterUtil.DICE, diceReidsBase.getRoleId().toString(), diceReidsBase);
        RedisControl.insertListRedis(RedisParameterUtil.DICE, diceReidsBase.getRoleId().toString(), diceReidsBase.getRoleId().toString());
    }
}
