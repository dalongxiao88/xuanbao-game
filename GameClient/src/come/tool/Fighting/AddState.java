package come.tool.Fighting;

import java.util.List;

public class AddState
{
    private String statename;
    private int type;
    private int Surplus;
    private int Ongoing;
    private List<FightingSkill> skills;
    
    public String getStatename() {
        if (this.statename == null) {
            this.statename = "";
        }
        return this.statename;
    }
    
    public int getSurplus() {
        return this.Surplus;
    }
    
    public int getType() {
        return this.type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    
    public List<FightingSkill> getSkills() {
        return this.skills;
    }
    
    public void setSkills(List<FightingSkill> skills) {
        this.skills = skills;
    }
}
