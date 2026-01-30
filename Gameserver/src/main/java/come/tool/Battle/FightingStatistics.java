package come.tool.Battle;

public class FightingStatistics
{
    private int camp;
    private Integer lingNum;
    private Integer petNum;
    private String tName;
    
    public int getCamp() {
        return this.camp;
    }
    
    public void setCamp(int camp) {
        this.camp = camp;
    }
    
    public int getLingNum() {
        if (this.lingNum == null) {
            this.lingNum = 0;
        }
        return (int)this.lingNum;
    }
    
    public void setLingNum(Integer lingNum) {
        this.lingNum = lingNum;
    }
    
    public int getPetNum() {
        if (this.petNum == null) {
            this.petNum = 0;
        }
        return (int)this.petNum;
    }
    
    public void setPetNum(Integer petNum) {
        this.petNum = petNum;
    }
    
    public String gettName() {
        return this.tName;
    }
    
    public void settName(String tName) {
        this.tName = tName;
    }
}
