package org.come.bean;

import org.come.entity.RoleTable;
import java.util.List;

public class UserRoleChangeBean
{
    private List<RoleTable> roleList;
    private List<RoleTable> otherRoleList;
    
    public UserRoleChangeBean() {
    }
    
    public UserRoleChangeBean(List<RoleTable> roleList, List<RoleTable> otherRoleList) {
        this.roleList = roleList;
        this.otherRoleList = otherRoleList;
    }
    
    public List<RoleTable> getRoleList() {
        return this.roleList;
    }
    
    public void setRoleList(List<RoleTable> roleList) {
        this.roleList = roleList;
    }
    
    public List<RoleTable> getOtherRoleList() {
        return this.otherRoleList;
    }
    
    public void setOtherRoleList(List<RoleTable> otherRoleList) {
        this.otherRoleList = otherRoleList;
    }
}
