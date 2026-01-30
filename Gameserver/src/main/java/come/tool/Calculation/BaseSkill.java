package come.tool.Calculation;

import org.come.server.GameServer;
import org.come.model.Skill;
import come.tool.FightingData.FightingSkill;

public class BaseSkill extends FightingSkill
{
    private int skillId;
    private int lvl;
    private double pz;
    private Skill skill;
    private BaseQl ql;
    
    public BaseSkill() {
    }
    
    public boolean isAffect() {
        return (this.skillId >= 6001 && this.skillId <= 6017) || this.skillId == 6030 || this.skillId == 6035 || this.skillId == 6036 || this.skillId == 6039 || this.skillId == 6031 || this.skillId == 6032 || (this.skillId >= 8001 && this.skillId <= 8023) || (this.skillId >= 8030 && this.skillId <= 8036) || (this.skillId >= 8038 && this.skillId <= 8039);
    }
    
    public BaseSkill(int skillId, int lvl) {
        this.skillId = skillId;
        this.lvl = lvl;
    }
    
    public BaseSkill(int skillId, int lvl, double pz, Skill skill) {
        this.skillId = skillId;
        this.lvl = lvl;
        this.pz = pz;
        this.skill = skill;
    }
    
    public BaseSkill(int skillId, int lvl, Skill skill, BaseQl ql) {
        this.skillId = skillId;
        this.lvl = lvl;
        this.skill = skill;
        this.ql = ql;
    }
    
    public int getSkillId() {
        return this.skillId;
    }
    
    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }
    
    public int getLvl() {
        return this.lvl;
    }
    
    public void setLvl(int lvl) {
        this.lvl = lvl;
    }
    
    public double getPz() {
        return this.pz;
    }
    
    public void setPz(double pz) {
        this.pz = pz;
    }
    
    public Skill getSkill() {
        if (this.skill == null) {
            this.skill = GameServer.getSkill(this.skillId + "");
        }
        return this.skill;
    }
    
    public void setSkill(Skill skill) {
        this.skill = skill;
    }
    
    public BaseQl getQl() {
        return this.ql;
    }
    
    public void setQl(BaseQl ql) {
        this.ql = ql;
    }
}
