package org.come.bean;

import java.util.Map;

public class SkillBean
{
    private Map<String, Skill> skillMap;
    
    public Map<String, Skill> getSkillMap() {
        return this.skillMap;
    }
    
    public void setSkillMap(Map<String, Skill> skillMap) {
        this.skillMap = skillMap;
    }
}
