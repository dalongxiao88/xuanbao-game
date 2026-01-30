package org.come.bean;

import org.come.model.Monster;
import java.util.Map;

public class MonsterBean
{
    private Map<String, Monster> monsterMap;
    
    public Map<String, Monster> getMonsterMap() {
        return this.monsterMap;
    }
    
    public void setMonsterMap(Map<String, Monster> monsterMap) {
        this.monsterMap = monsterMap;
    }
}
