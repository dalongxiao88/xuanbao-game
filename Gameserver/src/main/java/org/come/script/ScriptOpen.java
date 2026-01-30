package org.come.script;

public class ScriptOpen
{
    private int type;
    private int mapId;
    private int x;
    private int y;
    private int robotId;
    private String taskId;
    private String alias;
    private int killType;
    
    public ScriptOpen(int x, int y) {
        this.type = 0;
        this.x = x;
        this.y = y;
    }
    
    public ScriptOpen(int mapId, int x, int y) {
        this.type = 1;
        this.mapId = mapId;
        this.x = x;
        this.y = y;
    }
    
    public ScriptOpen(String taskId) {
        this.type = 2;
        this.taskId = taskId;
    }
    
    public ScriptOpen(String alias, int robotId, int killType) {
        this.type = 3;
        this.alias = alias;
        this.robotId = robotId;
        this.killType = killType;
    }
    
    public ScriptOpen(String alias, String taskId, int robotId, int killType) {
        this.type = 4;
        this.alias = alias;
        this.taskId = taskId;
        this.robotId = robotId;
        this.killType = killType;
    }
    
    public int getType() {
        return this.type;
    }
    
    public int getMapId() {
        return this.mapId;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public int getRobotId() {
        return this.robotId;
    }
    
    public String getTaskId() {
        return this.taskId;
    }
    
    public String getAlias() {
        return this.alias;
    }
    
    public int getKillType() {
        return this.killType;
    }
}
