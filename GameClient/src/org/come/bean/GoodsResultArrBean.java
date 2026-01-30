package org.come.bean;

import java.util.ArrayList;
import org.come.entity.Goodstable;
import java.util.List;

public class GoodsResultArrBean
{
    private int I;
    private List<Goodstable> list;
    
    public List<Goodstable> getList() {
        if (this.list == null) {
            this.list = new ArrayList<>();
        }
        return this.list;
    }
    
    public void setList(List<Goodstable> list) {
        this.list = list;
    }
    
    public int getI() {
        return this.I;
    }
    
    public void setI(int i) {
        this.I = i;
    }
}
