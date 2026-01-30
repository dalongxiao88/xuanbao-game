package org.come.entity;

import java.util.List;

public class SearchSumminglist
{
    private int sumpage;
    private List<RolesummoningRoleUser> rolesummingList;
    
    public int getSumpage() {
        return this.sumpage;
    }
    
    public void setSumpage(int sumpage) {
        this.sumpage = sumpage;
    }
    
    public List<RolesummoningRoleUser> getRolesummingList() {
        return this.rolesummingList;
    }
    
    public void setRolesummingList(List<RolesummoningRoleUser> rolesummingList) {
        this.rolesummingList = rolesummingList;
    }
}
