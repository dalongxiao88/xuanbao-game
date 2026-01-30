package org.cbg.bean;

import org.cbg.entity.Roleorder;
import java.util.List;

public class SearchOrderResultBean
{
    private List<Roleorder> roleorders;
    private Integer total;
    
    public List<Roleorder> getRoleorders() {
        return this.roleorders;
    }
    
    public void setRoleorders(List<Roleorder> roleorders) {
        this.roleorders = roleorders;
    }
    
    public Integer getTotal() {
        return this.total;
    }
    
    public void setTotal(Integer total) {
        this.total = total;
    }
}
