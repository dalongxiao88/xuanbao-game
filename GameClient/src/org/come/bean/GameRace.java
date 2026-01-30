package org.come.bean;

import java.math.BigDecimal;

public class GameRace
{
    private BigDecimal race_id;
    private String race_name;
    
    public BigDecimal getRace_id() {
        return this.race_id;
    }
    
    public void setRace_id(BigDecimal race_id) {
        this.race_id = race_id;
    }
    
    public String getRace_name() {
        return this.race_name;
    }
    
    public void setRace_name(String race_name) {
        this.race_name = race_name;
    }
}
