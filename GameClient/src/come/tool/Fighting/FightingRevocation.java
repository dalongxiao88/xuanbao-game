package come.tool.Fighting;

public class FightingRevocation
{
    private Integer man;
    private Integer fightingMixDealState;
    private Integer type;
    
    public FightingRevocation() {
        this.type = Integer.valueOf(-1);
    }
    
    public Integer getMan() {
        return this.man;
    }
    
    public void setMan(Integer man) {
        this.man = man;
    }
    
    public Integer getFightingMixDealState() {
        return this.fightingMixDealState;
    }
    
    public void setFightingMixDealState(Integer fightingMixDealState) {
        this.fightingMixDealState = fightingMixDealState;
    }
    
    public Integer getType() {
        return this.type;
    }
    
    public void setType(Integer type) {
        this.type = type;
    }
}
