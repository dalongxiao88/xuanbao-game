package org.come.bean;

import org.come.model.Npctable;
import java.util.List;
import org.come.model.Gamemap;

public class MapNpcResultBean
{
    private Gamemap gamemap;
    private List<Npctable> npctables;
    
    public Gamemap getGamemap() {
        return this.gamemap;
    }
    
    public void setGamemap(Gamemap gamemap) {
        this.gamemap = gamemap;
    }
    
    public List<Npctable> getNpctables() {
        return this.npctables;
    }
    
    public void setNpctables(List<Npctable> npctables) {
        this.npctables = npctables;
    }
}
