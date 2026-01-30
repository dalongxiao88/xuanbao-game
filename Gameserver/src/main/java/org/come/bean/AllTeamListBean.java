package org.come.bean;

import java.util.List;
import java.math.BigDecimal;
import java.util.Map;

public class AllTeamListBean
{
    private Map<BigDecimal, List<LoginResult>> teamMembers;
    
    public Map<BigDecimal, List<LoginResult>> getTeamMembers() {
        return this.teamMembers;
    }
    
    public void setTeamMembers(Map<BigDecimal, List<LoginResult>> teamMembers) {
        this.teamMembers = teamMembers;
    }
}
