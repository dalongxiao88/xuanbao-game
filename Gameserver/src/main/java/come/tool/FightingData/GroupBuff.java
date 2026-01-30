package come.tool.FightingData;
//群体buff
public class GroupBuff
{
    private int buffId;//buffid
    private String buffType;
    private ManData data;//buff所属者
    private int camp;
    private double value;
    private double value2;
    
    public GroupBuff(int buffId, String buffType, ManData data, double value) {
        this.buffId = buffId;
        this.buffType = buffType;
        this.data = data;
        this.value = value;
        this.camp = data.getCamp();
    }
    
    public int getBuffId() {
        return this.buffId;
    }
    
    public void setBuffId(int buffId) {
        this.buffId = buffId;
    }
    
    public String getBuffType() {
        return this.buffType;
    }
    
    public void setBuffType(String buffType) {
        this.buffType = buffType;
    }
    
    public double getValue() {
        return this.value;
    }
    
    public void setValue(double value) {
        this.value = value;
    }
    
    public int getCamp() {
        return this.camp;
    }
    
    public void setCamp(int camp) {
        this.camp = camp;
    }
    
    public ManData getData() {
        return this.data;
    }
    
    public void setData(ManData data) {
        this.data = data;
    }
    
    public double getValue2() {
        return this.value2;
    }
    
    public void setValue2(double value2) {
        this.value2 = value2;
    }
}
