package org.come.bean;

import org.come.entity.RoleSummoning;
import java.util.Map;

public class PetBean
{
    private Map<String, RoleSummoning> allPetInfo;
    
    public Map<String, RoleSummoning> getAllPetInfo() {
        return this.allPetInfo;
    }
    
    public void setAllPetInfo(Map<String, RoleSummoning> allPetInfo) {
        this.allPetInfo = allPetInfo;
    }
}
