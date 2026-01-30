package org.come.readBean;

import org.come.model.PalData;
import java.util.concurrent.ConcurrentHashMap;

public class AllPal
{
    private ConcurrentHashMap<Integer, PalData> allPalData;
    
    public ConcurrentHashMap<Integer, PalData> getAllPalData() {
        return this.allPalData;
    }
    
    public void setAllPalData(ConcurrentHashMap<Integer, PalData> allPalData) {
        this.allPalData = allPalData;
    }
}
