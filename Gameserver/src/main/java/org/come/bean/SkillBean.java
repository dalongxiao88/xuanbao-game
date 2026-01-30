package org.come.bean;

import org.come.model.SkillModel;
import java.util.Map;

public class SkillBean
{
    private Map<String, SkillModel> skillMap;
    
    public Map<String, SkillModel> getSkillMap() {
        return this.skillMap;
    }
    
    public void setSkillMap(Map<String, SkillModel> skillMap) {
        this.skillMap = skillMap;
    }
}
