package org.come.service;

import org.come.model.DiceReidsBase;

public interface IDiceService
{
    DiceReidsBase selectByID(String p0);
    
    void deleteByID(String p0);
    
    void addReidsLimit(DiceReidsBase p0);
    
    void updateReidsLimit(DiceReidsBase p0);
}
