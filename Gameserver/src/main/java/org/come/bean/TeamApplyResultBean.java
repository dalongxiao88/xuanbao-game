package org.come.bean;

import java.util.List;

public class TeamApplyResultBean
{
    private List<String> roleInfoList;
    private int joinTeam;
    
    public List<String> getRoleInfoList() {
        return this.roleInfoList;
    }
    
    public void setRoleInfoList(List<String> roleInfoList) {
        this.roleInfoList = roleInfoList;
    }
    
    public int getJoinTeam() {
        return this.joinTeam;
    }
    
    public void setJoinTeam(int joinTeam) {
        this.joinTeam = joinTeam;
    }
}
