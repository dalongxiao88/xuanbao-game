package org.come.bean;

import org.come.server.GameServer;

public class ChangeMapBean
{
    private String mapid;
    private int mapx;
    private int mapy;
    private int type;
    
    public ChangeMapBean() {
    }
    
    public ChangeMapBean(String mapid, int mapx, int mapy) {
        this.mapid = mapid;
        this.mapx = mapx;
        this.mapy = mapy;
    }
    
    public ChangeMapBean(long mapid, int mapx, int mapy) {
        this.mapid = String.valueOf(mapid);
        this.mapx = mapx + this.random();
        this.mapy = mapy + this.random();
    }
    
    private int random() {
        return 60 - GameServer.random.nextInt(121);
    }
    
    public String getMapid() {
        return this.mapid;
    }
    
    public int getMapx() {
        return this.mapx;
    }
    
    public void setMapx(int mapx) {
        this.mapx = mapx;
    }
    
    public int getMapy() {
        return this.mapy;
    }
    
    public void setMapy(int mapy) {
        this.mapy = mapy;
    }
    
    public int getType() {
        return this.type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    
    public void setMapid(String mapid) {
        this.mapid = mapid;
    }
}
