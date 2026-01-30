package org.come.server;

import org.come.task.MapMonsterBean;
import java.util.List;
import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;

public class GameMap
{
    private long mapId;
    private int type;
    private ConcurrentHashMap<BigDecimal, GameMap> hashMap;
    private List<MapMonsterBean> robotList;
    
    public GameMap(long mapId) {
        this.mapId = mapId;
    }
    
    public int getType() {
        return this.type;
    }
}
