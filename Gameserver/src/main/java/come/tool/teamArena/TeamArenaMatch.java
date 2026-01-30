package come.tool.teamArena;

import java.math.BigDecimal;

public class TeamArenaMatch
{
    private int JF;
    private int type;
    private long time;
    private BigDecimal[] ids;
    private int state;
    
    public TeamArenaMatch(int size) {
        this.ids = new BigDecimal[size];
        this.time = System.currentTimeMillis();
    }
    
    public int isIds(BigDecimal id) {
        synchronized (this) {
            for (int i = 0; i < this.ids.length; ++i) {
                if (this.ids[i] == null) {
                    this.ids[i] = id;
                    return (i + 1 == this.ids.length) ? 2 : 1;
                }
                if (this.ids[i].compareTo(id) == 0) {
                    return 0;
                }
            }
            return 0;
        }
    }
    
    public int getJF() {
        return this.JF;
    }
    
    public void setJF(int jF) {
        this.JF = jF;
    }
    
    public long getTime() {
        return this.time;
    }
    
    public void setTime(long time) {
        this.time = time;
    }
    
    public BigDecimal[] getIds() {
        return this.ids;
    }
    
    public void setIds(BigDecimal[] ids) {
        this.ids = ids;
    }
    
    public int getState() {
        return this.state;
    }
    
    public void setState(int state) {
        this.state = state;
    }
    
    public int getType() {
        return this.type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
}
