package org.come.model;

import java.util.concurrent.ConcurrentHashMap;

public class allPetExchange
{
    private ConcurrentHashMap<Integer, petExchange> allPetExchange;
    
    public ConcurrentHashMap<Integer, petExchange> getAllPetExchange() {
        return this.allPetExchange;
    }
    
    public void setAllPetExchange(ConcurrentHashMap<Integer, petExchange> allPetExchange) {
        this.allPetExchange = allPetExchange;
    }
}
