package org.come.model;

import org.come.action.role.RoleTransAction;
import come.tool.FightingData.Sepcies_MixDeal;
import org.apache.commons.lang.StringUtils;
import java.math.BigDecimal;

public class GolemDraw
{
    private String type;
    private String race;
    private BigDecimal id;
    private String value;
    
    public boolean isMatching(BigDecimal speciesId) {
        if (StringUtils.isBlank(this.race)) {
            return true;
        }
        if (this.race.equals("全部")) {
            return true;
        }
        if (Sepcies_MixDeal.getLocalName(speciesId.intValue()).equals(this.race)) {
            return true;
        }
        String race = RoleTransAction.getSepciesN(speciesId);
        return race != null && race.equals(this.race);
    }
    
    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getRace() {
        return this.race;
    }
    
    public void setRace(String race) {
        this.race = race;
    }
    
    public BigDecimal getId() {
        return this.id;
    }
    
    public void setId(BigDecimal id) {
        this.id = id;
    }
    
    public String getValue() {
        return this.value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
}
