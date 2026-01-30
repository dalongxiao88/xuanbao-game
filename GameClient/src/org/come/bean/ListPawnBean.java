package org.come.bean;

import org.come.entity.Goodstable;
import java.util.List;

public class ListPawnBean
{
    private List<Goodstable> PawnList;
    
    public List<Goodstable> getPawnList() {
        return this.PawnList;
    }
    
    public void setPawnList(List<Goodstable> pawnList) {
        this.PawnList = pawnList;
    }
}
