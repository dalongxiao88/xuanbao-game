package org.come.bean;

import org.come.entity.Baby;
import java.util.List;

public class BabyListBean
{
    private List<Baby> babyList;
    
    public List<Baby> getBabyList() {
        return this.babyList;
    }
    
    public void setBabyList(List<Baby> babyList) {
        this.babyList = babyList;
    }
}
