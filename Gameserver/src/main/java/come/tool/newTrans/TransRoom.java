package come.tool.newTrans;

public class TransRoom
{
    private int transId;
    private TransRole role1;
    private TransRole role2;
    
    public TransRoom(int transId, TransRole role1, TransRole role2) {
        this.transId = transId;
        this.role1 = role1;
        this.role2 = role2;
    }
    
    public boolean isTrans() {
        return this.role1.getState() != 0 && this.role2.getState() != 0;
    }
    
    public int getTransId() {
        return this.transId;
    }
    
    public void setTransId(int transId) {
        this.transId = transId;
    }
    
    public TransRole getRole1() {
        return this.role1;
    }
    
    public void setRole1(TransRole role1) {
        this.role1 = role1;
    }
    
    public TransRole getRole2() {
        return this.role2;
    }
    
    public void setRole2(TransRole role2) {
        this.role2 = role2;
    }
}
