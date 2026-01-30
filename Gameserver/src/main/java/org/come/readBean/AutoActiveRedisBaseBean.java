package org.come.readBean;

import org.come.model.AutoActiveReidsBase;
import java.util.List;

public class AutoActiveRedisBaseBean
{
    List<AutoActiveReidsBase> allautobase;
    
    public List<AutoActiveReidsBase> getAllautobase() {
        return this.allautobase;
    }
    
    public void setAllautobase(List<AutoActiveReidsBase> allautobase) {
        this.allautobase = allautobase;
    }
}
