package org.come.bean;

import org.come.model.Achieve;
import java.util.List;

public class AllAchieve
{
    private List<Achieve> achieves;
    
    public AllAchieve(List<Achieve> achieves) {
        this.achieves = achieves;
    }
    
    public List<Achieve> getAchieves() {
        return this.achieves;
    }
    
    public void setAchieves(List<Achieve> achieves) {
        this.achieves = achieves;
    }
}
