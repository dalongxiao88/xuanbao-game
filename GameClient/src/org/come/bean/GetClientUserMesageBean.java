package org.come.bean;

import com.tool.Stall.StallBean;
import java.util.List;

public class GetClientUserMesageBean
{
    private int ismap;
    private List<RoleShow> roleShows;
    private String monster;
    private List<StallBean> stallBeans;
    private String sceneMsg;
    
    public String getSceneMsg() {
        return this.sceneMsg;
    }
    
    public void setSceneMsg(String sceneMsg) {
        this.sceneMsg = sceneMsg;
    }
    
    public int getIsmap() {
        return this.ismap;
    }
    
    public void setIsmap(int ismap) {
        this.ismap = ismap;
    }
    
    public List<RoleShow> getRoleShows() {
        return this.roleShows;
    }
    
    public void setRoleShows(List<RoleShow> roleShows) {
        this.roleShows = roleShows;
    }
    
    public String getMonster() {
        return this.monster;
    }
    
    public void setMonster(String monster) {
        this.monster = monster;
    }
    
    public List<StallBean> getStallBeans() {
        return this.stallBeans;
    }
    
    public void setStallBeans(List<StallBean> stallBeans) {
        this.stallBeans = stallBeans;
    }
}
