package org.come.bean;

import org.come.model.Door;
import java.util.List;
import org.come.model.Gamemap;

public class MapInfoBean
{
    private Gamemap gamemap;
    private List<Door> doors;
    private List<NpcInfoBean> npcInfoBeans;
    
    public List<Door> getDoors() {
        return this.doors;
    }
    
    public void setDoors(List<Door> doors) {
        this.doors = doors;
    }
    
    public List<NpcInfoBean> getNpcInfoBeans() {
        return this.npcInfoBeans;
    }
    
    public void setNpcInfoBeans(List<NpcInfoBean> npcInfoBeans) {
        this.npcInfoBeans = npcInfoBeans;
    }
    
    public Gamemap getGamemap() {
        return this.gamemap;
    }
    
    public void setGamemap(Gamemap gamemap) {
        this.gamemap = gamemap;
    }
}
