package org.come.bean;

import org.come.model.Sghostpoint;
import java.util.List;
import java.util.Map;

public class SghostpointBean
{
    private Map<String, List<Sghostpoint>> monsterTask;
    
    public Map<String, List<Sghostpoint>> getMonsterTask() {
        return this.monsterTask;
    }
    
    public void setMonsterTask(Map<String, List<Sghostpoint>> monsterTask) {
        this.monsterTask = monsterTask;
    }
}
