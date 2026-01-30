package come.tool.FightingData;

public class AICondition
{
    private int x;
    private int y;
    private String sy;
    private Boolean b;
    
    public AICondition() {
        this.b = Boolean.valueOf(false);
    }
    
    public AICondition(int x, int y) {
        this.b = Boolean.valueOf(false);
        this.x = x;
        this.y = y;
    }
    
    public AICondition(int x, String sy) {
        this.b = Boolean.valueOf(false);
        this.x = x;
        this.sy = sy;
    }
    
    public AICondition(int x, int y, String sy) {
        this.b = Boolean.valueOf(false);
        this.x = x;
        this.y = y;
        this.sy = sy;
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
    
    public String getSy() {
        return this.sy;
    }
    
    public void setSy(String sy) {
        this.sy = sy;
    }
    
    public Boolean getB() {
        return this.b;
    }
    
    public void setB(Boolean b) {
        this.b = b;
    }
}
