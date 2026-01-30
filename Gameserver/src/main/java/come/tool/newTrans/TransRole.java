package come.tool.newTrans;

import java.math.BigDecimal;

public class TransRole
{
    private BigDecimal role_id;
    private String rolename;
    private int state;
    private GoodTrans goodTrans;
    
    public TransRole(BigDecimal role_id, String rolename) {
        this.role_id = role_id;
        this.rolename = rolename;
        this.goodTrans = new GoodTrans();
    }
    
    public BigDecimal getRole_id() {
        return this.role_id;
    }
    
    public void setRole_id(BigDecimal role_id) {
        this.role_id = role_id;
    }
    
    public String getRolename() {
        return this.rolename;
    }
    
    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
    
    public int getState() {
        return this.state;
    }
    
    public void setState(int state) {
        this.state = state;
    }
    
    public GoodTrans getGoodTrans() {
        return this.goodTrans;
    }
    
    public void setGoodTrans(GoodTrans goodTrans) {
        this.goodTrans = goodTrans;
    }
}
