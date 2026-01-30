package org.come.bean;

import org.come.task.MapMonsterBean;
import java.util.List;

public class MonsterListBean
{
    private List<MapMonsterBean> monsterList;
    
    public List<MapMonsterBean> getMonsterList() {
        return this.monsterList;
    }
    
    public void setMonsterList(List<MapMonsterBean> monsterList) {
        this.monsterList = monsterList;
    }
}
