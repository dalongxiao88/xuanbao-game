package org.come.bean;

import org.come.entity.Titletable;
import java.util.List;

public class TitleBean
{
    private List<Titletable> titletables;
    
    public List<Titletable> getTitletables() {
        return this.titletables;
    }
    
    public void setTitletables(List<Titletable> titletables) {
        this.titletables = titletables;
    }
}
