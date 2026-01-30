package come.tool.Good;

public class HpSet
{
    private long hp;
    private long hpMax;
    private boolean isMuch;
    
    public HpSet(String msg) {
        if (msg != null && msg.split("=").length >= 4) {
            String[] v = msg.split("=");
            this.hp = (long)(int)Integer.valueOf(v[1]);
            this.hpMax = (long)(int)Integer.valueOf(v[2]);
            int isM = (int)Integer.valueOf(v[3]);
            if (isM == 1) {
                this.isMuch = true;
            }
            else {
                this.isMuch = false;
            }
        }
    }
    
    public long getHp() {
        return this.hp;
    }
    
    public void setHp(long hp) {
        this.hp = hp;
    }
    
    public long getHpMax() {
        return this.hpMax;
    }
    
    public void setHpMax(long hpMax) {
        this.hpMax = hpMax;
    }
    
    public boolean isMuch() {
        return this.isMuch;
    }
    
    public void setMuch(boolean much) {
        this.isMuch = much;
    }
}
