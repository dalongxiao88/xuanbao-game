package org.come.bean;

import java.math.BigDecimal;

public class RoleChangeBean
{
    private String race_name;
    private BigDecimal species_id;
    
    public String getRace_name() {
        return this.race_name;
    }
    
    public void setRace_name(String race_name) {
        this.race_name = race_name;
    }
    
    public BigDecimal getSpecies_id() {
        return this.species_id;
    }
    
    public void setSpecies_id(BigDecimal species_id) {
        this.species_id = species_id;
    }
}
