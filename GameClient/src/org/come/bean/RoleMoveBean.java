package org.come.bean;

import java.util.ArrayList;
import java.util.List;

public class RoleMoveBean
{
    List<PathPoint> Paths;
    private String role;
    
    public RoleMoveBean() {
        this.Paths = new ArrayList<>();
    }
    
    public List<PathPoint> getPaths() {
        return this.Paths;
    }
    
    public void setPaths(List<PathPoint> paths) {
        this.Paths = paths;
    }
    
    public String getRole() {
        return this.role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
}
