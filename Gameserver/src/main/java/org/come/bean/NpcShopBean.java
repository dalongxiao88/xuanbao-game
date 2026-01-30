package org.come.bean;

import org.come.model.Shop;
import java.util.List;
import java.util.Map;

public class NpcShopBean
{
    private Map<String, List<Shop>> npcShopMap;
    
    public Map<String, List<Shop>> getNpcShopMap() {
        return this.npcShopMap;
    }
    
    public void setNpcShopMap(Map<String, List<Shop>> npcShopMap) {
        this.npcShopMap = npcShopMap;
    }
}
