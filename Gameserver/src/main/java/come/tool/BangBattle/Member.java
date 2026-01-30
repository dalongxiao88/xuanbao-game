package come.tool.BangBattle;

import java.math.BigDecimal;
//参与成员
public class Member
{
    private String key;//键值
    private BigDecimal camp;//阵营
    private int state;//状态                       -1 离开  0正常  1休息 2冰冻 3挑战状态  4充能或者攻击
    private long time;//时间  记录参与时间
    private long time2;//时间  记录状态剩余时间
    //处理
    public boolean process() {
        ++this.time;
        if (this.state == 1 || this.state == 2) {
            --this.time2;
            if (this.time2 <= 0L) {
                this.time2 = 0L;
                this.state = 0;
                return true;
            }
        }
        return false;
    }
    
    public Member(String key, BigDecimal camp) {
        this.key = key;
        this.camp = camp;
    }
    
    public String getKey() {
        return this.key;
    }
    
    public void setKey(String key) {
        this.key = key;
    }
    
    public BigDecimal getCamp() {
        return this.camp;
    }
    
    public void setCamp(BigDecimal camp) {
        this.camp = camp;
    }
    
    public int getState() {
        return this.state;
    }
    
    public void setState(int state) {
        this.state = state;
    }
    
    public long getTime() {
        return this.time;
    }
    
    public void setTime(long time) {
        this.time = time;
    }
    
    public long getTime2() {
        return this.time2;
    }
    
    public void setTime2(long time2) {
        this.time2 = time2;
    }
}
