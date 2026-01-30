package org.come.bean;

import java.util.List;

public class UserRoleArrBean
{
    private List<LoginResult> list;
    private int phonestatues;
    private int index;
    private String seasonInfo;
    private String currSeason;
    
    public List<LoginResult> getList() {
        return this.list;
    }
    
    public void setList(List<LoginResult> list) {
        this.list = list;
    }
    
    public int getPhonestatues() {
        return this.phonestatues;
    }
    
    public void setPhonestatues(int phonestatues) {
        this.phonestatues = phonestatues;
    }
    
    public int getIndex() {
        return this.index;
    }
    
    public void setIndex(int index) {
        this.index = index;
    }
    
    public String getSeasonInfo() {
        return this.seasonInfo;
    }
    
    public void setSeasonInfo(String seasonInfo) {
        this.seasonInfo = seasonInfo;
    }
    
    public String getCurrSeason() {
        return this.currSeason;
    }
    
    public void setCurrSeason(String currSeason) {
        this.currSeason = currSeason;
    }
}
