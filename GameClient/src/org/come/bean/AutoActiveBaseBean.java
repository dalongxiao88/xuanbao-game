package org.come.bean;

import org.come.model.AutoTaskBase;
import java.util.List;

public class AutoActiveBaseBean
{
    List<AutoActiveBase> allautobase;
    List<AutoTaskBase> atlist;
    
    public List<AutoTaskBase> getAtlist() {
        return this.atlist;
    }
    
    public void setAtlist(List<AutoTaskBase> atlist) {
        this.atlist = atlist;
    }
    
    public List<AutoActiveBase> getAllautobase() {
        return this.allautobase;
    }
    
    public void setAllautobase(List<AutoActiveBase> allautobase) {
        this.allautobase = allautobase;
    }
}
