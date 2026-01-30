package org.come.bean;

import org.come.entity.Fly;
import java.util.List;

public class FlyResult
{
    private List<Fly> flys;
    
    public List<Fly> gettFlys() {
        return this.flys;
    }
    
    public void setFlys(List<Fly> flys) {
        this.flys = flys;
    }
}
