package come.tool.Scene.ZZS;

public class ZZSAward
{
    private int type;
    private boolean isAward;
    
    public ZZSAward(int type) {
        this.type = type;
    }
    
    public int getType() {
        return this.type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    
    public boolean isAward() {
        return this.isAward;
    }
    
    public void setAward(boolean isAward) {
        this.isAward = isAward;
    }
}
