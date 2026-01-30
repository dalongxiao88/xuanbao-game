package org.come.server;

import java.awt.Polygon;

public class Point extends Polygon
{
    private int mapId;
    private int x;
    private int y;
    
    public Point generatePointsInRegion() {
        int minX = this.getBounds().x;
        int minY = this.getBounds().y;
        int x;
        int y;
        do {
            x = GameServer.random.nextInt(this.getBounds().width + 1) + minX;
            y = GameServer.random.nextInt(this.getBounds().height + 1) + minY;
        } while (!this.contains(x, y));
        this.x = x;
        this.y = y;
        return this;
    }
    
    public boolean contains(int mapId, int x, int y) {
        return this.mapId == mapId && this.contains(x, y);
    }
    
    public int getMapId() {
        return this.mapId;
    }
    
    public void setMapId(int mapId) {
        this.mapId = mapId;
    }
    
    public int getX() {
        return this.x;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public void setY(int y) {
        this.y = y;
    }
}
