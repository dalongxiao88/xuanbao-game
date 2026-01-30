package come.tool.Scene.DNTG;

public class DNTGCountDown
{
    private int type;
    private long endTime;
    
    public DNTGCountDown(int type, int time) {
        this.type = type;
        this.endTime = System.currentTimeMillis() + (long)(time * 1000 * 60);
    }
    
    public void toString(StringBuffer buffer) {
        buffer.append(this.type);
        buffer.append("$");
        buffer.append(this.endTime);
    }
    
    public int getType() {
        return this.type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    
    public long getEndTime() {
        return this.endTime;
    }
    
    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
}
