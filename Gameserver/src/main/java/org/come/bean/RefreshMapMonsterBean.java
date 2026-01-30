package org.come.bean;

import org.come.task.MapMonsterBean;
import java.util.List;
import java.util.Map;

public class RefreshMapMonsterBean
{
    private Map<String, List<MapMonsterBean>> mapMonsterMap;
    
    public Map<String, List<MapMonsterBean>> getMapMonsterMap() {
        return this.mapMonsterMap;
    }
    
    public void setMapMonsterMap(Map<String, List<MapMonsterBean>> mapMonsterMap) {
        this.mapMonsterMap = mapMonsterMap;
    }
}
