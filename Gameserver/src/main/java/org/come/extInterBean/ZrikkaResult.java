package org.come.extInterBean;

import java.util.List;

public class ZrikkaResult
{
    private String type;
    private List<AllOnline> allOnline;
    
    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public List<AllOnline> getAllOnline() {
        return this.allOnline;
    }
    
    public void setAllOnline(List<AllOnline> allOnline) {
        this.allOnline = allOnline;
    }
}
