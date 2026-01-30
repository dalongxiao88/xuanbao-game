package come.tool.Fighting;

public class FightingSkill implements Cloneable
{
    private int skillid;
    private String skillname;
    private String skilltype;
    
    public int getSkillid() {
        return this.skillid;
    }
    
    public void setSkillid(int skillid) {
        this.skillid = skillid;
    }
    
    public String getSkillname() {
        return this.skillname;
    }
    
    public void setSkillname(String skillname) {
        this.skillname = skillname;
    }
}
