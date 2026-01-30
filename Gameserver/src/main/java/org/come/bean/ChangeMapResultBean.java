package org.come.bean;

import org.come.task.MapMonsterBean;
import java.util.Map;
import org.come.model.Door;
import org.come.model.Npctable;
import java.util.List;

public class ChangeMapResultBean
{
    private List<LoginResult> userRoleArr;
    private List<Npctable> npctables;
    private List<Door> doors;
    private Map<String, List<MapMonsterBean>> mapMonsterMap;
    
    public List<LoginResult> getUserRoleArr() {
        return this.userRoleArr;
    }
    
    public void setUserRoleArr(List<LoginResult> userRoleArr) {
        this.userRoleArr = userRoleArr;
    }
    
    public List<Npctable> getNpctables() {
        return this.npctables;
    }
    
    public void setNpctables(List<Npctable> npctables) {
        this.npctables = npctables;
    }
    
    public List<Door> getDoors() {
        return this.doors;
    }
    
    public void setDoors(List<Door> doors) {
        this.doors = doors;
    }
    
    public Map<String, List<MapMonsterBean>> getMapMonsterMap() {
        return this.mapMonsterMap;
    }
    
    public void setMapMonsterMap(Map<String, List<MapMonsterBean>> mapMonsterMap) {
        this.mapMonsterMap = mapMonsterMap;
    }
}
