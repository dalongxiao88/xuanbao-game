package come.tool.Stall;

import java.math.BigDecimal;

public class StallBean
{
    private int id;
    private int mapid;
    private String role;
    private BigDecimal roleid;
    private String stall;
    private int state;
    private int x;
    private int y;
    private boolean isPurchase;
    
    public StallBean(Stall stall) {
        this.id = stall.getId();
        this.mapid = stall.getMapid();
        this.role = stall.getRole();
        this.roleid = stall.getRoleid();
        this.stall = stall.getStall();
        this.state = stall.getState();
        this.x = stall.getX();
        this.y = stall.getY();
    }
    
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getMapid() {
        return this.mapid;
    }
    
    public void setMapid(int mapid) {
        this.mapid = mapid;
    }
    
    public String getRole() {
        return this.role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public BigDecimal getRoleid() {
        return this.roleid;
    }
    
    public void setRoleid(BigDecimal roleid) {
        this.roleid = roleid;
    }
    
    public String getStall() {
        return this.stall;
    }
    
    public void setStall(String stall) {
        this.stall = stall;
    }
    
    public int getState() {
        return this.state;
    }
    
    public void setState(int state) {
        this.state = state;
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
    
    public boolean isPurchase() {
        return this.isPurchase;
    }
    
    public void setPurchase(boolean purchase) {
        this.isPurchase = purchase;
    }
}
