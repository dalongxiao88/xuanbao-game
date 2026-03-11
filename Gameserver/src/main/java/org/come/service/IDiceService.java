package org.come.service;

import org.come.model.DiceReidsBase;

/**
 * 掷骰限制服务接口。
 */
public interface IDiceService
{
    DiceReidsBase selectByID(String roleId);
    
    void deleteByID(String roleId);
    
    void addReidsLimit(DiceReidsBase diceReidsBase);
    
    void updateReidsLimit(DiceReidsBase diceReidsBase);
}
