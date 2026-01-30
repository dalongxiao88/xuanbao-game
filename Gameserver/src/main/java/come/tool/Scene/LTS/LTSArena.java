package come.tool.Scene.LTS;

import come.tool.PK.PkMatch;

public class LTSArena
{
    private int lId;
    private int lLvl;
    private int battleNumber;
    private PkMatch pkMatch;
    private int minLvl;
    private int maxLvl;
    
    public LTSArena(int lId) {
        this.lId = lId;
        if (this.lId == 510 || this.lId == 511) {
            this.minLvl = 399;
            this.maxLvl = 438;
            this.lLvl = 1;
        }
        else if (this.lId == 512 || this.lId == 513) {
            this.minLvl = 439;
            this.maxLvl = 459;
            this.lLvl = 2;
        }
        else {
            this.minLvl = 460;
            this.maxLvl = 520;
            this.lLvl = 3;
        }
    }
    
    public boolean isLvl(int lvl) {
        return lvl >= this.minLvl && lvl <= this.maxLvl;
    }
    
    public int getlId() {
        return this.lId;
    }
    
    public void setlId(int lId) {
        this.lId = lId;
    }
    
    public int getBattleNumber() {
        return this.battleNumber;
    }
    
    public void setBattleNumber(int battleNumber) {
        this.battleNumber = battleNumber;
    }
    
    public PkMatch getPkMatch() {
        return this.pkMatch;
    }
    
    public void setPkMatch(PkMatch pkMatch) {
        this.pkMatch = pkMatch;
    }
    
    public int getMinLvl() {
        return this.minLvl;
    }
    
    public void setMinLvl(int minLvl) {
        this.minLvl = minLvl;
    }
    
    public int getMaxLvl() {
        return this.maxLvl;
    }
    
    public void setMaxLvl(int maxLvl) {
        this.maxLvl = maxLvl;
    }
    
    public int getlLvl() {
        return this.lLvl;
    }
    
    public void setlLvl(int lLvl) {
        this.lLvl = lLvl;
    }
}
