package org.come.readBean;

import org.come.model.PetExchange;
import java.util.concurrent.ConcurrentHashMap;

public class allPetExchange
{
    private ConcurrentHashMap<Integer, PetExchange> allPetExchange;
    
    public ConcurrentHashMap<Integer, PetExchange> getAllPetExchange() {
        return this.allPetExchange;
    }
    
    public void setAllPetExchange(ConcurrentHashMap<Integer, PetExchange> allPetExchange) {
        this.allPetExchange = allPetExchange;
    }
}
