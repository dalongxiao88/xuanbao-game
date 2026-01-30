package org.come.bean;

import java.math.BigDecimal;
import java.util.HashMap;

public class QianDaoBean
{
    private HashMap<BigDecimal, QianDao> qianDaoConcurrentHashMap;
    
    public HashMap<BigDecimal, QianDao> getAllQianDao() {
        return this.qianDaoConcurrentHashMap;
    }
    
    public void setAllPetExchange(HashMap<BigDecimal, QianDao> allQianDao) {
    }
}
