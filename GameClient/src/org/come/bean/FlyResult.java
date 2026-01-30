package org.come.bean;

import org.come.entity.Fly;
import java.util.List;

public class FlyResult
{
    private List<Fly> flys;
    private Boolean showForm;
    
    public List<Fly> getFlys() {
        return this.flys;
    }
    
    public void setFlys(List<Fly> flys) {
        this.flys = flys;
    }
    
    public Boolean getShowForm() {
        return this.showForm;
    }
    
    public void setShowForm(Boolean showForm) {
        this.showForm = showForm;
    }
}
